public class RedBinariaLLena{

    public int retardoReenvio(){
        ArbolBinario<T> a= null;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		int nivel=0;
        int retardo = 0;
        cola.encolar(this);
		cola.encolar(null);
		while(!cola.esVacia()){
			a= cola.desencolar();
			if(a.esVacio()){
				retardo += a.getDato();
				if(a.tieneHijoIzquierdo()){
					cola.encolar(a.getHijoIzquierdo());
				}
				if(a.tieneHijoDerecho()){
					cola.encolar(a.getHijoDerecho());
				}
				else (!cola.esVacia()){
					System.out.println();
					nivel++;
					cola.encolar(null);
				}
			}
        }
        return retardo;
    }
}
