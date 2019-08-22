package com.escom.automata;
/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

import java.util.HashSet;

public class Alphabet
{
  private HashSet<Character> symbols;

  public Alphabet()
  {
    symbols=new HashSet<Character>();
  }
  public Boolean addElement(Character symbol)
  {
    if(!symbols.add(symbol))
    {
      System.out.println("WARNING: The symbol "+symbol+" was not added");
      return false;
    }
    return true;
  }
  public Boolean verifySymbol(Character symbol)
  {
    return symbols.contains(symbol);
  }
  public int size()
  {
    return symbols.size();
  }
  public String getAlphabet()
  {
    return symbols.toString();
  }
}
