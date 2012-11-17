using System;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Estados : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdmaster = (helpdesk)this.Master;
        ((Label)hdmaster.FindControl("lblTitulo")).Text = "Estados";
        if (!Page.IsPostBack)
        {
            estados();
        }
    }
    private void estados()
    {
        SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["helpdeskcn"].ConnectionString);
        con.Open();
        String cadena = "Select IdEstado, Estado from Estados";
        SqlCommand cmd= new SqlCommand(cadena, con);
        SqlDataReader data = cmd.ExecuteReader();
      ddlEstado.DataSource = data;
      ddlEstado.DataTextField = "Estado";
      ddlEstado.DataValueField = "IdEstado";
      ddlEstado.DataBind();
      ddlEstado.Items.Insert(0, new ListItem("Todos", "0"));
      con.Close();
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["helpdeskcn"].ConnectionString);
        con.Open();
        String cadena;
        if (ddlEstado.SelectedIndex == 0 && txtEstado.Text.Equals(""))
        {
            cadena = "select IdEstado, Estado from Estados order by IdEstado asc";
            ViewState["criterio"] = "asc";
        }
        else 
        {
            cadena = "select IdEstado ,Estado from Estados where IdEstado =" + txtEstado.Text + " or Estado ='" + ddlEstado.SelectedItem.Text + "'";
        }
        SqlCommand cmd = new SqlCommand(cadena, con);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataSet set = new DataSet();
        da.Fill(set, "Estados");
        gvEstados.DataSource = set.Tables["Estados"];
        gvEstados.DataBind();
        con.Close();
    }
    private void cargarGrilla(String criterio)
    {

        SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["helpdeskcn"].ConnectionString);
        con.Open();
        String cadena;

        cadena = "select IdEstado, Estado from Estados order by IdEstado "+criterio;


        SqlCommand cmd = new SqlCommand(cadena, con);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataSet set = new DataSet();
        da.Fill(set, "Estados");
        gvEstados.DataSource = set.Tables["Estados"];
        gvEstados.DataBind();
        con.Close();
    }
    protected void gvEstados_PageIndexChanging(object sender, GridViewPageEventArgs e)
    {
        gvEstados.PageIndex = e.NewPageIndex;

        cargarGrilla(ViewState["criterio"].ToString());
    }
    protected void gvEstados_Sorting(object sender, GridViewSortEventArgs e)
    {
        if (ViewState["criterio"].ToString().Equals("asc"))
        {
            cargarGrilla("desc");
            ViewState["criterio"] = "desc";
        }
        else 
        {
            cargarGrilla("asc");
            ViewState["criterio"] = "asc";
        }
    }
}