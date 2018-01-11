package co.com.persistencia.crud;

import org.springframework.data.repository.CrudRepository;

import co.com.persistencia.entity.FacturaEntity;

public interface ICrudFacturaRepository  extends CrudRepository<FacturaEntity, Long> {

}
