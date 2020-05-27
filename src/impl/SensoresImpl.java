package impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import interfaces.ISensores;
import pojos.Bandeja;
import pojos.Factura;
import pojos.Vale;

public class SensoresImpl implements ISensores {

	Random rand;

	public SensoresImpl() {
		rand = new Random();
	}

	@Override
	public void leerVale(Factura factura) {
		factura.setVale(new Vale(rand.nextInt(1000) + 100, true));
	}

	@Override
	public void leerBandeja(Factura factura) {

		factura.setBandeja(new Bandeja(rand.nextInt(1000) + 100, true));

		Date inicio = new Date();
		factura.setInicio(inicio);
		// simulamos una duración entre 1 y 10 minutos
		int duracion = (rand.nextInt((10 - 1) + 1) + 1) * 1000 * 60;

		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		Date fechaDespues = new Date(t + duracion);

		factura.setFin(fechaDespues);
	}

}
