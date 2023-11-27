package modulo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurante {

    private ArrayList<Combo> combos;
    private HashMap<Integer, Pedido> pedidos;
    private ArrayList<Ingredientes> ingredientes;
    public HashMap<String, ProductoMenu> menuBase;
    public HashMap<String, ProductoMenu> bebidas;

    private Pedido pedidoenCurso;
    private int idActual;

    public Restaurante() {
        this.menuBase = new HashMap<String, ProductoMenu>();
        this.bebidas = new HashMap<String, ProductoMenu>();
        this.pedidos = new HashMap<Integer, Pedido>();
        this.ingredientes = new ArrayList<Ingredientes>();
        this.combos = new ArrayList<Combo>();
    }

    public void iniciarNuevoPedido(String nombreCliente, String direccionCliente) {
        idActual += 1;
        Pedido nuevoPedido = new Pedido(nombreCliente, direccionCliente, idActual);
        this.asignarPedidoEnCurso(nuevoPedido);
        pedidos.put(idActual, nuevoPedido);
    }

    public void cerrarYGuardarPedido() throws IOException {
        int idPedido = pedidoenCurso.getIdPedido();
        this.pedidos.put(idPedido, pedidoenCurso);
        File archivoFactura = new File("./data/Factura" + idPedido + ".txt");
        this.pedidoenCurso.generarFactura(archivoFactura);
    }

    public Pedido obtenerPedidoEnCurso() {
        return pedidoenCurso;
    }

    public ArrayList<ProductoMenu> obtenerMenuBase() {
        ArrayList<ProductoMenu> menu = new ArrayList<ProductoMenu>();
        for (ProductoMenu producto : menuBase.values()) {
            menu.add(producto);
        }
        return menu;
    }

    public ArrayList<ProductoMenu> obtenerBebidas() {
        ArrayList<ProductoMenu> liquidos = new ArrayList<ProductoMenu>();
        for (ProductoMenu producto : bebidas.values()) {
            liquidos.add(producto);
        }
        return liquidos;
    }

    public ArrayList<Ingredientes> obtenerIngredientes() {
        return ingredientes;
    }

    public int obtenerIdActual() {
        return idActual;
    }

    public HashMap<Integer, Pedido> obtenerPedidos() {
        return pedidos;
    }

    public void asignarCombos(ArrayList<Combo> listaCombos) {
        this.combos = listaCombos;
    }

    public void asignarPedidos(HashMap<Integer, Pedido> listaPedidos) {
        this.pedidos = listaPedidos;
    }

    public void asignarIngredientes(ArrayList<Ingredientes> listaIngredientes) {
        this.ingredientes = listaIngredientes;
    }

    public void asignarMenuBase(HashMap<String, ProductoMenu> listaMenuBase) {
        this.menuBase = listaMenuBase;
    }

    public void asignarPedidoEnCurso(Pedido pedidoEnCurso) {
        this.pedidoenCurso = pedidoEnCurso;
    }

    public void asignarIdActual(int nuevoId) {
        this.idActual = nuevoId;
    }

    public ArrayList<Combo> obtenerCombos() {
        return combos;
    }

    public void agregarProductoAlPedido(String nombreProducto) {
        ProductoMenu producto = menuBase.get(nombreProducto);
        try {
            this.pedidoenCurso.agregarProducto(producto);
        } catch (HamburguesasException e) {
            e.printStackTrace();
        }
    }

    public void agregarComboAlPedido(int indiceCombo) {
        Combo comboPedido = combos.get(indiceCombo);
        try {
            this.pedidoenCurso.agregarProducto(comboPedido);
        } catch (HamburguesasException e) {
            e.printStackTrace();
        }
    }

    public void cargarInformacionDelRestaurante(String archivoIngredientes, String archivoMenu, String archivoCombos, String archivoBebidas) throws IOException {
        menuBase = new HashMap<String, ProductoMenu>();
        bebidas = new HashMap<String, ProductoMenu>();
        ingredientes = new ArrayList<Ingredientes>();
        combos = new ArrayList<Combo>();
        File archivoI = new File(archivoIngredientes);
        File archivoM = new File(archivoMenu);
        File archivoC = new File(archivoCombos);
        File archivoB = new File(archivoBebidas);
        cargarIngredientesDesdeArchivo(archivoI);
        cargarMenuDesdeArchivo(archivoM);
        cargarCombosDesdeArchivo(archivoC);
        cargarBebidasDesdeArchivo(archivoB);
    }

    public void cargarIngredientesDesdeArchivo(File archivoIngredientes) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(archivoIngredientes));
        String linea = lector.readLine();
        while (linea != null) {

            String[] datos = linea.split(";");
            String nombreI = datos[0];
            String precioA = datos[1];
            String calorias = datos[2];
            int caloriasN = Integer.parseInt(calorias);
            int precioN = Integer.parseInt(precioA);
            Ingredientes nuevo = new Ingredientes(nombreI, precioN, caloriasN);
            ingredientes.add(nuevo);
            linea = lector.readLine();
        }
        lector.close();
    }

    public void cargarMenuDesdeArchivo(File archivoMenu) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(archivoMenu));
        String linea = lector.readLine();
        while (linea != null) {
            String[] datos = linea.split(";");
            String nombreI = datos[0];
            String precio = datos[1];
            String calorias = datos[2];
            int precioN = Integer.parseInt(precio);
            int caloriasN = Integer.parseInt(calorias);
            ProductoMenu nuevo = new ProductoMenu(nombreI, precioN, caloriasN);
            if (menuBase.containsValue(nuevo)) {
                System.out.println("Se encontró un producto repetido en el menú");
            } else {
                menuBase.put(nombreI, nuevo);
            }
            linea = lector.readLine();
        }
        lector.close();
    }

    public void cargarBebidasDesdeArchivo(File archivoBebidas) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(archivoBebidas));
        String linea = lector.readLine();
        while (linea != null) {
            String[] datos = linea.split(";");
            String nombreI = datos[0];
            String precio = datos[1];
            String calorias = datos[2];
            int precioN = Integer.parseInt(precio);
            int caloriasN = Integer.parseInt(calorias);
            ProductoMenu nuevo = new ProductoMenu(nombreI, precioN, caloriasN);
            bebidas.put(nombreI, nuevo);
            linea = lector.readLine();
        }
        lector.close();
    }

    public void cargarCombosDesdeArchivo(File archivoCombos) throws IOException {
        BufferedReader lector = new BufferedReader(new FileReader(archivoCombos));
        String linea = lector.readLine();
        while (linea != null) {
            String[] datos = linea.split(";");
            String nombreI = datos[0];
            String descuento = datos[1];
            String item1 = datos[2];
            String item2 = datos[3];
            String item3 = datos[4];
            ArrayList<ProductoMenu> items = new ArrayList<ProductoMenu>();
            descuento = descuento.replace("%", "");
            double descuentod = Double.valueOf(descuento);
            descuentod = descuentod / 100;
            items.add(menuBase.get(item1));
            items.add(menuBase.get(item2));
            items.add(bebidas.get(item3));
            Combo nuevo = new Combo(nombreI, descuentod, items);
            combos.add(nuevo);
            linea = lector.readLine();
        }
        lector.close();
    }
}
