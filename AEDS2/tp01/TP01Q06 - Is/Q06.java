import java.util.*;



public class Q06{

    
//Compara a string se é igual a vogais
public boolean Vogal( String str){
    boolean ver=false;
    String texto = str.toUpperCase();
    for(int i=0; i< str.length();i++){
        if(texto.charAt(i)=='A'||texto.charAt(i)=='E'||texto.charAt(i)=='I'||texto.charAt(i)=='O'||texto.charAt(i)=='U'){
            ver = true;
        }else{
            return false;
        }
    }

    return ver;
}
//Compara a string se é igual a consoantes, usa os intervalo das letars maiusculas da tabela ASCII ignorando as vogais
public boolean Consoante( String str){
    boolean ver=false;
    String texto = str.toUpperCase();
    
    for(int i=0; i< str.length();i++){
        if((texto.charAt(i)!='A'&&texto.charAt(i)!='E'&&texto.charAt(i)!='I'&&texto.charAt(i)!='O'&&texto.charAt(i)!='U')&&((int) texto.charAt(i)>=65&&(int) texto.charAt(i)<=90)){
            ver = true;
        }else{
            return false;
        }
    }

    return ver;
}
//Compara a string se é igual a inteiro, vendo se os characteres da string estão dentro da faixa de valores de 0 a 9
public boolean Inteiro(String str){
    boolean ver=false;
    for(int i=0; i< str.length();i++){
        if((int) str.charAt(i)>=48 && (int) str.charAt(i)<=57){
            ver = true;
        }else{
            return false;
        }
    }
    return ver;
}

//Compara a string se é igual a real, vendo se os characteres da string estão dentro da faixa de valores de 0 a 9 ou se o character é . ou ,
public boolean Real(String str){
    boolean ver=true;
    int cont =0;
    for(int i=0; i< str.length();i++){
        if(str.charAt(i)=='.'|| str.charAt(i)==',' ){
            cont = cont +1;
        }
        if((((int) (str.charAt(i))>=48) && ((int) str.charAt(i))<=57)||(( str.charAt(i)=='.'||str.charAt(i)==',')&&cont<2)){
            ver = true;
        }else{
            return false;
        }

    }
    return ver;
}




    public static void main(String args[]){
        Q06 quest6 = new Q06();
        
        String texto = MyIO.readLine(); 
        
        while (!texto.equals("FIM")) {
           
            //testa vogal
            if(quest6.Vogal(texto)== true){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }

            //testa consoante
            if(quest6.Consoante(texto)== true){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }

            //testa inteiro
            if(quest6.Inteiro(texto)== true){
                System.out.print("SIM ");
            }else{
                System.out.print("NAO ");
            }

            //testa real
            if(quest6.Real(texto)== true){
                System.out.print("SIM");
            }else{
                System.out.print("NAO");
            }
            System.out.println();
            
            //Verifica se todas as saidas são falsas e se for printa mais um "NÃO"
           /* if(quest6.Consoante(texto)==false&&quest6.Real(texto)== false && quest6.Inteiro(texto)== false&&quest6.Vogal(texto)== false){
                System.out.println("NÃO");
            }else{
                System.out.println();
            }*/ 

            texto = MyIO.readLine();  
        }
     
    }
}






