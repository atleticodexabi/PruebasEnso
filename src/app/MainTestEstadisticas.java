package app;

import java.util.ArrayList;

import impl.AnalisisEstadisticasImpl;
import interfaces.IAnalisisEstadisticas;
import pojos.Plato;

public class MainTestEstadisticas {
	public static void main(String[] args) {

		// instanciamos la interfaz de estadísticas
		IAnalisisEstadisticas estadisticas = new AnalisisEstadisticasImpl();

		
		// probamos los métodos de las estadísticas:
		System.out.println("Plato mejor puntuado: " + estadisticas.getPlatoMejorPuntuado().getTitulo());
		System.out.println("Plato peor puntuado: " + estadisticas.getPlatoPeorPuntuado().getTitulo());
		System.out.println("Plato más seleccionado: " + estadisticas.getPlatoMasSeleccionado().getTitulo());
		System.out.println("Plato menos seleccionado: " + estadisticas.getPlatoMenosSeleccionado().getTitulo());
		System.out.println("Ocupación actual del comedor: " + estadisticas.getOcupacionComedor());
		System.out.println(
				"Tiempo medio de duración de comida: " + estadisticas.getTiempoMedioDuracionComida() + " minutos");
		System.out.println("Ránking de platos según su valoración media:");

		ArrayList<Plato> rankingPlatos = (ArrayList<Plato>) estadisticas.getRankingPlatos();
		int i = 0;

		for (Plato p : rankingPlatos) {
			i++;
			System.out.println("\t" + i + ". " + p.getTitulo());
		}

		System.out.println("Hora más frecuente los lunes: :" + estadisticas.getHoraMasFrecuentePorDia(1));
		System.out.println("Hora más frecuente los martes: :" + estadisticas.getHoraMasFrecuentePorDia(2));
		System.out.println("Hora más frecuente los miércoles: :" + estadisticas.getHoraMasFrecuentePorDia(3));
		System.out.println("Hora más frecuente los jueves: :" + estadisticas.getHoraMasFrecuentePorDia(4));
		System.out.println("Hora más frecuente los viernes: :" + estadisticas.getHoraMasFrecuentePorDia(5));
		System.out.println("Hora más frecuente los sábado: :" + estadisticas.getHoraMasFrecuentePorDia(6));
		System.out.println("Hora más frecuente los domingo: :" + estadisticas.getHoraMasFrecuentePorDia(7));

		System.out.println("Ocupación el miércoles a las 16:00 -> " + estadisticas.getOcupacionPorHoraYDia(16, 3)
				+ " comensal(es)");

	}
}
