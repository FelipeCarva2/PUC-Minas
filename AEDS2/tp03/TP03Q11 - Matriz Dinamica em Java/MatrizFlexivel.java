import java.util.*;

public class MatrizFlexivel {
    private Celula inicio;

    public MatrizFlexivel() {
        this(3, 3);
    }

    public MatrizFlexivel(int linhas, int colunas) {
        if (linhas <= 0 || colunas <= 0) {
            throw new RuntimeException("Erro: dimensões inválidas!");
        }

        Celula primeiraLinha = new Celula(0);
        inicio = primeiraLinha;
        Celula atual = primeiraLinha;

        for (int j = 1; j < colunas; j++) {
            atual.direita = new Celula(0);
            atual.direita.esquerda = atual;
            atual = atual.direita;
        }

        for (int i = 1; i < linhas; i++) {
            Celula novaLinha = new Celula(0);
            primeiraLinha.abaixo = novaLinha;
            novaLinha.acima = primeiraLinha;
            atual = novaLinha;
            for (int j = 1; j < colunas; j++) {
                atual.direita = new Celula(0);
                atual.direita.esquerda = atual;
                atual.acima.direita.abaixo = atual.direita;
                atual.direita.acima = atual.acima.direita;
                atual = atual.direita;
            }
            primeiraLinha = primeiraLinha.abaixo;
        }
    }

    public void inserir(int linha, int coluna, int valor) {
        if (linha < 0 || coluna < 0) {
            throw new RuntimeException("Erro: índice inválido!");
        }
        Celula atual = inicio;
        for (int i = 0; i < linha; i++) {
            if (atual.abaixo == null) throw new IndexOutOfBoundsException("Erro! Linha inválida!");
            atual = atual.abaixo;
        }
        for (int j = 0; j < coluna; j++) {
            if (atual.direita == null) throw new IndexOutOfBoundsException("Erro! Coluna inválida!");
            atual = atual.direita;
        }
        atual.valor = valor;
    }

    public int remover(int linha, int coluna) {
        if (linha < 0 || coluna < 0) {
            throw new RuntimeException("Erro: índice inválido!");
        }
        Celula atual = inicio;
        for (int i = 0; i < linha; i++) atual = atual.abaixo;
        for (int j = 0; j < coluna; j++) atual = atual.direita;
        int valor = atual.valor;
        atual.valor = 0;
        return valor;
    }

    public int getTotalColunas() {
        int cont = 0;
        for (Celula atual = inicio; atual != null; atual = atual.direita) cont++;
        return cont;
    }

    public int getTotalLinhas() {
        int cont = 0;
        for (Celula atual = inicio; atual != null; atual = atual.abaixo) cont++;
        return cont;
    }

    public void imprimirDiagonalSecundaria() {
        int linhas = getTotalLinhas(), colunas = getTotalColunas();
        if (linhas != colunas) throw new RuntimeException("Erro: matriz não é quadrada!");
        Celula atualLinha = inicio;
        for (int i = 0; i < linhas; i++) {
            Celula atualColuna = atualLinha;
            for (int j = 0; j < colunas; j++) {
                if (i + j == colunas - 1) System.out.print(atualColuna.valor + " ");
                atualColuna = atualColuna.direita;
            }
            atualLinha = atualLinha.abaixo;
        }
        System.out.println();
    }

    public void imprimirDiagonalPrincipal() {
        int linhas = getTotalLinhas(), colunas = getTotalColunas();
        if (linhas != colunas) throw new RuntimeException("Erro: matriz não é quadrada!");
        Celula atualLinha = inicio;
        for (int i = 0; i < linhas; i++) {
            Celula atualColuna = atualLinha;
            for (int j = 0; j < colunas; j++) {
                if (i == j) System.out.print(atualColuna.valor + " ");
                atualColuna = atualColuna.direita;
            }
            atualLinha = atualLinha.abaixo;
        }
        System.out.println();
    }

    public MatrizFlexivel somar(MatrizFlexivel outra) {
        int linhas = getTotalLinhas(), colunas = getTotalColunas();
        MatrizFlexivel resultado = new MatrizFlexivel(linhas, colunas);

        Celula aLinha = this.inicio;
        Celula bLinha = outra.inicio;

        for (int i = 0; i < linhas; i++) {
            Celula aColuna = aLinha;
            Celula bColuna = bLinha;
            for (int j = 0; j < colunas; j++) {
                resultado.inserir(i, j, aColuna.valor + bColuna.valor);
                aColuna = aColuna.direita;
                bColuna = bColuna.direita;
            }
            aLinha = aLinha.abaixo;
            bLinha = bLinha.abaixo;
        }
        return resultado;
    }

    public MatrizFlexivel multiplicar(MatrizFlexivel outra) {
        int linhas1 = getTotalLinhas(), colunas1 = getTotalColunas();
        int colunas2 = outra.getTotalColunas();
        MatrizFlexivel resultado = new MatrizFlexivel(linhas1, colunas2);

        Celula linha1 = this.inicio;
        for (int i = 0; i < linhas1; i++) {
            Celula coluna2Inicial = outra.inicio;
            for (int j = 0; j < colunas2; j++) {
                Celula temp1 = linha1;
                Celula temp2 = coluna2Inicial;
                int soma = 0;
                for (int k = 0; k < colunas1; k++) {
                    soma += temp1.valor * temp2.valor;
                    temp1 = temp1.direita;
                    temp2 = temp2.abaixo;
                }
                resultado.inserir(i, j, soma);
                coluna2Inicial = coluna2Inicial.direita;
            }
            linha1 = linha1.abaixo;
        }
        return resultado;
    }

    public void mostrar() {
        for (Celula linha = inicio; linha != null; linha = linha.abaixo) {
            for (Celula coluna = linha; coluna != null; coluna = coluna.direita) {
                System.out.print(coluna.valor);
                if (coluna.direita != null) System.out.print(" ");
            }
            System.out.println();
        }
    }

    // < +-+-+-+-+-+-+-+-+-+ Main +-+-+-+-+-+-+-+-+-+ >
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();
        for (int t = 0; t < casos; t++) {
            int linhas1 = sc.nextInt(), colunas1 = sc.nextInt();
            MatrizFlexivel matriz1 = new MatrizFlexivel(linhas1, colunas1);
            for (int i = 0; i < linhas1; i++)
                for (int j = 0; j < colunas1; j++)
                    matriz1.inserir(i, j, sc.nextInt());

            int linhas2 = sc.nextInt(), colunas2 = sc.nextInt();
            MatrizFlexivel matriz2 = new MatrizFlexivel(linhas2, colunas2);
            for (int i = 0; i < linhas2; i++)
                for (int j = 0; j < colunas2; j++)
                    matriz2.inserir(i, j, sc.nextInt());

            MatrizFlexivel soma = matriz1.somar(matriz2);
            MatrizFlexivel produto = matriz1.multiplicar(matriz2);

            matriz1.imprimirDiagonalPrincipal();
            matriz1.imprimirDiagonalSecundaria();
            soma.mostrar();
            produto.mostrar();
        }
        sc.close();
    }
}

// < +-+-+-+-+-+-+-+-+-+ Classe Celula +-+-+-+-+-+-+-+-+-+ >
class Celula {
    public int valor;
    public Celula acima, esquerda, direita, abaixo;

    public Celula() {
        this(0);
    }

    public Celula(int valor) {
        this.valor = valor;
        this.acima = this.esquerda = this.direita = this.abaixo = null;
    }
}
