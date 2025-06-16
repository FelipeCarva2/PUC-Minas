import java.util.*;

public class Q18 {

    // Método para codificar, converte o caractere para sua respectiva representação na tablea ASCII, soma 3 e "reconverte"
    public String textbf(String textoNormal,int i) {
        String textoCodificado = "";
        int converte;
        //condição de parada é o tamanho do array
        if(textoNormal.length()==i){
            return "";
        }else{
        
            
            converte = (int)textoNormal.charAt(i); // Obtém o valor numérico do caractere
            converte += 3; // Desloca o caractere na tabela ASCII
            textoCodificado += (char) converte; // Converte de volta para caractere e adiciona à string
            return textoCodificado + textbf(textoNormal, i+1);
            
        }
        
    }

    public static void main(String args[]) {
        Q18 quest18 = new Q18();
        String texto = "";
        int i =0;
        
        // Lê as entradas até o comando "FIM"
        texto = MyIO.readLine(); 
        while (!(texto.equals("FIM"))) {
            MyIO.println(quest18.textbf(texto, i)); // Aplica a cifra e printa o texto codificado
            texto = MyIO.readLine(); 
        }
    }
}
