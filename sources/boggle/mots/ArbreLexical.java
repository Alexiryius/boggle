package boggle.mots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

/**
 * La classe ArbreLexical permet de stocker de façon compacte et d'accéder
 * rapidement à un ensemble de mots.
 */
public class ArbreLexical {
	public static final int TAILLE_ALPHABET = 26;
	public String lettre;
	private boolean estMot; // vrai si le noeud courant est la fin d'un mot
							// valide
	private  ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET]; // les
	// sous-arbres

	/** Crée un arbre vide (sans aucun mot) */
	public ArbreLexical() {
		lettre = null;
		estMot = false;
	}

	/**
	 * Indique si le noeud courant est situé à l'extrémité d'un mot valide
	 */
	public boolean estMot() {
		return estMot;
	}

	public boolean ajouterNormalize(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		temp = Normalizer.normalize(temp.toUpperCase(), Normalizer.Form.NFD);
		return this.ajouter(temp);

	}

	/**
	 * Place le mot spécifié dans l'arbre
	 * 
	 * @return <code>true</code> si le mot a été ajouté, <code>false</code> sinon
	 */
	public boolean ajouter(String word) {
		String nWord = "";
		ArbreLexical Nfils = new ArbreLexical();
		int valueInt = 0;

		if (word.length() >= 1) {
			valueInt = word.charAt(0) - 65;
			//System.out.println(valueInt);
			Nfils.lettre = word.charAt(0) + "";
			for (int i = 1; i < word.length(); i++) {
				nWord += word.charAt(i) + "";
			}
			if (word.length() == 1) {
				Nfils.estMot = true;
				Nfils.fils = null;
			} else {
				Nfils.ajouter(nWord);
				Nfils.estMot = false;
				this.fils[valueInt] = Nfils;
			}
			//System.out.println(word + "___________" + nWord + "___________"+this.lettre+"____________"+Nfils.lettre);
			return true;
		} else {
			return false;
		}

	}

//	public boolean contientNormalize(String s) {
//		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
//		temp.replaceAll("[^\\p{ASCII}]", "").toUpperCase();
//		return this.contient(temp);
//
//	}
//
//	/**
//	 * Teste si l'arbre lexical contient le mot spécifié.
//	 * 
//	 * @return <code>true</code> si <code>o</code> est un mot (String) contenu dans
//	 *         l'arbre, <code>false</code> si <code>o</code> n'est pas une instance
//	 *         de String ou si le mot n'est pas dans l'arbre lexical.
//	 */
//	public boolean contient(String word) {
//		String nWord = "";
//		if (word.length() >= 1) {
//			for (int i = 1; i < word.length(); i++) {
//				nWord += word.charAt(i) + "";
//			}
//
//			int valueInt = word.charAt(0) - 65;
//
//			if (ArbreLexical.fils[valueInt] != null && ArbreLexical.fils[valueInt].contient(nWord)) {
//				if (nWord.equals("") && this.estMot()) {
//					return true;
//				} else
//					return false;
//			} else
//				return false;
//		} else
//			return false;
//	}

	/**
	 * Ajoute à la liste <code>resultat<code> tous les mots de
	  * l'arbre commençant par le préfixe spécifié. 
	  * @return <code>true</code> si <code>resultat</code> a été modifié,
	 *         <code>false</code> sinon.
	 */
	public boolean motsCommencantPar(String prefixe, List<String> resultat) {

		return false;
	}

	// public boolean motsCPar(String motAj, List<String> listMots){
	// motAj += this.lettre;
	// System.out.println(this.estMot() +" "+ motAj);
	// if(this.estMot()){
	// listMots.add(motAj);
	// return true;
	// }
	//
	// for(int i = 0 ; i<TAILLE_ALPHABET ; i++){
	// if(this.fils!= null && this.fils[i]!= null){
	//
	// System.out.println("entre la ");
	// if(this.fils[i].motsCPar(motAj, listMots)){
	// return true;
	// }
	// }
	// else{
	// return false;
	// }
	// motAj="";
	// }
	// return true;
	//
	//
	// }

	/**
	 * Crée un arbre lexical qui contient tous les mots du fichier spécifié.
	 */
	public void lireMots(String fichier) {

		BufferedReader fichier_dico = null;
		String line = "";
		int i = 0;
		try {
			fichier_dico = new BufferedReader(new FileReader(fichier));
			for ( line = fichier_dico.readLine(); line != null; line = fichier_dico.readLine()) {
				System.out.println(i + "-------------" + line);
				this.ajouterNormalize(line);
				i++;
			}

			fichier_dico.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fichier_dico != null) {
					fichier_dico.close();
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

	}

}
