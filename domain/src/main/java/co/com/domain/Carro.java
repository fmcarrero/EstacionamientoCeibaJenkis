package co.com.domain;

import org.enumvehiculos.TipoVehiculoEnum;

public class Carro extends Vehiculo {
	
	private double tarifaDia=8000;
	private double tarifaHora = 1000;
	private double TarifaAdicionalCilidndraje =0;
	public Carro(String placa, String color, long cilindraje) {
		super(placa, color, cilindraje);
	}
	
	@Override
	public int getIdTipoVehiculo(){
		return TipoVehiculoEnum.CARRO.getValue();
	}
	@Override
	public double getTariaDia(){ return this.tarifaDia;}
	
	@Override
	public double getTariaHora(){ return this.tarifaHora;}
	@Override
	public double getTarifaAdicionalCilidndraje() {return this.TarifaAdicionalCilidndraje;}
}
