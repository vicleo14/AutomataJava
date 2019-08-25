
package com.escom.automata.test.transition;

import com.escom.automata.Transition;
import java.util.HashSet;


public class TransitionTest {
    public static void main(String[] args)
    {
        Transition t =new Transition('c',2);
        t.addNextState(3);
        t.addNextState(4);
        t.addNextState(5);
        Transition t2=new Transition('c',7);
        t2.addNextState(8);
        t2.addNextState(10);
        t2.addNextState(12);
        System.out.println(t.toString());
        System.out.println(t2.toString());
        t.joinTransition(t2);
        System.out.println(t.toString());
    }
}
