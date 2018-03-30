
import java.io.*;
import java.util.Scanner;

public class main{

    private static Case tabr[];
    private static Boolean fin = true;
    private static Scanner scan = new Scanner(System.in);


  public static void main (String [] args) throws IOException{
      
      String nomfic;
      int select;
      while( fin){
        System.out.println("Veuillez faire un choix");
        System.out.println(" 1 : fichier vers TABR");
        System.out.println(" 2 : afficher TABR");
        System.out.println(" 3 : TABR vers fichier");
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
          default :
            fin = false;
            break;
        }
      }
      scan.close();
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
	    		pWriter.print(tabr[i].getMin()+":"+tabr[i].getMax()+";"+tabr[i].getAllNoeud()+"\n");
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
        System.out.println ("Le fichier n'a pas été trouvé");
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
  
  public int bonneCase(int v) {
	  int i = 0;
	  while (i < tabr.length) {
		  if ( !(v >= tabr[i].getMin()) && (v <= tabr[i].getMax())) {
			  i++;
		  }else {
			  return i;
		  }
	  }
	  if ((v >= tabr[i].getMin()) && (v >= tabr[i].getMax())) {
		  return i;
	  }else {
		  return -1;
	  }
	  
  }
  
  public void supprimerValeur(int v) {
	  int c = 0;
	  if (( c = bonneCase(v)) != -1 ) {
		  tabr[c].supprimerValeur(c);
	  }else {
		  System.out.print("la valeur n existe pas dans le tabr");
	  }
  }


}
