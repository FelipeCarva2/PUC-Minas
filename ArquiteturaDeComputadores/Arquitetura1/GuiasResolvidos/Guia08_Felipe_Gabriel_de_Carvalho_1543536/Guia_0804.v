// -------------------------
// Guia_0804 - COMPARADOR DE DESIGUALDADE 6 bits
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// -------------------------

// -------------------------
// comparador de desigualdade
// -------------------------
module comparadorDesigualdade(output diferente,
                             input [5:0] a,
                             input [5:0] b);
    
    // definir dados locais
    wire [5:0] xorSaida;
    
    // descrever por portas
    xor XOR0(xorSaida[0], a[0], b[0]);
    xor XOR1(xorSaida[1], a[1], b[1]);
    xor XOR2(xorSaida[2], a[2], b[2]);
    xor XOR3(xorSaida[3], a[3], b[3]);
    xor XOR4(xorSaida[4], a[4], b[4]);
    xor XOR5(xorSaida[5], a[5], b[5]);
    
    or OR0(diferente, xorSaida[0], xorSaida[1], xorSaida[2], 
           xorSaida[3], xorSaida[4], xorSaida[5]);
    
endmodule // comparadorDesigualdade

// -------------------------
// modulo de teste
// -------------------------
module teste_comparadorDesigualdade;
    // definir dados
    reg [5:0] a;
    reg [5:0] b;
    wire diferente;
    
    comparadorDesigualdade COMP(diferente, a, b);
    
    // parte principal
    initial begin : main
        $display("Guia_0804 - Felipe Gabriel de Carvalho - 1543536");
        $display("Teste do Comparador de Desigualdade 6 bits");
        
        // projetar testes do modulo
        a = 6'b000000; b = 6'b000000;
        #1 $display("%b != %b? %b", a, b, diferente);
        
        a = 6'b000001; b = 6'b000000;
        #1 $display("%b != %b? %b", a, b, diferente);
        
        a = 6'b010101; b = 6'b010101;
        #1 $display("%b != %b? %b", a, b, diferente);
        
        a = 6'b101010; b = 6'b010101;
        #1 $display("%b != %b? %b", a, b, diferente);
        
        a = 6'b111111; b = 6'b111111;
        #1 $display("%b != %b? %b", a, b, diferente);
    end
endmodule // teste_comparadorDesigualdade

/*
Para compilar: iverilog -o Guia_0804.vvp Guia_0804.v 
Para executar: vvp Guia_0804.vvp
*/