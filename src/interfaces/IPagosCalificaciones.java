package interfaces;

import exceptions.ValoracionIlegalException;
import pojos.Factura;
import pojos.Plato;

public interface IPagosCalificaciones {

	Factura crearFactura(Factura factura);

	void calificarPlato(Plato plato, int calificacion) throws ValoracionIlegalException;
}
