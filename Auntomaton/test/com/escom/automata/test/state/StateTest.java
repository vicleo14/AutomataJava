
package com.escom.automata.test.state;

import com.escom.automata.IState;
import com.escom.automata.State;
import com.escom.automata.Transition;


public class StateTest {
    public static void main(String args[])
    {
       State s1=new State(false);
        Transition t=new Transition('a','j',s1);
        Transition t2=new Transition('1','9',s1);
        Transition t3=new Transition('1','9',s1);
        
        s1.addTransition(t);
        s1.addTransition(t2);
        System.out.println(s1.toString());
        IState s2=new State(true);
        System.out.println(s2.toString());
        
    }
}
