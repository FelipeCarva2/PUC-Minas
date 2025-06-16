import java.util.*;

public class Q03 {

    // Método para codificar, converte o caractere para sua respectiva representação na tablea ASCII, soma 3 e "reconverte"
    public String textbf(String textoNormal) {
        String textoCodificado = "";
        int converte;
        
        for (int i = 0; i < textoNormal.length(); i++) {
            converte = (int) textoNormal.charAt(i); // Obtém o valor numérico do caractere
            converte += 3; // Desloca o caractere na tabela ASCII
            textoCodificado += (char) converte; // Converte de volta para caractere e adiciona à string
        }
        
        return textoCodificado;
    }

    public static void main(String args[]) {
        Q03 cod = new Q03();
        
        String texto = MyIO.readLine();
        
        // Lê as entradas até o comando "FIM"
        
        while (!(texto.equals("FIM"))) {
            MyIO.println(cod.textbf(texto)); // Aplica a cifra e printa o texto codificado
            texto = MyIO.readLine(); 
        }
        
    }
}
