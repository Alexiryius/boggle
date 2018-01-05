package iu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Test extends JFrame {

	Font font = new Font("Monospaced", Font.PLAIN, 12);// changement de font si besoin

	public Test() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // pour inserer un panel au centre avec 20 pixels de
																// margin H et V

		JPanel panel = new JPanel(new BorderLayout(10, 20)); // panel principal à inserer 10 pour espacer entre WEST et
																// CENTER, 20 pour espacer entre CENTER et SOUTH
		panel.setPreferredSize(new Dimension(450, 350));// taille preferee du panel principal
		add(panel);

		/**** creation des éléments ****/

		JLabel labelPrenom = new JLabel("Nombre de Joueur");
		labelPrenom.setFont(font);
		JLabel labelNom = new JLabel("Nombre de Manche");
		labelNom.setFont(font);
		JLabel labelSexe = new JLabel("Score a Atteindre");
		labelSexe.setFont(font);
		JLabel labelDate = new JLabel("Taille de la Grille");
		labelDate.setFont(font);
		JLabel labelNbreScore = new JLabel("Choisissez un score vous empêche de jouer par manche");
		labelNbreScore.setFont(font);

		JPanel panelNbreJoueur = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 21));// on mets textPrenom dans un JPanel
																					// avec LEFT pour coller à gauche et
																					// preferredSize fonctionne
		JComboBox comboNombreJoueur = new JComboBox();
		comboNombreJoueur.setFont(font);
		comboNombreJoueur.addItem("choisissez nombre");
		for (int i = 2; i < 6; i++)
			comboNombreJoueur.addItem(i);
		//comboNombreJoueur.setOpaque(true);
		((JLabel)comboNombreJoueur.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelNbreJoueur.add(comboNombreJoueur);
	

		JPanel panelNbreManche = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 21));// on mets textPrenom dans un JPanel
																					// avec LEFT pour coller à gauche et
																					// preferredSize fonctionne
		JComboBox comboNombreManche = new JComboBox();
		comboNombreManche.setFont(font);
		comboNombreManche.addItem("choisissez nombre");
		comboNombreManche.addItem(0);
		for (int i = 2; i < 11; i++)
			comboNombreManche.addItem(i);
		((JLabel)comboNombreManche.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelNbreManche.add(comboNombreManche);

		JPanel panelScore = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 21));// on mets textPrenom dans un JPanel avec
																				// LEFT pour coller à gauche et
																				// preferredSize fonctionne
		
		labelNbreScore.setFont(font);
		JComboBox comboNombreScore = new JComboBox();
		comboNombreScore.setFont(font);
		comboNombreScore.addItem("choisissez nombre");
		for (int i = 10; i < 201; i++)
			comboNombreScore.addItem(i);
		((JLabel)comboNombreScore.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		panelScore.add(comboNombreScore);

		JPanel panelTailleGrille = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 21));// on mets textPrenom dans un
																						// JPanel avec LEFT pour coller
																						// à gauche et preferredSize
																						// fonctionne
		JComboBox comboTaillegrille = new JComboBox();
		comboTaillegrille.setFont(font);
		comboTaillegrille.addItem("choisissez une taille");
		for (int i = 4; i < 7; i++)
			comboTaillegrille.addItem(i);
		((JLabel)comboTaillegrille.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelTailleGrille.add(comboTaillegrille);


		/**** fin de la création des éléments ****/
		
		/**** début de la création des listener ****/
		
		comboNombreManche.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboNombreManche.getSelectedItem().toString().equals("choisissez nombre")) {
					comboNombreScore.setEnabled(true);
				} else {
					comboNombreScore.setEnabled(false);
				}
				//System.out.println(arg0);
			}
		});
		comboNombreScore.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboNombreScore.getSelectedItem().toString().equals("choisissez nombre")) {
					comboNombreManche.setEnabled(true);
				} else {
					comboNombreManche.setEnabled(false);
				}
				//System.out.println(arg0);
			}
		});
		
		/**** fin de la création des listener ****/
		
		JPanel panelNorth= new JPanel();
		
		panelNorth.add(labelNbreScore);
		panel.add(labelNbreScore, BorderLayout.NORTH);
		
		JPanel panelWest = new JPanel(new GridLayout(4, 1));// panelWest à l'ouest du borderlayout. c'est une grille
															// d'une colonne avec 4 lignes pour les labels
		panelWest.add(labelPrenom);
		panelWest.add(labelNom);
		panelWest.add(labelSexe);
		panelWest.add(labelDate);
		panel.add(panelWest, BorderLayout.WEST);// ajout du panelWest au panel principal

		JPanel panelCentre = new JPanel(new GridLayout(4, 1));// panelCentre au centre du borderlayout. c'est une grille
			
		// d'une colonne avec 4 lignes
	
		panelCentre.add(panelNbreJoueur);
		panelCentre.add(panelNbreManche);
		panelCentre.add(panelScore);
		panelCentre.add(panelTailleGrille);
		panel.add(panelCentre, BorderLayout.CENTER);// ajout du panelCentre au panel principal

		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));// panel sud pour les 2 boutons, 60
																					// pixels entre chaque
		JButton boutonEnregistrer = new JButton("Annuler");
		JButton boutonAnnuler = new JButton("Lancer la partie");
		panelSouth.add(boutonEnregistrer);
		panelSouth.add(boutonAnnuler);
		panel.add(panelSouth, BorderLayout.SOUTH);// ajout du panelSouth au panel principal

		pack();// java propose la meilleure taille en fonction des elements
		setVisible(true);
	}

	// Ecouteur des radio bouton nobre joueurs
	class EcouteurRadioBoutonChanger implements ActionListener {
		public void actionPerformed(ActionEvent clic) {
			AbstractButton aButton = (AbstractButton) clic.getSource();
			System.out.println("Selected: " + aButton.getText());

		}

	}

	public static void main(String[] args) {
		Test t = new Test();
	}
}
