package co.com.persistenciaimplementaciontest;

import org.excepciones.VehiculoException;
import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;


import co.com.builder.persistencia.EstacionamientoEntityBuilder;
import co.com.builder.persistencia.VehiculoEntityBuilder;
import co.com.builder.persistencia.VehiculoTestDataBuilder;
import co.com.domain.Vehiculo;
import co.com.persistencia.crud.ICrudVehiculoRepository;
import co.com.persistencia.entity.EstacionamientoEntity;
import co.com.persistencia.entity.VehiculoEntity;
import co.com.persistenciaimplementacion.VehiculoRepository;
import factory.IVehiculoFactory;


public class VehiculoRepositoryTest {
	
	@InjectMocks
	private VehiculoRepository vehiculoRepository;
	
	@Mock
	private ICrudVehiculoRepository crudVehiculoRepository;
	
	@Mock
	private IVehiculoFactory vehiculoFactory;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void findByPlacaWithEstacionamientoTest() throws VehiculoException{
		//arrange
		String placa ="xcv-765";		
		VehiculoEntity entity = new VehiculoEntityBuilder().withPlaca(placa).buildCarro();
		EstacionamientoEntity entityestacionamiento = new EstacionamientoEntityBuilder().withPlaca(placa).build();
		entity.setEstacionamiento(entityestacionamiento);
		Vehiculo vehiculoMock = new VehiculoTestDataBuilder().withPlaca(placa).buildCarro();
		Mockito.when(this.crudVehiculoRepository.findByPlaca(placa)).thenReturn(entity);
		Mockito.when(this.vehiculoFactory.getVehiculo(entity)).thenReturn(vehiculoMock);
		//act
	    Vehiculo vehiculo= this.vehiculoRepository.findByPlacaWithEstacionamiento(placa);
		//assert
	    assertEquals(vehiculo.getPlaca(), vehiculoMock.getPlaca());
	}
	@Test (expected =ObjectNotFoundException.class)
	public void findByPlacaWithEstacionamientoFailTest() throws VehiculoException{
		//arrange
		String placa ="xcv-765";		
		VehiculoEntity entity = new VehiculoEntityBuilder().withPlaca(placa).buildCarro();
		EstacionamientoEntity entityestacionamiento = new EstacionamientoEntityBuilder().withPlaca(placa).build();
		entity.setEstacionamiento(entityestacionamiento);
		Vehiculo vehiculoMock = new VehiculoTestDataBuilder().withPlaca(placa).buildCarro();
		Mockito.when(this.crudVehiculoRepository.findByPlaca(placa)).thenReturn(null);
		Mockito.when(this.vehiculoFactory.getVehiculo(entity)).thenReturn(vehiculoMock);
		//act
	    this.vehiculoRepository.findByPlacaWithEstacionamiento(placa);
		//assert	   
	}
	
	@Test
	public void guardarTest() throws VehiculoException{
		//arrage
		String placa ="xcv-765";		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca(placa).buildCarro();
		VehiculoEntity entity = new VehiculoEntityBuilder().withPlaca(placa).buildCarro();
		Mockito.when(this.vehiculoFactory.getVehiculoEntity(vehiculo)).thenReturn(entity);
		Mockito.when(this.crudVehiculoRepository.save(entity)).thenReturn(entity);
		//act
		this.vehiculoRepository.guardar(vehiculo);
		//assert
		assertNotNull(entity);
	}
	@Test
	public void getOkTest() throws VehiculoException{
		//arrange
		String placa ="xcv-765";	
		VehiculoEntity entity = new VehiculoEntityBuilder().withPlaca(placa).buildCarro();
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca(placa).buildCarro();
		Mockito.when(this.vehiculoFactory.getVehiculoWithOUTEstacionamiento(entity)).thenReturn(vehiculo);
		Mockito.when(this.crudVehiculoRepository.findByPlaca(placa)).thenReturn(entity);
		//act
		Vehiculo vehiculoEsperado = this.vehiculoRepository.get(placa);
		//assert
		assertNotNull(vehiculoEsperado);
	}
	@Test (expected = ObjectNotFoundException.class)
	public void getFailTest() throws VehiculoException{
		//arrange
		String placa ="xcv-765";	
		VehiculoEntity entity = new VehiculoEntityBuilder().withPlaca(placa).buildCarro();
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withPlaca(placa).buildCarro();
		Mockito.when(this.vehiculoFactory.getVehiculoWithOUTEstacionamiento(entity)).thenReturn(vehiculo);
		Mockito.when(this.crudVehiculoRepository.findByPlaca(placa)).thenReturn(null);
		//act
		Vehiculo vehiculoEsperado = this.vehiculoRepository.get(placa);
		//assert
	
	}
}
