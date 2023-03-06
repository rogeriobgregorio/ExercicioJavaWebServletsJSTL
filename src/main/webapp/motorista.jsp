<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Motorista</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" ></jsp:include>
	</div>
	<br />
	<div align="center" class="container">
		<form action="motorista" method="post">
			<p class="title">
				<b>Motoristas</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="input_data_id" type="number" min="0"
							step="1" id="codigo" name="codigo" placeholder="Código"
							value="<c:out value="${motorista.codigo }"></c:out>">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="nome" name="nome"
							placeholder="Nome"
							value="<c:out value="${motorista.nome }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="naturalidade" name="naturalidade"
							placeholder="Naturalidade"
							value="<c:out value="${motorista.naturalidade }"></c:out>">
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
		<c:if test="${not empty erro }">
			<h2><c:out value="${erro }"></c:out></h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<h3><c:out value="${saida }"></c:out></h3>
		</c:if>
	</div>
	<br />
	<br />
	<div align="center">
		<c:if test="${not empty motoristas }">
			<table class="table_round">
				<thead>
					<tr>
						<th><b>Codigo</b></th>
						<th><b>Nome</b></th>
						<th><b>Naturalidade</b></th>
					</tr>	
				</thead>
				<tbody>
					<c:forEach items="${motoristas }" var="m">
						<tr>
							<td><c:out value="${m.codigo }"></c:out></td>
							<td><c:out value="${m.nome }"></c:out></td>
							<td><c:out value="${m.naturalidade }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>