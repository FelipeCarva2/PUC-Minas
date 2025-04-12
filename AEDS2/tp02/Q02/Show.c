#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#define FILE_PATH "/home/felipe/PUCMinas/AEDS2/tp02/Q02/disneyplus.csv"
//#define FILE_PATH "/tmp/disneyplus.csv"
typedef struct Show{
    char show_id[10];
    char type[100];
    char title[100];
    char director[100];
    char cast[100][100];
    char country[100];
    struct tm date_added;
    int release_year;
    char rating[100];
    char duration[100];
    char listed_in[100][100];   
} Show;
Show show[1400];
void printShow(Show);





          


Show TrasInObj(char* linha) {
    Show showzinho;
    char obj[11][100] = {0}; // Inicializa tudo com zeros
    int i = 0, l = 0;
    
    while(i < strlen(linha) && l < 11) { // Adicionado limite para obj
        if(linha[i] == '\"') {
            char elemento[100] = {0};
            i++;
            int j = 0;
            
            while(i < strlen(linha) && j < 99) { // Proteção contra overflow
                if(linha[i] == '\"') {
                    if(i+1 < strlen(linha) && linha[i+1] == ',') {
                        i++;
                        break;
                    }
                }
                elemento[j] = linha[i];
                i++;
                j++;
            }
            strncpy(obj[l], elemento, sizeof(obj[l]) - 1);
            l++;
            
        } else if(linha[i] == ',') {
            if(i+1 < strlen(linha) && linha[i+1] == ',') {
                strncpy(obj[l], "NaN", sizeof(obj[l]) - 1);
                l++;
            }
            i++;
        } else {
            char elemento[100] = {0};
            int j = 0;
            
            while(i < strlen(linha) && linha[i] != ',' && j < 99) {
                elemento[j] = linha[i];
                i++;
                j++;
            }
            strncpy(obj[l], elemento, sizeof(obj[l]) - 1);
            l++;
        }
    }

    // Debug: imprimir todos os campos
    for(int w = 0; w < l; w++) {
        printf("obj[%d] = %s\n", w, obj[w]);
    }

    strncpy(showzinho.show_id, obj[0], sizeof(showzinho.show_id) - 1);
    strncpy(showzinho.type, obj[1], sizeof(showzinho.type) - 1);
    strncpy(showzinho.title, obj[2], sizeof(showzinho.title) - 1);
    strncpy(showzinho.director, obj[3], sizeof(showzinho.director) - 1);
    strncpy(showzinho.country, obj[5], sizeof(showzinho.country) - 1);
    strncpy(showzinho.rating, obj[8], sizeof(showzinho.rating) - 1);
    strncpy(showzinho.duration, obj[9], sizeof(showzinho.duration) - 1);

    // Campos especiais - CAST (array de strings)
    if(strlen(obj[4]) > 0) {
        char *token = strtok(obj[4], ","); // Separa por vírgulas
        int cast_index = 0;
        while(token != NULL && cast_index < 100) {
            // Remove espaços extras no início/fim
            char *trimmed = token;
            while(*trimmed == ' ') trimmed++;
            char *end = trimmed + strlen(trimmed) - 1;
            while(end > trimmed && *end == ' ') end--;
            *(end + 1) = '\0';
            
            strncpy(showzinho.cast[cast_index], trimmed, 
                   sizeof(showzinho.cast[0]) - 1);
            cast_index++;
            token = strtok(NULL, ",");
        }
    }

    // Campos especiais - LISTED_IN (array de strings)
    if(strlen(obj[10]) > 0) {
        char *token = strtok(obj[10], ",");
        int listed_index = 0;
        while(token != NULL && listed_index < 100) {
            // Trim como feito no cast
            char *trimmed = token;
            while(*trimmed == ' ') trimmed++;
            char *end = trimmed + strlen(trimmed) - 1;
            while(end > trimmed && *end == ' ') end--;
            *(end + 1) = '\0';
            
            strncpy(showzinho.listed_in[listed_index], trimmed, 
                   sizeof(showzinho.listed_in[0]) - 1);
            listed_index++;
            token = strtok(NULL, ",");
        }
    }

    // Campo DATA - assumindo formato "MMMM dd, yyyy" ou similar
    if(strlen(obj[6]) > 0) {
        char *date_str = obj[6];
        // Remove aspas se existirem
        if(date_str[0] == '"') {
            date_str++;
            date_str[strlen(date_str)-1] = '\0';
        }
        
        // Exemplo simples para formato "January 1, 2020"
        char month[20];
        int day, year;
        if(sscanf(date_str, "%s %d, %d", month, &day, &year) == 3) {
            // Converte mês para número (exemplo simplificado)
            const char *months[] = {"January","February","March","April","May","June",
                                   "July","August","September","October","November","December"};
            for(int m = 0; m < 12; m++) {
                if(strcmp(month, months[m]) == 0) {
                    showzinho.date_added.tm_mon = m;
                    break;
                }
            }
            showzinho.date_added.tm_mday = day;
            showzinho.date_added.tm_year = year - 1900;
        }
    }

    // Campo ANO - inteiro simples
    if(strlen(obj[7]) > 0) {
        showzinho.release_year = atoi(obj[7]);
    }

    









    
    // Teste completo da struct
printf("\n=== DADOS DO SHOW ===");
printf("\nID: %s", showzinho.show_id);
printf("\nTipo: %s", showzinho.type);
printf("\nTítulo: %s", showzinho.title);
printf("\nDiretor: %s", showzinho.director);

// Imprimir elenco (array de strings)
printf("\nElenco:");
if(strlen(showzinho.cast[0]) > 0) {
    for(int i = 0; i < 100 && strlen(showzinho.cast[i]) > 0; i++) {
        printf("\n  - %s", showzinho.cast[i]);
    }
} else {
    printf(" N/A");
}

printf("\nPaís: %s", showzinho.country);

// Imprimir data formatada
printf("\nData de adição: ");
if(showzinho.date_added.tm_year != 0) { // Ano 0 indica não inicializado
    char date_buffer[50];
    strftime(date_buffer, sizeof(date_buffer), "%B %d, %Y", &showzinho.date_added);
    printf("%s", date_buffer);
} else {
    printf("N/A");
}

printf("\nAno de lançamento: %d", showzinho.release_year);
printf("\nClassificação: %s", showzinho.rating);
printf("\nDuração: %s", showzinho.duration);

// Imprimir categorias (array de strings)
printf("\nCategorias:");
if(strlen(showzinho.listed_in[0]) > 0) {
    for(int i = 0; i < 100 && strlen(showzinho.listed_in[i]) > 0; i++) {
        printf("\n  - %s", showzinho.listed_in[i]);
    }
} else {
    printf(" N/A");
}

printf("\n=====================\n");





    return showzinho;
}


