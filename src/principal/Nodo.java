/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author nath
 */
public class Nodo {
    public int sol [] [];
    public int x,y;
    public int indexh;
    //constructor para nodo raiz
    public Nodo() {
        sol=new int [9] [9];
    }
    //constructor para nodo padre
    public Nodo(Nodo padre){
        sol=new int [9] [9];
         for(int g=0;g<9;g++){
            for(int f=0;f<9;f++){
                this.sol[g][f]=padre.sol[g][f];
            }
        }
    }
    boolean ramifica(heuristicaS e) {
        boolean ramificable=false;
        for(int i=3;i<e.posibilidades.size();i++){
            
            Nodo nuevo=new Nodo(this);
            nuevo.indexh=e.posibilidades.get(0);
            nuevo.x=e.posibilidades.get(1);
            nuevo.y=e.posibilidades.get(2);

            if(!nuevo.esDescartable(e.posibilidades.get(i))){
                ramificable=true;
                nuevo.sol[nuevo.x][nuevo.y]=e.posibilidades.get(i);
                SudokuResolver.vivos.agregar(nuevo);
            }
        }
        return ramificable;
    }
    boolean esDescartable(int dato) {
        
        return !SudokuResolver.verificar(sol,dato,x,y);
    }
 
}
