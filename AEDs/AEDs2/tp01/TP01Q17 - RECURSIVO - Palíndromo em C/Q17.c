#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//Metodo recursivo para testar se uma palvra é palindromo, varre as duas extremindades comparando os valores se são iguais
bool testePalindromo(char *palavra, int i, int j) {

    if (i >= j) {
        return true;
    }else{
        if (palavra[i] != palavra[j]) {//Interrompe se não for palindromo
            return false; 
        }
        return testePalindromo(palavra, i + 1, j - 1);//Faz a chamada recursiva

    }
}

int main() {

    char texto[1000];
    fgets(texto, 1000, stdin);
    texto[strcspn(texto, "\n")] = '\0'; 
    while (strcmp(texto, "FIM") != 0) {
        // Verifica se a string é um palíndromo e imprime SIM ou NAO
        if (testePalindromo(texto, 0, strlen(texto) - 1)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
        fgets(texto, 1000, stdin);
        texto[strcspn(texto, "\n")] = '\0'; 
    }
    return 0;
}