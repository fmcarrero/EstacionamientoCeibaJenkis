package co.com.domain;

import org.enumvehiculos.TipoVehiculoEnum;

public class Moto  extends Vehiculo {
	
	
	private double tarifaDia=4000;
	private double tarifaHora = 500;
	private double TarifaAdicionalCilidndraje =2000;
	
	public Moto(String placa, String color, long cilindraje) {
		super(placa, color, cilindraje);		
	}
	@Override
	public int getIdTipoVehiculo(){
		return TipoVehiculoEnum.MOTO.getValue();
	}
	@Override
	public double getTariaDia(){ return this.tarifaDia;}
	
	@Override
	public double getTariaHora(){ return this.tarifaHora;}
	
	@Override
	public double getTarifaAdicionalCilidndraje() {return this.TarifaAdicionalCilidndraje;}
}
