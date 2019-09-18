
package com.escom.automata.util;

import java.util.Scanner;

public class IOClass {
    Scanner sc;
    public IOClass()
    {
        sc=new Scanner(System.in);
    }
    public Integer askForToken()
    {
        System.out.println("Give me a token please. If you dont have put -1");
        return sc.nextInt();
    }
    public String askForString()
    {
        System.out.println("Give a string ");
        return sc.next();
    }
    
}
