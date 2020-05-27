package app;

import exceptions.PlatoIlegalException;
import impl.SensoresImpl;
import interfaces.ISensores;
import pojos.Bebida;
import pojos.Factura;
import pojos.Plato;

public class MainTestSensores {
	public static void main(String[] args) {

		// instanciamos la interfaz de sensores
		ISensores sensores = new SensoresImpl();

		// creamos un ojbeto seleccionMenu para posteriormente crear una factura
		Factura factura = new Factura();

		try {

			// selecci�n de 1�, 2� y postre
			factura.setPrimero(new Plato("sopa de pollo", "sopa de pollo con fideos"));
			factura.setSegundo(new Plato("lasa�a", "lasa�a bolo�esa"));
			factura.setPostre(new Plato("flan", "flan de huevo con nata y caramelo"));

			// seleccionamos la bebida
			factura.setBebida(Bebida.valueOf("AGUA"));

			System.out.println("\nFactura antes de leer bandeja y vale:");
			System.out.println(factura.toString());

			// "leemos" el vale
			sensores.leerVale(factura);

			// "leemos" la bandeja
			sensores.leerBandeja(factura);

			System.out.println("\nFactura despu�s de leer bandeja y vale"); // NOTA: aqu� la factura todav�a est� sin id
																			// (este se obtiene al guardar, pero en este
																			// main no estamos guardando, ya que estamos
																			// probando el subsistema de sensores)
			System.out.println(factura.toString());

		} catch (PlatoIlegalException e) {

			e.printStackTrace();
		}
	}
}
