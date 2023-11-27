package modulo;


import java.util.ArrayList;


public class Combo implements Producto {


    public double descuento;  
    public String nombreCombo; 
    private ArrayList<ProductoMenu> itemsCombo; 


    public Combo(String nombreCombo, double descuento, ArrayList<ProductoMenu> itemsCombo) {
        this.nombreCombo = nombreCombo;
        this.descuento = descuento;
        this.itemsCombo = itemsCombo;
	}


	public ArrayList<ProductoMenu> getItemsCombo() {
        return itemsCombo;
    }

    public void setItemsCombo(ArrayList<ProductoMenu> itemsCombo) {
        this.itemsCombo = itemsCombo;
    }


    public void agregarItemACombo(ProductoMenu itemCombo) {
        itemsCombo.add(itemCombo);
    }


    @Override
    public String getNombre() {
        return nombreCombo;
    }


    @Override
    public int getPrecio() {
        int precio = 0;
       
        for (ProductoMenu item : itemsCombo) {
            int iteracion = item.getPrecio();
            precio = precio + iteracion;
        }

        precio = (int) (precio - (precio * descuento));
        return precio;
    }


    @Override
    public String getTextoFactura() {
        return null;
    }

    
    @Override
    public int getCaloria() {
        int calorias = 0;
        
        for (ProductoMenu item : itemsCombo) {
            int iteracion = item.getCaloria();
            calorias = calorias + iteracion;
        }
        return calorias;
    }
}
