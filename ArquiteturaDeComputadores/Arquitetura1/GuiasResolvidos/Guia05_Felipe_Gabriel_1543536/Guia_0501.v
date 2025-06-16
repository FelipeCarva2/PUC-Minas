// ------------------------- 
// Guia_0501.v - NOR GATES 
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// ------------------------- 

//  f5_gate
//  m  a  b  s 
//  0  0  0  0 
//  1  0  1  0 
//  2  1  0  1 <- a & ~b 
//  3  1  1  0 

// ------------------------- 
module f5a ( output s, 
                     input  a, 
                     input  b ); 
// Definir dado local
   wire not_b, and_ab; //conectar portas ou expressar sinais que não armazenam valores 
   
// Descrever usando apenas portas NOR 
   nor NOR1 ( not_b, b, b );    // NOT b = b NOR b   ~(b+b)
   nor NOR2 ( and_ab, a, not_b );  // a AND ~b = (a NOR (b NOR b)) NOR (a NOR (b NOR b))  
   assign s = and_ab; // resultado final 
endmodule // f5a

// ------------------------- 
module f5b ( output s, 
                     input  a, 
                     input  b ); 
// Descrever usando expressão equivalente
   assign s = (a & ~b); // mesma expressão, mas não usa apenas NOR
endmodule // f5b 

 
module test_f5; 

       reg  x; 
       reg  y; 
       wire a, b; 
       
       f5a moduloA ( a, x, y ); 
       f5b moduloB ( b, x, y ); 

   initial 
   begin : main 
          $display("Guia_0501 - Felipe Gabriel de Carvalho - 1543536"); 
          $display("Test module"); 
          $display("   x     y    a    b"); 
       
       
         $monitor("%4b %4b %4b %4b", x, y, a, b); 
             x = 1'b0;  y = 1'b0; 
   #1      x = 1'b0;  y = 1'b1; 
   #1      x = 1'b1;  y = 1'b0; 
   #1      x = 1'b1;  y = 1'b1; 
   end 
endmodule 

/*
Para compilar: iverilog -o Guia_0501.vvp Guia_0501.v 
Para executar: vvp Guia_0501.vvp
*/