
package com.escom.automata;

import java.util.HashSet;


public class State implements IState {
    
  private Integer id;
  private HashSet<Transition> transitions;
  private Boolean finalState;
  
  public Boolean isFinal()
  {
        return finalState;
  }
  public HashSet<IState> epsilonClosure(HashSet<IState> states)
  {
      HashSet<IState> ec=new HashSet<IState>();
      State s=new State();
      ec.add(s);
      return ec;
  }
  public Boolean addTransition(Character c,Integer nextState)
  {
      return true;
  }
  
  public Boolean addTransition(Transition t)
  {
      return true;
  }
  
    
    
}
