package iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import com.sun.glass.events.MouseEvent;

import boggle.jeu.Config;
import boggle.jeu.Joueur;
import boggle.mots.GrilleLettres;

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
	JButton[][] tab;
	char[][] tabgrille;
	String motConstruit = "";

	private GrilleLettres grille;
	private ArrayList<String> motsDejaDonnes;
	private Joueur[] joueurs;
	private Config config;

	Box panneauRadio = Box.createVerticalBox();
	ButtonGroup group = new ButtonGroup();
	JRadioButton btn2Joueurs = new JRadioButton("2 joueurs", true);
	JRadioButton btn3Joueurs = new JRadioButton("3 joueurs", false);
	JRadioButton btn4Joueurs = new JRadioButton("4 joueurs", false);
	JRadioButton btn5Joueurs = new JRadioButton("5 joueurs", false);
	JRadioButton btn6Joueurs = new JRadioButton("6 joueurs", false);

	Font fontLettres = new Font("sansserif", Font.BOLD, 18);// changement de font si besoin

	// Constructeur de ta fenêtre
	public AffichageIU(Config config) {
		// Paramètres (tu règles selon tes envies)
		super("Boggle Le Jeu");

		this.config = config;
		this.joueurs = config.getJoueurs();
		grille = new GrilleLettres(config.getTailleGrille());
		tabgrille = grille.getTabCharGrille();

		tailleFenetre();
		// this.setSize(width, height);
		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		// this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		// Par défaut, on place le bouton dans le premier panel qui est dans la fenêtre

		panel3.setLayout(null); // layout a null pour position à la main

		tailleJPanel3();

		affichTabLettres(panel3, 4, panelHeight, panelWidth);

		splitPane45 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel4, panel5);
		splitPane45.setResizeWeight(.80d);
		splitPane45.setEnabled(false);

		splitPane3s45 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel3, splitPane45);
		// splitPane.setDividerLocation(.8d);
		splitPane3s45.setResizeWeight(.75d);
		splitPane3s45.setEnabled(false);

		this.panel3.setBackground(Color.lightGray);
		this.panel4.setBackground(Color.blue);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.pack();
		this.setSize(width, height);
		this.setContentPane(splitPane3s45);

		this.setVisible(true);

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
		tab = new JButton[tailleGrille][tailleGrille];
		int debutGauche = (largeur - (tailleGrille * 50)) / 2;
		int debutHaut = ((hauteur * 4 / 5) - (tailleGrille * 50)) / 2;
		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y < tailleGrille; y++) {
				tab[i][y] = createJButton();// on crée les JLabel et on met dans tab
				remplirTabLettres(tab[i][y], i, y);
				tab[i][y].setBounds((i * 50) + debutGauche, (y * 50) + debutHaut, 50, 50);
				panel.add(tab[i][y]);
				
			}
		}
	}

	public void remplirTabLettres(JButton bouton, int x, int y) {

		bouton.setText(tabgrille[x][y] + "");
		bouton.setFont(fontLettres);
		bouton.setHorizontalAlignment(SwingConstants.CENTER);
		bouton.setVerticalAlignment(SwingConstants.CENTER);
		bouton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		bouton.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  if(bouton.isSelected()) {
				  bouton.setSelected(false);
				  //bouton.setEnabled(false);
				  bouton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			  }
			  else {
				  bouton.setSelected(true);
				  //bouton.setEnabled(true);
				  bouton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
				  motConstruit+=e.getActionCommand();
				  System.out.println(motConstruit);
			  }
			
		  }
		});

	}

	// Ecouteur des radio bouton nobre joueurs
	public class EcouteurRadioBoutonChanger implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			AbstractButton aButton = (AbstractButton) clic.getSource();
			System.out.println("Selected: " + aButton.getText());

		}

	}

	public JButton createJButton() {
		JButton jl = new JButton();

		return jl;
	}

	public static void main(String[] args) {
		new AffichageIU(new Config(4, 2, 4, 0));
	}

}
