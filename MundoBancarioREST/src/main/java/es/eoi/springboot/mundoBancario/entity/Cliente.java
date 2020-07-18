package es.eoi.springboot.mundoBancario.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "CLIENTES")
public class Cliente implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column (name = "DNI", unique = true)
	private String dni;
	
	@Column (name = "NOMBRE")
	private String nombre;
	
	@Column (name = "DIRECCION")
	private String direccion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Cuenta> cuentas;

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + ", cuentas=" + cuentas + "]";
	}


	
}
