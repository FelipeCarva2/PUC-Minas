// ------------------------- 
// Guia_0503.v - GATES 
// Nome: Felipe Gabreil de Carvalho 
// Matrícula: 1543536
// ------------------------- 

// ------------------------- 
//  f5_gate 
// ~(a |~b)
//  m  a  b  s 
//  0  0  0  0 
//  1  0  1  1 
//  2  1  0  0 
//  3  1  1  0 
// ------------------------- 
module f5a (output s, input a, input b);
   wire not_b;

// Inverter 'b' usando NOR
   nor NOR1 (not_b, b, b); 
   nor NOR2 (s, a, not_b); // NOR entre 'a' e '~b' já representa a expressão ~(a | ~b)

endmodule // f5
module f5b (output s, input a, input b);
assign s = ~(a | ~b); 
endmodule //f5b

module test_f5;

   reg x;
   reg y;
   wire a, b;

   f5a moduloA (a, x, y);
   f5b moduloB (b, x, y);



   initial
   begin : main
      $display("Guia_0503 - Felipe Gabriel de Carvalho - 1543536");
      $display("Test module for ~(a | ~b)");
      $display("   x     y     a    b");

      
      $monitor("%4b %4b %4b %4b", x, y, a, b);
      x = 1'b0; y = 1'b0;
      #1 x = 1'b0; y = 1'b1;
      #1 x = 1'b1; y = 1'b0;
      #1 x = 1'b1; y = 1'b1;
   end

endmodule // test_f5



/*
Para compilar: iverilog -o Guia_0503.vvp Guia_0503.v 
Para executar: vvp Guia_0503.vvp
*/