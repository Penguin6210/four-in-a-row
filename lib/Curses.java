package lib;
import java.io.*;

/**
 * Tiny class that implements the basic functionality of <curses.h> in pure java
 * 
 */
 public class Curses
{
     private static char screen[][]; // das Array, indem der Bildschirminhalt gespeichert wird
     private static int xSize;       // x-Groesse des Bildschirminhaltes in Zeichen
     private static int ySize;       // y-Groesse des Bildschirminhaltes in Zeichen
     private static int xPos;        // x-Position auf dem Bildschirm
     private static int yPos;        // y-Position auf dem Bildschirm
     
    /**
     * Initialises the screen.
     */
     public static void initscr(int y, int x){
        xSize = x;
        ySize = y;
        screen=new char[ySize][xSize]; // Dimensionieren des Arrays
        yPos = 0;
        xPos = 0;
        for (int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++)screen[i][j]= ' '; // Überschreiben aller daten im Array mit ' '
        }
    }
    
    /**
     * Returns the horizontal size.
     */
    public static int getXSize(){
        return xSize; //gibt die horizontale Groesse in Zeichen zurück
    }
    
    /**
     * Returns the vertical size.
     */
    public static int getYSize(){
        return ySize; //gibt die vertikale Groesse in Zeichen zurück
    }
    
    /**
     * Prints modified screen on console. 
     */
     public static void refresh(){
        System.out.println("\u000c"); // Terminal löschen
        
        //Ausgeben des Arrays Zeichen für Zeichen
        for (int i = 0; i < ySize; i++){
            for(int j = 0; j < xSize; j++)System.out.print(screen[i][j]);
            System.out.println("");
        }
    }
    
    /**
     * Adds a string to the actual position.
     */
     public static void addstr(String string){
        for (int i = 0; i < string.length(); i++){
            if(xPos < xSize){ //falls man nicht am vertikalen bwz. horizontalen Ende des Arrays ist
                xPos++;
            }else if (xPos >= xSize){ //falls man nicht am vertikalen aber am horizontalen Ende ist
                yPos++;
                xPos = 0;
            }else{ //falls man am vertikalen bwz. horizontalen Ende des Arrays ist
                yPos = 0;
                xPos = 0;
            }
            screen[yPos][xPos] = string.charAt(i);
        }
    }
    
    /**
     * Moves to specific position.
     */
     public static void move(int x, int y){
        xPos = x; // X-Position setzen
        yPos = y; // Y-Position setzen
    }
    
    /**
     * Moves to a specified position and adds a string of text.
     */
     public static void mvaddstr(int x, int y, String string){
        move(x,y);
        addstr(string);
    }
    
    /**
     * Draws a horizontal line.
     */
    public static void horizontalLine(char character, int y, int start, int end){
        if(end > xSize)end = xSize;
        for(int i=start; i < end; i++)screen[y][i]=character;
    }
    
    /**
     * Draws a vertical line.
     */
    public static void verticalLine(char character, int x, int start, int end){
        if(end > xSize)end = xSize;
        for(int i=start; i < end; i++)screen[i][x]=character;
    }
    
    /**
     * Draws a frame around the screen.
     */
    public static void drawFrame(char horizontalChar, char verticalChar, char corners){
        screen[0][0]= corners;
        screen[0][xSize-1] = corners;
        screen[ySize-1][0] = corners;
        screen[ySize-1][xSize-1] = corners;
        verticalLine(verticalChar,0,1,ySize-1);
        verticalLine(verticalChar,xSize-1,1,ySize-1);
        horizontalLine(horizontalChar,0,1,xSize-1);
        horizontalLine(horizontalChar,ySize-1,1,xSize-1);
    }
    
    /**
     * Draws a window onto the screen.
     */
    public static void window(int x1, int y1, int x2, int y2, String title){

        verticalLine('|',x1,y1,y2);
        verticalLine('|',x2,y1,y2);
        horizontalLine('-',y1,x1,x2);
        horizontalLine('-',y2,x1,x2);
        mvaddstr(x1+2,y1,"| " + title + " |");
        screen[y1][x1] = '+';
        screen[y1][x2] = '+';
        screen[y2][x1] = '+';
        screen[y2][x2] = '+';
    }
    /**
     *  Deletes an Area of the screen.
     */
    public static void delArea(int x1, int y1, int x2, int y2){
        for (int i = y1; i <= y2; i++){
            for(int j = x1; j <= x2; j++)screen[i][j]= ' ';
        }        
    }
    /**
     * Prints the screen to console and asks for text.
     */
    public static String readline(String question) {
        String read = "";
        while(read.length() == 0){
            refresh();
            System.out.print(question);
            try {
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(System.in));
                read = bufferedreader.readLine();
            } catch (IOException ioe) {
                window(15,10,44,14,"Error");
                mvaddstr(16,12,"IO-Error reading System.in");
                //System.exit(1);
                return "0";
            }
        }
        return read;
    }
    
    /**
     * Prints the screen to Console and asks for an Integer
     */
    public static int readInt(String question){
        int read = Integer.parseInt(readline(question));
        return read;
    }
    
    /**
     * Shows a menu
     */
    public static int menu(String caption, String question, String options[], int x, int y){
        window(x,y,x+question.length()+2,y+3+options.length, caption);
        mvaddstr(x+1,y+1,question);
        for(int i = 0; i < options.length; i++)mvaddstr(x+1,y+2+i,"[" + i + "] " + options[i]);
        refresh();
        return readInt(">");
    }
}
