// ---------------------------------------------------------------------------------------------------- //
//Includes
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
// ---------------------------------------------------------------------------------------------------- //
//Variaveis globais
//#define FILE_PATH "/home/felipe/PUCMinas/AEDS2/tp02/Q02/disneyplus.csv"
#define FILE_PATH "/tmp/disneyplus.csv"
#define bool      short
#define true      1
#define false     0

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
Show show[1368];
//Show lista[1368];
int con=0, mov=0, comp=0;
// ---------------------------------------------------------------------------------------------------- //
// ---------------------------------------------------------------------------------------------------- //
// Getters para a estrutura Show

char* show_getId(Show *show) { return show->show_id; }
char* show_getType(Show *show) { return show->type; }
char* show_getTitle(Show *show) { return show->title; }
char* show_getDirector(Show *show) { return show->director; }
char* show_getCountry(Show *show) { return show->country; }
struct tm show_getDateAdded(Show *show) { return show->date_added; }
int show_getReleaseYear(Show *show) { return show->release_year; }
char* show_getRating(Show *show) { return show->rating; }
char* show_getDuration(Show *show) { return show->duration; }

// Getters para arrays (retorna elemento específico)
char* show_getCastMember(Show *show, int index) { 
    if(index >= 0 && index < 100) return show->cast[index]; 
    return NULL;
}

char* show_getListedInMember(Show *show, int index) { 
    if(index >= 0 && index < 100) return show->listed_in[index]; 
    return NULL;
}

// ---------------------------------------------------------------------------------------------------- //
// Setters para a estrutura Show

void show_setId(Show *show, char *id) { strcpy(show->show_id, id); }
void show_setType(Show *show, char *type) { strcpy(show->type, type); }
void show_setTitle(Show *show, char *title) { strcpy(show->title, title); }
void show_setDirector(Show *show, char *director) { strcpy(show->director, director); }
void show_setCountry(Show *show, char *country) { strcpy(show->country, country); }
void show_setDateAdded(Show *show, struct tm date) { show->date_added = date; }
void show_setReleaseYear(Show *show, int year) { show->release_year = year; }
void show_setRating(Show *show, char *rating) { strcpy(show->rating, rating); }
void show_setDuration(Show *show, char *duration) { strcpy(show->duration, duration); }

// Setters para arrays (define elemento específico)
void show_setCastMember(Show *show, int index, char *member) {
    if(index >= 0 && index < 100) strcpy(show->cast[index], member);
}

void show_setListedInMember(Show *show, int index, char *category) {
    if(index >= 0 && index < 100) strcpy(show->listed_in[index], category);
}
// ---------------------------------------------------------------------------------------------------- //
// Construtores para a estrutura Show

// Construtor sem parametros
Show show_newEmpty() {
    Show s;
    
    // Inicializa strings vazias
    strcpy(s.show_id, "");
    strcpy(s.type, "");
    strcpy(s.title, "");
    strcpy(s.director, "");
    strcpy(s.country, "");
    strcpy(s.rating, "");
    strcpy(s.duration, "");
    
    // Inicializa arrays de strings
    for(int i = 0; i < 100; i++) {
        strcpy(s.cast[i], "");
        strcpy(s.listed_in[i], "");
    }
    
    // Inicializa data com zeros
    memset(&s.date_added, 0, sizeof(struct tm));
    s.release_year = 0;
    
    return s;
}

// Construtor com parâmetros
Show show_new(
    char *id, char *type, char *title, char *director, 
    char *country, struct tm date_added, int release_year,
    char *rating, char *duration
) {
    Show s = show_newEmpty(); // Usa o construtor vazio como base
    
    // Define os valores fornecidos
    if(id) strncpy(s.show_id, id, sizeof(s.show_id) - 1);
    if(type) strncpy(s.type, type, sizeof(s.type) - 1);
    if(title) strncpy(s.title, title, sizeof(s.title) - 1);
    if(director) strncpy(s.director, director, sizeof(s.director) - 1);
    if(country) strncpy(s.country, country, sizeof(s.country) - 1);
    if(rating) strncpy(s.rating, rating, sizeof(s.rating) - 1);
    if(duration) strncpy(s.duration, duration, sizeof(s.duration) - 1);
    
    s.date_added = date_added;
    s.release_year = release_year;
    
    return s;
}
// ---------------------------------------------------------------------------------------------------- //
//Funções
void printShow(Show*, char*);

