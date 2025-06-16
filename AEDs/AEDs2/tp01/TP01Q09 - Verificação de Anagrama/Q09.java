import java.util.*;

class Q09{
//Recebe duas strings, as converte para array de caracteres, os ordena e por fim vê se tem o mesmo tamanho para comparar-las, se forem iguais, é um anagrama
	public static boolean testeAnagrama (String palavra1, String palavra2){
		boolean resp = true;
		
		palavra1 = palavra1.toUpperCase();
		palavra2 = palavra2.toUpperCase();
        //Precisa converter a string para um array de caractere
		char [] palavraChar1 = palavra1.toCharArray();
		char [] palavraChar2 = palavra2.toCharArray();
        //Arrays.sort serve para ordenar o array
		Arrays.sort (palavraChar1);
		Arrays.sort (palavraChar2);

        //primeiramente testa se as strings tem o mesmo tamanho se tiver compara as posições das strings
		if (palavra1.length() != palavra2.length()){
			resp = false;
		}else{
			for (int i = 0; i < palavra1.length(); i++){
				if (palavraChar1[i] != palavraChar2[i]){
					resp = false;
				}
			}
		}

		return resp;
	}

	public static void main (String args[]){

		Scanner sc = new Scanner (System.in);
		String entrada = sc.nextLine();
		
		while (!entrada.equals("FIM")){
		String [] palavras = entrada.split (" - "); //O método split() divide a string de entrada onde o padrão " - " (um espaço, seguido de um hífen e outro espaço) é encontrado.
		String primeiraPalavra = palavras[0];
		String segundaPalavra = palavras[1];        
		
		System.out.println (testeAnagrama (primeiraPalavra, segundaPalavra) ? "SIM" : "NÃO");
		entrada = sc.nextLine();
		}
	}

}