import java.util.*;



public class Q20{

    
//Compara a string se é igual a vogais
public boolean Vogal( String texto, int i){
     texto = texto.toUpperCase();

    if(texto.length()== i){
        return true;
    }else{

        if(texto.charAt(i)=='A'||texto.charAt(i)=='E'||texto.charAt(i)=='I'||texto.charAt(i)=='O'||texto.charAt(i)=='U'){
          return Vogal(texto, i+1);
        }else{

            return false;
        }
    }


}

   

//Compara a string se é igual a consoantes, usa os intervalo das letars maiusculas da tabela ASCII ignorando as vogais
public boolean Consoante( String str, int i){
   
    String texto = str.toUpperCase();
    
    if(texto.length()<= i){
        return true;
    }else{
        
        if((texto.charAt(i)!='A'&&texto.charAt(i)!='E'&&texto.charAt(i)!='I'&&texto.charAt(i)!='O'&&texto.charAt(i)!='U')&&((int) texto.charAt(i)>=65&&(int) texto.charAt(i)<=90)){
           return Consoante(texto, i+1);
        }else{

            return false;
        }
    }
    
}
//Compara a string se é igual a inteiro, vendo se os characteres da string estão dentro da faixa de valores de 0 a 9
public boolean Inteiro(String str, int i){

    if(str.length()==i){
        return true;
    }else{
        if((int) str.charAt(i)>=48 && (int) str.charAt(i)<=57){
          return Inteiro(str, i+1);
        }else{
            return false;
        }
    }
   
    }
    


//Compara a string se é igual a real, vendo se os characteres da string estão dentro da faixa de valores de 0 a 9 ou se o character é . ou ,
public boolean Real(String str, int i, int cont){
    boolean ver=true;

    if(str.length()==i){
        return true;
    }else{
        if(str.charAt(i)=='.'|| str.charAt(i)==',' ){
            cont = cont +1;
        }
        if((((int) (str.charAt(i))>=48) && ((int) str.charAt(i))<=57)||(( str.charAt(i)=='.'||str.charAt(i)==',')&&cont<2)){
            return Real(str, i+1, cont);
        }else{
            return false;
        }
        
        
    }
        

    }
 





    public static void main(String args[]){
        Q20 quest20 = new Q20();
        
        String texto = MyIO.readLine(); 
        int i=0;
        while (!texto.equals("FIM")) {
           
            //testa vogal
            if(quest20.Vogal(texto, i)== true){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }

            //testa consoante
            if(quest20.Consoante(texto,i)== true){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }

            //testa inteiro
            if(quest20.Inteiro(texto,i)== true){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }

            //testa real
            if(quest20.Real(texto,i,i)== true){
                System.out.print("SIM");
            }else{
                System.out.print("NAO");
            }
            System.out.println();
            
           

            texto = MyIO.readLine();  
        }
     
    }
}






