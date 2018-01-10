package boggle.iu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import boggle.jeu.Config;
import boggle.jeu.Joueur;
import boggle.mots.ArbreLexical;
import boggle.mots.GrilleLettres;
import boggle.mots.Verifications;

@SuppressWarnings("serial")
public class AffichageIU extends JFrame {

	// Les deux panels de ta fenetre

	JPanel panel3 = new JPanel();
	JPanel panel31 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JSplitPane splitPane3s45;
	JSplitPane splitPane45;
	int height;
	int width;
	int panelHeight;
	int panelWidth;
	JLabel[][] tab;
	char[][] tabgrille;
	String motConstruit = "";
	boolean finTour;

	private GrilleLettres grille;
	int tailleGrille;
	private ArrayList<String> motsDejaDonnes;
	private Joueur[] joueurs;
	private Config config;
	private int tour = 0;
	ArbreLexical arbre;
	Verifications verif;

	Box panneauRadio = Box.createVerticalBox();
	ButtonGroup group = new ButtonGroup();
	JRadioButton btn2Joueurs = new JRadioButton("2 joueurs", true);
	JRadioButton btn3Joueurs = new JRadioButton("3 joueurs", false);
	JRadioButton btn4Joueurs = new JRadioButton("4 joueurs", false);
	JRadioButton btn5Joueurs = new JRadioButton("5 joueurs", false);
	JRadioButton btn6Joueurs = new JRadioButton("6 joueurs", false);

	JButton boutonFinTour = new JButton("Fin du Tour");
	JLabel labelComptManche;
	JLabel labelManche;
	JLabel scoreJoueur;
	JLabel titreMotDonnee;
	JLabel motDonnee;
	JLabel labelInfo;
	JTextField textField;

	Font fontLettres = new Font("sansserif", Font.BOLD, 18);// changement de font si besoin

	// Constructeur de ta fenêtre
	public AffichageIU(Config config) {
		// Paramètres (tu règles selon tes envies)
		super("Boggle Le Jeu");

		this.config = config;
		this.joueurs = config.getJoueurs();
		tailleGrille = config.getTailleGrille();

		// on instancie l'arbre lexical avec le dictionnaire
		arbre = new ArbreLexical();
		arbre.lireMots("config/dict-fr.txt");

		tailleFenetre();
		// this.setSize(width, height);
		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// Par défaut, on place le bouton dans le premier panel qui est dans la fenêtre
		panel3.setLayout(null); // layout a null pour position à la main

		tailleJPanel3();

		splitPane45 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel4, panel5);
		splitPane45.setResizeWeight(.80d);
		// splitPane45.setEnabled(false);

