package pojos;

public class Bandeja {
	int id;
	boolean estado;

	public int getId() {
		return id;
	}

	public boolean isEstado() {
		return estado;
	}

	public Bandeja(int id, boolean estado) {
		this.id = id;
		this.estado = estado;
	}

	public Bandeja(int id) {
		this.id = id;
		this.estado = false;
	}

	@Override
	public String toString() {
		return "Bandeja [id=" + id + ", estado=" + estado + "]";
	}

}
