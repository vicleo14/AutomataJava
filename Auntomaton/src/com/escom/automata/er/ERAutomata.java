/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.er;

import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.AfnConverter;
import com.escom.automata.IAfd;
import com.escom.automata.IAfn;
import com.escom.automata.lexic.LexicAnalyzer;
import com.escom.automata.util.Constants;
import java.util.Scanner;

/**
 *
 * @author Victor
 */
public class ERAutomata {
    IAfd afd;
    public ERAutomata()
    {
        IAfn f1=getHibrid2();
        
        
        AfnConverter afnc=new AfnConverter();
        afd=afnc.convertAfn(f1);
        //System.out.println(afd.analizeString("a"));
        //System.out.println(afd.analizeString("a+&(a|b)?&c"));
        //System.out.println(afd.analizeString("(a|b)?&9+&.&8+"));
    }

    public IAfd getAfd() {
        return afd;
    }

    public void setAfd(IAfd afd) {
        this.afd = afd;
    }
    
    private IAfn generate()
    {
        return getAllAutomata();
    }
    private IAfn getHibrid1()
    {
        IAfn f1=getAllAutomata();
        IAfn f2=getAllAutomataWithPar();
        f1.addAFN(f2);
        return f1;
    }
     private IAfn getHibrid2()
    {
        IAfn f1=getHibrid1();
        IAfn f2=getHibrid1();
        
        
        
        Afn f3=new Afn('&');
        f3.associateToken(Constants.CONC);
        Afn f4=new Afn('|');
        f4.associateToken(Constants.OR);
        f3.addAFN(f4);
        f3.concatenateAFN(f2);
        
        f3.kleenClosure();
        f1.concatenateAFN(f3);
        return f1;
    }
    private IAfn getAllAutomataWithPar()
    {
        IAfn f1=getAllAutomata();
        Afn f2=new Afn('(');
        f2.associateToken(Constants.PAR_I);
        Afn f3=new Afn(')');
        f3.associateToken(Constants.PAR_D);
        f2.concatenateAFN(f1);
        f2.concatenateAFN(f3);
        
         /*AFN para SIMB(+|*|?)*/
        Afn f4=new Afn('+');
        f4.associateToken(Constants.ADD);
        Afn f5=new Afn('*');
        f5.associateToken(Constants.PROD);
        Afn f6=new Afn('?');
        f6.associateToken(Constants.OPT);
        
        f4.addAFN(f5);
        f4.addAFN(f6);
        f4.optional();
        f2.concatenateAFN(f4);
        
        return f2;
    }
    private IAfn getAllAutomata()
    {
        IAfn f1=generateBasicSimb();
        /*GENERAMOS LA SIGUIENTE OPCION*/
        IAfn f2=generateBasicSimb();
        Afn f3=new Afn('&');
        f3.associateToken(Constants.CONC);
        Afn f4=new Afn('|');
        f4.associateToken(Constants.OR);
        f3.addAFN(f4);
        f3.concatenateAFN(f2);
        f3.kleenClosure();
        //System.out.println(f3.toString());
        f1.concatenateAFN(f3);
        
        return f1;
    }
    private IAfn generateBasicSimb()
    {
         /*AFN para SIMB(+|*|?)*/
        Afn f1=new Afn('+');
        f1.associateToken(Constants.ADD);
        Afn f2=new Afn('*');
        f2.associateToken(Constants.PROD);
        Afn f3=new Afn('?');
        f3.associateToken(Constants.OPT);
        
        f1.addAFN(f2);
        f1.addAFN(f3);
        f1.optional();
        /*UNIMOS TOOS LOS AFN DE FINAL*/
        Afn f4=new Afn('0','9');
        Afn f5=new Afn('A','Z');
        Afn f6=new Afn('a','z');
        Afn f7=new Afn('.');
        f4.addAFN(f5);
        f4.addAFN(f6);
        f4.addAFN(f7);
        f4.associateToken(Constants.SIMB);
        f4.concatenateAFN(f1);
        
        return f4;
    }
}
