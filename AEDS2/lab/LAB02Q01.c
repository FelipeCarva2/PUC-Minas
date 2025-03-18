#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* Combinador(char* str1, char* str2) {
    int tam1 = strlen(str1);
    int tam2 = strlen(str2);
    int tamTotal = tam1 + tam2;
  
    // Aloca memória para a string
    char *str3 = (char*)malloc((tamTotal + 1) * sizeof(char));
    
    int i = 0, j = 0;

    for(i; i<tam1;i++){
        str3[j]=str1[i];
        j+=2;
    } 
    i = 0, j=1;

    for(i; i<tam1;i++){
        str3[j]=str2[i];
        j+=2;
    } 
    j--;
    for(i;i<=tamTotal;i++){
       str3[j]=str2[i];
       j++;
    }

    str3[i] = '\0';

    return str3;
}

int main() {
    char str1[100];
    char str2[100];

    //fgets(str1, 100, stdin);
    //fgets(str2, 100, stdin);

    while(scanf("%s", str1)!=EOF){
        //fgets(str1, 100, stdin);
        fgets(str2, 100, stdin);
       //str1[strcspn(str1, "\n")] = '\0';
        str2[strcspn(str2, "\n")] = '\0';
        char *str3 = Combinador(str1, str2);
        printf("%s\n", str3);
        free(str3);
    }
  

    return 0;
}
