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
 * @author art
 */
public class ClaseTest {
    public static void main(String[] args)
    {
        /*Generamos f1*/
        Afn af11=new Afn('s');
        af11.optional();
        Afn f2= new Afn('d');
        f2.positiveClosure();
        af11.concatenateAFN(f2);
        
        /*Generamos f2*/
        Afn f21 = new Afn('s');
        f21.optional();
        Afn f22 = new Afn('d');
        f22.positiveClosure();
        Afn f23 = new Afn('.');
        Afn f24 = new Afn('d');
        f24.positiveClosure();
        
        f21.concatenateAFN(f22);
        f21.concatenateAFN(f23);
        f21.concatenateAFN(f24);
        
        /*Generamos F3*/
        Afn f31 = new Afn('l');
        Afn f32 = new Afn('l');
        Afn f33 = new Afn('d');
        f32.addAFN(f33);
        f32.kleenClosure();//chido
        
        f31.concatenateAFN(f32);
        
        /*Generamos f4*/
        Afn f41 = new Afn('=');
        Afn f42 = new Afn('=');
        f41.concatenateAFN(f42);
        
        /*Generamos f5*/
        Afn f51 = new Afn('+');
        
        af11.addAFN(f21);
        af11.addAFN(f31);
        af11.addAFN(f41);
        af11.addAFN(f51);
        //af11.addAFN(f21);
        //System.out.println(af11.toString());
        Afd afd=new Afd(af11.generateSetStates(),af11.getAlphabet());
        //System.out.println(afd.toString());
        afd.printTable();
        System.out.println("Fin");
    }
}
