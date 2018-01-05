package iu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import boggle.jeu.Config;
import boggle.jeu.Joueur;
import boggle.mots.GrilleLettres;

public class AffichageIU extends JFrame {

	// Les deux panels de ta fenetre
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel31 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JSplitPane splitPane1;
	JSplitPane splitPane2;
	int height;
	int width;
	int panelHeight;
	int panelWidth;
	JLabel[][] tab ;
	char[][] tabgrille;
	
	private GrilleLettres grille;
	private ArrayList<String> motsDejaDonnes;
	private Joueur[] joueurs;
	private Config config;
	


	// Le bouton qui va changer le panel
	JButton bChangerPanel = new JButton("lancer le jeu");

	// Constructeur de ta fenêtre
	public AffichageIU(Config config) {
		// Paramètres (tu règles selon tes envies)
		super("Boggle Le Jeu");
		
		this.config = config;
		this.joueurs = config.getJoueurs();
		grille = new GrilleLettres(config.getTailleGrille());
		tabgrille =  grille.getTabCharGrille();
		
		tailleFenetre();
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		// Ecouteur sur le bouton
		this.bChangerPanel.addActionListener(new EcouteurBoutonChanger());
		// Par défaut, on place le bouton dans le premier panel qui est dans la fenêtre

		panel3.setLayout(null); // layout a null pour position à la main
	
		tailleJPanel3();
		
		affichTabLettres(panel3,config.getTailleGrille(),panelHeight,panelWidth);
		
		

		splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel4, panel5);
		splitPane2.setResizeWeight(.80d);
		splitPane2.setEnabled(false);

		splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel3, splitPane2);
		// splitPane.setDividerLocation(.8d);
		splitPane1.setResizeWeight(.75d);
		splitPane1.setEnabled(false);

		this.panel1.add(this.bChangerPanel);
		this.setContentPane(this.panel1);
		// Couleur des panels (pour voir le changement)
		this.panel1.setBackground(Color.red);
		this.panel3.setBackground(Color.yellow);
		this.panel4.setBackground(Color.blue);

	}

	// Méthode qui change le panel de ta fenêtre
	public void changerMenu() {
		this.setContentPane(this.splitPane1);
		this.revalidate();
	}

	// Méthode récupère la taille de l'écran pour la fenètre générale
	public void tailleFenetre() {
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		height = (int) dimension.getHeight();
		width = (int) dimension.getWidth();
	}
	
	// Méthode qui donne la taille du jpanel3 principal
	public void tailleJPanel3(){
		panelHeight = height;
		panelWidth = (int) (width*(0.75));
	}
	
	public void affichTabLettres(JPanel panel, int tailleGrille, int hauteur, int largeur){
		tab = new JLabel[tailleGrille][tailleGrille];
		int debutGauche = (largeur-(tailleGrille*50))/2;
		int debutHaut =((hauteur*4/5)-(tailleGrille*50))/2;
		for (int i = 0; i < tailleGrille; i++) {
			for (int y = 0; y  < tailleGrille; y++) {
				tab[i][y] = createJLabel();// on crée les JLabel et on met dans tab
				remplirTabLettres(tab[i][y],i,y);
				
				tab[i][y].setBounds((i * 50)+debutGauche, (y * 50)+debutHaut, 50, 50);
				panel.add(tab[i][y]);
			}
		}
	}
	
	public void remplirTabLettres(JLabel label,int x, int y) {
		
		label.setText(tabgrille[x][y]+"");
	}

	// Ecouteur de ton bouton
	public class EcouteurBoutonChanger implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			// Appelle la méthode de changement de panel
			AffichageIU.this.changerMenu();
		}

	}

	public JLabel createJLabel() {
		JLabel jl = new JLabel();
		return jl;
	}

}
