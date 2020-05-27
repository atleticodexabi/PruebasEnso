package pojos;

public class Valoracion {
	int id;
	Plato plato;

	int valoracion;

	public int getId() {
		return id;
	}

	public Plato getPlato() {
		return plato;
	}

	public int getValoracion() {
		return valoracion;
	}

	public Valoracion(int id, Plato plato, int valoracion) {
		this.id = id;
		this.plato = plato;
		this.valoracion = valoracion;
	}

	public Valoracion(Plato plato, int valoracion) {
		this.plato = plato;
		this.valoracion = valoracion;
	}

	@Override
	public String toString() {
		return "Id:" + id + "\n" + plato + "Valoración: " + valoracion + "\n";
	}

	public int compareTo(Valoracion v) {

		if (v.getValoracion() > this.valoracion) {
			return -1;
		} else if (v.getValoracion() < this.valoracion) {
			return 0;
		}
		return -1;
	}

}
