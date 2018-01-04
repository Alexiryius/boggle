package boggle.mots;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe ArbreLexical permet de stocker de façon compacte et d'accéder
 * rapidement à un ensemble de mots.
 */
class ArbreLexical {
	public static final int TAILLE_ALPHABET = 26;
	public String lettre;
	private boolean estMot; // vrai si le noeud courant est la fin d'un mot
							// valide
	ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET]; // les
	// sous-arbres

	/** Crée un arbre vide (sans aucun mot) */
	protected ArbreLexical() {
		this.lettre = null;
		this.estMot = false;
		for (int i = 0; i < TAILLE_ALPHABET; i++) {
			this.fils[i] = null;
		}
	}

	/**
	 * Indique si le noeud courant est situé à l'extrémité d'un mot valide
	 */
	public boolean estMot() {
		return estMot;
	}

	/**
	 * Transforme un mot en minuscule et accent en mot en majuscule sans accent
	 */
	public static String aNormalise(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		temp = Normalizer.normalize(temp.toUpperCase(), Normalizer.Form.NFD);
		return temp;
	}

	/**
	 * Place le mot spécifié dans l'arbre
	 * 
	 * @return <code>true</code> si le mot a été ajouté, <code>false</code> sinon
	 */
	int atat = 0;
	public boolean ajouter(String word) {
	System.out.println(word);
		if (this.contient(word)) {
			return true;
		} else {

			return ajouterNouveau(word);
		}
	}

	public boolean ajouterNouveau(String word) {

		String nWord = "";

		int valueInt = 0;

		valueInt = word.charAt(0) - 65;
		for (int i = 1; i < word.length(); i++) {
			nWord += word.charAt(i) + "";
		}
		if (word.length() == 1) {
			if (this.fils[valueInt] == null) {
				this.fils[valueInt] = new ArbreLexical();
			}
			this.fils[valueInt].estMot = true;
			this.fils[valueInt].lettre = word;
			return true;
		} else if (nWord.length() >= 1) {

			if (this.fils[valueInt] == null) {
				this.fils[valueInt] = new ArbreLexical();
			}
			String lettre = word.charAt(0) + "";
			this.fils[valueInt].lettre = lettre;
			this.fils[valueInt].ajouterNouveau(nWord);
			return true;
		} else {
			return false;
		}

	}

//	public void toStrong() {
//		System.out.println("");
//		for (int i = 0; i < TAILLE_ALPHABET; i++) {
//			try {
//				if (this.fils[i] != null) {
//					this.fils[i].toStrong();
//					System.out.print((this.fils[i].lettre));
//				}
//			} catch (NullPointerException e) {
//				System.out.println("exeption");
//			}
//		}
//
//	}

	/**
	 * Teste si l'arbre lexical contient le mot spécifié.
	 *
	 * @return true si word est un mot (String) contenu dans l'arbre, false si word
	 *         n'est pas une instance de String ou si le mot n'est pas dans l'arbre
	 *         lexical.
	 */
	public boolean contient(String word) {
		if (word instanceof String == false && word.length() <= 0) {
			return false;
		} else {
			word = aNormalise(word);
			return this.contientNormalise(word);
		}
	}

	/**
	 * 
	 * @param word
	 * @return
	 */
	public boolean contientNormalise(String word) {
		// System.out.println("est-ce que " + word + " est dans l'arbre");
		String nWord = "";

		for (int i = 1; i < word.length(); i++) {
			nWord += word.charAt(i) + "";
		}

		// pour mettre à zero l'indice du caractère ascii 'A'

		// si l'indice de la première lettre est stockée dans le fils de this et si le
		// fils contient la suite du mot

		if (word.equals(""))
			return false;
		int valueInt = word.charAt(0) - 65;
		// System.out.println(valueInt);
		try {
			if (this.fils[valueInt] != null) {
				// System.out.println((char) (valueInt + 65) + " existe dans l'arbre");
				if (this.fils[valueInt].estMot && nWord.equals("")) {
					return true;
				} else {
					return this.fils[valueInt].contientNormalise(nWord);
				}
			}
		} catch (NullPointerException e) {
			return false;
		}
		return false;

	}

	/**
	 * Ajoute à la liste resultat tous les mots de l'arbre commençant par le préfixe
	 * spécifié.
	 * 
	 * @return true si resultat a été modifié, false sinon.
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
			for (line = fichier_dico.readLine(); line != null; line = fichier_dico.readLine()) {

				if (verifierMot(line)) {
					this.ajouter(aNormalise(line));
				} else
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

	/**
	 * Fonction qui sert à savoir si un mot contient un chiffre, une lettre de
	 * l'alphabet grec, un signe de ponctuation, des caractère spéciaux comme les
	 * exposant ou le caractère lambda.
	 */
	public boolean verifierMot(String mot) {

		String regex1 = "\\d+";
		String regex2 = "\\p{IsGreek}+";
		String regex3 = "\\p{Punct}+";
		String regex4 = "[\\u02B0-\\u1D49]+";
		String regex5 = "[\\u00B5]+";
		String regex6 = "[\\u2082]+";

		Pattern p = Pattern.compile(regex1);
		Matcher m1 = p.matcher(mot);
		p = Pattern.compile(regex2);
		Matcher m2 = p.matcher(mot);
		p = Pattern.compile(regex3);
		Matcher m3 = p.matcher(mot);
		p = Pattern.compile(regex4);
		Matcher m4 = p.matcher(mot);
		p = Pattern.compile(regex5);
		Matcher m5 = p.matcher(mot);
		p = Pattern.compile(regex6);
		Matcher m6 = p.matcher(mot);

		if (m1.find() || m2.find() || m3.find() || m4.find() || m5.find()|| m6.find()) {
			return false;
		}

		return true;
	}

}
