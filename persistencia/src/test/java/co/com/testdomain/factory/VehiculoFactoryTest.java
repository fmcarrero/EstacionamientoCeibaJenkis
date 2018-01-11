package co.com.testdomain.factory;

import static org.junit.Assert.*;

import org.excepciones.VehiculoException;
import org.junit.Before;
import org.junit.Test;

import co.com.builder.persistencia.EstacionamientoEntityBuilder;
import co.com.builder.persistencia.VehiculoEntityBuilder;
import co.com.builder.persistencia.VehiculoTestDataBuilder;
import co.com.domain.Carro;
import co.com.domain.Moto;
import co.com.domain.Vehiculo;
import co.com.persistencia.entity.CarroEntity;
import co.com.persistencia.entity.MotoEntity;
import co.com.persistencia.entity.VehiculoEntity;
import factory.VehiculoFactory;


public class VehiculoFactoryTest {
	VehiculoFactory factory ;
	
	@Before
	public void setup(){
		this.factory = new VehiculoFactory();
	}
	
	@Test
	public void getVehiculoTestCarroOk() throws VehiculoException{
		//arrange
		VehiculoEntity entity = new VehiculoEntityBuilder().buildCarro();
		entity.setEstacionamiento(new EstacionamientoEntityBuilder().withPlaca(entity.getPlaca()).build());
		//act		
		Vehiculo vehiculo = factory.getVehiculo(entity);
		//assert
		assertEquals(vehiculo.getClass().getSimpleName(), Carro.class.getSimpleName());	
	}
	@Test
	public void getVehiculoTestMotoOk() throws VehiculoException{
		//arrange
		VehiculoEntity entity = new VehiculoEntityBuilder().buildMoto();
		entity.setEstacionamiento(new EstacionamientoEntityBuilder().withPlaca(entity.getPlaca()).build());
		//act		
		Vehiculo vehiculo = factory.getVehiculo(entity);
		//assert
		assertEquals(vehiculo.getClass().getSimpleName(), Moto.class.getSimpleName());		
	}
	
	@Test
	public void getVehiculoEntityMotoOk() throws VehiculoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildMoto();
		//act
		VehiculoEntity entity = this.factory.getVehiculoEntity(vehiculo);
		//assert
		assertEquals(entity.getClass().getSimpleName(), MotoEntity.class.getSimpleName());		
	}
	@Test
	public void getVehiculoEntityCarroOk() throws VehiculoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();
		//act
		VehiculoEntity entity = this.factory.getVehiculoEntity(vehiculo);
		//assert
		assertEquals(entity.getClass().getSimpleName(), CarroEntity.class.getSimpleName());		
	}
	
	@Test
	public void getVehiculoWithOUTEstacionamientoTestCarroOk() throws VehiculoException{
		//arrange
		VehiculoEntity entity = new VehiculoEntityBuilder().buildCarro();
		//act		
		Vehiculo vehiculo = factory.getVehiculoWithOUTEstacionamiento(entity);
		//assert
		assertEquals(vehiculo.getClass().getSimpleName(), Carro.class.getSimpleName());	
	}
	@Test
	public void getVehiculoWithOUTEstacionamientoTestMotoOk() throws VehiculoException{
		//arrange
		VehiculoEntity entity = new VehiculoEntityBuilder().buildMoto();
		//act		
		Vehiculo vehiculo = factory.getVehiculoWithOUTEstacionamiento(entity);
		//assert
		assertEquals(vehiculo.getClass().getSimpleName(), Moto.class.getSimpleName());	
	}
}
