/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

package com.escom.automata;

import java.util.Collection;

public interface IState
{
  public Boolean isFinal();
  public Collection<Integer> epsilonClosure();
  public Boolean addTransition(Transition t);
  public Collection<Transition> getTransitions();
  public Integer getId();
}
