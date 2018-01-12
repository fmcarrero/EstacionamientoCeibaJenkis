package co.com.builderweb;

import co.com.domain.Cotizacion;
import co.com.domain.Estacionamiento;

public class CotizacionTestDataBuilder {
	private Estacionamiento estacionamiento;
	private double valorPagar;
	
	public CotizacionTestDataBuilder(){
		this.valorPagar=10;
		this.estacionamiento = new EstacionamientoTestDataBuilder().build();
	}
	public Cotizacion build(){
		return new Cotizacion(this.estacionamiento, this.valorPagar);
	}
}
