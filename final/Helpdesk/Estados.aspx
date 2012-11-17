<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="Estados.aspx.cs" Inherits="Estados" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <asp:Label ID="Label2" runat="server" Text="Estado"></asp:Label>
    <asp:TextBox ID="txtEstado" runat="server"></asp:TextBox>
    <asp:DropDownList ID="ddlEstado" runat="server">
    </asp:DropDownList>
    <asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Buscar" />
    <br />
    <asp:GridView ID="gvEstados" runat="server" AllowPaging="True" 
        AllowSorting="True" AutoGenerateColumns="False" 
        onpageindexchanging="gvEstados_PageIndexChanging" onsorting="gvEstados_Sorting" 
        PageSize="2" style="margin-top: 0px">
        <Columns>
            <asp:BoundField DataField="Estado" HeaderText="Estado" />
            <asp:BoundField DataField="IdEstado" HeaderText="ID" 
                SortExpression="IdEstado" />
        </Columns>
    </asp:GridView>
</asp:Content>

