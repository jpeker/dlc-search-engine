<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="Incidentes.aspx.cs" Inherits="Incidentes_Incidentes" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <asp:Label ID="Label2" runat="server" Text="Titulo"></asp:Label>
    :<asp:TextBox ID="txtTitulo" runat="server"></asp:TextBox>
    <asp:DropDownList ID="ddlEstado" runat="server">
    </asp:DropDownList>
    <asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Buscar" />
    <asp:GridView ID="gvIncidentes" runat="server" AllowPaging="True" 
        AllowSorting="True" AutoGenerateColumns="False" 
        onpageindexchanging="gvIncidentes_PageIndexChanging" 
        onsorting="gvIncidentes_Sorting" 
        onselectedindexchanged="gvIncidentes_SelectedIndexChanged" PageSize="4">
        <Columns>
            <asp:BoundField DataField="IdIncidente" HeaderText="ID" />
            <asp:BoundField DataField="Titulo" HeaderText="Titulo" />
            <asp:BoundField DataField="Fecha" DataFormatString="{0:d}" HeaderText="Fecha" 
                HtmlEncode="False" SortExpression="Fecha" />
            <asp:BoundField DataField="Usuario" HeaderText="Usuario" />
            <asp:BoundField DataField="Producto" HeaderText="Producto" />
            <asp:BoundField DataField="Estado" HeaderText="Estado" />
            <asp:CommandField ShowSelectButton="True" />
        </Columns>
    </asp:GridView>
</asp:Content>

