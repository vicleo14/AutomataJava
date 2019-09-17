
package com.escom.automata;

import com.escom.automata.util.Constants;
import com.escom.automata.util.StatesCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class Afn implements IAfn{
    private Collection<State> acceptedStates;
    private Collection<State> states;
    private IState currentState;
    private Alphabet alphabet;
    static Integer counterAfn=0;
    private Integer idAfn;

    public static Integer getCounterAfn() {
        return counterAfn;
    }

    public static void setCounterAfn(Integer counterAfn) {
        Afn.counterAfn = counterAfn;
    }

    public Afn(char c, char c0) {
        init();
        alphabet.addElement(c);
        alphabet.addElement(c0);
        State state1=new State(false);
        State state2=new State(true);
        Transition t=new Transition(c,c0,state2);
        state1.addTransition(t);
        currentState=state1;
        states.add(state1);
        states.add(state2);
        acceptedStates.add(state2);
    }

    public Integer getIdAfn() {
        return idAfn;
    }

    public void setIdAfn(Integer idAfn) {
        this.idAfn = idAfn;
    }
    
    private void init()
    {
        idAfn=counterAfn++;
        acceptedStates=new StatesCollection();
        states=new StatesCollection();
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
        Transition t=new Transition(symbol,state2);
        state1.addTransition(t);
        currentState=state1;
        states.add(state1);
        states.add(state2);
        acceptedStates.add(state2);
        currentState=state1;
    }
    public Afn(Character symbol,Integer token)
    {
        init();
        alphabet.addElement(symbol);
        State state1=new State(false);
        State state2=new State(true);
        state2.setToken(token);
        Transition t=new Transition(symbol,state2);
        state1.addTransition(t);
        currentState=state1;
        states.add(state1);
        states.add(state2);
        acceptedStates.add(state2);
        currentState=state1;
    }

    public void associateToken(Integer token)
    {
        for(IState s:acceptedStates)
        {
            s.setToken(token);
        }
    }
    @Override
    public void optional() {
        State newInitialState = new State(false);
        State newFinalState = new State(true);
        Transition transition = new Transition(Constants.EPSILON, ((State)currentState));
        newInitialState.addTransition(transition);
        this.setInitialState(newInitialState);
        addAcceptedState(this, newFinalState);
        transition = new Transition(Constants.EPSILON, newFinalState);
        currentState.addTransition(transition);
        states.add(newInitialState);
        states.add(newFinalState);
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
        states.add(finalState1);
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
        Transition t1=new Transition(Constants.EPSILON,s);
        Transition t2=new Transition(Constants.EPSILON,s2);
        newIniState.addTransition(t1);
        newIniState.addTransition(t2);
        currentState=newIniState;
        addAcceptedState(this,newFinalState);
        addAcceptedState(af,newFinalState);
        
        //acceptedStates.clear();
        //acceptedStates.add(newFinalState);
    }
    public void addAcceptedState(Afn afn,State newFinalState)
    {
        Iterator<State> its1=afn.getAcceptedStates().iterator();
        while(its1.hasNext())
        {
            State ss=its1.next();
            ss.addTransition(new Transition(Constants.EPSILON,newFinalState));
            ss.setFinalState(false);
        }
        afn.acceptedStates.clear();
        afn.acceptedStates.add(newFinalState);
        /*
        *Un AFnD puede tener más de un estado de aceptación, por lo tanto si es general me parece que 
        *quitarle los estados de aceptación, pero si es un automata de Thompson es correcto
        */

    }

    @Override
    public void positiveClosure() {
        /*
        * Cerradura positiva
        * 1.- Creamos un nuevo estado inicial
        * 2.- Creamons un nuevo estado final
        * 3.- Creamos transiciones epsilon del nuevo estado inicial al estado inicial anterior
        * 4.- Creamos transiciones epsilon de los estados finales anteriores al nuevo estado final
        */
        State newInitialState = new State(false);
        State newFinalState = new State(true);
        State actualInitialState=(State)currentState;
        Transition transition = new Transition(Constants.EPSILON, actualInitialState);
        newInitialState.addTransition(transition);
        for (State actualFinalState : acceptedStates) {
            transition = new Transition(Constants.EPSILON, actualInitialState);
            actualFinalState.addTransition(transition);
        }
        this.setInitialState(newInitialState);
        addAcceptedState(this, newFinalState);
        states.add(newInitialState);
        states.add(newFinalState);

    }

    @Override
    public void kleenClosure() {
        positiveClosure();
        if(acceptedStates.size()!=1){
            System.out.println("More than one final state or the collection is empty");
        } else{
            Iterator<State> iterator=this.getAcceptedStates().iterator();
            State actualAcceptedState=iterator.next();
            Transition transition = new Transition(Constants.EPSILON, actualAcceptedState);
            currentState.addTransition(transition);
       }
    }

    @Override
    public Boolean analizeString(String string) {
        Collection<IState> states;
        states=(Collection<IState>) (epsilonClausure(currentState));
        for(char symbol : string.toCharArray()){
            states=goTo(states, new Character(symbol));
        }
        for(IState state : states){
            if(state.isFinal()){
                return true;
            }
        }
        return false;
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
            info+=statesIt.next().description();
        }
        info+="--CURRENT STATE--\n";
        info+=currentState.toString()+"\n";
        info+="--FINAL STATES--\n";
        Iterator<State> acc=this.acceptedStates.iterator();
        while(acc.hasNext())
        {
            IState s=acc.next();
            info+=s.getId()+":"+s.getToken()+",";
        }
        return info;
    }
    
    
    public IState getInitialState() {
        return currentState;
    }

    public void setInitialState(IState currentState) {
        this.currentState = currentState;
    }
    
    public Collection<IState> epsilonClausure(Collection<IState> states){
        Collection<IState> c;
        c=new HashSet<>();
        for(IState state : states){
            c.addAll(epsilonClausure(state));
        }
        return c;
    }
    
    public Collection<IState> epsilonClausure(IState state){
        //System.out.println("Entra a letodo epsilon");
        Stack<IState> stack= new Stack<IState>();
        stack.push(state);
        IState e=null;
        Collection<IState> c;
        c=new HashSet<>();
        while(!stack.isEmpty())
        {
            IState s=stack.pop();
            if(!c.contains(s))
            {
                for(IState sp:s.epsilonClosure())
                {
                    stack.add(sp);
                }
                c.add(s);
            }
            else
            {
                continue;
            }
        }
        //System.out.println("Termina");
        return c;
    }
    
    public IState getStateById(int id){
        StatesCollection sc=(StatesCollection)states;
        return (IState)sc.get(id);
        
    }
    
    public Collection<IState> getStatesByIds(Collection<Integer> ids){
        //1System.out.println("Aqui ");
        Collection<IState> states;
        states=new HashSet<>();
        for(Integer id:ids){
            states.add(getStateById(id));
        }
        return states;
    }
    
    public Collection<IState> move(Collection<IState> states, Character symbol){
        Collection<IState> moveStates;
        moveStates=new HashSet<>();
        for(IState state: states){
            moveStates.addAll(move(state,symbol));
        }
        return moveStates;
    }
    
     
    public Collection<IState> move(IState e,Character c)
    {
        Collection<IState> r=new StatesCollection();
        for(Transition t:e.getTransitions())
        {
            if(t.getInitialSymbol()==c)
            {
                r.addAll(t.getNextStates());
            }
        }
        return r;
    }
    
    public Collection<IState> goTo(Collection<IState> states, Character symbol){
        return epsilonClausure(move(states, symbol));
    }
    
    public Collection<IState> goTo(IState state, Character symbol){
        return epsilonClausure(move(state, symbol));
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idAfn);
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
        final Afn other = (Afn) obj;
        if (!Objects.equals(this.idAfn, other.idAfn)) {
            return false;
        }
        return true;
    }
    
}