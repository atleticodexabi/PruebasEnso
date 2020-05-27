package impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import exceptions.MenuIlegalException;
import exceptions.MenuNoExistenteException;
import interfaces.IGestionMenus;
import pojos.Menu;
import pojos.Plato;

public class GestionMenusImpl implements IGestionMenus {
	GestionDatosImpl datos;

	public GestionMenusImpl() {
		datos = new GestionDatosImpl();
	}

	
	@Override
	public ArrayList<Menu> getMenus() {
		return (ArrayList<Menu>) this.datos.getMenus();
	}

	@Override
	public void crearMenu(Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres)
			throws MenuIlegalException {
		ArrayList<Menu> menus = this.getMenus();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		for (Menu menu : menus) {
			if (dateFormat.format(menu.getFecha()).compareTo(dateFormat.format(fecha)) == 0) {
				throw new MenuIlegalException("Ya existe un menú para la fecha indicada");
			}
		}

		if (primeros.size() == 3 && segundos.size() == 3 && postres.size() == 3 && fecha != null
				&& !fecha.after(new Date())) {
			this.datos.crearMenu(new Menu(fecha, primeros, segundos, postres));
		} else {
			throw new MenuIlegalException("Definición incorrecta al crear el menú");
		}
	}

	@Override
	public void modificarMenu(Menu menu) throws MenuIlegalException, MenuNoExistenteException {
		if (menu.getPrimeros().size() != 3 && menu.getSegundos().size() != 3 && menu.getPostres().size() != 3
				&& menu.getFecha() != null && menu != null) {
			this.datos.modificarMenu(menu);
		} else {
			throw new MenuIlegalException("Definición incorrecta al modificar el menú");
		}
	}

	@Override
	public void borrarMenu(Menu menu) throws MenuNoExistenteException {
		if (menu != null && menu.getFecha() != null) {
			this.datos.borrarMenu(menu);
		} else {
			throw new MenuNoExistenteException("El menú indicado no tiene fecha");
		}
	}
}
