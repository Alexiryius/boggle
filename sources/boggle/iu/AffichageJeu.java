package boggle.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import boggle.jeu.Config;
import boggle.jeu.Joueur;
import boggle.mots.ArbreLexical;
import boggle.mots.GrilleLettres;
import boggle.mots.Verifications;

/**
 * @author rogeza radixr
 *Classe qui permet de lancer la fenetre de  jeu en  interface graphique
 */
@SuppressWarnings("serial")
public class AffichageJeu extends JFrame {


	JPanel panelPrincipal = new JPanel();
	
	JPanel panelCentral = new JPanel();
	GridBagConstraints gbcPanelCentral = new GridBagConstraints();
	JPanel panelGrilleLettre = new JPanel();
	JButton boutonMotTrouve = new JButton("Ok");
	JLabel labelManche;
	JLabel labelInfo;
	JTextField textField;
	JLabel[][] tab;
	char[][] tabgrille;
	private GrilleLettres grille;
	int tailleGrille;
	
	JPanel panelPointJoueur = new JPanel();
	JButton boutonFinTour = new JButton("Fin du Tour");
	JLabel nomJoueur;
	JLabel titreMotDonnee;
	JList<String> listeMotDonnes;
	String motConstruit = "";
	ArrayList<String> motsDejaDonnes;
	Joueur[] joueurs;
	
	JPanel panelJoueurSuivant = new JPanel();
	JLabel nomJoueurSuivant;

	Config config;

	ArbreLexical arbre;
	Verifications verif;

	int tour = 0;
	int manche = 1;
	Font fontLettres = new Font("sansserif", Font.BOLD, 20);
	Font fontLettres2 = new Font("sansserif", Font.CENTER_BASELINE, 12);


	/**
	 * Constructeur qui va afficher tous les composants de la fenêtre et lancer le jeu
	 */
	public AffichageJeu(Config config) {

		super("Boggle Le Jeu");

		this.config = config;
		this.joueurs = config.getJoueurs();
		tailleGrille = config.getTailleGrille();

		arbre = new ArbreLexical();
		arbre.lireMots("config/dict-fr.txt");

		motsDejaDonnes = new ArrayList<String>();


		panelCentral.setBackground(new Color(153, 204, 255));
		panelCentral.setLayout(new GridBagLayout());
		panelCentral.setBorder(BorderFactory.createBevelBorder(2));
		afficherPanelCentral();
		remplirPanelCentral();

		panelPointJoueur.setBackground(new Color(204, 153, 255));
		panelPointJoueur.setLayout(new BorderLayout());
		panelPointJoueur.setBorder(BorderFactory.createBevelBorder(2));
		afficherPanelScore();
		remplirPanelScore();

		panelJoueurSuivant.setBackground(new Color(255, 153, 255));
		panelJoueurSuivant.setLayout(new BorderLayout());
		panelJoueurSuivant.setBorder(BorderFactory.createBevelBorder(2));
		afficherPanelJoueurSuivant();
		remplirPanelJoueurSuivant();

		panelPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		panelPrincipal.add(panelCentral, gbc);

		gbc.weightx = 0.3;
		gbc.weighty = 0.3;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 0;

		panelPrincipal.add(panelPointJoueur, gbc);

		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 1;
		panelPrincipal.add(panelJoueurSuivant, gbc);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		this.setContentPane(panelPrincipal);
		this.setVisible(true);

	}
	
	
	//----------------------------------------------------------------------------------------------
	//-----------------------------Affichages des différents panels---------------------------------
	//----------------------------------------------------------------------------------------------

	
	/**
	 * méthode qui permet de placer les composants du panel central
	 * la grille, le label de manche, le textfield pour écrire les mot, le bouton de mot trouvé
	 */
	public void afficherPanelCentral() {

		textField = new JTextField();
		labelInfo = new JLabel("Entrez un mot qui existe");
		textField.setToolTipText("Appuyez sur \"Ok\" quand votre mot est construit");
		textField.setPreferredSize(new Dimension(300, 25));
		textField.setMinimumSize(new Dimension(300, 25));

		gbcPanelCentral.gridx = 1;
		gbcPanelCentral.gridy = 6;
		gbcPanelCentral.gridwidth = 3;
		gbcPanelCentral.fill = GridBagConstraints.NONE;
		panelCentral.add(textField, gbcPanelCentral);

		gbcPanelCentral.gridx = 1;
		gbcPanelCentral.gridy = 8;
		gbcPanelCentral.gridwidth = 3;
		gbcPanelCentral.fill = GridBagConstraints.BOTH;
		panelCentral.add(boutonMotTrouve, gbcPanelCentral);

		gbcPanelCentral.gridx = 1;
		gbcPanelCentral.gridy = 7;
		gbcPanelCentral.gridwidth = 3;
		panelCentral.add(labelInfo, gbcPanelCentral);

		boutonMotTrouve.addActionListener(new EcouteurBoutonMotTrouve());

	}
	
