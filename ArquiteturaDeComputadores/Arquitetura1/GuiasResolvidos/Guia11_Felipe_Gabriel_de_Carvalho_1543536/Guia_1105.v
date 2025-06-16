`timescale 1ns/1ns
`define found 1
`define notfound 0

// ------------------- Módulo Mealy: Detecta 1100 -------------------
module Guia_1102 (y, x, clk, reset);
  output reg y;
  input x, clk, reset;
  
  parameter
    S0 = 2'b00,
    S1 = 2'b01,
    S2 = 2'b10,
    S3 = 2'b11;
  
  reg [1:0] current_state, next_state;

  // Lógica do próximo estado e saída (Mealy)
  always @(x or current_state) begin
    y = `notfound;
    case (current_state)
      S0: begin
        next_state = x ? S1 : S0;
        y = `notfound;
      end
      S1: begin
        next_state = x ? S1 : S2;
        y = `notfound;
      end
      S2: begin
        next_state = x ? S3 : S0;
        y = `notfound;
      end
      S3: begin
        next_state = x ? S1 : S0;
        y = (x == 0) ? `found : `notfound; // Detecta sequência 1100
      end
      default: next_state = S0;
    endcase
  end

  // Atualização de estado
  always @(posedge clk or negedge reset) begin
    if (!reset)
      current_state <= S0;
    else
      current_state <= next_state;
  end
endmodule

// ------------------- Testbench -------------------
module Guia_1102_Test;
  reg clk, reset, x;
  wire y;

  Guia_1102 dut (y, x, clk, reset);

  // Geração do clock
  always #5 clk = ~clk;

  initial begin
    $dumpfile("guia_1102_wave.vcd");
    $dumpvars(0, Guia_1102_Test);

    clk = 0;
    reset = 0;
    x = 0;

    // Ativa reset
    #5 reset = 1;

    // Sequência: 1, 1, 0, 0 (deve detectar aqui: y = 1)
    #10 x = 1;
    #10 x = 1;
    #10 x = 0;
    #10 x = 0;

    // Testa reinício com nova sequência válida
    #10 x = 1;
    #10 x = 1;
    #10 x = 0;
    #10 x = 0;

    #20 $finish;
  end
endmodule
