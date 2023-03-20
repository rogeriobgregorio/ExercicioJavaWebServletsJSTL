<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Viagem</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" ></jsp:include>
	</div>
	<br />
	<div align="center" class="container">
		<form action="viagem" method="post">
			<p class="title">
				<b>Viagem</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="input_data_id" type="number" min="0"
							step="1" id="codigo" name="codigo" placeholder="Código"
							value="<c:out value="${viagem.codigo }"></c:out>">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="onibus" name="onibus"
							placeholder="Placa do onibus" value="<c:out value="${viagem.onibus.placa }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data_id" type="number" min="0"
							step="1" id="motorista" name="motorista" placeholder="Código do motorista"
							value="<c:out value="${viagem.motorista.codigo }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data_id" type="number" min="0"
							step="1" id="hora_saida" name="hora_saida" placeholder="Hora da saida"
							value="<c:out value="${viagem.hora_saida }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data_id" type="number" min="0"
							step="1" id="hora_chegada" name="hora_chegada" placeholder="Hora da chegada"
							value="<c:out value="${viagem.hora_chegada }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="partida" name="partida"
							placeholder="Partida" value="<c:out value="${viagem.partida }"></c:out>">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text" id="destino" name="destino"
							placeholder="Destino" value="<c:out value="${viagem.destino }"></c:out>">
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
		<c:if test="${not empty viagens }">
			<table class="table_round">
				<thead>
					<tr>
						<th><b>Codigo</b></th>
						<th><b>Placa do onibus</b></th>
						<th><b>Codigo do motorista</b></th>
						<th><b>Hora da saida</b></th>
						<th><b>Hora da chegada</b></th>
						<th><b>Partida</b></th>
						<th><b>Destino</b></th>
					</tr>	
				</thead>
				<tbody>
					<c:forEach items="${viagens }" var="v">
						<tr>
							<td><c:out value="${v.codigo }"></c:out></td>
							<td><c:out value="${v.onibus.placa }"></c:out></td>
							<td><c:out value="${v.motorista.codigo }"></c:out></td>
							<td><c:out value="${v.hora_saida }"></c:out></td>
							<td><c:out value="${v.hora_chegada }"></c:out></td>
							<td><c:out value="${v.partida}"></c:out></td>
							<td><c:out value="${v.destino}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>