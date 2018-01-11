package co.com.persistencia.crud;

import org.springframework.data.repository.CrudRepository;

import co.com.persistencia.entity.VehiculoEntity;

public interface ICrudVehiculoRepository  extends CrudRepository<VehiculoEntity, Long>{
	
	VehiculoEntity findByPlaca(String placa);
}
