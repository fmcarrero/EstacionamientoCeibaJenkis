package co.com.builder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.dto.SalidaVehiculoDto;



public class SalidaVehiculoDtoTestDataBuilder {
	 private String placa;
	 private Timestamp fechaHoraFin;
	 
	 LocalDateTime hoy =LocalDateTime.of(2018, 1, 2,0,0);
	
	 
	 public SalidaVehiculoDtoTestDataBuilder() {
		 this.placa = "AXF-654";
		 this.fechaHoraFin = Timestamp.valueOf(hoy.withDayOfMonth(5));
	 }
	 public SalidaVehiculoDtoTestDataBuilder withFechaHoraFin(Timestamp fechahorafin) {
		 this.fechaHoraFin= fechahorafin;
		 return this;
	 }
	 public SalidaVehiculoDtoTestDataBuilder withPlaca(String placa) {
		 this.placa= placa;
		 return this;
	 }
	 public SalidaVehiculoDto build() {
		 return new SalidaVehiculoDto(this.placa,this.fechaHoraFin);
	 }
}
