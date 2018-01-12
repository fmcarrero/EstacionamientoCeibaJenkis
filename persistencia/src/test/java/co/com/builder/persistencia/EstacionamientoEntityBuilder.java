package co.com.builder.persistencia;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.mockito.Mockito;

import co.com.persistencia.entity.EstacionamientoEntity;
import co.com.persistencia.entity.VehiculoEntity;

public class EstacionamientoEntityBuilder {
	 
	 private String placa;	
	 private VehiculoEntity vehiculo;
	 private Timestamp fechaHoraInicio;	 
	 private String observacion;
	 
	 public EstacionamientoEntityBuilder(){
		 this.placa = "xcv-765";
		 this.fechaHoraInicio = Timestamp.valueOf(LocalDateTime.now());
		 this.observacion = "nada";
     }
	 public EstacionamientoEntityBuilder withVehiculoEntity(VehiculoEntity entity){
		 this.vehiculo = entity;
		 return this;
	 }
	 public EstacionamientoEntityBuilder withPlaca(String placa){
		 this.placa= placa;
		 return this;
	 }
	 public EstacionamientoEntityBuilder withObservacion (String observacion){
		 this.observacion= observacion;
		 return this;
	 }
	 public EstacionamientoEntity build(){
		 EstacionamientoEntity entity = new EstacionamientoEntity(this.placa,this.fechaHoraInicio,this.observacion);
		 entity.setVehiculo(this.vehiculo);
		 return entity;
	 }
	 public Iterable<EstacionamientoEntity> buildIterable(int size){
		 ArrayList<EstacionamientoEntity> iterable = new ArrayList<EstacionamientoEntity>();
		 for(int i=0;i<size;i++){
			 iterable.add(this.withVehiculoEntity(new VehiculoEntityBuilder().withPlaca(this.placa).buildCarro()).build());
		 }		 
		 return iterable;
	 }
	 
}
