
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class main{

    private static Case tabr[];
    private static ABR abr;

    private static Boolean fin = true;
    private static Scanner scan = new Scanner(System.in);


  public static void main (String [] args) throws IOException{
      
	  NoeudABR<Integer> n;
	  n = new NoeudABR(2);
	  n.ajoutValeur(5);
	  n.ajoutValeur(2);
	  n.ajoutValeur(15);
	  n.ajoutValeur(6);
	  n.ajoutValeur(19);
	  n.ajoutValeur(1);
	  n.ajoutValeur(3);
	  System.out.println(n.toString());
	
	  
	  ABR a;
	  a = new ABR(n);
	  a.ajoutValeur(3);
	  a.ajoutValeur(5);
	  System.out.println(a.toString());
	  a.remove(5);
	  System.out.println(a.toString());
	  
	  Case c;
	  c = new Case(1, 20, a);
	  System.out.println(c.getMin());
	  System.out.println(c.getMax());
	  System.out.println(c.getAbr().toString());
	  c.ajoutValeur(20);
	  System.out.println(c.getAbr().toString());
	  c.remove(20);
	  System.out.println(c.getAbr().toString());
	  
	  /*String nomfic;
	  System.out.println("Entrer le nom de votre fichier .txt");
      nomfic = scan.next();
      lireFichier(nomfic);
      for (int i = 0 ; i< tabr.length; i++) {
    	  tabr[i].draw();
      }
      tabr[bonneCase(78)].ajoutValeur(78);
      System.out.println("\n");
      for (int i = 0 ; i< tabr.length; i++) {
    	  tabr[i].draw();
      }*/
	  
     /* String nomfic;
      int select, val;
      while( fin){
        System.out.println("Veuillez faire un choix");
        System.out.println(" 1 : fichier vers TABR");
        System.out.println(" 2 : afficher TABR");
        System.out.println(" 3 : TABR vers fichier");

        System.out.println(" 4 : Ajouter une valeur");
        System.out.println(" 7 : Fusion TABR");
        System.out.println(" 8 : TABR vers ABR");
        select = scan.nextInt();
        switch (select) {
          case 1:
            System.out.println("Entrer le nom de votre fichier .txt");
            nomfic = scan.next();
            lireFichier(nomfic);
            break;
          case 2:
            for(int i = 0; i < tabr.length; i++){
              tabr[i].draw();
            }
            break;
          case 3:
        	  TabrToFichier(tabr);
        	  break;

          case 4 : 
        	   System.out.println("Entrer le nom de votre fichier .txt correspondant � votre tabr ");
              nomfic = scan.next();
              lireFichier(nomfic);
        	  System.out.println("saisir la valeur a ajouter");
        	  val = scan.nextInt();
        	  ajoutValeur(val);
        	  for(int i = 0; i < tabr.length; i++){
                 tabr[i].draw();
              }
        	  break;
          
          case 7:
        	  fusionTABR(tabr);
        	  break;
          case 8:
        	  tabrToAbr(tabr);
        	  break;

          default :
            fin = false;
            break;
        }
      }
      scan.close();*/
      
      
  }
  
  public static void fusionTABR(Case tabr[]) {
	  int indice;
	  int min;
	  int max;
	  String valeur;
	  String valeurs[];
	  System.out.print("Veuillez entrer l'indice : ");
	  indice = scan.nextInt();
	  min = tabr[indice].getMin();
	  max = tabr[indice+1].getMax();
	  valeur = tabr[indice+1].getValeurs();
	  valeurs = valeur.split(":");
	  tabr[indice].setMin(min);
	  tabr[indice].setMax(max);
	  tabr[indice].addValeur(valeurs);
	  for(int i = indice; i < tabr.length; i++) {
		  min    = tabr[indice+1].getMin();
		  max    = tabr[indice+1].getMax();
		  valeur = tabr[indice+1].getValeurs();
		  valeurs = valeur.split(":");
		  tabr[indice].setMin(min);
		  tabr[indice].setMax(max);
//		  tabr[indice].addValeur(valeurs);
	  }

	  

	  
  }
  
  public static boolean verifArthur(Case tabr[]) {
	  boolean verif = true;
	  int intervalle = 0;
	  for(int i = 0; i < tabr.length; i++) {
		  if(tabr[i].getMin() > tabr[i].getMax()) verif = false;
		  if( i > 0 ) {
			  if( tabr[i].getMin() < intervalle)  verif = false;  
		  }
		  intervalle = tabr[i].getMax();
	  }
	  
	  return verif;
  }
  
  public static void tabrToAbr(Case tabr[]) {
	  String val="";
	  String split[];
	  abr = new ABR();
	  for(int i = 0; i < tabr.length; i++) {
		  val = tabr[i].getValeurs();
		  split = val.split(":");
		  for(int j = 0; j < split.length; j++){
		     abr.ajoutValeur(Integer.parseInt(split[i]));
		  }
	  }
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
	    		pWriter.print(tabr[i].getMin()+":"+tabr[i].getMax()+";"+tabr[i].getValeurs()+"\n");
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
		  if ((v >= tabr[i].getMin()) && (v <= tabr[i].getMax())) {
			  return i;
		  }else {
			  i++;
		  }
	  }
	  if ((v >= tabr[i].getMin()) && (v <= tabr[i].getMax())) {
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
  
  
  
  public void remove(int v) {
	  int c = 0;
	  if (( c = bonneCase(v)) != -1 ) {
		  tabr[c].remove(c);
	  }else {
		  System.out.print("la valeur n existe pas dans le tabr");
	  }
  }

  /*public void fusionCasesTabr(int pos) {
	  if (pos < tabr.length-1) {
		  Case c = new Case(tabr[pos].getMin(), tabr[pos+1].getMax(),
				  tabr[pos+1].getAbr().fusion(tabr[pos+1]));
	  }
  }*/
  
  public void supprimerCase(int i) {
	  
  }

}
