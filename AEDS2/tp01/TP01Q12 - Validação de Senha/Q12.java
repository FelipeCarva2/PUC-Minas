import java.util.*;

public class Q12{

    //Faz um teste para cada requito da senha, se todos os requisitos forem verdadeiros a senha é valida, olha as condições pela tabela ASCII
 public boolean testeSenha(String senha){
    boolean teste = false;
    boolean hasEspecial=false;
    boolean hasNumber=false;
    boolean hasCharUpper=false;
    boolean hasCharLower=false;
    //testa se tem no minimo 8 caracteres
   if(senha.length()>=8){
    for(int i=0; i< senha.length();i++){
        //testa se tem char especiais
        if(((int) senha.charAt(i)>=32 && (int) senha.charAt(i)<=47)||((int) senha.charAt(i)>=58 && (int) senha.charAt(i)<=64)||((int) senha.charAt(i)>=91 && (int) senha.charAt(i)<=96)||((int) senha.charAt(i)>=123&& (int) senha.charAt(i)<=126)){
            hasEspecial=true;
        }
        //testa se tem maiusculas
        if((int) senha.charAt(i)>=65 && (int) senha.charAt(i)<=90){
            hasCharUpper=true;
        }
         //testa se tem minusculas
         if((int) senha.charAt(i)>=97 && (int) senha.charAt(i)<=122){
            hasCharLower=true;
        }
         //testa se tem numeros
         if((int) senha.charAt(i)>=48 && (int) senha.charAt(i)<=57){
            hasNumber=true;
        }

    }
    //Testa se tem o que é necessario para ser uma senha
    if(hasEspecial==true && hasCharUpper==true && hasCharLower==true && hasNumber==true){
        teste=true;
    }
   }
    
    return teste;
 }




public static void main (String args []){
    Q12 quest12 = new Q12();
    Scanner sc = new Scanner (System.in);
    String senha = sc.nextLine();

    while (!senha.equals ("FIM")){
         if(quest12.testeSenha(senha)){
            System.out.println("SIM");
         }else{
            System.out.println("NAO");
         }
        senha = sc.nextLine();
    }
    sc.close();
}

}