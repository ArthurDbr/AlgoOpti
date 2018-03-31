
import java.io.*;
import java.util.Scanner;

public class main{

    private static Case tabr[];
    private static ABR<Integer> abr;

    private static Boolean fin = true;
    private static Scanner scan = new Scanner(System.in);


  public static void main (String [] args) throws IOException{
      
      String nomfic;
      int select, val;
	  
      System.out.println("Entrer le nom de votre fichier ( en .txt)");
      nomfic = scan.next();
      lireFichier(nomfic);
      
      while( fin){
        System.out.println("Veuillez faire un choix");
        System.out.println(" 1 : TABR vers fichier");
        System.out.println(" 2 : afficher TABR");
        System.out.println(" 3 : Verif TABR");
        System.out.println(" 4 : Ajouter une valeur");
        System.out.println(" 5 : Supprimer une valeur");
        System.out.println(" 6 : Fusion TABR");
        System.out.println(" 7 : TABR vers ABR");

        select = scan.nextInt();
        switch (select) {
          case 1:
        	TabrToFichier(tabr);
            break;
          case 2:
            for(int i = 0; i < tabr.length; i++){
            	if( tabr[i] != null) {
            		tabr[i].draw();
            	}
            }
            break;
          case 3:
        	  System.out.println(verif(tabr));
        	  break;

          case 4 : 
        	   System.out.println("saisir la valeur a ajouter");
        	   val = scan.nextInt();
        	   ajoutValeur(val);
        	  break;
          case 5 :
        	  int x;
        	  System.out.print("Quelle est la valeur a supprimer : ");
        	  x = scan.nextInt();
        	  remove(x);
        	  break;
          case 6:
        	  fusionTABR(tabr);
        	  break;
          case 7:
        	  tabrToAbr(tabr);
        	  break;

          default :
            fin = false;
            break;
        }
      }
      scan.close();
      
      
  }
  
  public static void fusionTABR(Case tabr[]) {
	  int indice = -1;
	  int min;
	  int max;
	  String valeur;
	  String valeurs[];
	  boolean indiceOk = false;
	  while( indice < 0 || indice > tabr.length) {
		  System.out.print("Veuillez entrer l'indice : ");
		  indice = scan.nextInt();
		  indiceOk = true;
	  }
	  
	  if( indiceOk) {
		  
		  min = tabr[indice].getMin();
		  max = tabr[indice+1].getMax();
		  valeur = tabr[indice+1].getValeurs();
		  valeurs = valeur.split(":");
		  tabr[indice].setMin(min);
		  tabr[indice].setMax(max);
		  tabr[indice].addValeur(valeurs);
		  indice++;
		  for(int i = indice; i < tabr.length; i++) {
			  if( tabr[i] != null) {
				  //suppression des valeurs
				  valeur = tabr[i].getValeurs();
				  valeurs = valeur.split(":");
				  for(int j = 0; j < valeurs.length; j++){
					  tabr[i].remove(Integer.parseInt(valeurs[j]));
				  }
				  
				  if( i == tabr.length-1) {
					  tabr[i] = null;
				  }else {
					  //recup�ration de min, max et des valeurs � tabr[i+1]
					  min    = tabr[i+1].getMin();
					  max    = tabr[i+1].getMax();
					  valeur = tabr[i+1].getValeurs();
					  valeurs = valeur.split(":");
					  tabr[i].setMin(min);
					  tabr[i].setMax(max);
					  tabr[i].addValeur(valeurs);
				  }
			  }
		  }
	  }
  }
  
