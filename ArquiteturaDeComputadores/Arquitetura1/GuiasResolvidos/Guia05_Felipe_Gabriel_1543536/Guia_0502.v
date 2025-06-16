// ------------------------- 
// Guia_0502.v - GATES 
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// ------------------------- 

// ------------------------- 
//  f5_gate 
//  m  a  b  s 
//  0  0  0  1 <- (~a | b)
//  1  0  1  1 <- (~a | b)
//  2  1  0  0  
//  3  1  1  1 <- (~a | b)
// ------------------------- 
module f5a (output s, input a, input b);

   wire not_a, not_a_or_b, nand1, nand2;


   nand NAND1 (not_a, a, a);

// Implementar (~a | b) = ~(a & ~b)
   nand NAND2 (nand1, not_a, not_a); // not_a NAND not_a = a
   nand NAND3 (nand2, b, b);       // b NAND b = ~b
   nand NAND4 (s, nand1, nand2);   // (a) NAND (~b) = ~ (a & ~b)

endmodule // f5a

module f5b (output s, input a, input b);

assign s = ~a | b; 
endmodule //f5b

module test_f5;

   reg x;
   reg y;
   wire a, b;

   f5a moduloA (a, x, y);
   f5b moduloB (b, x, y);



   initial
   begin : main
      $display("Guia_0502 - Felipe Gabriel de Carvalho - 1543536");
      $display("Test module for (~a | b)");
      $display("   x     y     a    b");

      // Testar todas as combinações possíveis
      $monitor("%4b %4b %4b %4b", x, y, a, b);
      x = 1'b0; y = 1'b0;
      #1 x = 1'b0; y = 1'b1;
      #1 x = 1'b1; y = 1'b0;
      #1 x = 1'b1; y = 1'b1;
   end

endmodule // test_f5

/*
Para compilar: iverilog -o Guia_0502.vvp Guia_0502.v 
Para executar: vvp Guia_0502.vvp
*/
