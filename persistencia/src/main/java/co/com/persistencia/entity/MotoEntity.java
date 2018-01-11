package co.com.persistencia.entity;
import javax.persistence.Entity;

@Entity
public class MotoEntity extends VehiculoEntity {

	public MotoEntity(String placa, String color, long cilindraje) {
		super(placa, color, cilindraje);		
	}
	public MotoEntity(){}

}
