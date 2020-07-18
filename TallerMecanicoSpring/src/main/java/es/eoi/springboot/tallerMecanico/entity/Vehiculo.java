package es.eoi.springboot.tallerMecanico.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "vehiculos")
public class Vehiculo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MATRICULA", unique = true)
	private String matricula;
	
	@Column(name = "MARCA")
	private String marca;
	
	@Column(name = "MODELO")
	private String modelo;
	
	@OneToMany(mappedBy = "vehiculo")
	private List<Reparacion> reparaciones;

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", reparaciones="
				+ reparaciones + "]";
	}
	
}
