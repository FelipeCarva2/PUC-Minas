// ------------------------- 
// Guia_0504.v - GATES 
// Nome: Felipe Gabriel de Carvalho 
// Matrícula: 1543536
// ------------------------- 

// ------------------------- 
//  f5_gate 
//~(a & ~b)
//  m  a  b  s 
//  0  0  0  1 
//  1  0  1  1 
//  2  1  0  0 
//  3  1  1  1 
// ------------------------- 
module f5a (output s, input a, input b);
   wire not_b, a_and_notb;
// Inverter 'b' usando NAND
   nand NAND1 (not_b, b, b); // ~b
// Calcular (a & ~b) 
   nand NAND2 (a_and_notb, a, not_b); // ~(a & ~b)
// A saída 's' é simplesmente o resultado de NAND2
   assign s = a_and_notb; 
endmodule // f5a

module f5b (output s, input a, input b);
assign s = ~(a & ~b); 
endmodule //f5b

module test_f5;

   reg x;
   reg y;
   wire a, b;

   f5a moduloA (a, x, y);
   f5b moduloB (b, x, y);



   initial
   begin : main
      $display("Guia_0504 - Felipe Gabriel de Carvalho - 1543536");
      $display("Test module"); 
          $display("   x     y    a    b"); 

  
      $monitor("%4b %4b %4b %4b", x, y, a, b);
      x = 1'b0; y = 1'b0;
      #1 x = 1'b0; y = 1'b1;
      #1 x = 1'b1; y = 1'b0;
      #1 x = 1'b1; y = 1'b1;
   end

endmodule // test_f5



/*
Para compilar: iverilog -o Guia_0504.vvp Guia_0504.v 
Para executar: vvp Guia_0504.vvp
*/