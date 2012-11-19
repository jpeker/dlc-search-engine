using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;

public partial class Incidentes_Incidentes : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Incidentes";
        if (!Page.IsPostBack)
        {
      
            EnlazarGrilla("Fecha desc");
            CargarListaEstados();
        }
    }
    private void EnlazarGrilla()
    {
        EnlazarGrilla(ViewState["CriterioOrdenacion"].ToString());
    }
    private void EnlazarGrilla(String criterio)
    {
        ViewState.Add("CriterioOrdenacion", criterio);
        String SQL = "select i.IdIncidente,i.Titulo,i.Fecha, u.Usuario,p.Producto,e.Estado from Incidentes i inner join Usuarios u on i.IdUsuarioAsignado = u.IdUsuario inner join Productos p on p.IdProducto =i.IdProducto inner join Estados e on e.IdEstado = i.IdEstado order by "+criterio;
        SqlConnection sqlCon = Datos.ObtenerConexion();
        DataSet incidentes = Datos.ObtenerDataset(SQL, sqlCon, "incidentes");
        gvIncidentes.DataSource = incidentes.Tables["incidentes"];
        gvIncidentes.DataBind();
    }
    protected void gvIncidentes_PageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        gvIncidentes.PageIndex = e.NewPageIndex;
        EnlazarGrilla();
    }
    protected void gvIncidentes_Sorting(object sender, GridViewSortEventArgs e)
    {
        EnlazarGrilla(string.Format("{0} {1}", e.SortExpression,
(e.SortDirection == SortDirection.Ascending) ? "ASC" : "DESC"));
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        SqlCommand cmd = new SqlCommand("L50221_sp_BuscarIncidentes",Datos.ObtenerConexion());
        //Le indicamos que es de tipo Stored Procedure
        cmd.CommandType = CommandType.StoredProcedure;
        //Le agregamos los parámetros
        cmd.Parameters.Add(new SqlParameter("@Titulo", txtTitulo.Text));
        cmd.Parameters.Add(new SqlParameter("@IdEstado",int.Parse( ddlEstado.SelectedItem.Value)));
        //Cargamos un Dataset mediante un SqlDataAdapter
        DataSet incidentes = new DataSet();
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        da.Fill(incidentes, "Incidentes");
        //Le aplicamos el orden a su DefaultView
        DataView dv = incidentes.Tables["Incidentes"].DefaultView;
        dv.Sort = " Fecha asc";
        //Enlazamos a la GridView
        gvIncidentes.DataSource = dv;
        gvIncidentes.DataBind();
    }
    protected void CargarListaEstados()
    {

        SqlConnection con = Datos.ObtenerConexion();
        String SqlS = "L50221_sp_ObtenerEstados";
      
        this.ddlEstado.DataSource = Datos.getDataReader(SqlS, con);
        this.ddlEstado.DataValueField = "IdEstado";
        this.ddlEstado.DataTextField = "Estado";
      
        ddlEstado.DataBind();
        ddlEstado.Items.Insert(0, new ListItem("Todos", "0"));
      
    }
    protected void gvIncidentes_SelectedIndexChanged(object sender, EventArgs e)
    {
        Session["ID"] = gvIncidentes.SelectedRow.Cells[0].Text;
        
        Response.Redirect("NuevoIncidente.aspx");
    }
}