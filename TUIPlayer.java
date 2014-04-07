import interfaces.*;
import static lib.Curses.*;
public class TUIPlayer implements Player
{
    private int number;
    private Game game;
    private String name;
    /**
    * initalisize the player
    */
    public void init(int colour, Game g){
        initscr(23,80);
        drawFrame('-','|','+');
        window(1,1,21,12,"Spielfeld");  // zeichnet ein Fenster "Spielfeld"
        window(61,1,78,12,"Info");      // zeichnet ein Fenster "Info"
        number = colour;                // Speichert die Zugewiesene Spielernummer
        refresh();                      // Ausgeben der Fenster auf das Terminal
        game = g;                       // Übernehmen des zugewiesenen game objekts
    }
    /**
     * activate Player and ask for throwing in
     */
    public void activate(){
        mvaddstr(62,2,"Player number:" + number);   // zeigt die Spielernummer an
                                                    //Infotext
        mvaddstr(62,4,"gib die Nummer");
        mvaddstr(62,5,"der Spalte ein,");
        mvaddstr(62,6,"in die du");
        mvaddstr(62,7,"einwerfen");
        mvaddstr(62,8,"willst");
                                                    //Ende Infotext
        printField(3,3);                            //Ausgeben des Spielfeldes 
        refresh();                                  //Aktualisieren des Terminals
        int row = readInt(">");                     //Lesen eines Integers, für den Einwurf
        game.throwIn(row,number);                   //Einwerfen
    }
    /**
     * print the field to screen
     */
    public void printField(int xPos, int yPos){
        for(int i = 0;i <= 6;i++){
            mvaddstr((xPos + 2*i),yPos,("|" + i));  //Ausgeben der Reihennummer
        }   
        mvaddstr((xPos + 14),yPos,"|");             //Vertikaler Strich am Ende
        int mod = 0;                                //Veränderung pro Zeile, da das 
                                                    //Spielfeld rückwärts gelesen wird
        for(int i = 5;i >= 0;i--){
            mvaddstr(xPos,yPos+mod+2,"|");          //Vertikalen Strich am anfang einer
                                                    //Zeile
            for(int j = 0;j <= 6;j++){
                int actual = game.getField(j,i);    //Aktuelles Feld
                if(actual == 0){
                    addstr(" |" ); //Falls das Feld leer sein sollte Leerzeichen + Strich
                }else{
                    addstr(Integer.toString(actual) + "|" ); // Falls nicht nummer + Strich
                }
            }
            mod++;  //Veränderung + 1
        }
    }
    /**
     * End the game
     */
    public void endGame(int winner){
           delArea(23,10,53,15);
           window(23,10,55,15,"Spiel Beendet"); //Fenster mit dem Titel Spiel beendet
           mvaddstr(62,2,"Player number:" + number); //Spielernummer ausgeben
            if(winner == number){  //im Falle des Gewinns
                mvaddstr(24,11,"Du hast gewonnen");
                mvaddstr(24,12,"Glueckwunsch!");
           }else if(winner != number){ // falls man verliert
               mvaddstr(24,11,"Du hast verloren");
               mvaddstr(24,12,"Oooooh Tut mir leid fuer dich");
               mvaddstr(24,13,"eine ganz grosse Tüte Mitleid");
           }else{ //unentschieden
               mvaddstr(24,11,"UNENTSCHIEDEN");
           }
           printField(3,3); // Spielfeld ausgeben
           refresh();   //Terminal aktualisieren
    }
    /**
     * Return Players name
     */
    public String getMyName(){
        return name;
    }
    
    /**
     * Set Playernumber
     */
    public void setNumber(int nbr){
        number = nbr;
    }
}
