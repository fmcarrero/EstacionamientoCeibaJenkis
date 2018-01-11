package co.com.domain;

import java.sql.Timestamp;

public class Factura {	
	
	private long numeroFactura;
	private String placa;	
	private Timestamp fechaHoraInicio;	
	private Timestamp fechaHoraFin;	
	private double tarifaHora;	
	private double tarifaDia;	
	private  double valorPagar;
	
	public Factura() {}
	
	public Factura(String placa,Timestamp fechahorainicio, Timestamp fechahorafin, double tarifahora, double tarifadia, double valorpagar ) {
		this.placa = placa;
		this.fechaHoraInicio = fechahorainicio;
		this.fechaHoraFin = fechahorafin;
		this.tarifaHora = tarifahora;
		this.tarifaDia = tarifadia;
		this.valorPagar= valorpagar;
	}
	
	public String getPlaca() {
		return placa;
	}
	public Timestamp getFechaHoraInicio() {
		return fechaHoraInicio;
	}
	public Timestamp getFechaHoraFin() {
		return fechaHoraFin;
	}
	public double getTarifaHora() {
		return tarifaHora;
	}
	public double getTarifaDia() {
		return tarifaDia;
	}
	public double getValorPagar() {
		return valorPagar;
	}	
	public long getnumeroFactura(){
		return this.numeroFactura;
	}
	
}
