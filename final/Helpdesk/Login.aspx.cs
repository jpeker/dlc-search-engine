using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Login";
    }
    protected void txtLogin_Authenticate(object sender, AuthenticateEventArgs e)
    {
        if (Seguridad.ValidarUsuario(txtLogin.UserName, txtLogin.Password))
        {
          
            Console.WriteLine("si existe");
           String roles= Seguridad.ObtenerRoles(txtLogin.UserName);
        }
    }
}