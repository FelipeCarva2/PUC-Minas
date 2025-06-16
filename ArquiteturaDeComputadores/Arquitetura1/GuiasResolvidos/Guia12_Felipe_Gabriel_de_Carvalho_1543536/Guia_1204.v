// Guia_1204.v
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536
// Questão 04: RAM 4x8 usando duas RAM 2x8

module ram2x8 (
  input wire clk,
  input wire rw,
  input wire [0:0] addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  reg [7:0] mem [1:0];
  always @(posedge clk) begin
    if (rw)
      mem[addr] <= data_in;
  end
  assign data_out = (!rw) ? mem[addr] : 8'bz;
endmodule

module ram4x8 (
  input wire clk,
  input wire rw,
  input wire [1:0] addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  wire [7:0] out0, out1;
  wire sel = addr[1];

  ram2x8 ram_lo(clk, rw, addr[0], clr, data_in, out0);
  ram2x8 ram_hi(clk, rw, addr[0], clr, data_in, out1);

  assign data_out = (!rw) ? (sel ? out1 : out0) : 8'bz;
endmodule

// Testbench
module test_ram4x8;
  reg clk, rw, clr;
  reg [1:0] addr;
  reg [7:0] data_in;
  wire [7:0] data_out;

  ram4x8 DUT(clk, rw, addr, clr, data_in, data_out);

  always #5 clk = ~clk;

  initial begin
    $dumpfile("ram4x8.vcd");
    $dumpvars(0, test_ram4x8);

    clk = 0; rw = 1; clr = 1; addr = 0; data_in = 8'h00;
    #10 clr = 0;

    // Escrita em cada endereço
    #10 addr = 2'b00; data_in = 8'hA1;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 2'b01; data_in = 8'hB2;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 2'b10; data_in = 8'hC3;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 2'b11; data_in = 8'hD4;
    #10 clk = 1; #10 clk = 0;

    // Leitura de todos
    #10 rw = 0;

    #10 addr = 2'b00; #10 clk = 1; #10 clk = 0;
    #10 addr = 2'b01; #10 clk = 1; #10 clk = 0;
    #10 addr = 2'b10; #10 clk = 1; #10 clk = 0;
    #10 addr = 2'b11; #10 clk = 1; #10 clk = 0;

    #20 $finish;
  end
endmodule