	/**
	 * méthode qui permet de placer les composants du panel de score, 
	 * le nom du joueur actuel, la liste de mot, le nombre de point, et le bouton fin de tour
	 */
	public void afficherPanelScore() {

		nomJoueur = new JLabel();
		nomJoueur.setHorizontalAlignment(JLabel.CENTER);
		nomJoueur.setVerticalAlignment(JLabel.CENTER);
		nomJoueur.setBorder(BorderFactory.createBevelBorder(1));

		titreMotDonnee = new JLabel();
		listeMotDonnes = new JList<String>();
		listeMotDonnes.setBackground(new Color(204, 153, 255));

		panelPointJoueur.add(boutonFinTour, BorderLayout.SOUTH);
		panelPointJoueur.add(nomJoueur, BorderLayout.NORTH);
		panelPointJoueur.add(listeMotDonnes, BorderLayout.CENTER);

		boutonFinTour.addActionListener(new EcouteurBoutonFinTour());

	}
	
	/**
	 * méthode qui permet de placer les composants du panel du joueur suivant
	 */
	public void afficherPanelJoueurSuivant() {
		nomJoueurSuivant = new JLabel();
		nomJoueurSuivant.setHorizontalAlignment(JLabel.CENTER);
		nomJoueurSuivant.setVerticalAlignment(JLabel.CENTER);
		nomJoueurSuivant.setBorder(BorderFactory.createBevelBorder(1));
		panelJoueurSuivant.add(nomJoueurSuivant, BorderLayout.NORTH);
	}


	//----------------------------------------------------------------------------------------------
	//-----------------------------Remplir les différents panels------------------------------------
	//----------------------------------------------------------------------------------------------

	

	/**
	 * méthode qui remplis les composants du panel central :
	 * la grille , le label de manche
	 */
	public void remplirPanelCentral() {

		panelGrilleLettre.setPreferredSize(new Dimension(300, 300));
		panelGrilleLettre.setLayout(new GridLayout(tailleGrille, tailleGrille));

		labelManche = new JLabel();
		remplirLabelManche();

		grille = new GrilleLettres(tailleGrille);
		verif = new Verifications(grille);
		tabgrille = grille.getTabCharGrille();
		remplirTabLettres(panelGrilleLettre, tailleGrille);

		gbcPanelCentral.gridx = 1;
		gbcPanelCentral.gridy = 0;
		gbcPanelCentral.gridwidth = 3;
		panelCentral.add(labelManche, gbcPanelCentral);

		gbcPanelCentral.gridx = 1;
		gbcPanelCentral.gridy = 1;
		gbcPanelCentral.gridwidth = 3;
		gbcPanelCentral.gridheight = 3;
		panelCentral.add(panelGrilleLettre, gbcPanelCentral);

	}
	
