import java.io.IOException;

/**
 * Classe NoeudABR<V> : classe générique qui représente un noeud d'un arbre binaire de recherche.
 *
 * @author Arthur Debar
 * @version 2018-03-15
 *
 */
public class NoeudABR<Integer extends Comparable<Integer>> {
	private int _valeur;
	private NoeudABR<Integer> _filsG;
	private NoeudABR<Integer> _filsD;


	/**
	 * Constructeur de NoeudABR.
	 * @param v : la valeur du noeud.
	 * @param fg : le noeud racine du sous-arbre gauche du noeud.
	 * @param fd : le noeud racine du sous-arbre droit du noeud.
	 */
	public NoeudABR(int v, NoeudABR<Integer> fg, NoeudABR<Integer> fd){
		_valeur = v;
		_filsG = fg;
		_filsD = fd;
	}

	/**
	 * Constructeur de NoeudABR.
	 * @param v : la valeur du noeud.
	 */
	public NoeudABR(int v){
		this(v, null, null);
	}


	/**
	 * Getter de la valeur du noeud.
	 * @return la valeur du noeud.
	 */
	public int getValeur(){
		return _valeur;
	}

	/**
	 * Getter du fils gauche du noeud.
	 * @return le fils gauche du noeud.
	 */
	public NoeudABR<Integer> getFilsGauche(){
		return _filsG;
	}

	/**
	 * Getter du fils droit du noeud.
	 * @return le fils droit du noeud.
	 */
	public NoeudABR<Integer> getFilsDroit(){
		return _filsD;
	}


	/**
	 * Setter de la valeur du noeud.
	 * @param v : la nouvelle valeur du noeud.
	 */
	public void setValeur(int v){
		_valeur = v;
	}

	/**
	 * Setter du fils gauche du noeud.
	 * @param fg : le nouveau fils gauche du noeud.
	 */
	public void setFilsGauche(NoeudABR<Integer> fg){
		_filsG = fg;
	}

	/**
	 * Setter du fils droit du noeud.
	 * @param fd : le nouveau fils droit du noeud.
	 */
	public void setFilsDroit(NoeudABR<Integer> fd){
		_filsD = fd;
	}




	/**
	 * Méthode toString() de NoeudDico.
	 * @return une chaîne de caractères correspondant aux valeursv du sous-arbre
	 * 		   dont le noeud courant est la racine.
	 */
	public String toString(){
		String valeurs = "";

		if(_filsG != null)
			valeurs +=   _filsG.toString() + " : ";

		valeurs += _valeur;

		if(_filsD != null)
			valeurs += " : " + _filsD.toString();

		return valeurs;
	}
	
	/**
	 * Méthode toString() de NoeudDico.
	 * @return une chaîne de caractères correspondant aux valeursv du sous-arbre
	 * 		   dont le noeud courant est la racine.
	 */
	public String getNoeuds(){
		String valeurs = "";

		if(_filsG != null)
			valeurs += _filsG.getNoeuds()+ ":";

		valeurs += _valeur;

		if(_filsD != null)
			valeurs += ":" + _filsD.getNoeuds();

		return valeurs;
	}



	/* Méthodes à compléter */


	/**
	 * Méthode récursive qui ajoute une valeur dans le sous-arbre dont le noeud courant est la racine,
	 * s'il n'est pas déjà présent.
	 * @param valeur : la valeur à ajouter à l'arbre.
	 * @return le noeud correspondant soit à la valeur ajoutée, soit à la valeur déjà présente.
	 */
	public void ajoutValeur(int valeur)
	{
		if( (this._valeur - valeur)>0)
		{
			if(this._filsG!=null)
			{
				 _filsG.ajoutValeur(valeur);
			}else
			{
				_filsG=new NoeudABR<Integer>(valeur);
				
			}
		}else if((this._valeur - valeur)<0)
		{
			if(this._filsD!=null)
			{
				 _filsD.ajoutValeur(valeur);
			}else
			{
				_filsD=new NoeudABR<Integer>(valeur);

			}
		}


	}
	
	


