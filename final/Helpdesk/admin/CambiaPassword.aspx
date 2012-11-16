<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="CambiaPassword.aspx.cs" Inherits="admin_CambiaPassword" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <asp:Label ID="lblNombre" runat="server"></asp:Label>
    <br />
    <asp:Label ID="Label2" runat="server" Text="PassWordActual"></asp:Label>
    <asp:TextBox ID="txtPassActual" runat="server" TextMode="Password"></asp:TextBox>
    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
        ControlToValidate="txtPassActual" Display="Dynamic" 
        ErrorMessage="Escriba el password">*</asp:RequiredFieldValidator>
    <br />
    <asp:Label ID="Label3" runat="server" Text="Nueva Password"></asp:Label>
    <asp:TextBox ID="txtPassNueva" runat="server" TextMode="Password"></asp:TextBox>
    <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
        ControlToValidate="txtPassNueva" Display="Dynamic" 
        ErrorMessage="Escriba el password">*</asp:RequiredFieldValidator>
    <br />
    <asp:Label ID="Label4" runat="server" Text="Confirma Password"></asp:Label>
    <asp:TextBox ID="txtConfirmaPass" runat="server" TextMode="Password"></asp:TextBox>
    <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
        ControlToValidate="txtConfirmaPass" Display="Dynamic" 
        ErrorMessage="Repita el Password">*</asp:RequiredFieldValidator>
    <br />
    <asp:Button ID="Button1" runat="server" onclick="Button1_Click" 
        Text="Guardar" />
    <asp:Button ID="Button2" runat="server" CausesValidation="False" 
        onclick="Button2_Click" Text="Cancelar" />
    <asp:Label ID="lblStatus" runat="server"></asp:Label>
    <asp:ValidationSummary ID="ValidationSummary1" runat="server" />
</asp:Content>

