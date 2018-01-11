package co.com.web.controller;

import java.util.List;

import org.dto.SalidaVehiculoDto;
import org.excepciones.VehiculoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import co.com.domain.Cotizacion;
import co.com.domain.Estacionamiento;
import co.com.domain.Factura;
import co.com.domain.Vehiculo;
import co.com.domain.Vigilante;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ParqueaderoController {
	
	@Autowired
	private Vigilante vigilante;
	
	
	@RequestMapping(value="/Vehiculo/",method = RequestMethod.POST)
    public ResponseEntity<String>  createVehiculo(@RequestBody Vehiculo vehiculo,   UriComponentsBuilder ucBuilder) throws Exception {		
		this.vigilante.registrarVehiculo(vehiculo);		
		return new ResponseEntity<String>("Vehiculo Creado",HttpStatus.CREATED);
    }
	
	 @RequestMapping(value = "/Vehiculo/", method = RequestMethod.GET)
	    public ResponseEntity<List<Estacionamiento>> listAllVehiculos() throws VehiculoException {
		   	List<Estacionamiento> estacionamientos = this.vigilante.findAllVehiculos();
	        if (estacionamientos.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }	        
	        return new ResponseEntity<List<Estacionamiento>> (estacionamientos, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value="/SalidaVehiculo/",method = RequestMethod.POST)
	    public ResponseEntity<Factura>  salidaVehiculo(@RequestBody SalidaVehiculoDto vehiculo,   UriComponentsBuilder ucBuilder) throws Exception{
			Factura factura= this.vigilante.salidaVehiculo(vehiculo);
			return new ResponseEntity<>(factura, HttpStatus.CREATED);
	    }
	 
	 @RequestMapping(value = "/Estacionamiento/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Cotizacion> getEstacionamiento(@PathVariable("id") String id) throws VehiculoException {	
	        Cotizacion cotizacion = this.vigilante.estimarValorPagar(id);	        
	        return new ResponseEntity<>( cotizacion, HttpStatus.OK)  ;
	 }
	 @RequestMapping(value = "/Vehiculo/{id}", method = RequestMethod.GET)
	    public ResponseEntity<Vehiculo> getVehiculo(@PathVariable("id") String id) throws VehiculoException {	
	        Vehiculo vehiculo = this.vigilante.finVehiculoByPlaca(id);	        
	        return new ResponseEntity<>( vehiculo, HttpStatus.OK)  ;
	 }
	
}
