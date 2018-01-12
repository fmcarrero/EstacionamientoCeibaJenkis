package co.com.persistenciaimplementaciontest;

import static org.mockito.Mockito.doNothing;
import java.util.List;

import org.excepciones.VehiculoException;
import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import co.com.builder.persistencia.EstacionamientoEntityBuilder;
import co.com.builder.persistencia.EstacionamientoTestDataBuilder;
import co.com.domain.Estacionamiento;
import co.com.persistencia.crud.ICrudEstacionamientoRepository;
import co.com.persistencia.entity.EstacionamientoEntity;
import co.com.persistenciaimplementacion.EstacionamientoRepository;
import factory.IVehiculoFactory;


public class EstacionamientoRepositoryTest {
	
	@InjectMocks
	private EstacionamientoRepository estacionamientoRepository;
	
	@Mock
	private ICrudEstacionamientoRepository crudEstacionamientoRepository;
	
	@Mock
	private IVehiculoFactory vehiculoFactory;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void guardarTestOk(){
		//arrange
		String placa = "xdf-987";
		Mockito.when(this.crudEstacionamientoRepository.exists(placa)).thenReturn(false);
		EstacionamientoEntity entity = new EstacionamientoEntityBuilder().withPlaca(placa).build();
		Mockito.when(this.crudEstacionamientoRepository.save(entity)).thenReturn(entity);
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withPlaca(placa).build();
		//act
		this.estacionamientoRepository.guardar(estacionamiento);
		//assert
		Assert.assertEquals(placa, entity.getPlaca());
	}
	@Test (expected =DataIntegrityViolationException.class)
	public void guardarTestFail(){
		//arrange
		String placa = "xdf-987";
		Mockito.when(this.crudEstacionamientoRepository.exists(placa)).thenReturn(true);
		EstacionamientoEntity entity = new EstacionamientoEntityBuilder().withPlaca(placa).build();
		Mockito.when(this.crudEstacionamientoRepository.save(entity)).thenReturn(entity);
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withPlaca(placa).build();
		//act
		this.estacionamientoRepository.guardar(estacionamiento);
		//assert		
	}
	@Test
	public void getAllTest() throws VehiculoException{
		//arrange
		int size =3;
		Iterable<EstacionamientoEntity> iterable= new EstacionamientoEntityBuilder().buildIterable(size);
		Mockito.when(this.crudEstacionamientoRepository.findAll()).thenReturn(iterable);
		//act
		List<Estacionamiento> estacionamientos = this.estacionamientoRepository.getAll();
		//assert
		Assert.assertEquals(size, estacionamientos.size());		
	}
	@Test
	public void deleteTest(){
		//arrange
		EstacionamientoEntity entity = new EstacionamientoEntityBuilder().build();
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().build();
		doNothing().when(this.crudEstacionamientoRepository).delete(entity);
		
		//act
		this.estacionamientoRepository.delete(estacionamiento);
		//assert
		Assert.assertFalse(false);		
	}
	@Test
	public void getOkTest(){
		//arrange
		String placa="dcx-654";
		EstacionamientoEntity entity = new EstacionamientoEntityBuilder().withPlaca(placa).build();
		Mockito.when(this.crudEstacionamientoRepository.findOne(placa)).thenReturn(entity);
		//act
		Estacionamiento estacionamiento = this.estacionamientoRepository.get(placa);
		//assert
		Assert.assertEquals(estacionamiento.getPlaca(),entity.getPlaca());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void getFailTest(){
		//arrange
		String placa="dcx-654";
		Mockito.when(this.crudEstacionamientoRepository.findOne(placa)).thenReturn(null);
		//act
		this.estacionamientoRepository.get(placa);
		//assert
		Assert.fail();
	}
}
