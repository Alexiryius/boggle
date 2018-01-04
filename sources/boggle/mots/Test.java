package boggle.mots;

public class Test {
	public static int curseur;

	public static void main(String[] args) {
		ArbreLexical a = new ArbreLexical();
		a.lireMots("config/dict-fr.txt") ;
//a.ajouterNouveau("BONJOUR");
//a.ajouterNouveau("SALUT");
//a.ajouterNouveau("TYNJOUR");
//a.ajouterNouveau("BONJALLILO");
//a.ajouterNouveau("BONJOUR");
//a.ajouterNouveau("BONJ");a.ajouterNouveau("BONJAUL");
//a.toStrong();

		System.out.println("réponse : "+a.contient("OEUVRAMESIMO"));


		
	}

}
