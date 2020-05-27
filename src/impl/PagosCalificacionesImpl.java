package impl;

import exceptions.ValoracionIlegalException;
import interfaces.IGestionDatos;
import interfaces.IPagosCalificaciones;
import interfaces.ISensores;
import pojos.Factura;
import pojos.Plato;
import pojos.Valoracion;

public class PagosCalificacionesImpl implements IPagosCalificaciones {
	IGestionDatos datos;
	ISensores sensores;

	public PagosCalificacionesImpl() {
		this.datos = new GestionDatosImpl();
		this.sensores = new SensoresImpl();

	}

	@Override
	public Factura crearFactura(Factura factura) {
		this.sensores.leerVale(factura);
		this.sensores.leerBandeja(factura);
		this.datos.crearFactura(factura);
		return factura;
	}

	
	@Override
	public void calificarPlato(Plato plato, int calificacion) throws ValoracionIlegalException {
		if (calificacion < 1 || calificacion > 10) {
			throw new ValoracionIlegalException("Valor incorrecto para la calificación: " + calificacion);
		}
		this.datos.crearValoracion(new Valoracion(plato, calificacion));
	}
}
