<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="GestionTipos.aspx.cs" Inherits="GestionTipos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
        .style4
        {
            width: 100%;
        }
        .style5
        {
        }
        .style6
        {
            width: 223px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <table class="style4">
        <tr>
            <td class="style6">
                Seleccione un tipo de incidente</td>
            <td>
                <asp:DropDownList ID="ddlIncidentes" runat="server" AutoPostBack="True" 
                    onselectedindexchanged="ddlIncidentes_SelectedIndexChanged">
                </asp:DropDownList>
            </td>
        </tr>
        <tr>
            <td class="style5" colspan="2">
                Datos del tipo de incidente</td>
        </tr>
        <tr>
            <td class="style6">
                id tipo</td>
            <td>
                <asp:Label ID="lblIdTipo" runat="server"></asp:Label>
            </td>
        </tr>
        <tr>
            <td class="style6">
                Descripcion</td>
            <td>
                <asp:TextBox ID="txtDescripcion" runat="server"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style6">
                tiempo promedio de ejecucion</td>
            <td>
                <asp:TextBox ID="txtTiempo" runat="server"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style5" colspan="2">
                <asp:Button ID="Button1" runat="server" Text="Nuevo" />
                --<asp:Button ID="Button2" runat="server" Text="Guardar" />
                --<asp:Button ID="Button3" runat="server" Text="Eliminar" />
            </td>
        </tr>
        <tr>
            <td class="style6">
                <asp:Label ID="lblStatus" runat="server"></asp:Label>
            </td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td class="style6">
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
    </table>
</asp:Content>

