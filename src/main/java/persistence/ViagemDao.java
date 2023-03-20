package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Motorista;
import model.Onibus;
import model.Viagem;

public class ViagemDao implements IViagemDao {
	
	private GenericDao gDao;
	
	public ViagemDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String insereViagem(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "INSERT INTO viagens VALUES (?, ?, ?, ?, ?, ?, ?)";
			
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.setString(2, v.getOnibus().getPlaca());
		ps.setInt(3, v.getMotorista().getCodigo());
		ps.setInt(4, v.getHora_saida());
		ps.setInt(5, v.getHora_chegada());
		ps.setString(6, v.getPartida());
		ps.setString(7, v.getDestino());
		ps.execute();
		ps.close();
		c.close();
			
		return "Viagem inserida com sucesso";
	}

	@Override
	public String atualizaViagem(Viagem v) throws SQLException, ClassNotFoundException {
		
		Connection c = gDao.getConnection();
		
		String sql = "UPDATE viagens onibus_placa = ?, motorista_codigo = ?, hora_saida = ?, hora_chegada = ?, partida = ?, destino = ? WHERE codigo = ?";
			
		PreparedStatement ps = c.prepareStatement(sql);
		
		ps.setString(1, v.getOnibus().getPlaca());
		ps.setInt(2, v.getMotorista().getCodigo());
		ps.setInt(3, v.getHora_saida());
		ps.setInt(4, v.getHora_chegada());
		ps.setString(5, v.getPartida());
		ps.setString(6, v.getDestino());
		ps.setInt(7, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
			
		return "Viagem atualizado com sucesso";
	}

	@Override
	public String excluiViagem(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		String sql = "DELETE viagens WHERE codigo = ?";
			
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
			
		return "Viagem excluida com sucesso";
	}	

	@Override
	public Viagem consultaViagem(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codigo AS Codigo,\n"
				 + "onibus_placa AS Placa,\n"
				 + "motorista_codigo AS Codigo_do_motorista,\n"
				 + "hora_saida AS Saida,\n"
				 + "hora_chegada AS Chegada,\n"
				 + "partida AS Partida,\n"
				 + "destino AS Destino\n"
				 + "FROM viagens WHERE codigo = ?");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			
            v.setCodigo(rs.getInt("codigo"));
            
            Onibus o = new Onibus();
            o.setPlaca(rs.getString("Placa"));
            v.setOnibus(o);
            
            Motorista m = new Motorista();
            m.setCodigo(rs.getInt("Codigo_do_motorista"));
            v.setMotorista(m);
            
            v.setHora_saida(rs.getInt("Saida"));
            v.setHora_chegada(rs.getInt("Chegada"));
            v.setPartida(rs.getString("Partida"));
            v.setDestino(rs.getString("Destino"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return v;
		
	}
	
	@Override
	public Viagem consultaDescricaoOnibus(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT viagens.codigo AS Codigo,\n"
				 + "motoristas.nome AS Nome,\n"
				 + "SUBSTRING(UPPER(onibus.placa),1,3)+' - '+\n"
				 + "SUBSTRING(onibus.placa,4,4) AS Placa,\n"
				 + "onibus.marca AS Marca,\n"
				 + "onibus.ano AS Ano,\n"
				 + "onibus.descricao AS Descricao\n"
				 + "FROM viagens\n"
				 + "INNER JOIN onibus ON viagens.onibus_placa = onibus.placa\n"
				 + "INNER JOIN motoristas ON viagens.motorista_codigo = motoristas.codigo\n"
				 + "FROM v_descricao_onibus\n"
				 + "WHERE viagens.codigo = ?");
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			v.setCodigo(rs.getInt("Codigo"));
			
			Motorista m = new Motorista();
			m.setNome(rs.getString("Nome"));
			v.setMotorista(m);
			
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("Placa"));
			o.setMarca(rs.getString("Marca"));
			o.setAno(rs.getInt("Ano"));
			o.setDescricao(rs.getString("Descricao"));
			v.setOnibus(o);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return v;
	}

	@Override
	public Viagem consultaDescricaoViagem(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT viagens.codigo AS Codigo,\n"
				 + "SUBSTRING(UPPER(onibus.placa),1,3)+' - '+\n"
				 + "SUBSTRING(onibus.placa,4,4) AS Placa,\n"
				 + "CONVERT(VARCHAR(5), DATEADD(HOUR, hora_saida, 0), 108) AS Saida,\n"
				 + "CONVERT(VARCHAR(5), DATEADD(HOUR, hora_chegada, 0), 108) AS Chegada,\n"
				 + "viagens.partida AS Partida,\n"
				 + "viagens.destino AS Destino\n"
				 + "FROM viagens\n"
				 + "INNER JOIN onibus ON viagens.onibus_placa = onibus.placa\n"
				 + "FROM v_descricao_viagem\n"
				 + "WHERE viagens.codigo = ?");
		
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			v.setCodigo(rs.getInt("Codigo"));
			
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("Placa"));
			v.setOnibus(o);
			
			v.setHora_saida(rs.getInt("Saida"));
			v.setHora_chegada(rs.getInt("Chegada"));
			v.setPartida(rs.getString("Partida"));
			v.setDestino(rs.getString("Destino"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return v;
		
	}
	
	@Override
	public List<Viagem> consultaViagens() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<Viagem>();
		
		Connection c = gDao.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codigo AS Codigo,\n"
				 + "onibus_placa AS Placa,\n"
				 + "motorista_codigo AS Codigo_do_motorista,\n"
				 + "hora_saida AS Saida,\n"
				 + "hora_chegada AS Chegada,\n"
				 + "partida AS Partida,\n"
				 + "destino AS Destino\n"
				 + "FROM viagens;");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Viagem v = new Viagem();
            v.setCodigo(rs.getInt("codigo"));
            
            Onibus o = new Onibus();
            o.setPlaca(rs.getString("Placa"));
            v.setOnibus(o);
            
            Motorista m = new Motorista();
            m.setCodigo(rs.getInt("Codigo_do_motorista"));
            v.setMotorista(m);
            
            v.setHora_saida(rs.getInt("Saída"));
            v.setHora_chegada(rs.getInt("Chegada"));
            v.setPartida(rs.getString("Partida"));
            v.setDestino(rs.getString("Destino"));
            
            viagens.add(v);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return viagens;
		
	}

}
