import lib.*;
import interfaces.*;
import java.util.Random;

public class KI implements Player
{
    private String name = "TKV-4WIN"; 
    private Game gam;
    private int nr;
    private int throwin;
    private int ySize = 6;
    private int xSize = 7;
    private int [][] fieldi = new int [xSize][ySize];
    private int [] fillingsi = new int[7]; 
    private int [] assessment = new int[7];
    private int thinkahead=10;
    int spnr2;
    Random row = new Random();
     
    public void init(int colour, Game game)
    {
        gam = game;
        nr=colour;
        if(nr==1){spnr2=2;}
        else {spnr2=1;}
        throwin=3;//Wird später rausgenommen
    }
    
    public void activate()
    {
        throwinRow();
        gam.throwIn(throwin,nr);
    }
    
    
    public void endGame(int winner){}
    public String getMyName(){return name;}
    public void setNumber(int number){nr = number;}
    
    private int throwinRow()
    {
        if(nr==1){spnr2=2;}
        else {spnr2=1;}
        
        assessment[0]=0;
        assessment[1]=1;
        assessment[2]=2;
        assessment[3]=2;
        assessment[4]=2;
        assessment[5]=1;
        assessment[6]=0;
        
        
        
        
        for(int i=0;i<7;i++)
        {
            fillingsi[i]=gam.getFillings(i);
            for(int j=0;j<6;j++){ fieldi[i][j]=gam.getField(i,j);}
        }
        if (canPlayerWin(nr,fillingsi,fieldi)==true){return throwin;}
        
        else if (canPlayerWin(spnr2,fillingsi,fieldi)==true){return throwin;    }
        
        else 
        {
            throwin=row.nextInt(7);
            return throwin;
        }
           
    }
    
    private boolean canPlayerWin(int player,int []filling,int[][]field)
    {
        
        if (wagerechtueberpruefung(filling,field,player)==true){return true;}
        else if (senkrechtueberpruefung(field,player)==true){return true;}
        else if (diagonalueberpruefung(filling,field,player)==true){return true;}
        else {return false;}
    }
    
    private boolean wagerechtueberpruefung(int []filling,int [][]field,int colour)
    {
        int [][]tocheckfiel = field;
        
        for(int i=0;i<6;i++)
        {
           for(int j=0;j<=3;j++)
           {
               
                if (tocheckfiel[j][i]==0 &&
                tocheckfiel[(j+1)][i]==colour &&
                tocheckfiel[(j+2)][i]==colour &&
                tocheckfiel[(j+3)][i]==colour &&
                filling[j]==i)
                {
                    throwin=j;
                    return true;
                }
               
                if (tocheckfiel[j][i]==colour &&
                tocheckfiel[(j+1)][i]==0 &&
                tocheckfiel[(j+2)][i]==colour &&
                tocheckfiel[(j+3)][i]==colour &&
                filling[j+1]==i)
                {
                    throwin=j+1;
                    return true;
                }
               
                if (tocheckfiel[j][i]==colour &&
                tocheckfiel[(j+1)][i]==colour &&
                tocheckfiel[(j+2)][i]==0 &&
                tocheckfiel[(j+3)][i]==colour &&
                filling[j+2]==i)
                {
                    throwin=j+2;
                    return true;
                }
               
                if (tocheckfiel[j][i]==colour &&
                tocheckfiel[(j+1)][i]==colour &&
                tocheckfiel[(j+2)][i]==colour &&
                tocheckfiel[(j+3)][i]==0 &&
                filling[j+3]==i)
                {
                    throwin=j+3;
                    return true;
                }
                
           }
        }
        return false;
    }
    
    private boolean senkrechtueberpruefung(int [][]field,int colour)
    {
        int [][]tocheckfiel = field;
        
        for(int i=0;i<6;i++)
        {
            for(int j = 0;j<3;j++)
            {
                if(tocheckfiel[i][j]==colour &&
                tocheckfiel[i][j+1]==colour &&
                tocheckfiel[i][j+2]==colour &&
                tocheckfiel[i][j+3]==0)
                {
                    throwin=i;
                    return true;
                }
            }
        }
        

        
        return false;
    }
    
