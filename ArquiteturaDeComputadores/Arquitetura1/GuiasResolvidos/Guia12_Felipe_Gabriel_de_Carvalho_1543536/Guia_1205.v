// Guia_1205.v
// Nome: Felipe Gabriel de Carvalho
// Matrícula: 1543536
// Questão 05: RAM 8x8 usando duas RAM 4x8

module ram4x8 (
  input wire clk,
  input wire rw,
  input wire [1:0] addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  reg [7:0] mem [3:0];
  always @(posedge clk) begin
    if (rw)
      mem[addr] <= data_in;
  end
  assign data_out = (!rw) ? mem[addr] : 8'bz;
endmodule

module ram8x8 (
  input wire clk,
  input wire rw,
  input wire [2:0] addr,
  input wire clr,
  input wire [7:0] data_in,
  output wire [7:0] data_out
);
  wire [7:0] out0, out1;
  wire sel = addr[2];

  ram4x8 ram_lo(clk, rw, addr[1:0], clr, data_in, out0);
  ram4x8 ram_hi(clk, rw, addr[1:0], clr, data_in, out1);

  assign data_out = (!rw) ? (sel ? out1 : out0) : 8'bz;
endmodule

// Testbench
module test_ram8x8;
  reg clk, rw, clr;
  reg [2:0] addr;
  reg [7:0] data_in;
  wire [7:0] data_out;

  ram8x8 DUT(clk, rw, addr, clr, data_in, data_out);

  always #5 clk = ~clk;

  initial begin
    $dumpfile("ram8x8.vcd");
    $dumpvars(0, test_ram8x8);

    clk = 0; rw = 1; clr = 1; addr = 0; data_in = 8'h00;
    #10 clr = 0;

    // Escrita em cada endereço
    #10 addr = 3'b000; data_in = 8'h11;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b001; data_in = 8'h22;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b010; data_in = 8'h33;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b011; data_in = 8'h44;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b100; data_in = 8'h55;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b101; data_in = 8'h66;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b110; data_in = 8'h77;
    #10 clk = 1; #10 clk = 0;

    #10 addr = 3'b111; data_in = 8'h88;
    #10 clk = 1; #10 clk = 0;

    // Leitura de todos os endereços
    #10 rw = 0;

    #10 addr = 3'b000; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b001; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b010; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b011; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b100; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b101; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b110; #10 clk = 1; #10 clk = 0;
    #10 addr = 3'b111; #10 clk = 1; #10 clk = 0;

    #20 $finish;
  end
endmodule
