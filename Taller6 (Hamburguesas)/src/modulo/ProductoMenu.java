package modulo;


public class ProductoMenu implements Producto{

	public String nombre;
	
	public int precioBase;
	
	public int calorias;

	public ProductoMenu(String nombre, int precioBase, int calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias = calorias;
	}

	public int getCaloria() {
		return calorias;
	}
	
	
	@Override
	public int getPrecio() {
		return precioBase;
	}

	@Override
	public String getTextoFactura() {
		return null;
	}

	public String getNombre() {
		return nombre;
	}
	
}