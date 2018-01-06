package iu;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.w3c.dom.Document;

public class Test extends JFrame {

	private Document doc;
	public Test() {
		
		
		JButton button = new JButton("Open XML");

		this.setBounds(500,300,800,500);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);this.setVisible(true);this.setLayout(new FlowLayout());

		this.add(button);

		button.addActionListener(new ActionListener(){

		public void actionPerformed(ActionEvent e) {
			SwingWorker<Document, Void> worker = new SwingWorker<Document, Void>() {

				public Document doInBackground() {
					Document intDoc = loadXML();
					return intDoc;
				}

				public void done() {
					try {
						doc = get();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					} catch (ExecutionException ex) {
						ex.printStackTrace();
					}
				}
			};
			worker.execute();
		}

		});

		
	}
	
	
	Document loadXML() {
		System.out.println("tototo");
		return null;

	}

	public static void main(String[] args) {
		new Test();
	}
}
