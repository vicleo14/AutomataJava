/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.grammar;

import com.escom.automata.lexic.LexicAnalyzer;
import com.escom.automata.util.Constants;

/**
 *
 * @author art
 */
public class GrammarGenerator {
    private LexicAnalyzer lexic;
    boolean g(){
	if(listaReglas()){
		return true;
	}
	return false;
    }

    boolean listaReglas(){
        int token;
        if(regla()){
            token=lexic.getToken();
            if(token == Constants.PC){
                if(listaReglasP()){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    boolean listaReglasP(){
        LexicAnalyzer E;
        int token;
        E=lexic.getStatus();
        if(regla()){
            token=lexic.getToken();
            if(token==Constants.PC){
                if(listaReglasP())
                    return true;
                return false;
            }
        }
        lexic.setStatus(E);
        return true;//Duda debe ser falso
    }

    boolean regla(){
        int token;
        if(ladoIzq()){
            token=lexic.getToken();
            if(token==Constants.FLECHA){
                if(ladosDerechos()){
                    return true;
                }
            }
        }
        return false;
    }

    boolean ladoIzq(){
        int token;
        token=lexic.getToken();
        if(token==Constants.SIMB){
            return true;//Ojoooooo
        }
        return false;
    }

    boolean ladosDerechos(){
        if(listaSimbolos()){
            if(ladosDerechosP()){
                return true;
            }
        }
        return false;
    }

    boolean ladosDerechosP(){
        int token;
        token=lexic.getToken();
        if(token==Constants.OR){
            if(listaSimbolos()){
                if(ladosDerechosP()){
                    return true;
                }
            }
        }
        lexic.undoYylex();
        return true;
    }

    boolean listaSimbolos(){
        int token;
        token=lexic.getToken();
        if(token==Constants.SIMB){
            if(listaSimbolosP()){
                return true;
            }
        }
        return false;
    }

    boolean listaSimbolosP(){
        int token;
        token=lexic.getToken();
        if(token==Constants.SIMB){
            if(listaSimbolosP()){
                return true;
            }
        }
        lexic.undoYylex();
        return false;
    }
}
