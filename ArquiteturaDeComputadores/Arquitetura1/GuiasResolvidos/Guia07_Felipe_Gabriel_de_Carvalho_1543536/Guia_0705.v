// Guia_0705 - LU com todas as operações lógicas
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

module LU_COMPLETA(output s, input a, input b, input [2:0] select);
    // Fios intermediários para todas as operações
    wire not_out, and_out, nand_out, or_out, nor_out, xor_out, xnor_out;
    
    // Portas lógicas para cada operação
    not NOT1(not_out, a);            // NOT (usa apenas a entrada 'a')
    and AND1(and_out, a, b);
    nand NAND1(nand_out, a, b);
    or OR1(or_out, a, b);
    nor NOR1(nor_out, a, b);
    xor XOR1(xor_out, a, b);
    xnor XNOR1(xnor_out, a, b);
    
    // Multiplexador 8x1 para seleção
    mux8x1 MUX1(s, 1'b0, xnor_out, xor_out, nor_out, or_out, nand_out, and_out, not_out, select);
endmodule

module mux8x1(output s, input w0, input w1, input w2, input w3, input w4, input w5, input w6, input w7, input [2:0] e);
    // Fios intermediários para o multiplexador
    wire [7:0] and_out;
    
    // Lógica do multiplexador 8x1
    and AND0(and_out[0], w0, ~e[2], ~e[1], ~e[0]);
    and AND1(and_out[1], w1, ~e[2], ~e[1], e[0]);
    and AND2(and_out[2], w2, ~e[2], e[1], ~e[0]);
    and AND3(and_out[3], w3, ~e[2], e[1], e[0]);
    and AND4(and_out[4], w4, e[2], ~e[1], ~e[0]);
    and AND5(and_out[5], w5, e[2], ~e[1], e[0]);
    and AND6(and_out[6], w6, e[2], e[1], ~e[0]);
    and AND7(and_out[7], w7, e[2], e[1], e[0]);
    
    or OR1(s, and_out[0], and_out[1], and_out[2], and_out[3], 
            and_out[4], and_out[5], and_out[6], and_out[7]);
endmodule

module test_LU_COMPLETA;
    // Variáveis de entrada e saída para teste
    reg a, b;
    reg [2:0] select;
    wire s;
    
    // Instanciação do módulo principal
    LU_COMPLETA LU1(s, a, b, select);
    
    initial begin
        // Cabeçalho do teste
        $display("Guia_0705 - Teste LU Completa");
        $display(" a b select s");
        $monitor("%b %b %b %b", a, b, select, s);
        
        // Teste das principais operações
        #1 a=0; b=1; select=3'b000;  // NOT (resultado de ~a)
        #1 a=1; b=1; select=3'b001;   // AND
        #1 a=1; b=0; select=3'b010;    // NAND
        #1 a=0; b=1; select=3'b011;    // OR
        #1 a=1; b=1; select=3'b100;    // NOR
        #1 a=1; b=0; select=3'b101;    // XOR
        #1 a=0; b=0; select=3'b110;    // XNOR
        #1 $finish;
    end
endmodule

/*
Para compilar: iverilog -o Guia_0705.vvp Guia_0705.v 
Para executar: vvp Guia_0705.vvp
*/