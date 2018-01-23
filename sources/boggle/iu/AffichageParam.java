package boggle.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import boggle.jeu.Config;
import boggle.jeu.Joueur;

/**
 * @author radixr rogeza
 *classe qui permet de lancer la fenètre de paramètrage du jeu en interface graphique
 */
@SuppressWarnings("serial")
public class AffichageParam extends JFrame {

	Font font = new Font("Monospaced", Font.PLAIN, 12);// changement de font si besoin
	int nombreJoueur = 0;
	int tailleGrille = 0;
	int nombreManche = 0;
	int nombreScore = 0;
	JLabel labelNbreScore;

	JDialog dialog;
	@SuppressWarnings("rawtypes")
	JComboBox comboNombreJoueur;
	@SuppressWarnings("rawtypes")
	JComboBox comboTaillegrille;
	@SuppressWarnings("rawtypes")
	JComboBox comboNombreManche;
	@SuppressWarnings("rawtypes")
	JComboBox comboNombreScore;

	/**
	 * constructeur qui va afficher la fenêtre avec le bouton du jeu 
	 * et lancer la partie avec la fenetre de paramètre
	 */
	public AffichageParam() {

		setBounds(500, 300, 800, 500);
		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new FlowLayout());
		JButton bLancerJeu = new JButton("Lancer le jeu");
		add(bLancerJeu);
		bLancerJeu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showDialog();
			}
		});
	}

	/**
	 * méthode qui construit la fenêtre dialog pour rentrer les paramètre du jeu
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void showDialog() {

//		// création de la fenètre dialog
		dialog = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);

		dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // pour inserer un panel au centre avec 20 pixels de
		// margin H et V
		JPanel panel = new JPanel(new BorderLayout(10, 20)); // panel principal à inserer 10 pour espacer entre WEST et
		// CENTER, 20 pour espacer entre CENTER et SOUTH
		panel.setPreferredSize(new Dimension(600, 350));// taille preferee du panel principal
		dialog.add(panel);
		Dimension dimCombo = new Dimension(300,25);

		/**** creation des éléments ****/

		JLabel labelNombreJoueur= new JLabel("Nombre de Joueur");
		labelNombreJoueur.setFont(font);
		JLabel labelNombreManche = new JLabel("Nombre de Manche");
		labelNombreManche.setFont(font);
		JLabel labelScore = new JLabel("Score a Atteindre");
		labelScore.setFont(font);
		JLabel labelTailleGrille = new JLabel("Taille de la Grille");
		labelTailleGrille.setFont(font);

		labelNbreScore = new JLabel("Choisir un score vous empêche de jouer par manche");
		ImageIcon icon = new ImageIcon(
				new ImageIcon("config/warn.png").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
		labelNbreScore.setIcon(icon);
		labelNbreScore.setFont(font);

		JPanel panelNbreJoueur = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 21));
		panelNbreJoueur.setBackground(Color.white);
	
		
		 comboNombreJoueur = new JComboBox();
		comboNombreJoueur.setFont(font);
		comboNombreJoueur.addItem("choisissez nombre de joueur");
		for (int i = 2; i < 6; i++)
			comboNombreJoueur.addItem(i);
		// comboNombreJoueur.setOpaque(true);
		((JLabel) comboNombreJoueur.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboNombreJoueur.setPreferredSize(dimCombo);
		panelNbreJoueur.add(comboNombreJoueur);

		JPanel panelTailleGrille = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 21));
		panelTailleGrille.setBackground(Color.white);
	
		
		 comboTaillegrille = new JComboBox();
		comboTaillegrille.setFont(font);
		comboTaillegrille.addItem("choisissez une taille de grille");
		for (int i = 4; i < 7; i++)
			comboTaillegrille.addItem(i);
		((JLabel) comboTaillegrille.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboTaillegrille.setPreferredSize(dimCombo);
		panelTailleGrille.add(comboTaillegrille);

		JPanel panelNbreManche = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 21));
		panelNbreManche.setBackground(Color.white);


		 comboNombreManche = new JComboBox();
		comboNombreManche.setFont(font);
		comboNombreManche.addItem("choisissez nombre de manche");
		for (int i = 2; i < 11; i++)
			comboNombreManche.addItem(i);
		((JLabel) comboNombreManche.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboNombreManche.setPreferredSize(dimCombo);
		panelNbreManche.add(comboNombreManche);

		JPanel panelScore = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 21));
		panelScore.setBackground(Color.white);
	

		labelNbreScore.setFont(font);
		 comboNombreScore = new JComboBox();
		comboNombreScore.setFont(font);
		comboNombreScore.addItem("choisissez un score à atteindre");
		for (int i = 10; i < 201; i++)
			comboNombreScore.addItem(i);
		((JLabel) comboNombreScore.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboNombreScore.setPreferredSize(dimCombo);
		panelScore.add(comboNombreScore);

		JButton boutonAnnuler = new JButton("Annuler");
		JButton boutonLancer = new JButton("Lancer la partie");

		/**** fin de la création des éléments ****/

		/**** début de la création des listener ****/

		boutonLancer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent clic) {
				int messageInt = 0;
				String messageText = "";
				
				if (nombreJoueur == 0) messageInt += 1;
				if (tailleGrille == 0) messageInt += 3;
				if (nombreScore == 0 && nombreManche == 0) messageInt += 5;
				
				if (messageInt > 0) {
					if (messageInt == 1) {
						messageText = "Vous devez choisir le nombre de joueur !";
					}
					if (messageInt == 3) {
						messageText = "Vous devez choisir la taille de la grille !";
					}
					if (messageInt == 5) {
						messageText = "Vous devez choisir le nombre de manche ou le score !";
					}
					if (messageInt == 4) {
						messageText = "Vous devez choisir le nombre de joueur et la taille de la grille !";
					}
					if (messageInt == 6) {
						messageText = "Vous devez choisir le nombre de joueur et le nombre de manche ou le score !";
					}
					if (messageInt == 8) {
						messageText = "Vous devez choisir la taille de la grille et le nombre de manche ou le score !";
					}
					if (messageInt == 9) {
						messageText = "Vous devez choisir des paramètres !";
					}

					labelNbreScore.setText(messageText);
				} else {
					fermerLeFenetre();
					
					Joueur[] joueurs = new Joueur[nombreJoueur];

					
					switch (nombreJoueur) {
					case 2:
						joueurs[0]= new Joueur("riri");
						joueurs[1]= new Joueur("fifi");
						break;
					 case 3: 
						  joueurs[0]= new Joueur("riri");
						  joueurs[1]= new Joueur("fifi");
						  joueurs[2]= new Joueur("loulou");
						  break;
					 case 4:
						  joueurs[0]= new Joueur("riri");
						  joueurs[1]= new Joueur("fifi");
						  joueurs[2]= new Joueur("loulou");
						  joueurs[3]= new Joueur("coco");
						  break;
					 case 5:        
						  joueurs[0]= new Joueur("riri");
						  joueurs[1]= new Joueur("fifi");
						  joueurs[2]= new Joueur("loulou");
						  joueurs[3]= new Joueur("coco");
						  joueurs[4]= new Joueur("lulu");
						  break;  
					 case 6:        
						  joueurs[0]= new Joueur("riri");
						  joueurs[1]= new Joueur("fifi");
						  joueurs[2]= new Joueur("loulou");
						  joueurs[3]= new Joueur("coco");
						  joueurs[4]= new Joueur("lulu");
						  joueurs[5]= new Joueur("jacques");
						  break;  
					 default:
						  joueurs[0]= new Joueur("riri");
						  joueurs[1]= new Joueur("fifi");
						  break;
					}

					Config config = new Config(tailleGrille, nombreJoueur, nombreManche, nombreScore, joueurs);
					new AffichageJeu(config);
				}

			}
		});
		
		boutonAnnuler.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent clic) {
				  dialog.dispose();
				
			}});

		comboNombreJoueur.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboNombreJoueur.getSelectedItem().toString().equals("choisissez nombre de joueur")) {
					nombreJoueur = 0;
				} else {
					nombreJoueur = Integer.parseInt(comboNombreJoueur.getSelectedItem().toString());
				}
			}
		});
		comboTaillegrille.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboTaillegrille.getSelectedItem().toString().equals("choisissez une taille de grille")) {
					tailleGrille = 0;
				} else {
					tailleGrille = Integer.parseInt(comboTaillegrille.getSelectedItem().toString());
				}
			}
		});

		comboNombreManche.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboNombreManche.getSelectedItem().toString().equals("choisissez nombre de manche")) {
					nombreManche = 0;
					comboNombreScore.setEnabled(true);
				} else {
					nombreManche = Integer.parseInt(comboNombreManche.getSelectedItem().toString());
					nombreScore = 0;
					comboNombreScore.setEnabled(false);

				}
			}
		});
		comboNombreScore.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboNombreScore.getSelectedItem().toString().equals("choisissez un score à atteindre")) {
					nombreScore = 0;
					comboNombreManche.setEnabled(true);
				} else {
					nombreScore = Integer.parseInt(comboNombreScore.getSelectedItem().toString());
					nombreManche = 0;
					comboNombreManche.setEnabled(false);

				}
			}
		});

		/**** fin des listener ****/

		JPanel panelNorth = new JPanel();
		panelNorth.add(labelNbreScore);
		panel.add(labelNbreScore, BorderLayout.NORTH);

		JPanel panelWest = new JPanel(new GridLayout(4, 1));
		labelNombreJoueur.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombreManche.setHorizontalAlignment(SwingConstants.CENTER);
		labelScore.setHorizontalAlignment(SwingConstants.CENTER);
		labelTailleGrille.setHorizontalAlignment(SwingConstants.CENTER);
		panelWest.add(labelNombreJoueur);
		panelWest.add(labelNombreManche);
		panelWest.add(labelScore);
		panelWest.add(labelTailleGrille);
		panelWest.setBackground(Color.white);
		panel.add(panelWest, BorderLayout.WEST);// ajout du panelWest au panel principal

		
		JPanel panelCentre = new JPanel(new GridLayout(4, 1));
		panelCentre.add(panelNbreJoueur);
		panelCentre.add(panelNbreManche);
		panelCentre.add(panelScore);
		panelCentre.add(panelTailleGrille);
		panelCentre.setBackground(Color.white);
		panel.add(panelCentre, BorderLayout.CENTER);

		
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.LEADING, 140, 0));
		panelSouth.add(boutonAnnuler);
		panelSouth.add(boutonLancer);
		panel.add(panelSouth, BorderLayout.SOUTH);// ajout du panelSouth au panel principal

		dialog.setResizable(false);
		dialog.pack();// java propose la meilleure taille en fonction des elements
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

		
		
	}

	/**
	 *methode qui ferme la fenètre principale et ja fenetre dialog 
	 *et libère les ressources 
	 */
	public void fermerLeFenetre() {

		this.setVisible(false);
		this.dispose();// Pour libérer les ressources.
	}

	/**
	 * méthode principale de lancement
	 * @param args
	 */
	public static void main(String[] args) {
		new AffichageParam();
	}
}
