package impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import interfaces.IAnalisisEstadisticas;
import interfaces.IGestionDatos;
import pojos.Factura;
import pojos.Plato;
import pojos.Valoracion;

public class AnalisisEstadisticasImpl implements IAnalisisEstadisticas {

	IGestionDatos datos;

	public AnalisisEstadisticasImpl() {
		datos = new GestionDatosImpl();
	}

	@Override
	public Plato getPlatoMejorPuntuado() {
		Valoracion mejor = null;

		// obtenemos las valoraciones
		ArrayList<Valoracion> valoraciones = (ArrayList<Valoracion>) datos.getValoraciones();

		// comprobamos que existan valoraciones
		if (valoraciones.size() > 0) {
			mejor = valoraciones.get(0);
		}

		// recorremos las valoraciones
		for (int i = 1; i < valoraciones.size(); i++) {
			if (valoraciones.get(i).getValoracion() > mejor.getValoracion()) {
				mejor = valoraciones.get(i);
			}
		}

		if (mejor != null) {
			return mejor.getPlato();
		} else {
			return null;
		}
	}

	@Override
	public Plato getPlatoPeorPuntuado() {
		Valoracion peor = null;

		ArrayList<Valoracion> valoraciones = (ArrayList<Valoracion>) datos.getValoraciones();

		// comprobamos que existan valoraciones
		if (valoraciones.size() > 0) {
			peor = valoraciones.get(0);
		}

		for (int i = 1; i < valoraciones.size(); i++) {
			if (valoraciones.get(i).getValoracion() < peor.getValoracion()) {
				peor = valoraciones.get(i);
			}
		}

		if (peor != null) {
			return peor.getPlato();
		} else {
			return null;
		}
	}

	private HashMap<Plato, Integer> obtenerFrecuencias() {
		ArrayList<Factura> facturas = (ArrayList<Factura>) datos.getFacturas();
		HashMap<Plato, Integer> frequencyMap = new HashMap<Plato, Integer>();
		for (Factura f : facturas) {
			frequencyMap.put(f.getPrimero(),
					(frequencyMap.get(f.getPrimero()) == null ? 1 : frequencyMap.get(f.getPrimero()) + 1));

			frequencyMap.put(f.getSegundo(),
					(frequencyMap.get(f.getSegundo()) == null ? 1 : frequencyMap.get(f.getSegundo()) + 1));

			frequencyMap.put(f.getPostre(),
					(frequencyMap.get(f.getPostre()) == null ? 1 : frequencyMap.get(f.getPostre()) + 1));
		}

		return frequencyMap;

	}

	@Override
	public Plato getPlatoMasSeleccionado() {
		HashMap<Plato, Integer> frecuencias = this.obtenerFrecuencias();
		Plato resultado = null;
		int frecuencia = 0;

		for (Map.Entry<Plato, Integer> entry : frecuencias.entrySet()) {
			if (entry.getValue() > frecuencia) {
				frecuencia = entry.getValue();
				resultado = entry.getKey();
			}
		}
		return resultado;
	}

	@Override
	public Plato getPlatoMenosSeleccionado() {

		HashMap<Plato, Integer> frecuencias = this.obtenerFrecuencias();
		Plato resultado = null;
		int frecuencia = Integer.MAX_VALUE;

		for (Map.Entry<Plato, Integer> entry : frecuencias.entrySet()) {
			if (entry.getValue() < frecuencia) {
				frecuencia = entry.getValue();
				resultado = entry.getKey();
			}
		}
		return resultado;
	}

	@Override
	public int getOcupacionComedor() {

		ArrayList<Factura> facturas = (ArrayList<Factura>) datos.getFacturas();
		int ocupacion = 0;
		for (Factura factura : facturas) {
			if (factura.getFin() == null) {
				ocupacion++;
			}

		}

		return ocupacion;
	}

