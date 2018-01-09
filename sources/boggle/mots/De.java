package boggle.mots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * @author rogeza radixr
 *
 */

public class De {
	private BufferedReader fichier_source;
	private char[] retour;

	/**
	 * 
	 */
	public De(int i) {
		this.chercherLettre(i);
	}

	/**
	 * @return
	 */
	public char[] getRetour() {
		return retour;
	}

	/**
	 * @param retour
	 */
	public void setRetour(char[] retour) {
		this.retour = retour;
	}

	/**
	 * de lettre au hasard à partir du fichier des-4*4 par défaut
	 * on peut choisir un de de 5 ou de 6
	 */
	public void chercherLettre(int i) {
		retour = new char[i*i];
		String str;
		Random rand = new Random();
		int y = 0;

		try {
			String chemin;
			if(i==5){
				chemin = "config/des-5x5.csv";
			}
			else if(i==6){
				chemin = "config/des-6x6.csv";
			}
			else{
				chemin = "config/des-4x4.csv";
			}
		
			fichier_source = new BufferedReader(new FileReader(chemin));

			while ((str = fichier_source.readLine()) != null) {
				int nombreAleatoire = rand.nextInt(6);
				String[] array = str.split(";");
				retour[y] = array[nombreAleatoire].charAt(0);
				y++;
			}

			fichier_source.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fichier_source != null)
					fichier_source.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
