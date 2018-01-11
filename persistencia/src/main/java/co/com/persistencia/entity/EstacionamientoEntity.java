package co.com.persistencia.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table (name = "estacionamiento")
public class EstacionamientoEntity implements Serializable {
	
	 @Id	
	 @Column(name="Placa")
	 private String placa;		
	
	 @OneToOne(mappedBy="estacionamiento")
	 private VehiculoEntity vehiculo;
	 
	 @Column(name="fechahorainicio", nullable= false, columnDefinition = "TIMESTAMP")
	 private Timestamp fechaHoraInicio;
	 
	 @Column(name="observacion",nullable= false,length = 500)
	 private String observacion;
	 
	 public String getPlaca() {
		 return this.placa;
	 }
	 public String getObservacion() {
		 return this.observacion;
	 }
	 public Timestamp getFechaHoraInicio() {
		 return this.fechaHoraInicio;
	 }
	 public VehiculoEntity getVehiculo() {
		 return this.vehiculo;
	 }
	 public void setVehiculo(VehiculoEntity vehiculo) {
		 this.vehiculo = vehiculo;
	 }
	 public EstacionamientoEntity() {}	 
	 
	 public EstacionamientoEntity(String placa, Timestamp fechahorainicio, String observacion) {
		 this.placa = placa;
		 this.fechaHoraInicio = fechahorainicio;
		 this.observacion = observacion;
	 }
}
