package factory;
import org.enumvehiculos.TipoVehiculoEnum;
import org.excepciones.VehiculoException;
import org.springframework.stereotype.Service;

import co.com.domain.Carro;
import co.com.domain.Estacionamiento;
import co.com.domain.Moto;
import co.com.domain.Vehiculo;
import co.com.persistencia.entity.CarroEntity;
import co.com.persistencia.entity.EstacionamientoEntity;
import co.com.persistencia.entity.MotoEntity;
import co.com.persistencia.entity.VehiculoEntity;


@Service
public class VehiculoFactory implements IVehiculoFactory{
	
	   public Vehiculo getVehiculo(VehiculoEntity vehiculo) throws VehiculoException{
		  String classObject = vehiculo.getClass().getSimpleName().toString().toUpperCase().replaceAll("ENTITY", "");
		  EstacionamientoEntity entityEstacionamiento = vehiculo.getEstacionamiento();
		  Estacionamiento estacionamiento =new Estacionamiento(entityEstacionamiento.getPlaca(),entityEstacionamiento.getFechaHoraInicio(),entityEstacionamiento.getObservacion() );
	      if(classObject.equals(TipoVehiculoEnum.CARRO.toString())){
	    	  Carro carro = new Carro(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());	    	  
	          carro.setEstacionamiento(estacionamiento);
	    	  return carro;
	         
	      } else if(classObject.equals(TipoVehiculoEnum.MOTO.toString())){
	    	  Moto moto = new Moto(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	    	  moto.setEstacionamiento(estacionamiento);
	         return moto;
	         
	      }	      
	      throw new VehiculoException("Vehiculo no permitido");
	   }

	@Override
	public VehiculoEntity getVehiculoEntity(Vehiculo vehiculo) throws VehiculoException {
		
	
	      if(vehiculo.getIdTipoVehiculo() ==TipoVehiculoEnum.CARRO.getValue()){
	         return new CarroEntity(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	         
	      } else if(vehiculo.getIdTipoVehiculo() ==TipoVehiculoEnum.MOTO.getValue()){
	         return new MotoEntity(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	         
	      }	      
	      throw new VehiculoException("Vehiculo no permitido");
	}

	@Override
	public Vehiculo getVehiculoWithOUTEstacionamiento(VehiculoEntity vehiculo) throws VehiculoException {
		String classObject = vehiculo.getClass().getSimpleName().toString().toUpperCase().replaceAll("ENTITY", "");
		if(classObject.equals(TipoVehiculoEnum.CARRO.toString())){
	    	  Carro carro = new Carro(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());	    	  
	        	return carro;
	         
	      } else if(classObject.equals(TipoVehiculoEnum.MOTO.toString())){
	    	  Moto moto = new Moto(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	    	  return moto;
	         
	      }	      
	      throw new VehiculoException("Vehiculo no permitido");
	}
}
