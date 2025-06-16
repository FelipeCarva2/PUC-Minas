// Guia_1301.v - Contador assíncrono decrescente de 6 bits com flip-flops JK
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536

`timescale 1ns/1ps

module jkff(
    output reg q,
    output qnot,
    input j,
    input k,
    input clk,
    input preset,
    input clear
);
    assign qnot = ~q;
    
    always @(negedge clk or posedge clear or posedge preset) begin
        if (clear) begin
            q <= 1'b0;
        end else if (preset) begin
            q <= 1'b1;
        end else begin
            case ({j,k})
                2'b00: q <= q;
                2'b01: q <= 1'b0;
                2'b10: q <= 1'b1;
                2'b11: q <= ~q;
            endcase
        end
    end
endmodule

module contador_decrescente_6bits(
    output [5:0] out,
    input clk,
    input clear
);
    wire [5:0] qnot;
    
    jkff ff0(out[0], qnot[0], 1'b1, 1'b1, clk, 1'b0, clear);
    jkff ff1(out[1], qnot[1], 1'b1, 1'b1, qnot[0], 1'b0, clear);
    jkff ff2(out[2], qnot[2], 1'b1, 1'b1, qnot[1], 1'b0, clear);
    jkff ff3(out[3], qnot[3], 1'b1, 1'b1, qnot[2], 1'b0, clear);
    jkff ff4(out[4], qnot[4], 1'b1, 1'b1, qnot[3], 1'b0, clear);
    jkff ff5(out[5], qnot[5], 1'b1, 1'b1, qnot[4], 1'b0, clear);
endmodule

// Testbench
module test_contador_decrescente;
    reg clk, clear;
    wire [5:0] out;
    
    contador_decrescente_6bits uut(out, clk, clear);
    
    initial begin
        // Inicializa entradas
        clk = 0;
        clear = 1;
        
        // Gera arquivo VCD para visualização
        $dumpfile("contador_decrescente.vcd");
        $dumpvars(0, test_contador_decrescente);
        
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