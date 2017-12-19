package boggle.mots;

import java.util.ArrayList;
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
	private ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET]; // les
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

	/**
	 * Place le mot spécifié dans l'arbre
	 * 
	 * @return <code>true</code> si le mot a été ajouté, <code>false</code>
	 *         sinon
	 */
	public boolean ajouter(String word) {
		String nWord = "";
		ArbreLexical Nfils = new ArbreLexical();
		int valueInt = 0;

		char maj = Character.toUpperCase(word.charAt(0));
		valueInt = maj - 65;
		
		Nfils.lettre = word.charAt(0) + "";
		
		for (int i = 1; i < word.length(); i++) {
			nWord += word.charAt(i) + "";
		}
		
		if (word.length() == 1) { Nfils.estMot = true; } 
		else { Nfils.estMot = false; }

		if (!nWord.equals("")) { Nfils.ajouter(nWord); } 
		else { Nfils.fils = null; }
		
		this.fils[valueInt] = Nfils;
		// à compléter
		return true;
	}

	/**
	 * Teste si l'arbre lexical contient le mot spécifié.
	 * 
	 * @return <code>true</code> si <code>o</code> est un mot (String) contenu
	 *         dans l'arbre, <code>false</code> si <code>o</code> n'est pas une
	 *         instance de String ou si le mot n'est pas dans l'arbre lexical.
	 */
	public boolean contient(String word) {
		String nWord = "";
		
		for (int i = 1; i < word.length(); i++) {
			nWord += word.charAt(i) + "";
		}
		char maj = Character.toUpperCase(word.charAt(0));
		int valueInt = maj - 65;
		if(nWord.equals("")){
			return true;
		}
		
		if( this.fils[valueInt]!= null  && this.fils[valueInt].contient(nWord)){
			return true;		
		}
		else{
			return false;
		}
	}

	/**
	 * Ajoute à la liste <code>resultat<code> tous les mots de
	  * l'arbre commençant par le préfixe spécifié. 
	  * @return <code>true</code> si <code>resultat</code> a été modifié,
	 *         <code>false</code> sinon.
	 */
	public boolean motsCommencantPar(String prefixe, List<String> resultat) {

		
		return false;
	}



//	public boolean motsCPar(String motAj, List<String> listMots){
//		motAj += this.lettre;
//		System.out.println(this.estMot() +" "+ motAj);
//		if(this.estMot()){
//			listMots.add(motAj);
//			return true;
//		}
//		
//		for(int i = 0 ; i<TAILLE_ALPHABET ; i++){
//			if(this.fils!= null && this.fils[i]!= null){
//
//				System.out.println("entre la ");
//				if(this.fils[i].motsCPar(motAj, listMots)){
//					return true;
//				}
//			}
//			else{
//				return false;
//			}
//			motAj="";
//		}
//		return true;
//		
//		
//	}

	/**
	 * Crée un arbre lexical qui contient tous les mots du fichier spécifié.
	 */
	public static ArbreLexical lireMots(String fichier) {
		// à compléter
		return null;
	}



}