	@Override
	public ArrayList<Plato> getRankingPlatos() {

		// obtenemos las valoraciones
		ArrayList<Valoracion> valoraciones = (ArrayList<Valoracion>) this.datos.getValoraciones();

		// hashmap para guardar los platos y la lista de sus valoraciones
		HashMap<Plato, ArrayList<Integer>> ranking = new HashMap<Plato, ArrayList<Integer>>();

		// ArrayList de platos para retornar el resultado
		ArrayList<Plato> resultado = new ArrayList<Plato>();

		// recorremos las valoraciones y las guardamos en un hashmap junto con todas sus
		// notas en un arraylist
		for (Valoracion valoracion : valoraciones) {
			if (ranking.get(valoracion.getPlato()) == null) {
				ranking.put(valoracion.getPlato(), new ArrayList<Integer>());
				ranking.get(valoracion.getPlato()).add(valoracion.getValoracion());
			} else {
				ranking.get(valoracion.getPlato()).add(valoracion.getValoracion());
			}
		}

		// hashmap para guardar los platos y su valoración media
		HashMap<Plato, Double> valoracionesMedias = new HashMap<Plato, Double>();

		// recorremos el hashmap
		for (Map.Entry<Plato, ArrayList<Integer>> entry : ranking.entrySet()) {
			double nota = 0.0;
			// recorremos para cada plato sus notas y asi calcular su nota media
			for (int i = 0; i < entry.getValue().size(); i++) {
				nota += entry.getValue().get(i);
			}
			nota /= entry.getValue().size();

			// añadimos al hashmap de valoraciones medias el plato y su nota media
			valoracionesMedias.put(entry.getKey(), nota);

		}

		// añadimos al arraylist el resultado de la ordenación del hashmap de
		// valoraciones medias
		valoracionesMedias.entrySet().stream().sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
				.forEach(k -> resultado.add(k.getKey()));

		return resultado;
	}

	@Override
	public double getTiempoMedioDuracionComida() {
		ArrayList<Factura> facturas = (ArrayList<Factura>) datos.getFacturas();
		double tiempo = 0;
		int menusEnCurso = 0;

		if (facturas.size() == 0) {
			return -1.0;
		}

		for (Factura f : facturas) {
			if (f.getFin() == null) {
				menusEnCurso++;
			} else {
				tiempo += (f.getFin().getTime() / 1000) - (f.getInicio().getTime() / 1000);
			}
		}

		if (menusEnCurso == facturas.size() || tiempo == 0) {
			return -1;
		} else {
			return (tiempo / (facturas.size() - menusEnCurso)) / 60;
		}

	}

	@Override
	public int getHoraMasFrecuentePorDia(int dia) {
		/*
		 * SUNDAY = 1 MONDAY = 2 TUESDAY = 3 WEDNESDAY = 4 THURSDAY = 5 FRIDAY = 6
		 * SATURDAY = 7
		 */

		if (dia < 1 || dia > 7) {
			return -1;
		}
		// corregimos para nuestra version del calendario (la semana empieza en lunes,
		// no en domingo)
		switch (dia) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			dia++;
			break;
		case 7:
			dia = 1;
			break;
		}
		int frecuencia = -1;
		int hora = -1;

		// obtenemos las facturas
		ArrayList<Factura> facturas = (ArrayList<Factura>) datos.getFacturas();

		// obtenemos una instancia de Calendar para posteriormente descartar por el día
		// y clasificar por hora
		Calendar cal = Calendar.getInstance();

		// obtenemos un hashmap con las frecuencias de las horas del día en las que hay
		// menús en curso para el día recibido por parámetro
		HashMap<Integer, Integer> horas = new HashMap<Integer, Integer>();
		for (Factura f : facturas) {
			cal.setTime(f.getInicio());
			if (cal.get(Calendar.DAY_OF_WEEK) == dia) {
				horas.put(cal.get(Calendar.HOUR_OF_DAY), (horas.get(cal.get(Calendar.HOUR_OF_DAY)) == null ? 1
						: horas.get(cal.get(Calendar.HOUR_OF_DAY)) + 1));
			}
		}

		// obtenemos la hora del día que tiene más ocurrencias en el hashmap
		for (Map.Entry<Integer, Integer> entry : horas.entrySet()) {
			if (entry.getValue() > frecuencia) {
				hora = entry.getKey();
				frecuencia = entry.getValue();
			}
		}
		return hora;
	}

	@Override
	public int getOcupacionPorHoraYDia(int hora, int dia) {

		int resultado = 0;
		if (hora < 0 || hora > 23 || dia < 1 || dia > 7) {
			return -1;
		}
		/*
		 * SUNDAY = 1 MONDAY = 2 TUESDAY = 3 WEDNESDAY = 4 THURSDAY = 5 FRIDAY = 6
		 * SATURDAY = 7
		 */

		// corregimos para nuestra version del calendario (la semana empieza en lunes,
		// no en domingo)
		switch (dia) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			dia++;
			break;
		case 7:
			dia = 1;
			break;

		}

		// obtenemos las facturas
		ArrayList<Factura> facturas = (ArrayList<Factura>) datos.getFacturas();

		// obtenemos una instancia de Calendar para posteriormente descartar por el día
		// y clasificar por hora
		Calendar cal = Calendar.getInstance();

		// recorremos el arraylist de facturas sumando cada ocurrencia filtrada por hora
		// y dia recibidos por parámetro
		for (Factura f : facturas) {
			cal.setTime(f.getInicio());
			if (cal.get(Calendar.DAY_OF_WEEK) == dia && cal.get(Calendar.HOUR_OF_DAY) == hora) {
				resultado++;
			}
		}

		return resultado;
	}

}
