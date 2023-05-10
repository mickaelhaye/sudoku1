package Sudoku_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author Mickael Hay�
 *
 */

public class Recherches {

	/**
	 * D�claration de variables
	 */

	static int[][] m_tLigne = new int[9][9];
	static int[][] m_tColonne = new int[9][9];
	static int[][] m_tCadre = new int[9][9];
	static Cases[][] m_tGrille = new Cases[9][9];
	static int m_iNombreCaseOk = 0;
	static List<Solution> listSolution;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	/**
	 * public static void main(String[] args) m�thode principale dans la boucle
	 * while il y a l'algorithme de recherche basic, on essaie de remplir des cases
	 * en regardant les chiffres non pr�sent dans la ligne et colonne et grille 3*3
	 * si on arrive pas � trouver de solution on va dans la m�thode remplirCases qui
	 * permet de cr�er toutes les solutions possibles
	 * 
	 * @param args rien
	 */
	public static void main(String[] args) {
		// recup�ration du fichier texte
		m_tLigne = lectureFichierTexte();
		m_tColonne = recupererColonneFichier();
		m_tCadre = recupererCadreFichier();

		while (!fin()) {

			// on rempli les cases
			remplirCases();

			// recherche valeur
			recherche();

			// recherche si validation valeur //mise � jour des tableaux
			if (validation()) {
				continue;
			}

			// on test si on avance
			if (!testAvance()) {
				// on sort
				break;
				// if(forcageValeur()) {
				// continue;
				// }
			}
		}
		remplirCases();
		/// si on a pas r�ussi � trouver
		listSolution = new ArrayList<Solution>();
		rechercheSolution();

		System.out.println("-----Solution trouv�e-----");

		for (int i = 0; i < listSolution.size(); i++) {
			if (i == 2) {
				break;
			}
			System.out.println("------SOLUTION " + (i + 1) + " ------");
			String sVal = "";
			for (int j = 0; j < listSolution.get(i).m_tLigne.length; j++) {

				for (int k = 0; k < listSolution.get(i).m_tLigne[j].length; k++) {
					String sVal1 = Integer.toString(listSolution.get(i).m_tLigne[j][k]);
					sVal = sVal + sVal1 + "|";
				}
				System.out.println(sVal);
				sVal = "";
			}

		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * public static int[][] lectureFichierTexte() m�thode pour la lecture du
	 * fichier texte contenant la grille
	 * 
	 * @return tableau ligne
	 */
	public static int[][] lectureFichierTexte() { /// lecture fichier texte
		int[][] ligne = new int[9][9];
		File file = new File("grille1.txt");

		Stack stack = new Stack();

		try {
			String[] m_tSGrille = new String[10];
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line = "";

			while ((line = bufferedReader.readLine()) != null) {
				if (line.charAt(0) != '#') {
					stack.push(line);
					System.out.println(line);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.printf("le fichier %s n'a pas �t� trouv�.", file.toString());
		} catch (IOException e) {
			System.err.printf("Impossible de lire le contenu du fichier %s", file.toString());
		}

		for (int i = 0; i < stack.size(); i++) {
			String text = (String) stack.get(i);
			text.length();
			for (int j = 0; j < text.length(); j++) {
				if (text.charAt(j) == '.') {
					ligne[i][j] = 0;
				} else {
					ligne[i][j] = Character.getNumericValue(text.charAt(j));
				}
			}

		}

		return ligne;

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static void rechercheSolution() m�thode pour cr�er et tester toutes
	 * les solutions possibles
	 */
	public static void rechercheSolution() {
		/// Stockage de la solution

		listSolution.add(new Solution(m_tLigne));
		for (int i = 0; i < m_tGrille.length; i++) {
			for (int j = 0; j < m_tGrille[i].length; j++) {
				if (m_tGrille[i][j] != null) {
					if (!m_tGrille[i][j].isM_bCaseOk()) {
						if (m_tGrille[i][j].nombreValeurPossible() >= 0) {
							int[] tiValeurPossible = m_tGrille[i][j].getTiValeurPossible();
							int itailleListSolution = listSolution.size();
							for (int l = 0; l < itailleListSolution; l++) {
								for (int k = 0; k < tiValeurPossible.length; k++) {
									if (tiValeurPossible[k] != 0) {
										int[][] tiCop3 = new int[9][9];
										int[][] tiCop4 = listSolution.get(l).getTLigne();
										for (int w = 0; w < tiCop4.length; w++) {
											tiCop3 = tiCop4.clone();
										}
										Solution newSolution = new Solution(tiCop3);

										// listSolution.add(new Solution(listSolution.get(l).getTLigne()));
										int iValrajout = tiValeurPossible[k];
										int iNumLigne = m_tGrille[i][j].getM_iNumLigne();
										int iNumColonne = m_tGrille[i][j].getM_iNumColonne();
										newSolution.majValeur(iValrajout, iNumLigne, iNumColonne);
										if (!newSolution.testSolutionLigneOK()) {
											// Mauvaise solution
											continue;
										}
										if (!newSolution.testSolutionColonneOK()) {
											// Mauvaise solution
											continue;
										}
										if (!newSolution.testSolutionCadreOK()) {
											// Mauvaise solution
											continue;
										}

										if (newSolution.testSolutiontrouvee()) {
											// solution trouv�e
										}
										listSolution.add(newSolution);
										for (int w = 0; w < listSolution.size(); w++) {
										}

									}
								}

							}
							for (int l = 0; l < itailleListSolution; l++) {
								listSolution.remove(0);
							}
							m_tGrille[i][j].setTiValeurPossibleTo0();
							m_tGrille[i][j].setM_bCaseOk(true);
						}
					}
				}
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static boolean testAvance() m�thode pour tester si on avcance dans la
	 * recherche
	 *
	 * @return est ce que la recherche progresse?
	 */
	public static boolean testAvance() {
		int iNombreValOk = 0;
		for (int i = 0; i < m_tGrille.length; i++) {
			for (int j = 0; j < m_tGrille[i].length; j++) {
				if (m_tGrille[i][j] != null) {
					if (m_tGrille[i][j].isM_bCaseOk()) {
						iNombreValOk = iNombreValOk + 1;
						// System.out.println("test "+ i + "_"+j +"_");
					}
				}
			}
		}

		if (iNombreValOk == m_iNombreCaseOk) {
			m_iNombreCaseOk = iNombreValOk;
			return false;
		}
		m_iNombreCaseOk = iNombreValOk;
		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static boolean fin() Pour tester si la grille est compl�tement
	 * remplie.
	 * 
	 * @return fin
	 */
	public static boolean fin() {
		for (int i = 0; i < m_tGrille.length; i++) {
			for (int j = 0; j < m_tGrille[i].length; j++) {
				if (m_tGrille[i][j] == null) {
					return false;
				}
				if (!m_tGrille[i][j].isM_bCaseOk()) {
					return false;
				}
			}
		}
		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean validation() {
		for (int i = 0; i < m_tGrille.length; i++) {
			for (int j = 0; j < m_tGrille[i].length; j++) {
				if (m_tGrille[i][j].validationValeur()) {
					int iNumLigne = m_tGrille[i][j].getM_iNumLigne();
					int iNumColonne = m_tGrille[i][j].getM_iNumColonne();
					int iNumCadre = m_tGrille[i][j].getM_iNumCadre();
					int iValeur = m_tGrille[i][j].getM_iValeur();
					majValeurTableau(iNumLigne, iNumColonne, iNumCadre, iValeur);
					return true;

				}
			}
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static void majValeurTableau(int _iNumLigne,int _iNumColonne,int
	 * _iNumCadre,int _iValeur) mise � jour de la valeur dans les tableaux
	 * 
	 * @param _iNumLigne   num�ro de ligne
	 * @param _iNumColonne num�ro de colonne
	 * @param _iNumCadre   num�ro de cadre
	 * @param _iValeur     valeur
	 */
	public static void majValeurTableau(int _iNumLigne, int _iNumColonne, int _iNumCadre, int _iValeur) {
		m_tLigne[_iNumLigne][_iNumColonne] = _iValeur;
		m_tColonne[_iNumColonne][_iNumLigne] = _iValeur;
		int _iNumCadre2 = 0;
		/// recherche num�ro de cadre 2
		if ((_iNumLigne == 0) || (_iNumLigne == 3) || (_iNumLigne == 6)) {
			if ((_iNumColonne == 0) || (_iNumColonne == 3) || (_iNumColonne == 6)) {
				_iNumCadre2 = 0;
			}
			if ((_iNumColonne == 1) || (_iNumColonne == 4) || (_iNumColonne == 7)) {
				_iNumCadre2 = 1;
			}
			if ((_iNumColonne == 2) || (_iNumColonne == 5) || (_iNumColonne == 8)) {
				_iNumCadre2 = 2;
			}
		}
		if ((_iNumLigne == 1) || (_iNumLigne == 4) || (_iNumLigne == 7)) {
			if ((_iNumColonne == 0) || (_iNumColonne == 3) || (_iNumColonne == 6)) {
				_iNumCadre2 = 3;
			}
			if ((_iNumColonne == 1) || (_iNumColonne == 4) || (_iNumColonne == 7)) {
				_iNumCadre2 = 4;
			}
			if ((_iNumColonne == 2) || (_iNumColonne == 5) || (_iNumColonne == 8)) {
				_iNumCadre2 = 5;
			}
		}
		if ((_iNumLigne == 2) || (_iNumLigne == 5) || (_iNumLigne == 8)) {
			if ((_iNumColonne == 0) || (_iNumColonne == 3) || (_iNumColonne == 6)) {
				_iNumCadre2 = 6;
			}
			if ((_iNumColonne == 1) || (_iNumColonne == 4) || (_iNumColonne == 7)) {
				_iNumCadre2 = 7;
			}
			if ((_iNumColonne == 2) || (_iNumColonne == 5) || (_iNumColonne == 8)) {
				_iNumCadre2 = 8;
			}
		}
		m_tCadre[_iNumCadre][_iNumCadre2] = _iValeur;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static void recherche() recherche du num�ro de cadre
	 */
	public static void recherche() {
		int iNumCadre = 0;
		boolean bChgtLigne = false;
		boolean bChgtColonne = false;
		boolean bChgtLigne2 = false;
		boolean bChgtLigne5 = false;

		for (int i = 0; i < m_tGrille.length; i++) {
			bChgtLigne = true;
			if (i == 3) {
				bChgtLigne2 = true;
			}
			if (i == 6) {
				bChgtLigne5 = true;
			}
			for (int j = 0; j < m_tGrille[i].length; j++) {
				if ((j == 3) || (j == 6)) {
					bChgtColonne = true;
				}

				// calcul num�ro de cadre
				if (bChgtLigne && !bChgtLigne2 && !bChgtLigne5) {
					iNumCadre = 0;
				}
				if (bChgtLigne && bChgtLigne2 && !bChgtLigne5) {
					iNumCadre = 3;
				}
				if (bChgtLigne && bChgtLigne2 && bChgtLigne5) {
					iNumCadre = 6;
				}
				if (!bChgtLigne && bChgtColonne) {
					iNumCadre = iNumCadre + 1;
				}
				bChgtColonne = false;
				bChgtLigne = false;

				// fin calcul num�ro de cadre
				if (!m_tGrille[i][j].isM_bCaseOk()) {
					// System.out.println("recherche valeur Impossible "+ i + "_"+j +"_"+
					// iNumCadre);
					m_tGrille[i][j].rechercheValImpossible(m_tLigne[i], m_tColonne[j], m_tCadre[iNumCadre]);
				}
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static void remplirCases() on rempli les cases
	 */
	public static void remplirCases() {
		int iNumCadre = 0;
		boolean bChgtLigne = false;
		boolean bChgtColonne = false;
		boolean bChgtLigne2 = false;
		boolean bChgtLigne5 = false;

		for (int i = 0; i < m_tGrille.length; i++) {
			bChgtLigne = true;
			if (i == 3) {
				bChgtLigne2 = true;
			}
			if (i == 6) {
				bChgtLigne5 = true;
			}
			for (int j = 0; j < m_tGrille[i].length; j++) {
				if ((j == 3) || (j == 6)) {
					bChgtColonne = true;
				}

				// calcul num�ro de cadre
				if (bChgtLigne && !bChgtLigne2 && !bChgtLigne5) {
					iNumCadre = 0;
				}
				if (bChgtLigne && bChgtLigne2 && !bChgtLigne5) {
					iNumCadre = 3;
				}
				if (bChgtLigne && bChgtLigne2 && bChgtLigne5) {
					iNumCadre = 6;
				}
				if (!bChgtLigne && bChgtColonne) {
					iNumCadre = iNumCadre + 1;
				}
				bChgtColonne = false;
				bChgtLigne = false;

				Cases grille = new Cases(i, j, iNumCadre);
				m_tGrille[i][j] = grille;
				m_tGrille[i][j].setM_iValeur(m_tLigne[i][j]);
				if (m_tGrille[i][j].getM_iValeur() != 0) {
					m_tGrille[i][j].setM_bCaseOk(true);
				}
			}

		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static int[][]recupererColonneFichier() cr�ation tableau colonnes
	 * venant du fichier texte
	 * 
	 * @return tableau colonne
	 */
	public static int[][] recupererColonneFichier() { /// m�thode pour r�cup�rer les colonnes dans fichier texte

		int[][] Colonne = { { 0, 3, 0, 9, 0, 8, 0, 0, 6 }, { 6, 0, 0, 0, 7, 5, 0, 0, 0 }, { 0, 0, 7, 6, 3, 0, 4, 9, 0 },
				{ 0, 0, 2, 3, 0, 0, 1, 0, 4 }, { 0, 0, 4, 0, 1, 6, 2, 0, 0 }, { 0, 8, 5, 4, 0, 2, 0, 3, 0 },
				{ 4, 9, 6, 0, 2, 3, 0, 0, 1 }, { 2, 0, 0, 0, 0, 4, 9, 6, 3 }, { 0, 0, 0, 0, 0, 9, 8, 0, 0 } };

		for (int i = 0; i < m_tLigne.length; i++) {
			for (int j = 0; j < m_tLigne[i].length; j++) {
				Colonne[j][i] = m_tLigne[i][j];
			}
		}
		return Colonne;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public static int[][]recupererCadreFichier() m�thode pour construire les
	 * cadres venant du fichier texte(cadre 3x3)
	 * 
	 * @return tableau cadre
	 */
	public static int[][] recupererCadreFichier() { /// m�thode pour r�cup�rer les cadres dans fichier texte

		int[][] Cadre = { { 0, 6, 0, 3, 0, 0, 0, 0, 7 }, { 0, 0, 0, 0, 0, 8, 2, 4, 5 }, { 4, 2, 0, 9, 0, 0, 6, 0, 0 },
				{ 9, 0, 6, 0, 7, 3, 8, 5, 0 }, { 3, 0, 4, 0, 1, 0, 0, 6, 2 }, { 0, 0, 0, 2, 0, 0, 3, 4, 9 },
				{ 0, 0, 4, 0, 0, 9, 6, 0, 0 }, { 1, 2, 0, 0, 0, 3, 4, 0, 0 }, { 0, 9, 8, 0, 6, 0, 1, 3, 0 } };

		int iNumCadre = 0;
		boolean bChgtLigne = false;
		boolean bChgtColonne = false;
		boolean bChgtLigne2 = false;
		boolean bChgtLigne5 = false;
		for (int i = 0; i < m_tLigne.length; i++) {
			bChgtLigne = true;
			if (i == 3) {
				bChgtLigne2 = true;
			}
			if (i == 6) {
				bChgtLigne5 = true;
			}

			for (int j = 0; j < m_tLigne[i].length; j++) {
				if ((j == 3) || (j == 6)) {
					bChgtColonne = true;
				}

				// calcul num�ro de cadre
				if (bChgtLigne && !bChgtLigne2 && !bChgtLigne5) {
					iNumCadre = 0;
				}
				if (bChgtLigne && bChgtLigne2 && !bChgtLigne5) {
					iNumCadre = 3;
				}
				if (bChgtLigne && bChgtLigne2 && bChgtLigne5) {
					iNumCadre = 6;
				}
				if (!bChgtLigne && bChgtColonne) {
					iNumCadre = iNumCadre + 1;
				}
				bChgtColonne = false;
				bChgtLigne = false;

				int _iNumCadre2 = 0;
				/// recherche num�ro de cadre 2
				if ((i == 0) || (i == 3) || (i == 6)) {
					if ((j == 0) || (j == 3) || (j == 6)) {
						_iNumCadre2 = 0;
					}
					if ((j == 1) || (j == 4) || (j == 7)) {
						_iNumCadre2 = 1;
					}
					if ((j == 2) || (j == 5) || (j == 8)) {
						_iNumCadre2 = 2;
					}
				}
				if ((i == 1) || (i == 4) || (i == 7)) {
					if ((j == 0) || (j == 3) || (j == 6)) {
						_iNumCadre2 = 3;
					}
					if ((j == 1) || (j == 4) || (j == 7)) {
						_iNumCadre2 = 4;
					}
					if ((j == 2) || (j == 5) || (j == 8)) {
						_iNumCadre2 = 5;
					}
				}
				if ((i == 2) || (i == 5) || (i == 8)) {
					if ((j == 0) || (j == 3) || (j == 6)) {
						_iNumCadre2 = 6;
					}
					if ((j == 1) || (j == 4) || (j == 7)) {
						_iNumCadre2 = 7;
					}
					if ((j == 2) || (j == 5) || (j == 8)) {
						_iNumCadre2 = 8;
					}
				}

				Cadre[iNumCadre][_iNumCadre2] = m_tLigne[i][j];
			}

		}
		return Cadre;
	}

}
