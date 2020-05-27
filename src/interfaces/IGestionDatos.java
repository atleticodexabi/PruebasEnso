package interfaces;

import java.util.List;

import exceptions.FacturaNoExistenteException;
import exceptions.MenuNoExistenteException;
import pojos.Factura;
import pojos.Menu;
import pojos.Plato;
import pojos.Valoracion;

public interface IGestionDatos {
	List<Plato> getPlatos();

	List<Menu> getMenus();

	List<Valoracion> getValoraciones();

	List<Factura> getFacturas();

	void crearMenu(Menu menu);

	int crearFactura(Factura factura);

	int crearValoracion(Valoracion valoracion);

	public void borrarMenu(Menu menu) throws MenuNoExistenteException;

	void borrarFactura(Factura factura) throws FacturaNoExistenteException;

	void modificarMenu(Menu menu) throws MenuNoExistenteException;

	void modificarFactura(Factura factura) throws FacturaNoExistenteException;

}