//Função clone
Show show_clone(Show *original) {
    Show clone = show_newEmpty();
    
    show_setId(&clone, original->show_id);
    show_setType(&clone, original->type);
    show_setTitle(&clone, original->title);
    show_setDirector(&clone, original->director);
    show_setCountry(&clone, original->country);
    show_setDateAdded(&clone, original->date_added);
    show_setReleaseYear(&clone, original->release_year);
    show_setRating(&clone, original->rating);
    show_setDuration(&clone, original->duration);
    
    
    for(int i = 0; i < 100 && strlen(original->cast[i]) > 0; i++) {
        show_setCastMember(&clone, i, original->cast[i]);
    }
    for(int i = 0; i < 100 && strlen(original->listed_in[i]) > 0; i++) {
        show_setListedInMember(&clone, i, original->listed_in[i]);
    } 
    return clone;
}

//+-+-+--+-+-+-+ Função para ordenar um array de Strings +-+-+--+-+-+-+
void ordenarStrings(char arr[][100], int tamanho) {
    char temp[100];
    
    for (int i = 0; i < tamanho - 1; i++) {
        for (int j = i + 1; j < tamanho; j++) {
            if (strcmp(arr[i], arr[j]) > 0) {
                strcpy(temp, arr[i]);
                strcpy(arr[i], arr[j]);
                strcpy(arr[j], temp);
            }
        }
    }
}

