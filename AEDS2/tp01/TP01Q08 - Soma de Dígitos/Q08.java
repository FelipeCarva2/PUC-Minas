import java.util.*;
public class Q08{

    //metódo recursivo para somar os algarismos de um numero inteiro, funciona por um loop que em cada cilclo soma o resto da divisão por 10 ate o quociente ser 0
    public int somaDigitos(int num){

        if(num==0){
            return 0;
        }else{
            return num%10 + somaDigitos(num/10);
        }


    }

    public static void main (String args []){
        Q08 quest8 = new Q08();
	Scanner scanner = new Scanner (System.in);
		int num = scanner.nextInt();
		while (num >= 0){
			try{
				System.out.println (quest8.somaDigitos(num));
				num = scanner.nextInt();
			}catch(InputMismatchException e){
				break;
		}
	}
	scanner.close();
    }




}