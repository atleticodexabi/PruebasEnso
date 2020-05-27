package pojos;

public class Vale {
	int id;
	boolean usado;

	public int getId() {
		return id;
	}

	public boolean isUsado() {
		return usado;
	}

	public Vale(int id) {
		this.id = id;
		this.usado = false;
	}

	public Vale(int id, boolean estado) {
		this.id = id;
		this.usado = estado;
	}

	@Override
	public String toString() {
		return "Bandeja [id=" + id + ", estado=" + usado + "]";
	}

}
