/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author nath
 */
public class SudokuResolver {
       
    public static Vivos vivos=new Vivos();
    void resuelve(){
        int[][] sol= new int [9][9];
        boolean[][] solb= new boolean [9][9];
        ArrayList<heuristicaS> heu = new ArrayList<>();
        Nodo raiz= new Nodo();
        int aux [] []=new int [9] [9];
        aux=Interfaz.tomarArreglo();
        for(int g=0;g<9;g++){
            for(int f=0;f<9;f++){
                sol[g][f]=aux[g][f];
            }
        } 
        
        
        solb=convertiraBool(sol);
        //INICIO aplicar heuristica
        //recorrer cada celda para agregar las posibilidades
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(!solb[i][j]){
                    heuristicaS h=new heuristicaS();
                    h.agregar(i);
                    h.agregar(j);
                    //verificar del 1-9 a partir del 3 item agregado son las posibilidades
                    for(int k=1;k<10;k++){
                        if(verificar(sol,k,i,j)){
                            h.agregar(k);
                        }
                    }
                    heu.add(h);
                }    
            }
        }
        Collections.sort(heu);//ordena mi lista de cada celda con sus posibilidades
        //FIN aplicar heuristica
        
        // INICIO poner los de tamaño 1 como fijos
        while(!heu.isEmpty()&&((heu.get(0).posibilidades.size()))==3){
            heuristicaS h=new heuristicaS();
            h=heu.remove(0);
            // System.out.println("primero:"+ h.posibilidades.get(3));
            sol[h.posibilidades.get(0)][h.posibilidades.get(1)]=h.posibilidades.get(2);
            solb[h.posibilidades.get(0)][h.posibilidades.get(1)]=true;
        }
                
        if(!heu.isEmpty()){
            for(int i=0;i<heu.size();i++){//agrega un indice al inicio de cada celda
               heu.get(i).posibilidades.add(0,i);
            }  
            //FIN poner los de tamaño 1 como fijos
            for(int g=0;g<9;g++){
                for(int f=0;f<9;f++){
                    raiz.sol[g][f]=sol[g][f]; //copia la sol nueva a sol de raiz
                }
            }
            vivos.agregar(raiz,1);
            Nodo extraccion = vivos.quitar();//nodo raiz
            boolean ramificable;

            for(int i=0;i<heu.size();i++){
                ramificable=extraccion.ramifica(heu.get(i));//extraccion tratarla como 

                if(!ramificable){
                    //System.out.println(" No hay solucion en esta rama");

                    if(vivos.pilaVacia()){
                        JOptionPane.showMessageDialog(null, "Error Solucion no encontrada");
                        Interfaz.moan();
                        break;
                    }

                    extraccion=vivos.quitar();
                    i=extraccion.indexh;    

                }
                else    if(vivos.pilaVacia()){
                            JOptionPane.showMessageDialog(null, "Error Solucion no encontrada");
                            break;
                        }
                        else
                            extraccion=vivos.quitar();
            }

            Interfaz.mostrarSolucion(extraccion.sol);}
        else 
            Interfaz.mostrarSolucion(sol);
        
    }

    private boolean[][] convertiraBool(int[][] sol) {
        int n;
        boolean[][] solb= new boolean [9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                n=sol[i][j];
                solb[i][j] = n != 0;
            }
        }
        /*for(int g=0;g<9;g++){
            System.out.println("|");
            for(int f=0;f<9;f++){
                System.out.print(solb[g][f]+" ");
            }
        }*/
        return solb;
    }
    
    public static boolean verificar(int[][] sol, int k, int i, int j) {
        if(!verificarX(sol,k,i)){
            return false;
        }
        else    if (!verificarY(sol,k,j)){
                    return false;
                } 
                else    if (!verificarBloque(sol,k,i,j)){
                            return false;
                        }
        return true;
    }

    public static  boolean verificarX(int[][] sol, int k,int i) {
        for(int j=0;j<9;j++){
            if(k==sol[i][j])
                return false;
        }
        return true;
    }

    public static  boolean verificarY(int[][] sol, int k,int j) {
        for(int i=0;i<9;i++){
            if(k==sol[i][j])return false;
        }
        return true;
    }

    public static  boolean verificarBloque(int[][] sol, int k, int i, int j) {
        int bi,bj; 
        bi=bloque(i);
        bj=bloque(j);
        for(int ii=bi;ii<bi+3;ii++){
            for(int jj=bj;jj<bj+3;jj++){
                if(k==sol[ii][jj])return false; 
            }
        }
        return true;
    }

    public static  int bloque(int o) {
        int resultado = 0;
        int k = o/3;  
        if(k==0)resultado=0;
        if(k==1)resultado=3;
        if(k==2)resultado=6;
        return resultado;
    }

    
}
