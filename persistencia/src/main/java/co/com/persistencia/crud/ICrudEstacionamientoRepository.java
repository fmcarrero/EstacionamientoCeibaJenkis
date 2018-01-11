package co.com.persistencia.crud;

import org.springframework.data.repository.CrudRepository;

import co.com.persistencia.entity.EstacionamientoEntity;



public interface ICrudEstacionamientoRepository extends CrudRepository<EstacionamientoEntity, String> {

}
