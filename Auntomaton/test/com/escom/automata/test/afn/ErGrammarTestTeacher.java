/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.AfnConverter;
import com.escom.automata.IAfn;
import com.escom.automata.er.ERAutomata2;
import com.escom.automata.lexic.LexicAnalyzer;
import com.escom.automata.util.IOClass;
import com.escom.grammar.ERGrammar;

/**
 *
 * @author Victor
 */
public class ErGrammarTestTeacher {
    public static void main(String args[])
    {
        ERAutomata2 erA=new ERAutomata2();
        IOClass io=new IOClass();
        
        String s1="(P|N)&(0|1|2|3|4|5|6|7|8|9)+";
        LexicAnalyzer lexic1=new LexicAnalyzer(s1,erA.getAfd().getAfdTable());
        
        String s2="(P|N)&(0|1|2|3|4|5|6|7|8|9)+&.&(0|1|2|3|4|5|6|7|8|9)+";
        LexicAnalyzer lexic2=new LexicAnalyzer(s2,erA.getAfd().getAfdTable());
        
        String s3="L&(L|D)*";
        LexicAnalyzer lexic3=new LexicAnalyzer(s3,erA.getAfd().getAfdTable());
        
        String s4="(S|T)+";
        LexicAnalyzer lexic4=new LexicAnalyzer(s4,erA.getAfd().getAfdTable());
        
        String s5="P&P";
        LexicAnalyzer lexic5=new LexicAnalyzer(s5,erA.getAfd().getAfdTable());
        
        String s6="P";
        LexicAnalyzer lexic6=new LexicAnalyzer(s6,erA.getAfd().getAfdTable());
        
        ERGrammar erG1=new ERGrammar(lexic1);
        ERGrammar erG2=new ERGrammar(lexic2);
        ERGrammar erG3=new ERGrammar(lexic3);
        ERGrammar erG4=new ERGrammar(lexic4);
        ERGrammar erG5=new ERGrammar(lexic5);
        ERGrammar erG6=new ERGrammar(lexic6);
        
        IAfn f1=new Afn();
        IAfn f2=new Afn();
        IAfn f3=new Afn();
        IAfn f4=new Afn();
        IAfn f5=new Afn();
        IAfn f6=new Afn();
        erG1.E(f1);
        erG2.E(f2);
        erG3.E(f3);
        erG4.E(f4);
        erG5.E(f5);
        erG6.E(f6);
        System.out.println("Ingresa un token para este automata =)");
        f1.associateToken(10);
        f2.associateToken(20);
        f3.associateToken(30);
        f4.associateToken(40);
        f5.associateToken(50);
        f6.associateToken(60);
        
        f1.addAFN(f2);
        f1.addAFN(f3);
        f1.addAFN(f4);
        f1.addAFN(f5);
        f1.addAFN(f6);
        
        /*CONVERTIMOS*/
        AfnConverter afnc=new AfnConverter();
        Afd afd=afnc.convertAfn(f1);
        //afd.getAfdTable().print();
        
        String s="SSSP965PTTTP74.96STTSLDLDSSLDDDTPPP179SSLDLLL";
        LexicAnalyzer lexic=new LexicAnalyzer(s,afd.getAfdTable());
        while(true)
        {
            Integer yyLexValue=lexic.yyLex();
            if(yyLexValue==0)
            {
                break;
            }
                
            System.out.println("yyLex:"+yyLexValue+" con lexema "+lexic.getLexeme());
        }
        
    }
}
