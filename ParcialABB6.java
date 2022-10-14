package ar.edu.unlp.info.oo1.ArbolesB;

import ar.edu.unlp.info.oo1.ArbolesB.tp2.ColaGenerica;

public class ParcialABB6 {

	/*
	Dada una clase Java denominada BuscadorDeArbol, que tiene como variable de instancia un
		ArbolBinario<Integer>denominado arbolimplemente en dicha clase el método public Integer
		buscar(). Al encontrar el primer primo debe cortar la ejecución.
		Considere que existe dentro de la definición de la clase el método esPrimo(Integer num)
		que retorna true si el número pasado como parámetro es primo o false en caso contrario.
		Realizar un recorrido por niveles para retornar el primer elemento primo del árbol.
	 */
	private ArbolBinario<Integer> arbol;
	
	public ParcialABB6(ArbolBinario<Integer> arbol) {
		this.arbol = arbol;
	}
	
	
	public Integer buscarPrimerElementoUltimoNivel() {
		ColaGenerica<ArbolBinario<Integer>> cola = new ColaGenerica<ArbolBinario<Integer>>();
		ArbolBinario<Integer> aux = null;
		Integer nivel=0;
		cola.encolar(arbol);
		cola.encolar(null);
		while(!cola.esVacio()) {
			aux=cola.desencolar();
			if(aux != null) {
				if(nivel == aux.altura1()) {
					return aux.getDato();
				}
				if(aux.tieneHijoIzquierdo()) {
					cola.encolar(aux.getHijoIzquierdo());
				}
				if(aux.tieneHijoDerecho()) {
					cola.encolar(aux.getHijoDerecho());
				}
			}else {
				if(!cola.esVacio()) {
					nivel++;
					cola.encolar(null);
				}
			}
		}
		return 0;
	}
}
