/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

package com.escom.automata;

import java.util.Collection;

public interface IState
{
  public Boolean isFinal();
  public Collection<IState> epsilonClosure(Collection<IState> states);
  public Boolean addTransition(Transition t);
  public Collection<Transition> getTransitions();
}
