
package com.escom.automata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
public class State implements IState {
  static Integer counter=0;
  private Integer id;
  private Collection<Transition> transitions;
  private Boolean finalState;
    public State(Boolean finalState)
    {
        id=counter++;
        transitions=new ArrayList<>();
        this.finalState=finalState;
    }
    @Override
    public Boolean isFinal()
    {
        return finalState;
    }
    public Boolean addTransition(Character c,Integer nextState)
    {
        return true;
    }
    public Boolean addTransition(Transition t)
    {
        if(!transitions.contains(t))
            return transitions.add(t);
        else
        {
            for(Transition itT:transitions)
            {
                if(itT.equals(t))
                    return itT.split(t);
            }
        }
        return false;
    }
    public Boolean addTransitions(Collection t)
    {
        return transitions.addAll(t);
    }
    @Override
    public Collection<IState> epsilonClosure(Collection<IState> states) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Transition> getTransitions() {
        return transitions;
    }

    public Integer getId() {
        return id;
    }

    /*public void setId(Integer id) {
        this.id = id;
    }*/

    public void setTransitions(Collection<Transition> transitions) {
        this.transitions = transitions;
    }

    public Boolean getFinalState() {
        return finalState;
    }

    public void setFinalState(Boolean finalState) {
        this.finalState = finalState;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        State.counter = counter;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString()
    {
        String info="State "+id+":\n";
        Iterator<Transition> it=transitions.iterator();
        while(it.hasNext())
            info+=it.next().toString()+"\n";
        info+="Final state:"+finalState+"\n";
        return info;
    }
}
