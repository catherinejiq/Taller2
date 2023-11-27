package modulo;

import java.util.ArrayList;

public class ProductoModificado implements Producto {

    
    private ProductoMenu productoBase;  
    private ArrayList<Ingredientes> agregados;  
    private ArrayList<Ingredientes> eliminados;  


    public ProductoModificado(ProductoMenu productoBase) {
        this.productoBase = productoBase;
        this.agregados = new ArrayList<Ingredientes>();
        this.eliminados = new ArrayList<Ingredientes>();
    }

    
    @Override
    public String getNombre() {
        return productoBase.getNombre();
    }

    
    @Override
    public int getPrecio() {
        return productoBase.getPrecio();
    }

    
    @Override
    public String getTextoFactura() {
        return null;
    }

    
    @Override
    public int getCaloria() {
        int calorias = 0;
  
        return calorias;
    }
}

