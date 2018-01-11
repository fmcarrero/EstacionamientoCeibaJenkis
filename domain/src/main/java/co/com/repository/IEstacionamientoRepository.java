package co.com.repository;

import java.util.List;

import org.excepciones.VehiculoException;

import co.com.domain.Estacionamiento;



public interface IEstacionamientoRepository {
	void guardar(Estacionamiento vehiculo) ;
	List<Estacionamiento> getAll() throws VehiculoException  ;
	void delete(Estacionamiento estacionamiento);
	Estacionamiento get(String placa);	
}
