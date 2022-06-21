package morpion.fx.v2;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import morpion.fx.modele.*;

public class ControleurV2 {

	private ModeleMorpionFX modele;

	@FXML
	private Label labelNbCoups;

	// @FXML
	// private Label labelJoueur;

	@FXML
	private Label labelEtatJeu;

	@FXML // pour rendre la variable visible depuis SceneBuilder
	private GridPane grille;

	@FXML // pour rendre la méthode visible depuis SceneBuilder
	private void initialize() {
		
		this.modele = new ModeleMorpionFX();
		
		modele.nbCoupsProperty().addListener((obsValue, oldValue, newValue) -> majNbCoups(newValue.intValue()));
		
		modele.setNombreCoups(-1); modele.setNombreCoups(0); 

		recalculerLabelEtatJeu();

		// labelJoueur.textProperty().bind(modele.symboleJoueurCourantProperty());

		for (Node enfant : grille.getChildren()) {
			
			enfant.setOnMouseClicked(e -> this.clicBouton(e));

			// enfants de "grille": des Labels, mais pas que...
			if (enfant instanceof Label) {
				Label l = (Label) enfant;
				int ligne = (int) l.getProperties().get("gridpane-row") + 1;
				int colonne = (int) l.getProperties().get("gridpane-column") + 1;
				modele.casePlateauProperty(ligne, colonne).addListener((obs, oldV, newV) -> {
					l.setText(modele.symboleJoueur(newV.intValue()));
				});
			}
		}
	}

	private void clicBouton(MouseEvent e) {
		Node n = (Node) e.getSource();
		Integer ligne = ((Integer) n.getProperties().get("gridpane-row")) + 1;
		Integer colonne = ((Integer) n.getProperties().get("gridpane-column")) + 1;
		modele.jouerCoup(ligne, colonne);
		System.out.println("Coup joué : " + ligne + "/" + colonne);
		System.out.println("résultat: " + modele.getEtatJeu());

		recalculerLabelEtatJeu();
	}

	private void recalculerLabelEtatJeu() {
		switch (modele.getEtatJeu()) {
		case J1_JOUE:
			labelEtatJeu.setText("C'est au tour de J1");
			break;
		case J2_JOUE:
			labelEtatJeu.setText("C'est au tour de J2");
			break;
		case J1_VAINQUEUR:
			labelEtatJeu.setText("Le gagnant est J1");
			break;
		case J2_VAINQUEUR:
			labelEtatJeu.setText("Le gagnant est J2");
			break;
		case MATCH_NUL:
			labelEtatJeu.setText("Match nul");
			break;
		}
	}

	private void majNbCoups(int nb) {
		if (nb == 0) {
			labelNbCoups.setText("aucun coup joué");
		} else {
			String ch;
			if (nb == 1)
				ch = " coup joué";
			else
				ch = " coups joués";
			labelNbCoups.setText(Integer.toString(nb) + ch);
		}
	}

}
