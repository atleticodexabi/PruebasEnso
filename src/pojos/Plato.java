package pojos;

import exceptions.PlatoIlegalException;

public class Plato {
	private String titulo;
	private String descripcion;

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Plato(String titulo, String descripcion) throws PlatoIlegalException {

		if (titulo.length() > 30 || descripcion.length() > 255) {
			throw new PlatoIlegalException(
					"restricciones para titulo y/o descripción incumplidas máx. 30 y 255 respectivamente");
		}
		this.titulo = titulo;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "título: " + titulo + "\ndescripción: " + descripcion + "\n";
	}

	@Override
	public int hashCode() {
		return (this.titulo + this.descripcion).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plato other = (Plato) obj;
		if (this.titulo.compareTo(other.getTitulo()) != 0)
			return false;
		return true;
	}

}
