package co.com.repository;



import org.excepciones.VehiculoException;

import co.com.domain.Vehiculo;

public interface IVehiculoRepository {
	
	Vehiculo findByPlacaWithEstacionamiento(String placa) throws VehiculoException;

	void guardar(Vehiculo vehiculo) throws VehiculoException;

	Vehiculo get(String placa)  throws VehiculoException ;
}
