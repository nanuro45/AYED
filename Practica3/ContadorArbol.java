
public class ContadorArbol {
    
    private ListaEnlazadaGenerica<Integer> list = new ListaEnlazadaGenerica<Integer>();

    public ListaEnlazadaGenerica<Integer> paresInOrden(ArbolBinario<Integer> arbol){
        if(arbol.esVacio()){
            return null;
        }else{
            if(arbol.tieneHijoIzquierdo())
                ContadorArbol.paresInOrden(arbol.GetHijoIzquierdo());
            if(arbol.getDato() % 2 == 0)
                list.agregarFinal(arbol.getDato());
            if(arbol.tieneHijoDerecho())
                ContadorArbol.paresInOrden(arbol.getHijoDerecho());
            return this.list;            
        }
    }

    public ListaEnlazadaGenerica<Integer> paresPostOrden(ArbolBinario<Integer> arbolito){
        if(arbol.esVacio()){
            return null;
        }
        else{
            if(arbolito.tieneHijoIzquierdo())
                ContadorArbol.paresPostOrden(arbolito.getHijoIzquierdo());
            if(arbolito.tieneHijoDerecho())
                ContadorArbol.paresPostOrden(arbolito.getHijoDerecho());
            if(arbol.getDato() % 2 ==0){
                list.agregarFinal(arbolito.getDato());
            }
            return this.list;
        }
        
    }
    
}
