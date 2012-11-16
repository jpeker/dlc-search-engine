<%@ Page Title="" Language="C#" MasterPageFile="~/practica.master" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:Login ID="txtLogin" runat="server" onauthenticate="txtLogin_Authenticate">
    </asp:Login>
</asp:Content>

