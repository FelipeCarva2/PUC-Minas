#include<stdio.h>
#include<string.h>

int testePalindromo (char * palavra) {
	int resp = 1;
	int i=0, j;
	j=strlen(palavra); //Atribui a j o tamanho do array
	j=j-1; // Tira um para não comaparar o \0 da string
	while(i<j){
		if (palavra[i] != palavra[j]) {
			resp = 0;
			i=j;
		}
		//Decrementa o j e acrecesta o i para comparar as posições
		j--;
		i++;
	}

	return resp;
}
   

  int main() {
	char texto[1000];
	fgets(texto, 1000, stdin);
	texto[strcspn(texto, "\n")] = '\0'; //Retirar o \n que a função fgets coloca
	while(strcmp (texto, "FIM") != 0){
	if (testePalindromo(texto)==1){
	  printf("SIM\n");
	}else{
	  printf("NAO\n");
	}
	fgets(texto, 1000, stdin);
	texto[strcspn(texto, "\n")] = '\0';
	}
	return 0;
}
//O codigo funciona da seguinte forma, lê uma string e se a string for igual a FIM para, se não entra em um loop onde fica lendo strings e testando se são palindromos

