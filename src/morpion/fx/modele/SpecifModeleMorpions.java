package morpion.fx.modele;

public interface SpecifModeleMorpions
{
	//-----------------------------------------------------------------------------------------------------------
	/* Taille du plateau */
	public static final int TAILLE = 3;

	//-----------------------------------------------------------------------------------------------------------
	/* Nombre de joueurs */
	public static final int NBJOUEURS = 2;

	//-----------------------------------------------------------------------------------------------------------
	/* SpecifMorpions.Etat : type énuméré des états possibles du jeu :
	 * J1_JOUE      : le joueur 1 est le prochain à jouer
	 * J2_JOUE      : le joueur 1 est le prochain à jouer
	 * MATCH_NUL    : jeu fini, pas de vainqueur
	 * J1_VAINQUEUR : jeu fini, le  vainqueur est 1e joueur 1
	 * J2_VAINQUEUR : jeu fini, le  vainqueur est 1e joueur 2
	*/
	public static enum Etat {J1_JOUE, J2_JOUE, MATCH_NUL, J1_VAINQUEUR, J2_VAINQUEUR};

	//-----------------------------------------------------------------------------------------------------------
	/* getEtatJeu() : état actuel du jeu
	 * @return une des valeurs du type énuméré SpecifMorpions.Etat
	 */
	public abstract Etat getEtatJeu();

	//-----------------------------------------------------------------------------------------------------------
	/* getJoueur() : numéro du joueur courant
	 * @return numéro du prochain joueur (1 ou 2), ou 0 si le jeu est fini
	 */
	public abstract int getJoueur() ;

	//-----------------------------------------------------------------------------------------------------------
	/* getVainqueur() : numéro du vainqueur
	 * @return numéro du vainqueur (1 ou 2), ou 0 s'il n'y a pas, ou pas encore, de vainqueur
	 */
	public abstract int getVainqueur() ;

	//-----------------------------------------------------------------------------------------------------------
	/* getNombreCoups() : nombre de coups joués
	 * @return nombre de coups joués
	 */
	public abstract int getNombreCoups();

	//-----------------------------------------------------------------------------------------------------------
	/* estFinie() : détermine si la partie est terminée ou non
	 * @return vrai si et seulement si getEtatJeu() est dans {MATCH_NUL, J1_VAINQUEUR, J2_VAINQUEUR}
	 */
	public abstract boolean estFinie();

	//-----------------------------------------------------------------------------------------------------------
	/* estCoupAutorise() : valide le coup (ligne, colonne) sans le jouer
	 * @param ligne : numéro de ligne
	 * @param colonne : numéro de colonne
	 * @return true si le coup est autorisé, false sinon
	 */
	public abstract boolean estCoupAutorise(int ligne, int colonne);

	//-----------------------------------------------------------------------------------------------------------
	/* jouerCoup() : joue le coup (ligne, colonne)
	 * @param ligne : numéro de ligne
	 * @param colonne : numéro de colonne
	 * @pre ! this.estFinie() ;
	 * @pre this.estCoupAutorise(ligne, colonne)
	 */
	public abstract void jouerCoup(int ligne, int colonne);

	// @Override
	// public abstract Object clone() ;
}
