package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import consola.Aplicacion;
import modulo.Combo;
import modulo.ProductoMenu;
import modulo.Restaurante;


public class ComboTest {

	private Restaurante restaurante;
	private Combo combo;
	
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
		ArrayList<ProductoMenu> listaProductos = new ArrayList<ProductoMenu>();
		listaProductos.add(restaurante.menuBase.get("corral"));
		listaProductos.add(restaurante.menuBase.get("papas medianas"));
		listaProductos.add(restaurante.bebidas.get("gaseosa"));
		Combo nvoCombo = new Combo("combo corral", 0.10, listaProductos);
	}
	
	@Test
	@Order(1)
	@DisplayName("agregarItemACombo")
	public void testAgregarItemACombo() {
		ProductoMenu producto = new ProductoMenu("corral pollo", 15000, 524);
		ArrayList<ProductoMenu> items = combo.getItemsCombo();
		int numero = items.size();
		combo.agregarItemACombo(producto);
		ArrayList<ProductoMenu> itemsActualizados = combo.getItemsCombo();
		int prueba = itemsActualizados.size();
		assertTrue(numero < prueba, () -> "Se agrega el producto correctamente");
	}

	@Test
	@Order(2)
	@DisplayName("getPrecio")
	public void testGetPrecio() {
		int precioEsperado = 22050;
		int precioActual = combo.getPrecio();
		assertTrue(precioEsperado == precioActual, () -> "Se calcula el precio correctamente");
	}
	
	@Test
	@Order(3)
	@DisplayName("getCaloria")
	public void testGetCaloria() {
		int caloriasEsperadas = 1156;
		int caloriasActuales = combo.getCaloria();
		assertTrue(caloriasEsperadas == caloriasActuales, () -> "Se calculan las calorías correctamente");
	}
}
