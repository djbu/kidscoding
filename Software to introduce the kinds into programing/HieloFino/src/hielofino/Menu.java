package hielofino;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author Diego André
 */
public class Menu extends BasicGameState{

    public static int MapaActual;    
    private static int cantidadDeMapas;
    
    public  ArrayList<int [][]> mapas;  
    public final int dimenIma;
    
    private ArrayList<Image> listImagenes;
    
    
    public int mapaActualMat[][];
    
    
    public Menu(int state, ArrayList<Image> listImagenes) 
    {        
        dimenIma=23;
        
        MapaActual=0;
        cargasMapas();
        
        this.listImagenes = listImagenes;
        
        cantidadDeMapas = mapas.size();
    }
    
    private void cargasMapas()
    {
        mapas = new ArrayList<>();
                
                
         String Smapas[] = leerArchivo().split("-");
         
         for (int i = 0; i < Smapas.length; i++) 
         {
             char mapAux[] = Smapas[i].toCharArray();
            // System.out.println( mapAux.length);
             
         int columDeMapIntAux=0;
         int mapIntAux[][] = new int[15][19];
         
             for (int j = 0; j < mapAux.length; j++) 
             {
                           
                    if(mapAux[j]==10)
                    {
                        if(j!=0)
                        columDeMapIntAux++;                             
                    }
                    else   
                    {
                        int fila = (j-1)-20*columDeMapIntAux;
                        
                        if(fila==-19)                        
                            break;
                        
                            int val = mapAux[j]-48;
                        
                        mapIntAux[columDeMapIntAux][fila] = val;  
                       // System.out.println(columDeMapIntAux+"  "+fila+" "+mapIntAux[columDeMapIntAux][fila]);
                    }
                    
             }
             
             /*System.out.println("***********");
             for (int j = 0; j < mapIntAux.length; j++) 
             {
                 for (int k = 0; k < mapIntAux[0].length; k++) 
                 {
                     System.out.print( mapIntAux[i][j] );
                 }
                 System.out.println("");
             }*/
             
             
             
          mapas.add(mapIntAux);
             
         }
    }
    
    @Override
    public int getID() {
       return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
      /*0 será nada
        1 será límite
        2 será hielo
        3 será hieloDoble
        4 será muñeco
        5 será llegada
        6 será agua*/
        
        try 
        {
            listImagenes.add(new Image("Icons\\mapa\\nada.png"));
            listImagenes.add( new Image("Icons\\mapa\\limite.png"));
            listImagenes.add( new Image("Icons\\mapa\\hielo.png"));
            listImagenes.add( new Image("Icons\\mapa\\hielo doble.png"));
            listImagenes.add( new Image("Icons\\mapa\\muñeco.png"));
            listImagenes.add( new Image("Icons\\mapa\\llegada.png"));
            listImagenes.add( new Image("Icons\\mapa\\agua.png")); 
        } catch (SlickException ex) {
            System.out.println("Error al cargar imagenes");
        }
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
        for (int i = 0; i < mapaActualMat.length; i++) {
            for (int j = 0; j < mapaActualMat[0].length; j++) 
            {
                Image im = listImagenes.get( mapaActualMat[i][j] );
                grphcs.drawImage( im, j*dimenIma, i*dimenIma, (j+1)*dimenIma, (i+1)*dimenIma, 0, 0, im.getWidth(), im.getHeight(), new Color(50, 50, 50));
            }
            
        }
        
    }
       
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        mapaActualMat = mapas.get( MapaActual );
    
    }
    
    public static void NextMap()
    {
        if(MapaActual == cantidadDeMapas-1)
        {
            MapaActual=0;
        }
        else
            MapaActual++;
        
        
    }
    
    public static void PrevousMap()
    {
        if(MapaActual == 0)
        {
            MapaActual= cantidadDeMapas-1;
        }
        else
            MapaActual--;
        
    }
    
    private String leerArchivo() 	
    {
	 
	try{       
	 
	//Creamos el objeto del archivo que vamos a leer
	File archivoActual = new File(System.getProperty("user.dir")+"\\..\\..\\..\\src\\Icons\\mapa\\mapas.txt");
	 
        
        
	//Creamos el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura
	FileReader lectorArchivo = new FileReader(archivoActual);
	 
	//Creamos un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que hemos creado
	BufferedReader br = new BufferedReader(lectorArchivo);
	 
	String l="";
	//Esta variable "l" la utilizamos para guardar mas adelante toda la lectura del archivo
	 
	String aux="";/*variable auxiliar*/
	 
	while(true)
	//este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
	{
	aux=br.readLine();
	//leemos una linea de texto y la guardamos en la variable auxiliar
	if(aux!=null)
	l=l+aux+"\n";
	/*si la variable aux tiene datos se va acumulando en la variable l,
	* en caso de ser nula quiere decir que ya nos hemos leido todo
	* el archivo de texto*/
	 
	else
	break;
	}
	 
	br.close();
	 
	lectorArchivo.close();
	 
	return l;
	 
	}catch(IOException e){
	System.out.println("Error:"+e.getMessage());
	}
	return null;
        
}
    
}
