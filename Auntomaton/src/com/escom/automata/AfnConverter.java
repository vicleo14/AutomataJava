
package com.escom.automata;

import com.escom.automata.util.StatesCollection;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Victor
 */
public class AfnConverter {
    public Afd convertAfn(IAfn afn)
    {
        int i=0;
        StatesCollection sc=new StatesCollection();
        ArrayList<Collection> scAL=new ArrayList<Collection>();
        scAL.add(afn.epsilonClausure(afn.getCurrentState()));
        IState s=new State(i,false);
        sc.add(s);
        
        while(i<scAL.size())
        {
            for(Character c:afn.getAlphabet().getSymbols())
            {
                Collection col=afn.goTo(afn.epsilonClausure(scAL.get(i)), c);
                if(!scAL.contains(col) && !col.isEmpty())
                {
                    s=new State(scAL.size(),hasFinalState(col,afn));
                    sc.add(s);
                    scAL.add(col);
                    Transition t=new Transition(c,s);
                    IState state=(IState)sc.get(i);
                    state.addTransition(t);                    
                }
                else if(!col.isEmpty())
                {
                    s=(IState)sc.get(scAL.indexOf(col));
                    Transition t=new Transition(c,s);
                    IState state=(IState)sc.get(i);
                    state.addTransition(t);
                }
            }
            i++;
        }
        Afd afd=new Afd(sc,afn.getAlphabet());
        return afd;
    }
    public Integer hasFinalState(Collection s,IAfn afn)
    {
        for(IState state:afn.getStates())
        {
            if(s.contains(state) && state.getToken()!=-1)
            {
                return state.getToken();
            } 
        }
        return -1;
    }
}
