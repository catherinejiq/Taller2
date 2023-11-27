package modulo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {

    private int numeroPedido;
    private int idPedido;
    private String nombreCliente;
    private String direccionCliente;
    private ArrayList<Producto> itemsPedido;

    public Pedido(String nombreCliente, String direccionCliente, int idPedido) {
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.idPedido = idPedido;
        this.numeroPedido = idPedido;
        this.itemsPedido = new ArrayList<Producto>();
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public ArrayList<Producto> getItemsPedido() {
        return itemsPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public void setItemsPedido(ArrayList<Producto> itemsPedido) {
        this.itemsPedido = itemsPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void agregarProducto(Producto nuevoItem) throws HamburguesasException {
        if (!verificarLimite()) {
            itemsPedido.add(nuevoItem);
        }
    }

    private int getPrecioNetoPedido() {
        int precio = 0;
        for (Producto item : itemsPedido) {
            int iteracion = item.getPrecio();
            precio += iteracion;
        }
        return precio;
    }

    public int getCaloriasPedido() {
        int calorias = 0;
        for (Producto item : itemsPedido) {
            int iteracion = item.getCaloria();
            calorias += iteracion;
        }
        return calorias;
    }

    public int getPrecioTotalPedido() {
        int neto = getPrecioNetoPedido();
        int iva = getPrecioIVAPedido();
        return neto + iva;
    }

    public int getPrecioIVAPedido() {
        int precio = 0;
        for (Producto item : itemsPedido) {
            int iteracion = item.getPrecio();
            precio += iteracion;
        }
        return (int) (precio * 0.19);
    }

    private String generarTextoFactura() {
        ArrayList<String> lista = new ArrayList<String>();
        for (Producto item : itemsPedido) {
            String nombre = item.getNombre();
            lista.add(nombre);
        }
        int precion = this.getPrecioNetoPedido();
        int precioi = this.getPrecioIVAPedido();
        int preciot = this.getPrecioTotalPedido();
        int calorias = this.getCaloriasPedido();
        return this.nombreCliente + ";" + this.direccionCliente + ";" + this.idPedido + ";" + this.numeroPedido + ";"
                + lista + ";" + precion + ";" + precioi + ";" + preciot + ";" + calorias;
    }

    public void generarFactura(File archivo) throws IOException {
        String explicacion = "Nombre;Direccion;ID,Numero de pedido;Productos;Precio Total; IVA(19%);Precio total,Calorias" + "\n";
        FileWriter fw = new FileWriter(archivo);
        String texto = this.generarTextoFactura();
        fw.write(explicacion);
        fw.write(texto);
        fw.close();
    }

    private boolean verificarLimite() {
        return this.getPrecioTotalPedido() >= 150000;
    }
}

