/*
@author: Lara Cazares Jaime Arturo
@author: Morales Flores Victor Leonel
*/

package com.escom.automata;
public interface IAfn extends IAutomata
{
    public void optional();
    public void concatenateAFN(IAfn automata);
    public void addAFN(IAfn automata);
}
