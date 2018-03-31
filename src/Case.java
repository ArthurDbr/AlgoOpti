public class Case{

  private int min;
  private int max;
  private ABR abr;

  public Case(int min, int max, String valeur[]){
    this.min = min;
    this.max = max;
    this.abr = new ABR();
    for(int i = 0; i < valeur.length; i++){
      this.abr.ajoutValeur(Integer.parseInt(valeur[i]));
    }
  }
  
  public Case(int min, int max, ABR arbre){
	    this.min = min;
	    this.max = max;
	    this.abr = arbre;
	  }
  
  public void setMin(int min) {
	  this.min = min;
  }
  public void setMax( int max) {
	  this.max = max;
  }
  public void addValeur(String valeurs[]) {
	  for(int i = 0; i < valeurs.length; i++){
		 this.abr.ajoutValeur(Integer.parseInt(valeurs[i]));
	  }
  }
  public int getMin() {
	  return this.min;
  }
  public int getMax() {
	  return this.max;
  }

  public ABR getAbr(){
    return this.abr;
  }

  public void draw(){
    System.out.println("["+this.min+" : "+this.max+" ; "+this.abr.toString()+"]");
  }
  
  
  public String getValeurs() {
	  return this.abr.getValeurs();
  }
  
  public void getSuivAbr() {
	  
  }

  public void ajoutValeur(int v) {
	  if ((v >= min) && (v <= max)) {
		  this.abr.ajoutValeur(v);
	  }
  }
  
  public void supprimerValeur(int v) {
	  abr.supprimerValeur(v);
  }

}
