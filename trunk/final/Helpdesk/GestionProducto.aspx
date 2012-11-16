<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="GestionProducto.aspx.cs" Inherits="GestionProducto" %>

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
            width: 517px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <table class="style4">
    <tr>
        <td class="style5">
            Id</td>
        <td class="style6">
            <asp:Label ID="lblIdProducto" runat="server"></asp:Label>
        </td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td class="style5">
            Codigo Producto</td>
        <td class="style6">
            <asp:TextBox ID="txtCodigo" runat="server"></asp:TextBox>
        </td>
        <td>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtCodigo" Display="Dynamic" 
                ErrorMessage="ingrese el codigo">*</asp:RequiredFieldValidator>
        </td>
    </tr>
    <tr>
        <td class="style5">
            Nombre</td>
        <td class="style6">
            <asp:TextBox ID="txtProducto" runat="server"></asp:TextBox>
        </td>
        <td>
            <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                ControlToValidate="txtProducto" Display="Dynamic" 
                ErrorMessage="Ingrese el nombre">*</asp:RequiredFieldValidator>
        </td>
    </tr>
    <tr>
        <td class="style5" colspan="3">
            <asp:GridView ID="gvProductos" runat="server" AllowPaging="True" 
                AutoGenerateColumns="False" onpageindexchanging="gvProductos_PageIndexChanging" 
                onselectedindexchanged="gvProductos_SelectedIndexChanged" PageSize="4" 
                AllowSorting="True" onsorting="gvProductos_Sorting">
                <Columns>
                    <asp:CommandField ShowSelectButton="True" />
                    <asp:BoundField DataField="IdProducto" HeaderText="ID" 
                        SortExpression="IdProducto" />
                    <asp:BoundField DataField="CodigoProducto" HeaderText="Codigo" />
                    <asp:BoundField DataField="Producto" HeaderText="Nombre" />
                </Columns>
            </asp:GridView>
        </td>
    </tr>
    <tr>
        <td class="style5">
            <asp:Button ID="Button1" runat="server" Text="Nuevo" onclick="Button1_Click" />
            <asp:Button ID="Button2" runat="server" onclick="Button2_Click" 
                Text="Guardar" />
            <asp:Button ID="Button3" runat="server" Text="Eliminar" 
                onclick="Button3_Click" />
            <asp:Button ID="Button4" runat="server" Text="Cancelar" />
        </td>
        <td class="style6">
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td class="style5">
            <asp:Label ID="lblStatus" runat="server"></asp:Label>
        </td>
        <td class="style6">
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
    <tr>
        <td class="style5">
            <asp:ValidationSummary ID="ValidationSummary1" runat="server" />
        </td>
        <td class="style6">
            &nbsp;</td>
        <td>
            &nbsp;</td>
    </tr>
</table>
</asp:Content>

