// -------------------------
// Guia_0802 - SUBTRATOR COMPLETO 6 bits
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// -------------------------

// -------------------------
// meio subtrator
// -------------------------
module meioSubtrator(output empresta,
                    output diferenca,
                    input a,
                    input b);
    
    // descrever por portas
    xor XOR1(diferenca, a, b);
    and AND1(empresta, ~a, b);
    
endmodule // meioSubtrator

// -------------------------
// subtrator completo
// -------------------------
module subtratorCompleto(output emprestaSaida,
                        output diferenca,
                        input a,
                        input b,
                        input emprestaEntrada);
    
    // definir dados locais
    wire diferencaParcial;
    wire emprestaParcial1;
    wire emprestaParcial2;
    
    // descrever por portas e/ou modulos
    meioSubtrator MS1(emprestaParcial1, diferencaParcial, a, b);
    meioSubtrator MS2(emprestaParcial2, diferenca, diferencaParcial, emprestaEntrada);
    or OR1(emprestaSaida, emprestaParcial1, emprestaParcial2);
    
endmodule // subtratorCompleto

// -------------------------
// subtrator de 6 bits
// -------------------------
module subtrator6bits(output [5:0] diferenca,
                     output emprestaFinal,
                     input [5:0] a,
                     input [5:0] b,
                     input emprestaInicial);
    
    // definir dados locais
    wire [5:0] empresta;
    
    // descrever por portas e/ou modulos
    subtratorCompleto SUB0(empresta[0], diferenca[0], a[0], b[0], emprestaInicial);
    subtratorCompleto SUB1(empresta[1], diferenca[1], a[1], b[1], empresta[0]);
    subtratorCompleto SUB2(empresta[2], diferenca[2], a[2], b[2], empresta[1]);
    subtratorCompleto SUB3(empresta[3], diferenca[3], a[3], b[3], empresta[2]);
    subtratorCompleto SUB4(empresta[4], diferenca[4], a[4], b[4], empresta[3]);
    subtratorCompleto SUB5(emprestaFinal, diferenca[5], a[5], b[5], empresta[4]);
    
endmodule // subtrator6bits

// -------------------------
// modulo de teste
// -------------------------
module teste_subtrator6bits;
    // definir dados
    reg [5:0] a;
    reg [5:0] b;
    wire [5:0] diferenca;
    wire emprestaFinal;
    
    subtrator6bits SUBTRATOR(diferenca, emprestaFinal, a, b, 1'b0);
    
    // parte principal
    initial begin : main
        $display("Guia_0802 - Felipe Gabriel de Carvalho - 1543536");
        $display("Teste do Subtrator de 6 bits");
        
        // projetar testes do modulo
        a = 6'b000000; b = 6'b000000;
        #1 $display("%b - %b = %b empresta=%b", a, b, diferenca, emprestaFinal);
        
        a = 6'b000001; b = 6'b000001;
        #1 $display("%b - %b = %b empresta=%b", a, b, diferenca, emprestaFinal);
        
        a = 6'b010101; b = 6'b001010;
        #1 $display("%b - %b = %b empresta=%b", a, b, diferenca, emprestaFinal);
        
        a = 6'b000001; b = 6'b000010;
        #1 $display("%b - %b = %b empresta=%b", a, b, diferenca, emprestaFinal);
        
        a = 6'b111111; b = 6'b111111;
        #1 $display("%b - %b = %b empresta=%b", a, b, diferenca, emprestaFinal);
    end
endmodule // teste_subtrator6bits

/*
Para compilar: iverilog -o Guia_0802.vvp Guia_0802.v 
Para executar: vvp Guia_0802.vvp
*/