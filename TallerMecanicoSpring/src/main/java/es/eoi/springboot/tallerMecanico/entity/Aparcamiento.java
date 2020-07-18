package es.eoi.springboot.tallerMecanico.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "aparcamientos")
public class Aparcamiento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique= true)
	private Long id;
	
	@Column(name = "FILA")
	private int fila;
	
	@Column(name = "COLUMNA")
	private String columna;
	
	@OneToOne (fetch = FetchType.LAZY, mappedBy = "aparcamiento", cascade = CascadeType.ALL)
	private Mecanico mecanico;

	@Override
	public String toString() {
		return "Aparcamiento [id=" + id + ", fila=" + fila + ", columna=" + columna + ", mecanico=" + mecanico + "]";
	}
	
}
