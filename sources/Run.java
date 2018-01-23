import boggle.jeu.Config;
import boggle.jeu.Jeu;
import clavier.Clavier;

/**
 * @author rogeza radixr
 *Classe qui permet de lancer le jeu en mode console sans interface graphique
 */
public class Run {
	public static int nbJoueurs, tailleGrille, nbManches, scoreMax;

	public static void main(String[] args) {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		String boggle ="";
		boggle+="\t\t          _               _            _              _              _             _      \n";
		boggle+="\t\t         / /\\            /\\ \\         /\\ \\           /\\ \\           _\\ \\          /\\ \\\n";   
		boggle+="\t\t        / /  \\          /  \\ \\       /  \\ \\         /  \\ \\         /\\__ \\        /  \\ \\\n";   
		boggle+="\t\t       / / /\\ \\        / /\\ \\ \\     / /\\ \\_\\       / /\\ \\_\\       / /_ \\_\\      / /\\ \\ \\\n";  
		boggle+="\t\t      / / /\\ \\ \\      / / /\\ \\ \\   / / /\\/_/      / / /\\/_/      / / /\\/_/     / / /\\ \\_\\\n"; 
		boggle+="\t\t     / / /\\ \\_\\ \\    / / /  \\ \\_\\ / / / ______   / / / ______   / / /         / /_/_ \\/_/ \n";
		boggle+="\t\t    / / /\\ \\ \\___\\  / / /   / / // / / /\\_____\\ / / / /\\_____\\ / / /         / /____/\\\n";    
		boggle+="\t\t   / / /  \\ \\ \\__/ / / /   / / // / /  \\/____ // / /  \\/____ // / / ____    / /\\____\\/\n";
		boggle+="\t\t  / / /____\\_\\ \\  / / /___/ / // / /_____/ / // / /_____/ / // /_/_/ ___/\\ / / /______\n";   
		boggle+="\t\t / / /__________\\/ / /____\\/ // / /______\\/ // / /______\\/ //_______/\\__\\// / /_______\\\n" ;  
		boggle+="\t\t \\/_____________/\\/_________/ \\/___________/ \\/___________/ \\_______\\/    \\/__________/\n" ;  
		boggle+="\n";                                                                           
		System.out.print(boggle);
		
		
		// Joueurs
		System.out.println("A combien de joueurs allez vous jouer ?");
		nbJoueurs = Clavier.readInt();

		// manches
		System.out.println("Sur combien de manches ? (0 pour atteindre un score)");
		nbManches = Clavier.readInt();

		// si pas de manche, score max
		if(nbManches == 0) {
			System.out.println("Quel score voulez vous atteindre ?");
			scoreMax = Clavier.readInt();
		}

		// taille grille
		System.out.println("Sur une grille de quelle taille (4 minimum) ?");
		tailleGrille = Clavier.readInt();

		// récap
		Config config = new Config(tailleGrille, nbJoueurs, nbManches, scoreMax);
		new Jeu(config);


	}
}