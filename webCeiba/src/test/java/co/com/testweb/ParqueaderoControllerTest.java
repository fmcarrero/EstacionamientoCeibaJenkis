package co.com.testweb;

import static org.mockito.Mockito.doNothing;

import java.util.List;

import org.dto.SalidaVehiculoDto;
import org.excepciones.VehiculoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.builderweb.CotizacionTestDataBuilder;
import co.com.builderweb.EstacionamientoTestDataBuilder;
import co.com.builderweb.FacturaTestDataBuilder;
import co.com.builderweb.SalidaVehiculoDtoTestDataBuilder;
import co.com.builderweb.VehiculoTestDataBuilder;
import co.com.domain.Cotizacion;
import co.com.domain.Estacionamiento;
import co.com.domain.Factura;
import co.com.domain.Vehiculo;
import co.com.domain.Vigilante;
import co.com.web.controller.ParqueaderoController;

public class ParqueaderoControllerTest {
	
	private static final String vehiculoOK="Vehiculo Creado";
	@InjectMocks
	private ParqueaderoController parqueaderoController;
	
	@Mock
	private Vigilante vigilante;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void createVehiculoTest() throws Exception{
		//arrange
		Vehiculo vehiculo= new VehiculoTestDataBuilder().buildCarro();
		doNothing().when(this.vigilante).registrarVehiculo(vehiculo);
		//act
		ResponseEntity<String> response = this.parqueaderoController.createVehiculo(vehiculo);
		//assert
		Assert.assertEquals(response.getBody(),vehiculoOK);
	}
	@Test
	public void listAllVehiculosTest() throws VehiculoException{
		//arrange
		int size =3;
		List<Estacionamiento> estacionamientos = new EstacionamientoTestDataBuilder().buildListCarros(size);
		Mockito.when(this.vigilante.findAllVehiculos()).thenReturn(estacionamientos);
		//act
		ResponseEntity<List<Estacionamiento>> responseEntity= this.parqueaderoController.listAllVehiculos();
		//assert
		Assert.assertEquals(responseEntity.getBody(),estacionamientos);
	}
	@Test
	public void listAllVehiculosFailTest() throws VehiculoException{
		//arrange
		int size =0;
		List<Estacionamiento> estacionamientos = new EstacionamientoTestDataBuilder().buildListCarros(size);
		Mockito.when(this.vigilante.findAllVehiculos()).thenReturn(estacionamientos);
		//act
		ResponseEntity<List<Estacionamiento>> responseEntity= this.parqueaderoController.listAllVehiculos();
		//assert
		Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	@Test
	public void salidaVehiculoTest() throws Exception{
		//arrange
		SalidaVehiculoDto salidaVehiculoDto = new SalidaVehiculoDtoTestDataBuilder().build();
		Factura factura = new FacturaTestDataBuilder().build();
		Mockito.when(this.vigilante.salidaVehiculo(salidaVehiculoDto)).thenReturn(factura);
		//act
		ResponseEntity<Factura> responseEntity = this.parqueaderoController.salidaVehiculo(salidaVehiculoDto);
		//assert
		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);				
	}
	@Test
	public void getCotizacionEstacionamientoTest() throws VehiculoException{
		//arrange
		String placa="xcv-654";
		Cotizacion  cotizacion = new CotizacionTestDataBuilder().build();
		Mockito.when(this.vigilante.estimarValorPagar(placa)).thenReturn(cotizacion);
		//act
		ResponseEntity<Cotizacion> responseEntity= this.parqueaderoController.getCotizacionEstacionamiento(placa);
		//assert
		Assert.assertEquals(cotizacion.getValorPagar(),responseEntity.getBody().getValorPagar(),0);
	}
	
	@Test
	public void getVehiculoTest() throws VehiculoException{
		//arrange
		String placa="xcv-654";
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();
		Mockito.when(this.vigilante.finVehiculoByPlaca(placa)).thenReturn(vehiculo);
		//act
		ResponseEntity<Vehiculo> responseEntity= this.parqueaderoController.getVehiculo(placa);
		//assert
		Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
	}
}
