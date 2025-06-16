// Guia_0702 - LU com operações OR e NOR
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

// Módulo do multiplexador 2x1 (adicionado para resolver o erro)
module mux2x1(output s, input x, input y, input e);
    wire not_e, sx, sy;
    
    not NOT1(not_e, e);
    and AND1(sx, x, not_e);
    and AND2(sy, y, e);
    or OR1(s, sx, sy);
endmodule

// Módulo principal da LU OR/NOR
module LU_OR_NOR(output s, input a, input b, input select);
    // Fios intermediários para as operações
    wire or_out, nor_out;
    
    // Portas lógicas para OR e NOR
    or OR1(or_out, a, b);
    nor NOR1(nor_out, a, b);
    
    // Multiplexador para seleção da saída
    mux2x1 MUX1(s, or_out, nor_out, select);
endmodule

module test_LU_OR_NOR;
    // Variáveis de entrada e saída para teste
    reg a, b, select;
    wire s;
    
    // Instanciação do módulo principal
    LU_OR_NOR LU1(s, a, b, select);
    
    initial begin
        // Cabeçalho do teste
        $display("Guia_0702 - Teste LU OR/NOR");
        $display(" a b select s");
        $monitor("%b %b %b %b", a, b, select, s);
        
        // Teste de todas as combinações possíveis
        #1 a=0; b=0; select=0;  // OR: 0 OR 0
        #1 a=0; b=1; select=0;   // OR: 0 OR 1
        #1 a=1; b=0; select=0;   // OR: 1 OR 0
        #1 a=1; b=1; select=0;   // OR: 1 OR 1
        #1 a=0; b=0; select=1;   // NOR: 0 NOR 0
        #1 a=0; b=1; select=1;   // NOR: 0 NOR 1
        #1 a=1; b=0; select=1;   // NOR: 1 NOR 0
        #1 a=1; b=1; select=1;   // NOR: 1 NOR 1
        #1 $finish;
    end
endmodule

/*
Para compilar: iverilog -o Guia_0702.vvp Guia_0702.v 
Para executar: vvp Guia_0702.vvp
*/