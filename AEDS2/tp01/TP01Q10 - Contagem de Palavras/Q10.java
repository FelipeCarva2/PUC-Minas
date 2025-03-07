import java.util.*;

public class Q10{

    //varre a string em busca de espaço, se encontrar um contabiliza uma palavra
public int Contagem(String palavra){
    int cont =1;
for(int i=0; i<palavra.length();i++){
    if (palavra.charAt(i) == ' ' ){
        cont++;
    }

}
    
    return cont;
}



public static void main (String args []){
    Q10 quest10 = new Q10();
    Scanner sc = new Scanner (System.in);
    String frase = sc.nextLine();
    while (!frase.equals ("FIM")){
        System.out.println (quest10.Contagem(frase));
        frase = sc.nextLine();
    }
    sc.close();
}

}