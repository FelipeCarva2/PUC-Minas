// -------------------------
// Guia_0805 - COMPLEMENTO DE 2 6 bits
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
// -------------------------

// -------------------------
// complemento de 2
// -------------------------
module complemento2(output [5:0] resultado,
                   input [5:0] a);
    
    // definir dados locais
    wire [5:0] complemento1;
    wire vaiUm;
    
    // descrever por portas
    not NOT0(complemento1[0], a[0]);
    not NOT1(complemento1[1], a[1]);
    not NOT2(complemento1[2], a[2]);
    not NOT3(complemento1[3], a[3]);
    not NOT4(complemento1[4], a[4]);
    not NOT5(complemento1[5], a[5]);
    
    // Usar o somador6bits do Exercício 1 para adicionar 1
    somador6bits SOMADOR(resultado, vaiUm, complemento1, 6'b000001, 1'b0);
    
endmodule // complemento2

// -------------------------
// modulo de teste
// -------------------------
module teste_complemento2;
    // definir dados
    reg [5:0] a;
    wire [5:0] resultado;
    
    complemento2 C2(resultado, a);
    
    // parte principal
    initial begin : main
        $display("Guia_0805 - Felipe Gabriel de Carvalho - 1543536");
        $display("Teste do Complemento de 2 para 6 bits");
        
        // projetar testes do modulo
        a = 6'b000000;
        #1 $display("Complemento de 2 de %b = %b", a, resultado);
        
        a = 6'b000001;
        #1 $display("Complemento de 2 de %b = %b", a, resultado);
        
        a = 6'b010101;
        #1 $display("Complemento de 2 de %b = %b", a, resultado);
        
        a = 6'b100000;
        #1 $display("Complemento de 2 de %b = %b", a, resultado);
        
        a = 6'b111111;
        #1 $display("Complemento de 2 de %b = %b", a, resultado);
    end
endmodule // teste_complemento2

/*
Para compilar: iverilog -o Guia_0805.vvp Guia_0805.v Guia_0801.v
Para executar: vvp Guia_0805.vvp
*/