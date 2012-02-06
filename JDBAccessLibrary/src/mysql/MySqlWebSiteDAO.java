package mysql;

import dbmanager.*;
import dao.*;
import beans.WebSite;
import beans.Word;
import java.util.LinkedList;
import java.sql.*;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Collections.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
//import notificador.Notificador;
//import notificador.ProcesadorDeNotificacion.Evento;

/**
 * Esta clase implementa la interface de WebSiteDAO para la base de datos MySQL.
 * 
 * @author Christian Adam
 * @author Federico Schaefer
 */
public class MySqlWebSiteDAO implements WebSiteDAO {

    /**
     * Graba la website en la base de datos. Si no existia la inserta y si existia
     * la actualiza
     * @param website website a grabar
     * @return true si se pudo grabar con éxito, false en caso contrario
     */
    public boolean grabarWebSite(WebSite website) {
        boolean ret = false;
        if (this.obtenerId(website) != -1) {
            ret = this.actualizarWebSite(website);
        } else {
            ret = this.insertarWebSite(website);
        }
        return ret;
    }

    /**
     * Obtiene la website desde la base de datos que se corresponde a la 
     * website de busqueda dada.
     * @param website website que se desea buscar en la base de datos
     * @return la website de la base de datos, null en caso que no exista o se
     * produzca algun error.
     */
    public WebSite obtenerWebSite(WebSite website) {
        WebSite ret = null;
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized (con) {
                String query = "Select idUrl, timestamp, estado, titulo, base, url from website where hash = ?";
                st = con.prepareStatement(query);
                st.setInt(1, website.hashCode());
                ResultSet results = st.executeQuery();
                if (results.next()) {
                    ret = new WebSite(results.getString("titulo"), results.getString("url"), results.getBoolean("estado"), results.getLong("timestamp"), results.getBoolean("base"));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
             //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener website por objeto website: " + website.getUrl(), ex);
        }
        return ret;
    }

    /**
     * Obtiene la website de la base de datos que se corresponde con el id 
     * pasado por parámetro.
     * @param idUrl el id de la website en la base de datos
     * @return la website correspondiente de la base de datos, null en caso de que
     * no exista una website para ese id, o si sucedió algun error.
     */
    public WebSite obtenerWebSite(int idUrl) {
        WebSite ret = null;
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized (con) {
                String query = "Select idUrl, timestamp, estado, titulo, base, url from website where idUrl = ?";
                st = con.prepareStatement(query);
                st.setInt(1, idUrl);
                ResultSet results = st.executeQuery();
                if (results.next()) {
                    ret = new WebSite(results.getString("titulo"), results.getString("url"), results.getBoolean("estado"), results.getLong("timestamp"), results.getBoolean("base"));
                }
                //No obtener las palabras de esa website por default, cargarlas por lazy load.
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
             //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener website por ID: " + idUrl, ex);
        }
        return ret;
    }
    
    /**
     * Obtiene las palabras y frecuencia de las mismas para la website dada.
     * @param website website cuyas palabras se desean conocer
     * @return Un Hashtable<Palabra,Long> donde la KEY es la palabra y el VALUE
     * es la frecuencia de esa palabra. El hashcode utilizado en la KEY para Word
     * esta basado en el string propio de la palabra.
     */
    public Hashtable<Word, Long> obtenerPalabrasDeWebSite(WebSite website) {
        Hashtable<Word, Long> ret = new Hashtable<Word, Long>();
        Connection con = MySqlDBManager.getConnection();
        PreparedStatement st;
        try {
            synchronized (con) {
                String palabrasQuery = "Select p.palabra, lp.fr from listaposteo as lp, website as w, palabra as p "
                        + "where lp.idUrl = w.idUrl and p.idPalabra = lp.idPalabra and w.hash = ?";
                st = con.prepareStatement(palabrasQuery);
                st.setInt(1, website.hashCode());
                ResultSet results = st.executeQuery();
                PalabraDAO palabraDAO = new MySqlPalabraDAO();
                while (results.next()) {
                    ret.put(palabraDAO.obtenerPalabra(new Word(results.getString("palabra"))), new Long(results.getLong("fr")));
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al obtener palabras de website: " + website.getUrl(), ex);
        }
        return ret;
    }
    
    /**
     * Elimina la website de la base de datos
     * @param website la website que se desea eliminar
     * @return true si la website se pudo eliminar exitosamente, false en caso
     * contrario
     */
    public boolean eliminarWebSite(WebSite website) {
        boolean ret = false;
        //Si la website no existe no hay nada que borrar
        int idUrl = this.obtenerId(website);
        if (idUrl == -1) {
            return ret;
        }
        PreparedStatement st;
        Connection con = MySqlDBManager.getConnection();
        synchronized (con) {
            try {
                //Para borrar la website necesito antes borrar su lista de posteo

                //Empezar transacción transaccion
                con.setAutoCommit(false);

                //Borrar la lista de posteo de la website
                String queryListaPosteo = "DELETE FROM listaposteo "
                        + "where idUrl = ?";

                st = con.prepareStatement(queryListaPosteo);
                st.setInt(1, idUrl);
                st.executeUpdate();

                //Borrar la website
                String queryWebSite = "DELETE FROM website "
                        + "where idUrl = ? ";

                st = con.prepareStatement(queryWebSite);
                st.setInt(1, idUrl);
                st.executeUpdate();

                //Finaliza transacción
                con.commit();
                st.close();
                ret = true;

            } catch (SQLException ex) {
                 //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Excepción SQL al intentar borrar website: " + website.getUrl(), ex);
                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                         //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Excepción SQL al tratar de hacer rollback de eliminación de website: " + website.getUrl(), ex);
                    }
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                     //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Excepción SQL al tratar de establecer la propiedad setAutoCommit en true para la conexión", ex);
                }
            }
        }
        return ret;
    }

    /**
     * Obtiene el id de la base de datos para la website dada.
     * @param website website de la cual se desea saber su id de base de datos
     * @return el id de la base de datos o -1 si no se pudo determinar el mismo
     */
    public int obtenerId(WebSite website) {
        int ret = -1;
        if(website==null){return ret;}
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized (con) {
                String query = "Select idUrl from website where hash = ?";
                st = con.prepareStatement(query);
                st.setInt(1, website.hashCode());
                ResultSet results = st.executeQuery();
                if (results.first()) {
                    ret = results.getInt("idUrl");
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
             //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Error al intentar obtener id de website: " + website.getUrl(), ex);
        }
        return ret;
    }
    
    /**
     * Inserta una website en la base de datos
     * @param website website a insertar
     * @return true si se pudo insertar exitosamente, false en caso contrario
     */
    public boolean insertarWebSite(WebSite website) {

        int idUrl = this.obtenerId(website);

        //Si ya existia entonces no lo puedo insertar. Tampoco si es vacia la URL.
        if (idUrl != -1 || website.getUrl().isEmpty()) {
            return false;
        }

        boolean ret = true;
        PreparedStatement st;
        Connection con = MySqlDBManager.getConnection();
        synchronized (con) {
            try {
                //1. Comienza la transacción
                con.setAutoCommit(false);

                String query = "INSERT INTO website (timestamp, estado, titulo, base, url, hash) VALUES (?,?,?,?,?,?)";
                st = con.prepareStatement(query);

                st.setLong(1, website.getTimeStamp());
                st.setBoolean(2, website.isEstado());
                st.setString(3, website.getTitulo());
                st.setBoolean(4, website.isEsBase());
                st.setString(5, website.getUrl());
                st.setInt(6, website.hashCode());

                int res = st.executeUpdate();
                //2. Si la palabra se pudo insertar y tiene palabras entonces a incertar palabra por palabra
                if (res == 1 && !website.isEsBase() && website.getPalabras() != null && !website.getPalabras().isEmpty()) {
                    this.grabarListaPosteo(website, con);
                }

                //3. Confirmamos la transacción.
                con.commit();
                st.close();

            } catch (SQLException ex) {
                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                         //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Excepción SQL al intentar insertar website: " + website.getUrl(), ex);
                    }
                }
                ret = false;
                //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Excepción SQL al tratar de hacer rollback de inserción de website: " + website.getUrl(), ex);
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Excepción SQL al tratar de establecer la propiedad setAutoCommit en true para la conexión", ex);
                }
            }
        }
        return ret;
    }

    /**
     * Graba la lista de posteo de una website a la base de datos
     * @param website la website cuya lista de posteo se desea grabar
     * @param con la conexion a la base de dato que comenzó la transacción de
     * insertar o actualizar esta website.
     * @return true si se pudo grabar exitosamente, false en caso contrario
     */
    private boolean grabarListaPosteo(WebSite website, Connection con) {
        if (con == null) {
             //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"Conexión nula al intentar grabar lista de posteo de website: " + website.getUrl(), null);
             return false;
        }
        int idWebSite = this.obtenerId(website);
        int duplicated = 0;
        if (idWebSite != -1) {
            //Preparamos los statements de INSERCION y UPDATE
            PreparedStatement insertStatement = null;
            String insertQuery = "INSERT INTO listaposteo (idPalabra, idUrl, fr) VALUES (?,?,?)";
            try {
                insertStatement = con.prepareStatement(insertQuery);
            } catch (SQLException ex) {
                 //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"No se pudo armar el statement de inserción al grabar lista de posteo de website: " + website.getUrl(), ex);
                try {
                    if (insertStatement != null) {
                        insertStatement.close();
                    }
                } catch (SQLException ex1) {
                     //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"No se pudo cerrar el statement de inserción al grabar lista de posteo de website: " + website.getUrl(), ex1);
                }
                return false;
            }

            PreparedStatement updateStatement = null;
            String updateQuery = "UPDATE listaposteo "
                    + "SET fr = ? "
                    + "where idPalabra = ? and idUrl = ? ";
            try {
                updateStatement = con.prepareStatement(updateQuery);
            } catch (SQLException ex) {
                 //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"No se pudo armar el statement de actualización al grabar lista de posteo de website: " + website.getUrl(), ex);
                try {
                    if (updateStatement != null) {
                        updateStatement.close();
                    }
                } catch (SQLException ex1) {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR,"No se pudo cerrar el statement de actualización al grabar lista de posteo de website: " + website.getUrl(), ex1);
                }
                return false;
            }

            //3. Vemos las palabras de la website
            Hashtable<Word, Long> palabras = website.getPalabras();

            int inserts = 0;
            int updates = 0;


            HashSet<Integer> palabrasProcesadas = new HashSet<Integer>();
            Enumeration e = palabras.keys();
            while (e.hasMoreElements()) {
                Word palabraCandidataAInsertar = (Word) e.nextElement();
                Long frecuencia = palabras.get(palabraCandidataAInsertar);
                MySqlPalabraDAO palabraDAO = new MySqlPalabraDAO();
                int idPalabra = palabraDAO.obtenerId(palabraCandidataAInsertar);

                //Si la palabra no existia entonces tenemos que grabarla!
                if (idPalabra == -1) {
                    palabraDAO.grabarPalabra(palabraCandidataAInsertar);
                    idPalabra = palabraDAO.obtenerId(palabraCandidataAInsertar);
                }

                if (palabrasProcesadas.contains(new Integer(idPalabra))) {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Se encontró palabra duplicada para la website: " + website.getUrl() + ". Total de duplicadas: " + duplicated, null);
                    duplicated++;
                    continue;
                } else {
                    palabrasProcesadas.add(new Integer(idPalabra));
                }

                Word palabraAInsertar = palabraDAO.obtenerPalabra(palabraCandidataAInsertar);

                //4. Aseguramos que la palabra esta en la BD
                if (palabraAInsertar != null) {

                    /*5. Ahora si sabemos que efectivamente tenemos una website y una palabra
                     *   con un id válido. A insertar en la lista de posteo!
                     */  //Verificamos que la entrada existe. (Ya estaba registrada esta palabra para esta website)
                    try {

                        boolean firstTimeInPage = false;

                        if (!yaExistePalabraParaWebSite(idPalabra, idWebSite)) {
                            insertStatement.setInt(1, idPalabra);
                            insertStatement.setInt(2, idWebSite);
                            insertStatement.setLong(3, frecuencia);

                            insertStatement.addBatch();

                            inserts++;

                            firstTimeInPage = true;
                        } else {
                            updateStatement.setLong(1, frecuencia);
                            updateStatement.setInt(2, idPalabra);
                            updateStatement.setInt(3, idWebSite);

                            updateStatement.addBatch();

                            updates++;
                        }

                        if (firstTimeInPage) {
                            long newNr = palabraAInsertar.getNr() + 1;
                            palabraAInsertar.setNr(newNr);
                            palabraDAO.grabarPalabra(palabraAInsertar);
                        }

                        if (inserts > 1000) {
                            synchronized (con) {
                                insertStatement.executeBatch();
                            }
                            inserts = 0;
                        }

                        if (updates > 1000) {
                            synchronized (con) {
                                updateStatement.executeBatch();
                            }

                            updates = 0;
                        }

                    } catch (SQLException ex) {
                        //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Sucedio una excepción en la BD al intentar grabar la lista de posteo de la website: " + website.getUrl(), ex);
                        return false;
                    }
                } else {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "No se puede grabar la palabra: " + palabraCandidataAInsertar.getPalabra() + " en la lista"
                           // + "de posteo de la website " + website.getUrl() + " porque la palabra no se grabó en la BD" , null);
                }
            }
            try {
                if (inserts > 0) {
                    synchronized (con) {
                        insertStatement.executeBatch();
                    }

                }
                if (updates > 0) {
                    synchronized (con) {

                        updateStatement.executeBatch();
                    }

                }
            } catch (BatchUpdateException ey) {
                //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Sucedio una excepción en la BD al intentar grabar la lista de posteo de la website: " + website.getUrl(), ey);
            } catch (SQLException ex) {
                //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Sucedio una excepción en la BD al intentar grabar la lista de posteo de la website: " + website.getUrl(), ex);
            } finally {
                try {
                    if (insertStatement != null) {
                        insertStatement.close();
                    }
                    if (updateStatement != null) {
                        updateStatement.close();
                    }
                } catch (SQLException ex) {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Sucedio una excepción en la BD al intentar grabar la lista de posteo de la website: " + website.getUrl(), ex);
                }
            }
        }
        return true;
    }

    /**
     * Determina si la palabra ya existe para la website dada
     * 
     * @param idPalabra id de la base de datos de la palabra en cuestión
     * @param idWebSite id de la base de datos de la website en cuestión
     * 
     * @return true si ya existia, false en caso contrario
     */
    private boolean yaExistePalabraParaWebSite(int idPalabra, int idWebSite) {
        boolean ret = false;
        Connection con = MySqlDBManager.getConnection();
        PreparedStatement st;
        String query = "Select idPalabra from listaposteo where idPalabra=? and idUrl=?";
        synchronized (con) {
            try {
                st = con.prepareStatement(query);
                st.setInt(1, idPalabra);
                st.setInt(2, idWebSite);
                ResultSet result = st.executeQuery();
                if (result.first()) {
                    ret = true;
                }
                result.close();
                st.close();
            } catch (SQLException ex) {
                //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de verificar si la palabra con id " + idPalabra + " ya existia para la website con id " + idWebSite, ex);
            }
        }

        return ret;
    }
    
    /**
     * Actualiza los datos de la website en la base de datos
     * @param website website a actualizar
     * @return true si se pudo actualizar exitosamente, false en caso contrario
     */
    public boolean actualizarWebSite(WebSite website) {

        int idUrl = this.obtenerId(website);

        //Si la pagina no estaba entonces no la puedo actualizar. Tampoco la puedo actualizar si es vacia la URL.
        if (this.obtenerId(website) == -1 || website.getUrl().isEmpty()) {
            return false;
        }

        boolean ret = true;
        PreparedStatement st;
        Connection con = MySqlDBManager.getConnection();

        synchronized (con) {

            try {
                con.setAutoCommit(false);

                String query = "UPDATE website "
                        + "SET timestamp=?, "
                        + "estado=?, "
                        + "titulo=?, "
                        + "base=? "
                        + "where idUrl=? ";
                st = con.prepareStatement(query);

                st.setLong(1, website.getTimeStamp());
                st.setBoolean(2, website.isEstado());
                st.setString(3, website.getTitulo());
                st.setBoolean(4, website.isEsBase());
                st.setInt(5, idUrl);
                int res = st.executeUpdate();
                if (res == 1 && website.getPalabras() != null && !website.getPalabras().isEmpty()) {
                    this.grabarListaPosteo(website, con);
                }

                con.commit();

                st.close();
            } catch (SQLException ex) {
                if (con != null) {
                    try {
                        con.rollback();
                    } catch (SQLException ex1) {
                        //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de hacer rollback al actualizar website: " + website.getUrl(), ex);
                    }
                }
                ret = false;
                //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de actualizar website: " + website.getUrl(), ex);
            } finally {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException ex) {
                    //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de cambiar la propiedad auto commit al actualizar website: " + website.getUrl(), ex);
                }
            }
        }
        return ret;
    }
    
    /**
     * Obtiene las websites bases almacenadas en la base de datos
     * @param rowLimit cantidad de websites bases que se desea obtener
     * @return una lista con las websites base
     */
    public LinkedList<WebSite> obtenerWebSitesBase(int rowLimit) {
        LinkedList<WebSite> ret = new LinkedList<WebSite>();
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            
            synchronized (con) {
                String query = "Select idUrl, timestamp, estado, titulo, base, url from website where base = ? limit ?";
                st = con.prepareStatement(query);
                st.setBoolean(1, true);
                st.setInt(2, rowLimit);
                ResultSet results = st.executeQuery();
                while (results.next()) {
                    WebSite wRet = new WebSite(results.getString("titulo"), results.getString("url"), results.getBoolean("estado"), results.getLong("timestamp"), results.getBoolean("base"));
                    ret.add(wRet);
                }
                results.close();
                st.close();
            }
         } catch (SQLException ex) {
           //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de obtener websites bases", ex);
        }
        return ret;
    }
    /**
     * Obtiene la lista de websites analizables
     * @param cantidad cantidad de websites analizables que se desean obtener
     * @return lista de websites analizables
     */
    public LinkedList<WebSite> obtenerWebSitesAnalizables(int cantidad) {
        //Esta lista es la que será retornada
        LinkedList<WebSite> analizables = new LinkedList<WebSite>();

        //Defino listas auxiliares que voy a necesitar
        LinkedList<WebSite> analizablesOffline;
        LinkedList<WebSite> analizablesOnline;

        //Ver que pasa aca
//        analizablesOffline = this.obtenerWebSiteAnalizables(false, Setting.getInstancia().getReScanOffline() * 1000, (int)(cantidad/2));
//        analizablesOnline = this.obtenerWebSiteAnalizables(true, Setting.getInstancia().getReScanOnline() * 1000, (int)(cantidad/2));
//
//        if (analizablesOffline != null) {
//            analizables.addAll(analizablesOffline);
//        }
//        if (analizablesOnline != null) {
//            analizables.addAll(analizablesOnline);
//        }

        return analizables;
    }

    /**
     * Obtiene las websites analizables de la base de datos
     * @param estado estado de las websites analizables a obtener
     * @param reScan tiempo en milisengundos que tiene que haber transcurrido 
     * desde el último escaneo para considerar que las websites son analizables
     * @param cantidad cantidad de websites analizables a obtener
     * @return lista de websites analizables
     */
    private LinkedList<WebSite> obtenerWebSiteAnalizables(boolean estado, long reScan, int cantidad) {
        LinkedList<WebSite> webSiteAnalizables = new LinkedList<WebSite>();
        long limit = System.currentTimeMillis() - reScan;
        PreparedStatement st;
        Connection con;
        try {
            con = MySqlDBManager.getConnection();
            synchronized (con) {
                String query = "Select url from website where estado = ? and timestamp <= ? limit ?";
                st = con.prepareStatement(query);
                st.setBoolean(1, estado);
                st.setLong(2, limit);
                st.setInt(3, cantidad);
                ResultSet results = st.executeQuery();
                while (results.next()) {
                    webSiteAnalizables.add(this.obtenerWebSite(new WebSite(results.getString("url"))));
                }
                results.close();
                st.close();
            }
         } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de obtener websites analizables", ex);
            webSiteAnalizables = null;
        }

        return webSiteAnalizables;
    }
    
    /**
     * Obtiene la cantidad de websites analizadas, o sea que poseen palabras
     * asociadas.
     * @return cantidad de websites indexadas
     */
    public long getCantidadWebsitesIndexadas() {
        long cantWebSitesIndexadas = 0;
        Connection con;
        Statement st;
        try {
            con = MySqlDBManager.getConnection();
            st = con.createStatement();
            synchronized (con) {
               String query = "Select Count(distinct idUrl) as cantWebSites from dlc.listaposteo";
                ResultSet results = st.executeQuery(query);
                if (results.next()) {
                    cantWebSitesIndexadas = results.getLong("cantWebSites");
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de obtener la cantidad de websites indexadas", ex);
        }
        return cantWebSitesIndexadas;
    }

    /**
     * Reporta las websites base 
     * @param websites websites bases a reportar
     */
    public void reportarWebSitesBase(LinkedList<WebSite> websites) {
         if(websites==null){return;}
        //Descartar aquellas websites que ya existen en la base!
        Iterator<WebSite> it = websites.iterator();
        while(it.hasNext())
        {
            if(obtenerId(it.next())!=-1)
            {
                it.remove();
            }
        }
        if(websites.size()==0){return;}       
        PreparedStatement st;
        Connection con = MySqlDBManager.getConnection();
        synchronized (con) {
           try {
                String query = "INSERT INTO website (timestamp, estado, titulo, base, url, hash) VALUES (?,?,?,?,?,?)";
                st = con.prepareStatement(query);

                int i = 0;

                for (WebSite website : websites) {
                    //Ignorar websites nulas, que NO sean base o que tengan URL nulas. 
                    if (website == null || this.obtenerId(website) != -1 || !website.isEsBase() || website.getUrl().isEmpty()) {
                        continue;
                    }

                    st.setLong(1, website.getTimeStamp());
                    st.setBoolean(2, website.isEstado());
                    st.setString(3, website.getTitulo());
                    st.setBoolean(4, website.isEsBase());
                    st.setString(5, website.getUrl());
                    st.setInt(6, website.hashCode());

                    st.addBatch();

                    i++;

                    if (i >= 1000) {
                        st.executeBatch();
                        i = 0;
                    }
                }
                if (i > 0) {
                    st.executeBatch();
                }
                st.close();


            } catch (SQLException ex) {
                //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de reportar websites bases", ex);
            }
        }
    }

    /**
     * Obtiene la frecuencia de una palabra para una website dada
     * @param site website sobre la cual se desea saber la frecuencia
     * @param palabra palabra sobre la cual se desea saber la frecuencia
     * @return numero de ocurrencias de la palabra en la website dadas, -1 si
     * la palabra o website no existen en la base de datos.
     */
    public long getFrecuenciaDePalabra(WebSite site, Word palabra) {

        MySqlPalabraDAO palabraDAO = new MySqlPalabraDAO();
        int idUrl = this.obtenerId(site);
        int idPalabra = palabraDAO.obtenerId(palabra);
        if (idUrl == -1 || idPalabra == -1) {
            return -1;
        }       
        long fr = 0;
        Connection con;
        PreparedStatement st;
        try {
            con = MySqlDBManager.getConnection();

            synchronized (con) {

                String query = "Select fr as frecuencia from dlc.listaposteo"
                        + "where idUrl = ? and idPalabra = ?";

                st = con.prepareStatement(query);
                st.setInt(1, idUrl);
                st.setInt(2, idPalabra);

                ResultSet results = st.executeQuery();

                if (results.next()) {
                    fr = results.getLong("frecuencia");
                }

                results.close();
                st.close();

            }
         } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de obtener frecuencia de palabra " + palabra.getPalabra() + " en website " + site.getUrl(), ex);
        }
        return fr;
    }

    /**
     * Para la palabra pasada por parámetro, devuelve la lista de websites en donde
     * la palabra esta presente junto con la frecuencia de ocurrencias en cada
     * una de ellas.
     * @param palabra palabra de la cual se desea saber las websites y frecuencias
     * @return un HashMap donde la "key" es la website y el "value" es la cantidad de
     * ocurrencias de la palabra en dicha website. Retorna null si la palabra no
     * esta registrada en la base.
     */
    public HashMap<WebSite, Long> getWebSitesDePalabra(Word palabra) {

        MySqlPalabraDAO palabraDAO = new MySqlPalabraDAO();
        int idPalabra = palabraDAO.obtenerId(palabra);
        if (idPalabra == -1) {
            return null;
        }
        HashMap<WebSite, Long> sitesDePalabra = new HashMap<WebSite, Long>();
        Connection con;
        PreparedStatement st;
        try {

            con = MySqlDBManager.getConnection();
           
            synchronized (con) {
                String query = "Select idUrl, fr from dlc.listaposteo "
                        + "where idPalabra = ?";

                st = con.prepareStatement(query);
                st.setInt(1, idPalabra);

                ResultSet results = st.executeQuery();

                while (results.next()) {
                    int idUrl = results.getInt("idUrl");
                    WebSite insertar = this.obtenerWebSite(idUrl);
                    long fr = results.getLong("fr");
                    sitesDePalabra.put(insertar, fr);
                }
                results.close();
                st.close();
            }
        } catch (SQLException ex) {
            //Notificador.getInstancia().reportar(Evento.CONDICION_DE_ERROR, "Excepción al tratar de obtener websites de palabra: " + palabra.getPalabra(), ex);
        }
        return sitesDePalabra;
    }
}