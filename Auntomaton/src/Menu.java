
import com.escom.automata.Afd;
import com.escom.automata.Afn;
import com.escom.automata.SetState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Menu {
    private HashMap<Integer,Afn> afns;
    private Afd afd;
    public Menu(){
        afns=new HashMap<>();
        
    }
    public static void main(String[] args)
    {
        Menu menu=new Menu();
        menu.show();
    }
    public void show()
    {
        Scanner sc=new Scanner(System.in);
        Afn afn1,afn2;
        while(true)
        {
            System.out.println("1.-Crear un afn");
            System.out.println("2.-Unir afns");
            System.out.println("3.-Concatenar afns");
            System.out.println("4.-Cerradura +");
            System.out.println("5.-Cerradura *");
            System.out.println("6.-Operador opcional");
            System.out.println("7.-Convertir a AFD");
            System.out.println("8.-Analizar cadena con AFD");

            switch(sc.nextInt())
            {
                case 1:
                    System.out.println("Introduce un caracter");
                    Afn afn=new Afn(sc.next().charAt(0));
                    afns.put(afn.getIdAfn(),afn);
                    System.out.println("AFN "+afn.getIdAfn()+" creado");
                    System.out.println(afn.toString());
                    break;
                case 2:
                    System.out.println("Selecciones los AFN a unir");
                    System.out.println("**AFN 1**");
                    afn1=selectAutomata();
                    System.out.println("**AFN 2**");
                    afn2=selectAutomata();
                    afn1.addAFN(afn2);
                    System.out.println("AFN "+afn1.getIdAfn()+" unido con "+afn2.getIdAfn());
                    afns.remove(afn2.getIdAfn());
                    System.out.println(afn1.toString());
                    break;
                case 3:
                    System.out.println("Selecciones los AFN a concatenar");
                    System.out.println("**AFN 1**");
                    afn1=selectAutomata();
                    System.out.println("**AFN 2**");
                    afn2=selectAutomata();
                    afn1.concatenateAFN(afn2);
                    System.out.println("AFN "+afn1.getIdAfn()+" concatenado con "+afn2.getIdAfn());
                    afns.remove(afn2.getIdAfn());
                    System.out.println(afn1.toString());
                    break;
                case 4:
                    System.out.println("**Escoge AFN para cerradura positiva**");
                    afn1=selectAutomata();
                    afn1.positiveClosure();
                    System.out.println("AFN  "+afn1.getIdAfn()+" con cerradura positiva");
                    System.out.println(afn1.toString());
                    break;
                case 5:
                    System.out.println("**Escoge AFN para cerradura de Kleen**");
                    afn1=selectAutomata();
                    afn1.kleenClosure();
                    System.out.println("AFN  "+afn1.getIdAfn()+" con cerradura de Kleen");
                    System.out.println(afn1.toString());
                    break;
                case 6:
                    System.out.println("**Escoge AFN para opearador opcional**");
                    afn1=selectAutomata();
                    afn1.optional();
                    System.out.println("AFN  "+afn1.getIdAfn()+" con operador opcional");
                    System.out.println(afn1.toString());
                    break;
                case 7:
                    System.out.println("**Escoge un AFN para convertirlo**");
                    afn1=selectAutomata();
                    System.out.println("SetStates:");
                    /*for(SetState s: afn1.generateSetStates()){
                        System.out.println(s.toString());
                    }
                    afd = new Afd(afn1.generateSetStates(), afn1.getAlphabet());
                    afd.printTable();*/
                    break;
                case 8:
                    System.out.println("**Analiza una cadena**");
                    System.out.println("Ingresa  una cadena:");
                    String cad=sc.next();
                    afd.analizeString(cad);
                    break;
                default: System.exit(0);
            }    
        }
    }
    public Afn selectAutomata()
    {
        Scanner sc=new Scanner(System.in);
        afns.forEach((k,v)->System.out.println("Automata "+k));
        System.out.println("Selecciones un indice:");
        return afns.get(sc.nextInt());
    }
}
