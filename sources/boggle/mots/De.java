package boggle.mots;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;

/**
 * @author rogeza radixr
 *
 */
public class De {
	private BufferedReader fichier_source;
	private String[] retour;

	public De() {
		this.chercherLettre();
	}

	public String[] getRetour() {
		return retour;
	}

	public void setRetour(String[] retour) {
		this.retour = retour;
	}

	public void chercherLettre() {
		retour = new String[16];
		String car;
		Random rand = new Random();
		int y = 0;

		try {
			String chemin = "config/des-4x4.csv";
			fichier_source = new BufferedReader(new FileReader(chemin));

			while ((car = fichier_source.readLine()) != null) {
				int nombreAleatoire = rand.nextInt(6);
				String[] array = car.split(";");
				retour[y] = array[nombreAleatoire];
				y++;
			}
			// for(int o = 0; o<=15;o++){
			// System.out.println(retour[o]);
			// }
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
