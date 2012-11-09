<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <asp:Login ID="txtLogin" runat="server" BackColor="#E3EAEB" 
        BorderColor="#E6E2D8" BorderPadding="4" BorderStyle="Solid" BorderWidth="1px" 
        Font-Names="Verdana" Font-Size="0.8em" ForeColor="#333333" 
        onauthenticate="txtLogin_Authenticate" TextLayout="TextOnTop">
        <InstructionTextStyle Font-Italic="True" ForeColor="Black" />
        <LoginButtonStyle BackColor="White" BorderColor="#C5BBAF" BorderStyle="Solid" 
            BorderWidth="1px" Font-Names="Verdana" Font-Size="0.8em" ForeColor="#1C5E55" />
        <TextBoxStyle Font-Size="0.8em" />
        <TitleTextStyle BackColor="#1C5E55" Font-Bold="True" Font-Size="0.9em" 
            ForeColor="White" />
    </asp:Login>
</asp:Content>

