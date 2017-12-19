package boggle.mots;

import java.util.ArrayList;
import java.util.List;
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
//
//		System.out.println(a.contient("bonjour"));
		a.ajouter("bonjour");a.ajouter("bonjui");a.ajouter("rfedf");
//		System.out.println(a.contient("bonjour"));
//		System.out.println(a.contient("rfedf"));
		List<String> liste = new ArrayList<String>();
//		boolean tyt = a.motsCPar("",liste);
		for(String ab : liste){
			System.out.println("aha");
			System.out.println(ab);
		}
		
		System.out.println("ok");
		// while(true) {
		// System.out.println("déplacer le curseur avec les flèches directionnelles");
		// curseur = sc.nextInt();
		// System.out.println(curseur);
		// }
	}

}
