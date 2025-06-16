#include<stdio.h>
int somaDigitos(int num){

    if(num==0){
        return 0;
    }else{
        return num%10 + somaDigitos(num/10);
    }


}
int main(){
    
    int num=0;
    int numeroConvertido;
    
    while(1==scanf ("%d", &num)){
        //numeroConvertido = atoi(numero);
        printf("%d\n", somaDigitos(num));
      
    }
    return 0;
}