/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata.lexic;

import com.escom.automata.AfdTable;
import com.escom.automata.util.Constants;
import java.util.Stack;

/**
 *
 * @author Victor
 */
public class LexicAnalyzer {
    private Stack<Integer> p;
    private AfdTable afdTable;
    String s;
    Boolean hadAcceptedState;
    Integer token;
    Integer actualCharacter;
    Integer iniLexeme;
    Integer lastLexeme;
    private String lexeme;
    
    public LexicAnalyzer(String cad,AfdTable afdTable)
    {
        s=cad+"$\0";
        this.afdTable=afdTable;
        p=new Stack<>();
        actualCharacter=0;
        iniLexeme=0;
        lastLexeme=0;
    }
    public Integer getToken()
    {
        return token;
    }
    public void undoYylex()
    {
        actualCharacter=p.pop();
    }
    public Character getLexeme(Integer i)
    {
        return 'a';
    }
    public Integer yyLex()
    {
        Integer actualState=0;
        hadAcceptedState=false;
        if(s.charAt(actualCharacter)=='\0')
        {
            
            return 0;
        }
        p.push(actualCharacter);
        iniLexeme=actualCharacter;
        while(s.charAt(actualCharacter)!='\0')
        {
            Integer indiceAlfabeto=-1;
            if(afdTable.getSymbols().containsKey(s.charAt(actualCharacter)))
            {
                indiceAlfabeto=afdTable.getSymbols().get(s.charAt(actualCharacter));
            }
            if(indiceAlfabeto==-1)
            {
                return asteriscoVerde();
            }
             //System.out.print("EVALUA '"+s.charAt(actualCharacter)+"' con i="+actualState+" y j="+indiceAlfabeto);
            actualState=afdTable.getTableTransition()[actualState][indiceAlfabeto];
            //System.out.println(" y nos da:"+actualState);
            if(actualState!=-1)
            {
                int x=afdTable.getTableTransition()[actualState][afdTable.getSymbols().size()];
                if(x!=-1)
                {
                    
                    token=x;
                    hadAcceptedState=true;
                    lastLexeme=actualCharacter;
                    //System.out.println("LEXEMA DETECTADO: "+s.substring(iniLexeme,lastLexeme+1));
                }
                actualCharacter++;               
            }
            else
            {
                
                //System.out.println("TRUENA AQUI2 con i="+actualState+" y j="+indiceAlfabeto);
                return asteriscoVerde();
            }
        }
        return 0;
    }
    private Integer asteriscoVerde()
    {
        if(!hadAcceptedState)
        {
            lexeme=s.substring(actualCharacter, actualCharacter+1);
            actualCharacter=++iniLexeme;
            return Constants.ERROR;
        }
        lexeme=s.substring(iniLexeme,lastLexeme+1);
        actualCharacter=++lastLexeme;
        return token;
    }
    public void getStatus()
    {
        
    }
    public void setStatus()
    {
        
    }

    public Stack<Integer> getP() {
        return p;
    }

    public void setP(Stack<Integer> p) {
        this.p = p;
    }

    public AfdTable getAfdTable() {
        return afdTable;
    }

    public void setAfdTable(AfdTable afdTable) {
        this.afdTable = afdTable;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Boolean getHadAcceptedState() {
        return hadAcceptedState;
    }

    public void setHadAcceptedState(Boolean hadAcceptedState) {
        this.hadAcceptedState = hadAcceptedState;
    }

    public Integer getActualCharacter() {
        return actualCharacter;
    }

    public void setActualCharacter(Integer actualCharacter) {
        this.actualCharacter = actualCharacter;
    }

    public Integer getIniLexeme() {
        return iniLexeme;
    }

    public void setIniLexeme(Integer iniLexeme) {
        this.iniLexeme = iniLexeme;
    }

    public Integer getLastLexeme() {
        return lastLexeme;
    }

    public void setLastLexeme(Integer lastLexeme) {
        this.lastLexeme = lastLexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }
    
}
