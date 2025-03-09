#include <stdio.h>

//Metódo para ler as entradas e salvar em um arquivo
void salvarArquivo(int n) {
    FILE *arquivo = fopen("saida.txt", "wb");
    
    if (arquivo == NULL) {
        printf("Erro ao abrir arquivo\n");
        return;
    }

    double valor;
    
    // Lê os valores e os escreve no arquivo
    for (int i = 0; i < n; i++) {
        scanf("%lf", &valor);
        fwrite(&valor, sizeof(double), 1, arquivo);
    }

    fclose(arquivo);
}
//Metódo para ler o conteudo de um arquivo de trás para frente
void lerReverso(int n) {
    FILE *arquivo = fopen("saida.txt", "rb");

    if (arquivo == NULL) {
        printf("Erro ao abrir arquivo\n");
        return;
    }

    double valor;
    
    // Lê os valores de trás para frente
    for (int i = n - 1; i >= 0; i--) {
        fseek(arquivo, i * sizeof(double), SEEK_SET); //fseek(arquivo, quanto vai mover, posição que começará amover);
        fread(&valor, sizeof(double), 1, arquivo);//ler dados de um arquivo binário e armazená-los em uma variável
        printf("%g\n", valor); // %g foi usado para representar a menor quantidade de digitos necessários para o número ponto flutuante
    }

    fclose(arquivo);
}

int main() {
    int n;
    scanf("%d", &n);
    salvarArquivo(n);
    lerReverso(n);
    return 0;
}
