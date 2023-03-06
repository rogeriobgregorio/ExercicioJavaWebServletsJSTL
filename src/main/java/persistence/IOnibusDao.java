package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Onibus;

public interface IOnibusDao {
	
	public String insereOnibus(Onibus o) throws SQLException, ClassNotFoundException;
	public String atualizaOnibus(Onibus o) throws SQLException, ClassNotFoundException; 
	public String excluiOnibus(Onibus o) throws SQLException, ClassNotFoundException;   
	public Onibus consultaOnibus(Onibus o) throws SQLException, ClassNotFoundException;
	public List<Onibus> consultaOnibus() throws SQLException, ClassNotFoundException;  
	
}
