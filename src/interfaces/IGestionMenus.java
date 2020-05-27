package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exceptions.MenuIlegalException;
import exceptions.MenuNoExistenteException;
import pojos.Menu;
import pojos.Plato;

public interface IGestionMenus {

	public List<Menu> getMenus();

	public void crearMenu(Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres)
			throws MenuIlegalException;

	public void modificarMenu(Menu menu) throws MenuIlegalException, MenuNoExistenteException;

	public void borrarMenu(Menu menu) throws MenuNoExistenteException;
}
