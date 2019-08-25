
package com.escom.automata.test.state;

import com.escom.automata.IState;
import com.escom.automata.State;
import com.escom.automata.Transition;


public class StateTest {
    public static void main(String args[])
    {
        Transition t=new Transition('a','j',1);
        Transition t2=new Transition('1','9',3);
        Transition t3=new Transition('1','9',4);
        IState s1=new State(false);
        s1.addTransition(t);
        s1.addTransition(t2);
        System.out.println(s1.toString());
        IState s2=new State(true);
        System.out.println(s2.toString());
        
    }
}
