import java.util.*;
public class Q04{

    //Em tese, inicializa uma string vazia e vai preenchendo ela  de acordo com a string recebida e quando a letra aleatoria 1 aparecer na string que recebe, substitui ela pela letra aleatoria 2
    public String Altera(String str){
        String cod ="";
        Random gerador = new Random();
        gerador.setSeed(4);
        char letra1 = (char) ('a' + Math.abs(gerador.nextInt()) % 26);  
        char letra2 = (char) ('a' + Math.abs(gerador.nextInt()) % 26);  

    
        for(int i =0; i<str.length(); i++){
            if(str.charAt(i) == letra1){
                cod += letra2;
            }else{
                cod += str.charAt(i);
            }
        }


        return cod;
    }
    public static void main (String args[]){
		Scanner sc = new Scanner (System.in);
        Q04 quest04 = new Q04();
		String frase = sc.nextLine();
		while(!frase.equals("FIM")){
			System.out.println(quest04.Altera(frase));
			frase = sc.nextLine();
		}
		sc.close();
	}

}