using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Configuration;

public partial class GestionTipos : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Gestion Tipo";
        if (!Page.IsPostBack)
        {
            CargarListaTipos();
            ddlIncidentes.Items.Insert(0, new ListItem("Buscar", "0"));
        }

    }
    private void CargarListaTipos()
    {
        string cnStr = ConfigurationManager.ConnectionStrings["helpdeskcn"].ConnectionString;
        string cmdStr = "SELECT * FROM tipos";
        SqlConnection cn = new SqlConnection(cnStr);
        try
        {
            cn.Open();
            SqlCommand cmd = new SqlCommand(cmdStr, cn);
            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                ddlIncidentes.Items.Add(new
                ListItem(dr["Tipo"].ToString(), dr["IdTipo"].ToString()));
            }
            dr.Close();
        }
        catch (Exception err)
        {
            lblStatus.Visible = true;
            lblStatus.Text = "Error al conectarse a la DB: ";
            lblStatus.Text += err.Message;
        }
        finally
        {
            cn.Close();
        }
    }
    protected void ddlIncidentes_SelectedIndexChanged(object sender, EventArgs e)
    {
        CargarDatosTipo();
    }
    private void CargarDatosTipo()
    {
        string cnStr =
        ConfigurationManager.ConnectionStrings["helpdeskcn"].ConnectionString;
      
        SqlConnection cn = new SqlConnection(cnStr);
        try
        {
            cn.Open();
            string cmdStr = "SELECT * FROM tipos WHERE IdTipo = @id";
      
            SqlCommand cmd = new SqlCommand(cmdStr, cn);
            cmd.Parameters.AddWithValue("@id",ddlIncidentes.SelectedItem.Value);
            SqlDataReader dr = cmd.ExecuteReader();
            if (dr.Read())
                lblIdTipo.Text = dr["IdTipo"].ToString();
            txtDescripcion.Text = dr["Tipo"].ToString();
            txtTiempo.Text =
            dr["TiempoPromedioResolucion"].ToString();
            dr.Close();
        }
        catch (Exception err)
        {
            lblStatus.Visible = true;
            lblStatus.Text = "Error al conectarse a la Base de Datos:";
            lblStatus.Text += err.Message;
        }
        finally
        {
            cn.Close();
        }
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            using (SqlConnection con = Datos.ObtenerConexion())
            {
                SqlTransaction tran = con.BeginTransaction();
                String cadena = "insert into Tipos (Tipo,TiempoPromedioResolucion) values (@tipo,@resolu)";
                SqlCommand cmd = new SqlCommand(cadena, con);
                cmd.Parameters.AddWithValue("@tipo",txtDescripcion.Text);
                cmd.Parameters.AddWithValue("@resolu", txtTiempo.Text);
                cmd.Transaction = tran;
                try
                {
                    cmd.ExecuteNonQuery();
                    tran.Commit();
                    lblStatus.Text = "se guardo con exito";
                 


                }
                catch (Exception ex)
                {
                    tran.Rollback();
                    lblStatus.Text = ex.Message;
                }
                con.Close();
            }
        
     
        
        }
    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        { 
        using(SqlConnection con =Datos.ObtenerConexion())
        {
            SqlTransaction tran = con.BeginTransaction();
            String cadena = "Update Tipos set Tipo= @tipo , TiempoPromedioResolucion = @tiempo where IdTipo = @id";
            SqlCommand cmd = new SqlCommand(cadena, con);
            cmd.Parameters.AddWithValue("@tipo", txtDescripcion.Text);
            cmd.Parameters.AddWithValue("@tiempo", txtTiempo.Text);
            cmd.Parameters.AddWithValue("@id", lblIdTipo.Text);
            cmd.Transaction = tran;
            try
            {
                cmd.ExecuteNonQuery();
                tran.Commit();
            

                lblStatus.Text = "se guardo con exito";


            }
            catch (Exception ex)
            {
                tran.Rollback();
                lblStatus.Text = ex.Message;
            }
            con.Close();
        }
        
        }
    }
    protected void Button3_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            using (SqlConnection con = Datos.ObtenerConexion())
            {
                SqlTransaction tran = con.BeginTransaction();
                String cadena = "Delete from Tipos  where IdTipo = @id";
                SqlCommand cmd = new SqlCommand(cadena, con);
             
                cmd.Parameters.AddWithValue("@id", lblIdTipo.Text);
                cmd.Transaction = tran;
                try
                {
                    cmd.ExecuteNonQuery();
                    tran.Commit();
    
                    lblStatus.Text = "se elimino con exito";


                }
                catch (Exception ex)
                {
                    tran.Rollback();
                    lblStatus.Text = ex.Message;
                }
                con.Close();
            }

        }
    }
}