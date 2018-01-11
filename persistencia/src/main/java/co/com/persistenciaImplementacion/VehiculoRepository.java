package co.com.persistenciaimplementacion;
import org.excepciones.VehiculoException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.domain.Vehiculo;
import co.com.persistencia.crud.ICrudVehiculoRepository;
import co.com.persistencia.entity.VehiculoEntity;
import co.com.repository.IVehiculoRepository;
import factory.IVehiculoFactory;


@Repository
public class VehiculoRepository implements IVehiculoRepository {

	@Autowired
	private ICrudVehiculoRepository crudVehiculoRepository;
	
	@Autowired
	private IVehiculoFactory vehiculoFactory;

	@Override
	public Vehiculo findByPlacaWithEstacionamiento(String placa) throws VehiculoException {
		VehiculoEntity vehiculo= this.crudVehiculoRepository.findByPlaca(placa);
		if(vehiculo == null || vehiculo.getEstacionamiento() == null){
			throw new ObjectNotFoundException("vehiculo no encontrado",placa);
		}
		return this.vehiculoFactory.getVehiculo(vehiculo);
	}


	@Override
	public void guardar(Vehiculo vehiculo) throws VehiculoException {
		VehiculoEntity vehiculoEntity = this.vehiculoFactory.getVehiculoEntity(vehiculo);
		this.crudVehiculoRepository.save(vehiculoEntity);		
	}


	@Override
	public Vehiculo get(String placa) throws VehiculoException {
		VehiculoEntity vehiculo= this.crudVehiculoRepository.findByPlaca(placa);
		if(vehiculo == null){
			throw new ObjectNotFoundException("vehiculo no encontrado",placa);
		}
		return this.vehiculoFactory.getVehiculoWithOUTEstacionamiento(vehiculo);
	}	

}
