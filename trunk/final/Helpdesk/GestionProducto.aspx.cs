using System;
using System.Data;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class GestionProducto : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdmaster = (helpdesk)this.Master;
        ((Label)hdmaster.FindControl("lblTitulo")).Text = "Gestion Producto";
    
        if (!Page.IsPostBack)
        {
            cargarGrilla("IdProducto DESC");
        }

    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        if (Page.IsValid) 
        {
            String cadena = "Update Productos set CodigoProducto ='" + txtCodigo.Text + "', Producto =' " + txtProducto.Text;
            cadena += "' where IdProducto = " + lblIdProducto.Text;
            Guardar(cadena);
        }
    }
    private void Guardar(String cadena)
    {
        using (SqlConnection con = Datos.ObtenerConexion())
        {
            SqlTransaction tran = con.BeginTransaction();
            SqlCommand cmd = new SqlCommand(cadena, con);
            cmd.Transaction = tran;
            try
            {
                cmd.ExecuteNonQuery();
                tran.Commit();
                lblStatus.Text = "Se realizo con exito";


            }
            catch (Exception ex)
            {
                tran.Rollback();
                lblStatus.Text = ex.Message;
            }


            con.Close();
        
        
        }
    
    }
    private void cargarGrilla()
    {
        cargarGrilla(ViewState["criterio"].ToString());
    }
    private void cargarGrilla(String criterio)
    {
        ViewState.Add("criterio", criterio);
        SqlConnection con = Datos.ObtenerConexion();
        try
        {
            String cadena="Select * from Productos order by "+criterio;
            DataSet productos = Datos.ObtenerDataset(cadena, con, "Productos");
           gvProductos.DataSource = productos.Tables["Productos"];
           // gvProductos.DataSource = Datos.getDataReader(cadena,con);
            gvProductos.DataBind();
        }
        catch (Exception ex)
        {
            lblStatus.Text = ex.Message;
        }
    
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            String cadena = "insert into Productos (CodigoProducto,Producto) values ";
            cadena += "( '" + txtCodigo.Text + "','" + txtProducto.Text + "')";
            Guardar(cadena);
        }
    }
    protected void gvProductos_SelectedIndexChanged(object sender, EventArgs e)
    {
        lblIdProducto.Text = gvProductos.SelectedRow.Cells[1].Text;
        txtCodigo.Text = gvProductos.SelectedRow.Cells[2].Text;
        txtProducto.Text = gvProductos.SelectedRow.Cells[3].Text;
    }
    protected void Button3_Click(object sender, EventArgs e)
    {
        if (Page.IsValid)
        {
            String cadena = "Delete from Productos where IdProducto = " + lblIdProducto.Text;
            Guardar(cadena);
        }
    }
    protected void gvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        gvProductos.PageIndex = e.NewPageIndex;
        cargarGrilla();
    }
    protected void gvProductos_Sorting(object sender, GridViewSortEventArgs e)
    {
   
        cargarGrilla(string.Format("{0} {1}", e.SortExpression, (e.SortDirection == SortDirection.Ascending) ? "ASC" : "DESC"));
    }
}