	/**
	 * Méthode récursive qui retourne le nombre de noeuds du sous-arbre dont le noeud courant est la racine.
	 * @return le nombre de noeuds présents dans le sous-arbre courant.
	 */
	public int nbNoeuds()
	{

		if(this._filsD==null && _filsG==null)
		{
			return 1;

		}else if(_filsG!=null &&  _filsD==null)
		{
			return 1+_filsG.nbNoeuds();
		}else if(_filsD!=null  && _filsG==null)
		{
			return 1+_filsD.nbNoeuds();
		}else
			return 1+_filsG.nbNoeuds()+_filsD.nbNoeuds();
	}


	/**
	 * Méthode récursive qui recherche une valeur dans le sous-arbre
	 * dont le noeud courant est la racine.
	 * @param valeur : la valeur à chercher.
	 * @return le noeud correspondant à la valeur recherchée si elle est présente
	 * 		   dans le sous-arbre courant ; null, sinon.
	 */
	public NoeudABR<Integer> rechercheNoeud(int valeur)
	{
		if( (this._valeur - valeur)>0)
		{
			if(this._filsG!=null)
			{
				return _filsG.rechercheNoeud(valeur);
			}else
			{
				return null;
			}
		}else if((this._valeur-valeur)<0)
		{
			if(this._filsD!=null)
			{
				return _filsD.rechercheNoeud(valeur);
			}else
			{
				return null;

			}
		}else
			return this;
	}


	/**
	 * Méthode qui teste si une valeur appartient au sous-arbre dont le noeud courant est la racine.
	 * @param valeur : la valeur considérée.
	 * @return vrai si la valeur est présente dans le sous-arbre courant ; faux sinon.
	 */
	//ne sert a rien selon mon implentation
	public boolean appartient(int valeur)
	{
		return rechercheNoeud(valeur)!=null;
	}


	/**
	 * Méthode qui retourne le noeud correspondant à la première valeur du sous-arbre dont le noeud courant est la racine.
	 * @return le noeud correspondant à la première valeur du sous-arbre courant.
	 */
	public NoeudABR<Integer> premierNoeud()
	{
		if(this._filsG==null)
			return this;
		else
			return this._filsG.premierNoeud();
	}


	/**
	 * Méthode qui retourne le noeud correspondant à la dernière valeur du sous-arbre dont le noeud courant est la racine.
	 * @return le noeud correspondant à la dernière valeur du sous-arbre courant.
	 */
	public NoeudABR<Integer> dernierNoeud()
	{
		if(this._filsD==null)
			return this;
		else
			return this._filsD.premierNoeud();
	}


	/**
	 * Méthode récursive qui retourne le père du noeud considéré,
	 * dans le sous-arbre dont le noeud courant est la racine.
	 * @param noeud : le noeud considéré.
	 * @return le noeud père du noeud considéré, dans le sous-arbre courant.
	 */
	public NoeudABR<Integer> noeudPere(NoeudABR<Integer> noeud, NoeudABR<Integer> pereCour)
	{
			if( (this._valeur - noeud.getValeur())>0)
			{
				if(this._filsG!=null)
				{
					return _filsG.noeudPere(noeud,this);
				}else
				{
					return null;
				}
			}else if( (this._valeur - noeud.getValeur())<0)
			{
				if(this._filsD!=null)
				{
					return _filsD.noeudPere(noeud,this);
				}else
				{
					return null;

				}
			}else
				return pereCour;


	}



	public NoeudABR<Integer> noeudSuivant(NoeudABR<Integer> noeud, NoeudABR<Integer> suivantCour)
	{
		if( (this._valeur - noeud.getValeur())>0)
		{
			if(this._filsG!=null)
			{
				return _filsG.noeudSuivant(noeud,this);
			}else
			{
				return null;
			}
		}else if((this._valeur - noeud.getValeur())<0)
		{
			if(this._filsD!=null)
			{
				return _filsD.noeudSuivant(noeud,suivantCour);
			}else
			{
				return null;

			}
		}else
			return suivantCour;


	}


	/**
	 * Méthode récursive qui retourne le noeud précédent du noeud considéré,
	 * dans le sous-arbre dont le noeud courant est la racine.
	 * @param noeud : le noeud considéré.
	 * @return le noeud précédent du noeud considéré, dans le sous-arbre courant.
	 */
//	public NoeudABR<V> noeudPrecedent(NoeudABR<V> noeud, NoeudABR<V> precedentCour){
//
//	}


}
