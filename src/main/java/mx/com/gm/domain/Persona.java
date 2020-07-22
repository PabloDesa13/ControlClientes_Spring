package mx.com.gm.domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data // Ayuda a tener acceso a los getters y setters de los atributos sin declararlos
@Entity // Convierte la clase a entidad
@Table(name = "persona") // Nombre de la tabla de BD
public class Persona implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id //indica la llave primaria de la clase entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Forma en la que se genera la llave primaria
	private Long idPersona;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;
	
	private String telefono;
	
	@NotNull
	private Double saldo;
}
