// -------------------------
// Guia_0801 - SOMADOR COMPLETO 6 bits
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// -------------------------

// -------------------------
// meio somador
// -------------------------
module meioSomador(output vaiUm, 
                  output soma, 
                  input a, 
                  input b);
    
    // descrever por portas
    xor XOR1(soma, a, b);
    and AND1(vaiUm, a, b);
    
endmodule // meioSomador

// -------------------------
// somador completo
// -------------------------
module somadorCompleto(output vaiUmSaida,
                      output soma,
                      input a,
                      input b,
                      input vaiUmEntrada);
    
    // definir dados locais
    wire somaParcial;
    wire vaiUmParcial1;
    wire vaiUmParcial2;
    
    // descrever por portas e/ou modulos
    meioSomador MS1(vaiUmParcial1, somaParcial, a, b);
    meioSomador MS2(vaiUmParcial2, soma, somaParcial, vaiUmEntrada);
    or OR1(vaiUmSaida, vaiUmParcial1, vaiUmParcial2);
    
endmodule // somadorCompleto

// -------------------------
// somador de 6 bits
// -------------------------
module somador6bits(output [5:0] soma,
                   output vaiUmFinal,
                   input [5:0] a,
                   input [5:0] b,
                   input vaiUmInicial);
    
    // definir dados locais
    wire [5:0] vaiUm;
    
    // descrever por portas e/ou modulos
    somadorCompleto SC0(vaiUm[0], soma[0], a[0], b[0], vaiUmInicial);
    somadorCompleto SC1(vaiUm[1], soma[1], a[1], b[1], vaiUm[0]);
    somadorCompleto SC2(vaiUm[2], soma[2], a[2], b[2], vaiUm[1]);
    somadorCompleto SC3(vaiUm[3], soma[3], a[3], b[3], vaiUm[2]);
    somadorCompleto SC4(vaiUm[4], soma[4], a[4], b[4], vaiUm[3]);
    somadorCompleto SC5(vaiUmFinal, soma[5], a[5], b[5], vaiUm[4]);
    
endmodule // somador6bits

// -------------------------
// modulo de teste
// -------------------------
module teste_somador6bits;
    // definir dados
    reg [5:0] a;
    reg [5:0] b;
    wire [5:0] soma;
    wire vaiUmFinal;
    
    somador6bits SOMADOR(soma, vaiUmFinal, a, b, 1'b0);
    
    // parte principal
    initial begin : main
        $display("Guia_0801 - Felipe Gabriel de Carvalho - 1543536");
        $display("Teste do Somador de 6 bits");
        
        // projetar testes do modulo
        a = 6'b000000; b = 6'b000000;
        #1 $display("%b + %b = %b carryOut=%b", a, b, soma, vaiUmFinal);
        
        a = 6'b000001; b = 6'b000001;
        #1 $display("%b + %b = %b carryOut=%b", a, b, soma, vaiUmFinal);
        
        a = 6'b010101; b = 6'b001010;
        #1 $display("%b + %b = %b carryOut=%b", a, b, soma, vaiUmFinal);
        
        a = 6'b111111; b = 6'b000001;
        #1 $display("%b + %b = %b carryOut=%b", a, b, soma, vaiUmFinal);
        
        a = 6'b111111; b = 6'b111111;
        #1 $display("%b + %b = %b carryOut=%b", a, b, soma, vaiUmFinal);
    end
endmodule // teste_somador6bits

/*
Para compilar: iverilog -o Guia_0801.vvp Guia_0801.v 
Para executar: vvp Guia_0801.vvp
*/