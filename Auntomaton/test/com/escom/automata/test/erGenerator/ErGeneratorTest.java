/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.erGenerator;

import com.escom.automata.Afn;
import com.escom.automata.IAfn;
import com.escom.automata.er.ERAutomata;
import com.escom.automata.er.ERAutomata2;
import com.escom.automata.lexic.LexicAnalyzer;
import com.escom.grammar.ERGrammar;

public class ErGeneratorTest {
    public static void main(String args[])
    {
        String s="(a|b)?&9+&8+";
        ERAutomata2 erA=new ERAutomata2();
        erA.getAfd().getAfdTable().print();
        //System.out.println(afd.analizeString("+10.98"));
        LexicAnalyzer lexic=new LexicAnalyzer(s,erA.getAfd().getAfdTable());
        ERGrammar erG=new ERGrammar(lexic);
        IAfn f=new Afn();
        erG.E(f);
        System.out.println(f.toString());
        /*while(true)
        {
            Integer yyLexValue=lexic.yyLex();
            if(yyLexValue==0)
            {
                break;
            }
                
            System.out.println("yyLex:"+yyLexValue+" con lexema "+lexic.getLexeme());
        }*/
    }
}
