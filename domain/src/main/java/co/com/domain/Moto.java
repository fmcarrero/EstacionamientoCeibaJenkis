package co.com.domain;

import org.enumvehiculos.TipoVehiculoEnum;

public class Moto  extends Vehiculo {
	
	
	private double tarifaDia=4000;
	private double tarifaHora = 500;
	private double tarifaAdicionalCilidndraje =2000;
	
	public Moto(String placa, String color, long cilindraje) {
		super(placa, color, cilindraje);		
	}
	@Override
	public int getIdTipoVehiculo(){
		return TipoVehiculoEnum.MOTO.getValue();
	}
	@Override
	public double getTarifaDia(){ return this.tarifaDia;}
	
	@Override
	public double getTarifaHora(){ return this.tarifaHora;}
	
	@Override
	public double getTarifaAdicionalCilidndraje() {return this.tarifaAdicionalCilidndraje;}
}
