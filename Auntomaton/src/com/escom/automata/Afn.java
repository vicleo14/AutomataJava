
package com.escom.automata;

import java.util.Collection;
import java.util.HashSet;

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
        alphabet.addElement(symbol);
        State state1=new State(false);
        State state2=new State(true);
        Transition t=new Transition(symbol,state2.getId());
        state1.addTransition(t);
                
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