	/**
	 * méthode qui appelle remplirTabLettres(2) pour chaque case de la grille
	 * et qui ajoute au panel chaque label
	 * @param panel
	 * @param tailleGrille
	 */
	public void remplirTabLettres(JPanel panel, int tailleGrille) {
		panel.removeAll();
		tab = new JLabel[tailleGrille][tailleGrille];
		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y] = new JLabel();
				remplirTabLettres(tab[i][y], i, y);
				panel.add(tab[i][y]);
			}
		}

	}
	
	/**
	 * méthode qui remplis une case de la grille et centre la lettre
	 * @param label le labelde la grille a remplir
	 * @param x
	 * @param y
	 */
	public void remplirTabLettres(JLabel label, int x, int y) {

		label.setText(tabgrille[x][y] + "");
		label.setFont(fontLettres);
		label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);

	}


	/**
	 * méthode qui remplis le label qui annonce le joueur qui doit jouer actuellement
	 * et qui annonce le joueur gagnant si le jeu et terminé
	 */
	public void remplirPanelScore() {

		if ((joueurs[tour % config.getNbJoueurs()].getScore() >= config.getScoreMax())
				&& (tour / config.getNbJoueurs() >= config.getNbMancheMax())) {
			System.out.println("Score final : \n");
			finPartieEffacer();
			declarerGagnant(joueurs);
		} else {

			tour++;
			manche = (tour / config.getNbJoueurs()) + 1;
			nomJoueur.setFont(fontLettres2);
			nomJoueur.setText(" A " + joueurs[tour % config.getNbJoueurs()].getName() + " de jouer");

		}
		System.out.println(joueurs[tour % config.getNbJoueurs()].getScore() + " <-/-> " + config.getScoreMax());
		System.out.println(tour / config.getNbJoueurs() + " <-/-> " + config.getNbMancheMax());

		panelPointJoueur.add(boutonFinTour, BorderLayout.SOUTH);
		panelPointJoueur.add(nomJoueur, BorderLayout.NORTH);
	}

	/**
	 * méthode qui remplis la liste des mots déjà donnés
	 */
	public void remplirPanelScoreMots() {

		DefaultListModel<String> listModel = new DefaultListModel<String>();

		listModel.addElement("Mots déjà donnés :");
		for (int index = 0; index < motsDejaDonnes.size(); index++) {

			listModel.addElement(motsDejaDonnes.get(index));
		}

		listeMotDonnes.setModel(listModel);

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) listeMotDonnes.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		panelPointJoueur.add(listeMotDonnes, BorderLayout.CENTER);

	}

	

	/**
	 * méthode qui remplis le label annoncant le joueur suivant
	 */
	public void remplirPanelJoueurSuivant() {
		nomJoueurSuivant.setFont(fontLettres2);
		nomJoueurSuivant.setText(joueurs[(tour + 1) % config.getNbJoueurs()].getName() + " est le suivant");
	}
	

	/**
	 * méthode qui remplis le label annoncant la manche actuelle
	 */
	public void remplirLabelManche() {
		labelManche.setFont(fontLettres2);
		labelManche.setText("Manche n° " + manche);
	}


	
	//----------------------------------------------------------------------------------------------
	//--------------------------------Effacer les différents panels---------------------------------
	//----------------------------------------------------------------------------------------------

	/**
	 * méthode qui efface le label qui annonce la manche actuelle 
	 */
	public void effacerLabelManche() {
		labelManche.setText("        ");
	}

	/**
	 * méthode qui efface la grille de lettre 
	 * pour éviter les superposition de lettre dans la grille
	 */
	public void effacerTabLettres() {

		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y].setText("");

			}
		}

	}

	/**
	 * méthode qui efface le score du joueur
	 */
	public void effacerScore() {
		panelPointJoueur.removeAll();
	}

	/**
	 * méthode qui désactive tous les bouton et qui annonce la fin de la partie
	 */
	public void finPartieEffacer() {
		boutonFinTour.setEnabled(false);
		boutonMotTrouve.setEnabled(false);
		effacerLabelManche();
		labelManche.setFont(fontLettres2);
		labelManche.setText("Fin de Partie");
		labelInfo.setText("");
		textField.setEnabled(false);
	}
	
	
	
	
	//----------------------------------------------------------------------------------------------
	//--------------------------------Vérification de mot construit---------------------------------
	//----------------------------------------------------------------------------------------------
	

	/**
	 * méthode qui permet de vérifier si le mot construit 
	 * correspond aux critères de taille d'existence dans le dictionnaire et la grille
	 * la méthode modifie le label d'information pour que le joueur puisse avoir un retour 
	 */
	public void verifMotConstruit() {
		if (!motConstruit.equals("")) {

			if (motConstruit.length() > 2) {

				// vérification du mot dans la grille
				if (verif.estDansGrille(motConstruit)) {
					System.out.println(motConstruit);
					// vérification du mot dans le dictionnaire
					if (arbre.contient(motConstruit.toLowerCase())) {
						// vérification dans les mots déjà dits
						if (motsDejaDonnes.contains(motConstruit) && !motsDejaDonnes.isEmpty()) {

							new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe",
									"Vous avez déjà donné ce mot.", 4);
						} else {
							// on ajoute le mot dans l'historique

							motsDejaDonnes.add(motConstruit);

							// on calcule les points apportés par le mot
							int points = calculerPoints(motConstruit);
							new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Bravo ! ", 4);
							// on ajoute au score
							joueurs[tour % config.getNbJoueurs()].setScore(points);
							System.out.println(" + " + points + " points ! Total : "
									+ joueurs[tour % config.getNbJoueurs()].getScore() + "\n");
						}
					} else {
						new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Ce mot n'existe pas", 4);
					}
				} else {
					new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Ce mot n'est pas dans la grille",
							4);
				}
			} else {
				new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Tapez un mot plus long", 4);
			}

		} else {

			new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Vous n'avez pas construit de mot", 3);
		}

	}

	/**
	 * méthode qui permet de compter le nombre de point gagné par mot donné
	 * @param mot
	 * @return le nombre de point gagné
	 */
	public int calculerPoints(String mot) {
		int points;
		switch (mot.length()) {
		case 3:
		case 4:
			points = 1;
			break;
		case 5:
			points = 2;
			break;
		case 6:
			points = 3;
			break;
		case 7:
			points = 5;
			break;
		default:
			points = 11;
			break;
		}
		return points;
	}

	/**
	 * méthode qui permet de déterminer et d'afficher quel est le joueur gagnant
	 */
	public void declarerGagnant(Joueur[] joueur) {
		int bestScore = 0;
		boolean unique = true;
		String gagnant = "";

		// on récupère le score maximum
		for (int i = 0; i < config.getNbJoueurs(); i++) {
			if (joueur[i].getScore() > bestScore) {
				bestScore = joueur[i].getScore();
			}
		}

		// pour ce score maximum on récupère le nom des joueurs correspondants
		for (int i = 0; i < config.getNbJoueurs(); i++) {
			if (joueur[i].getScore() == bestScore) {
				if (gagnant.equals("")) {
					gagnant += joueur[i].getName();
				} else {
					// s'il y a plusieurs joueurs avec le même score on passe la
					// variable unique a
					// false
					unique = false;
					gagnant += ", " + joueur[i].getName();
				}
			}
		}

		// on affiche le ou les gagnants
		if (unique) {
			System.out.println("Gagnant :\n" + gagnant + "\n");
		} else {
			System.out.println("Gagnants ex-aequo :\n" + gagnant + "\n");
		}
	}


	
	//----------------------------------------------------------------------------------------------
	//-----------------------------Classes internes---Les Listeners---------------------------------
	//----------------------------------------------------------------------------------------------
	
	
	/**
	 * classe interne extends thread permettant de 
	 * modifier un label pour un temps qu'on peut donner en paramètre
	 */
	/**
	 * @author alex
	 *
	 */
	public class ChangerUnLabelUnMoment extends Thread {

		JLabel jl = new JLabel();
		String sAvant = new String();
		String sApres = new String();
		int temps = 0;

		
		/**
		 * méthode qui modifie un label pour un temps donné
		 * @param jl le label 
		 * @param sAvant la chaine de caractère à modifier
		 * @param sApres la nouvelle chaine de caractère 
		 * @param temps la durée de modification en seconde 
		 */
		public ChangerUnLabelUnMoment(JLabel jl, String sAvant, String sApres, int temps) {
			this.jl = jl;
			this.sAvant = sAvant;
			this.sApres = sApres;
			this.temps = temps;
			this.start();
		}

		/*
		 *méthode de lancement de la classe
		 */
		public void run() {
			try {

				jl.setText("");
				jl.setFont(fontLettres2);
				jl.setText(sApres);
				Thread.sleep(temps * 800);
				jl.setText("");
				jl.setText(sAvant);
				textField.setText("");

			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}

	}
	
	/**
	 * classe interne qui permet d'écouter l'évènement clic sur le bouton finDeTour
	 *
	 */
	public class EcouteurBoutonFinTour implements ActionListener {
		public void actionPerformed(ActionEvent clic) {

			motsDejaDonnes = null;
			System.out.println(tour + " <- tour / manche -> " + manche);

			effacerTabLettres();
			effacerLabelManche();
			remplirPanelCentral();

			effacerScore();
			remplirPanelScore();

			remplirPanelJoueurSuivant();

		}
	}

	/**
	 * classe interne qui permet d'écouter l'évènement clic sur le bouton motTrouve
	 *
	 */
	public class EcouteurBoutonMotTrouve implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			motConstruit = textField.getText().toUpperCase();
			verifMotConstruit();
			remplirPanelScoreMots();
		}
	}
	
	//----------------------------------------------------------------------------------------------
	//--------------------------------------------Main----------------------------------------------
	//----------------------------------------------------------------------------------------------
	

	/**
	 * main de cette classe AffichageJeu pour les tests
	 */
	public static void main(String[] args) {
		new AffichageJeu(new Config(5, 2, 4, 0));
	}

}
