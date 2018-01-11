package co.com.domain;

public class Cotizacion {
	
	private Estacionamiento estacionamiento;
	private double valorPagar;
	
	public Cotizacion(Estacionamiento estacionamiento, double valorpagar){
		this.estacionamiento = estacionamiento;
		this.valorPagar = valorpagar;
	}

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
	}

	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}
	
	
}
