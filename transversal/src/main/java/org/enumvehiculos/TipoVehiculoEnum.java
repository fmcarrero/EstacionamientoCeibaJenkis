package org.enumvehiculos;
public enum TipoVehiculoEnum {
	MOTO(1),	CARRO(2);	
	private final int id;
	private TipoVehiculoEnum(int id) {
		this.id = id; 
	}
    public int getValue() {
    	return id; 
    }
}
