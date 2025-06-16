// -------------------------
// Guia_0803 - COMPARADOR DE IGUALDADE 6 bits
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// -------------------------

// -------------------------
// comparador de igualdade
// -------------------------
module comparadorIgualdade(output igual,
                          input [5:0] a,
                          input [5:0] b);
    
    // definir dados locais
    wire [5:0] xnorSaida;
    
    // descrever por portas
    xnor XNOR0(xnorSaida[0], a[0], b[0]);
    xnor XNOR1(xnorSaida[1], a[1], b[1]);
    xnor XNOR2(xnorSaida[2], a[2], b[2]);
    xnor XNOR3(xnorSaida[3], a[3], b[3]);
    xnor XNOR4(xnorSaida[4], a[4], b[4]);
    xnor XNOR5(xnorSaida[5], a[5], b[5]);
    
    and AND0(igual, xnorSaida[0], xnorSaida[1], xnorSaida[2], 
             xnorSaida[3], xnorSaida[4], xnorSaida[5]);
    
endmodule // comparadorIgualdade

// -------------------------
// modulo de teste
// -------------------------
module teste_comparadorIgualdade;
    // definir dados
    reg [5:0] a;
    reg [5:0] b;
    wire igual;
    
    comparadorIgualdade COMP(igual, a, b);
    
    // parte principal
    initial begin : main
        $display("Guia_0803 - Felipe Gabriel de Carvalho - 1543536");
        $display("Teste do Comparador de Igualdade 6 bits");
        
        // projetar testes do modulo
        a = 6'b000000; b = 6'b000000;
        #1 $display("%b == %b? %b", a, b, igual);
        
        a = 6'b000001; b = 6'b000000;
        #1 $display("%b == %b? %b", a, b, igual);
        
        a = 6'b010101; b = 6'b010101;
        #1 $display("%b == %b? %b", a, b, igual);
        
        a = 6'b101010; b = 6'b010101;
        #1 $display("%b == %b? %b", a, b, igual);
        
        a = 6'b111111; b = 6'b111111;
        #1 $display("%b == %b? %b", a, b, igual);
    end
endmodule // teste_comparadorIgualdade

/*
Para compilar: iverilog -o Guia_0803.vvp Guia_0803.v 
Para executar: vvp Guia_0803.vvp
*/