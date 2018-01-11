package co.com.persistenciaImplementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.domain.Factura;
import co.com.persistencia.base.BaseRepository;
import co.com.persistencia.crud.ICrudFacturaRepository;
import co.com.persistencia.entity.FacturaEntity;
import co.com.repository.IFacturaRepository;

@Repository
public class FacturaRepository extends BaseRepository<FacturaEntity,Factura> implements IFacturaRepository{

	@Autowired
	private ICrudFacturaRepository crudFacturaRepository;
	public  FacturaRepository(){
		super(Factura.class, FacturaEntity.class);
	}

	@Override
	public void guardar(Factura factura) {
		FacturaEntity entity = new FacturaEntity(factura.getPlaca(), factura.getFechaHoraFin(), factura.getFechaHoraFin(),factura.getTarifaHora(), factura.getTarifaDia(),factura.getValorPagar());
		this.crudFacturaRepository.save(entity);
	}
}
