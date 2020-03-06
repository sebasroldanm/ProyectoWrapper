package edu.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.udec.entity.Persona;
import edu.udec.excepcion.NotFoundModelException;
import edu.udec.service.interfaces.IPersonaService;

/**
 * funcion implemetna interface y mapeo
 * @author Sebastian
 *
 */
@RestController
@RequestMapping("/personas")
public class PersonaController {

		@Autowired
		IPersonaService iPersonaService;
		
		/**
		 * Mapeo y metodo de istar personas, devuelve json y estado de peticion
		 * @return
		 */
		@GetMapping("/listar")
		public ResponseEntity<List<Persona>> listarPersonas() {
			List<Persona> listaPersona = iPersonaService.listarPersonas();
			return new ResponseEntity<List<Persona>>(listaPersona, HttpStatus.OK);
		}
		
		/**
		 * Mapeo y metodo de listar persona por cedula, devuelve json y estado de peticion
		 * @param cedula
		 * @return
		 */
		@GetMapping("/listarCedula/{cedula}")
		public ResponseEntity<Persona> listarPorCedula(@PathVariable long cedula){
			Persona persona = iPersonaService.listarPersonaPorCedula(cedula);
			if(persona.getNombre() == null) {
				throw new NotFoundModelException("Persona no encontrada");
			}
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		}
		
		/**
		 * Mapeo y metodo de listar persona por nombre, devuelve json y estado de peticion
		 * @param nombre
		 * @return
		 */
		@GetMapping("/listarNombre/{nombre}")
		public ResponseEntity<Persona> listarPorNombre(@PathVariable String nombre){
			Persona persona = iPersonaService.listarPersonaPoNombre(nombre);
			if(persona.getNombre() == null) {
				throw new NotFoundModelException("Persona no encontrada");
			}
			return new ResponseEntity<Persona>(persona, HttpStatus.OK);
		}
		
		/**
		 * Mapeo y metodo de guardar persona, devuelve json (persona nueva) y estado de peticion
		 * @param persona
		 * @return
		 */
		@PostMapping("/agregar")
		public ResponseEntity<Persona> guardar(@Valid @RequestBody Persona persona) {
			Persona personaCreate = iPersonaService.guardar(persona);
			System.out.print(persona.getCedula());
			if(persona.getNombre() == null) {
				throw new NotFoundModelException("Esta Cedula ya esta registrada");
			}
			return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
		}
		
		/**
		 * Mapeo y metodo de editar persona por cedula, devuelve json (persona editada) y estado de peticion
		 * @param persona
		 * @return
		 */
		@PutMapping("/editar")
		public ResponseEntity<Persona> editar(@Valid @RequestBody Persona persona){
			Persona personaUpdate = iPersonaService.editar(persona);
			return new ResponseEntity<Persona>(personaUpdate, HttpStatus.ACCEPTED);
		}
		
		/**
		 * Mapeo y metodo de eliminar persona por cedula, devuelve estado de peticion (204)	
		 * @param cedula
		 * @return
		 */
		@DeleteMapping("/eliminar/{cedula}")
		public ResponseEntity<Void> eliminar(@PathVariable long cedula){
			iPersonaService.eliminar(cedula);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
}
