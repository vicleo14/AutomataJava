package com.escom.automata;


public interface IAutomata {
    public void positiveClosure();
    public void kleenClosure();
    public Boolean analizeString(String string);
    
}
