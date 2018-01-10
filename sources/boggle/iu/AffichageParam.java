package boggle.iu;

import javax.swing.*;

import com.sun.javafx.iio.ImageLoader;

import boggle.jeu.Config;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AffichageParam extends JFrame {

	Font font = new Font("Monospaced", Font.PLAIN, 12);// changement de font si besoin
	int nombreJoueur = 0;
	int tailleGrille = 0;
	int nombreManche = 0;
	int nombreScore = 0;
	JLabel labelNbreScore;


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

	private void showDialog() {

//		// création de la fenètre dialog
		JDialog dialog = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);

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
	
		
		JComboBox comboNombreJoueur = new JComboBox();
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
	
		
		JComboBox comboTaillegrille = new JComboBox();
		comboTaillegrille.setFont(font);
		comboTaillegrille.addItem("choisissez une taille de grille");
		for (int i = 4; i < 7; i++)
			comboTaillegrille.addItem(i);
		((JLabel) comboTaillegrille.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		comboTaillegrille.setPreferredSize(dimCombo);
		panelTailleGrille.add(comboTaillegrille);

		JPanel panelNbreManche = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 21));
		panelNbreManche.setBackground(Color.white);


		JComboBox comboNombreManche = new JComboBox();
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
		JComboBox comboNombreScore = new JComboBox();
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
					Config config = new Config(tailleGrille, nombreJoueur, nombreManche, nombreScore);
					RunAff.config = config;
					new AffichageIU(config);
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

	public void fermerLeFenetre() {
		this.setVisible(false);
		this.dispose();// Pour libérer les ressources.
	}


	public static void main(String[] args) {
		new AffichageParam();
	}
}
