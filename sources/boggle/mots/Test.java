package boggle.mots;

import java.util.Scanner;

import boggle.ui.*;
import clavier.Clavier;

public class Test {
	public static int curseur;

	public static void main(String[] args) {

//		Verifications dede = new Verifications();
//		dede.affichageGrille();
//		while (true) {
//			Scanner sc = new Scanner(System.in);
//			System.out.println(dede.estDansGrille(sc.nextLine()));
//			
//		}
		
		
		ArbreLexical a = new ArbreLexical();
		a.ajouter("bonjour");
		System.out.println("ok");
		// while(true) {
		// System.out.println("déplacer le curseur avec les flèches directionnelles");
		// curseur = sc.nextInt();
		// System.out.println(curseur);
		// }
	}

}
