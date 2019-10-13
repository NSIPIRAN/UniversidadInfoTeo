/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import java.util.ArrayList;


/**
 *
 * @author nath
 */
public class heuristicaS implements Comparable<heuristicaS>{
    //el primero es para su indice, los dos primeros son para x e y respectivvamente
    
    ArrayList<Integer> posibilidades;

    public heuristicaS() {
        posibilidades = new ArrayList<>();
    }

    @Override
    public int compareTo(heuristicaS t) {
        if(posibilidades.size()<t.posibilidades.size())return -1;
        if(posibilidades.size()>t.posibilidades.size())return 1;
        return 0;
                
    }
    public void agregar(int n){
        posibilidades.add(n);
    }
    public String toStringg(){
        String str = "(" +posibilidades.get(0)+", "+posibilidades.get(1)+") ";
            for(int i=2; i<posibilidades.size();i++){
                
                str=str+posibilidades.get(i)+"-";
            }   
        return str;
    }

    String toStringg2() {
        String str = posibilidades.get(0)+": (" +posibilidades.get(1)+", "+posibilidades.get(2)+") ";
            for(int i=3; i<posibilidades.size();i++){
                
                str=str+posibilidades.get(i)+"-";
            }   
        return str;    }

    
}
