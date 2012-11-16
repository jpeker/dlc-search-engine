<%@ Page Title="" Language="C#" MasterPageFile="~/practica.master" AutoEventWireup="true" CodeFile="Informacion.aspx.cs" Inherits="Informacion" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <p>
        Ingrese nombre<asp:TextBox ID="txtNombre" runat="server"></asp:TextBox>
    </p>
    <p>
        Ingrese Apellido<asp:TextBox ID="txtApellido" runat="server"></asp:TextBox>
    </p>
    <p>
        <asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Enviar" />
    </p>
</asp:Content>

