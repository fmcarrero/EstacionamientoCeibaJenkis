package co.com.builder.persistencia;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import co.com.persistencia.entity.FacturaEntity;



public class FacturaEntityTestDataBuilder {
	
	private long numeroFactura;	
	private String placa;
	private Timestamp fechaHoraInicio;
	private Timestamp fechaHoraFin;
	private double tarifaHora;	
	private double tarifaDia;
	private  double valorPagar;
	
	public FacturaEntityTestDataBuilder(){
		this.placa ="sdc-654";
		this.numeroFactura=1;
		this.fechaHoraInicio = Timestamp.valueOf(LocalDateTime.now());
		this.fechaHoraFin = Timestamp.valueOf(LocalDateTime.now());
		this.tarifaHora = 120D;
		this.tarifaDia = 60D;
		this.valorPagar = 300D;
	}
	public FacturaEntity build(){
		return new FacturaEntity(this.placa, this.fechaHoraInicio, this.fechaHoraFin, this.tarifaHora, this.tarifaDia, this.valorPagar);
	}
}
