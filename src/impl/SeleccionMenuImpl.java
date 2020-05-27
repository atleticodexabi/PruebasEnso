package impl;

import interfaces.IPagosCalificaciones;
import interfaces.ISeleccionMenu;
import pojos.Bebida;
import pojos.Factura;
import pojos.Plato;

public class SeleccionMenuImpl implements ISeleccionMenu {
	Factura factura;
	IPagosCalificaciones pagosCalificaciones;

	public SeleccionMenuImpl() {
		factura = new Factura();
		pagosCalificaciones = new PagosCalificacionesImpl();

	}

	@Override
	public Plato getPrimero() {
		return this.factura.getPrimero();
	}

	@Override
	public Plato getSegundo() {
		return this.factura.getSegundo();
	}

	@Override
	public Plato getPostre() {
		return this.factura.getPostre();
	}

	@Override
	public Bebida getBebida() {
		return this.factura.getBebida();
	}

	@Override
	public void setPrimero(Plato primero) {
		this.factura.setPrimero(primero);
	}

	@Override
	public void setSegundo(Plato segundo) {
		this.factura.setSegundo(segundo);
	}

	@Override
	public void setPostre(Plato postre) {
		this.factura.setPostre(postre);
	}

	@Override
	public void setBebida(Bebida bebida) {
		this.factura.setBebida(bebida);
	}

	@Override
	public Factura confirmarMenu() {
		if (this.factura.getPrimero() != null && this.factura.getSegundo() != null && this.factura.getPostre() != null
				&& this.factura.getBebida().toString().compareTo("") != 0 && this.factura.getBebida() != null) {
			return this.pagosCalificaciones.crearFactura(factura);
		}
		return null;
	}
}
