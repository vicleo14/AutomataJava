package com.escom.automata.test.afn;

import com.escom.automata.Afn;

public class AfnTest {
    public static void main(String args[])
    {
        Character a = new Character('a');
        Afn f = new Afn(a);
        System.out.println("Afn states\n"+f.getStates().toString());
        //f.positiveClosure();
        //System.out.println("Afn states(positive clausure)\n"+f.getStates().toString());
        //f.kleenClosure();
        //System.out.println("Afn states(Kleen clausure)\n"+f.getStates().toString());
        f.optional();
        System.out.println("Afn states(optional)\n"+f.getStates().toString());
    }
}