void printShow(Show s) {
printf(" ## %s", s.show_id);
printf(" ## %s", s.type);
printf(" ## %s", s.title);
printf(" ## %s", s.director);

// Imprimir elenco (array de strings)
printf(" ## [");
if(strlen(s.cast[0]) > 0) {
    for(int i = 0; i < 100 && strlen(s.cast[i]) > 0; i++) {
        printf(" %s,", s.cast[i]);
    }
} else {
    printf(" N/A");
}
printf("]");
printf(" ## %s", s.country);

// Imprimir data formatada
printf(" ## ");
if(s.date_added.tm_year != 0) { // Ano 0 indica não inicializado
    char date_buffer[50];
    strftime(date_buffer, sizeof(date_buffer), "%B %d, %Y", &s.date_added);
    printf(" %s", date_buffer);
} else {
    printf("N/A");
}

printf(" ## o %d", s.release_year);
printf(" ## %s", s.rating);
printf(" ## %s", s.duration);

// Imprimir categorias (array de strings)
printf(" ## [");
if(strlen(s.listed_in[0]) > 0) {
    for(int i = 0; i < 100 && strlen(s.listed_in[i]) > 0; i++) {
        printf(" %s,", s.listed_in[i]);
    }
} else {
    printf(" N/A");
}
printf("]");

printf("\n\n");
}

//+-+-+--+-+-+-+ Função ler arquivo csv +-+-+--+-+-+-+
void Ler(){

    FILE *file = fopen(FILE_PATH, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return;
    }
    char linha[500]; // Buffer para armazenar cada linha
    int aux=0;
    int i=0;
    while (fgets(linha, sizeof(linha), file) != NULL) {
        // Remove o caractere de nova linha, se existir
        linha[strcspn(linha, "\n")] = '\0';
        if(aux>0){
            printf(" \n%s\n", linha);
            show[i] = TrasInObj(linha);
            i++;
        }
        aux=1;
    }


   

}

int main(){
    
    
    Ler();
    char id[10];
    scanf("%s", id);
    
    while (strcmp(id, "FIM") != 0) {
        //ImprimirPorId();
        scanf("%s", id);
    }

    printf("\n\n\n\n\n\n");
    for(int i=0;i<1300; i++){
        printShow(show[i]);

    }
    
   
    return 0;
}