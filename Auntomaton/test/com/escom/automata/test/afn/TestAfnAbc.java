/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.Afn;

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
        //System.out.println("Kleen"+af13.toString());
        af11.concatenateAFN(af13);
        
        //System.out.println(af11.toString());
        System.out.println("8:"+af11.getStateById(8).epsilonClosure());
        System.out.println("6:"+af11.getStateById(6).epsilonClosure());
        System.out.println("Currente state:"+af11.getCurrentState().epsilonClosure());
        Afd afd=new Afd(af11.generateSetStates(),af11.getAlphabet());
        //afd.printTable();
        
    }
    
}
