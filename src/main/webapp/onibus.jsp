<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Onibus</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" ></jsp:include>
	</div>
	<br />
	<div align="center" class="container">
		<form action="onibus" method="post">
			<p class="title">
				<b>Onibus</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="input_data_id" type="text" 
							id="placa" name="placa" placeholder="Placa"
							value="<c:out value="${onibus.placa }"></c:out>">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="marca" name="marca"
							placeholder="Marca" value="<c:out value="${onibus.marca }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="date" id="abo" name="ano"
							placeholder="Ano" value="<c:out value="${onibus.ano }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="descricao" name="descricao"
							placeholder="Descricao" value="<c:out value="${onibus.descricao }"></c:out>">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" id="botao" name="botao" value="Inserir">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Atualizar">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Excluir">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Listar">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center">
		<c:if test="${not empty erro }"></c:if>
			<h2><c:out value="${erro }"></c:out></h2>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<h3><c:out value="${saida }"></c:out></h3>
		</c:if>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty listOnibus }">
			<table class="table_round">
				<thead>
					<tr>
						<th><b>Placa</b></th>
						<th><b>Marca</b></th>
						<th><b>Ano</b></th>
						<th><b>Descricao</b></th>
					</tr>	
				</thead>
				<tbody>
					<c:forEach items="${listOnibus }" var="o">
						<tr>
							<td><c:out value="${o.placa }"></c:out></td>
							<td><c:out value="${o.marca }"></c:out></td>
							<td><c:out value="${o.ano }"></c:out></td>
							<td><c:out value="${o.descricao }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>