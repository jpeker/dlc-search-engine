using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Informacion : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        practica hdMaster = (practica)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Informacion";
       
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        HttpCookie p = new HttpCookie("micookie");
        p.Value = User.Identity.Name;
        p.Expires = DateTime.Now.AddMinutes(60);
        Response.Cookies.Add(p);
        Session["apellido"] = txtApellido.Text;
    }
}