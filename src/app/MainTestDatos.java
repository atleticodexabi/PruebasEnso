package app;

import java.util.ArrayList;
import java.util.Date;

import exceptions.FacturaNoExistenteException;
import exceptions.MenuNoExistenteException;
import exceptions.PlatoIlegalException;
import impl.GestionDatosImpl;
import interfaces.IGestionDatos;
import pojos.Bandeja;
import pojos.Bebida;
import pojos.Factura;
import pojos.Menu;
import pojos.Plato;
import pojos.Vale;
import pojos.Valoracion;

public class MainTestDatos {
	public static void main(String[] args) {

		// instanciamos la interfaz de gestión de datos
		IGestionDatos datos = new GestionDatosImpl();

		/***********************************************************************************************************************************************/
		// OBTENER LOS PLATOS:

		System.out.println("--------------PLATOS--------------");
		// obtenemos los platos
		ArrayList<Plato> platos = (ArrayList<Plato>) datos.getPlatos();

		// mostramos los platos
		for (Plato plato : platos) {
			System.out.println(plato.toString());
		}

		/***********************************************************************************************************************************************/
		// OBTENER LOS MENÚS:

		System.out.println("--------------MENÚS--------------");
		// obtenemos los menus
		ArrayList<Menu> menus = (ArrayList<Menu>) datos.getMenus();

		// mostramos los menus
		for (Menu menu : menus) {
			System.out.println(menu.toString());
		}

		/***********************************************************************************************************************************************/
		// OBTENER LAS FACTURAS:

		System.out.println("--------------FACTURAS--------------");
		// obtenemos las facturas
		ArrayList<Factura> facturas = (ArrayList<Factura>) datos.getFacturas();

		// mostramos las facturas
		for (Factura factura : facturas) {
			System.out.println(factura.toString());
		}

		/***********************************************************************************************************************************************/
		// OBTENER LAS VALORACIONES:

		System.out.println("--------------VALORACIONES--------------");
		// obtenemos las valoraciones
		ArrayList<Valoracion> valoraciones = (ArrayList<Valoracion>) datos.getValoraciones();

		// mostramos las valoraciones
		for (Valoracion valoracion : valoraciones) {
			System.out.println(valoracion.toString());
		}

		/***********************************************************************************************************************************************/

		try {
			// CREACIÓN DE UN MENÚ:

			// Creamos las listas de primeros, segundos y postres
			ArrayList<Plato> primeros, segundos, postres;

			primeros = new ArrayList<Plato>();
			segundos = new ArrayList<Plato>();
			postres = new ArrayList<Plato>();

			// A continuación añadimos 3 platos a cada lista
			primeros.add(new Plato("tortilla", "tortilla española"));
			primeros.add(new Plato("crema de verduras", "crema de calabacín, zanahoria, calabaza y puerro"));
			primeros.add(new Plato("sopa de pollo", "sopa de pollo con fideos"));

			segundos.add(new Plato("lasaña", "lasaña boloñesa"));
			segundos.add(new Plato("ternera guisada", "ternera guisada con guarnición de verduras"));
			segundos.add(new Plato("pollo asado", "pollo asado al horno"));

			postres.add(new Plato("flan", "flan de huevo con caramelo y nata"));
			postres.add(new Plato("tarta de queso", "tarta de queso fría con mermelada de arándanos"));
			postres.add(new Plato("manzana", "manzana golden"));

			// Añadimos las listas de platos (primeros, segundos y postres) al
			// menú nuevo
			Menu nuevoMenu = new Menu(new Date(), primeros, segundos, postres);

			System.out.println("--------------MENÚ RECIÉN CREADO--------------");
			System.out.println(nuevoMenu.toString());

			// Guardamos el nuevo menú
			datos.crearMenu(nuevoMenu);

			System.out.println("--------------LISTADO DE MENÚS DESPUES CREAR UN MENÚ--------------");
			menus = (ArrayList<Menu>) datos.getMenus();

			// mostramos los menus después de crear el nuevo
			for (Menu menu : menus) {
				System.out.println(menu.toString());
			}

			/***********************************************************************************************************************************************/
			// MODIFICACIÓN DE UN MENÚ:

			// modificamos el menú (concretamente los postres)
			nuevoMenu.getPostres().remove(2);
			nuevoMenu.getPostres().add(new Plato("macedonia", "macedonia de frutas de temporada"));

			// guardamos la modificación del menú
			datos.modificarMenu(nuevoMenu);

			System.out.println("--------------LISTADO DE MENÚS DESPUES DE MODIFICAR UN MENÚ--------------");
			menus = (ArrayList<Menu>) datos.getMenus();

			// mostramos los menus después de modificar un menú
			for (Menu menu : menus) {
				System.out.println(menu.toString());
			}

			/***********************************************************************************************************************************************/
			// BORRADO DE UN MENÚ
			datos.borrarMenu(nuevoMenu);

		} catch (PlatoIlegalException | MenuNoExistenteException e) {

			e.printStackTrace();
		}

		/***********************************************************************************************************************************************/
		// CREACIÓN DE UNA FACTURA

		try {

			// creamos una bandeja
			Bandeja nuevaBandeja = new Bandeja(1);

			// creamos un vale
			Vale nuevoVale = new Vale(1);

			// creamos la factura
			Factura nuevaFactura = new Factura(99, nuevaBandeja, nuevoVale, new Plato("tortilla", "tortilla española"),
					new Plato("lasaña", "lasaña boloñesa"), new Plato("manzana", "manzana golden"),
					Bebida.valueOf("CERVEZA"), false, false, false, new Date(), null);

			System.out.println("--------------FACTURA RECIÉN CREADA--------------");
			System.out.println(nuevaFactura.toString());

			// Guardamos la nueva factura
			int idFacturaNueva = datos.crearFactura(nuevaFactura);
			nuevaFactura.setId(idFacturaNueva);

			facturas = (ArrayList<Factura>) datos.getFacturas();
			System.out.println("--------------LISTADO DE FACTURAS DESPUÉS DE GUARDAR UNA FACTURA--------------");

			// mostramos las facturas
			for (Factura factura : facturas) {
				System.out.println(factura.toString());
			}

			/***********************************************************************************************************************************************/
			// MODIFICACIÓN DE UNA FACTURA

			nuevaFactura.setBandeja(new Bandeja(89));
			datos.modificarFactura(nuevaFactura);

			facturas = (ArrayList<Factura>) datos.getFacturas();
			System.out.println("--------------LISTADO DE FACTURAS DESPUÉS DE MODIFICAR UNA FACTURA-------------");

			// mostramos las facturas
			for (Factura factura : facturas) {
				System.out.println(factura.toString());
			}

			/***********************************************************************************************************************************************/
			// BORRADO DE UNA FACTURA

			datos.borrarFactura(nuevaFactura);

			facturas = (ArrayList<Factura>) datos.getFacturas();
			System.out.println("--------------LISTADO DE FACTURAS DESPUÉS DE BORRAR UNA FACTURA--------------");

			// mostramos las facturas
			for (Factura factura : facturas) {
				System.out.println(factura.toString());
			}

		} catch (PlatoIlegalException | FacturaNoExistenteException e1) {

			e1.printStackTrace();
		}

		/***********************************************************************************************************************************************/
		// CREACIÓN DE UNA VALORACIÓN
		Valoracion nuevaValoracion = null;
		try {

			valoraciones = (ArrayList<Valoracion>) datos.getValoraciones();
			System.out.println("--------------LISTADO DE VALORACIONES ANTES DE CREAR UNA VALORACIÓN--------------");

			// mostramos las valoraciones
			for (Valoracion valoracion : valoraciones) {
				System.out.println(valoracion.toString());
			}

			// creamos la valoración
			nuevaValoracion = new Valoracion(99, new Plato("tortilla", "tortilla española"), 10);

			// guardamos la valoración
			datos.crearValoracion(nuevaValoracion);

			valoraciones = (ArrayList<Valoracion>) datos.getValoraciones();
			System.out.println("--------------LISTADO DE VALORACIONES DESPUÉS DE CREAR UNA VALORACIÓN--------------");

			// mostramos las facturas
			for (Valoracion valoracion : valoraciones) {
				System.out.println(valoracion.toString());
			}

		} catch (PlatoIlegalException e) {

			e.printStackTrace();

		}
	}
}
