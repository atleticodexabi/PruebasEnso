package app;

import java.util.ArrayList;
import java.util.Date;
import exceptions.MenuIlegalException;
import exceptions.PlatoIlegalException;
import impl.GestionMenusImpl;
import interfaces.IGestionMenus;
import pojos.Plato;

public class MainTestGestionMenus {
	public static void main(String[] args) {

		// instanciamos la interfaz de gestion de menús
		IGestionMenus gestionMenus = new GestionMenusImpl();

		// CREACIÓN DE UN MENÚ

		// En primer lugar creamos las listas de primeros, segundos y postres
		ArrayList<Plato> primeros, segundos, postres;
		primeros = new ArrayList<Plato>();
		segundos = new ArrayList<Plato>();
		postres = new ArrayList<Plato>();

		try {

			// A continuación añadimos platos a las listas de primeros, segundos y postres
			primeros.add(new Plato("tortilla", "tortilla española"));
			primeros.add(new Plato("crema de verduras", "crema de calabacín, zanahoria, calabaza y puerro"));
			primeros.add(new Plato("sopa de pollo", "sopa de pollo con fideos"));

			segundos.add(new Plato("lasaña", "lasaña boloñesa"));
			segundos.add(new Plato("ternera guisada", "ternera guisada con guarnición de verduras"));
			segundos.add(new Plato("pollo asado", "pollo asado al horno"));

			postres.add(new Plato("flan", "flan de huevo con caramelo y nata"));
			postres.add(new Plato("tarta de queso", "tarta de queso fría con mermelada de arándanos"));
			postres.add(new Plato("manzana", "manzana golden"));

			// por último creamos el menú con una fecha y las listas previamente rellenadas
			gestionMenus.crearMenu(new Date(), primeros, segundos, postres);

		} catch (PlatoIlegalException | MenuIlegalException e) {

			e.printStackTrace();
		}
	}
}
