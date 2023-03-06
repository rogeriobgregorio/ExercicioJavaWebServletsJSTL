package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Motorista;

public interface IMotoristaDao {
	
	public String insereMotorista(Motorista m) throws SQLException, ClassNotFoundException;
	public String atualizaMotorista(Motorista m) throws SQLException, ClassNotFoundException; 
	public String excluiMotorista(Motorista m) throws SQLException, ClassNotFoundException;   
	public Motorista consultaMotorista(Motorista m) throws SQLException, ClassNotFoundException;
	public List<Motorista> consultaMotoristas() throws SQLException, ClassNotFoundException;  

}
