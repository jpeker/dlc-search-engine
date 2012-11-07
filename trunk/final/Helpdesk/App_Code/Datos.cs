using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;

/// <summary>
/// Descripción breve de Datos
/// </summary>
public static class Datos
{

    public static SqlConnection ObtenerConexion()
    {
        SqlConnection con = new SqlConnection(ConfigurationManager.ConnectionStrings["helpdeskcn"].ConnectionString.ToString());
        con.Open();
        return con;
    }
    public static void EjecutarComando(string strSQL, SqlConnection con)
    {
        SqlCommand cmd = new SqlCommand(strSQL, con);
        cmd.ExecuteNonQuery();
    }
    public static DataSet ObtenerDataset(string strSQL, SqlConnection con,string dataTable)
    {
        SqlCommand cmd = new SqlCommand(strSQL, con);
        SqlDataAdapter da = new SqlDataAdapter(cmd);
        DataSet ds = new DataSet();
        da.Fill(ds, dataTable);
        return ds;
    }
    public static SqlDataReader getDataReader(string strSQL, SqlConnection con)
    {
        SqlCommand cmd = new SqlCommand(strSQL, con);
        return cmd.ExecuteReader();
    }
}