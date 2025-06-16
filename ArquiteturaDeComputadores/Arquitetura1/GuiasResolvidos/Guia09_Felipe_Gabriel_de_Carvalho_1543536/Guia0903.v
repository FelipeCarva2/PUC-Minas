// Guia_0903 - Pulse with 1/3 Frequency
// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536

`include "clock.v"

module pulse_third(output signal, input clock);
    reg signal;
    integer count = 0;
    
    always @(posedge clock) begin
        count = count + 1;
        if (count == 3) begin
            signal = 1'b1;
            #12 signal = 1'b0;
            count = 0;
        end
    end
endmodule

module Guia_0903;
    wire clock;
    clock clk(clock);
    
    wire p1;
    
    pulse_third pls1(p1, clock);
    
    initial begin
        $dumpfile("Guia_0903.vcd");
        $dumpvars(1, clock, p1);
        #480 $finish;
    end
endmodule