package lib;
public class Libgame{

    private static int spielfeld[][];
        public static int checkwin(int toCheck[][])//Überpruft ob es einen Gewinner gibt
    {
        spielfeld = toCheck;
        return siegerueberpruefung();
    }
        public static int siegerueberpruefung()//ueberpruft ob es einen Gewinner gibt
    {
       if(wagerechtueberpruefung()!=0)//ueberprueft ob auf den wagerechten ein kein gewinner liegt wenn nicht dann:
       {
           if (wagerechtueberpruefung()==1){return 1;}//Gibt zurueck spieler 1 hat gewonnen
           else if (wagerechtueberpruefung()==2){return 2;}//Gibt zurueck spieler 2 hat gewonnen
       }
       
       if (senkrechtueberpruefung()!=0)//ueberprueft ob auf den horrizontalen ein kein gewinner liegt wenn nicht dann:
       {
           if (senkrechtueberpruefung()==1){return 1;}//Gibt zurueck spieler 1 hat gewonnen
           else if (senkrechtueberpruefung()==2){return 2;}//Gibt zurueck spieler 2 hat gewonnen
       }
       
       if(diagonalueberpruefung()!=0)//ueberprueft ob auf den diagonalen ein kein Gewinner liegt wenn nicht dann:
       {
           if (diagonalueberpruefung()==1){return 1;}//Gibt zurueck spieler 1 hat gewonnen
           else if (diagonalueberpruefung()==2){return 2;}//Gibt zurueck spieler 2 hat gewonnen
       }
       
       return 0;//gibt null zurueck wenn kein spieler gewinnen hat
    }
    private static int wagerechtueberpruefung()//ueberprueft , ob ein spieler auf einer wagerechten Ebene gewonnen hat
    {
       for(int i=6;i>=0;i--)
       {
           for(int j=0;j<=2;j++)
           {
            if (spielfeld[j][i]==1 &&
                spielfeld[(j+1)][i]==1 &&
                spielfeld[(j+2)][i]==1 &&
                spielfeld[(j+3)][i]==1) {return 1;}//guckt ob Spieler 1 gewonnen hat und gibt dies gegebenenfalls aus
            else if (spielfeld[j][i]==2 &&
                     spielfeld[(j+1)][i]==2 &&
                     spielfeld[(j+2)][i]==2 &&
                     spielfeld[(j+3)][i]==2) {return 2;}//guckt ob Spieler 2 gewonnen hat und gibt dies gegebenenfalls aus
            //j erhoeht sich immer innerhalb der if-Bedinngung um eins um zu gucken ob 4 in folge gleich sind
            
           }
           
       }
       return 0;//fals kein spieler in einer wagerechten Reihe gewonnen hat gibt er 0 fuer kein Spieler hat gewonnen zurueck.
    }
    
    private static int senkrechtueberpruefung()//ueberprueft , ob ein spieler auf einer senkrechten Ebene gewonnen hat
    {
        for(int j=5;j>=0;j--)
       {
           for(int i=0;i<=4;i++)
           {
            if (spielfeld[j][i]==1 && 
                spielfeld[j][(i+1)]==1 && 
                spielfeld[j][(i+2)]==1 &&
                spielfeld[j][(i+3)]==1) {return 1;}//guckt ob Spieler 1 gewonnen hat und gibt dies gegebenenfalls aus
            else if (spielfeld[j][i]==2 &&
                     spielfeld[j][(i+1)]==2 &&
                     spielfeld[j][(i+2)]==2 &&
                     spielfeld[j][(i+3)]==2) {return 2;}//guckt ob Spieler 2 gewonnen hat und gibt dies gegebenenfalls aus
            //i erhoeht sich immer innerhalb der if-Bedinngung um eins um zu gucken ob 4 in folge gleich sind
           }
           
       }
       return 0;//falls kein spieler in einer senkrechten Reihe gewonnen hat gibt er 0 fuer kein Spieler hat gewonnen zurueck.
    }
    
    private static int diagonalueberpruefung()//diagonalueberpruefung von links nach rechts oben nach unten und unten nach oben 
    {
        for(int i=0;i<=5;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(spielfeld[j][i]==1 &&
                   spielfeld[(j+1)][(i+1)]==1 &&
                   spielfeld[j+2][i+2]==1 &&
                   spielfeld[j+3][i+3]==1){return 1;}//guckt ob Spieler 1 gewonnen hat und gibt dies gegebenenfalls aus
                else if(spielfeld[j][i]==2 &&
                        spielfeld[(j+1)][(i+1)]==2 &&
                        spielfeld[j+2][i+2]==2 &&
                        spielfeld[j+3][i+3]==2){return 2;}//guckt ob Spieler 2 gewonnen hat und gibt dies gegebenenfalls aus
                //i und j erhoeht sich immerhalb der if bedingung,damit die diagonale ueberprueft werden kann 
            }
        }
        
        for(int i=5;i>2;i--)
        {
            for(int j=0;j<=3;j++)
            {
                if(spielfeld[j][i]==1 &&
                   spielfeld[(j+1)][(i-1)]==1 &&
                   spielfeld[(j+2)][(i-2)]==1 &&
                   spielfeld[(j+3)][(i-3)]==1){return 1;}//guckt ob Spieler 1 gewonnen hat und gibt dies gegebenenfalls aus
                else if(spielfeld[j][i]==2 &&
                        spielfeld[(j+1)][(i-1)]==2 &&
                        spielfeld[(j+2)][(i-2)]==2 &&
                        spielfeld[(j+3)][(i-3)]==2){return 2;}//guckt ob Spieler 2 gewonnen hat und gibt dies gegebenenfalls aus
            }
        }
        return 0;//falls kein spieler in einer diagonalen Reihe gewonnen hat gibt er 0 fuer kein Spieler hat gewonnen zurueck.
    }
}
