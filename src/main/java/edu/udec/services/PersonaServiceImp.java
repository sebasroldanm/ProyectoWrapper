package edu.udec.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.udec.entity.Persona;
import edu.udec.excepcion.NotFoundModelException;
import edu.udec.service.interfaces.IPersonaService;

/**
 * funcion donde se ejecuta el servico
 * @author Sebastian
 *
 */
@Service
public class PersonaServiceImp implements IPersonaService{
	
	/**
	 * Crear lista en memoria
	 */
//	List<Persona> personas = new ArrayList<Persona>();
	
	/**
	 * Metodo para llamar los primeros datos al levantar el proyecto
	 */
//	public PersonaServiceImp() {
//		llenarLista();
//	}
	
	/**
	 * Metodo para llenar a lista
	 */
//	public void llenarLista() {
//		personas.add(new Persona("Juan", 1070984090, "mail@app.com", 18, "Av Simpre Viva", "Soltero"));
//		personas.add(new Persona("Camilo", 123456, "camilo@app.com", 22, "Mi direccion", "Casado"));
//	}
	
	/**
	 * Lista todas las personas, retorna objeto persona
	 */
	@Override
	public List<Persona> listarPersonas() {
		List<Persona> personas = obtenerArchivo();
		return personas;
	}

	/**
	 * Lista persona por cedula, devueve objeto persona ,necesita parametro long
	 */
	@Override
	public Persona listarPersonaPorCedula(long cedula){
		List<Persona> personas = obtenerArchivo();
		Persona persona = new Persona();
		for (int i = 0; i < personas.size(); i++) {
//			i = i / 0;
			if (personas.get(i).getCedula() == cedula) {
				persona = personas.get(i);
				break;
			}
		}
		return persona;
	}

	/**
	 * lista persona por nombre, devuelve objeto persona y necesita parametro de string
	 */
	@Override
	public Persona listarPersonaPoNombre(String nombre) {
		List<Persona> personas = obtenerArchivo();
		Persona persona = new Persona();
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				persona = personas.get(i);
				break;
			}
		}
		return persona;
	}

	/**
	 * Guarda perosna, requiere objeto persona json y devuelve el mismo objeto
	 */
	@Override
	public Persona guardar(Persona persona) {
		List<Persona> personas = obtenerArchivo();
		for (int i = 0; i < personas.size(); i++) {
			
			if (personas.get(i).getCedula() != persona.getCedula()) {
				escribirArchivo(persona);
				return persona;
			}
			else {
				
				break;
			}
		}
		return null;
	}

	/**
	 * Edita la persona, requiere objeto persona y edita por cedula
	 */
	@Override
	public Persona editar(Persona persona) {
		List<Persona> personas = obtenerArchivo();
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getCedula() == persona.getCedula()) {
				personas.remove(personas.get(i));
				personas.add(persona);
			}
		}
		editarEliminarArchivo(personas);
		return persona;
	}

	/**
	 * Elimina objeto persona, requiere parametro cedula long
	 */
	@Override
	public void eliminar(long cedula) {
		List<Persona> personas = obtenerArchivo();
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getCedula() == cedula) {
				personas.remove(personas.get(i));
			}
			else {
				throw new NotFoundModelException("Usurio no existe");
			}
		}
		editarEliminarArchivo(personas);
	}
	
	/*
	 * Metodo para leer archivo
	 * @return List<Persona>
	 */
	public List<Persona> obtenerArchivo() {
		
		List<Persona> personas = new ArrayList<Persona>();
		BufferedReader contenido;
		String texto = "";
		String resultado[] = null;
		try {
			
			File file = new File("C:\\Users\\Sebastian\\Documents\\workspace-sts-3.9.11.RELEASE\\Proyecto2\\src\\main\\resources\\static\\datos.txt");

			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileReader reader = new FileReader(file);
			
			contenido = new BufferedReader(reader);

			while((texto = contenido.readLine()) != null) {
				resultado = texto.split(",");
				Persona persona = new Persona();
				persona.setCedula(Integer.parseInt(resultado[0]));
				persona.setNombre(resultado[1]);
				persona.setEdad(Integer.parseInt(resultado[2]));
				persona.setEstadoCivil(resultado[3]);
				persona.setDireccion(resultado[4]);
				persona.setEmail(resultado[5]);
				personas.add(persona);
			} 
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Error al leer archivo txt.");
		}
		return personas;
	}
	
	/**
	 * Metodo que devuelve un String con los datos del archivo txt
	 * @return String
	 */
	public String obtenerStringArchivo() {
		
		String texto = "";
		String resultado = "";
		BufferedReader contenido;
		try {
			FileReader file = new FileReader("C:\\Users\\Sebastian\\Documents\\workspace-sts-3.9.11.RELEASE\\Proyecto2\\src\\main\\resources\\static\\datos.txt");
			
			contenido = new BufferedReader(file);

			while((texto = contenido.readLine()) != null) {
				resultado = resultado + texto  + "\n";
				System.out.println(resultado);
			}
		}catch(Exception e) {
			System.out.println("Error al leer archivo txt.");
		}
		return resultado;
	}
	
	/**
	 * Agrega persona al final del objeto PErsona 
	 * @param persona
	 * @return Persona
	 */
	public Persona escribirArchivo(Persona persona) {
		
		String personaString = obtenerStringArchivo() + persona.getCedula() + "," + persona.getNombre() + "," 
		+ persona.getEdad() + "," + persona.getEstadoCivil() + "," + persona.getDireccion() + "," 
		+ persona.getEmail() + "\n";
		
		try {
			File archivo = new File("C:\\Users\\Sebastian\\Documents\\workspace-sts-3.9.11.RELEASE\\Proyecto2\\src\\main\\resources\\static\\datos.txt");
	
			FileWriter escribir = new FileWriter(archivo);
			escribir.write(personaString);
			escribir.close();
		}
		catch(Exception e) {
			System.out.println("Error al escribir en el archivo" + e.getMessage());
		}
		return persona;
	}
	
	
	/**
	 * Metodo que elimina o edita a Persona de un archivo txt
	 * @param personas
	 */
	public void editarEliminarArchivo(List<Persona> personas) {
		
		String personaString = "";
		for (int i = 0; i < personas.size(); i++) {
			personaString = personaString + personas.get(i).getCedula() + "," + personas.get(i).getNombre() + "," + personas.get(i).getEdad() + ","
					+ personas.get(i).getEstadoCivil() + "," + personas.get(i).getDireccion() + "," + personas.get(i).getEmail() + "\n";
			
		}
		
		try {
			File archivo = new File("C:\\Users\\Sebastian\\Documents\\workspace-sts-3.9.11.RELEASE\\Proyecto2\\src\\main\\resources\\static\\datos.txt");
	
			FileWriter escribir = new FileWriter(archivo);
			escribir.write(personaString);
			escribir.close();
		} catch(Exception e) {
			System.out.println("Error al escribir en el archivo" + e.getMessage());
		}
	}
	

}
