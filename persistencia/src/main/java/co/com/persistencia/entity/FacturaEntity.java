package co.com.persistencia.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="factura")
public class FacturaEntity {
	
	@Id
	@GeneratedValue
	private long numeroFactura;
	
	@Column(nullable= false)
	private String placa;
	
	@Column(nullable= false,columnDefinition = "TIMESTAMP")
	private Timestamp fechaHoraInicio;
	
	@Column(nullable= false,columnDefinition = "TIMESTAMP")
	private Timestamp fechaHoraFin;
	
	@Column(nullable= false)
	private double tarifaHora;
	
	@Column(nullable= false)
	private double tarifaDia;
	
	@Column(nullable= false )
	private  double valorPagar;
	
	public FacturaEntity() {}
	
	public FacturaEntity(String placa,Timestamp fechahorainicio, Timestamp fechahorafin, double tarifahora, double tarifadia, double valorpagar ) {
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
	
}
