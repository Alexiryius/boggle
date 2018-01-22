package boggle.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import boggle.jeu.Config;
import boggle.jeu.Joueur;
import boggle.mots.ArbreLexical;
import boggle.mots.GrilleLettres;
import boggle.mots.Verifications;

@SuppressWarnings("serial")
public class AffichageIU extends JFrame {

	// Les deux panels de ta fenetre

	JPanel panelPrincipal = new JPanel();
	JPanel panelCentral = new JPanel();
	GridBagConstraints gbcPanelCentral = new GridBagConstraints();
	JPanel panelPointJoueur = new JPanel();
	JPanel panelJoueurSuivant = new JPanel();

	JPanel panelGrilleLettre = new JPanel();

	int height;
	int width;
	int panelHeight;
	int panelWidth;
	JLabel[][] tab;
	char[][] tabgrille;
	String motConstruit = "";

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
	JButton boutonMotTrouve = new JButton("Ok");
	JLabel labelManche;
	JLabel nomJoueur;
	JLabel titreMotDonnee;
	JList<String> listeMotDonnes;
	JLabel labelInfo;
	JTextField textField;

	JLabel nomJoueurSuivant;

	int manche = 1;

	Font fontLettres = new Font("sansserif", Font.BOLD, 18);// changement de
															// font si besoin

	// Constructeur de ta fenêtre
	public AffichageIU(Config config) {

		super("Boggle Le Jeu");

		this.config = config;
		this.joueurs = config.getJoueurs();
		tailleGrille = config.getTailleGrille();

		arbre = new ArbreLexical();
		arbre.lireMots("config/dict-fr.txt");

		motsDejaDonnes = new ArrayList<String>();

		getTailleFenetre();

		panelCentral.setBackground(new Color(153, 204, 255));
		panelCentral.setLayout(new GridBagLayout());
	    panelCentral.setBorder(BorderFactory.createBevelBorder(2));
		afficherPanelCentral(panelHeight, panelWidth);
		remplirPanelCentral();

		panelPointJoueur.setBackground(new Color(204, 153, 255));
		panelPointJoueur.setLayout(new BorderLayout());
		panelPointJoueur.setBorder(BorderFactory.createBevelBorder(2));
		afficherPanelScore(joueurs, panelHeight, panelWidth);
		remplirPanelScore();

		panelJoueurSuivant.setBackground(new Color(255, 153, 255));
		panelJoueurSuivant.setLayout(new BorderLayout());
		panelJoueurSuivant.setBorder(BorderFactory.createBevelBorder(2));
		afficherPanelJoueurSuivant(panelHeight, panelWidth);
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
		this.setSize(500, 500);
		this.setContentPane(panelPrincipal);
		this.setVisible(true);

	}

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

	public class EcouteurBoutonMotTrouve implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			motConstruit = textField.getText().toUpperCase();
			KeyEnter();
			remplirPanelScoreMots();
		}
	}

	public void KeyEnter() {
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
				new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe",
						"Trop court, les mots nécessitent au moins 3 caractères", 4);
			}

			// for (String motDonne : motsDejaDonnes) {
			// motDonnee.setText(motDonne);
			// }
		} else {

			new ChangerUnLabelUnMoment(labelInfo, "Tapez un mot qui existe", "Vous n'avez pas construit de mot", 3);
		}

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
				Thread.sleep(temps * 800);
				jl.setText("");
				jl.setText(sAvant);
				textField.setText("");

			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}

	}

	public void afficherPanelCentral(int hauteur, int largeur) {

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

	public void remplirPanelCentral() {

		panelGrilleLettre.setPreferredSize(new Dimension(300, 300));
		panelGrilleLettre.setLayout(new GridLayout(4, 4));

		labelManche = new JLabel();
		affichLabelManche();

		grille = new GrilleLettres(tailleGrille);
		verif = new Verifications(grille);
		tabgrille = grille.getTabCharGrille();
		affichTabLettres(panelGrilleLettre, tailleGrille, panelHeight, panelWidth);

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

	public void afficherPanelScore(Joueur[] joueurs, int hauteur, int largeur) {

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

	public void remplirPanelScore() {

		if ((joueurs[tour % config.getNbJoueurs()].getScore() >= config.getScoreMax())
				&& (tour / config.getNbJoueurs() >= config.getNbMancheMax())) {
			System.out.println("Score final : \n");
			finPartieEffacer();
			declarerGagnant(joueurs);
		} else {

			tour++;
			manche = (tour / config.getNbJoueurs()) + 1;
			nomJoueur.setText(" A " + joueurs[tour % config.getNbJoueurs()].getName() + " de jouer");

		}
		System.out.println(joueurs[tour % config.getNbJoueurs()].getScore() + " <-/-> " + config.getScoreMax());
		System.out.println(tour / config.getNbJoueurs() + " <-/-> " + config.getNbMancheMax());

		panelPointJoueur.add(boutonFinTour, BorderLayout.SOUTH);
		panelPointJoueur.add(nomJoueur, BorderLayout.NORTH);
	}

	public void remplirPanelScoreMots() {

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		listModel.addElement("Mots déjà donnés :" );
		for(int index=0; index<motsDejaDonnes.size(); index++)
		{

		     listModel.addElement(motsDejaDonnes.get(index));
		}
		
		
		listeMotDonnes.setModel(listModel);
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer)listeMotDonnes.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JLabel labelo = new JLabel("totot");
		
		panelPointJoueur.add(listeMotDonnes, BorderLayout.CENTER);

	}

	public void afficherPanelJoueurSuivant(int hauteur, int largeur) {
		nomJoueurSuivant = new JLabel();
		nomJoueurSuivant.setHorizontalAlignment(JLabel.CENTER);
		nomJoueurSuivant.setVerticalAlignment(JLabel.CENTER);
		nomJoueurSuivant.setBorder(BorderFactory.createBevelBorder(1));
		panelJoueurSuivant.add(nomJoueurSuivant, BorderLayout.NORTH);
	}

	public void remplirPanelJoueurSuivant() {
		nomJoueurSuivant.setText(joueurs[(tour + 1) % config.getNbJoueurs()].getName() + " est le suivant");
	}

	public void getTailleFenetre() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		height = (int) dimension.getHeight();
		width = (int) dimension.getWidth();
	}

	public void affichTabLettres(JPanel panel, int tailleGrille, int hauteur, int largeur) {
		panel.removeAll();
		tab = new JLabel[tailleGrille][tailleGrille];
		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y] = createJLabel();
				remplirTabLettres(tab[i][y], i, y);
				panel.add(tab[i][y]);
			}
		}

	}

	public void affichLabelManche() {
		labelManche.setText("Manche n° " + manche);
	}

	public void effacerLabelManche() {
		labelManche.setText("        ");
	}

	public void effacerTabLettres() {

		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y].setText("");

			}
		}

	}

	public void effacerScore() {
		panelPointJoueur.removeAll();
	}

	public void finPartieEffacer() {
		boutonFinTour.setEnabled(false);
		boutonMotTrouve.setEnabled(false);
		effacerLabelManche();
		labelManche.setText("Fin de Partie");
		labelInfo.setText("");
		textField.setEnabled(false);
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

	public static String enleverAccents(String mot) {
		return Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static void main(String[] args) {
		new AffichageIU(new Config(4, 2, 4, 0));
	}

}
