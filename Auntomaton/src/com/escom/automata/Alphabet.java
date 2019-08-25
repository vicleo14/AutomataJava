package com.escom.automata;
/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

import java.util.Collection;
import java.util.HashSet;

public class Alphabet
{
  private Collection<Character> symbols;
  
  public Alphabet()
  {
    symbols=new HashSet<>();
  }
  public Boolean addAlphabet(Alphabet alphabet2)
  {
      Collection symbols2=alphabet2.getSymbols();
      return this.symbols.addAll(symbols2);
  }
  public Alphabet(Character initialSymbol,Character lastSymbol)
  {
      symbols=new HashSet<>();
      for(int i=initialSymbol;i<=lastSymbol;i++)
      {
          symbols.add((char)i);
      }
  }
  public Boolean removeElement(Character symbol)
  {
      return symbols.remove(symbol);
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

    public Collection<Character> getSymbols() {
        return symbols;
    }

    public void setSymbols(Collection<Character> symbols) {
        this.symbols = symbols;
    }
  
}
