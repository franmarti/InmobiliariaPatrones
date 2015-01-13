package logica;

import java.util.*;

public class Asesor {

	//atributos
	private String codigo_emp;
	private String nombre;
	private String apellidos;
	private ArrayList <Visita> visitas;
	private Inmobiliaria inmobiliaria;

	//metodos
	
	public Asesor(String cod, String nom, String apellidos){
		this.codigo_emp = cod;
		this.nombre = nom;
		this.apellidos = apellidos;
	}
	
	public void agregarVisita(Visita visita){
		visitas.add(visita);
	}
	
	public void borrarVisita(Visita visita){
		visitas.remove(visita);
	}
	
	public Visita buscarVisita(String fecha){
		for(int i=0;i<visitas.size();i++){
			if(visitas.get(i).getFecha() == fecha){
				return visitas.get(i);
			}
		}
		return null;
	}
	
	public String getCodigo_emp(){
		return codigo_emp;
	}

	public void setCodigo_emp(String codigo_emp) {
		this.codigo_emp = codigo_emp;
	}

	public Inmobiliaria getInmobiliaria() {
		return inmobiliaria;
	}

	public void setInmobiliaria(Inmobiliaria inmobiliaria) {
		this.inmobiliaria = inmobiliaria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String toString(){
		return this.getCodigo_emp()+"-"+this.getNombre() + " " + this.getApellidos();
	}
	
	
}
