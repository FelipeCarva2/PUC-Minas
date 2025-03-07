
import java.util.*;

public class Q01 {

    // Método para testar se a palavra é palíndromo
    public boolean testePalindromo(String str) {
        int j = str.length() - 1;
        int i = 0;
        
        // Comparando os caracteres das extremidades até o meio da string
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false; //Se os caracteres comparados forem diferentes retorna false
            }
            i++; 
            j--; 
        }

        return true; //Se todos os caracteres forem iguais nas comparações retorna true
    }

    public static void main(String[] args) {
        // Cria uma instância da classe Q01
        Q01 palavra = new Q01();
        
        
        String texto;
		texto = MyIO.readString();  
        while (!(texto.equals("FIM"))) {   // Se a linha for "FIM", encerra o programa
			
			
            // Verifica se a string é um palíndromo e imprime SIM ou NAO
            if (palavra.testePalindromo(texto)) {
				MyIO.println("SIM");
            } else {
				MyIO.println("NAO");
            }
			texto = MyIO.readLine();  
        }
    }
}
