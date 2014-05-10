package hielofino;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author Diego André
 */
public class Game extends StateBasedGame {
    
    public static final String gamaename = "Hielo Fino";
    public static final int menu = 0;
    public static final int play = 1;
    public static final int width = 437;
    public static final int hight = 345;

    public static final int nada=0;
    public static final int limite=1;
    public static final int hielo=2;
    public static final int hieloDoble=3;
    public static final int muñeco=4;
    public static final int llegada=5;
    public static final int agua=6;
    
    private Menu GameMenu;
    private Play GamePlay;
    
    private ArrayList<Image> listImagenes;
        
    public  void LetsPlay()           
    {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer( new Game(gamaename));
            appgc.setDisplayMode(width, hight, false);
            appgc.setShowFPS(false);
            appgc.start();
            
            
        } catch (SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Game(String name) {
        super(name);
        
        listImagenes = new ArrayList<Image>();
        
        GameMenu = new Menu(menu, listImagenes);
        GamePlay = new Play(play, listImagenes, GameMenu);
        
        
        this.addState(GameMenu);
        this.addState(GamePlay);
    }
      
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
        gc.setShowFPS(false);
        
    }
    
    public void setMenu()
    {        
        this.enterState(menu);
    }
     
    public void setJugar(ListadeMovimientos lista)
    {
        
        //GamePlay = new Play(play);
        GamePlay.stateEntered(lista);
        this.enterState(play);
        
    }
    
}