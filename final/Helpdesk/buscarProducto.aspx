<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="buscarProducto.aspx.cs" Inherits="buscarProducto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <p>
        ID:<asp:TextBox ID="txtIdProducto" runat="server"></asp:TextBox>
        Nombre<asp:TextBox ID="txtProducto" runat="server"></asp:TextBox>
        Codigo<asp:TextBox ID="txtCodigo" runat="server"></asp:TextBox>
        <asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Buscar" />
    </p>
    <p>
        <asp:GridView ID="gvProductos" runat="server" AllowPaging="False" 
          PageSize="10">
        </asp:GridView>
    </p>
</asp:Content>

