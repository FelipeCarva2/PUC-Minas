import java.util.*;
public class Q16{

    //Metodo recursivo para testar se uma palvra é palindromo, varre as duas extremindades comparando os valores se são iguais
public boolean testePalindromo(String texto, int i, int j){

    if(i>=j){
        return true;
    }else{
        if(texto.charAt(i)==texto.charAt(j)){
            return testePalindromo(texto, i+1, j-1);
        }else{
            return false;
        }
    }
    
}



    public static void main(String[] args) {
        // Cria uma instância da classe Q16
        Q16 quest16 = new Q16();
        
        String texto;
		texto = MyIO.readString();  
        int i=0;
        int j=0;
        while (!(texto.equals("FIM"))) {   // Se a linha for "FIM", encerra o programa
			
			j=texto.length()-1; //ATENÇÃO! tem que tirar 1 para pegar os indices

            // Verifica se a string é um palíndromo e imprime SIM ou NAO
            if (quest16.testePalindromo(texto, i, j)) {
				MyIO.println("SIM");
            } else {
				MyIO.println("NAO");
            }
			texto = MyIO.readLine();  
        }
    }

}