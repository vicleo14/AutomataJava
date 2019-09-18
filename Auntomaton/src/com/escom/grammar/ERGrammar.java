/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.grammar;

import com.escom.automata.Afn;
import com.escom.automata.IAfn;
import com.escom.automata.lexic.LexicAnalyzer;
import com.escom.automata.util.Constants;

/**
 *
 * @author Victor
 */
public class ERGrammar {
    private LexicAnalyzer lexic;
    public ERGrammar(LexicAnalyzer lexic)
    {
        this.lexic=lexic;
    }
    public Boolean E(IAfn f)
    {
        if(T(f))
        {
            if(Ep(f))
            {
                return true;
            }
        }
        return false;
    }
    public Boolean Ep(IAfn f)
    {
        int token;
        IAfn f2=new Afn();
        token=lexic.getToken();
        if(token==Constants.OR)
        {
            if(T(f2))
            {
                f.addAFN(f2);//REVISAR
                if(Ep(f))
                {
                    return true;
                }
            }
            return false;
        }
        lexic.undoYylex();
        return true;
    }
    public Boolean T(IAfn f)//CUIDADO CON EL RETURN
    {
        if(C(f))
        {
            if(Tp(f))
            {
                return true;
            }
        }
        return false;
    }
    public Boolean Tp(IAfn f)
    {
        int token;
        IAfn f2=new Afn();
        token=lexic.getToken();
        if(token==Constants.CONC)
        {
            if(C(f2))
            {
                f.concatenateAFN(f2);
                System.out.println("CONCATENA "+f.getAcceptedStates() +" con "+f2.getAcceptedStates());
                if(Tp(f))
                {
                    return true;
                }
            }
            return false;
        }
        lexic.undoYylex();
        return true;
    }
    public Boolean C(IAfn f)
    {
        if(F(f))
        {
            if(Cp(f))
            {
                return true;
            }
        }
        return false;
    }
    public Boolean Cp(IAfn f)
    {
        int token;
        token=lexic.getToken();
        switch(token)
        {

            case Constants.PROD:
                f.kleenClosure();
                if(Cp(f))
                {
                    return true;
                }
                return false;
            
            case Constants.ADD:
                f.positiveClosure();
                if(Cp(f))
                    
                {
                    return true;
                }
                return false;
            case Constants.OPT:
                f.optional();
                if(Cp(f))
                {
                    return true;
                }
                return false;
        }
        lexic.undoYylex();
        return true;
    }
    public Boolean F(IAfn f)
    {
        Integer token;
        token=lexic.getToken();
        switch(token)
        {
            case Constants.PAR_I:
                if(E(f))
                {
                    token=lexic.getToken();
                    if(token==Constants.PAR_D)
                    {
                        return true;
                    }
                }
                return false;
            case Constants.SIMB:
                f=new Afn(lexic.getLexeme().charAt(0));
                System.out.println("Se crea automata con "+lexic.getLexeme()+f.toString());
                return true;
        }
        return false;
    }
}
