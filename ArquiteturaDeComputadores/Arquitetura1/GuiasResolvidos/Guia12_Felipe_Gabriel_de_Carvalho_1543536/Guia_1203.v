
// Guia_1203.v
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536
// Questão 03: RAM 2x8 usando duas RAM 1x8

module ram1x8 (
  input wire clk,
  input wire rw,
  input wire addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  reg [7:0] mem;
  always @(posedge clk) begin
    if (rw && addr) mem <= data_in;
  end
  assign data_out = (!rw && addr) ? mem : 8'bz;
endmodule

module ram2x8 (
  input wire clk,
  input wire rw,
  input wire [0:0] addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  wire [7:0] out0, out1;
  wire sel0 = ~addr & rw;
  wire sel1 = addr & rw;

  ram1x8 ram0(clk, sel0, 1'b1, clr, data_in, out0);
  ram1x8 ram1(clk, sel1, 1'b1, clr, data_in, out1);

  assign data_out = (!rw) ? (addr ? out1 : out0) : 8'bz;
endmodule

// Testbench
module test_ram2x8;
  reg clk, rw, clr;
  reg [0:0] addr;
  reg [7:0] data_in;
  wire [7:0] data_out;

  ram2x8 DUT(clk, rw, addr, clr, data_in, data_out);

  always #5 clk = ~clk;

  initial begin
    $dumpfile("ram2x8.vcd");
    $dumpvars(0, test_ram2x8);

    clk = 0; rw = 0; clr = 0; addr = 0; data_in = 8'h00;
    #10 clr = 1;

    // Escreve em endereço 0
    #10 rw = 1; addr = 0; data_in = 8'hAA;
    #10 clk = 1; #10 clk = 0;

    // Escreve em endereço 1
    #10 addr = 1; data_in = 8'h55;
    #10 clk = 1; #10 clk = 0;

    // Leitura endereço 0
    #10 rw = 0; addr = 0;
    #10 clk = 1; #10 clk = 0;

    // Leitura endereço 1
    #10 addr = 1;
    #10 clk = 1; #10 clk = 0;

    #20 $finish;
  end
endmodule
