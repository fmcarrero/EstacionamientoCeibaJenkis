package co.com.persistenciaimplementaciontest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import co.com.builder.persistencia.FacturaEntityTestDataBuilder;
import co.com.builder.persistencia.FacturaTestDataBuilder;
import co.com.domain.Factura;
import co.com.persistencia.crud.ICrudFacturaRepository;
import co.com.persistencia.entity.FacturaEntity;
import co.com.persistenciaimplementacion.FacturaRepository;

public class FacturaRepositoryTest {
	
	@InjectMocks
	private FacturaRepository facturaRepository;
	
	@Mock
	private ICrudFacturaRepository crudFacturaRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
    }
	@Test
	public void guardarOkTest(){
		//arrange
		Factura factura = new FacturaTestDataBuilder().build();
		FacturaEntity entity = new FacturaEntityTestDataBuilder().build();
		Mockito.when(this.crudFacturaRepository.save(entity)).thenReturn(entity);
		//act
		this.facturaRepository.guardar(factura);
		//assert
		Assert.assertNotNull(entity); 
	}
	
}
