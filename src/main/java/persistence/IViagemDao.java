package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Viagem;

public interface IViagemDao {
	
	public String insereViagem(Viagem v) throws SQLException, ClassNotFoundException;
	public String atualizaViagem(Viagem v) throws SQLException, ClassNotFoundException; 
	public String excluiViagem(Viagem v) throws SQLException, ClassNotFoundException; 
	public Viagem consultaViagem(Viagem v) throws SQLException, ClassNotFoundException;
	public Viagem consultaDescricaoOnibus(Viagem v) throws SQLException, ClassNotFoundException;
	public Viagem consultaDescricaoViagem(Viagem v) throws SQLException, ClassNotFoundException;
	public List<Viagem> consultaViagens() throws SQLException, ClassNotFoundException;
	
}
