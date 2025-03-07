import java.util.*;

public class Q07{
//Recebe uma string e preenche outra varrendo a primeira de trás para frente
    public String Inverte(String str){
        String invert="";
        for(int i=str.length()-1; i>=0;i--){
            invert += str.charAt(i); //vai preenchendo a string 
        }
        return invert;
    }
    
    
    public static void main(String args[]){
        Q07 quest7 = new Q07();
        String texto = MyIO.readLine();
  
        while(!texto.equals("FIM")){
            MyIO.println(quest7.Inverte(texto)); //print achamando a função
            texto = MyIO.readLine();  
        }
        }
    
    
    }


