
/* Sobre o código:
 * Metodologia: Um menu que seleciona qual tipo de ordenação será feita para os 4 vetores.
 * Todos os 4 vetores foram preenchidos com a mesma seed aleatoria para a analise ser feita para um mesmo conjunto de dados qualquer
 * Utilizado variaveis globais para as alterações nos arrays serem mais facil e não ter que ficar passando valores
 * Usei long para contar as movimentações e comparações pois com int estva dando overFlow
 * Usei os metodos e variaveis estaticos para não haver a necessidade de instanciar um objto da minha classe
 * 
 * Por Felipe Gabriel de Carvalho 
 * 30/04/2025
 * 
 */

import java.util.*;
public class OrdemCmp{
// ---------------------------------------------------------------------------------------------------- //
//Variaveis globais
    public static int[] vet100 = new int[100];
    public static int[] vet1000 = new int[1000];
    public static int[] vet10000 = new int[10000];
    public static int[] vet100000 = new int[100000];
    public static long mov=0;
    public static long cmp=0;
// ---------------------------------------------------------------------------------------------------- //
//Funções
      //+-+-+--+-+-+-+ Função Popular vetor +-+-+--+-+-+-+
    public static int[] preencheVetor(int[] vet){
        Random random = new Random(12345L); 
        for(int i=0;i<vet.length;i++){
            vet[i]= random.nextInt(10000);
        }
        return vet;
    }

    public static int[] crescente(int[] array) {
        int n = array.length;
		for (int i = 0; i < n; i++) {
			array[i] = i;
		}
        return array;
	}


	public static int[] decrescente(int[] array) {
        int n = array.length;
		for (int i = 0; i < n; i++) {
			array[i] = n - 1 - i;
		}
        return array;
	}
    //+-+-+--+-+-+-+ Função Printar vetor +-+-+--+-+-+-+
    public static void printVetor(int[] vet){
        System.out.println("Vetor de tamanho: " + vet.length);
        for(int i=0;i<vet.length;i++){
            System.out.println("[" + i + "]  " + vet[i]);
        }

    }

    public static void swap(int i, int j, int[]array) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

  //+-+-+--+-+-+-+ Função Selection Sort +-+-+--+-+-+-+
    public static void Selecao(int[] array) {
        mov =0; cmp =0;
        int n = array.length;
		for (int i = 0; i < (n - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < n; j++){
                cmp++;
				if (array[menor] > array[j]){
					menor = j;
				}
			}
			swap(menor, i, array);
            mov+=3;
		}
	}

    //+-+-+--+-+-+-+ Função Insercion Sort +-+-+--+-+-+-+
    public static void Insercao(int[] array) {
        mov =0; cmp =0;
        int n = array.length;
		for (int i = 1; i < n; i++) {
			int tmp = array[i];
         int j = i - 1;
         while ((j >= 0) && (array[j] > tmp)) {
            cmp++;//
            array[j + 1] = array[j];
            mov++;//
            j--;
         }
         array[j + 1] = tmp;
         mov++;//
      }
	}

    //+-+-+--+-+-+-+ Função Bubble Sort +-+-+--+-+-+-+
    public static void Bolha(int[] array) {
        mov =0; cmp =0;
        int n = array.length;
		for (int i = (n - 1); i > 0; i--) {
			for (int j = 0; j < i; j++) {
                cmp++;
				if (array[j] > array[j + 1]) {
               swap(j, j+1, array);
               mov+=3;
				}
			}
		}
   }


    //+-+-+--+-+-+-+ Função Quick Sort +-+-+--+-+-+-+
    public static void Quick(int[] array) {
        mov=0; cmp=0;
        int n = array.length;
        quicksort(0, n-1, array);
    }

    /**
     * Algoritmo de ordenacao Quicksort.
     * @param int esq inicio do array a ser ordenado
    * @param int dir fim do array a ser ordenado
    */
    private static void quicksort(int esq, int dir, int[] array) {
        int i = esq, j = dir;
        int pivo = array[(dir+esq)/2];
        while (i <= j) {
            while (array[i] < pivo){ cmp++;i++;}
            
            while (array[j] > pivo) {cmp++;j--;}
            
            if (i <= j) {
                swap(i, j, array);
                mov+=3;
                i++;
                j--;
            }
        }
        if (esq < j)  quicksort(esq, j, array);
        if (i < dir)  quicksort(i, dir, array);
        
    }
// ---------------------------------------------------------------------------------------------------- //
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double inicio, fim, duracao=0;
        vet100 = decrescente(vet100);
        vet1000 = decrescente(vet1000);
        vet10000 = decrescente(vet10000);
        vet100000 = decrescente(vet100000);

        //printVetor(vet100);
        //printVetor(vet1000);
        //printVetor(vet10000);
        //printVetor(vet100000);

        int op = sc.nextInt();
        switch (op) {
            case 1:
            System.out.println("Seleção");
                inicio = System.nanoTime();
                Selecao(vet100);
                fim = System.nanoTime();
                duracao = (fim - inicio)/1000000;
                System.out.println("[100] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

                inicio = System.nanoTime();
                Selecao(vet1000);
                fim = System.nanoTime();
                duracao = (fim - inicio)/1000000;
                System.out.println("[1000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

                inicio = System.nanoTime();
                Selecao(vet10000);
                fim = System.nanoTime();
                duracao = (fim - inicio)/1000000;
                System.out.println("[10000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

                inicio = System.nanoTime();
                Selecao(vet100000);
                fim = System.nanoTime();
                duracao = (fim - inicio)/1000000;
                System.out.println("[100000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);


           
                break;
        
            case 2:
            System.out.println("Inserção");
            inicio = System.nanoTime();
            Insercao(vet100);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[100] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Insercao(vet1000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[1000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Insercao(vet10000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[10000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Insercao(vet100000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[100000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);



                break;

            case 3:
            System.out.println("Bolha");
            inicio = System.nanoTime();
            Bolha(vet100);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[100] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Bolha(vet1000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[1000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Bolha(vet10000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[10000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Bolha(vet100000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[100000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

                break;

            case 4:
            System.out.println("Quick");
            inicio = System.nanoTime();
            Quick(vet100);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[100] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Quick(vet1000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[1000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Quick(vet10000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[10000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

            inicio = System.nanoTime();
            Quick(vet100000);
            fim = System.nanoTime();
            duracao = (fim - inicio)/1000000;
            System.out.println("[100000] Movimentações: " + mov + "\t  Comparações: " + cmp + "\t  Tempo: " + duracao);

                break;


            default:
                break;
        }

        //printVetor(vet100);
        //printVetor(vet1000);
        //printVetor(vet10000);
        //printVetor(vet100000);
    }


}