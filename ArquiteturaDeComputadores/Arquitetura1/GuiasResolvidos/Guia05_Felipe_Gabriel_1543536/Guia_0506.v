// ------------------------- 
// Guia_0506.v 
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// ------------------------- 

// ------------------------- 
//  a  b  s
//  0  0  1
//  0  1  0
//  1  0  0
//  1  1  1
// ------------------------- 

module f5a (output s, 
                  input  a, 
                  input  b); 


   wire nand1_out, nand2_out, nand3_out, nand4_out, nand5_out;


   nand NAND1 (nand1_out, a, a);         // nand1_out = ~a
   nand NAND2 (nand2_out, b, b);         // nand2_out = ~b
   nand NAND3 (nand3_out, a, b);         // nand3_out = ~(a & b)
   nand NAND4 (nand4_out, nand1_out, nand2_out); // nand4_out = ~(~a & ~b)
   nand NAND5 (s, nand3_out, nand4_out); // s = ~((~(a & b)) & (~(~a & ~b)))

endmodule // f5

module f5b (output s, input a, input b);
assign s = (~(a ^ b)); 
endmodule //f5b

module test_f5;


   reg  x;
   reg  y;
   wire a, b;


   f5a moduloA (a, x, y);
   f5b moduloB(b, x, y );

   initial
   begin : main
      $display("Guia_0506 - Felipe gabriel de Carvalho - 1543536");
      $display("Test modulo");
      $display("    x   y   a   b");

      $monitor(" %4b %4b %4b %4b", x, y, a, b);

      // Testes com todas as combinações possíveis
      x = 1'b0; y = 1'b0; #1;
      x = 1'b0; y = 1'b1; #1;
      x = 1'b1; y = 1'b0; #1;
      x = 1'b1; y = 1'b1; #1;
   end

endmodule // test_f5


/*
Para compilar: iverilog -o Guia_0506.vvp Guia_0506.v 
Para executar: vvp Guia_0506.vvp
*/