import java.util.*;

public class Q11 {

    public int SubString(String word) {
        int max = 0; // Armazena o valor do maior comprimento de substring
        int cont = 0; // Conta enquanto o caractere não se repetir
        int indiceDireita = 0; //indice para varrer a string para a direita
        int indiceEsquerda = 0; // indice para varrer para a esquerda caçando se o caractere da direita ja teve alguma ocorrencia

        // Percorre a string com o índice direito
        for (indiceDireita = 0; indiceDireita < word.length(); indiceDireita++) {
            char caractereAtual = word.charAt(indiceDireita);

            // Verifica se o caractere atual já apareceu na substring atual
           
            for (int i = indiceEsquerda; i < indiceDireita; i++) {
                if (word.charAt(i) == caractereAtual) {
                
                    indiceEsquerda = i + 1; // Move o início para depois do caractere repetido
                    break;
                }
            }

            
            cont = indiceDireita - indiceEsquerda + 1;

            // Atualiza o comprimento máximo da substring
            if (cont > max) {
                max = cont;
            }
        }

        return max;
    }

    public static void main(String args[]) {
        Q11 quest11 = new Q11();
        Scanner sc = new Scanner(System.in);
        String frase = sc.nextLine();

        while (!frase.equals("FIM")) {
            System.out.println(quest11.SubString(frase));
            frase = sc.nextLine();
        }

        sc.close();
    }
}