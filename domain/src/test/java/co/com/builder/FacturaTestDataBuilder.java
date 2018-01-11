package co.com.builder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import co.com.domain.Factura;



public class FacturaTestDataBuilder {
	
	private long numeroFactura;	
	private String placa;	
	private Timestamp fechaHoraInicio;	
	private Timestamp fechaHoraFin;	
	private double tarifaHora;	
	private double tarifaDia;	
	private  double valorPagar;
	
	LocalDateTime hoy =LocalDateTime.of(2018, 1, 2,0,0);
	Timestamp fechaHoraIngreso = Timestamp.valueOf(hoy);
	
	public FacturaTestDataBuilder() {
		this.numeroFactura=123456;
		this.placa = "AXT-654";
		this.fechaHoraFin = fechaHoraIngreso;
		this.fechaHoraFin = Timestamp.valueOf(hoy.withHour(4));
		this.tarifaHora = 354D;
		this.tarifaDia =10D;
		this.valorPagar = 50;
	}
	public FacturaTestDataBuilder withValoraPagar(double valorapagar) {
		this.valorPagar = valorapagar;
		return this;
	}
	public FacturaTestDataBuilder withTarifaDia (double tarifadia) {
		this.tarifaDia = tarifadia;
		return this;
	}
	public FacturaTestDataBuilder withTarifaHora(double tarifahora) {
		this.tarifaHora = tarifahora;
		return this;
	}
	public FacturaTestDataBuilder withFechaHoraFin(Timestamp fechaHoraFin) {
		this.fechaHoraFin = fechaHoraFin;
		return this;
	}
	public FacturaTestDataBuilder withFechaHoraInicio(Timestamp fechaHoraInicio) {
		this.fechaHoraInicio = fechaHoraInicio;
		return this;
	}
	public FacturaTestDataBuilder withPlaca(String placa) {
		this.placa = placa;
		return this;		
	}
	public Factura build() {
		return new Factura(this.placa,this.fechaHoraInicio,this.fechaHoraFin,this.tarifaHora,this.tarifaDia,this.valorPagar);
	}
}
