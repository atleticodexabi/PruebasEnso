package interfaces;

import pojos.Bebida;
import pojos.Factura;
import pojos.Plato;

public interface ISeleccionMenu {
	Plato getPrimero();

	Plato getSegundo();

	Plato getPostre();

	Bebida getBebida();

	void setPrimero(Plato primero);

	void setSegundo(Plato segundo);

	void setPostre(Plato postre);

	void setBebida(Bebida bebida);

	Factura confirmarMenu();

}
