package com.escom.automata;

import java.util.Collection;

/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

public class Transition
{
  private Character initialSymbol,lastSymbol;
  private Collection<Integer> nextStates;

  /*GETTERS*/
  public Character getInitialSymbol()
  {
    return initialSymbol;
  }
  public Character getLastSymbol()
  {
    return lastSymbol;
  }
  public Collection<Integer> getNextStates()
  {
    return nextStates;
  }
  /*SETTERS*/
  public void setInitialSymbol(Character c)
  {
    this.initialSymbol=c;
  }
  public void setLastSymbol(Character c)
  {
    this.lastSymbol=c;
  }
  public void setNextStates(Integer nextState)
  {
    this.nextStates.add(nextState);
  }
  
  
}
