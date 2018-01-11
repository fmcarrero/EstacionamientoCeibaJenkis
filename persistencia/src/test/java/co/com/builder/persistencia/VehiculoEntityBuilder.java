package co.com.builder.persistencia;


import co.com.persistencia.entity.CarroEntity;
import co.com.persistencia.entity.EstacionamientoEntity;
import co.com.persistencia.entity.MotoEntity;

public class VehiculoEntityBuilder {
	
	 private String placa;	 
	 private String color;	 
	 private long cilindraje;	
	 protected EstacionamientoEntity estacionamiento;
	 
	 public VehiculoEntityBuilder(){
		 this.placa ="xcv-567";
		 this.color = "azul";
		 this.cilindraje=21;
	 }
	 public VehiculoEntityBuilder withEstacionamiento (EstacionamientoEntity entity){
		 this.estacionamiento = entity;
		 return this;
	 }
	 public VehiculoEntityBuilder withPlaca(String placa){
		 this.placa= placa;
		 return this;
	 }
	 public VehiculoEntityBuilder withColor (String color){
		 this.color = color;
		 return this;
		 
	 }
	 public VehiculoEntityBuilder withCilindraje(long cilindraje){
		 this.cilindraje = cilindraje;
		 return this;
	 }
	 public MotoEntity buildMoto(){
		 return new MotoEntity(this.placa,this.color,this.cilindraje);
	 }
	 public CarroEntity buildCarro(){
		 return new CarroEntity(this.placa,this.color,this.cilindraje);
	 }
}
