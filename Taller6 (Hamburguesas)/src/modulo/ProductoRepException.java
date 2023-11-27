package modulo;

@SuppressWarnings("serial")
public class ProductoRepException extends HamburguesasException {
    private String repetido;

    public ProductoRepException(String repetido) {
        super("Error: Producto repetido - " + repetido);
        this.repetido = repetido;
    }

    public String obtenerProductoRepetido() {
        return repetido;
    }
}
