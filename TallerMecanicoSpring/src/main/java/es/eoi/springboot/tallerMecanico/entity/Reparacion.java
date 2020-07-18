package es.eoi.springboot.tallerMecanico.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reparaciones")
public class Reparacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "PRECIO_REP")
	private Double precioReparacion;
	
	@Column(name = "FECHA_REP")
	private Date fechaReparacion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name= "mecanico_id", referencedColumnName = "DNI")
	private Mecanico mecanico;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name= "vehiculo_matricula", referencedColumnName = "MATRICULA")
	private Vehiculo vehiculo;

	@Override
	public String toString() {
		return "Reparacion [id=" + id + ", precioReparacion=" + precioReparacion + ", fechaReparacion="
				+ fechaReparacion + ", mecanico=" + mecanico + ", vehiculo=" + vehiculo + "]";
	}
	
}
