using System;
using System.Data;
using System.Data.SqlClient;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class buscarProducto : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdmaster = (helpdesk)this.Master;
        ((Label)hdmaster.FindControl("lblTitulo")).Text = "Buscar Productos";
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        using (SqlConnection con = Datos.ObtenerConexion())
        { 
            String  cadena="Select * from Productos where IdProducto = @ID or CodigoProducto = @Codigo or Producto = @Producto";
            SqlCommand cmd = new SqlCommand(cadena, con);
            cmd.Parameters.AddWithValue("@ID", txtIdProducto.Text);
            cmd.Parameters.AddWithValue("@Codigo", txtCodigo.Text);
            cmd.Parameters.AddWithValue("@Producto", txtProducto.Text);
            SqlDataAdapter da = new SqlDataAdapter(cmd);
            DataSet ds = new DataSet();
            da.Fill(ds, "Producto");
            gvProductos.DataSource = ds;
            gvProductos.DataBind();
            con.Close() ;
        
        }
    }


   
}