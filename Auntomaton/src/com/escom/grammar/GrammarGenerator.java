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
    boolean G(Grammar g){
	if(listaReglas(g)){
		return true;
	}
	return false;
    }

    boolean listaReglas(Grammar g){
        int token;
        if(regla(g)){
            token=lexic.getToken();
            if(token == Constants.PC){
                if(listaReglasP(g)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    boolean listaReglasP(Grammar g){
        LexicAnalyzer E;
        int token;
        E=lexic.getStatus();
        if(regla(g)){
            token=lexic.getToken();
            if(token==Constants.PC){
                if(listaReglasP(g))
                    return true;
                return false;
            }
        }
        lexic.setStatus(E);
        return true;
    }

    boolean regla(Grammar g){
        DerivationNode r = new DerivationNode();
        int token;
        if(ladoIzq(r)){
            token=lexic.getToken();
            if(token==Constants.FLECHA){
                if(ladosDerechos(r)){
                    g.add(r);
                    return true;
                }
            }
        }
        return false;
    }

    boolean ladoIzq(DerivationNode r){
        int token;
        token=lexic.getToken();
        if(token==Constants.SIMB){
            r.setDerivationSybol(lexic.getLexeme(token));//revisar parametros de lexeme
            return true;//Ojoooooo
        }
        return false;
    }

    boolean ladosDerechos(DerivationNode r){
        if(listaSimbolos(r)){
            if(ladosDerechosP(r)){
                return true;
            }
        }
        return false;
    }

    boolean ladosDerechosP(DerivationNode r){
        DerivationNode n2 = r.getDerivA();
        int token;
        token=lexic.getToken();
        if(token==Constants.OR){
            if(listaSimbolos(r)){
                n2.setDerivA(r.getDerivA());
                r.setDerivA(n2);
                if(ladosDerechosP(r)){
                    return true;
                }
            }
        }
        lexic.undoYylex();
        return true;
    }

    boolean listaSimbolos(DerivationNode r){
        DerivationNode n = new DerivationNode();
        int token;
        token=lexic.getToken();
        if(token==Constants.SIMB){
            n.setDerivationSybol(lexic.getLexeme(token));//revisar
            if(listaSimbolosP(n)){
                r.setDerivA(n);
                return true;
            }
        }
        return false;
    }

    boolean listaSimbolosP(DerivationNode n1){
        int token;
        DerivationNode n = new DerivationNode();
        token=lexic.getToken();
        if(token==Constants.SIMB){
            n.setDerivationSybol(lexic.getLexeme(token));
            n1.setDerivA(n);
            if(listaSimbolosP(n)){
                return true;
            }
        }
        lexic.undoYylex();
        return false;
    }
}
