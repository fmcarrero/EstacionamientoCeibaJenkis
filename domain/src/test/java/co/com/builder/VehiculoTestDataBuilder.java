package co.com.builder;

import co.com.domain.Carro;
import co.com.domain.Estacionamiento;
import co.com.domain.Moto;
import co.com.domain.Vehiculo;

public class VehiculoTestDataBuilder {
	
	 private String Placa;
	 private String Color;	 
	 private long Cilindraje;
	 private Estacionamiento estacionamiento;
	 
	 public VehiculoTestDataBuilder() {
		 this.Placa = "xct-87";
		 this.Color = "azul";
		 this.Cilindraje = 332;
		 this.estacionamiento = new EstacionamientoTestDataBuilder().build();
	 }
	 public VehiculoTestDataBuilder withPlaca(String placa) {
			this.Placa =placa;
			return this;
	 }
	 public VehiculoTestDataBuilder withColor(String color) {
			this.Color =color;
			return this;
	 }
	 public VehiculoTestDataBuilder withCilindraje(long cilindraje) {
			this.Cilindraje =cilindraje;
			return this;
	 }
	 public VehiculoTestDataBuilder withEstacionamiento(Estacionamiento estacionamiento) {
		 this.estacionamiento = estacionamiento;
		 return this;			 
	 }
	 
	 public Vehiculo buildCarro() {
		return new Carro(this.Placa,this.Color,this.Cilindraje);
	 }
	 public Vehiculo buildMoto() {
			return new Moto(this.Placa,this.Color,this.Cilindraje);
	 }
	
}
