package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Viagens;
import persistence.GenericDao;
import persistence.IViagensDao;
import persistence.ViagensDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViagensServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViagensServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		String onibus = request.getParameter("onibus");
		String motorista = request.getParameter("motorista");
		String hora_saida = request.getParameter("hora_saida");
		String hora_chegada = request.getParameter("hora_chegada");
		String partida = request.getParameter("partida");
		String destino = request.getParameter("destino");
		String botao = request.getParameter("botao");
		
		Viagens v = new Viagens();
		
		GenericDao gDao = new GenericDao();
		IViagensDao mDao = new ViagensDao(gDao);
		String erro = "";
		String saida = "";
		List<Viagens> viagens = new ArrayList<Viagens>();
		
		try {
			if (botao.equals("Listar")) {
				viagens = mDao.consultaViagens();
			}
			if (botao.equals("Inserir")) {
				v = valido(codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino, botao);
				saida = mDao.insereViagens(v);
				v = new Viagens();
			}
			if (botao.equals("Atualizar")) {
				v = valido(codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino, botao);
				saida = mDao.atualizaViagens(v);
				v = new Viagens();
			}
			if (botao.equals("Excluir")) {
				v = valido(codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino, botao);
				saida = mDao.excluiViagens(v);
				v = new Viagens();
			}
			if (botao.equals("Buscar")) {
				v = valido(codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino, botao);
				v = mDao.consultaViagens(v);
			}
			
		} catch(SQLException | ClassNotFoundException | IOException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("viagens.jsp");
			request.setAttribute("viagens", v);
			request.setAttribute("viagens", viagens);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			rd.forward(request, response);
		}
	}
	
	private Viagens valido(String codigo, String onibus, String motorista, String hora_saida, String hora_chegada, String partida, String destino, String botao) throws IOException {
		Viagens v = new Viagens();
		
		if (botao.equals("Inserir")) {
			if (codigo.equals("") || onibus.equals("") || motorista.equals("") || hora_saida.equals("") || hora_chegada.equals("") || partida.equals("") || destino.equals("")) {
				throw new IOException("Preencha todos os campos");
			} else {
				v.setCodigo(Integer.parseInt(codigo));
				v.setPlacaOnibus(onibus);
				v.setCodigoMotorista(Integer.parseInt(motorista));
				v.setHora_saida(Integer.parseInt(hora_saida));
				v.setHora_chegada(Integer.parseInt(hora_chegada));
				v.setPartida(partida);
				v.setDestino(destino);
			}
		}
		
		if (botao.equals("Atualizar")) {
			if (codigo.equals("") || onibus.equals("") || motorista.equals("") || hora_saida.equals("") || hora_chegada.equals("") || partida.equals("") || destino.equals("")) {
				throw new IOException("Preencha todos os campos");
			} else {
				v.setCodigo(Integer.parseInt(codigo));
				v.setPlacaOnibus(onibus);
				v.setCodigoMotorista(Integer.parseInt(motorista));
				v.setHora_saida(Integer.parseInt(hora_saida));
				v.setHora_chegada(Integer.parseInt(hora_chegada));
				v.setPartida(partida);
				v.setDestino(destino);
			}
		}
		
		if (botao.equals("Excluir")) {
			if (codigo.equals("")) {
				throw new IOException("Preencha o codigo");
			} else {
				v.setCodigo(Integer.parseInt(codigo));
			}
		}
		
		if (botao.equals("Buscar")) {
			if (codigo.equals("")) {
				throw new IOException("Preencha o codigo");
			} else {
				v.setCodigo(Integer.parseInt(codigo));
			}
		}
		
		return v;
		
	}

}
