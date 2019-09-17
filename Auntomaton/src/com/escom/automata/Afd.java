/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata;

import com.escom.automata.util.StatesCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author art
 */
public class Afd implements IAfd{

    private Collection<State> acceptedStates;
    private Collection<State> states;
    private IState currentState;
    private IState initialState;
    private Alphabet alphabet;
    private AfdTable afdTable;

    public AfdTable getAfdTable() {
        return afdTable;
    }

    public void setAfdTable(AfdTable afdTable) {
        this.afdTable = afdTable;
    }

    public Afd(Collection<State> acceptedStates, Collection<State> states, IState currentState, IState initialState, Alphabet alphabet) {
        this.acceptedStates = acceptedStates;
        this.states = states;
        this.currentState = currentState;
        this.initialState = initialState;
        this.alphabet = alphabet;
        afdTable=new AfdTable(this);
        
    }
    
    public Afd(StatesCollection sc,Alphabet alphabet)
    {
        init();
        this.alphabet=alphabet;
        Iterator it=sc.iterator();
        while(it.hasNext())
        {
            State statep=(State)it.next();
            if(statep.isFinal())
            {
                acceptedStates.add(statep);
            }
            states.add(statep);
        }
        currentState=(State)sc.get(0);
        afdTable=new AfdTable(this);
    }
     

    Afd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Collection<State> getAcceptedStates() {
        return acceptedStates;
    }

    public void setAcceptedStates(Collection<State> acceptedStates) {
        this.acceptedStates = acceptedStates;
    }

    public Collection<State> getStates() {
        return states;
    }

    public void setStates(Collection<State> states) {
        this.states = states;
    }

    public IState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IState currentState) {
        this.currentState = currentState;
    }

    public IState getInitialState() {
        return initialState;
    }

    public void setInitialState(IState initialState) {
        this.initialState = initialState;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }
    
    
   
    @Override
    public void positiveClosure() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void kleenClosure() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean analizeString(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void init()
    {
        acceptedStates=new StatesCollection();
        states=new StatesCollection();
        //currentState=new State(false);
        alphabet=new Alphabet();
    }
    
    @Override
    public String toString()
    {
        String info="AUTOMATA::\n";
        afdTable.print();
        info+="--ALPHABET--\n";
        info+=alphabet.getAlphabet()+"\n";
        info+="--STATES--\n";
        Iterator<State> statesIt=this.states.iterator();
        while(statesIt.hasNext())
        {
            info+=statesIt.next().description();
        }
        info+="--CURRENT STATE--\n";
        info+=currentState.toString()+"\n";
        info+="--FINAL STATES--\n";
        Iterator<State> acc=this.acceptedStates.iterator();
        while(acc.hasNext())
        {
            info+=acc.next().getId()+",";
        }
        return info;
    }
    
}