  public static boolean verif(Case tabr[]) {
	  boolean verif = true;
	  int intervalle = 0;
	  String valeur;
	  String valeurs[];
	  for(int i = 0; i < tabr.length; i++) {
		  if( tabr[i] != null) {
			  if(tabr[i].getMin() >= tabr[i].getMax()) 
				  verif = false;
			  if( i > 0 ) {
				  if( tabr[i].getMin() <= intervalle)  verif = false;  
			  }
			  valeur = tabr[i].getValeurs();
			  valeurs = valeur.split(":");
			  for(int j = 0; j < valeurs.length; j++) {
				  if(Integer.parseInt(valeurs[j]) < tabr[i].getMin() ||
					Integer.parseInt(valeurs[j]) > tabr[i].getMax()) {
					  verif = false;
				  }
			  }
			  intervalle = tabr[i].getMax();
		  }
	  }
	  
	  return verif;
  }
  
  
  public static void tabrToAbr(Case tabr[]) {
	  String val="";
	  String split[];
	  abr = new ABR();
	  for(int i = 0; i < tabr.length; i++) {
		  if( tabr[i] != null) {
			  val = tabr[i].getValeurs();
			  split = val.split(":");
			  for(int j = 0; j < split.length; j++){
			     abr.ajoutValeur(Integer.parseInt(split[j]));
			  }
		  }
	  }
	  
	  System.out.println(abr.toString());
	  
  }
  
  public static  boolean TabrToFichier(Case tabr[]) throws IOException{
	    String nomfic;
	    Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom du fichier (sera au format .txt)");
        nomfic = sc.next();
        nomfic += ".txt";

	    try
	    {
	    	PrintWriter pWriter = new PrintWriter(new FileWriter(nomfic, true));
	    	for(int i = 0; i < tabr.length; i++) {
	    		if( tabr[i] != null) {
	    			pWriter.print(tabr[i].getMin()+":"+tabr[i].getMax()+";"+tabr[i].getValeurs()+"\n");
	    		}
	    	}
	        pWriter.close() ;

	    }
	    catch (FileNotFoundException exception)
	    {
	        System.out.println ("Le fichier n'a pas ete trouve");
	    }
	    return true;
	  }

  public static  boolean lireFichier(String nomfic){
    int deb;
    int fin;
    String split[];
    String splitIndice[];
    String splitValeur[];
    tabr = new Case[getNbLigne()];
    try
    {
        File f = new File(nomfic + ".txt");
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader (fr);

        try
        {
            String line = br.readLine();
            int i = 0;
            while (line != null)
            {
                split = line.split(";");
                splitIndice = split[0].split(":");
                splitValeur = split[1].split(":");
                deb = Integer.parseInt(splitIndice[0]);
                fin = Integer.parseInt(splitIndice[1]);

                tabr[i] = new Case(deb, fin, splitValeur);
                i++;
                line = br.readLine();
            }

            br.close();
            fr.close();
        }
        catch (IOException exception)
        {
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
    catch (FileNotFoundException exception)
    {
        System.out.println ("Le fichier n'a pas ete trouve");
    }
      return true;
  }

  public static int getNbLigne(){
    int nbLignes = 0;
    try
    {
        File f = new File("fichier.txt");
        FileReader fr = new FileReader (f);
        BufferedReader br = new BufferedReader (fr);

        try
        {
            String line = br.readLine();;
            while (line != null){
                line = br.readLine();
                nbLignes++;
            }
            br.close();
            fr.close();
        }
        catch (IOException exception)
        {
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
    }
    catch (FileNotFoundException exception)
    {
        System.out.println ("Le fichier n'a pas été trouvé");
    }
      return nbLignes;
  }
  
  public static int bonneCase(int v) {
	  int i = 0;
	  while (i < tabr.length) {
		  if( tabr[i] != null) {
			  if ((v >= tabr[i].getMin()) && (v <= tabr[i].getMax())) {
				  return i;
			  }else {
				  i++;
			  }
		  }
	  }
	  if ( (tabr[i] != null) && (v >= tabr[i].getMin()) && (v <= tabr[i].getMax())) {
		  return i;
	  }else {
		  return -1;
	  }
	  
  }
  
  public static void ajoutValeur(int v) {
	  int i;
	  if((i = bonneCase(v)) != -1) {
	  	tabr[bonneCase(v)].ajoutValeur(v);
	  }
  }
  
  
  
  public static void remove(int v) {
	  int c;
	  if (( c = bonneCase(v)) != -1 ) {
		  tabr[bonneCase(v)].remove(c);
	  }else {
		  System.out.print("la valeur n existe pas dans le tabr");
	  }
  }

  

}
