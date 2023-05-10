package Sudoku_1;


/**
 * class permettant de stocker une solution au sudoku
 * @author Mickael Hayé
 *
 */

public class Solution {
	
	int [][] m_tLigne = {	{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
	int [][] m_tColonne = {	{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
	int [][] m_tCadre = {	{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0}};
	
	/**
	 * public Solution (int [][] _tLigne)
	 * constructeur
	 * @param _tLigne tableau ligne
	 */
	public Solution (int [][] _tLigne) {
		m_tLigne = _tLigne.clone();
		for (int i=0; i<_tLigne.length; i++)
		{
			m_tLigne[i]=_tLigne[i].clone();
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public void majValeur (int _iValrajout,int _iNumLigne,int _iNumColonne)
	 * @param _iValrajout Nouvelle valeur
	 * @param _iNumLigne numéro de ligne
	 * @param _iNumColonne numéro de colonne
	 */
	public void majValeur (int _iValrajout,int _iNumLigne,int _iNumColonne) {
		m_tLigne[_iNumLigne][_iNumColonne]=_iValrajout;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public int [][] getTLigne() 
	 * @return tableau ligne
	 */
	public int [][] getTLigne() {
		return m_tLigne;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * public boolean testSolutionCadreOK() 
	 * @return cadre OK
	 */
	public boolean testSolutionCadreOK() {
		//onTest si il y a 2 fois la même valeur dans la colonne
		//déjà il faut refaire les cadres
		int iNumCadre =0;
		boolean bChgtLigne = false;
		boolean bChgtColonne = false;
		boolean bChgtLigne2 = false;
		boolean bChgtLigne5 = false;
		for (int i=0; i<m_tLigne.length; i++)
		{
			bChgtLigne = true;
			if (i==3) {
				bChgtLigne2 = true;
			}
			if (i==6) {
				bChgtLigne5 = true;
			}
			
			for (int j=0; j<m_tLigne[i].length; j++)
			{
				if ((j==3) || (j==6)) {
					bChgtColonne = true;
				}
				
				//calcul numéro de cadre
				if(bChgtLigne && !bChgtLigne2 && !bChgtLigne5) {
					iNumCadre=0;
				}
				if(bChgtLigne && bChgtLigne2 && !bChgtLigne5) {
					iNumCadre=3;
				}
				if(bChgtLigne && bChgtLigne2 && bChgtLigne5) {
					iNumCadre=6;
				}
				if(!bChgtLigne && bChgtColonne) {
					iNumCadre=iNumCadre+1;
				}
				bChgtColonne = false;
				bChgtLigne = false;
			
				int _iNumCadre2 = 0;
				///recherche numéro de cadre 2
				if((i==0)||(i==3)||(i==6))
				{
					if((j==0)||(j==3)||(j==6))
					{
						_iNumCadre2 = 0; 
					}
					if((j==1)||(j==4)||(j==7))
					{
						_iNumCadre2 = 1; 
					}
					if((j==2)||(j==5)||(j==8))
					{
						_iNumCadre2 = 2; 
					}
				}
				if((i==1)||(i==4)||(i==7))
				{
					if((j==0)||(j==3)||(j==6))
					{
						_iNumCadre2 = 3; 
					}
					if((j==1)||(j==4)||(j==7))
					{
						_iNumCadre2 = 4; 
					}
					if((j==2)||(j==5)||(j==8))
					{
						_iNumCadre2 = 5; 
					}
				}
				if((i==2)||(i==5)||(i==8))
				{
					if((j==0)||(j==3)||(j==6))
					{
						_iNumCadre2 = 6; 
					}
					if((j==1)||(j==4)||(j==7))
					{
						_iNumCadre2 = 7; 
					}
					if((j==2)||(j==5)||(j==8))
					{
						_iNumCadre2 = 8; 
					}
				}
				
				m_tCadre[iNumCadre][_iNumCadre2]= m_tLigne[i][j];
			}
			
		}
		
		boolean bSolOk = true;
		for (int i=0; i<m_tCadre.length; i++)
		{
			int tITest[] = new int[]{1,2,3,4,5,6,7,8,9};
			boolean bTestOk = false;
			for (int j=0; j<m_tCadre[i].length; j++)
			{
				if(m_tCadre[i][j]!=0)
				{
					for (int k=0; k<tITest.length; k++)
					{
						bTestOk = false;
						if (m_tCadre[i][j] == tITest[k])
						{
							bTestOk = true;
							tITest[k] = 0;
							break;
						}
					}
					if(!bTestOk)
					{
						//mauvaise solution
						bSolOk=false;
						break;
					}
				}
			}
			if(!bTestOk)
			{
				//mauvaise solution
				break;
			}
		}
		return bSolOk;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public boolean testSolutionColonneOK()
	 * @return la colonne est OK
	 */
	public boolean testSolutionColonneOK() {
		//onTest si il y a 2 fois la même valeur dans la colonne
		//déjà il faut refaire les colonnes
		for (int i=0; i<m_tLigne.length; i++)
		{
			for (int j=0; j<m_tLigne[i].length; j++)
			{
				m_tColonne[j][i] = m_tLigne[i][j];
			}
		}
		
		boolean bSolOk = true;
		for (int i=0; i<m_tColonne.length; i++)
		{
			int tITest[] = new int[]{1,2,3,4,5,6,7,8,9};
			boolean bTestOk = false;
			for (int j=0; j<m_tColonne[i].length; j++)
			{
				if(m_tColonne[i][j]!=0)
				{
					for (int k=0; k<tITest.length; k++)
					{
						bTestOk = false;
						
						if (m_tColonne[i][j] == tITest[k])
						{
							bTestOk = true;
							tITest[k] = 0;
							break;
						}
					}
					if(!bTestOk)
					{
						//mauvaise solution
						bSolOk=false;
						break;
					}
				}
			}
			if(!bTestOk)
			{
				//mauvaise solution
				break;
			}
		}
		return bSolOk;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * testSolutionLigneOK()
	 * @return la ligne OK
	 */
	public boolean testSolutionLigneOK() {
		//onTest si il y a 2 fois la même valeur dans la ligne
		
		boolean bSolOk = true;
		for (int i=0; i<m_tLigne.length; i++)
		{
			int tITest[] = new int[]{1,2,3,4,5,6,7,8,9};
			boolean bTestOk = false;
			for (int j=0; j<m_tLigne[i].length; j++)
			{
				if(m_tLigne[i][j]!=0)
				{
					for (int k=0; k<tITest.length; k++)
					{
						bTestOk = false;
						if (m_tLigne[i][j] == tITest[k])
						{
							bTestOk = true;
							tITest[k] = 0;
							break;
						}
					}
					if(!bTestOk)
					{
						//mauvaise solution
						bSolOk=false;
						break;
					}
				}
			}
			if(!bTestOk)
			{
				//mauvaise solution
				break;
			}
		}
		return bSolOk;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * public boolean testSolutiontrouvee() 
	 * @return Solution trouvée
	 */
	public boolean testSolutiontrouvee() {
		//onTest si on a trouvé
		
		boolean bSolOk = true;
		for (int i=0; i<m_tLigne.length; i++)
		{
			for (int j=0; j<m_tLigne[i].length; j++)
			{
				if (m_tLigne[i][j] == 0)
				{
					bSolOk = false;
					break;
				}
			}
			if(!bSolOk)
			{
				// solution pas encore trouvé
				break;
			}
		}
		return bSolOk;
	}
}
