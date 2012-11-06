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
        EnlazarGrilla("Fecha asc");
    }
    private void EnlazarGrilla(String criterio)
    {
        String SQL = "select i.Titulo,i.Fecha, u.Usuario,p.Producto,e.Estado from Incidentes i inner join Usuarios u on i.IdUsuarioAsignado = u.IdUsuario inner join Productos p on p.IdProducto =i.IdProducto inner join Estados e on e.IdEstado = i.IdEstado order by "+criterio;
        SqlConnection sqlCon = Datos.ObtenerConexion();
        DataSet incidentes = Datos.ObtenerDataset(SQL, sqlCon, "incidentes");
        gvIncidentes.DataSource = incidentes.Tables["incidentes"];
        gvIncidentes.DataBind();
    }
    protected void gvIncidentes_PageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        gvIncidentes.PageIndex = e.NewPageIndex;
        EnlazarGrilla("Fecha asc");
    }
    protected void gvIncidentes_Sorting(object sender, GridViewSortEventArgs e)
    {
        EnlazarGrilla(string.Format("{0} {1}", e.SortExpression,
(e.SortDirection == SortDirection.Ascending) ? "ASC" : "DESC"));
    }
}