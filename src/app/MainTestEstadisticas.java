package app;

import java.util.ArrayList;

import impl.AnalisisEstadisticasImpl;
import interfaces.IAnalisisEstadisticas;
import pojos.Plato;

public class MainTestEstadisticas {
	public static void main(String[] args) {

		// instanciamos la interfaz de estad�sticas
		IAnalisisEstadisticas estadisticas = new AnalisisEstadisticasImpl();

		
		// probamos los m�todos de las estad�sticas:
		System.out.println("Plato mejor puntuado: " + estadisticas.getPlatoMejorPuntuado().getTitulo());
		System.out.println("Plato peor puntuado: " + estadisticas.getPlatoPeorPuntuado().getTitulo());
		System.out.println("Plato m�s seleccionado: " + estadisticas.getPlatoMasSeleccionado().getTitulo());
		System.out.println("Plato menos seleccionado: " + estadisticas.getPlatoMenosSeleccionado().getTitulo());
		System.out.println("Ocupaci�n actual del comedor: " + estadisticas.getOcupacionComedor());
		System.out.println(
				"Tiempo medio de duraci�n de comida: " + estadisticas.getTiempoMedioDuracionComida() + " minutos");
		System.out.println("R�nking de platos seg�n su valoraci�n media:");

		ArrayList<Plato> rankingPlatos = (ArrayList<Plato>) estadisticas.getRankingPlatos();
		int i = 0;

		for (Plato p : rankingPlatos) {
			i++;
			System.out.println("\t" + i + ". " + p.getTitulo());
		}

		System.out.println("Hora m�s frecuente los lunes: :" + estadisticas.getHoraMasFrecuentePorDia(1));
		System.out.println("Hora m�s frecuente los martes: :" + estadisticas.getHoraMasFrecuentePorDia(2));
		System.out.println("Hora m�s frecuente los mi�rcoles: :" + estadisticas.getHoraMasFrecuentePorDia(3));
		System.out.println("Hora m�s frecuente los jueves: :" + estadisticas.getHoraMasFrecuentePorDia(4));
		System.out.println("Hora m�s frecuente los viernes: :" + estadisticas.getHoraMasFrecuentePorDia(5));
		System.out.println("Hora m�s frecuente los s�bado: :" + estadisticas.getHoraMasFrecuentePorDia(6));
		System.out.println("Hora m�s frecuente los domingo: :" + estadisticas.getHoraMasFrecuentePorDia(7));

		System.out.println("Ocupaci�n el mi�rcoles a las 16:00 -> " + estadisticas.getOcupacionPorHoraYDia(16, 3)
				+ " comensal(es)");

	}
}
