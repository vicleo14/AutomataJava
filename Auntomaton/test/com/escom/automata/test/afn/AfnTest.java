package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.IState;
import com.escom.automata.SetState;
import com.escom.automata.State;
import java.util.Collection;
import java.util.HashSet;

public class AfnTest {
    public static void main(String args[])
    {
        Character a = new Character('a');
        Character b = new Character('b');
        Afn f = new Afn(a);
        //System.out.println("Afn states\n"+f.getStates().toString());
        //f.positiveClosure();
        //System.out.println("Afn states(positive clausure)\n"+f.getStates().toString());
        f.kleenClosure();
        System.out.println("Afn stastes(Kleen clausure)\n"+f.getStates().toString());
        //f.optional();
        //System.out.println("Afn states(optional)\n"+f.getStates().toString());
        //System.out.println("VErif="+f.analizeString(""));
        /*System.out.println("Epsilon Clausure:");
        Collection<Integer> i;
        i = new HashSet<>();;
        i.add(new Integer(1));
        i.add(new Integer(2));
        System.out.println(f.epsilonClausure(f.getStatesByIds(i)).toString());
        */
        //System.out.println("Move:");
        //System.out.println(f.move(f.getStates(), a).toString());
        //System.out.println("GoTo:");
        //System.out.println(f.goTo(f.getStates(), a));
        //System.out.println("analizar aaa");
        //System.out.println("strin="+f.analizeString("a"));
        /*System.out.println("SetStates:");
        for(SetState s: f.generateSetStates()){
            System.out.println(s.toString());
        }
        System.out.println("---------------------------------------------------------------");
        Afd afd = new Afd(f.generateSetStates(), f.getAlphabet());
        System.out.println("Afd:");
        System.out.println(afd.toString());
        System.out.println("--------------------------------------------------------------");
        afd.printTable();*/
    }
}
