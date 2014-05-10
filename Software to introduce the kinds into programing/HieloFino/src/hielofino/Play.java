package hielofino;

import java.awt.Point;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/** *
 * @author Diego André
 */
public class Play extends BasicGameState{

    float a=0;
    boolean b=true;
    
  
    
    public static final int DezArriba=0;
    public static final int DezAbajo=1;
    public static final int DezIzquierda=2;
    public static final int DezDerecha=3;
    
    private final int velocidadAgua,velocidadMuñeco;    
    private final int velocidadDeMovimiento;
    
    private org.newdawn.slick.geom.Point dirMuñe;
    
    private ArrayList<Image> listImagenes;    
    private Menu menu;
    
    private int tim;
    
    private int matInterpretada[][];
    private int direccion,pasos;
    private Point posicionMuñe;
    private boolean tareaEjecutada;
    private boolean muerto;
    
    private SpriteSheet aguaFrames;
    private Animation muñeco;
    private Animation morir;
    private Animation matAnimaciones[][];
    private float velMun;
    
    private ListadeMovimientos listOrdenes;
    
    private Sound caminar,morirSound,hieloGruesoSound,ganarSound;
    
    public Play(int state,  ArrayList<Image> listImagenes, Menu menu ) 
    {
        
        velocidadAgua=90;
        velocidadMuñeco=80;
        velocidadDeMovimiento=14;
        
        velMun =(float) (menu.dimenIma)/velocidadDeMovimiento;
        
        this.listImagenes = listImagenes;
        this.menu = menu;
        
        direccion = -1;
        pasos=-1;
        
        tareaEjecutada = true;
        muerto = false;
    }

    
    @Override
    public int getID() {
       return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        caminar = new Sound("Audio\\caminar.wav");
        morirSound = new Sound("Audio\\muerte.wav");
        hieloGruesoSound = new Sound("Audio\\hieloDoble.wav");
        ganarSound = new Sound("Audio\\gano.wav");
        
        aguaFrames = new SpriteSheet("Icons\\mapa\\aguaAnim.png", 52, 52);
        muñeco = new Animation( new SpriteSheet("Icons\\mapa\\muñecoAnim.png", 52, 52),velocidadMuñeco);
        
        morir = new Animation( new SpriteSheet("Icons\\mapa\\muerto.png", 52, 52),velocidadMuñeco);
        morir.setPingPong(true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

        /*grphcs.drawLine(150, 150, a, a);
        grphcs.drawLine(150, 150, -a, a);        
        grphcs.drawLine(150, 150, a, -a);*/
        
        for (int i = 0; i < menu.mapaActualMat.length; i++) {
            for (int j = 0; j < menu.mapaActualMat[0].length; j++) 
            {
                int cuadro = matInterpretada[i][j];
                
                switch (cuadro)
                {
                    case Game.agua:
                        matAnimaciones[i][j].draw(j*menu.dimenIma, i*menu.dimenIma, menu.dimenIma, menu.dimenIma);
                    break;   
                    default:
                       Image im = listImagenes.get( cuadro );
                       grphcs.drawImage( im, j*menu.dimenIma, i*menu.dimenIma, (j+1)*menu.dimenIma, (i+1)*menu.dimenIma, 0, 0, im.getWidth(), im.getHeight()); 
                    break;
                }
                
             }
        }
        
       if(!muerto)
       muñeco.draw(dirMuñe.getX(), dirMuñe.getY(), menu.dimenIma, menu.dimenIma);
       else
       morir.draw(dirMuñe.getX(), dirMuñe.getY(), menu.dimenIma, menu.dimenIma);
       
       
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
       
        
        if(listOrdenes != null)
        {
            tim += i;
        
            if(tim >= velocidadDeMovimiento )
            {
                //si ya termino de hacer todos los pasos
                if(tareaEjecutada)
                {
                    
                   tareaEjecutada=false;                    
                    if(listOrdenes.sacarMovimiento() != null)
                    {
                        direccion = listOrdenes.getDir();
                        pasos = listOrdenes.getPasos();
                    }
                      
                }
                else
                {
                    moverMuñeco();
                }
                
            }
            
        }
        
           
    }
    
    public void stateEntered(ListadeMovimientos listOrdenes)
    {
        matInterpretada = new int[ menu.mapaActualMat.length ][ menu.mapaActualMat[0].length ];
        matAnimaciones = new Animation[ menu.mapaActualMat.length ][ menu.mapaActualMat[0].length ];
        
        for (int i = 0; i < menu.mapaActualMat.length; i++) {
            for (int j = 0; j < menu.mapaActualMat[0].length; j++) 
            {
                matInterpretada[i][j]=menu.mapaActualMat[i][j];
                
                if(matInterpretada[i][j]==Game.muñeco)
                {
                    posicionMuñe = new Point(j, i);
                    dirMuñe = new org.newdawn.slick.geom.Point(j*menu.dimenIma, i*menu.dimenIma);
                }
                
                if( matInterpretada[i][j]==Game.hielo || matInterpretada[i][j]==Game.hieloDoble || matInterpretada[i][j]==Game.muñeco)
                {  
                    Animation auxAgu = new Animation(aguaFrames, velocidadAgua);
                    matAnimaciones[i][j]= auxAgu;
                }
                
                
            }
        }
        
        this.listOrdenes = listOrdenes;
        tim=0;
        
        direccion = -1;
        pasos=-1;
        
        tareaEjecutada = true;
        muerto = false;
    }

    public void setListOrdenes(ListadeMovimientos listOrdenes) {
        this.listOrdenes = listOrdenes;
    }
    
    
    //aquí van todos las validaciones del desplazamiento
    private void desplazarMat()
    {
        if(matInterpretada[posicionMuñe.y][posicionMuñe.x]==Game.hieloDoble)
        {
                matInterpretada[posicionMuñe.y][posicionMuñe.x]=Game.hielo;
                hieloGruesoSound.play();
        }
            else
                if(matInterpretada[posicionMuñe.y][posicionMuñe.x]!=Game.llegada)
                {
                matInterpretada[posicionMuñe.y][posicionMuñe.x]=Game.agua;
                }
                
        
        int cosa = 0;
            switch (direccion)
            {
                case DezAbajo:                                        
                   
                       posicionMuñe.y += 1;
                       tim =0;
                    
                    
                break;
                case DezArriba:
                                      
                   
                        posicionMuñe.y -= 1;
                        tim =0;
                    
                    
                break;
                case DezDerecha:
                                      
                        posicionMuñe.x += 1;
                        tim =0;
                    
                break;
                case DezIzquierda:
                                       
                    
                    posicionMuñe.x -= 1;
                    tim =0;
                    
                break;

                case -1:
                    //la direccion del muñeco no es conocida
                break;
            }
        
           if( matInterpretada[posicionMuñe.y][posicionMuñe.x]!=Game.llegada )
            morir();
           
           
            
           if( matInterpretada[posicionMuñe.y][posicionMuñe.x] == Game.llegada  )
              ganarSound.play();
           else
            caminar.play();
            
            
            
        pasos--;
        
        if(pasos<=0)
        {
            tareaEjecutada = true;
            direccion = -1;
        }
        
    }
    
    public void morir()
    {
        int uno1=posicionMuñe.y+1,uno2=posicionMuñe.x; //arriba
        int dos1=posicionMuñe.y-1,dos2=posicionMuñe.x; //abajo
        int tres1=posicionMuñe.y,tres2=posicionMuñe.x-1; //izquierda
        int cuatro1=posicionMuñe.y,cuatro2=posicionMuñe.x+1; //derecha
        
            if( (matInterpretada[uno1][uno2]== Game.agua || matInterpretada[uno1][uno2]== Game.limite) &&
                (matInterpretada[dos1][dos2]== Game.agua || matInterpretada[dos1][dos2]== Game.limite) &&    
                (matInterpretada[tres1][tres2]== Game.agua || matInterpretada[tres1][tres2]== Game.limite) &&    
                (matInterpretada[cuatro1][cuatro2]== Game.agua || matInterpretada[cuatro1][cuatro2]== Game.limite) ) 
            {
                muerto = true;
                morirSound.play();
            }
    }

    
    
    //si se encuentra cualquiera de estas cosas, no avanzará
    private boolean puedeAvanzar(int cosa)
    {
        if(cosa == Game.agua || cosa==Game.nada || cosa == Game.limite)
        {
            pasos=0;
            tareaEjecutada=true;
            return false;
        }
        
        
        
        return true;
    }

    private void moverMuñeco() {
         int a = (int) (dirMuñe.getX()/menu.dimenIma);
         int b = (int) (dirMuñe.getY()/menu.dimenIma);
         
        switch(direccion)
        {
            case Play.DezIzquierda:
                
              if(puedeAvanzar( matInterpretada[ posicionMuñe.y ][posicionMuñe.x-1] ))
              {
                dirMuñe.setX( dirMuñe.getX() - velMun );
                
                if(a < posicionMuñe.x-1)                
                    desplazarMat();
              }
              
            break;
                
            case Play.DezDerecha:
                
                if(puedeAvanzar( matInterpretada[posicionMuñe.y][posicionMuñe.x+1] ))
                {
                    dirMuñe.setX( dirMuñe.getX() + velMun );

                    if(a >= posicionMuñe.x+1)                
                        desplazarMat();
                }
            break;
                
            
            case Play.DezArriba:
               if(puedeAvanzar( matInterpretada[posicionMuñe.y-1][posicionMuñe.x] ))
               {
                dirMuñe.setY( dirMuñe.getY() - velMun );
                
                if(b < posicionMuñe.y-1)                
                    desplazarMat();
               }                
            break;
                
                
          case Play.DezAbajo:
                
                if(puedeAvanzar( matInterpretada[posicionMuñe.y+1][posicionMuñe.x] ))
                {
                    dirMuñe.setY( dirMuñe.getY() + velMun );

                    if(b >= posicionMuñe.y+1)                
                        desplazarMat();
                }
            break;
                
                
                
        }
    }
    

}



