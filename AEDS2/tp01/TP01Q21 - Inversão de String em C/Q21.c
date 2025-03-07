
#include <stdio.h>
#include <string.h>

void inverterString(char *str) {
    // Caso base: chegar no fim da string, ou seja \0, aqui começa a desempilhar
    if (*str == '\0') {
        return;
    }else{
        inverterString(str + 1); //anda com o indice do array, é nessa parte que vai "Empilhando"
        printf("%c", *str); //vai printando de trás para frente
    }

  
   
}

int main() {
	char texto[1000];
	fgets(texto, 1000, stdin);
	texto[strcspn(texto, "\n")] = '\0'; //Retirar o \n que a função fgets coloca
	while(strcmp (texto, "FIM") != 0){
        inverterString(texto);
        printf("\n");
        fgets(texto, 1000, stdin);
        texto[strcspn(texto, "\n")] = '\0';
    }
	return 0;
}