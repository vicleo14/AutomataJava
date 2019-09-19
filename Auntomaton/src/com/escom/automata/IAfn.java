/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

package com.escom.automata;

import java.util.Collection;

public interface IAfn extends IAutomata
{
    public void optional();
    public void concatenateAFN(IAfn automata);
    public void addAFN(IAfn automata);
    public Collection<State> getStates();
    public Alphabet getAlphabet();
    public Collection<IState> epsilonClausure(Collection<IState> states);
    public Collection<IState> epsilonClausure(IState state);
    public Collection<IState> goTo(Collection<IState> states, Character symbol);
    public Collection<IState> goTo(IState state, Character symbol);
    public IState getCurrentState();
    public Collection<State> getAcceptedStates();
    public void createBasic(Character symbol);
    public void createBasic(Character a,Character b);
    public void associateToken(Integer token);
    public void setCurrentState(IState state);
    public Integer getIdAfn();
}
