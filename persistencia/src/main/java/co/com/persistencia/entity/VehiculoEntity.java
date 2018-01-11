package co.com.persistencia.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;




@SuppressWarnings("serial")
@Entity
@Table(name="Vehiculo")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE )
public abstract class VehiculoEntity implements Serializable  {	
	
	 @Id
	 @Column(name="Placa", nullable=false, unique = true)
	 private String placa;
	 
	 @Column(name="Color", nullable=false)
	 private String color;
	 
	 @Column(name="Cilindraje", nullable=false)
	 private long cilindraje;
	 
	
	 @OneToOne(targetEntity= EstacionamientoEntity.class, fetch=FetchType.EAGER , orphanRemoval=true)	
	 @PrimaryKeyJoinColumn		
	 protected EstacionamientoEntity estacionamiento;
	 
	 protected VehiculoEntity(){}
	 
	 public VehiculoEntity(String placa, String color, long cilindraje){
		 this.placa= placa;
		 this.color=color;
		 this.cilindraje = cilindraje;
	 }
	public EstacionamientoEntity getEstacionamiento(){ return this.estacionamiento;}
	
	public void setEstacionamiento (EstacionamientoEntity estacionamiento) {
		 this.estacionamiento = estacionamiento;
	}
	public String getPlaca() {
		return placa;
	}

	public String getColor() {
		return color;
	}

	public long getCilindraje() {
		return cilindraje;
	}	 
	 
}
