/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.erGenerator;

import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.AfnConverter;
import com.escom.automata.IAfn;
import com.escom.automata.er.ERAutomata;
import com.escom.automata.er.ERAutomata2;
import com.escom.automata.lexic.LexicAnalyzer;
import com.escom.automata.util.IOClass;
import com.escom.grammar.ERGrammar;
import java.util.Scanner;

public class ErGeneratorTest {
    public static void main(String args[])
    {
        IOClass io=new IOClass();
        String s="(0|1|2|3|4|5|6|7|8|9)&(0|1|2|3|4|5|6|7|8|9)*&.&(0|1|2|3|4|5|6|7|8|9)&(0|1|2|3|4|5|6|7|8|9)*";
        ERAutomata2 erA=new ERAutomata2();
        //erA.getAfd().getAfdTable().print();
        //System.out.println(afd.analizeString("+10.98"));
        LexicAnalyzer lexic=new LexicAnalyzer(s,erA.getAfd().getAfdTable());
        ERGrammar erG=new ERGrammar(lexic);
        IAfn f=new Afn();
        erG.E(f);
        System.out.println("Ingresa un token para este automata =)");
        f.associateToken(io.askForToken());
        
        
        /*CONVERTIMOS*/
        AfnConverter afnc=new AfnConverter();
        Afd afd=afnc.convertAfn(f);
        afd.getAfdTable().print();
        System.out.println(afd.analizeString(io.askForString()));
        
    }
}
