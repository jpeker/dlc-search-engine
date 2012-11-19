using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.Security;
using System.Web.UI.WebControls;

public partial class Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        practica hdMaster = (practica)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Login";
        ((Button)hdMaster.FindControl("btnCerrar")).Visible = false;
    }
    protected void txtLogin_Authenticate(object sender, AuthenticateEventArgs e)
    {
        if (Seguridad.ValidarUsuario(txtLogin.UserName, txtLogin.Password))
        {
            e.Authenticated = true;
            String roles = Seguridad.ObtenerRoles(txtLogin.UserName);
            FormsAuthenticationTicket ticket= new FormsAuthenticationTicket(1, txtLogin.UserName, DateTime.Now, DateTime.Now.AddMinutes(60), false, roles);
            String sticket = FormsAuthentication.Encrypt(ticket);
            HttpCookie cookie = new HttpCookie("test", sticket);
            Response.Cookies.Add(cookie);
            Response.Redirect(FormsAuthentication.GetRedirectUrl(txtLogin.UserName, false));
        }
        else e.Authenticated = false;
    }
}