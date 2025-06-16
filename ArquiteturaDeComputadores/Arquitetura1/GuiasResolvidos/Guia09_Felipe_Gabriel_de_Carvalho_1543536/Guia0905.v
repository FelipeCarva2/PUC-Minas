// Guia_0905 - 3-time-unit Pulse
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

`include "clock.v"

module pulse_3units(output signal, input clock);
    reg signal;
    
    always @(posedge clock) begin
        signal = 1'b1;
        #3 signal = 1'b0;
    end
endmodule

module Guia_0905;
    wire clock;
    clock clk(clock);
    
    wire p1;
    
    pulse_3units pls1(p1, clock);
    
    initial begin
        $dumpfile("Guia_0905.vcd");
        $dumpvars(1, clock, p1);
        #120 $finish;
    end
endmodule