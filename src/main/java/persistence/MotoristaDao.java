package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Motorista;

public class MotoristaDao implements IMotoristaDao {
	
	private GenericDao gDao;
	
	public MotoristaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String insereMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "INSERT INTO motoristas VALUES (?, ?, ?)";
		
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, m.getCodigo());
        ps.setString(2, m.getNome());
        ps.setString(3, m.getNaturalidade());
        ps.execute();
        ps.close();
        c.close();
        
        return "Motorista inserido com sucesso";
	}

	@Override
	public String atualizaMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "UPDATE motoristas SET nome=?, naturalidade=? WHERE codigo=?";
		
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, m.getNome());
        ps.setString(2, m.getNaturalidade());
        ps.setInt(3, m.getCodigo());
        ps.execute();
        ps.close();
        c.close();
        
        return "Motorista atualizado com sucesso";
	}

	@Override
	public String excluiMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "DELETE motoristas WHERE codigo=?";
		
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, m.getCodigo());
        ps.execute();
        ps.close();
        c.close();
        
        return "Motorista excluido com sucesso";	
	}

	@Override
	public Motorista consultaMotorista(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "SELECT codigo, nome, naturalidade FROM motoristas WHERE codigo = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			m.setCodigo(rs.getInt("codigo"));
            m.setNome(rs.getString("nome"));
            m.setNaturalidade(rs.getString("naturalidade"));
		}
		rs.close();
		ps.close();
		c.close();
		
		return m;
	}

	@Override
	public List<Motorista> consultaMotoristas() throws SQLException, ClassNotFoundException {
		List<Motorista> motoristas = new ArrayList<Motorista>();
		
		Connection c = gDao.getConnection();
		
		String sql = "SELECT codigo, nome, naturalidade FROM motoristas";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("codigo"));
            m.setNome(rs.getString("nome"));
            m.setNaturalidade(rs.getString("naturalidade"));
            
            motoristas.add(m);
		}
		rs.close();
		ps.close();
		c.close();
		
		return motoristas;
	}

}
