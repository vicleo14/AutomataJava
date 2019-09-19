/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.util;

import com.escom.automata.Afn;
import com.escom.automata.IAfn;
import com.escom.automata.IState;
import com.escom.automata.State;
import com.escom.automata.Transition;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Victor
 */
public class UnionEspecial {
    
    public void unir(HashMap<Integer,Afn> afns)
    {
        Iterator it1=afns.values().iterator();
        
        IState sIni=new State(false);
        IAfn newAfn=new Afn();
        newAfn.getStates().add((State)sIni);
               
        while(it1.hasNext())
        {
            IAfn afn=(IAfn)it1.next();
            newAfn.getStates().addAll(afn.getStates());
            newAfn.getAlphabet().addAlphabet(afn.getAlphabet());
            newAfn.getAcceptedStates().addAll(afn.getAcceptedStates());
            Transition t=new Transition(Constants.EPSILON,afn.getCurrentState());
            sIni.addTransition(t);
            
        }
        newAfn.setCurrentState(sIni);
        System.out.println("NUEVO AFN");    
        System.out.println(newAfn.toString());
        afns.clear();
        afns.put(newAfn.getIdAfn(),(Afn)newAfn);
        
    }
}
