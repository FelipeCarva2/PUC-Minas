import java.util.*;

public class Q02{

    public void Espelho(int inicio, int fim){
        int[] num = new int[100];
        String numStr="";
        for(int i = inicio; i<=fim;i++){
            num[i-inicio] = i;
            numStr += Integer.toString(num[i-inicio]);

        }
        for(int i =0; i< numStr.length();i++){
            System.out.print(numStr.charAt(i));
        }
        for(int i =numStr.length()-1; i>=0;i--){
            System.out.print(numStr.charAt(i));
        }

        

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    Q02 questQ02 = new Q02();
       
        int inicio, fim;
        while(sc.hasNext()){
            inicio = sc.nextInt();
            fim = sc.nextInt();
            questQ02.Espelho(inicio, fim);  
            System.out.println("");


    }


    }
}
