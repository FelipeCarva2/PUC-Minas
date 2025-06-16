// Nome: Felipe Gabriel de Carvalho
// Matricula: 1543536
`timescale 1ns/1ns
`define found 1
`define notfound 0

// ------------------- Módulo Moore com sobreposição -------------------
module Guia_1103 (y, x, clk, reset);
  output wire y;
  input x, clk, reset;
  
  parameter
    S0 = 3'b000,
    S1 = 3'b001,
    S2 = 3'b010,
    S3 = 3'b011,
    S4 = 3'b100; // Estado de detecção
  
  reg [2:0] current_state, next_state;

  // Lógica de transição de estado
  always @(x or current_state) begin
    case (current_state)
      S0: next_state = x ? S1 : S0;
      S1: next_state = x ? S1 : S2;
      S2: next_state = x ? S3 : S0;
      S3: next_state = x ? S1 : S4;
      S4: next_state = x ? S1 : S2; // Sobreposição permitida
      default: next_state = S0;
    endcase
  end

  // Atualização de estado síncrona
  always @(posedge clk or negedge reset) begin
    if (!reset)
      current_state <= S0;
    else
      current_state <= next_state;
  end

  // Saída Moore
  assign y = (current_state == S4) ? `found : `notfound;
endmodule

// ------------------- Testbench -------------------
module Guia_1103_Test;
  reg clk, reset, x;
  wire y;

  Guia_1103 dut (y, x, clk, reset);

  // Clock
  always #5 clk = ~clk;

  initial begin
    $dumpfile("guia_1103_wave.vcd");
    $dumpvars(0, Guia_1103_Test);

    clk = 0;
    reset = 0;
    x = 0;

    // Ativa reset
    #5 reset = 1;

    // Sequência: 1,1,0,1 — deve detectar e y = 1
    #10 x = 1;
    #10 x = 1;
    #10 x = 0;
    #10 x = 1;

    // Sobreposição: 1,0,1 — outra detecção logo em seguida
    #10 x = 1;
    #10 x = 0;
    #10 x = 1;

    #30 $finish;
  end
endmodule
