// Guia_0904 - Pulse with 4x Frequency
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

`include "clock.v"

module pulse_fourx(output signal, input clock);
    reg signal;
    
    always @(posedge clock) begin
        signal = 1'b1;
        #3 signal = 1'b0;
        #3 signal = 1'b1;
        #3 signal = 1'b0;
        #3 signal = 1'b1;
        #3 signal = 1'b0;
        #3 signal = 1'b1;
        #3 signal = 1'b0;
        #3 signal = 1'b1;
        #3 signal = 1'b0;
    end
endmodule

module Guia_0904;
    wire clock;
    clock clk(clock);
    
    wire p1;
    
    pulse_fourx pls1(p1, clock);
    
    initial begin
        $dumpfile("Guia_0904.vcd");
        $dumpvars(1, clock, p1);
        #120 $finish;
    end
endmodule