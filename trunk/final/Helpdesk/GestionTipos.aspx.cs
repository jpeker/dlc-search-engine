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
        string cmdStr = "SELECT * FROM tipos WHERE IdTipo = " +
        ddlIncidentes.SelectedItem.Value;
        SqlConnection cn = new SqlConnection(cnStr);
        try
        {
            cn.Open();
            SqlCommand cmd = new SqlCommand(cmdStr, cn);
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
}