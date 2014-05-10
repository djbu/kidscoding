/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hielofino;

import java.util.ArrayList;



/**
 *
 * @author diego.juliao
 */
public class ListadeMovimientos 
{
    private ArrayList<movimiento> lista;
    private movimiento movimiento;
    
    public int getMovimientosRestantes()
    {
        return lista.size();
    }

    public ListadeMovimientos() 
    {
        lista = new ArrayList<>();
    }
    
    public void agregarMovimiento(int dir, int cantPasos)
    {
        lista.add( new movimiento(dir, cantPasos) );
    }
    
    public movimiento sacarMovimiento()
    {
        if(lista.isEmpty())
        {
            movimiento = null;
            return null;
        }
        
        movimiento = lista.get( 0 ); 
        lista.remove(0);
        return movimiento;
    }
    
    public int getPasos()
    {
        return movimiento.pasos;
    }
    
    public int getDir()
    {
        return movimiento.dir;
    }
    
    
}

/**/
class movimiento 
{
    public int dir;
    public int pasos;

    public movimiento(int dir, int pasos) {
        this.dir = dir;
        this.pasos = pasos;
    }

    
}
