package com.escom.automata;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

public class Transition
{
  private Character initialSymbol,lastSymbol;
  private Collection<Integer> nextStates;
  public void init()
  {
      nextStates=new HashSet<>();
      
  }
  public Transition(Character c,Collection<Integer> nextStates)
  {
      init();
      if(nextStates!=null)
          this.nextStates=nextStates;
      initialSymbol=c;
      lastSymbol=c;
      
  }
  public Transition(Character c,Integer nextState)
  {
      init();
      initialSymbol=c;
      lastSymbol=c;
      nextStates.add(nextState);
  }
  public Transition(Character initialSymbol,Character lastSymbol,Collection<Integer> nextStates)
  {
      init();
      this.initialSymbol=initialSymbol;
      this.lastSymbol=lastSymbol;
      if(nextStates!=null)
          this.nextStates=nextStates;
  }
  public Transition(Character initialSymbol,Character lastSymbol,Integer nextState)
  {
      init();
      this.initialSymbol=initialSymbol;
      this.lastSymbol=lastSymbol;
      nextStates.add(nextState);
  }
  public Boolean addNextState(Integer i)
  {
    return nextStates.add(i);
  }
  public Boolean removeState(Integer i)
  {
      return nextStates.remove(i);
  }
  public void joinTransition(Transition t2)
  {
      nextStates.addAll(t2.getNextStates());
  }
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
  @Override
  public String toString()
  {
      String s=(initialSymbol==lastSymbol)?initialSymbol.toString():initialSymbol.toString()+":"+lastSymbol.toString();
      s+="=[";
      for(Integer i:nextStates)
      {
          s+=i+",";
      }
      
      return s+"]";
  }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.initialSymbol);
        hash = 41 * hash + Objects.hashCode(this.lastSymbol);
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
        final Transition other = (Transition) obj;
        if (!Objects.equals(this.initialSymbol, other.initialSymbol)) {
            return false;
        }
        if (!Objects.equals(this.lastSymbol, other.lastSymbol)) {
            return false;
        }
        return true;
    }
    
    public Boolean split(Transition t)
    {
        return nextStates.addAll(t.getNextStates());
    }
  
}
