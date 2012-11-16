using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        practica hdMaster = (practica)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Inicio";
        if (!Page.IsPostBack)
        {
            if (Request.Cookies["micookie"] != null)
            {
                txtNombre.Text = Request.Cookies["micookie"].Value.ToString();
                Request.Cookies["micookie"].Expires = DateTime.Now;
            }
            if (Session["apellido"] != null)
            {
                txtApellido.Text = Session["apellido"].ToString();
            }
        }
    }
    protected void txtNombre_TextChanged(object sender, EventArgs e)
    {

    }
}