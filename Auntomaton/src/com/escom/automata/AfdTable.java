/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.escom.automata;

/**
 *
 * @author Victor
 */
public class AfdTable {
    Integer[][] tableTransition;
    char[] symbols;
    Integer numberOfStates;
    public Integer[][] getTableTransition() {
        return tableTransition;
    }

    public void setTableTransition(Integer[][] tableTransition) {
        this.tableTransition = tableTransition;
    }

    public char[] getSymbols() {
        return symbols;
    }

    public void setSymbols(char[] symbols) {
        this.symbols = symbols;
    }
    

    public AfdTable(Integer[][] tableTransition, char[] symbols) {
        this.tableTransition = tableTransition;
        this.symbols = symbols;
    }

    public AfdTable(Afd afd) {
        int i=0;
        numberOfStates=afd.getStates().size();
        this.tableTransition = new Integer[afd.getStates().size()][afd.getAlphabet().getSymbols().size()+1];
        System.out.println("Se inicializa");
        symbols = new char[afd.getAlphabet().getSymbols().size()];
        for(State state : afd.getStates()){
            //info+=state.getId()+"\t";
            int j=0;
            for(Character c: afd.getAlphabet().getSymbols()){
                if(state.getId()==0){
                    symbols[i]=c;
                    i++;
                }
                tableTransition[state.getId()][j]=(state.hastransition(c)!=null)?state.hastransition(c).getId():-1;
                //System.out.println("["+state.getId()+"]["+j+"]="+tableTransition[state.getId()][j]);
                j++;
            }
            tableTransition[state.getId()][j]=state.getToken();
        }
    }
    public void print()
    {
        System.out.println("Tabla:");
        String info = "State\t";
        for(Character c: symbols){
            info+=c.charValue()+"(Symbol)\t";
        }
        info+="Final";
        System.out.println(info);
        for(int i=0;i<numberOfStates;i++){
            System.out.print(i+"\t");
            for(int j=0; j<symbols.length+1; j++){
                System.out.print(tableTransition[i][j]+"\t\t");
            }
            System.out.println("");
        }
    }
    
    
}
