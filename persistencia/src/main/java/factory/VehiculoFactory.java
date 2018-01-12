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
		private static final String VEHICULONOPERMITIDO="Vehiculo no permitido";
	
	   public Vehiculo getVehiculo(VehiculoEntity vehiculo) throws VehiculoException{
		  String classObject = vehiculo.getClass().getSimpleName().toUpperCase().replaceAll("ENTITY", "");
		  EstacionamientoEntity entityEstacionamiento = vehiculo.getEstacionamiento();
		  Estacionamiento estacionamiento =new Estacionamiento(entityEstacionamiento.getPlaca(),entityEstacionamiento.getFechaHoraInicio(),entityEstacionamiento.getObservacion() );
	      Vehiculo vehiculo2;
		  if(classObject.equals(TipoVehiculoEnum.CARRO.toString())){
			  vehiculo2 =new Carro(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());	    	  
			      	         
	      } else if(classObject.equals(TipoVehiculoEnum.MOTO.toString())){
	    	  vehiculo2= new Moto(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	    	  	         	         
	      }else{    
	    	  throw new VehiculoException(VEHICULONOPERMITIDO);
	      }
		  vehiculo2.setEstacionamiento(estacionamiento);
		  return vehiculo2;
	   }

	@Override
	public VehiculoEntity getVehiculoEntity(Vehiculo vehiculo) throws VehiculoException {
		
	
	      if(vehiculo.getIdTipoVehiculo() ==TipoVehiculoEnum.CARRO.getValue()){
	         return new CarroEntity(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	         
	      } else if(vehiculo.getIdTipoVehiculo() ==TipoVehiculoEnum.MOTO.getValue()){
	         return new MotoEntity(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());
	         
	      }	      
	      throw new VehiculoException(VEHICULONOPERMITIDO);
	}

	@Override
	public Vehiculo getVehiculoWithOUTEstacionamiento(VehiculoEntity vehiculo) throws VehiculoException {
		String classObject = vehiculo.getClass().getSimpleName().toUpperCase().replaceAll("ENTITY", "");
		if(classObject.equals(TipoVehiculoEnum.CARRO.toString())){
	    	 return new Carro(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());	        
	         
	      } else if(classObject.equals(TipoVehiculoEnum.MOTO.toString())){
	    	  return new Moto(vehiculo.getPlaca(),vehiculo.getColor(),vehiculo.getCilindraje());	    		         
	      }	      
	      throw new VehiculoException(VEHICULONOPERMITIDO);
	}
	
}
