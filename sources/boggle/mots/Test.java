package boggle.mots;

public class Test {
	public static int curseur;

	public static void main(String[] args) {
		ArbreLexical a = new ArbreLexical();
		//a.lireMots("toté"));
		a.lireMots("config/dict-fr.txt") ;

	}

}
