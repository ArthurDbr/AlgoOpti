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
    System.out.println(this.abr.toString());
  }
  
  public String getAllNoeud() {
	  return this.abr.getValeurs();
  }


}
