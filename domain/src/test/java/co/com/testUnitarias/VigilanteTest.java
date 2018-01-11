package co.com.testUnitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.dto.SalidaVehiculoDto;
import org.excepciones.VehiculoException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.builder.domain.EstacionamientoTestDataBuilder;
import co.com.builder.domain.FacturaTestDataBuilder;
import co.com.builder.domain.SalidaVehiculoDtoTestDataBuilder;
import co.com.builder.domain.VehiculoTestDataBuilder;
import co.com.domain.Estacionamiento;
import co.com.domain.Factura;
import co.com.domain.Vehiculo;
import co.com.domain.Vigilante;
import co.com.repository.IEstacionamientoRepository;
import co.com.repository.IFacturaRepository;
import co.com.repository.IVehiculoRepository;


public class VigilanteTest {
	
	private IVehiculoRepository vehiculoRepository;
	private IEstacionamientoRepository estacionamientoRepository;
	private IFacturaRepository facturaRepository;
	private Vigilante vigilante;
	
	@Before
	public void setup(){
		this.vehiculoRepository = Mockito.mock(IVehiculoRepository.class);
		this.estacionamientoRepository = Mockito.mock(IEstacionamientoRepository.class);
		this.facturaRepository = Mockito.mock(IFacturaRepository.class);
		this.vigilante = new Vigilante(this.vehiculoRepository,this.estacionamientoRepository,this.facturaRepository);	
	}
	
	@Test
	public void RegistrarVehiculoTest() throws Exception {
		//arrange		
		Vehiculo vehiculoMoto = new VehiculoTestDataBuilder().buildMoto();	
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withObservacion("BITCH").withPlaca(vehiculoMoto.getPlaca()).build();
		estacionamiento.setVehiculo(vehiculoMoto);
		doNothing().when(this.vehiculoRepository).guardar(vehiculoMoto);
		doNothing().when(this.estacionamientoRepository).guardar(estacionamiento);			
		//act
		vigilante.registrarVehiculo(vehiculoMoto);
		//assert
		assertNotNull(vehiculoMoto);
	}
	@Test
	public void ValidarPlacaOKTest() throws Exception {
		//arrange
		Vehiculo vehiculoCarro = new VehiculoTestDataBuilder().withPlaca("ADF-87").buildCarro();  
		DayOfWeek day = LocalDate.now().withMonth(12).withYear(2017).withDayOfMonth(26).getDayOfWeek();
		//act
		vigilante.validarPlaca(vehiculoCarro, day);
	}
	@Test
	public void ValidarDisponibilidad() throws Exception {
		//arrange		
		Estacionamiento  estacionamiento = new EstacionamientoTestDataBuilder().withPlaca("xcv-456").build();
		Vehiculo vehiculoCarro = new VehiculoTestDataBuilder()
									.withEstacionamiento(estacionamiento)
									.withPlaca("xcv-456").buildCarro();
		List<Estacionamiento> lista = new EstacionamientoTestDataBuilder().buildListCarros(3);
		Mockito.when(this.estacionamientoRepository.getAll()).thenReturn(lista);
				
		//act		
		vigilante.validarDisponibilidadEstacionamiento(vehiculoCarro);		
		//assert
		verify(this.estacionamientoRepository,times(1)).getAll();		
	}
	@Test(expected = Exception.class)
	public void ValidarDisponibilidadFail() throws Exception {
		//arrange		
		Vehiculo vehiculoCarro = new VehiculoTestDataBuilder().buildCarro();
		List<Estacionamiento> lista = new EstacionamientoTestDataBuilder().buildListCarros(2423);
		Mockito.when(this.estacionamientoRepository.getAll()).thenReturn(lista);
		
		//act		
		vigilante.validarDisponibilidadEstacionamiento(vehiculoCarro);		
		//assert		
	}
	@Test(expected = Exception.class)
	public void ValidarPlacaFailTest() throws Exception {
		//arrange
		Vehiculo vehiculoCarro = new VehiculoTestDataBuilder().withPlaca("ADF-87").buildCarro();  
		DayOfWeek day = LocalDate.now().withMonth(12).withYear(2017).withDayOfMonth(24).getDayOfWeek();		
		//act
		vigilante.validarPlaca(vehiculoCarro,day);
	}
	
