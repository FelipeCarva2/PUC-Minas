#include <stdio.h> // biblioteca para entrada e saída padrão
#include <string.h> //biblioteca para string
#include <stdlib.h> //biblioteca para alocação dinamica

void Combinador(char* str1, char* str2) {
    int tam1 = strlen(str1);
    int tam2 = strlen(str2);
    int tamTotal = tam1 + tam2;
  
    
    char *str3 = (char*)malloc((tamTotal + 1) * sizeof(char)); //Aloca dinamicamente um array de char

   
    int i = 0, j = 0;

    // Alterna os caracteres de str1 e str2
    while (i < tam1 && i < tam2) {
        str3[j++] = str1[i];
        str3[j++] = str2[i];
        i++;
    }

    // Adiciona o restante dos caracteres de str1, se houver
    while (i < tam1) {
        str3[j++] = str1[i++];
    }

    // Adiciona o restante dos caracteres de str2, se houver
    while (i < tam2) {
        str3[j++] = str2[i++];
    }

    str3[j] = '\0';  // Garante que a string resultante seja corretamente terminada

    // Exibe a string combinada
    printf("%s\n", str3);
    
    // Libera a memória alocada
    free(str3);
}

int main() {
    char str1[100];
    char str2[100];

    // Enquanto houver entrada
    while (scanf("%99s", str1) != EOF) {
        // Limpa o buffer de entrada antes de ler a segunda string
        getchar();  // Lê o caractere '\n' após o scanf

        fgets(str2, 100, stdin);  // Lê a segunda string
        str2[strcspn(str2, "\n")] = '\0';  // Remove o '\n' se existir

        // Chama a função Combinador para criar a nova string intercalada
        Combinador(str1, str2);
    }

    return 0;
}
