
package com.escom.automata.test.afn.automata;

import com.escom.automata.Afn;
import com.escom.automata.State;
import com.escom.automata.Transition;

public class AutomataAddTest {
    public static void main(String args[])
    {
        Afn af1=new Afn('a');
        Afn af2=new Afn('b');
        //System.out.println(af1.toString());
        af1.addAFN(af2);
        System.out.println(af1.toString());
    }
    
}
