// Guia_0704 - LU com operações OR, NOR, XOR, XNOR
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

module LU_OR_NOR_XOR_XNOR(output s, input a, input b, input [1:0] select);
    // Fios intermediários para todas as operações
    wire or_out, nor_out, xor_out, xnor_out;
    
    // Portas lógicas para cada operação
    or OR1(or_out, a, b);
    nor NOR1(nor_out, a, b);
    xor XOR1(xor_out, a, b);
    xnor XNOR1(xnor_out, a, b);
    
    // Multiplexador 4x1 para seleção
    mux4x1 MUX1(s, xnor_out, xor_out, or_out, nor_out, select);
endmodule

module mux4x1(output s, input w, input x, input y, input z, input [1:0] e);
    // Fios intermediários para o multiplexador
    wire not_e0, not_e1;
    wire and0, and1, and2, and3;
    
    // Lógica do multiplexador 4x1
    not NOT1(not_e0, e[0]);
    not NOT2(not_e1, e[1]);
    
    and AND0(and0, w, not_e1, not_e0);
    and AND1(and1, x, not_e1, e[0]);
    and AND2(and2, y, e[1], not_e0);
    and AND3(and3, z, e[1], e[0]);
    
    or OR1(s, and0, and1, and2, and3);
endmodule

module test_LU_OR_NOR_XOR_XNOR;
    // Variáveis de entrada e saída para teste
    reg a, b;
    reg [1:0] select;
    wire s;
    
    // Instanciação do módulo principal
    LU_OR_NOR_XOR_XNOR LU1(s, a, b, select);
    
    initial begin
        // Cabeçalho do teste
        $display("Guia_0704 - Teste LU OR/NOR/XOR/XNOR");
        $display(" a b select s");
        $monitor("%b %b %b %b", a, b, select, s);
        
        // Teste de todas as seleções possíveis
        #1 a=0; b=0; select=2'b00;  // XNOR
        #1 a=0; b=1; select=2'b01;   // XOR
        #1 a=1; b=0; select=2'b10;   // OR
        #1 a=1; b=1; select=2'b11;   // NOR
        #1 $finish;
    end
endmodule

/*
Para compilar: iverilog -o Guia_0704.vvp Guia_0704.v 
Para executar: vvp Guia_0704.vvp
*/