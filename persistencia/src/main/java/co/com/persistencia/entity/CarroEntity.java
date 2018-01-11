package co.com.persistencia.entity;
import javax.persistence.Entity;


@Entity
public class CarroEntity extends VehiculoEntity {

	public CarroEntity(String placa, String color, long cilindraje) {
		super(placa, color, cilindraje);
		
	}	
	
	public CarroEntity(){}
	
}
