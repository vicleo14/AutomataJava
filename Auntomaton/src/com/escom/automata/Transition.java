package com.escom.automata;
/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

public class Transition
{
  private Character c;
  private Integer nextState;

  /*GETTERS*/
  public Character getC()
  {
    return c;
  }
  public Integer getNextState()
  {
    return nextState;
  }
  /*SETTERS*/
  public void setC(Character c)
  {
    this.c=c;
  }
  public void setNextState(Integer nextState)
  {
    this.nextState=nextState;
  }
  
  
}
