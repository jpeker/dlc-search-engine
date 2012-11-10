using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.Security;

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
            e.Authenticated = true;

            Console.WriteLine("si existe");
            String roles = Seguridad.ObtenerRoles(txtLogin.UserName);
            FormsAuthenticationTicket autTicket = new FormsAuthenticationTicket(1, txtLogin.UserName, DateTime.Now, DateTime.Now.AddMinutes(60), false, roles);
            //Encriptar el ticket
            string encrTicket = FormsAuthentication.Encrypt(autTicket);
            // Crea una cookie con el ticket encriptado
            HttpCookie autCookie = new HttpCookie("testseguridad", encrTicket);
            // Agrega la cookie a la Response
            Response.Cookies.Add(autCookie);
            // Redirecciona al usuario a la página que solicitó

            Response.Redirect(FormsAuthentication.GetRedirectUrl(txtLogin.UserName, false));
        }
        else { }
    }
}