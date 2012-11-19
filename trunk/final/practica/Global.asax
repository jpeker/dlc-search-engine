<%@ Application Language="C#" %>
<%@ Import Namespace="System.Web.Security" %>
<%@ Import Namespace="System.Security.Principal" %>

<script runat="server">

    void Application_Start(object sender, EventArgs e) 
    {
        // Código que se ejecuta al iniciarse la aplicación

    }
    
    void Application_End(object sender, EventArgs e) 
    {
        //  Código que se ejecuta cuando se cierra la aplicación

    }
        
    void Application_Error(object sender, EventArgs e) 
    { 
        // Código que se ejecuta al producirse un error no controlado

    }

    void Session_Start(object sender, EventArgs e) 
    {
        // Código que se ejecuta cuando se inicia una nueva sesión

    }

    void Session_End(object sender, EventArgs e) 
    {
        // Código que se ejecuta cuando finaliza una sesión. 
        // Nota: el evento Session_End se desencadena sólo cuando el modo sessionstate
        // se establece como InProc en el archivo Web.config. Si el modo de sesión se establece como StateServer 
        // o SQLServer, el evento no se genera.

    }
    protected void Application_AuthenticateRequest(object sender, EventArgs e)
    {
        HttpCookie cookie = Context.Request.Cookies["test"];
        if (cookie == null) return;
        FormsAuthenticationTicket aut = null;
        try 
        {
            aut = FormsAuthentication.Decrypt(cookie.Value);
            
        }
        catch(Exception ex)
        {
            return;
        }
        if (aut == null) return;
        String[] roles = aut.UserData.Split(new Char[] { '|' });
        GenericIdentity id = new GenericIdentity(aut.Name, "test");
        GenericPrincipal principal = new GenericPrincipal(id, roles);
        Context.User = principal;
    }
  
</script>
