
package com.escom.automata;

import com.escom.automata.util.Constants;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Afn implements IAfn{
    private Collection<State> acceptedStates;
    private Collection<State> states;
    private IState currentState;
    private Alphabet alphabet;
    
    private void init()
    {
        acceptedStates=new HashSet<>();
        states=new HashSet<>();
        //currentState=new State(false);
        alphabet=new Alphabet();
    }
    public Afn(Character symbol)
    {
        /*
        * Genera un automata simple a partir de un solo simbolo
        * 1.- Agregamos el simbolo al alfabeto
        * 2.- Creamos los estados incial y final
        * 3.- Creamos la transición del estado 1 al 2
        */
        init();
        alphabet.addElement(symbol);
        State state1=new State(false);
        State state2=new State(true);
        Transition t=new Transition(symbol,state2.getId());
        state1.addTransition(t);
        currentState=state1;
        states.add(state1);
        states.add(state2);
        acceptedStates.add(state2);
    }

    @Override
    public void optional() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void concatenateAFN(IAfn automata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAFN(IAfn automata) {
        /*
        * Unir un automata con el actual por Thompson
        * 1.- Unimos los alfabetos de los 2 automatas
        * 2.- Creamos un nuevo estado inicial
        * 3.- Creamons un nuevo estado final
        * 4.- Creamos transiciones epsilon del estado inicial o los estados iniciales anteriores
        * 5.- 
        */
        Afn af=(Afn)automata;
        this.alphabet.addAlphabet(af.getAlphabet());
        State newIniState=new State(false);
        State newFinalState=new State(true);
        State s=(State)currentState;
        
        State s2=(State)af.currentState;
        Transition t1=new Transition(Constants.EPSILON,s.getId());
        Transition t2=new Transition(Constants.EPSILON,s2.getId());
        newIniState.addTransition(t1);
        newIniState.addTransition(t2);
        currentState=newIniState;
        Iterator<State> its1=acceptedStates.iterator();
        Iterator<State> its2=acceptedStates.iterator();
        while(its1.hasNext())
        {
            State ss=its1.next();
            ss.addTransition(new Transition(Constants.EPSILON,newFinalState.getId()));
            ss.setFinalState(false);
        }
        while(its2.hasNext())
        {
            State ss=its2.next();
            ss.addTransition(new Transition(Constants.EPSILON,newFinalState.getId()));
            ss.setFinalState(false);
        }
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

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }
    @Override
    public String toString()
    {
        String info="AUTOMATA::\n";
        info+="--ALPHABET--\n";
        info+=alphabet.toString()+"\n";
        info+="--STATES--\n";
        Iterator<State> statesIt=this.states.iterator();
        while(statesIt.hasNext())
        {
            info+=statesIt.next().toString();
        }
        info+="--FINAL STATES--\n";
        Iterator<State> acc=this.acceptedStates.iterator();
        while(acc.hasNext())
        {
            info+=statesIt.next().getId()+",";
        }
        return info;
    }
    
    
}
