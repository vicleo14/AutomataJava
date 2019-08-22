/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

package com.escom.automata;

import java.util.HashSet;

public interface IState
{
  public Boolean isFinal();
  public HashSet<IState> epsilonClosure(HashSet<IState> states);
}
