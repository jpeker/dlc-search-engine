using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class practica : System.Web.UI.MasterPage
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
   
    protected void btnCerrar_Click(object sender, EventArgs e)
    {
        Context.Response.Cookies["test"].Expires = DateTime.Now;
        FormsAuthentication.SignOut();
        Response.Redirect("login.aspx");

    }
}
