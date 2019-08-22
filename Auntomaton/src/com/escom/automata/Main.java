package com.escom.automata;
public class Main
{
    public static void main(String args[])
    {
      Alphabet alphabet=new Alphabet();
      System.out.println("Hello");
      alphabet.addElement('a');
      alphabet.addElement('b');
      alphabet.addElement('c');
      alphabet.addElement('a');
      alphabet.addElement('a');

      System.out.println(alphabet.getAlphabet());
    }
  }
