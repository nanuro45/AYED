package tp04.ejercicio1;

import tp02.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ColaGenerica;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	public ListaEnlazadaGenerica<T> preOrden() {
		ListaEnlazadaGenerica<T> list = new ListaEnlazadaGenerica<T>();
		this.preOrden(list);
		return list;
	}

	public void preOrden(ListaGenerica<T> l){
		l.agregarFinal(this.getDato());
		ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
		lhijos.comenzar();
		while(!lhijos.fin()){
			(lhijos.proximo()).preOrden(l);
		} 
	}

	public ListaEnlazadaGenerica<T> postOrden(){
		ListaEnlazadaGenerica<T> list = new ListaEnlazadaGenerica<T>();
		this.postOrden(list);
		return list;
	}

	public void postOrden(ListaGenerica<T>list){
		ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
		list.agregarFinal(this.getDato());
		lhijos.comenzar();
		while(!lhijos.fin()){
			(lhijos.proximo()).postOrden(list);
		}

	}

	public ListaGenerica<T> inOrden(){
		ListaEnlazadaGenerica<T> list = new ListaEnlazadaGenerica<T>();
		this.inOrden(list);
		return list;
	}
	public void inOrden(ListaGenerica<T> list){
		ListaGenerica<ArbolGeneral<T>> lhijos= this.getHijos();
		list.agregarFinal(this.getDato());
		lhijos.comenzar();
		while(!lhijos.fin()){
			(lhijos.proximo()).inOrden(list);
		}
		
	}

	public ListaGenerica<T> porNiveles(ArbolGeneral<T> arbol){
		ListaGenerica<T> result = new ListaEnlazadaGenerica<T>();
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T>arbol_aux;
		while(!cola.esVacia()){
			arbol_aux = cola.desencolar();
			result.agregarFinal(arbol_aux.getDato());
			if(arbol_aux.tieneHijos()){
				ListaGenerica<ArbolGeneral<T>> hijos = arbol_aux.getHijos();
				hijos.comenzar();
				while(!hijos.fin()){
					cola.encolar(hijos.proximo());
				}
			}
		}
		return result;
	}
	
	public ListaGenerica<T> numerosImparesMayoresQuePreOrden(Integer n){
		ListaEnlazadaGenerica<T> listImpares = new ListaEnlazadaGenerica<T>();
		this.preOrdenImpares(listImpares,n);
		return listImpares;
	}
	public void preOrdenImpares(ListaGenerica<T>list,Integer n){
		Integer m = (Integer)getDato();
		if( m > n && m % 2 != 0){
			list.agregarFinal((T)m);			
		}
		ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
		lhijos.comenzar();
		while(!lhijos.fin()){
			(lhijos.proximo()).preOrdenImpares(list,n);
		}
	}
	
	public ListaGenerica<T> numerosImparesMayoresEnInOrden(Integer n){
		ListaEnlazadaGenerica<T> list = new ListaEnlazadaGenerica<T>();
		this.InOrdenImpares(list, n);
		return list;
	}

	public void InOrdenImpares(ListaGenerica<T> list, Integer n){
		Integer m = (Integer)getDato();
		ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
		lhijos.comenzar();
		lhijos.proximo();
		while(!lhijos.fin()){
			(lhijos.proximo()).InOrdenImpares(list,n);
		}
		if((Integer)lhijos.proximo().getDato() > n && (Integer)lhijos.proximo().getDato() % 2 != 0){
			list.agregarFinal(lhijos.proximo().getDato());
		}
		if(m > n && m %2 !=0){
			list.agregarFinal((T)m);
		}
	}

	public ListaGenerica<T> numerosImparesMayoresPostOrden(Integer n){
		ListaEnlazadaGenerica<T> listImpares = new ListaEnlazadaGenerica<T>();
		this.numerosImparesPostOrden(listImpares,n); 
		return null;

	}
	public void numerosImparesPostOrden(ListaGenerica<T> list, Integer n){
		ListaGenerica<ArbolGeneral<T>> lhijos = this.getHijos();
		lhijos.comenzar();
		Integer i = (Integer)lhijos.proximo().getDato();
		if(i > n && i % 2 != 0){
			list.agregarFinal(lhijos.proximo().getDato());
		}
		Integer m = (Integer)getDato();
		if(m > n && m%2 != 0){
			list.agregarFinal((T)m);
		}
		while(!lhijos.esVacia()){
			(lhijos.proximo()).numerosImparesPostOrden(list,n);
		}
	}

	public ListaGenerica<T> porNivelesImparesMayores(Integer n){
		ListaGenerica<T> result = new ListaEnlazadaGenerica<T>();
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T>arbol_aux;
		while(!cola.esVacia()){
			arbol_aux = cola.desencolar();
			if((Integer) arbol_aux.getDato() > n && (Integer)arbol_aux.getDato() %2 != 0){
				result.agregarFinal(arbol_aux.getDato());
			}
			if(arbol_aux.tieneHijos()){
				ListaGenerica<ArbolGeneral<T>> hijos = arbol_aux.getHijos();
				hijos.comenzar();
				if((Integer)hijos.proximo().getDato()>n && (Integer)hijos.proximo().getDato() % 2 !=0){
					result.agregarFinal(hijos.proximo().getDato());
				}
				while(!hijos.fin()){
					cola.encolar(hijos.proximo());
				}
			}
		}
		return result;
	}
	public Integer altura() {
		//this.recorridoPr(0);
		return this.recorridoPr();
	}

	private Integer recorridoPr(){
		if(this.esHoja()){
			return 0;
		}
		else{
			Integer max = -1;
			ListaGenerica<ArbolGeneral<T>>lhijos = this.getHijos();
			lhijos.comenzar();
			while(!lhijos.fin()){
				Integer j = 1;
				j += lhijos.proximo().recorridoPr();	
				if(max < j){
					max = j;
				}
			}
			return max;
		}

	}

	public Integer nivel(T dato) {
		// falta implementar
		return -1;
	}

	public Integer ancho() {
		// Falta implementar..
		return 0;
	}

}
