
import interfaces.*;
import static lib.Curses.*;
import java.io.*;

public class Launcher
{
    public static void main(String args[]){
        Player player1 = getPlayer(1);
        Player player2 = getPlayer(2);
        Game game = new Core();
        
        player1.init(1,game);
        player2.init(2,game);
        game.init(player1,player2);
    }
    
    private static Player getPlayer(int pnbr){
        System.out.print("\u000c");                         //Bildschirm Löschen
        String ptypes[]= {"Mensch / TUI","Maschine / KI"};  //Namen der Verschiedenen Spielertypen festlegen
        for(int i=0; i <= 1; i++) 
                System.out.println("[" + i + "] " + ptypes[i]); //Spielertypen auusgeben
        int ptype = readint("Type of Player" + pnbr + ":");     //Integer lesen
        Player p;                                               //Player object allozieren
        switch(ptype){
            case 0: p = new TUIPlayer();                        //neuer Menschlicher gegner
                    break;
            case 1: p = new KI();                               //neuer Computergegner
                    break;
            default:p = new TUIPlayer();                        //bei ungültiger eingabe Menschlicher gegner
                    break;
        }
        System.out.print("\u000c");                             //Bildschirm löschen
        return p;                                               //p zurückgeben
    }
    
    private static int readint(String question){ 
        String read = "";
        while(read.length() == 0){
            System.out.print(question); //Frage ausgeben
            try {
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));   //neuer Buffered reader mit System.in als inputstring
                read = bufferedreader.readLine();                                                       //einzelne Zeile auslesen
            } catch (IOException ioe) {
                System.err.println("IO-Error reading System.in");                                       //Falls es einen Fehler gibt
                //System.exit(1);
                return 0;
            }
        }
        return Integer.parseInt(read); //aus dem String einen int machen
    }
}
