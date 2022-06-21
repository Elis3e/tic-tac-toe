package morpion.fx.modele;

import javafx.beans.property.*;

public class ModeleMorpionFX implements SpecifModeleMorpions {

	// Variables d'instance
	private Etat etatDuJeu;

	private ReadOnlyIntegerWrapper[][] plateau;

	private ReadOnlyIntegerWrapper nbCoups;

	public ReadOnlyIntegerProperty nbCoupsProperty() {
		return nbCoups.getReadOnlyProperty();
	}

	@Override
	public int getNombreCoups() {
		return nbCoups.intValue();
	}

	public void setNombreCoups(int n) {
		this.nbCoups.setValue(n);
	}

	// Symbole désignant le joueur courant
	private StringProperty symboleJoueurCourant;

	public StringProperty symboleJoueurCourantProperty() {
		return this.symboleJoueurCourant;
	}

	// Accesseurs "Java Bean" sur la valeur encapsulée
	public String getSymboleJoueurCourant() {
		return symboleJoueurCourant.getValue();
	}

	private void setSymboleJoueurCourant(String ch) {
		symboleJoueurCourant.setValue(ch);
	}

	public String symboleJoueur(int val) {
		switch (val) {
		case 1:
			return "x";
		case 2:
			return "o";
		default:
			return " ";
		}
	}

	// Constructeur(s)
	public ModeleMorpionFX() {
		this.etatDuJeu = /* ((int) (2 * Math.random()) == 0) ? */ Etat.J1_JOUE /* : Etat.J2_JOUE */;
		this.nbCoups = new ReadOnlyIntegerWrapper(0);
		this.symboleJoueurCourant = new SimpleStringProperty();
		this.plateau = new ReadOnlyIntegerWrapper[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.plateau[i][j] = new ReadOnlyIntegerWrapper();
			}
		}
	}

	// Accesseurs
	public ReadOnlyIntegerProperty casePlateauProperty(int ligne, int colonne) {
		return plateau[ligne - 1][colonne - 1].getReadOnlyProperty();
	}

	private int getVal(int ligne, int colonne) {
		return plateau[ligne - 1][colonne - 1].getValue();
	}

	private void setVal(int ligne, int colonne, int val) {
		plateau[ligne - 1][colonne - 1].setValue(val);
	}

	private boolean coordCorrectes(int ligne, int colonne) {
		return (0 < ligne && ligne < 4) && (0 < colonne && colonne < 4);
	}

	@Override
	public Etat getEtatJeu() {
		return this.etatDuJeu;
	}

	@Override
	public int getJoueur() {
		return this.getEtatJeu() == Etat.J1_JOUE ? 1 : this.getEtatJeu() == Etat.J2_JOUE ? 2 : 0;
	}

	@Override
	public int getVainqueur() {
		return this.getEtatJeu() == Etat.J1_VAINQUEUR ? 1 : this.getEtatJeu() == Etat.J2_VAINQUEUR ? 2 : 0;
	}

	@Override
	public boolean estFinie() {
		return this.getEtatJeu() != Etat.J1_JOUE && this.getEtatJeu() != Etat.J2_JOUE;
	}

	@Override
	public boolean estCoupAutorise(int ligne, int colonne) {
		return !this.estFinie() && coordCorrectes(ligne, colonne) && this.getVal(ligne, colonne) == 0; /* !=2 && !=1 */
	}

	@Override
	public void jouerCoup(int ligne, int colonne) {
		if (estFinie())
			throw new IllegalArgumentException("Game already over!");
		if (!estCoupAutorise(ligne, colonne))
			throw new IllegalArgumentException("Coup non autorisé à cette case (" + ligne + "," + colonne + ")");
		this.setVal(ligne, colonne, this.getJoueur());
		this.setNombreCoups(this.getNombreCoups() + 1);
		this.setSymboleJoueurCourant(this.symboleJoueur(this.getJoueur()));
		this.determinerEtatApresUnCoupJoue();
	}

	private int determinerVainqueur() {
		int produit = 1;
		// parcours lignes
		for (int ligne = 1; ligne < 4; ligne++) {
			produit = 1;
			for (int colonne = 1; colonne < 4; colonne++) {
				produit *= this.getVal(ligne, colonne);
			}
			if (produit == 8)
				return 2;
			if (produit == 1)
				return 1;
		}
		// parcours colonnes
		for (int colonne = 1; colonne < 4; colonne++) {
			produit = 1;
			for (int ligne = 1; ligne < 4; ligne++) {
				produit *= this.getVal(ligne, colonne);
			}
			if (produit == 8)
				return 2;
			if (produit == 1)
				return 1;
		}
		// parcours diagonale \
		produit = 1;
		for (int diag = 1; diag < 4; diag++) {
			produit *= this.getVal(diag, diag);
		}
		if (produit == 8)
			return 2;
		if (produit == 1)
			return 1;

		// parcours diagonale /
		produit = 1;
		for (int diag = 1; diag < 4; diag++) {
			produit *= this.getVal(diag, 4 - diag);
		}
		if (produit == 8)
			return 2;
		if (produit == 1)
			return 1;

		return 0;
	}

	private void determinerEtatApresUnCoupJoue() {
		int vainqueur = this.determinerVainqueur();
		this.etatDuJeu = this.getNombreCoups() == 9 ? Etat.MATCH_NUL
				: vainqueur == 1 ? Etat.J1_VAINQUEUR
						: vainqueur == 2 ? Etat.J2_VAINQUEUR
								: this.getEtatJeu() == Etat.J1_JOUE ? Etat.J2_JOUE : Etat.J1_JOUE;
	}

}
