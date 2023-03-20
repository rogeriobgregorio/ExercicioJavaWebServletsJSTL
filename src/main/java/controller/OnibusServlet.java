package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Onibus;
import persistence.GenericDao;
import persistence.IOnibusDao;
import persistence.OnibusDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnibusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OnibusServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placa = request.getParameter("placa");
		String marca = request.getParameter("marca");
		String ano = request.getParameter("ano");
		String descricao = request.getParameter("descricao");
		String botao = request.getParameter("botao");
		
		Onibus o = new Onibus();
		
		GenericDao gDao = new GenericDao();
		IOnibusDao mDao = new OnibusDao(gDao);
		String erro = "";
		String saida = "";
		List<Onibus> listOnibus = new ArrayList<Onibus>();
		
		try {
			if (botao.equals("Listar")) {
				listOnibus = mDao.consultaListOnibus();
			}
			if (botao.equals("Inserir")) {
				o = valido(placa, marca, ano, descricao, botao);
				saida = mDao.insereOnibus(o);
				o = new Onibus();
			}
			if (botao.equals("Atualizar")) {
				o = valido(placa, marca, ano, descricao, botao);
				saida = mDao.atualizaOnibus(o);
				o = new Onibus();
			}
			if (botao.equals("Excluir")) {
				o = valido(placa, marca, ano, descricao, botao);
				saida = mDao.excluiOnibus(o);
				o = new Onibus();
			}
			if (botao.equals("Buscar")) {
				o = valido(placa, marca, ano, descricao, botao);
				o = mDao.consultaOnibus(o);
			}
			
		} catch(SQLException | ClassNotFoundException | IOException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("onibus.jsp");
			request.setAttribute("onibus", o);
			request.setAttribute("onibus", listOnibus);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			rd.forward(request, response);
		}
	}
	
	private Onibus valido(String placa, String marca, String ano, String descricao, String botao) throws IOException {
		Onibus o = new Onibus();
		
		if (botao.equals("Inserir")) {
			if (placa.equals("") || marca.equals("") || ano.equals("") || descricao.equals("")) {
				throw new IOException("Preencha todos os campos");
			} else {
				o.setPlaca(placa);
				o.setMarca(marca);
				o.setAno(Integer.parseInt(ano));
				o.setDescricao(descricao);
			}
		}
		
		if (botao.equals("Atualizar")) {
			if (placa.equals("") || marca.equals("") || ano.equals("") || descricao.equals("")) {
				throw new IOException("Preencha todos os campos");
			} else {
				o.setPlaca(placa);
				o.setMarca(marca);
				o.setAno(Integer.parseInt(ano));
				o.setDescricao(descricao);
			}
		}
		
		if (botao.equals("Excluir")) {
			if (placa.equals("")) {
				throw new IOException("Preencha a placa");
			} else {
				o.setPlaca(placa);
			}
		}
		
		if (botao.equals("Buscar")) {
			if (placa.equals("")) {
				throw new IOException("Preencha o placa");
			} else {
				o.setPlaca(placa);
			}
		}
		
		return o;
	}

}
