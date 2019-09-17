/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.AfnConverter;
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
        AfnConverter afnc=new AfnConverter();
        afnc.convertAfn(af11);
    }
    
}
