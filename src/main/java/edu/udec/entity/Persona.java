package edu.udec.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
//import javax.validation.constraints.Size;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Clase que contiene atributos de persona
 * @author Sebastian
 *
 */
@ApiModel( description  = "Clase contenedora de los atributos comunes de una persona.")
public class Persona {

	@ApiModelProperty(notes = "Nombre del persona, entre 3 y 30 caracteres")
	@Size(min = 3,max = 30, message = "Nombre debe tener al menos 3 caracteres")
	private String nombre;
	
	@ApiModelProperty(notes = "Cedula de persona, mayor de 99999")
	@Min(value = 99999, message = "Digitar un valor válido")
	private long cedula;
	
	@ApiModelProperty(notes = "Correo de persona")
	@Email(message = "Digitar un email válido")
	private String email;
	
	@ApiModelProperty(notes = "Edad de persona, entre 18 y 60 años")
	@Min(value = 18, message = "Debe tener 18 años como mínimo")
	@Max(value = 60, message = "La edad no debe sobrepasar de años")
	private int edad;

	@ApiModelProperty(notes = "Dirección de persona")
	@Size(min = 5, max = 50, message = "La direccion debe ser completa, minimo 5 caracteres, entre 5 a 50 caracteres")
	private String direccion;
	
	@ApiModelProperty(notes = "Estado Civil de persona, entre 3 y 20 caracteres")
	@Size(min = 3, max = 20, message = "Estado Civil debe tener almenos 3 caracteres")
	private String estadoCivil;
	
	
	public Persona() {

	}

	public Persona(String nombre, long cedula, String email, int edad, String direccion, String estadoCivil) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.email = email;
		this.edad = edad;
		this.direccion = direccion;
		this.estadoCivil = estadoCivil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

}
