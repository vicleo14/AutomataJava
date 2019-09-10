/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author art
 */
public class SetState {
    private Collection<IState> states;
    private int id;
    private boolean initial;
    private boolean accepted;
    private boolean analyzed;
    private Collection<Transition> transitions;

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
    
    public Collection<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(Collection<Transition> transitions) {
        this.transitions = transitions;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        //id++;
        this.id = id;
    }

    public SetState(Collection<IState> states) {
        this.states = states;
        transitions=new ArrayList<>();
        updateAcceptedState();
    }

    public SetState(Collection<IState> states, boolean initial, boolean accepted, boolean analyzed, int id) {
        this.states = states;
        this.initial = initial;
        this.accepted = accepted;
        this.analyzed = analyzed;
        this.id=id;
        transitions=new ArrayList<>();
        updateAcceptedState();
    }

    public Collection<IState> getStates() {
        return states;
    }

    public void setStates(Collection<IState> states) {
        this.states = states;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isAnalyzed() {
        return analyzed;
    }

    public void setAnalyzed(boolean analyzed) {
        this.analyzed = analyzed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.states);
        hash = 67 * hash + this.id;
        hash = 67 * hash + (this.initial ? 1 : 0);
        hash = 67 * hash + (this.accepted ? 1 : 0);
        hash = 67 * hash + Objects.hashCode(this.transitions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        //System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final SetState other = (SetState) obj;
        int num=other.getStates().size();
        if(num!=this.states.size()){
            return false;
        }
        for(IState state2:other.getStates()){
            for(IState state: this.states){
                if(state.equals(state2)){
                    num--;
                }
            }
        }
        if(num!=0){
            return false;
        }
        /*if (this.id != other.id) {
            return false;
        }
        if (this.initial != other.initial) {
            return false;
        }
        if (this.accepted != other.accepted) {
            return false;
        }
        if (!Objects.equals(this.states, other.states)) {
            return false;
        }*/
        
        /*if (!Objects.equals(this.transitions, other.transitions)) {
            return false;
        }*/
        return true;
    }
   
    
    @Override
    public String toString()
    {
        String info="SetStateId="+id+"\n"; 
        info+="Initial="+initial+":\n";
        info+="Final state:"+accepted+"\n";
        //info+="Analysed:"+analyzed+"\n";
        info+="=>Set states:"+"\n";
        for(IState state: states){
            info+=state.getId();
            info+="\n";
        }
        info+="=>Transitions:\n";
        Iterator<Transition> it=transitions.iterator();
        while(it.hasNext())
            info+=it.next().toString()+"\n";
        return info;
    }
    
    public void updateAcceptedState(){
        for(IState state: states){
            if(state.isFinal()){
                accepted=true;
                break;
            }
        }
    }
}
