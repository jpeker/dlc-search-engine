using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;

public partial class Incidentes_NuevoIncidente : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Nuevo Incidentes";
        if (!Page.IsPostBack)
        {
            if(Session["Titulo"]!=  null)
            {
                txtTitulo.Text = Session["Titulo"].ToString();
               // DateTime pa = DateTime.Parse(Session["Fecha"].ToString());
                txtFecha.Text = Session["Fecha"].ToString();
            }
            txtFecha.Text = DateTime.Now.ToString("yyyy/MM/dd");
            CargarTipo();
            cargarAsginado();
            CargarEstado();
            CargarProducto();
            ddlAsignadoa.SelectedIndex = 1;
          
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
        {
            
            using (SqlConnection con = Datos.ObtenerConexion())
            {
                //con.Open();
                SqlTransaction tran = con.BeginTransaction();
                String cadena="Insert into Incidentes(Titulo,Fecha,IdTipo,IdProducto,Usuario,IdUsuarioAsignado,FechaResolucion,Email,Descripcion,IdEstado)" ;
                cadena += " values ( '" + txtTitulo.Text + "','" + txtFecha.Text + "'," + ddlTipo.SelectedItem.Value + "," + ddlProducto.SelectedItem.Value + ",'" + txtUsuario.Text +"',"+ddlAsignadoa.SelectedItem.Value+ ",'";
                cadena += txtFechaEstimadaResolucion.Text + "','" + txtEmail.Text + "','" + txtDescripcion.Text + "'," + ddlEstado.SelectedItem.Value + ")";
                SqlCommand cmd = new SqlCommand(cadena, con);
                cmd.Transaction = tran;
                try 
                {
                    cmd.ExecuteNonQuery();
                    tran.Commit();
                    lblMensaje.Text = "Incidente registrado con éxito";
                
                }
                catch(Exception ex)
                {
                    tran.Rollback();
                    lblMensaje.Text = "Mensaje " + ex.Message;
                }
                con.Close();
            }
        }
        else
            lblMensaje.Text = "No se pudo registrar el incidente";
    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        Response.Redirect("Incidentes.aspx");
    }
    private void CargarTipo()
    {
        SqlConnection con = Datos.ObtenerConexion();
            String  sqls="Select * from Tipos";
        ddlTipo.DataSource=Datos.getDataReader(sqls,con);
        ddlTipo.DataTextField="Tipo";
        ddlTipo.DataValueField="IdTipo";
        ddlTipo.DataBind();
        ddlTipo.Items.Insert(0, new ListItem("Seleccione", "0"));
        con.Close();
    }
    private void CargarProducto()
    {
        SqlConnection con = Datos.ObtenerConexion();
        String sqls = "Select * from Productos";
        ddlProducto.DataSource = Datos.getDataReader(sqls, con);
        ddlProducto.DataTextField = "Producto";
        ddlProducto.DataValueField = "IdProducto";
        ddlProducto.DataBind();
        ddlProducto.Items.Insert(0, new ListItem("Seleccione", "0"));
        con.Close();
    }
    private void CargarEstado() 
    {
        SqlConnection con = Datos.ObtenerConexion();
        String sqls = "Select * from Estados";
        ddlEstado.DataSource = Datos.getDataReader(sqls,con);
        ddlEstado.DataTextField = "Estado";
        ddlEstado.DataValueField = "IdEstado";
        ddlEstado.DataBind();
        ddlEstado.Items.Insert(0, new ListItem("Seleccione", "0"));
        con.Close();
        
    }
    private void cargarAsginado()
    {
        SqlConnection con = Datos.ObtenerConexion();
        String sqls = "Select * from Usuarios";
        ddlAsignadoa.DataSource = Datos.getDataReader(sqls, con);
        ddlAsignadoa.DataTextField = "Usuario";
        ddlAsignadoa.DataValueField = "IdUsuario";
        ddlAsignadoa.DataBind();

        ddlAsignadoa.Items.Insert(0, new ListItem("Seleccione", "0"));
        con.Close();
    }

}