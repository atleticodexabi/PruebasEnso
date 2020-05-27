package pojos;

import java.util.Date;

public class Factura {
	int id;
	Bandeja bandeja;
	Vale vale;
	Plato primero;
	Plato segundo;
	Plato postre;
	Bebida bebida;
	boolean valoradoPrimero, valoradoSegundo, valoradoPostre;
	Date inicio;
	Date fin;

	public int getId() {
		return id;
	}

	public Bandeja getBandeja() {
		return bandeja;
	}

	public Vale getVale() {
		return vale;
	}

	public Plato getPrimero() {
		return primero;
	}

	public Plato getSegundo() {
		return segundo;
	}

	public Plato getPostre() {
		return postre;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setPrimero(Plato primero) {
		this.primero = primero;
	}

	public void setSegundo(Plato segundo) {
		this.segundo = segundo;
	}

	public void setPostre(Plato postre) {
		this.postre = postre;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public boolean isValoradoPrimero() {
		return valoradoPrimero;
	}

	public boolean isValoradoSegundo() {
		return valoradoPrimero;
	}

	public boolean isValoradoPostre() {
		return valoradoPrimero;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public void setVale(Vale vale) {
		this.vale = vale;
	}

	public void setBandeja(Bandeja bandeja) {
		this.bandeja = bandeja;
	}

	public Factura() {
		this.id = -1;
	}

	public Factura(Plato primero, Plato segundo, Plato postre, Bebida bebida) {
		this.id = -1;
		this.primero = primero;
		this.segundo = segundo;
		this.postre = postre;
		this.bebida = bebida;
		this.valoradoPrimero = false;
		this.valoradoSegundo = false;
		this.valoradoPostre = false;
		this.inicio = new Date();
	}

	public Factura(int idFactura, Bandeja bandeja, Vale vale, Plato primero, Plato segundo, Plato postre, Bebida bebida,
			boolean valoradoPrimero, boolean valoradoSegundo, boolean valoradoPostre, Date inicio, Date fin) {
		this.id = idFactura;
		this.bandeja = bandeja;
		this.vale = vale;
		this.primero = primero;
		this.segundo = segundo;
		this.postre = postre;
		this.bebida = bebida;

		this.valoradoPrimero = valoradoPrimero;
		this.valoradoSegundo = valoradoSegundo;
		this.valoradoPostre = valoradoPostre;
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public String toString() {
		String result = "";
		result += "Factura: " + (id != -1 ? id : "sin id de factura");
		result += ", Fecha: " + (inicio != null ? inicio.toString() : "Sin fecha de inicio");
		result += "\nVale:" + (vale != null ? vale.getId() : "sin vale asociado");
		result += ", Bandeja:" + (bandeja != null ? bandeja.getId() : "sin bandeja asociada");

		result += "\nSelección del menú:\n";

		result += "primero:\n" + primero.toString();
		result += "segundo:\n" + segundo.toString();
		result += "postre:\n" + postre.toString();
		result += "bebida: " + bebida.toString();

		result += "\nPrimero valorado: " + (valoradoPrimero ? "Sí" : "No");
		result += ", Segundo valorado: " + (valoradoSegundo ? "Sí" : "No");
		result += ", Postre valorado: " + (valoradoPostre ? "Sí" : "No");
		result += "\nFinalización: " + (fin == null ? "No finalizada" : fin.toString()) + "\n";
		return result;

	}

}
