
package com.escom.automata.test.transition;

import com.escom.automata.State;
import com.escom.automata.Transition;
import java.util.HashSet;


public class TransitionTest {
    public static void main(String[] args)
    {
        State s=new State(false);
        Transition t =new Transition('c',s);
        t.addNextState(s);
        t.addNextState(s);
        t.addNextState(s);
        Transition t2=new Transition('c',s);
        t2.addNextState(s);
        t2.addNextState(s);
        t2.addNextState(s);
        System.out.println(t.toString());
        System.out.println(t2.toString());
        t.joinTransition(t2);
        System.out.println(t.toString());
    }
}
