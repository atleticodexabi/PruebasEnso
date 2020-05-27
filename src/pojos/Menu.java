package pojos;

import java.util.ArrayList;
import java.util.Date;

public class Menu {

	Date fecha;
	ArrayList<Plato> primeros;
	ArrayList<Plato> segundos;
	ArrayList<Plato> postres;

	public Date getFecha() {
		return fecha;
	}

	public ArrayList<Plato> getPrimeros() {
		return primeros;
	}

	public ArrayList<Plato> getSegundos() {
		return segundos;
	}

	public ArrayList<Plato> getPostres() {
		return postres;
	}

	public Menu(Date fecha, ArrayList<Plato> primeros, ArrayList<Plato> segundos, ArrayList<Plato> postres) {
		this.fecha = fecha;
		this.primeros = primeros;
		this.segundos = segundos;
		this.postres = postres;
	}

	@Override
	public String toString() {
		return "Menú del día: " + fecha.toString() + "\nPRIMEROS:\n" + primeros.get(0) + primeros.get(1)
				+ primeros.get(2) + "\n" + "\nSEGUNDOS:\n" + segundos.get(0) + segundos.get(1) + segundos.get(2) + "\n"
				+ "\nPOSTRES:\n" + postres.get(0) + postres.get(1) + postres.get(2) + "\n";
	}
}
