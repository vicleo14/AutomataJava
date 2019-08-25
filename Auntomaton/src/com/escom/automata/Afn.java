
package com.escom.automata;

import com.escom.automata.util.Constants;
import java.util.ArrayList;
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
        states=new ArrayList<>();
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
        /*
        * Cerradura positiva
        * 1.- Unimos los alfabetos de los 2 automatas
        * 2.- Elegimos el estado final de este automata en caso de que haya mas de uno
        * 3.- Quitamos el caracter de estado fina al ese estado del paso 2
        * 4.- Agregamos transiion al estado final elegido opiando las transiiones del estado iniial del automata reibido
        * 5.- Eliminamos estado iniial del automata reibido
        * 6.- Anadimos estados del automata 2
        * 7.- Anadimos estados finales del automata                                         
        */
        Integer selected;  
        State finalState1;
        Afn af=(Afn)automata;
        this.getAlphabet().addAlphabet(af.getAlphabet());
        if(this.acceptedStates.size()>1)
        {
            selected=selectFinalState();
            finalState1=null;
        }
        else
            finalState1=acceptedStates.iterator().next();
        finalState1.setFinalState(false);
        acceptedStates.remove(finalState1);
        finalState1.addTransitions(af.getCurrentState().getTransitions());
        af.getStates().remove(af.currentState);
        this.states.addAll(af.getStates());
        acceptedStates.addAll(af.getAcceptedStates());
    }
    public Integer selectFinalState()
    {
        return -1;
    }
    @Override
    public void addAFN(IAfn automata) {
        /*
        * Unir un automata con el actual por Thompson
        * 1.- Unimos los alfabetos de los 2 automatas
        * 2.- Creamos un nuevo estado inicial
        * 3.- Creamons un nuevo estado final
        * 4.- Creamos transiciones epsilon del estado inicial a los estados iniciales anteriores
        * 5.- Creamos transiciones epsilon de los estados finales anteriores al nuevo estado final
        */
        Afn af=(Afn)automata;
        this.alphabet.addAlphabet(af.getAlphabet());
        State newIniState=new State(false);
        State newFinalState=new State(true);
        states.add(newIniState);
        states.add(newFinalState);
        State s=(State)currentState;
        
        State s2=(State)af.currentState;
        Collection<State> states2=af.getStates();
        states.addAll(states2);
        Transition t1=new Transition(Constants.EPSILON,s.getId());
        Transition t2=new Transition(Constants.EPSILON,s2.getId());
        newIniState.addTransition(t1);
        newIniState.addTransition(t2);
        currentState=newIniState;
        addAcceptedState(this,newFinalState);
        addAcceptedState(af,newFinalState);
        
        acceptedStates.clear();
        acceptedStates.add(newFinalState);
    }
    public void addAcceptedState(Afn afn,State newFinalState)
    {
        Iterator<State> its1=afn.getAcceptedStates().iterator();
        while(its1.hasNext())
        {
            State ss=its1.next();
            ss.addTransition(new Transition(Constants.EPSILON,newFinalState.getId()));
            ss.setFinalState(false);
        }
    }

    @Override
    public void positiveClosure() {
        /*
        * Cerradura positiva
        * 1.- Creamos un nuevo estado inicial
        * 2.- Creamons un nuevo estado final
        * 3.- Creamos transiciones epsilon del nuevo estado inicial al estado inicial anteriorcccccfgccccccccccccccccccccccccccccccccccccccccccccc 
        * 4.- Creamos transiciones epsilon de los estados finales anteriores al nuevo estado final
        */
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
        info+=alphabet.getAlphabet()+"\n";
        info+="--STATES--\n";
        Iterator<State> statesIt=this.states.iterator();
        while(statesIt.hasNext())
        {
            info+=statesIt.next().toString();
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
