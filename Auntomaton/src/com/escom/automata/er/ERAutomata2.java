/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.er;

import com.escom.automata.Afn;
import com.escom.automata.AfnConverter;
import com.escom.automata.IAfd;
import com.escom.automata.IAfn;
import com.escom.automata.util.Constants;

/**
 *
 * @author Victor
 */
public class ERAutomata2 {
    IAfd afd;
    public ERAutomata2()
    {
        Afn f1=new Afn('+');
        f1.associateToken(Constants.ADD);
        Afn f2=new Afn('*');
        f2.associateToken(Constants.PROD);
        Afn f3=new Afn('?');
        f3.associateToken(Constants.OPT);
        
        Afn f4=new Afn('0','9');
        Afn f5=new Afn('A','Z');
        Afn f6=new Afn('a','z');
        Afn f7=new Afn('.');
        f4.addAFN(f5);
        f4.addAFN(f6);
        f4.addAFN(f7);
        f4.associateToken(Constants.SIMB);
        
        Afn f8=new Afn('&');
        f8.associateToken(Constants.CONC);
        Afn f9=new Afn('|');
        f9.associateToken(Constants.OR);
        
        
         Afn f10=new Afn('(');
        f10.associateToken(Constants.PAR_I);
        Afn f11=new Afn(')');
        f11.associateToken(Constants.PAR_D);
        
        f1.addAFN(f2);
        f1.addAFN(f3);
        f1.addAFN(f4);
        f1.addAFN(f8);
        f1.addAFN(f9);
        f1.addAFN(f10);
        f1.addAFN(f11);
        AfnConverter afnConverter=new AfnConverter();
        afd= afnConverter.convertAfn(f1);
        
    }

    public IAfd getAfd() {
        return afd;
    }

    public void setAfd(IAfd afd) {
        this.afd = afd;
    }
    
    
}
