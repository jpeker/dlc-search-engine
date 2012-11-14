<%@ Page Title="" Language="C#" MasterPageFile="~/helpdesk.master" AutoEventWireup="true" CodeFile="NuevoIncidente.aspx.cs" Inherits="Incidentes_NuevoIncidente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">

    <style type="text/css">
        .style4
        {
            width: 100%;
        }
        .style5
        {
            width: 96px;
        }
        .style6
        {
            width: 129px;
        }
        .style7
        {
            width: 92px;
        }
        .style8
        {
            width: 27px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="Contenido" Runat="Server">
    <asp:Label ID="lblMensaje" runat="server"></asp:Label>
    <asp:ValidationSummary ID="ValidationSummary1" runat="server" />
    <table class="style4">
        <tr>
            <td class="style5">
                Titulo
            </td>
            <td class="style6">
                <asp:TextBox ID="txtTitulo" runat="server"></asp:TextBox>
            </td>
            <td class="style8">
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                    ControlToValidate="txtTitulo" Display="Dynamic" 
                    ErrorMessage="ingrese el titulo">*</asp:RequiredFieldValidator>
            </td>
            <td class="style7">
                Fecha</td>
            <td>
                <asp:TextBox ID="txtFecha" runat="server"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style5">
                Tipo</td>
            <td class="style6">
                <asp:DropDownList ID="ddlTipo" runat="server">
                </asp:DropDownList>
            </td>
            <td class="style8">
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                    ControlToValidate="ddlTipo" Display="Dynamic" 
                    ErrorMessage="selecione un tipo valido" InitialValue="0">*</asp:RequiredFieldValidator>
            </td>
            <td class="style7">
                Estado</td>
            <td>
                <asp:DropDownList ID="ddlEstado" runat="server">
                </asp:DropDownList>
                <asp:CustomValidator ID="cusEstado" runat="server" Display="Dynamic" 
                    ErrorMessage="Debe selecionar un estado valido" 
                    ControlToValidate="ddlEstado" >*</asp:CustomValidator>
                </td>
        </tr>
        <tr>
            <td class="style5">
                Producto</td>
            <td class="style6">
                <asp:DropDownList ID="ddlProducto" runat="server">
                </asp:DropDownList>
            </td>
            <td class="style8">
                <asp:CustomValidator ID="cusProducto" runat="server" 
                    ControlToValidate="ddlProducto" 
                    ErrorMessage="Debe Selecionar un producto valido" Display="Dynamic" 
                   >*</asp:CustomValidator>
            </td>
            <td class="style7">
                Asignado</td>
            <td>
                <asp:DropDownList ID="ddlAsignadoa" runat="server">
                </asp:DropDownList>
                <asp:CustomValidator ID="cusAsignado" runat="server" 
                    ControlToValidate="ddlAsignadoa" 
                    ErrorMessage="Debe Selecionar un asignado valido" Display="Dynamic" 
                 >*</asp:CustomValidator>
            </td>
        </tr>
        <tr>
            <td class="style5">
                Usuario</td>
            <td class="style6">
                <asp:TextBox ID="txtUsuario" runat="server"></asp:TextBox>
            </td>
            <td class="style8">
                &nbsp;</td>
            <td class="style7">
                Fecha Esti</td>
            <td>
                <asp:TextBox ID="txtFechaEstimadaResolucion" runat="server"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style5">
                Email</td>
            <td class="style6">
                <asp:TextBox ID="txtEmail" runat="server"></asp:TextBox>
            </td>
            <td class="style8">
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
                    ControlToValidate="txtEmail" Display="Dynamic" ErrorMessage="ingresse un email">*</asp:RequiredFieldValidator>
                <asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" 
                    ControlToValidate="txtEmail" ErrorMessage="ingrese un email valido" 
                    ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*">*</asp:RegularExpressionValidator>
            </td>
            <td class="style7">
                Prioridad</td>
            <td>
                <asp:TextBox ID="txtPrioridad" runat="server"></asp:TextBox>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" 
                    ControlToValidate="txtPrioridad" Display="Dynamic" 
                    ErrorMessage="ingrese un valor valido ">*</asp:RequiredFieldValidator>
                <asp:RangeValidator ID="RangeValidator1" runat="server" 
                    ControlToValidate="txtPrioridad" Display="Dynamic" 
                    ErrorMessage="ingrese un numero de 1 a 3" MaximumValue="3" MinimumValue="1"></asp:RangeValidator>
            </td>
        </tr>
        <tr>
            <td class="style5">
                Descripcion</td>
            <td class="style6">
                <asp:TextBox ID="txtDescripcion" runat="server" TextMode="MultiLine"></asp:TextBox>
            </td>
            <td class="style8">
                &nbsp;</td>
            <td class="style7">
                <asp:Button ID="Button1" runat="server" onclick="Button1_Click" 
                    Text="guardar" />
            </td>
            <td>
                <asp:Button ID="Button2" runat="server" onclick="Button2_Click" 
                    Text="Cancelar" CausesValidation="False" />

                -<asp:Button ID="btnEliminar" runat="server" Text="Eliminar" 
                    CausesValidation="False" onclick="Button3_Click" Visible="False" />
            </td>
        </tr>
    </table>
</asp:Content>