    private boolean diagonalueberpruefung(int []filling,int [][]field,int colour)
    {
        int [][]tocheckfiel = field;
        
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(tocheckfiel[j][i]==0 &&//null
                   tocheckfiel[(j+1)][(i+1)]==colour &&
                   tocheckfiel[j+2][i+2]==colour &&
                   tocheckfiel[j+3][i+3]==colour &&
                   filling[j]==i)
                   {
                       throwin=j;
                       return true;
                   }
                       
                else if(tocheckfiel[j][i]==colour &&
                        tocheckfiel[(j+1)][(i+1)]==0 &&
                        tocheckfiel[j+2][i+2]==colour &&
                        tocheckfiel[j+3][i+3]==colour &&
                        filling[(j+1)]==(i+1))
                        {
                            throwin=j+1;
                            return true;
                        }
                        
                else if(tocheckfiel[j][i]==colour &&
                        tocheckfiel[(j+1)][(i+1)]==colour &&
                        tocheckfiel[j+2][i+2]==0 &&
                        tocheckfiel[j+3][i+3]==colour &&
                        filling[(j+2)]==(i+2))
                        {
                            throwin=j+2;
                            return true;
                        }
                        
                else if(tocheckfiel[j][i]==colour &&
                        tocheckfiel[(j+1)][(i+1)]==colour &&
                        tocheckfiel[j+2][i+2]==colour &&
                        tocheckfiel[j+3][i+3]==0 &&
                        filling[(j+3)]==(i+3))
                        {
                            throwin=j+3;
                            return true;
                        }
                
            }
        }
        
        for(int i=5;i>2;i--)
        {
            for(int j=0;j<4;j++)
            {
                if(tocheckfiel[j][i]==0 &&
                   tocheckfiel[(j+1)][(i-1)]==colour &&
                   tocheckfiel[(j+2)][(i-2)]==colour &&
                   tocheckfiel[(j+3)][(i-3)]==colour &&
                   filling[j]==(i))
                   {
                       throwin=j;
                       return true;
                   }
                   
                else if(tocheckfiel[j][i]==colour &&
                        tocheckfiel[(j+1)][(i-1)]==0 &&
                        tocheckfiel[(j+2)][(i-2)]==colour &&
                        tocheckfiel[(j+3)][(i-3)]==colour&&
                        filling[j+1]==(i-1))
                        {
                            throwin=j+1;
                            return true;
                        }
                        
                else if(tocheckfiel[j][i]==colour &&
                       tocheckfiel[(j+1)][(i-1)]==colour &&
                        tocheckfiel[(j+2)][(i-2)]==0 &&
                        tocheckfiel[(j+3)][(i-3)]==colour&&
                        filling[j+2]==(i-2))
                        {
                           throwin=j+2;
                           return true;
                        }
                        
                else if(tocheckfiel[j][i]==colour &&
                        tocheckfiel[(j+1)][(i-1)]==colour &&
                        tocheckfiel[(j+2)][(i-2)]==colour &&
                        tocheckfiel[(j+3)][(i-3)]==0 &&
                        filling[j+3]==(i-3))
                        {
                           throwin=j+3;
                           return true;
                        }      
            }
        }
        return false ;
    } 
    
    public void dobestturn()
    {   
        int [][][] fieldclone = new int [thinkahead][6][7];
        int [][] fillingsclone = new int [thinkahead][7];
        int spnr2; 
        int turns=0;
        
        
        if(nr==1){spnr2=2;}
        else {spnr2=1;}
        
        for (int i=0;i<7;i++)
        {
            fillingsclone[turns]= fillingsi.clone();
            fieldclone[turns]= fieldi.clone();
            
            throwinKI(fillingsclone[turns],fieldclone[turns],i,nr);
                    
                if (canPlayerWin(spnr2,fillingsclone[turns],fieldclone[turns])==true)
                {assessment[i]-=3;break;}
                                    
                if (canPlayerWin(nr,fillingsclone[turns],fieldclone[turns])==true)
                {assessment[i]+=2; break;}
                                    
                if (canPlayerWin(nr,fillingsclone[turns],fieldclone[turns])==false
                &&canPlayerWin(spnr2,fillingsclone[turns],fieldclone[turns])==false)
                
                turns = turns++;
                
                for (int j=0;j<7;j++)
                {
                    fillingsclone[1]=fillingsclone[0].clone();
                    fieldclone[1]=fieldclone[0].clone();
                    throwinKI(fillingsclone[1],fieldclone[1],j,spnr2);
                    
                    
                    
            
                
                }
            }
        }
        
    
                                            
                                        
     private boolean throwinKI(int [] filling,int [][] field ,int row,int colour)
    {
        return false;
    }
    
    public void notThrowable(){}
  
}