	@Test
	public void salidaVehiculoOkTest() throws VehiculoException {
		//arrange
		LocalDateTime hoy =LocalDateTime.of(2018, 1, 2,0,0);
		LocalDateTime mañana =LocalDateTime.of(2018, 1, 3,0,0);
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();		
		SalidaVehiculoDto salidaVehiculo = new SalidaVehiculoDtoTestDataBuilder().withFechaHoraFin(Timestamp.valueOf(mañana)).withPlaca(vehiculo.getPlaca()).build();
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withFechaHoraInicio(Timestamp.valueOf(hoy)).withPlaca(vehiculo.getPlaca()).build();
		vehiculo.setEstacionamiento(estacionamiento);
		Mockito.when(this.vehiculoRepository.findByPlacaWithEstacionamiento(vehiculo.getPlaca())).thenReturn(vehiculo);
		Factura factura = new FacturaTestDataBuilder().build();
		doNothing().when(this.facturaRepository).guardar(factura);
		doNothing().when(this.estacionamientoRepository).delete(vehiculo.getEstacionamiento());
		//act		
		Factura facturaResultado=vigilante.salidaVehiculo(salidaVehiculo);
		//assert
		assertNotNull(factura.getPlaca(), facturaResultado.getPlaca());		
	}
	
	@Test(expected = VehiculoException.class)
	public void salidaVehiculoFailTest() throws VehiculoException {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();		
		SalidaVehiculoDto salidaVehiculo = new SalidaVehiculoDtoTestDataBuilder().withPlaca(vehiculo.getPlaca()).build();
		Mockito.when(this.vehiculoRepository.findByPlacaWithEstacionamiento(vehiculo.getPlaca())).thenReturn(vehiculo);
		Factura factura = new FacturaTestDataBuilder().build();
		doNothing().when(this.facturaRepository).guardar(factura);
		doNothing().when(this.estacionamientoRepository).delete(vehiculo.getEstacionamiento());
		//act		
		vigilante.salidaVehiculo(salidaVehiculo);
		//assert
		assertNotNull(vehiculo.getEstacionamiento());		
	}
	@Test(expected = IllegalArgumentException.class)
	public void salidaVehiculoFailFechaSalidaMenorTest() throws VehiculoException {
		//arrange
		Timestamp fechahorafin = Timestamp.valueOf(LocalDateTime.of(2017, 1, 6,0,0));		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();		
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withPlaca(vehiculo.getPlaca()).build();
		vehiculo.setEstacionamiento(estacionamiento);
		SalidaVehiculoDto salidaVehiculo = new SalidaVehiculoDtoTestDataBuilder().withFechaHoraFin(fechahorafin).withPlaca(vehiculo.getPlaca()).build();
		Mockito.when(this.vehiculoRepository.findByPlacaWithEstacionamiento(vehiculo.getPlaca())).thenReturn(vehiculo);
		Factura factura = new FacturaTestDataBuilder().build();
		doNothing().when(this.facturaRepository).guardar(factura);
		doNothing().when(this.estacionamientoRepository).delete(vehiculo.getEstacionamiento());
		//act
		vigilante.salidaVehiculo(salidaVehiculo);
		//assert
		assertNotNull(vehiculo.getEstacionamiento());		
	}
	@Test
	public void crearFacturaOkTest() {
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withPlaca(vehiculo.getPlaca()).withVehiculo(vehiculo).build();
		SalidaVehiculoDto salidavehiculo = new SalidaVehiculoDtoTestDataBuilder().withPlaca(vehiculo.getPlaca()).build();

		//act
		Factura factura=vigilante.crearFactura(vehiculo, estacionamiento, salidavehiculo);
		//assert
		assertNotNull(factura);
	}
	@Test 
	public void findAllVehiculosOkTest() throws VehiculoException {
		//arrange
		List<Estacionamiento> estacionamientos = new ArrayList<>();
		when(this.estacionamientoRepository.getAll()).thenReturn(estacionamientos);
		
		//act
		List<Estacionamiento> estacionamientos2= vigilante.findAllVehiculos();
		//assert
		assertEquals(estacionamientos.size(), estacionamientos2.size());
	}
	
	@Test
	public void  finVehiculoByPlacaOkTest() throws VehiculoException{
		//arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();
		//act
		Mockito.when(this.vehiculoRepository.get(vehiculo.getPlaca())).thenReturn(vehiculo);
		//assert
		assertNotNull(vehiculo);
	}
	
	@Test 
	public void getValorAPagarTest(){
		//arrage
		LocalDateTime hoy = LocalDateTime.of(2018, 1, 2,0,0);
		LocalDateTime masTarde = hoy.withHour(10);
		Vehiculo vehiculo = new VehiculoTestDataBuilder().buildCarro();
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withFechaHoraInicio(Timestamp.valueOf(hoy)).withVehiculo(vehiculo).build();
		SalidaVehiculoDto salidaVehiculoDto = new SalidaVehiculoDtoTestDataBuilder().withFechaHoraFin(Timestamp.valueOf(masTarde)).build();
		//act
		double valorapagar = this.vigilante.getValorAPagar(vehiculo, estacionamiento, salidaVehiculoDto);
		System.out.println(valorapagar);
		//assert
		assertEquals(valorapagar, vehiculo.getTariaDia(),0);
	}
	
}
