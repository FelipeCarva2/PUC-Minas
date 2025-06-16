import java.io.RandomAccessFile;
import java.io.IOException;

public class Q14 {

    public void salvarArquivo(int n) {
        try {
            //Cria um arquivo em modo escrita e leitura
            RandomAccessFile arquivo = new RandomAccessFile("saida.txt", "rw");
            //Lê o arquivo  até o taanho de entradas fornecidos
            for (int i = 0; i < n; i++) {
                //escreve no arquivo as entradas que foram lidas
                arquivo.writeDouble(MyIO.readDouble());
            }
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro na gravação!");
        }
    }

    public void lerReverso(int n) {
        try {
            RandomAccessFile arquivo = new RandomAccessFile("saida.txt", "r");
            for (long i = n - 1; i >= 0; i--) {
                arquivo.seek(i * 8L);
                System.out.println(arquivo.readDouble());
            }
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro na leitura!");
        }
    }

    public static void main(String[] args) {
        Q14 quest14 = new Q14();
        int n = MyIO.readInt();
        quest14.salvarArquivo(n);
        quest14.lerReverso(n);
    }
}