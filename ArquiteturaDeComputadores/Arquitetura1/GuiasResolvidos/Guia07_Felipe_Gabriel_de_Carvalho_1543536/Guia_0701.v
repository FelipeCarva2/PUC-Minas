// Guia_0701 - LU com operações AND e NAND
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

module LU_AND_NAND(output s, input a, input b, input select);
    wire and_out, nand_out;
    
    and AND1(and_out, a, b);
    nand NAND1(nand_out, a, b);
    
    mux2x1 MUX1(s, and_out, nand_out, select);
endmodule

module mux2x1(output s, input x, input y, input e);
    wire not_e, sx, sy;
    
    not NOT1(not_e, e);
    and AND1(sx, x, not_e);
    and AND2(sy, y, e);
    or OR1(s, sx, sy);
endmodule

module test_LU_AND_NAND;
    reg a, b, select;
    wire s;
    
    LU_AND_NAND LU1(s, a, b, select);
    
    initial begin
        $display("Guia_0701 - Teste LU AND/NAND");
        $display(" a b select s");
        $monitor("%b %b %b %b", a, b, select, s);
        
        // Testar todas as combinações
        #1 a=0; b=0; select=0;
        #1 a=0; b=1; select=0;
        #1 a=1; b=0; select=0;
        #1 a=1; b=1; select=0;
        #1 a=0; b=0; select=1;
        #1 a=0; b=1; select=1;
        #1 a=1; b=0; select=1;
        #1 a=1; b=1; select=1;
        #1 $finish;
    end
endmodule
/*
Para compilar: iverilog -o Guia_0701.vvp Guia_0701.v 
Para executar: vvp Guia_0701.vvp
*/