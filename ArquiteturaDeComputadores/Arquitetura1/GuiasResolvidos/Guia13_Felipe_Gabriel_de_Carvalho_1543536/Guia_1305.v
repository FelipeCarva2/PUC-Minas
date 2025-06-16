// Guia_1305.v - Contador síncrono módulo 7 com flip-flops T
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536

`timescale 1ns/1ps

module tff(
    output reg q,
    output qnot,
    input t,
    input clk,
    input preset,
    input clear
);
    assign qnot = ~q;
    
    always @(posedge clk or posedge clear or posedge preset) begin
        if (clear) begin
            q <= 1'b0;
        end else if (preset) begin
            q <= 1'b1;
        end else if (t) begin
            q <= ~q;
        end
    end
endmodule

module contador_mod7(
    output [2:0] out,
    input clk,
    input clear
);
    wire [2:0] qnot;
    wire [2:0] t;
    wire reset;
    
    // Detecta 6 (110) para resetar para 0
    assign reset = out[2] & out[1] & ~out[0];
    
    // Lógica de entrada T para cada flip-flop
    assign t[0] = 1'b1; // Sempre alterna
    assign t[1] = out[0];
    assign t[2] = out[0] & out[1];
    
    tff ff0(out[0], qnot[0], t[0], clk, 1'b0, clear | reset);
    tff ff1(out[1], qnot[1], t[1], clk, 1'b0, clear | reset);
    tff ff2(out[2], qnot[2], t[2], clk, 1'b0, clear | reset);
endmodule

// Testbench
module test_contador_mod7;
    reg clk, clear;
    wire [2:0] out;
    
    contador_mod7 uut(out, clk, clear);
    
    initial begin
        // Inicializa entradas
        clk = 0;
        clear = 1;
        
        // Gera arquivo VCD para visualização
        $dumpfile("contador_mod7.vcd");
        $dumpvars(0, test_contador_mod7);
        
        // Aplica clear
        #10 clear = 0;
        
        // Simula por 200ns
        #200 $finish;
    end
    
    // Gera clock com período de 10ns
    always #5 clk = ~clk;
    
    // Monitora as saídas
    initial begin
        $monitor("Tempo: %t, Contador: %b (%d)", $time, out, out);
    end
endmodule