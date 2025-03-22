import java.util.*;
import java.io.*;
import java.net.*;

public class Q13 {

    // Método para contar as ocorrências de uma letra específica no conteúdo HTML
    public static int contador(String endereco, char letra) {
        String linha;
        int cont = 0;
        char carac;
        try {
            URL url = new URL(endereco);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            linha = leitor.readLine();
            while (linha != null) {
                for (int i = 0; i < linha.length(); i++) {
                    carac = linha.charAt(i);
                    if (carac == letra) {
                        cont++;
                    }
                }
                linha = leitor.readLine();
            }
            leitor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cont;
    }

    // Método para contar as consoantes no conteúdo HTML
    public static int contadorConsoante(String endereco) {
        String linha, consoantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
        int cont = 0;
        char carac;
        try {
            URL url = new URL(endereco);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            linha = leitor.readLine();
            while (linha != null) {
                for (int i = 0; i < linha.length(); i++) {
                    carac = linha.charAt(i);
                    if (consoantes.indexOf(carac) != -1) {
                        cont++;
                    }
                }
                linha = leitor.readLine();
            }
            leitor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cont;
    }

    // Método para contar ocorrências de uma string no conteúdo HTML
    public static int contadorStr(String endereco, String str) {
        int cont = 0;
        String linha;
        try {
            URL url = new URL(endereco);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            linha = leitor.readLine();
            while (linha != null) {
                if (linha.contains(str)) {
                    cont++;
                }
                linha = leitor.readLine();
            }
            leitor.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cont;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nome = "", endereco = "";
        int x = 1;
        
        // Array com os valores das vogais com acento, sem acento, e com til
        int[] c = {97, 101, 105, 111, 117, 225, 233, 237, 243, 250, 224, 232, 236, 242, 249, 227, 245, 226, 234, 238, 244, 251};

        while (x == 1) {
            nome = sc.nextLine();
            if (nome.equals("FIM")) {
                x = -1;
            } else {
                endereco = sc.nextLine();
                // Contar as ocorrências de cada vogal com e sem acento
                for (int i = 0; i < 22; i++) {
                    System.out.print((char) c[i] + "(" + contador(endereco, (char) c[i]) + ") ");
                }
                // Contar as consoantes
                System.out.print("consoante(" + contadorConsoante(endereco) + ") ");
                // Contar as ocorrências das tags <br> e <table>
                System.out.print("<br>(" + contadorStr(endereco, "<br>") + ") ");
                System.out.print("<table>(" + contadorStr(endereco, "<table>") + ") ");
                // Imprimir o nome da página
                System.out.println(nome);
            }
        }
        sc.close();
    }
}
