package edu.udec.excepcion;

import java.time.LocalTime;

public class ErrorWrapper {
	
	/*
	 * atributo para fecha
	 */
	private LocalTime timestamp;
	
	/*
	 * atributo estado error
	 */
	private int status;
	
	/*
	 * atrbuto para error
	 */
	private String error;
	
	/*
	 * atriuto para el mesaje del error
	 */
	private String message;
	
	/*
	 * atributo para la causa del error
	 */
	private String trace;
	
	/*
	 * atributo para el path de error
	 */
	private String path;

	public ErrorWrapper() {
		super();
	}

	public ErrorWrapper(int status, String error, String message, String trace, String path) {
		super();
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.trace = trace;
		this.path = path;
	}

	public LocalTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
