package co.com.persistenciaimplementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.domain.Factura;
import co.com.persistencia.crud.ICrudFacturaRepository;
import co.com.persistencia.entity.FacturaEntity;
import co.com.repository.IFacturaRepository;

@Repository
public class FacturaRepository  implements IFacturaRepository{

	@Autowired
	private ICrudFacturaRepository crudFacturaRepository;
	

	@Override
	public void guardar(Factura factura) {
		FacturaEntity entity = new FacturaEntity(factura.getPlaca(), factura.getFechaHoraFin(), factura.getFechaHoraFin(),factura.getTarifaHora(), factura.getTarifaDia(),factura.getValorPagar());
		this.crudFacturaRepository.save(entity);
	}
}
