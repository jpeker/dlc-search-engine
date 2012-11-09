using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;


/// <summary>
/// Descripción breve de Seguridad
/// </summary>
public static class Seguridad
{
	public static bool ValidarUsuario(string usuario, string password)
{
    String sql = "Select * from Usuarios where Nombre = '" + usuario + "' and Password = '" + password+"'";
    SqlDataReader da = Datos.getDataReader(sql, Datos.ObtenerConexion());
    return da.HasRows;
}
    public static string ObtenerRoles(string usuario)
    {
        string roles="";
        String sql = "Select r.nombre from Usuarios u inner join Roles r on u.IdUsuario = r.IdUsuario where u.Nombre ='" + usuario + "'";
        SqlDataReader da = Datos.getDataReader(sql, Datos.ObtenerConexion());
       
        while (da.Read())
        {
            
            roles += da["nombre"].ToString() + " | ";
        }
        return roles;
        }
}