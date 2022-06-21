package morpion.fx.modele;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MorpionsTest {

	SpecifModeleMorpions morpions;
	public static final int TAILLE = 3;
	public static final int NB_CASES = 9;

	@BeforeEach
	public void setUp() throws Exception {
		morpions = new ModeleMorpionFX();
	}

	@Test
	public void testInit() {
		assertTrue(!morpions.estFinie(), "Partie pas finie en début de partie");

		boolean CasestoutesAccessibles = true;
		for (int ligne = 1; ligne < 4; ligne++) {
			for (int colonne = 1; colonne < 4; colonne++) {
				CasestoutesAccessibles &= morpions.estCoupAutorise(ligne, colonne);
			}
		}
		assertTrue(CasestoutesAccessibles, "Toutes les cases sont accessibles en debut de partie");

		// Test de l'invariant de la classe
		testInvariant();
	}

	@Test
	public void testPremierCoup() {
		assertTrue(morpions.getJoueur() == 1, "Le premier joueur est le joueur " + morpions.getJoueur());
		morpions.jouerCoup(1, 1);
		assertTrue(!morpions.estFinie(), "Partie pas finie après premier coup");

		// On reteste l'invariant
		testInvariant();
	}

	private void testInvariant() {
		// Le nombre de coups est positif ou nul, et inférieur ou égal au nombre de
		// cases
		assertTrue(morpions.getNombreCoups() >= 0, "Nombre de coups >= 0");
		assertTrue(morpions.getNombreCoups() <= NB_CASES, "Nombre de coups <= " + NB_CASES);
		// ----------------------
		// SÃ‰QUENCE 3 Ã€ COMPLÃ‰TER
		// ----------------------
	}

	@Test
	public void testCoupDejaJoue() {
		morpions.jouerCoup(1, 1);
		assertTrue(!morpions.estCoupAutorise(1, 1), "La case (1,1) ne peut être jouée");
	}

	@Test
	public void testPartiePasFinie() {
		morpions.jouerCoup(1, 1);
		morpions.jouerCoup(2, 2);
		assertTrue(!morpions.estFinie(), "La partie ne peut être terminée apres 2 coups");
	}

	@Test
	public void testJoueur1gagnant() {
		morpions.jouerCoup(1, 3);// J1
		morpions.jouerCoup(1, 2);// J2
		morpions.jouerCoup(2, 1);// J1
		morpions.jouerCoup(1, 1);// J2
		morpions.jouerCoup(2, 2);// J1
		morpions.jouerCoup(3, 1);// J2
		morpions.jouerCoup(2, 3);// J1
		assertTrue(morpions.getVainqueur() == 1,
				"Apres une série de coups, le vainqueur est le joueur " + morpions.getVainqueur());
		assertTrue(morpions.estFinie(), "La parie est finie car le joueur 1 est le vainqueur");

	}

	// TODO Faire d'autres tests pour (pour les classes bogues 2 & 4)

}
