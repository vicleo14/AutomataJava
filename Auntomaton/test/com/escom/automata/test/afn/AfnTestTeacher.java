/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.Afn;

/**
 *
 * @author Victor
 */
public class AfnTestTeacher {
    public static void main(String[] args)
    {
        /*GEENRAMOS f1-QUEDA EN f11*/
        Afn f11=new Afn('+');
        Afn f12=new Afn('-');
        Afn f13=new Afn('d');
        Afn f14=new Afn('.');
        Afn f15=new Afn('d');
        
        f11.addAFN(f12);
        f11.optional();
        f13.positiveClosure();
        f15.positiveClosure();
        
        f11.concatenateAFN(f13);
        f11.concatenateAFN(f14);
        f11.concatenateAFN(f15);
        
        /*GEENRAMOS f2-QUEDA EN f21*/
        Afn f21=new Afn('+');
        Afn f22=new Afn('-');
        Afn f23=new Afn('d');
        
        f21.addAFN(f22);
        f21.optional();
        f23.positiveClosure();
        
        f21.concatenateAFN(f23);
        
        /*GENERAMOS f3-QUEDA EN f31*/
        Afn f31=new Afn('a');
        Afn f32=new Afn('A');
        Afn f33=new Afn('a');
        Afn f34=new Afn('A');
        Afn f35=new Afn('d');
        
        f31.addAFN(f32);//[a-z]|[A-Z]
        f33.addAFN(f34);//[a-z]|[A-Z]
        f33.addAFN(f35);//[a-z]|[A-Z]|[0-9]
        f33.kleenClosure();
        f31.concatenateAFN(f33);
        
        /*GEENRAMOS f4-QUEDA EN f41*/
        Afn f41=new Afn('+');
        Afn f42=new Afn('+');
        
        f41.concatenateAFN(f42);
        
        
        
        /*GEENRAMOS f5-QUEDA EN f51 xD*/
        Afn f51=new Afn('+');
        
        /*UNION DE TODOS*/
        f11.addAFN(f21);
        f11.addAFN(f31);
        f11.addAFN(f41);
        f11.addAFN(f51);
        //System.out.println(f11.toString());
        System.out.println("Tabla.--------------------------------------");
        Afd afd=new Afd(f11.generateSetStates(),f11.getAlphabet());
        System.out.println("nnnn");
        afd.printTable();
    }
}
