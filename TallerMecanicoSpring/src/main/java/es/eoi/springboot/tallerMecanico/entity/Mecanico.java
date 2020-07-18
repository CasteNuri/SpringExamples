package es.eoi.springboot.tallerMecanico.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "mecanicos")
public class Mecanico implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DNI", unique = true)
	private String dni;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "CIUDAD")
	private String ciudad;
	
	@Column(name = "SALARIO")
	private Double salario;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parking_id", referencedColumnName = "ID")
	private Aparcamiento aparcamiento;
	
	@OneToMany(mappedBy = "mecanico")
	private List<Reparacion> reparaciones;

	@Override
	public String toString() {
		return "Mecanico [dni=" + dni + ", nombre=" + nombre + ", ciudad=" + ciudad + ", salario=" + salario
				+ ", aparcamiento=" + aparcamiento + ", reparaciones=" + reparaciones + "]";
	}
	
}