		splitPane3s45 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel3, splitPane45);
		// splitPane.setDividerLocation(.8d);
		splitPane3s45.setResizeWeight(.75d);
		// splitPane3s45.setEnabled(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setSize(width, height);
		this.setContentPane(splitPane3s45);
		this.setVisible(true);

		startGame();

	}

	public void startGame() {
		// début du jeu

		// on boucle tant que le joueur n'a pas atteint le score max ou le nombre de
		// manche max
		afficherBtnLblPanel3(panel3, panelHeight, panelWidth);
		do {
			// on instancie l'objet qui vérifie les mots de cette grille
			grille = new GrilleLettres(tailleGrille);
			verif = new Verifications(grille);
			// création de l'arraylist qui recense tous les mots entrés pour ne pas les
			// compter en double
			motsDejaDonnes = new ArrayList<String>();
			int manche = getTour() / config.getNbJoueurs() + 1;
			finTour = false;
			labelComptManche = new JLabel(manche + "");

			afficherScore(joueurs, panelHeight, panelWidth);

			tabgrille = grille.getTabCharGrille();
			// afficherBtnLblPanel3(panel3);
			affichTabLettres(panel3, tailleGrille, panelHeight, panelWidth);

			while (!finTour) {
				waitForEnter();
			}
			// Incrémentation du tour
			this.setTour();
		} while ((joueurs[tour % config.getNbJoueurs()].getScore() < config.getScoreMax())
				|| (tour / config.getNbJoueurs() < config.getNbMancheMax()));

		// affichage final
		System.out.println("Score final : \n");
		afficherScore(joueurs, panelHeight, panelWidth);
		this.declarerGagnant(joueurs);
	}

	public class EcouteurBoutonFinTour implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			effacerTabLettres();
			finTour = true;
		}
	}

	public class IsKeyPressed {
		private volatile boolean wPressed = false;

		public boolean isWPressed() {
			synchronized (IsKeyPressed.class) {
				return wPressed;
			}
		}
	}

	public void waitForEnter() {
		final CountDownLatch latch = new CountDownLatch(1);
		KeyEventDispatcher dispatcher = new KeyEventDispatcher() {

			public boolean dispatchKeyEvent(KeyEvent e) {

				synchronized (IsKeyPressed.class) {
					if (e.getID() == KeyEvent.KEY_PRESSED) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) {
							latch.countDown();
							KeyEnter();
						}
					}

				}
				return false;
			}
		};
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
		try {
			latch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(dispatcher);
	}

	public void KeyEnter() {
		if (!motConstruit.equals("")) {

			// récupération de l'entrée standard
			// on supprime les accents et on met en majuscule

			// on vérifie la longueur
			if (motConstruit.length() > 2) {

				// vérification du mot dans la grille
				if (verif.estDansGrille(motConstruit)) {
					// vérification du mot dans le dictionnaire
					if (arbre.contient(motConstruit.toLowerCase())) {
						// vérification dans les mots déjà dits
						if (motsDejaDonnes.contains(motConstruit)) {

							new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe",
									"Vous avez déjà donné ce mot.", 4);
						} else {
							// on ajoute le mot dans l'historique
							motsDejaDonnes.add(motConstruit);
							// on calcule les points apportés par le mot
							int points = calculerPoints(motConstruit);
							// on ajoute au score
							joueurs[tour % config.getNbJoueurs()].setScore(points);
							System.out.println(" + " + points + " points ! Total : "
									+ joueurs[tour % config.getNbJoueurs()].getScore() + "\n");
						}
					} else {
						new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe",
								"Ce mot n'existe pas dans le dictionnaire", 4);
					}
				} else {
					new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Ce mot n'est pas dans la grille",
							4);
				}
			} else {
				new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe",
						"Les mots nécessitent au moins 3 caractères", 4);
			}

			for (String motDonne : motsDejaDonnes) {
				motDonnee.setText(motDonne);
			}
		} else {

			new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Vous n'avez pas construit de mot", 3);
		}
		// remonterTabLettres();

	}

	public class ChangerUnLabelUnMoment extends Thread {

		JLabel jl = new JLabel();
		String sAvant = new String();
		String sApres = new String();
		int temps = 0;

		public ChangerUnLabelUnMoment(JLabel jl, String sAvant, String sApres, int temps) {
			this.jl = jl;
			this.sAvant = sAvant;
			this.sApres = sApres;
			this.temps = temps;
			this.start();
		}

		public void run() {
			try {

				jl.setText("");
				jl.setText(sApres);
				Thread.sleep(temps * 1000);
				jl.setText("");
				jl.setText(sAvant);

			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}

	}

	// Méthode qui change le panel de ta fenêtre
	public void changerMenu() {
		this.setContentPane(this.splitPane3s45);
		this.revalidate();
	}

	// Méthode récupère la taille de l'écran pour la fenètre générale
	public void tailleFenetre() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		height = (int) dimension.getHeight();
		width = (int) dimension.getWidth();
	}

	// Méthode qui donne la taille du jpanel3 principal
	public void tailleJPanel3() {
		panelHeight = height;
		panelWidth = (int) (width * (0.75));
	}

	public void affichTabLettres(JPanel panel, int tailleGrille, int hauteur, int largeur) {
		tab = new JLabel[tailleGrille][tailleGrille];
		int debutGauche = (largeur - (tailleGrille * 50)) / 2;
		int debutHaut = ((hauteur * 4 / 5) - (tailleGrille * 50)) / 2;
		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y] = createJLabel();// on crée les JLabel et on met dans tab
				remplirTabLettres(tab[i][y], i, y);

				panel.add(tab[i][y]);
				tab[i][y].setBounds((i * 50) + debutGauche, (y * 50) + debutHaut, 50, 50);
			}
		}

	}

	public void effacerTabLettres() {
		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y].setText("");

			}
		}
	}

	public void afficherBtnLblPanel3(JPanel panel, int hauteur, int largeur) {
		labelManche = new JLabel("Tour n°");
		labelComptManche = new JLabel("0");
		textField = new JTextField();
		labelInfo = new JLabel("Tapez un mot qui existe");
		textField.setToolTipText("Appuyez sur \"Entrée\" quand votre mot est construit");
		panel.add(labelManche);
		panel.add(labelComptManche);
		panel.add(boutonFinTour);
		panel.add(textField);
		panel.add(labelInfo);

		labelManche.setBounds((largeur / 2) - 35, (hauteur / 7), 55, 50);
		labelComptManche.setBounds((largeur / 2) + 20, (hauteur / 7), 50, 50);
		boutonFinTour.setBounds((largeur - 300), (hauteur - 150), 150, 50);
		textField.setBounds((largeur / 2) - 180, (hauteur / 2) + 50, 360, 25);
		labelInfo.setBounds((largeur / 2) - 180, (hauteur / 2) + 75, 360, 25);
		boutonFinTour.addActionListener(new EcouteurBoutonFinTour());

	}

	public void afficherScore(Joueur[] joueurs, int hauteur, int largeur) {
		scoreJoueur = new JLabel("");
		titreMotDonnee = new JLabel("Mots déjà données");
		motDonnee = new JLabel("");
		for (Joueur joueur : joueurs) {
			scoreJoueur.setText(joueur + "");
		}

		panel4.add(scoreJoueur);
		panel4.add(titreMotDonnee);
		panel4.add(scoreJoueur);

		labelManche.setBounds((largeur / 2) - 35, (hauteur / 7), 55, 50);
		labelComptManche.setBounds((largeur / 2) + 20, (hauteur / 7), 50, 50);
		boutonFinTour.setBounds((largeur - 300), (hauteur - 150), 150, 50);

	}

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
					// s'il y a plusieurs joueurs avec le même score on passe la variable unique a
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

	public void remplirTabLettres(JLabel label, int x, int y) {

		label.setText(tabgrille[x][y] + "");
		label.setFont(fontLettres);
		label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);

	}

	// Ecouteur des radio bouton nobre joueurs
	public class EcouteurRadioBoutonChanger implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			AbstractButton aButton = (AbstractButton) clic.getSource();
			System.out.println("Selected: " + aButton.getText());

		}

	}

	public JLabel createJLabel() {
		JLabel jl = new JLabel();

		return jl;
	}

	public int getTour() {
		return tour;
	}

	public void setTour() {
		tour++;
	}

	public static String enleverAccents(String mot) {
		return Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static void main(String[] args) {
		new AffichageIU(new Config(4, 2, 4, 0));
	}

}