//+-+-+--+-+-+-+ Função transformar uma string em um objeto +-+-+--+-+-+-+
Show TrasInObj(char* linha) {
    Show showzinho = show_newEmpty();
    char obj[11][200] = {0}; 
    int i = 0, l = 0;
    
    while(i < strlen(linha) && l < 11) { 
        if(linha[i] == '\"') {
            char elemento[200] = {0};
            i++;
            int j = 0;
            
            while(i < strlen(linha) && j < 199) {
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
            char elemento[200] = {0};
            int j = 0;
            
            while(i < strlen(linha) && linha[i] != ',' && j < 199) {
                elemento[j] = linha[i];
                i++;
                j++;
            }
            strncpy(obj[l], elemento, sizeof(obj[l]) - 1);
            l++;
        }
    }

    //Ponto de controle, dados salvos em Strings 

    // Debug: imprimir todos os campos
    /*if(con<15){
        for(int w = 0; w < l; w++) {
            printf("obj[%d] = %s\n", w, obj[w]);
        }
        con++;
    }*/


    //Atribui os campos para o struct
    strncpy(showzinho.show_id, obj[0], sizeof(showzinho.show_id) - 1);
    strncpy(showzinho.type, obj[1], sizeof(showzinho.type) - 1);
    strncpy(showzinho.title, obj[2], sizeof(showzinho.title) - 1);
    strncpy(showzinho.director, obj[3], sizeof(showzinho.director) - 1);
    strncpy(showzinho.country, obj[5], sizeof(showzinho.country) - 1);
    strncpy(showzinho.rating, obj[8], sizeof(showzinho.rating) - 1);
    strncpy(showzinho.duration, obj[9], sizeof(showzinho.duration) - 1);


    //Atribui o cast
    if(strlen(obj[4]) > 0 && strcmp(obj[4], "NaN") != 0) {
        char *token = strtok(obj[4], ",");
        int cast_index = 0;
        while(token != NULL && cast_index < 100) {
            // Remove espaços extras
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
        
        // Ordenar o cast imediatamente após preencher
        if(cast_index > 0) {
            ordenarStrings(showzinho.cast, cast_index);
        }
    } else {
        // Se for vazio ou NaN, colocar apenas "NaN" no primeiro elemento
        strncpy(showzinho.cast[0], "NaN", sizeof(showzinho.cast[0]) - 1);
    }

    //atribui o listed_in
    if(strlen(obj[10]) > 0) {
        char *token = strtok(obj[10], ",");
        int listed_index = 0;
        while(token != NULL && listed_index < 100) {
            // Remove espaços extras
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
        
        // Ordenar as categorias imediatamente após preencher
        ordenarStrings(showzinho.listed_in, listed_index);
    }

    // Campo DATA - assumindo formato "MMMM dd, yyyy" ou similar
    if(strlen(obj[6]) > 0) {
        char *date_str = obj[6];
        // Remove aspas se existirem
        if(date_str[0] == '"') {
            date_str++;
            date_str[strlen(date_str)-1] = '\0';
        }
        
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

    return showzinho;
}


//+-+-+--+-+-+-+ Função para imprimir as series desejadas +-+-+--+-+-+-+
void imprimir(Show show) {
	int i = 0;
	printf("=> %s ## ", show.show_id);
	printf("%s ## ", show.title);
	printf("%s ## ", show.type);
	printf("%s ## ", show.director);
	printf("[");
	while (show.cast[i][0] != '\0') {
		printf("%s", show.cast[i]);
		if (show.cast[i + 1][0] != '\0') {
			printf(", ");
		}
		i++;
	}
	printf("] ## ");
	printf("%s ## ", show.country);

        if(show.date_added.tm_year != 0) { // Ano 0 indica não inicializado
            char date_buffer[50];
            strftime(date_buffer, sizeof(date_buffer), "%B %d, %Y", &show.date_added);
            printf(" %s", date_buffer);
        } else {
           printf("N/A");
        }
    printf(" ## ");
	printf("%d ## ", show.release_year);
	printf("%s ## ", show.rating);
	printf("%s ## ", show.duration);
	printf("[");
	i = 0;
	while (show.listed_in[i][0] != '\0') {
		printf("%s", show.listed_in[i]);
		if (show.listed_in[i + 1][0] != '\0') {
			printf(", ");
		}
		i++;
	}
	printf("] ##");
	printf("\n");
}

//+-+-+--+-+-+-+ Função ler arquivo csv +-+-+--+-+-+-+
void Ler(){

    FILE *file = fopen(FILE_PATH, "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return;
    }
    char linha[500];
    int aux=0;
    int i=0;
    while (fgets(linha, sizeof(linha), file) != NULL) {
        linha[strcspn(linha, "\n")] = '\0';
        if(aux>0){           
            show[i] = TrasInObj(linha);
            i++;
        }
        aux=1;
    }


   

}

//+-+-+--+-+-+-+ Função Pesquisa Binaria +-+-+--+-+-+-+
void buscaBinariaPorTitulo(Show *array, int tamanho, char *titulo) {
    int esquerda = 0;
    int direita = tamanho - 1;
    int encontrado = 0;
    
    while (esquerda <= direita) {
        int meio = esquerda + (direita - esquerda) / 2;
        int comparacao = strcmp(array[meio].title, titulo);
        
        if (comparacao == 0) {
            encontrado = 1;
            break;
        }
        comp++;
        
        if (comparacao < 0) {
            esquerda = meio + 1;
        } else {
            direita = meio - 1;
        }
        comp++;
    }
    
    if (encontrado) {
        printf("SIM\n");
    } else {
        printf("NAO\n");
    }
}

//+-+-+--+-+-+-+ Função para comparar títulos +-+-+--+-+-+-+
int compararTitulos(const void *a, const void *b) {
    const Show *showA = (const Show *)a;
    const Show *showB = (const Show *)b;
    return strcmp(showA->title, showB->title);
}

//+-+-+--+-+-+-+ Função para ordenar o array por título +-+-+--+-+-+-+
void ordenarPorTitulo(Show *array, int tamanho) {
    qsort(array, tamanho, sizeof(Show), compararTitulos);
}

//+-+-+--+-+-+-+ Função para fazer o swap de shows +-+-+--+-+-+-+
void swapShows(Show *a, Show *b) {
    Show temp = *a;
    *a = *b;
    *b = temp;
    mov=mov+3; 
}



//+-+-+--+-+-+-+-+-+-+--+-+-+-+  Implementação da lista +-+-+--+-+-+-+-+-+-+--+-+-+-+ 
typedef struct ListaSeq{
    int max;
    Show *array;
    int n;
}ListaSeq;

ListaSeq* newListaSeq(int max){
    ListaSeq *lista = (ListaSeq*) malloc(sizeof(ListaSeq));
    lista->max = max;
    lista->n = 0;
    lista->array = (Show*)malloc(max*sizeof(Show));
    return lista;
}

void delListaSeq(ListaSeq* lista){
    if(lista != NULL){
        free(lista->array);
        free(lista);
    }
}
    
void inserirInicio(ListaSeq* lista, Show elem){
    if(lista->n >= lista->max){
        printf("Erro!");
        return; 
    }
    for(int i = lista->n; i > 0; i--){
        lista->array[i] = lista->array[i-1];
    }
    lista->array[0] = elem;
    lista->n++;
}    

void inserirFim(ListaSeq* lista, Show elem){
    if(lista->n >= lista->max){
        printf("Erro!");
        return; 
    }
    lista->array[lista->n++] = elem;
}

Show removerInicio(ListaSeq* lista){
    if(lista->n == 0){
        printf("Erro!");
        return show_newEmpty();
    }
    Show x = lista->array[0];
    for(int i = 0; i < lista->n; i++){
        lista->array[i] = lista->array[i+1];
    }
    lista->n--;
    return x;
}

Show removerFim(ListaSeq* lista){
    if(lista->n == 0){
        printf("Erro!");
        return show_newEmpty();
    }
    lista->n--;
    Show x = lista->array[lista->n];
    return x;
}

void inserir(ListaSeq* lista, Show elem, int pos){
    if(lista->n >= lista->max || pos < 0 || pos > lista->n){
        printf("Erro!");
        return;
    }
    for(int i = lista->n; i > pos; i--){
        lista->array[i] = lista->array[i-1];
    }
    lista->array[pos] = elem;
    lista->n++;
}

Show remover(ListaSeq* lista, int pos){
    if(lista->n >= lista->max || pos < 0 || pos >= lista->n){
        printf("Erro!");
        return show_newEmpty();
    }
    Show x = lista->array[pos];
    for(int i = pos; i < lista->n-1; i++){
        lista->array[i] = lista->array[i+1];
    }
    lista->n--;
    return x;
}

Show procurar(Show* show, int tam, char id[]){
    for (int i = 0; i < tam; i++) {
        if (strcmp(show[i].show_id, id) == 0) {
                
            return show[i];
        }
    }
    return show_newEmpty();
}

Show pegar(ListaSeq* lista, int pos){
    return lista->array[pos];
}

int main() {
	Ler();
	int quant = 1368;
	char id[11];
	
	ListaSeq* lista = newListaSeq(1000);

	scanf("%[^\n]", id);
	getchar();
	while (strcmp(id, "FIM") != 0) {
		for (int i = 0; i < quant; i++) {
			if (strcmp(id, show[i].show_id) == 0) {
				inserirFim(lista, show[i]);
			}
		}
		scanf("%[^\n]", id);
		getchar();
	}

	ListaSeq* listaRemovidos = newListaSeq(1000);
	int n;
	scanf("%d", &n);
	for(int i = 0; i < n; i++) {
		char comando[5];
		int pos;
		scanf("%s", comando);
        
		if(strcmp(comando, "II")== 0) {
            scanf(" %s", id);
            inserirInicio(lista, procurar(show, quant, id));
		} else if(strcmp(comando, "IF") == 0) {
            scanf(" %s", id);
            inserirFim(lista, procurar(show, quant, id));
		} else if(strcmp(comando, "I*") == 0) {
            scanf("%d %s", &pos, id);
            inserir(lista, procurar(show, quant, id), pos);
		} else if(strcmp(comando, "RI") == 0) {
            Show removido = removerInicio(lista);
            inserirFim(listaRemovidos, removido);
		} else if(strcmp(comando, "RF") == 0) {
		    Show removido = removerFim(lista);
            inserirFim(listaRemovidos, removido);
		} else if(strcmp(comando, "R*") == 0) {
            scanf("%d", &pos);
            Show removido = remover(lista, pos);
            inserirFim(listaRemovidos, removido);
		}
	}
	
    for(int i = 0; i < listaRemovidos->n; i++){
        Show item = pegar(listaRemovidos, i);
        printf("(R) %s\n", item.title);
    }
    
    for(int i = 0; i < lista->n; i++){
        imprimir(pegar(lista, i));
    }

	delListaSeq(lista);
	delListaSeq(listaRemovidos);
	return 0;
}
   
      
