package co.com.domain;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.dto.SalidaVehiculoDto;
import org.enumvehiculos.TipoVehiculoEnum;
import org.excepciones.VehiculoException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateSystemException;
import org.util.fechas.Fechas;

import co.com.repository.IEstacionamientoRepository;
import co.com.repository.IFacturaRepository;
import co.com.repository.IVehiculoRepository;




public class Vigilante {
	
	private IVehiculoRepository vehiculoRepository;
	private IEstacionamientoRepository estacionamientoRepository;
	private IFacturaRepository facturaRepository;
	private static final int DISPONIBILIDADMOTO = 10; 
	private static final int DISPONIBILIDADCARRO = 20; 
	
	public Vigilante(IVehiculoRepository vehiculoRepository, IEstacionamientoRepository estacionamientoRepository,IFacturaRepository facturaRepository){
		this.vehiculoRepository =vehiculoRepository;
		this.estacionamientoRepository=estacionamientoRepository;
		this.facturaRepository=facturaRepository;
	}
	
	public Vehiculo finVehiculoByPlaca (String placa) throws VehiculoException{		
		return  this.vehiculoRepository.get(placa);
	}

	@Transactional
	public void registrarVehiculo(Vehiculo vehiculo)throws Exception  {
		try {	
			LocalDateTime localDate = LocalDateTime.now();	
	        Timestamp fechaHoraIngreso = Timestamp.valueOf(localDate);
			Estacionamiento estacionamiento = new Estacionamiento(vehiculo.getPlaca(),fechaHoraIngreso,vehiculo.getObservacion());
			vehiculo.setEstacionamiento(estacionamiento);		
			DayOfWeek dayOfWeek = Fechas.getDiaActual();
			validarPlaca(vehiculo,dayOfWeek);
			validarDisponibilidadEstacionamiento(vehiculo);	
			this.vehiculoRepository.guardar(vehiculo);
			this.estacionamientoRepository.guardar(estacionamiento);
		}
		catch (DataIntegrityViolationException e) {
			throw e;
		}
		catch(Exception e) {
			throw new Exception("error desconocido");
		}
		
	}	
	
	public void validarDisponibilidadEstacionamiento(Vehiculo vehiculo) throws Exception {    
		
		long cupoparqueadero=this.estacionamientoRepository.getAll()			
				.stream()				
				.filter(e -> e.getVehiculo().getIdTipoVehiculo() == vehiculo.getIdTipoVehiculo()).count();
		
		if(vehiculo.getIdTipoVehiculo() == TipoVehiculoEnum.CARRO.getValue()){
			if (cupoparqueadero>this.DISPONIBILIDADCARRO)
				throw new VehiculoException("parqueadero se encuentra lleno");
		}else if(vehiculo.getIdTipoVehiculo() == TipoVehiculoEnum.MOTO.getValue() && cupoparqueadero>this.DISPONIBILIDADMOTO) {
		     		throw new VehiculoException("parqueadero se encuentra lleno");
		}		
	}
	public void validarPlaca(Vehiculo vehiculo,DayOfWeek dayOfWeek) throws VehiculoException {
		char letra = vehiculo.getPlaca().replaceAll("[^a-zA-Z]+", "").toUpperCase().charAt(0);
		if( letra=='A' && puedeIngresar(dayOfWeek.getValue())) {				
			throw new VehiculoException("No puede parquear el dia de hoy");			
		}
	}
	private boolean  puedeIngresar(int dayOfWeek) {		
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(7);
		return lista.contains(dayOfWeek);		
	}

	public List<Estacionamiento> findAllVehiculos() throws VehiculoException {		
		return this.estacionamientoRepository.getAll();
	}
	@Transactional
	public Factura salidaVehiculo(SalidaVehiculoDto vehiculoDto) throws VehiculoException {		
		try {			
			Vehiculo vehiculo =this.vehiculoRepository.findByPlacaWithEstacionamiento(vehiculoDto.getPlaca());//
			if(vehiculo.getEstacionamiento()==null) {
				throw new VehiculoException("el vehiculo no se encuentra en el estacionamiento");
			}	
			Estacionamiento estacionamiento=vehiculo.getEstacionamiento();				
			if(vehiculoDto.getFechaHoraFin().before(estacionamiento.getFechaHoraInicio())) {
				throw new IllegalArgumentException("la fecha de salida debe ser mayor a la fecha de ingreso");
			}
			Factura factura = crearFactura(vehiculo,estacionamiento,vehiculoDto);
			this.facturaRepository.guardar(factura);
			this.estacionamientoRepository.delete(estacionamiento);
			return factura;
		}
		catch(ObjectNotFoundException | HibernateSystemException | VehiculoException e) {
			throw new VehiculoException("el vehiculo no se encuentra en el estacionamiento",e);
		}		
		catch (Exception e) {			
			throw e;
		}		
	}
	public Factura crearFactura(Vehiculo vehiculo ,Estacionamiento estacionamiento,SalidaVehiculoDto salidaVehiculo ) {
		double valoraPagar = this.getValorAPagar(vehiculo,estacionamiento,salidaVehiculo);
		return new Factura(vehiculo.getPlaca(),estacionamiento.getFechaHoraInicio() ,salidaVehiculo.getFechaHoraFin(), vehiculo.getTariaHora(),vehiculo.getTariaDia(), valoraPagar);
	}
	public double getValorAPagar(Vehiculo vehiculo, Estacionamiento estacionamiento, SalidaVehiculoDto salidaVehiculoDto ){
		
		 Timestamp fechaHoraFin = salidaVehiculoDto.getFechaHoraFin();
		 Timestamp fechaHoraInicio = estacionamiento.getFechaHoraInicio();
		 int diferenciadias =(int)(( fechaHoraFin.getTime() -  fechaHoraInicio.getTime())/86400000);
		 int diferenciaHoras =(int)((fechaHoraFin.getTime() - fechaHoraInicio.getTime())/3600000)-(diferenciadias*24);
		 if(diferenciaHoras>=9) {
				diferenciadias++;
				diferenciaHoras=0;
		 }
		 double valorPagar=calcularDia(diferenciadias,vehiculo);
		 valorPagar += calcularHora(diferenciaHoras,vehiculo);
		 return vehiculo.getCilindraje()> 500 ?  valorPagar + vehiculo.getTarifaAdicionalCilidndraje()  : valorPagar;
		
	}
	public double calcularDia(int horas,Vehiculo vehiculo) {
		return  horas*vehiculo.getTariaDia();
	}
	public double calcularHora(int horas,Vehiculo vehiculo) {			
			return (double) horas * vehiculo.getTariaHora();
	}
	
	
	public Cotizacion estimarValorPagar(String placa) throws VehiculoException {
		Estacionamiento estacionamiento= this.estacionamientoRepository.get(placa);	
		Vehiculo vehiculo = this.vehiculoRepository.get(placa);
		estacionamiento.setVehiculo(vehiculo);
		LocalDateTime localDate = LocalDateTime.now();	
        Timestamp fechaHorasalida = Timestamp.valueOf(localDate);	        
        double valorpagar =this.getValorAPagar(vehiculo, estacionamiento, new SalidaVehiculoDto(placa, fechaHorasalida)) ;
		return new Cotizacion(estacionamiento, valorpagar);
	}
}
