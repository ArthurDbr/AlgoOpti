/**
 * Classe ABR<Integer> : classe générique qui représente un arbre binaire de recherche.
 *
 * @author Arthur Debar
 * @version 2018-03-15
 *
 */
public class ABR<Integer extends Comparable<Integer>> {
	private NoeudABR<Integer> _racine;



	/**
	 * Constructeur de ABR.
	 */
	public ABR(){
		_racine = null;

	}


	/**
	 * Méthode toString() de ABR.
	 * @return une chaîne de caractères correspondant aux valeurs de l'ABR,
	 * 		   dans l'ordre correspondant au type générique V.
	 */
	public String toString(){
		String valeurs = "";

		if(!arbreVide())
			valeurs += _racine.toString();


		return valeurs;
	}
	
	
	/**
	 * Methode toString() de ABR.
	 * @return une chaine de caracteres correspondant aux valeurs de l'ABR,
	 * 		   dans l'ordre correspondant au type generique V.
	 */
	public String getValeurs(){
		String valeurs = "";

		if(!arbreVide())
			valeurs += _racine.getNoeuds();


		return valeurs;
	}




	/**
	 * Methode qui teste si l'ABR est vide.
	 * @return vrai si l'ABR est vide ; faux sinon.
	 */
	public boolean arbreVide()
	{
		return _racine==null;
	}


	/**
	 * Methode recursive qui ajoute une valeur dans l'ABR, si elle n'est pas deja� presente.
	 * @param valeur : la valeur a� ajouter à l'ABR.
	 * @return le noeud correspondant soit a� la valeur ajoutee, soit a� la valeur deja� presente.
	 */
	public void ajoutValeur(int valeur)
	{
		if (arbreVide())
		{
			this._racine=new NoeudABR<Integer>(valeur);
		}else
			this._racine.ajoutValeur(valeur);
	}
	
	public void supprimerValeur(int v) {
		
		
	}


	/**
	 * Methode recursive qui retourne le nombre de noeuds de l'ABR.
	 * @return le nombre de noeuds presents dans l'ABR.
	 */
	public int nbNoeuds()
	{
		return arbreVide()?0:_racine.nbNoeuds();
	}


	/**
	 * Methode recursive qui recherche une valeur dans l'ABR.
	 * @param valeur : la valeur a� chercher.
	 * @return le noeud correspondant à la valeur recherchee
	 * 		   si elle est presente dans l'ABR ; null, sinon.
	 */
	public NoeudABR<Integer> rechercheNoeud(int valeur)
	{
		return arbreVide()?null:_racine.rechercheNoeud(valeur);
	}



}
