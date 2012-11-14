﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class _Default : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Inicio";
        if (!Page.IsPostBack)
        {
            if (Request.Cookies["micookie"] != null)
                lblLogeo.Text = Request.Cookies["micookie"].Value;
        }
    }
}