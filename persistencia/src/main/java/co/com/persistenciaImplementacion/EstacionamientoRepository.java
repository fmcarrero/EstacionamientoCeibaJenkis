package co.com.persistenciaimplementacion;

import java.util.ArrayList;
import java.util.List;

import org.excepciones.VehiculoException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import co.com.domain.Estacionamiento;
import co.com.persistencia.base.BaseRepository;
import co.com.persistencia.crud.ICrudEstacionamientoRepository;
import co.com.persistencia.entity.EstacionamientoEntity;
import co.com.repository.IEstacionamientoRepository;
import factory.IVehiculoFactory;

@Repository
public class EstacionamientoRepository extends BaseRepository<EstacionamientoEntity,Estacionamiento> implements IEstacionamientoRepository {

	@Autowired
	private ICrudEstacionamientoRepository crudEstacionamientoRepository;
	
	@Autowired
	private IVehiculoFactory vehiculoFactory;
	
	public EstacionamientoRepository() {
		super(Estacionamiento.class, EstacionamientoEntity.class);
	}
	
	@Override
	public void guardar(Estacionamiento vehiculo)  {		
		if(this.crudEstacionamientoRepository.exists(vehiculo.getPlaca())){
			throw new DataIntegrityViolationException("Vehiculo ya se encuentra en el estacionamiento");
		}
		EstacionamientoEntity estacionamientoEntity = new EstacionamientoEntity(vehiculo.getPlaca(),vehiculo.getFechaHoraInicio(),vehiculo.getObservacion());
		this.crudEstacionamientoRepository.save(estacionamientoEntity);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Estacionamiento> getAll() throws VehiculoException {		
		List<EstacionamientoEntity> lista = Lists.newArrayList(this.crudEstacionamientoRepository.findAll());
		List<Estacionamiento> listaEstacionamiento = new ArrayList<>();
		for( EstacionamientoEntity e : lista){
			Estacionamiento estacionamiento = new Estacionamiento(e.getPlaca(),e.getFechaHoraInicio(),e.getObservacion());
			estacionamiento.setVehiculo(this.vehiculoFactory.getVehiculo(e.getVehiculo()));
			listaEstacionamiento.add(estacionamiento);
		}		
		return listaEstacionamiento;	
	}

	@Override
	public void delete(Estacionamiento estacionamiento) {
		EstacionamientoEntity estacionamientoEntity = new EstacionamientoEntity(estacionamiento.getPlaca(),estacionamiento.getFechaHoraInicio(),estacionamiento.getObservacion());
		this.crudEstacionamientoRepository.delete(estacionamientoEntity);		
	}

	@Override
	public Estacionamiento get(String placa) {
		EstacionamientoEntity entity = this.crudEstacionamientoRepository.findOne(placa);
		if(entity == null ){
			throw new ObjectNotFoundException("Estacionamiento no encontrado",placa);
		}
		return  new Estacionamiento(entity.getPlaca(),entity.getFechaHoraInicio(),entity.getObservacion());
	}
}
