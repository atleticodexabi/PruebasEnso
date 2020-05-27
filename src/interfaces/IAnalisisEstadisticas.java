package interfaces;

import java.util.List;

import pojos.Plato;

public interface IAnalisisEstadisticas {

	public Plato getPlatoMejorPuntuado();

	public Plato getPlatoPeorPuntuado();

	public Plato getPlatoMasSeleccionado();

	public Plato getPlatoMenosSeleccionado();

	public int getOcupacionComedor();

	public List<Plato> getRankingPlatos();

	public double getTiempoMedioDuracionComida();

	public int getHoraMasFrecuentePorDia(int dia);

	public int getOcupacionPorHoraYDia(int hora, int dia);

}
