/* 
  Guia_0202.v 
     Felipe Gabriel de Carvalho - 1543536
*/ 
module Guia_0202; 
// define data 
   real        x = 0.125000; // decimal 
   integer   y = 7  ;    // counter 
   integer i = 0;
   reg [7:0] b = 0  ;   // binary 
 
// actions 
   initial 
    begin : main 
     $display ( "Guia_0202 - Tests" ); 
     $display ( "x = %f" , x ); 
     $display ( "b = 0.%8b", b ); 
     while ( x > 0 && y >= 0 ) 
      begin 
       if ( x*2 >= 1 ) 
        begin 
         b[y] = 1; 
         x = x*2.0 - 1.0; 
        end 
       else 
        begin 
         b[y] = 0; 
         x = x*2.0; 
        end // end if 
       $display ( "b = 0.%8b", b ); 
       y=y-1; 
      end // end while 

    end // main 
 
endmodule // Guia_0202
/*
Para compilar: iverilog -o Guia_0202.vvp Guia_0202.v 
Para executar: vvp Guia_0202.vvp
*/

//Só consegui testa outros valores alterando o x, não consegui em novas linhas fazer outros testes