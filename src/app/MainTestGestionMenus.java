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

		// instanciamos la interfaz de gestion de men�s
		IGestionMenus gestionMenus = new GestionMenusImpl();

		// CREACI�N DE UN MEN�

		// En primer lugar creamos las listas de primeros, segundos y postres
		ArrayList<Plato> primeros, segundos, postres;
		primeros = new ArrayList<Plato>();
		segundos = new ArrayList<Plato>();
		postres = new ArrayList<Plato>();

		try {

			// A continuaci�n a�adimos platos a las listas de primeros, segundos y postres
			primeros.add(new Plato("tortilla", "tortilla espa�ola"));
			primeros.add(new Plato("crema de verduras", "crema de calabac�n, zanahoria, calabaza y puerro"));
			primeros.add(new Plato("sopa de pollo", "sopa de pollo con fideos"));

			segundos.add(new Plato("lasa�a", "lasa�a bolo�esa"));
			segundos.add(new Plato("ternera guisada", "ternera guisada con guarnici�n de verduras"));
			segundos.add(new Plato("pollo asado", "pollo asado al horno"));

			postres.add(new Plato("flan", "flan de huevo con caramelo y nata"));
			postres.add(new Plato("tarta de queso", "tarta de queso fr�a con mermelada de ar�ndanos"));
			postres.add(new Plato("manzana", "manzana golden"));

			// por �ltimo creamos el men� con una fecha y las listas previamente rellenadas
			gestionMenus.crearMenu(new Date(), primeros, segundos, postres);

		} catch (PlatoIlegalException | MenuIlegalException e) {

			e.printStackTrace();
		}
	}
}
