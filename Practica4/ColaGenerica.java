package tp04.ejercicio1;

import tp02.ejercicio2.ListaEnlazadaGenerica;

public class ColaGenerica {    
        private ListaEnlazadaGenerica cola = new ListaEnlazadaGenerica<Object>();
    
        public ColaGenerica(){
            
        }
        public void encolar(Object elem){
            cola.agregarFinal(elem);
        }
    
        public Object desencolar(){
            Object l = cola.elemento(1);
            cola.eliminar(l);
            return l;
        }
    
        public void tope(){
            System.out.println(cola.elemento(1));
        }
        public boolean esVacio(){
            return cola.tamanio() == 0;
        }
    
}

