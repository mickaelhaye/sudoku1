package Sudoku_1;

/**
 * 
 * @author Mickael Hay�
 * Dans une case on stocke les valeurs possible par rapport aux valeurs d�j� renseign�es dans la ligne o� se situe la case = tableau PossLigne
 * Dans une case on stocke les valeurs possible par rapport aux valeurs d�j� renseign�es dans la colonne o� se situe la case = tableau PossColonne
 * Dans une case on stocke les valeurs possible par rapport aux valeurs d�j� renseign�es dans le cadre o� se situe la case = tableau PossCadre (3x3)
 *
 */
public class Cases {
	//donn�es d'une case 
	private boolean  m_bCaseOk = false; //valeur de la case trouv�e

	private int m_tPossLigne[] =new int [9];//possibilit� par rapport aux valeurs d�j� trouv�es dans la ligne
	private int m_tPossColonne[] =new int [9];//possibilit� par rapport aux valeurs d�j� trouv�es dans la colonne
	private int m_tPossCadre[] =new int [9];//possibilit� par rapport aux valeurs d�j� trouv�es dans le cadre
	private int m_tImpossLigne[] =new int [9];//impossibilit� par rapport aux valeurs d�j� trouv�es dans la ligne
	private int m_tImpossColonne[] =new int [9];//impossibilit� par rapport aux valeurs d�j� trouv�es dans la colonne
	private int m_tImpossCadre[] =new int [9];//impossibilit� par rapport aux valeurs d�j� trouv�es dans le cadre
	private int m_tPoss[] =new int [9];//possibilit� pour la case
	private int m_iNumLigne = 0;//Num�ro de ligne
	private int m_iNumColonne = 0;//Num�ro de colonne
	private int m_iNumCadre = 0;///Num�ro de cadre
	private int m_iValeur = 0;///Valeur de la case
	private int m_tiValeurPossible[] = {1,2,3,4,5,6,7,8,9};//valeur possible 

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public Cases (int _iNumLigne, int _iNumColonne, int _iNumCadre)
	 * Constructeur
	 * @param _iNumLigne num�ro de ligne
	 * @param _iNumColonne num�ro de colonne
	 * @param _iNumCadre num�ro de cadre
	 */
	public Cases (int _iNumLigne, int _iNumColonne, int _iNumCadre) {
		this.m_iNumLigne = _iNumLigne;
		this.m_iNumColonne = _iNumColonne;
		this.m_iNumCadre = _iNumCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * getM_iValeur()
	 * @return retourne de la valeur de la case
	 */
	public int getM_iValeur() {
		return m_iValeur;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_iValeur(int m_iValeur)
	 * @param m_iValeur set la valeur de la case
	 */
	public void setM_iValeur(int m_iValeur) {
		this.m_iValeur = m_iValeur;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public boolean isM_bCaseOk()
	 * @return si la case est OK
	 */
	public boolean isM_bCaseOk() {
		return m_bCaseOk;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_bCaseOk(boolean m_bCaseOk)
	 * @param m_bCaseOk la case est ok
	 */
	public void setM_bCaseOk(boolean m_bCaseOk) {
			this.m_bCaseOk = m_bCaseOk;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tPossLigne()
	 * @return tableau ligne
	 */
	public int[] getM_tPossLigne() {
		return m_tPossLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tPossLigne(int[] m_tPossLigne)
	 * @param m_tPossLigne set un tableau ligne
	 */
	public void setM_tPossLigne(int[] m_tPossLigne) {
		this.m_tPossLigne = m_tPossLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tPossColonne() 
	 * @return set un tableau colonne
	 */
	public int[] getM_tPossColonne() {
		return m_tPossColonne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tPossColonne(int[] m_tPossColonne)
	 * @param m_tPossColonne tableau possibilit� colonne
	 */
	public void setM_tPossColonne(int[] m_tPossColonne) {
		this.m_tPossColonne = m_tPossColonne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tPossCadre()
	 * @return tableau possibilit� cadre
	 */
	public int[] getM_tPossCadre() {
		return m_tPossCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tPossCadre(int[] m_tPossCadre) 
	 * @param m_tPossCadre tableau possibilit� cadre
	 */
	public void setM_tPossCadre(int[] m_tPossCadre) {
		this.m_tPossCadre = m_tPossCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tImpossLigne() 
	 * @return tableau impossibilit� ligne
	 */
	public int[] getM_tImpossLigne() {
		return m_tImpossLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tImpossLigne(int[] m_tImpossLigne)
	 * @param m_tImpossLigne tableau impossibilit� ligne
	 */
	public void setM_tImpossLigne(int[] m_tImpossLigne) {
		this.m_tImpossLigne = m_tImpossLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tImpossColonne()
	 * @return tableau impossibilit� colonne
	 */
	public int[] getM_tImpossColonne() {
		return m_tImpossColonne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tImpossColonne(int[] m_tImpossColonne)
	 * @param m_tImpossColonne tableau impossibilit� colonne
	 */
	public void setM_tImpossColonne(int[] m_tImpossColonne) {
		this.m_tImpossColonne = m_tImpossColonne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tImpossCadre()
	 * @return tableau impossibilit� cadre
	 */
	public int[] getM_tImpossCadre() {
		return m_tImpossCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tImpossCadre(int[] m_tImpossCadre)
	 * @param m_tImpossCadre tableau impossibilit� cadre
	 */
	public void setM_tImpossCadre(int[] m_tImpossCadre) {
		this.m_tImpossCadre = m_tImpossCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getM_tPoss()
	 * @return ????
	 */
	public int[] getM_tPoss() {
		return m_tPoss;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_tPoss(int[] m_tPoss)
	 * @param m_tPoss ???
	 */
	public void setM_tPoss(int[] m_tPoss) {
		this.m_tPoss = m_tPoss;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int getM_iNumLigne() 
	 * @return num�ro de ligne
	 */
	public int getM_iNumLigne() {
		return m_iNumLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_iNumLigne(int m_iNumLigne)
	 * @param m_iNumLigne num�ro de ligne
	 */
	public void setM_iNumLigne(int m_iNumLigne) {
		this.m_iNumLigne = m_iNumLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int getM_iNumColonne()
	 * @return num�ro de colonne
	 */
	public int getM_iNumColonne() {
		return m_iNumColonne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_iNumColonne(int m_iNumColonne)
	 * @param m_iNumColonne num�ro de colonne
	 */
	public void setM_iNumColonne(int m_iNumColonne) {
		this.m_iNumColonne = m_iNumColonne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int getM_iNumCadre() 
	 * @return num�ro de cadre
	 */
	public int getM_iNumCadre() {
		return m_iNumCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setM_iNumCadre(int m_iNumCadre)
	 * @param m_iNumCadre num�ro de cadre
	 */
	public void setM_iNumCadre(int m_iNumCadre) {
		this.m_iNumCadre = m_iNumCadre;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void rechercheValImpossible(int[] _ligne, int[] _colonne, int[] _cadre )
	 *
	 * @param _ligne num�ro de ligne
	 * @param _colonne num�ro de colonne
	 * @param _cadre num�ro de cadre
	 */
	public void rechercheValImpossible(int[] _ligne, int[] _colonne, int[] _cadre ) {
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			for (int j=0; j<_ligne.length; j++)
			{
				if(m_tiValeurPossible[i]==_ligne[j])
				{
					m_tiValeurPossible[i] = 0;
				}
			}
			for (int j=0; j<_colonne.length; j++)
			{
				if(m_tiValeurPossible[i]==_colonne[j])
				{
					m_tiValeurPossible[i] = 0;
				}
			}
			for (int j=0; j<_cadre.length; j++)
			{
				if(m_tiValeurPossible[i]==_cadre[j])
				{
					m_tiValeurPossible[i] = 0;
				}
			}
		}
		
		String sValPossible = ""; 
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			sValPossible = sValPossible + m_tiValeurPossible[i]+"/";
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public boolean validationValeur()
	 * @return validation de la valeur
	 */
	public boolean validationValeur() {
		int iNbrVal = 0;
		int iVal = 0;
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			if(m_tiValeurPossible[i] != 0)
			{
				iNbrVal = iNbrVal+1;
				if (iVal == 0)
				{
					iVal = m_tiValeurPossible[i];
				}
			}
		}
		if(iNbrVal==1)
		{
			setM_iValeur(iVal);
			setM_bCaseOk(true);
			return true;
		}
		return false;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public boolean forcageValeur()
	 * @return forcage de la valeur
	 */
	public boolean forcageValeur() {
		int iNbrVal = 0;
		int iVal = 0;
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			if(m_tiValeurPossible[i] != 0)
			{
				iNbrVal = iNbrVal+1;
				if (iVal == 0)
				{
					iVal = m_tiValeurPossible[i];
				}
			}
		}
		if(iNbrVal==2)
		{
			setM_iValeur(iVal);
			setM_bCaseOk(true);
			return true;
		}
		return false;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public boolean valeurTableauZero()
	 * @return tableau � 0 
	 */
	public boolean valeurTableauZero() {
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			if(m_tiValeurPossible[i] != 0)
			{
				return false;
			}
		}
		return true;
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int nombreValeurPossible()
	 * @return valeur impossible
	 */
	public int nombreValeurPossible() {
		int iNbrVal = 0;
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			if(m_tiValeurPossible[i] != 0)
			{
				iNbrVal = iNbrVal+1 ;
			}
		}
		return iNbrVal;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int[] getTiValeurPossible()
	 * @return valeur possible
	 */
	public int[] getTiValeurPossible() {
		return m_tiValeurPossible;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void setTiValeurPossibleTo0()
	 */
	public void setTiValeurPossibleTo0() {
		for (int i=0; i<m_tiValeurPossible.length; i++)
		{
			m_tiValeurPossible[i] = 0;
		}
	}
}
	
