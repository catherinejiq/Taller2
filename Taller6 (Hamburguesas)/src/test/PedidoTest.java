package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import modulo.Combo;
import modulo.HamburguesasException;
import modulo.Pedido;
import modulo.Producto;
import modulo.ProductoMenu;
import modulo.Restaurante;

public class PedidoTest {

	private Restaurante restaurante;
	private Pedido pedido;
	
	public void cargarDatos() {
		String ingr = "./data/ingredientes.txt";
		String menu = "./data/menu.txt";
		String comb = "./data/combos.txt";
		String bebi = "./data/bebidas.txt";
		Restaurante restaurante = new Restaurante();
		try {
			restaurante.cargarInformacionDelRestaurante(ingr, menu, comb, bebi);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@BeforeEach
	public void setUp() {
		this.cargarDatos();
		Pedido nuevoPedido = new Pedido("Gabriel", "Calle 24c #69-59", 1);
	}
	
	@Test
	@Order(1)
	@DisplayName("agregarProducto")
	public void testAgregarProducto() {
		ProductoMenu producto = new ProductoMenu("corral pollo",15000,524);
		ArrayList<Producto> items = pedido.getItemsPedido();
		int numero = items.size();
		try {
			pedido.agregarProducto(producto);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		ArrayList<Producto> itemsActualizados = pedido.getItemsPedido();
		int prueba = itemsActualizados.size();
		assertTrue(numero < prueba, () -> "Se agrega el producto correctamente");
	}

	@Test
	@Order(2)
	@DisplayName("getPrecioTotalPedido")
	public void testGetPrecioTotalPedido() {
		ProductoMenu producto = new ProductoMenu("corral pollo",15000,524);
		ProductoMenu producto2 = new ProductoMenu("gaseosa",5000,160);
		try {
			pedido.agregarProducto(producto);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		try {
			pedido.agregarProducto(producto2);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		int precioEsperado = 20000;
		int precioActual = pedido.getPrecioTotalPedido();
		assertTrue(precioEsperado == precioActual, () -> "Se calcula el precio correctamente");
	}
	
	@Test
	@Order(3)
	@DisplayName("getCaloriasPedido")
	public void testGetCaloriasPedido() {
		ProductoMenu producto = new ProductoMenu("corral pollo",15000,524);
		ProductoMenu producto2 = new ProductoMenu("gaseosa",5000,160);
		try {
			pedido.agregarProducto(producto);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		try {
			pedido.agregarProducto(producto2);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		int caloriasEsperadas = 160 + 524;
		int caloriasActuales = pedido.getCaloriasPedido();
		assertTrue(caloriasEsperadas == caloriasActuales, () -> "Se calculan las calorías correctamente");
	}

	@Test
	@Order(3)
	@DisplayName("generarFactura")
	public void testGenerarFactura() throws IOException {
		ProductoMenu producto = new ProductoMenu("corral pollo",15000,524);
		ProductoMenu producto2 = new ProductoMenu("gaseosa",5000,160);
		try {
			pedido.agregarProducto(producto);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		try {
			pedido.agregarProducto(producto2);
		} catch (HamburguesasException e) {
			e.printStackTrace();
		}
		File archivo = new File("./data/Factura" + pedido.getIdPedido() + ".txt");
		pedido.generarFactura(archivo);
		assertTrue(archivo != null, () -> "Se crea el archivo");
	}
}

