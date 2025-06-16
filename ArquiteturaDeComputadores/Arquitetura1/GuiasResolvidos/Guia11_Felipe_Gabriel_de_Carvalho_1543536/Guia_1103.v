`timescale 1ns/1ns

// ---------- Mealy: Detecta 1101 ----------
module mealy_1101 (y, x, clk, reset);
  output reg y;
  input x, clk, reset;

  parameter
    start = 2'b00,
    id1 = 2'b01,
    id11 = 2'b11,
    id110 = 2'b10;

  reg [1:0] current_state, next_state;

  always @(x or current_state) begin
    y = 0;
    case (current_state)
      start: next_state = x ? id1 : start;
      id1: next_state = x ? id11 : start;
      id11: next_state = x ? id11 : id110;
      id110: begin
        next_state = x ? id1 : start;
        y = x ? 1 : 0;
      end
      default: next_state = start;
    endcase
  end

  always @(posedge clk or negedge reset) begin
    if (!reset)
      current_state <= start;
    else
      current_state <= next_state;
  end
endmodule

// ---------- Moore: Detecta 1101 ----------
module moore_1101(y, x, clk, reset);
  input x, clk, reset;
  output reg y;

  parameter
    start = 3'b000,
    id1 = 3'b001,
    id11 = 3'b010,
    id110 = 3'b011,
    id1101 = 3'b100;

  reg [2:0] current_state, next_state;

  always @(current_state)
    case (current_state)
      id1101: y = 1;
      default: y = 0;
    endcase

  always @(x or current_state) begin
    case (current_state)
      start: next_state = x ? id1 : start;
      id1: next_state = x ? id11 : start;
      id11: next_state = x ? id11 : id110;
      id110: next_state = x ? id1101 : start;
      id1101: next_state = x ? id11 : start;
      default: next_state = start;
    endcase
  end

  always @(posedge clk or negedge reset) begin
    if (!reset)
      current_state <= start;
    else
      current_state <= next_state;
  end
endmodule

// ---------- Testbench comparativo ----------
module Guia_1100;
  reg clk, reset, x;
  wire m1, m2;

  mealy_1101 mealy1 (m1, x, clk, reset);
  moore_1101 moore1 (m2, x, clk, reset);

  // Gerador de clock
  always #5 clk = ~clk;

  initial begin
    $dumpfile("guia_1100_wave.vcd");
    $dumpvars(0, Guia_1100);

    clk = 1;
    reset = 0;
    x = 0;
    #5 reset = 1;

    // Sequência de entrada: 1,1,0,1,0,1
    #10 x = 1;
    #10 x = 1;
    #10 x = 0;
    #10 x = 1;
    #10 x = 0;
    #10 x = 1;

    #30 $finish;
  end
endmodule
