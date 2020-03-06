package edu.udec.service.interfaces;

import java.util.List;

import edu.udec.entity.Persona;

/**
 * Interfaz que contiene las operaciones con controller
 * @author Sebastian
 *
 */
public interface IPersonaService {
	
	/**
	 * función que retorna el listado de personas
	 * @return List<Persona>
	 */
	public List<Persona> listarPersonas();
	
	/**
	 * función que lista persona por cédula
	 * @param cedula
	 * @return persona
	 */
	public Persona listarPersonaPorCedula(long cedula);
	
	/**
	 * función que lista persona por nombre
	 * @param nombre
	 * @return persona
	 */
	public Persona listarPersonaPoNombre(String nombre);
	
	/**
	 * funcion que guarda persona
	 * @param persona
	 * @return persona (nueva)
	 */
	public Persona guardar(Persona persona);
	
	/**
	 * función que edita persona por cedula
	 * @param persona
	 * @return persona (actualizado)
	 */
	public Persona editar(Persona persona);
	
	/**
	 * funcion que elimina persona por cedula
	 * @param cedula
	 */
	public void eliminar(long cedula);
}
