package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforoNorte = new Semaphore(1);
		Semaphore semaforoSul = new Semaphore(1);
		
		for (int idAviao = 0; idAviao < 12 ; idAviao++) {
			int idPista = (int)((Math.random() * 99) + 1);
			idPista = idPista % 2;
			
			Thread tAviao = new ThreadAeroporto(idAviao, semaforoNorte, semaforoSul, idPista);
			tAviao.start();
		}
			
		
		
	}

}
