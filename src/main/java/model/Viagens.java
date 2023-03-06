package model;

public class Viagens {
	
	private int codigo;
    private Onibus onibus;
    private Motorista motorista;
    private int hora_saida;
    private int hora_chegada;
    private String partida;
    private String destino;
    
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Onibus getOnibus() {
		return onibus;
	}
	public void setOnibus(Onibus onibus) {
		this.onibus = onibus;
	}
	public Motorista getMotorista() {
		return motorista;
	}
	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}
	public int getHora_saida() {
		return hora_saida;
	}
	public void setHora_saida(int hora_saida) {
		this.hora_saida = hora_saida;
	}
	public int getHora_chegada() {
		return hora_chegada;
	}
	public void setHora_chegada(int hora_chegada) {
		this.hora_chegada = hora_chegada;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public void setPlacaOnibus(String placa) {
	    this.onibus.setPlaca(placa);
	}
	public void setCodigoMotorista(int codigo) {
	    this.motorista.setCodigo(codigo);
	}
	
	@Override
	public String toString() {
		return "viagens [codigo=" + codigo + ", onibus=" + onibus + ", motorista=" + motorista + ", hora_saida="
				+ hora_saida + ", hora_chegada=" + hora_chegada + ", partida=" + partida + ", destino=" + destino + "]";
	}
    
    

}
