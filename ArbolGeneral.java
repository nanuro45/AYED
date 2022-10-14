package ar.edu.unlp.info.oo1.ArbolesB;

import ar.edu.unlp.info.oo1.ArbolesB.tp2.ColaGenerica;
import ar.edu.unlp.info.oo1.ArbolesB.tp2.ListaEnlazadaGenerica;
import ar.edu.unlp.info.oo1.ArbolesB.tp2.ListaGenerica;

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
	
	public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden(ArbolGeneral<Integer> a, Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		numerosImparesPreOrden(a,n,lista);
		return null;
	}
	
	public ListaGenerica<Integer> numerosImparesMayoresQueInOrden(ArbolGeneral<Integer> a, Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer> ();
		numerosImparesInOrden(a,n,lista);
		return lista;
	}
	
	public ListaGenerica<Integer> numerosMayoresQuePostOrden(ArbolGeneral<Integer>a ,Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		numerosImparesPostOrden(a,n,lista);
		return lista;
	}
	private void numerosImparesPostOrden(ArbolGeneral<Integer> a, Integer n, ListaGenerica<Integer> l) {
		ListaGenerica<ArbolGeneral<Integer>> aux = a.getHijos();
		aux.comenzar();
		Integer x = aux.proximo().getDato();
		if(x > n && x % 2 != 0 ) {
			l.agregarFinal(x);
		}
		if (a.getDato() > n && a.getDato() % 2 != 0) {
			l.agregarFinal(a.getDato());
		}
		while(!aux.fin()) {
				numerosImparesPostOrden(aux.proximo(),n,l);
		}
	}
	private void numerosImparesPreOrden(ArbolGeneral<Integer>a, Integer n, ListaGenerica<Integer> l) {
		if(a.getDato() > n && a.getDato() % 2 != 0) {
			l.agregarFinal(a.getDato());
		}
		ListaGenerica<ArbolGeneral<Integer>> aux = a.getHijos();
		aux.comenzar();
		while(!aux.fin()) {
			numerosImparesPreOrden(aux.proximo(),n,l);
		}
	}
	public int nivel(T dato) {
		ColaGenerica<ArbolGeneral> cola = new ColaGenerica<ArbolGeneral>();
		ArbolGeneral<Integer> aux = null;
		int nivel = 0;
		boolean encontro = true;
		cola.encolar(this);
		cola.encolar(null);
		while(!cola.esVacio() && encontro) {
			aux = cola.desencolar();
			if(aux != null) {
				if(aux.getDato() == dato) {
					encontro = false;
				}
				else {
					nivel++;
					ListaGenerica<ArbolGeneral<Integer>> lhijos = aux.getHijos();
					lhijos.comenzar();
					while(!lhijos.fin()) {
						cola.encolar(lhijos.proximo());
					}
				}
			}
		}
		return nivel;
	}
	private void numerosImparesInOrden(ArbolGeneral<Integer>a, Integer n, ListaGenerica<Integer> l) {
		if(a.esHoja()) {
			if(a.getDato() > n && a.getDato() % 2 != 0) {
				l.agregarFinal(a.getDato());
			}
		}
		else {
			if(a.getDato() > n && a.getDato() % 2 != 0) {
				l.agregarFinal(a.getDato());
			}
			ListaGenerica<ArbolGeneral<Integer>> aux =  a.getHijos();
			aux.comenzar();
			while(!aux.fin()) {
				numerosImparesInOrden(aux.proximo(), n, l);
			}
		}
	}
	public int devolverMaximoPromedio(ArbolGeneral<T> arbol) {
		int devolucion = 0;
		int nodo = 0;
		int cont = 0;
		int nivel = 0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T> aux = null;
		cola.encolar(arbol);
		cola.encolar(null);
		while(!cola.esVacio()) {
			aux = cola.desencolar();
			if(aux != null) {
				cont = (int)aux.getDato();
				nodo++;
				ListaGenerica<ArbolGeneral<T>> lhijos = aux.getHijos();
				lhijos.comenzar();
				while(!lhijos.fin()) {
					cola.encolar(lhijos.proximo());
				}
			}
			else {
				if(!cola.esVacio()) {
					int max = cont / nodo;
					if(max > devolucion) {
						devolucion = max;
					}
					nivel++;
					cola.encolar(null);
				}
			}
		}
		return devolucion;
	}
	public boolean esAncestro(T a, T b) {
		ListaGenerica<ArbolGeneral<T>> aux = buscarA(this, a);
		return true;
	}
	private ListaGenerica<ArbolGeneral<T>> buscarA(ArbolGeneral<T> a, T aux) {
		if(aux == a.getDato()) {
			return a.getHijos();
		}
		else {
			ListaGenerica<ArbolGeneral<T>> lhijos = a.getHijos();
			lhijos.comenzar();
			while(!lhijos.fin()) {
				buscarA(lhijos.proximo(),(T)a);
			}
		}
		return null;
	}
	private boolean buscarB(ListaGenerica<ArbolGeneral<T>> x , T b) {
		x.comenzar();
		while(!x.fin()) {
			if(x.proximo() == b) {
				return true;
			}
			x.proximo();
		
		}
		return false;
	}
	
	public ListaGenerica<Integer> resolver(){
		ListaGenerica<Integer> devolver = new ListaEnlazadaGenerica<Integer>();
		resolverPrivado(devolver,this);
		return devolver;
	}
	private void resolverPrivado(ListaGenerica<Integer> d,ArbolGeneral<T> a) {
		Integer sum=0;
		if(!a.esHoja()) {
			ListaGenerica<ArbolGeneral<T>> lhijos = a.getHijos();
			lhijos.comenzar();
			while(!lhijos.fin()) {
				resolverPrivado(d, lhijos.proximo());
				sum+=(Integer) lhijos.proximo().getDato();
			}
			if(lhijos.tamanio() % 2 == 1){
				d.agregarFinal(sum);
			}
		}
	}
	public ListaGenerica<ArbolGeneral<T>> resolver(ArbolGeneral<T> arbol){
		ListaGenerica<ArbolGeneral<T>> devolver = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		resolverImpares(arbol,devolver);
		return devolver;
	}
	private void resolverImpares(ArbolGeneral<T> arbol, ListaGenerica<ArbolGeneral<T>>l) {
		if(!arbol.esHoja()) {
			ListaGenerica<ArbolGeneral<T>> lhijos = arbol.getHijos();
			lhijos.comenzar();
			if(!lhijos.esVacia()) {
				resolverImpares(lhijos.proximo(),l);
			}
			if(lhijos.tamanio()%2 ==0) {
				l.agregarFinal((ArbolGeneral<T>) lhijos.proximo().getDato());
			}
			while(!lhijos.fin()) {
				resolverImpares(lhijos.proximo(),l);
			}
		}
	}
	
	
	
	
}
