// Guia_0703 - LU com operações AND, NAND, OR, NOR
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

// Módulo do multiplexador 2x1 (necessário para a solução)
module mux2x1(output s, input x, input y, input e);
    wire not_e, sx, sy;
    
    not NOT1(not_e, e);
    and AND1(sx, x, not_e);
    and AND2(sy, y, e);
    or OR1(s, sx, sy);
endmodule

// Módulo principal da LU
module LU_AND_NAND_OR_NOR(output s, input a, input b, input select_op, input select_group);
    // Fios intermediários para todas as operações
    wire and_out, nand_out, or_out, nor_out;
    wire group1_out, group2_out;
    
    // Grupo AND/NAND
    and AND1(and_out, a, b);
    nand NAND1(nand_out, a, b);
    mux2x1 MUX1(group1_out, and_out, nand_out, select_op);
    
    // Grupo OR/NOR
    or OR1(or_out, a, b);
    nor NOR1(nor_out, a, b);
    mux2x1 MUX2(group2_out, or_out, nor_out, select_op);
    
    // Seleção final entre grupos
    mux2x1 MUX3(s, group2_out, group1_out, select_group);
endmodule

module test_LU_AND_NAND_OR_NOR;
    // Variáveis de entrada e saída para teste
    reg a, b, select_op, select_group;
    wire s;
    
    // Instanciação do módulo principal
    LU_AND_NAND_OR_NOR LU1(s, a, b, select_op, select_group);
    
    initial begin
        // Cabeçalho do teste
        $display("Guia_0703 - Teste LU AND/NAND/OR/NOR");
        $display(" a b sel_op sel_grp s");
        $monitor("%b %b %b %b %b", a, b, select_op, select_group, s);
        
        // Teste das combinações mais relevantes
        #1 a=0; b=0; select_op=0; select_group=0;  // OR
        #1 a=0; b=1; select_op=1; select_group=0;  // NOR
        #1 a=1; b=0; select_op=0; select_group=1;  // AND
        #1 a=1; b=1; select_op=1; select_group=1;  // NAND
        #1 $finish;
    end
endmodule

/*
Para compilar: iverilog -o Guia_0703.vvp Guia_0703.v 
Para executar: vvp Guia_0703.vvp
*/