package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread {

	private int idAviao;
	private Semaphore semaforoNorte;
	private Semaphore semaforoSul;
	private int idPista;

	public ThreadAeroporto (int idAviao, Semaphore semaforoNorte, Semaphore semaforoSul, int idPista) {
		this.idAviao = idAviao;
		this.semaforoNorte = semaforoNorte;
		this.semaforoSul = semaforoSul;
		this.idPista = idPista;
	}

	@Override
	public void run() {
		manobrar();
		taxiar();
		try {
			if (idPista == 0) {
				semaforoNorte.acquire();
			} else {
				semaforoSul.acquire();
			}
			decolagem();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (idPista == 0) {
				semaforoNorte.release();
			} else {
				semaforoSul.release();
			}
			afastamento();
		}
	}

	private void manobrar() {
		System.out.println("Avião " + idAviao + " manobrando");
		int tempo = (int) ((Math.random() * 401) + 300);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void taxiar() {
		System.out.println("Avião " + idAviao + " taxiando");
		int tempo = (int) ((Math.random() * 501) + 500);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void decolagem() {
		String pista = "";
		if (idPista == 0) {
			pista = "Norte";
		} else {
			pista = "Sul";
		}
		System.out.println("Avião " + idAviao + " decolando na pista "+ pista);
		int tempo = (int) ((Math.random() * 301) + 100);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void afastamento() {
		System.out.println("Avião " + idAviao + " afastando do aeroporto");
		int tempo = (int) ((Math.random() * 501) + 300);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
