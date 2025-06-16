// Guia_1303.v - Contador assíncrono decádico crescente de 5 bits com flip-flops JK 
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

module contador_decadico_crescente_5bits(
    output [4:0] out,
    input clk,
    input clear
);
    wire [4:0] qnot;
    wire reset;
    
    // Detecta 9 (01001) para resetar para 0
    assign reset = out[3] & ~out[2] & ~out[1] & out[0];
    
    jkff ff0(out[0], qnot[0], 1'b1, 1'b1, clk, 1'b0, clear | reset);
    jkff ff1(out[1], qnot[1], 1'b1, 1'b1, out[0], 1'b0, clear | reset);
    jkff ff2(out[2], qnot[2], 1'b1, 1'b1, out[1], 1'b0, clear | reset);
    jkff ff3(out[3], qnot[3], 1'b1, 1'b1, out[2], 1'b0, clear | reset);
    jkff ff4(out[4], qnot[4], 1'b1, 1'b1, out[3], 1'b0, clear | reset);
endmodule

// Testbench
module test_contador_decadico_crescente;
    reg clk, clear;
    wire [4:0] out;
    
    contador_decadico_crescente_5bits uut(out, clk, clear);
    
    initial begin
        // Inicializa entradas
        clk = 0;
        clear = 1;
        
        // Gera arquivo VCD para visualização
        $dumpfile("contador_decadico_crescente.vcd");
        $dumpvars(0, test_contador_decadico_crescente);
        
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