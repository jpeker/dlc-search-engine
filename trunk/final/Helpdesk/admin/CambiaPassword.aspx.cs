using System;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class admin_CambiaPassword : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Cambiar Password";
        if (!Page.IsPostBack) 
        {
            lblNombre.Text =User.Identity.Name;
        }
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            if (Seguridad.ValidarUsuario(lblNombre.Text, txtPassActual.Text))
            {
                if (txtPassNueva.Text.Equals(txtConfirmaPass.Text))
                {
                    try
                    {
                        String cadena = "Update Usuarios set  Password = '" + txtPassNueva.Text + "' where Nombre ='" + lblNombre.Text + "'";
                        SqlConnection con = Datos.ObtenerConexion();
                        Datos.EjecutarComando(cadena, con);
                        lblStatus.Text = "Se cambio con exito";
                    }
                    catch (Exception ex)
                    {
                        lblStatus.Text = ex.Message;
                    }
                }
                else
                {
                    lblStatus.Text = "No coinciden los Password";
                }
            }
            else {
                lblStatus.Text = "Datos incorrectos";
            }
        }
    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        Response.Redirect("~/Default.aspx");
    }
}