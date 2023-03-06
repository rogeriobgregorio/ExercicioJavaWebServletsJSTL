package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Motorista;
import persistence.GenericDao;
import persistence.IMotoristaDao;
import persistence.MotoristaDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MotoristaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MotoristaServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String naturalidade = request.getParameter("naturalidade");
		String botao = request.getParameter("botao");
		
		Motorista m = new Motorista();
		
		GenericDao gDao = new GenericDao();
		IMotoristaDao mDao = new MotoristaDao(gDao);
		String erro = "";
		String saida = "";
		List<Motorista> motoristas = new ArrayList<Motorista>();
		
		try {
			if (botao.equals("Listar")) {
				motoristas = mDao.consultaMotoristas();
			}
			if (botao.equals("Inserir")) {
				m = valido(codigo, nome, naturalidade, botao);
				saida = mDao.insereMotorista(m);
				m = new Motorista();
			}
			if (botao.equals("Atualizar")) {
				m = valido(codigo, nome, naturalidade, botao);
				saida = mDao.atualizaMotorista(m);
				m = new Motorista();
			}
			if (botao.equals("Excluir")) {
				m = valido(codigo, nome, naturalidade, botao);
				saida = mDao.excluiMotorista(m);
				m = new Motorista();
			}
			if (botao.equals("Buscar")) {
				m = valido(codigo, nome, naturalidade, botao);
				m = mDao.consultaMotorista(m);
			}
			
		} catch(SQLException | ClassNotFoundException | IOException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("motorista.jsp");
			request.setAttribute("motorista", m);
			request.setAttribute("motoristas", motoristas);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			rd.forward(request, response);
		}
	}
	
	private Motorista valido(String codigo, String nome, String naturalidade, String botao) throws IOException {
		Motorista m = new Motorista();
		
		if (botao.equals("Inserir")) {
			if (codigo.equals("") || nome.equals("") || naturalidade.equals("")) {
				throw new IOException("Preencha todos os campos");
			} else {
				m.setCodigo(Integer.parseInt(codigo));
				m.setNome(nome);
				m.setNaturalidade(naturalidade);
			}
		}
		
		if (botao.equals("Atualizar")) {
			if (codigo.equals("") || nome.equals("") || naturalidade.equals("")) {
				throw new IOException("Preencha todos os campos");
			} else {
				m.setCodigo(Integer.parseInt(codigo));
				m.setNome(nome);
				m.setNaturalidade(naturalidade);
			}
		}
		
		if (botao.equals("Excluir")) {
			if (codigo.equals("")) {
				throw new IOException("Preencha o codigo");
			} else {
				m.setCodigo(Integer.parseInt(codigo));
			}
		}
		
		if (botao.equals("Buscar")) {
			if (codigo.equals("")) {
				throw new IOException("Preencha o codigo");
			} else {
				m.setCodigo(Integer.parseInt(codigo));
			}
		}
		
		return m;
	}

}
