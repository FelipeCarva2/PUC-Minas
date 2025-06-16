// ------------------------- 
// Guia_0505.v - Gate disjunção exclusiva
// Nome: Felipe Gabriel de Carvalho 
// Matricula: 1543536
// ------------------------- 
//  m  a  b  s
//  0  0  0  0
//  1  0  1  1
//  2  1  0  1
//  3  1  1  0
// ------------------------- 
module f5a (output s, 
                 input  a, 
                 input  b); 


   wire nor1_out, nor2_out, nor3_out, nor4_out, nor5_out;

// Descrever por portas NOR
   nor NOR1 (nor1_out, a, b);         //  = ~(a | b)
   nor NOR2 (nor2_out, a, nor1_out);  // = ~(a | ~(a | b)) = a AND ~b
   nor NOR3 (nor3_out, b, nor1_out);  //  = ~(b | ~(a | b)) = b AND ~a
   nor NOR4 (nor4_out, nor2_out, nor3_out); //  = ~( (a AND ~b) | (b AND ~a) ) = ~(a XOR b)
   nor NOR5 (s, nor4_out, nor4_out);  

endmodule
// f5a
module f5b (output s, input a, input b);
assign s = (a ^ b); 
endmodule //f5b

module test_f5;

   reg  x;
   reg  y;
   wire a, b;

   f5a moduloA ( a, x, y );
   f5b moduloB (b, x, y);

   initial
   begin : main
      $display("Guia_0505 - Felipe Gabriel de Carvalho  - 1543536");
      $display("Test modulos");
      $display("   x     y    a     b");

      $monitor("%4b %4b %4b %4b", x, y, a, b);

  
      x = 1'b0; y = 1'b0;
   #1 x = 1'b0; y = 1'b1;
   #1 x = 1'b1; y = 1'b0;
   #1 x = 1'b1; y = 1'b1;
   end

endmodule // test_f5


/*
Para compilar: iverilog -o Guia_0505.vvp Guia_0505.v 
Para executar: vvp Guia_0505.vvp
*/