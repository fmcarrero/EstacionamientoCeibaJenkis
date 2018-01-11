package co.com.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.domain.Vigilante;
import co.com.repository.IEstacionamientoRepository;
import co.com.repository.IFacturaRepository;
import co.com.repository.IVehiculoRepository;


@Configuration
public class DomainConfig {

	
	@Bean
	public Vigilante createVigilante(IVehiculoRepository vehiculoRepository,IEstacionamientoRepository estacionamientoRepository
									 ,IFacturaRepository facturaRepository){
		return new Vigilante(vehiculoRepository,estacionamientoRepository,facturaRepository);
	}
}
