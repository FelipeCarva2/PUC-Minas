// Guia_1201.v
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536
// Questão 01: RAM 1x4 usando flip-flops JK

module jkff (output reg q, output reg qnot, input j, input k, input clk, input preset, input clear);
  always @(posedge clk or posedge preset or posedge clear) begin
    if (clear) begin q <= 0; qnot <= 1; end
    else if (preset) begin q <= 1; qnot <= 0; end
    else if (j & ~k) begin q <= 1; qnot <= 0; end
    else if (~j & k) begin q <= 0; qnot <= 1; end
    else if (j & k) begin q <= ~q; qnot <= ~qnot; end
  end
endmodule

module ram1x4 (
  input wire clk,
  input wire rw,      
  input wire addr,
  input wire clr,
  input wire [3:0] data_in,
  output wire [3:0] data_out
);

  wire [3:0] q, qnot_dummy;
  wire enable = rw & addr;

  jkff ff0 (q[0], qnot_dummy[0], data_in[0], ~data_in[0], clk & enable, 0, clr);
  jkff ff1 (q[1], qnot_dummy[1], data_in[1], ~data_in[1], clk & enable, 0, clr);
  jkff ff2 (q[2], qnot_dummy[2], data_in[2], ~data_in[2], clk & enable, 0, clr);
  jkff ff3 (q[3], qnot_dummy[3], data_in[3], ~data_in[3], clk & enable, 0, clr);

  assign data_out = (rw == 0 && addr) ? q : 4'bz;

endmodule

module test_ram1x4;

  reg clk, rw, addr, clr;
  reg [3:0] data_in;
  wire [3:0] data_out;

  ram1x4 DUT (clk, rw, addr, clr, data_in, data_out);

  // Geração de clock
  always #5 clk = ~clk;

  initial begin
    $display("Testando RAM 1x4");
    $dumpfile("ram1x4.vcd");
    $dumpvars(0, test_ram1x4);

    clk = 0; rw = 0; addr = 1; clr = 1; data_in = 4'b0000;
    #10 clr = 0;

    // Escrita: 1101
    #10 rw = 1; data_in = 4'b1101;
    #20;

    // Leitura
    rw = 0;
    #20;

    $display("Saída lida: %b", data_out);

    #20 $finish;
  end

endmodule
