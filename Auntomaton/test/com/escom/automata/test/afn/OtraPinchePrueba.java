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
import com.escom.automata.util.Constants;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author art
 */
public class OtraPinchePrueba {
    public static void main(String[] args)
    {
        Afn af11=new Afn('a');
        Afn af12=new Afn('b');
        Afn af13=new Afn('c');
        
        
        af11.addAFN(af12);
        af11.positiveClosure();
        
        af13.kleenClosure();
        //System.out.println("Kleen"+af13.toString());
        af11.concatenateAFN(af13);
        
        //System.out.println(af11.toString());
        /*System.out.println("8:"+af11.getStateById(8).epsilonClosure());
        System.out.println("6:"+af11.getStateById(6).epsilonClosure());
        System.out.println("Currente state:"+af11.getCurrentState().epsilonClosure());
        System.out.println("Solicitado:"+af11.epsilonClausure(af11.getCurrentState()));
        System.out.println("------------------------------------------------------");
        System.out.println("Solicitado3:\n"+af11.epsilonClausure(af11.getStateById(1)));
        */
        //System.out.println("Mover 8 con a:"+af11.goTo((Collection<IState>) af11.getStateById(8), 'a'));
        /*Collection<Integer> indices = new HashSet<>();
        indices.add(new Integer(7));
        indices.add(new Integer(9));
        for(IState state:af11.move(af11.getStatesByIds(indices), 'a')){
            System.out.println(state.toString());
        }
        System.out.println("Estoy hasta la madre");
        */
        /*System.out.println(":::::::::::::::::::::::::::::::::::::.SetStates:");
        for(SetState state: af11.generateSetStates()){
            System.out.println(state.toString());
        }*/
        Afd afd=new Afd(af11.generateSetStates(),af11.getAlphabet());
        //System.out.println(afd.toString());
        afd.printTable();
        System.out.println("Fin");
    }
}
