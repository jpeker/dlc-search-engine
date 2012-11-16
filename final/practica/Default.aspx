<%@ Page Title="" Language="C#" MasterPageFile="~/practica.master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <p>
    nombre<asp:TextBox ID="txtNombre" runat="server" 
        ontextchanged="txtNombre_TextChanged"></asp:TextBox>
</p>
<p>
    apellido<asp:TextBox ID="txtApellido" runat="server"></asp:TextBox>
</p>
</asp:Content>

