/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.IState;
import com.escom.automata.SetState;
import com.escom.automata.util.StatesCollection;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author art
 */
public class TestAfnAbc {
    public static void main(String[] args)
    {
        Afn af11=new Afn('a');
        Afn af12=new Afn('b');
        Afn af13=new Afn('c');
        
        
        af11.addAFN(af12);
        af11.positiveClosure();
        
        af13.kleenClosure();
        af11.concatenateAFN(af13);
        
        System.out.println(af11.toString());
        System.out.println("Cerradura epsilon 8:"+af11.epsilonClausure(af11.getStateById(3)));
        StatesCollection sc=new StatesCollection();
        sc.add(af11.getStateById(8));
        ArrayList<Collection> scAL=new ArrayList<Collection>();
        for(IState is:af11.getStates())
        {
            for(Character c:af11.getAlphabet().getSymbols())
            {
                Collection col=af11.goTo(af11.epsilonClausure(is), c);
                if(!scAL.contains(col) && !col.isEmpty())
                {
                    System.out.println("Se agrego "+col);
                }
                else if(!col.isEmpty())
                {
                    System.out.println("Ya estaba "+col);
                }
            }
        }
        
        
    }
    
}
