package impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.FacturaNoExistenteException;
import exceptions.MenuNoExistenteException;
import exceptions.PlatoIlegalException;
import interfaces.IGestionDatos;
import pojos.Bandeja;
import pojos.Bebida;
import pojos.Factura;
import pojos.Menu;
import pojos.Plato;
import pojos.Vale;
import pojos.Valoracion;

public class GestionDatosImpl implements IGestionDatos {

	private String buscarNombreEnPlatos(ArrayList<Plato> platos, String titulo) {
		for (Plato plato : platos) {
			if (plato.getTitulo().equals(titulo)) {
				return plato.getDescripcion();
			}
		}
		return "";

	}

	private void borrarArchivo(String path) {
		Writer output;
		try {
			output = new BufferedWriter(new FileWriter(path));
			output.append("");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Plato> getPlatos() {
		ArrayList<Plato> platos = new ArrayList<>();
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream("platos.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;

			while ((strLine = br.readLine()) != null) {
				String[] campos = strLine.split(";");
				try {
					platos.add(new Plato(campos[0], campos[1]));
				} catch (PlatoIlegalException e) {
					e.printStackTrace();
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return platos;
	}

	@Override
	public List<Menu> getMenus() {
		String strLine;
		ArrayList<Menu> menus = new ArrayList<>();
		ArrayList<Plato> primeros = null;
		ArrayList<Plato> segundos = null;
		ArrayList<Plato> postres = null;
		FileInputStream fstream = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<Plato> platos = (ArrayList<Plato>) this.getPlatos();

		try {
			fstream = new FileInputStream("menus.txt");

			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			while ((strLine = br.readLine()) != null) {
				String[] campos = strLine.split(";");

				primeros = new ArrayList<>();
				primeros.add(new Plato(campos[1], buscarNombreEnPlatos(platos, campos[1])));
				primeros.add(new Plato(campos[2], buscarNombreEnPlatos(platos, campos[2])));
				primeros.add(new Plato(campos[3], buscarNombreEnPlatos(platos, campos[3])));

				segundos = new ArrayList<>();
				segundos.add(new Plato(campos[4], buscarNombreEnPlatos(platos, campos[4])));
				segundos.add(new Plato(campos[5], buscarNombreEnPlatos(platos, campos[5])));
				segundos.add(new Plato(campos[6], buscarNombreEnPlatos(platos, campos[6])));

				postres = new ArrayList<>();
				postres.add(new Plato(campos[7], buscarNombreEnPlatos(platos, campos[7])));
				postres.add(new Plato(campos[8], buscarNombreEnPlatos(platos, campos[8])));
				postres.add(new Plato(campos[9], buscarNombreEnPlatos(platos, campos[9])));

				menus.add(new Menu(dateFormat.parse(campos[0]), primeros, segundos, postres));

			}
			br.close();
		} catch (IOException | ParseException | PlatoIlegalException e) {
			e.printStackTrace();
		}
		return menus;
	}

	@Override
	public List<Valoracion> getValoraciones() {
		ArrayList<Valoracion> valoraciones = new ArrayList<>();
		FileInputStream fstream = null;
		String strLine;
		try {
			fstream = new FileInputStream("valoraciones.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			ArrayList<Plato> platos = (ArrayList<Plato>) this.getPlatos();

			while ((strLine = br.readLine()) != null) {
				String[] campos = strLine.split(";");

				Plato plato = new Plato(campos[1], buscarNombreEnPlatos(platos, campos[1]));
				Valoracion valoracion = new Valoracion(Integer.parseInt(campos[0]), plato, Integer.parseInt(campos[2]));
				valoraciones.add(valoracion);
			}
			br.close();
		} catch (IOException | PlatoIlegalException e) {
			e.printStackTrace();
		}
		return valoraciones;
	}

	@Override
	public List<Factura> getFacturas() {
		ArrayList<Factura> facturas = new ArrayList<>();
		FileInputStream fstream = null;
		String strLine;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		ArrayList<Plato> platos = (ArrayList<Plato>) this.getPlatos();
		try {
			fstream = new FileInputStream("facturas.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			while ((strLine = br.readLine()) != null) {
				String[] campos = strLine.split(";");

				int idFactura = Integer.parseInt(campos[0]);
				Bandeja bandeja = new Bandeja(Integer.parseInt(campos[1]), false);
				Vale vale = new Vale(Integer.parseInt(campos[2]), false);

				Plato primero = new Plato(campos[3], buscarNombreEnPlatos(platos, campos[3]));
				Plato segundo = new Plato(campos[4], buscarNombreEnPlatos(platos, campos[4]));
				Plato postre = new Plato(campos[5], buscarNombreEnPlatos(platos, campos[5]));

				Factura factura = new Factura(idFactura, bandeja, vale, primero, segundo, postre,
						Bebida.valueOf(campos[6]), Boolean.parseBoolean(campos[7]), Boolean.parseBoolean(campos[8]),
						Boolean.parseBoolean(campos[9]), dateFormat.parse(campos[10]),
						(campos.length == 12 ? dateFormat.parse(campos[11]) : null));
				facturas.add(factura);
			}
			br.close();
		} catch (IOException | NumberFormatException | ParseException | PlatoIlegalException e) {
			e.printStackTrace();
		}
		return facturas;
	}

	@Override
	public void crearMenu(Menu menu) {

		Writer output;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {

			output = new BufferedWriter(new FileWriter("menus.txt", true));
			String primeros = "", segundos = "", postres = "", fecha = "";

			for (int i = 0; i < menu.getPrimeros().size(); i++) {
				primeros += menu.getPrimeros().get(i).getTitulo() + ";";
			}
			for (int i = 0; i < menu.getSegundos().size(); i++) {
				segundos += menu.getSegundos().get(i).getTitulo() + ";";
			}
			for (int i = 0; i < menu.getPostres().size(); i++) {
				postres += menu.getPostres().get(i).getTitulo() + ";";
			}
			postres = postres.substring(0, postres.length() - 1);
			fecha = dateFormat.format(menu.getFecha());
			output.append(fecha + ";" + primeros + segundos + postres + "\n");
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	@Override
	public int crearFactura(Factura factura) {
		Writer output;
		String result = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		ArrayList<Factura> facturas = (ArrayList<Factura>) this.getFacturas();
		int id = 1;

		// ordenamos la lista de facturas
		Collections.sort(facturas, new Comparator<Factura>() {
			@Override
			public int compare(Factura v1, Factura v2) {
				if (v1.getId() > v2.getId()) {
					return 1;
				} else if (v1.getId() < v2.getId()) {
					return -1;
				}
				return 0;
			}
		});

		if (!facturas.isEmpty()) {
			factura.setId((facturas.get(facturas.size() - 1).getId() + 1));
		}
		// guardamos en archivo
		try {
			output = new BufferedWriter(new FileWriter("facturas.txt", true));
			result += factura.getId() + ";";
			result += factura.getBandeja().getId() + ";";
			result += factura.getVale().getId() + ";";
			result += factura.getPrimero().getTitulo() + ";";
			result += factura.getSegundo().getTitulo() + ";";
			result += factura.getPostre().getTitulo() + ";";
			result += factura.getBebida().toString() + ";";
			result += factura.isValoradoPrimero() + ";";
			result += factura.isValoradoSegundo() + ";";
			result += factura.isValoradoPostre() + ";";
			result += dateFormat.format(factura.getInicio()) + ";";
			result += (factura.getFin() != null ? dateFormat.format(factura.getFin()) : "");
			output.append(result + "\n");
			output.close();
			return id;
		} catch (IOException e) {
			e.printStackTrace();
			// si hay un error devolvemos -1
			return -1;
		}
	}

	@Override
	public int crearValoracion(Valoracion valoracion) {
		Writer output;
		String result = "";
		int id = 1;
		ArrayList<Valoracion> valoraciones = (ArrayList<Valoracion>) this.getValoraciones();

		// ordenamos la lista de valoraciones
		Collections.sort(valoraciones, new Comparator<Valoracion>() {
			@Override
			public int compare(Valoracion v1, Valoracion v2) {
				if (v1.getId() > v2.getId()) {
					return 1;
				} else if (v1.getId() < v2.getId()) {
					return -1;
				}
				return 0;
			}
		});

		// obtenemos el id más alto y le sumamos uno
		id = valoraciones.get(valoraciones.size() - 1).getId() + 1;

		try {
			output = new BufferedWriter(new FileWriter("valoraciones.txt", true));
			result += id + ";";
			result += valoracion.getPlato().getTitulo() + ";";
			result += valoracion.getValoracion();
			output.append(result + "\n");
			output.close();
			return id;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void borrarMenu(Menu menu) throws MenuNoExistenteException {
		ArrayList<Menu> menus = (ArrayList<Menu>) this.getMenus();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		int delete = -1;
		for (int i = 0; i < menus.size(); i++) {
			if (dateFormat.format(menus.get(i).getFecha()).compareTo(dateFormat.format(menu.getFecha())) == 0) {
				delete = i;
				break;
			}
		}
		if (delete == -1) {
			throw new MenuNoExistenteException("No se ha encontrado menú para la fecha: " + menu.getFecha().toString());
		}
		menus.remove(delete);
		this.borrarArchivo("menus.txt");
		for (int i = 0; i < menus.size(); i++) {
			this.crearMenu(menus.get(i));
		}
	}

	@Override
	public void borrarFactura(Factura factura) throws FacturaNoExistenteException {
		ArrayList<Factura> facturas = (ArrayList<Factura>) this.getFacturas();
		int delete = -1;
		for (int i = 0; i < facturas.size(); i++) {
			if (facturas.get(i).getId() == factura.getId()) {
				delete = i;
				break;
			}
		}
		if (delete == -1) {
			throw new FacturaNoExistenteException("No se ha encontrado factura con id: " + factura.getId());
		}
		facturas.remove(delete);
		this.borrarArchivo("facturas.txt");
		for (int i = 0; i < facturas.size(); i++) {
			this.crearFactura(facturas.get(i));
		}
	}

	@Override
	public void modificarMenu(Menu menu) throws MenuNoExistenteException {
		this.borrarMenu(menu);
		this.crearMenu(menu);
	}

	@Override
	public void modificarFactura(Factura factura) throws FacturaNoExistenteException {
		this.borrarFactura(factura);
		this.crearFactura(factura);
	}

}
