using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Incidentes_NuevoIncidente : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Nuevo Incidentes";
        if (!Page.IsPostBack)
        {
            txtFecha.Text = DateTime.Now.ToString("dd/MM/yyyy");
            ddlTipo.Items.Add(new ListItem("---Seleccione una opción---", "0"));
            ddlTipo.Items.Add(new ListItem("Tecnología", "1"));
            ddlTipo.Items.Add(new ListItem("Software", "2"));
            ddlTipo.Items.Add(new ListItem("Mantenimiento", "3"));
            ddlProducto.Items.Add(new ListItem("---Seleccione unaopción---", "0"));
            ddlProducto.Items.Add(new ListItem("Producto1", "1"));
            ddlProducto.Items.Add(new ListItem("Producto2", "2"));
            ddlProducto.Items.Add(new ListItem("Producto3", "3"));
            ddlEstado.Items.Add(new ListItem("---Seleccione una opción---", "0"));
            ddlEstado.Items.Add(new ListItem("Registrado", "1"));
            ddlEstado.Items.Add(new ListItem("Asignado", "2"));
            ddlEstado.Items.Add(new ListItem("Suspendido", "3"));
            ddlEstado.Items.Add(new ListItem("Cancelado", "4"));
            ddlEstado.Items.Add(new ListItem("Resuelto", "5"));
            ddlAsignadoa.Items.Add(new ListItem("---Seleccione una opción---", "0"));
            ddlAsignadoa.Items.Add(new ListItem("Resolutor 1", "1"));
            ddlAsignadoa.Items.Add(new ListItem("Resolutor 2", "2"));
        }
    }


    protected void cusTipo_ServerValidate(object source, ServerValidateEventArgs args)
    {
        if (ddlTipo.SelectedIndex > 1)
            args.IsValid = true;
        else
            args.IsValid = false;
    }
    protected void cusProducto_ServerValidate(object source, ServerValidateEventArgs args)
    {
        if (ddlProducto.SelectedIndex > 1)
            args.IsValid = true;
        else
            args.IsValid = false;
    }
    protected void cusEstado_ServerValidate(object source, ServerValidateEventArgs args)
    {
        if (ddlEstado.SelectedIndex > 1)
            args.IsValid = true;
        else
            args.IsValid = false;
    }
    protected void cusAsignado_ServerValidate(object source, ServerValidateEventArgs args)
    {
        if (ddlAsignadoa.SelectedIndex > 1)
            args.IsValid = true;
        else
            args.IsValid = false;
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
            lblMensaje.Text = "Incidente registrado con éxito";
        else
            lblMensaje.Text = "No se pudo registrar el incidente";
    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        Response.Redirect("Incidentes.aspx");
    }
}