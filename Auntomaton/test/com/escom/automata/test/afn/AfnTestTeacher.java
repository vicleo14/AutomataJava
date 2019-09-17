/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn;

import com.escom.automata.Afd;
import com.escom.automata.AfdTable;
import com.escom.automata.Afn;
import com.escom.automata.AfnConverter;
import java.util.Scanner;

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
        Afn f13=new Afn('0','9');
        Afn f14=new Afn('.');
        Afn f15=new Afn('0','9');
        
        //System.out.println(f11.toString());
        f11.addAFN(f12);
        f11.optional();
        
        
        f13.positiveClosure();
        //f15.positiveClosure();
        
        f11.concatenateAFN(f13);
        f11.concatenateAFN(f14);
        f11.concatenateAFN(f15);
        f11.associateToken(10);
        
        
        /*GEENRAMOS f2-QUEDA EN f21*/
        Afn f21=new Afn('+');
        Afn f22=new Afn('-');
        Afn f23=new Afn('0','9');
        
        f21.addAFN(f22);
        f21.optional();
        f23.positiveClosure();
        
        f21.concatenateAFN(f23);
        f21.associateToken(20);
        /*GENERAMOS f3-QUEDA EN f31*/
        Afn f31=new Afn('a','z');
        Afn f32=new Afn('A','Z');
        Afn f33=new Afn('a','z');
        Afn f34=new Afn('A','Z');
        Afn f35=new Afn('0','9');
        f31.addAFN(f32);//[a-z]|[A-Z]
        f33.addAFN(f34);//[a-z]|[A-Z]
        f33.addAFN(f35);//[a-z]|[A-Z]|[0-9]
        f33.kleenClosure();
        f31.concatenateAFN(f33);
        f31.associateToken(30);
        /*GEENRAMOS f4-QUEDA EN f41*/
        Afn f41=new Afn('+');
        Afn f42=new Afn('+');
        f41.concatenateAFN(f42);//BIEN
        f41.associateToken(40);
        
        /*GEENRAMOS f5-QUEDA EN f51 xD*/
        Afn f51=new Afn('+');
        f51.associateToken(50);
        /*UNION DE TODOS*/
        f11.addAFN(f21);
        f11.addAFN(f31);
        f11.addAFN(f41);
        f11.addAFN(f51);
        System.out.println("Tabla.--------------------------------------");

        AfnConverter afnc=new AfnConverter();
        Scanner sc=new Scanner(System.in);
        Afd afd=afnc.convertAfn(f11);
        afd.getAfdTable().print();
        System.out.println(afd.analizeString("victor10+9"));
        
    }
}
