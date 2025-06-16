
// Guia_1202.v
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536
// Questão 02: RAM 1x8 usando duas RAM 1x4

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
  wire [3:0] q;
  wire enable = rw & addr;

  jkff ff0 (q[0], , data_in[0], ~data_in[0], clk & enable, 0, clr);
  jkff ff1 (q[1], , data_in[1], ~data_in[1], clk & enable, 0, clr);
  jkff ff2 (q[2], , data_in[2], ~data_in[2], clk & enable, 0, clr);
  jkff ff3 (q[3], , data_in[3], ~data_in[3], clk & enable, 0, clr);

  assign data_out = (rw == 0 && addr) ? q : 4'bz;
endmodule

module ram1x8 (
  input wire clk,
  input wire rw,
  input wire addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  wire [3:0] lo, hi;

  ram1x4 low(clk, rw, addr, clr, data_in[3:0], lo);
  ram1x4 high(clk, rw, addr, clr, data_in[7:4], hi);

  assign data_out = {hi, lo};
endmodule

// Testbench
module test_ram1x8;
  reg clk, rw, addr, clr;
  reg [7:0] data_in;
  wire [7:0] data_out;

  ram1x8 DUT (clk, rw, addr, clr, data_in, data_out);

  always #5 clk = ~clk;

  initial begin
    $dumpfile("ram1x8.vcd");
    $dumpvars(0, test_ram1x8);

    clk = 0; rw = 0; addr = 1; clr = 1; data_in = 8'b0;
    #10 clr = 0;

    // Escrita: 10101010
    #10 rw = 1; data_in = 8'b10101010;
    #10 clk = 1; #10 clk = 0;

    // Leitura
    #10 rw = 0;
    #10 clk = 1; #10 clk = 0;

    #20 $finish;
  end
endmodule
