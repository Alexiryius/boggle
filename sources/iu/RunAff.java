package iu;

import javax.swing.SwingUtilities;

import boggle.jeu.Config;
import boggle.jeu.JeuIU;




public class RunAff {
	public static int nbJoueurs, tailleGrille, nbManches, scoreMax;
	static Config config = null;

	public static void main(String[] args) {
		
		

		Thread t1 = new Thread(new Runnable() {
			public void run() {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						new AffichageParam();

					}
				});
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {

						new JeuIU(config);

					}
				});
			}
		});
		
		
		t1.start();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		
		

	}

}
