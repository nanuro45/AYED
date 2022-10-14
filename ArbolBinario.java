package ar.edu.unlp.info.oo1.ArbolesB;

import ar.edu.unlp.info.oo1.ArbolesB.tp2.ColaGenerica;
import ar.edu.unlp.info.oo1.ArbolesB.tp2.ListaEnlazadaGenerica;
import ar.edu.unlp.info.oo1.ArbolesB.tp2.ListaGenerica;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;   
	private ArbolBinario<T> hijoDerecho; 

	
	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	public int contarHojas() {
		int contarhoja = 0;
		contarHojas(this);
		return contarhoja;
	}
	
	private int contarHojas(ArbolBinario<T> ab) {
		if(this.esHoja()) {
			return 1;
		}else {
			int hi = 0;
			int hd =0;
			if(this.tieneHijoIzquierdo()) {
				 hi +=contarHojas(ab.getHijoIzquierdo());
			}
			if(ab.tieneHijoDerecho()) {
				 hd +=contarHojas(ab.getHijoDerecho());
		
			}
			
			return hi + hd;  
		}
		
	}
	public int altura1() {
		int izq = 0, der = 0;
		if(this.tieneHijoIzquierdo())
			izq = 1 + this.getHijoIzquierdo().altura1();
		if(this.tieneHijoDerecho()) {
			der = 1 + this.getHijoDerecho().altura1();
		}
		if(izq < der) return der;
		else return izq;
	}

    public ArbolBinario<T> espejo() {
    	ArbolBinario<T> e = new ArbolBinario<T>();   
    	ListaGenerica<T> l = new ListaEnlazadaGenerica<T>();
    	if(!this.esVacio()) {
    		this.espejo(e,l);
    	}
		return e;
    }
    
    private ArbolBinario<T> espejo(ArbolBinario<T> ab1,ListaGenerica<T> ab2){
    	return null;
    }

    	
	public void entreNiveles(int n, int m){
		ArbolBinario<T> a = null;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while(!cola.esVacio()) {
			a=cola.desencolar();
			if(a != null) {
				System.out.print(a.getDato());
				if(a.tieneHijoIzquierdo()) {
					cola.encolar(a.getHijoIzquierdo());
				}
				if(a.tieneHijoDerecho()) {
					cola.encolar(a.getHijoDerecho());
				}
			}
			else {
				if(!cola.esVacio()) {
					cola.encolar(null);
				}
			}
		}
	}

	public ListaGenerica<T> caminoMasLargo(ArbolBinario<T> a) {
		ListaGenerica<T> lista = new ListaEnlazadaGenerica<T>();
		ListaGenerica<T> actual = new ListaEnlazadaGenerica<T>();
		caminoMasLargo(a,lista, actual);
		return lista;
	}
	
	private void caminoMasLargo(ArbolBinario<T> a, ListaGenerica<T> l, ListaGenerica<T> actual) {
		if(a.esHoja()) {
			if(actual.tamanio() > l.tamanio()) {
				l = actual.clonar();
			}
		}else {
			actual.agregarFinal(a.getDato());
			if(a.tieneHijoIzquierdo()) {
				a.getHijoIzquierdo().caminoMasLargo(a, l, actual);
			}
			if(a.tieneHijoDerecho()) {
				a.getHijoDerecho().caminoMasLargo(a, l, actual);
			}
			actual.eliminarEn(actual.tamanio());
		}
		
	}
	
	public int contadorArbol() {
		int retorno = 0;
		contadorArbol(this,retorno);
		return retorno;
	}
	private void contadorArbol(ArbolBinario<T> a,int cont) {
		if(a.tieneHijoIzquierdo()) {
			a.getHijoIzquierdo().contadorArbol(a,cont);
		}
		cont += (int)a.getDato();
		if(a.tieneHijoDerecho()) {
			a.getHijoDerecho().contadorArbol(a, cont);
		}
	}
	public int retardoEnvio() {
		int ret = 0;
		int retAct = 0;
		retardoEnvio(this,ret,retAct);
		return ret;
	}
	private void retardoEnvio(ArbolBinario<T>a,int ret,int retAct) {
		if(a.esHoja()) {
			if(retAct > ret) {
				ret = retAct;
			}
		}
		else {
			retAct += (int)a.getDato();
			if(a.tieneHijoIzquierdo()) {
				a.getHijoIzquierdo().retardoEnvio(a,ret,retAct);
				retAct -= (int)a.getHijoIzquierdo().getDato();
			}
			
			if(a.tieneHijoDerecho()) {
				a.getHijoDerecho().retardoEnvio(a,ret,retAct);
				retAct -= (int)a.getHijoDerecho().getDato();
			}
		}
	}
	public int sumaElementosProfundidad(int p) {
		int retorno =0;
		int nivel = 0;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		ArbolBinario<T> arbol = null;
		cola.encolar(this);
		cola.encolar(null);
		while(!cola.esVacio()) {
			arbol = cola.desencolar();
			if(arbol != null) {
				if(nivel == p) {
					retorno += (int)arbol.getDato();
				}
				if(arbol.tieneHijoIzquierdo()) {
					cola.encolar(arbol.getHijoIzquierdo());
				}
				if(arbol.tieneHijoDerecho()) {
					cola.encolar(arbol.getHijoDerecho());
				}
			}
			else {
				if(!cola.esVacio()) {
					nivel++;
					cola.encolar(null);
				}
			}
		}
		return retorno;
	}
	public int resolver(ArbolBinario<String> ab, int k) {
		int suma=0;
		int contador=0;
		resolverPrivado(ab,k,contador,suma);
		return suma;
	}
	private void resolverPrivado(ArbolBinario<String> ab, int k, int contador, int max) {
		if(ab.esHoja()) {
			if(k == contador) {
				max++;
				
			}
		}else {
			if((!ab.tieneHijoIzquierdo() && ab.tieneHijoDerecho()) || (ab.tieneHijoIzquierdo() && !ab.tieneHijoDerecho())) {
				contador++;
			}
			if(ab.tieneHijoIzquierdo()){
				resolverPrivado(ab.getHijoIzquierdo(),k,contador,max);
				contador--;
			}
			if(ab.tieneHijoDerecho()) {
				resolverPrivado(ab.getHijoDerecho(),k,contador,max);
				contador--;
			}
			
		}
		
	}
	/*
	 * Implemente el método cantidadHijosPares(): int que devuelve cuántos subárboles del
	 * 	árbol tienen un número par de hijos. Los nros 0 y 2 son pares. Realice un recorrido
	 * 	en inorden.
	 */
	
	public int cantidadHijosPares() {
		int cantidad=0;
		return cantidadHijos(this,cantidad);
	}
	private int cantidadHijos(ArbolBinario<T> a, int cantidad) {
		if(!a.esVacio()) {
			if(a.esHoja()) {
				cantidad++;
			}else {	
				if(a.tieneHijoIzquierdo()) {
					cantidadHijos(a.getHijoIzquierdo(),cantidad);
				}
				if(a.tieneHijoIzquierdo() && a.tieneHijoDerecho()) {
					cantidad++;
				}
				if(a.tieneHijoDerecho()) {
					cantidadHijos(a.getHijoDerecho(),cantidad);
				}
			}
		}
		return cantidad;
	}

	public int altura() {
		// TODO Auto-generated method stub
		return 0;
	}
}
