﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Reportes_ListadoIncidentes : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

        helpdesk hdMaster = (helpdesk)this.Master;
        ((Label)hdMaster.FindControl("lblTitulo")).Text = "Listado de Incidentes";
    }
}