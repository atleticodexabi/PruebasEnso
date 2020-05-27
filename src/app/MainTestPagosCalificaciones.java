package app;

import exceptions.PlatoIlegalException;
import exceptions.ValoracionIlegalException;
import impl.PagosCalificacionesImpl;
import interfaces.IPagosCalificaciones;
import pojos.Bebida;
import pojos.Factura;
import pojos.Plato;

public class MainTestPagosCalificaciones {
	public static void main(String[] args) {

		// instanciamos la interfaz de pagos y calificaciones
		IPagosCalificaciones pagosCalificaciones = new PagosCalificacionesImpl();

		// creamos un ojbeto seleccionMenu para posteriormente crear una factura
		Factura factura = new Factura();

		try {

			// selecci�n de 1�, 2� y postre
			factura.setPrimero(new Plato("sopa de pollo", "sopa de pollo con fideos"));
			factura.setSegundo(new Plato("lasa�a", "lasa�a bolo�esa"));
			factura.setPostre(new Plato("flan", "flan de huevo con caramelo y nata"));

			// bebida
			factura.setBebida(Bebida.valueOf("AGUA"));

			// creaci�n de factura
			pagosCalificaciones.crearFactura(factura);

			// valoraci�n de un plato
			pagosCalificaciones.calificarPlato(new Plato("sopa de pollo", "sopa de pollo con fideos"), 5);

		} catch (ValoracionIlegalException | PlatoIlegalException e) {

			e.printStackTrace();
		}
	}

}
