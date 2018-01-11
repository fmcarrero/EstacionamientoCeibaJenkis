package factory;
import org.excepciones.VehiculoException;

import co.com.domain.Vehiculo;
import co.com.persistencia.entity.VehiculoEntity;



public interface IVehiculoFactory {
	Vehiculo getVehiculo(VehiculoEntity vehiculo) throws VehiculoException;

	VehiculoEntity getVehiculoEntity(Vehiculo vehiculo) throws VehiculoException;
	
	Vehiculo getVehiculoWithOUTEstacionamiento(VehiculoEntity vehiculo) throws VehiculoException;
}
