<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar seguros</title>
</head>
<body>
	
	<a href="Inicio.jsp">Inicio</a> <a href="AgregarSeguros.jsp"> Agregar Seguros</a> <a href="ListarSeguros.jsp"> Listar Seguros</a>
	<form>
	<h1>Agregar Seguros</h1>
	<table>
	<tr><td>Id Seguro:</td> <td></td></tr>
	<tr><td>Descripcion:</td> <td><input type="text" name="txtDescripcion"></td></tr>
	<tr><td>Tipo de seguro:</td> <td> <select></select> </td> </tr>
	<tr><td>Costo Contratación:</td> <td> <input type="text" name="txtCostoContratacion"> </td> </tr>
	<tr><td>Costo Máximo Asegurado:</td> <td> <input type="text" name="txtCostoMaximo"> </td> </tr>
	<tr><td></td> <td> <input type="submit" name="btnAceptar" value="Aceptar"> </td></tr>
	</table>
	<input type="submit" name="btnAceptar" value="Aceptar">
	</form>
	
</body>
</html>