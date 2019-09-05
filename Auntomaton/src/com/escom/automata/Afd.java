/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata;

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

    public Afd(Collection<State> acceptedStates, Collection<State> states, IState currentState, IState initialState, Alphabet alphabet) {
        this.acceptedStates = acceptedStates;
        this.states = states;
        this.currentState = currentState;
        this.initialState = initialState;
        this.alphabet = alphabet;
    }

     public Afd(Collection<SetState> setStates, Alphabet alphabet) {
        init();
        State.setCounter(new Integer(0));
        for(SetState setState: setStates){
            
            State state = new State(setState.isAccepted());
            for(Transition transition: setState.getTransitions()){
                state.addTransition(transition);
            }
            if(state.isFinal()){
                acceptedStates.add(state);
            }
            if(setState.isInitial()){
                this.initialState=state;
            }
            states.add(state);
        }
        this.alphabet = alphabet;
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
    public void printTable() {
        String info = "State\t";
        for(Character c: getAlphabet().getSymbols()){
            info+=c.charValue()+"(Symbol)\t";
        }
        info+="Final\n";
        for(State state : states){
            info+=state.getId()+"\t";
            for(Transition transition: state.getTransitions()){
                for(Character c: getAlphabet().getSymbols()){
                    int lowerLimit, highLimit, symbolInt=(int)c.charValue();
                    lowerLimit=(int)transition.getInitialSymbol().charValue();
                    highLimit=(int)transition.getLastSymbol().charValue();
                    //System.out.println("LowerLimit="+lowerLimit);
                    //System.out.println("HighLimit="+highLimit);
                    if(lowerLimit>=symbolInt && highLimit<=symbolInt){
                        info+=c.charValue()+"\t";
                    } else{
                        info+="-1\t";
                    }
                }
            }
            info+=state.isFinal()+"\t\n";
        }
        System.out.println(info);
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
        acceptedStates=new HashSet<>();
        states=new ArrayList<>();
        //currentState=new State(false);
        alphabet=new Alphabet();
    }
    
    @Override
    public String toString()
    {
        String info="AUTOMATA::\n";
        info+="--ALPHABET--\n";
        info+=alphabet.getAlphabet()+"\n";
        info+="--STATES--\n";
        Iterator<State> statesIt=this.states.iterator();
        while(statesIt.hasNext())
        {
            info+=statesIt.next().toString();
        }
        info+="--CURRENT STATE--\n";
        //info+=currentState.toString()+"\n";
        info+="--FINAL STATES--\n";
        Iterator<State> acc=this.acceptedStates.iterator();
        while(acc.hasNext())
        {
            info+=acc.next().getId()+",";
        }
        return info;
    }
    
}
