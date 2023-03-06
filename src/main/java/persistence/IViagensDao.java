package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Viagens;

public interface IViagensDao {
	
	public String insereViagens(Viagens v) throws SQLException, ClassNotFoundException;
	public String atualizaViagens(Viagens v) throws SQLException, ClassNotFoundException; 
	public String excluiViagens(Viagens v) throws SQLException, ClassNotFoundException; 
	public Viagens consultaViagens(Viagens v) throws SQLException, ClassNotFoundException;
	public Viagens consultaDescricaoOnibus(Viagens v) throws SQLException, ClassNotFoundException;
	public Viagens consultaDescricaoViagem(Viagens v) throws SQLException, ClassNotFoundException;
	public List<Viagens> consultaViagens() throws SQLException, ClassNotFoundException;
	
}
