/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.test.afn.automata;

import com.escom.automata.Afn;

/**
 *
 * @author Victor
 */
public class LLDAfn {
    public static void main(String args[]){
        /*Generamos F3*/
        Afn f31 = new Afn('l');
        Afn f32 = new Afn('l');
        Afn f33 = new Afn('d');
        f32.addAFN(f33);
        f32.kleenClosure();//chido
        
        f31.concatenateAFN(f32);
        System.out.println(f31.toString());
    }
}
