package co.com.persistencia.entity;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Transient;



@Entity
public class MotoEntity extends VehiculoEntity {

	public MotoEntity(String placa, String color, long cilindraje) {
		super(placa, color, cilindraje);		
	}
	public MotoEntity(){}

}
