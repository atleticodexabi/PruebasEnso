package app;

import exceptions.PlatoIlegalException;
import impl.SeleccionMenuImpl;
import interfaces.ISeleccionMenu;
import pojos.Bebida;
import pojos.Factura;
import pojos.Plato;

public class MainTestSeleccionMenus {
	public static void main(String[] args) {

		// instanciamos la interfaz de selección de menú
		ISeleccionMenu seleccionMenu = new SeleccionMenuImpl();
		Factura resultado;

		try {
			// seleccion de 1º, 2º y postre
			seleccionMenu.setPrimero(new Plato("tortilla", "tortilla española"));
			seleccionMenu.setSegundo(new Plato("lasaña", "lasaña boloñesa"));
			seleccionMenu.setPostre(new Plato("manzana", "manzana golden"));

			// bebida
			seleccionMenu.setBebida(Bebida.valueOf("REFRESCO"));

			System.out.println("Primero seleccionado:");
			System.out.println(seleccionMenu.getPrimero());

			System.out.println("Segundo seleccionado:");
			System.out.println(seleccionMenu.getSegundo());

			System.out.println("Postre seleccionado:");
			System.out.println(seleccionMenu.getPostre());

			System.out.println("Bebida seleccionada:");
			System.out.println(seleccionMenu.getBebida());

			// confirmación de menú
			resultado = seleccionMenu.confirmarMenu();

			System.out.println("\nFactura creada:");
			System.out.println(resultado);

		} catch (PlatoIlegalException e) {

			e.printStackTrace();
		}

	}

}
