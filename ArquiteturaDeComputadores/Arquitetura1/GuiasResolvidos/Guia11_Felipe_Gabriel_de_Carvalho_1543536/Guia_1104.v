`timescale 1ns/1ns
`define found 1
`define notfound 0

// ------------------- Módulo Moore -------------------
module Guia_1101 (y, x, clk, reset);
  output wire y;
  input x, clk, reset;
  
  parameter
    S0 = 3'b000,
    S1 = 3'b001,
    S2 = 3'b010,
    S3 = 3'b011,
    S4 = 3'b100; // Estado final
  
  reg [2:0] current_state, next_state;

  // Transição de estado (combinacional)
  always @(x or current_state) begin
    case (current_state)
      S0: next_state = x ? S1 : S0;
      S1: next_state = x ? S1 : S2;
      S2: next_state = x ? S3 : S0;
      S3: next_state = x ? S1 : S4;
      S4: next_state = S4; // Trava no estado final
      default: next_state = S0;
    endcase
  end

  // Atualização do estado (sincronizada com clock)
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
module Guia_1101_Test;
  reg clk, reset, x;
  wire y;

  Guia_1101 dut (y, x, clk, reset);

  // Geração do clock
  always #5 clk = ~clk;

  initial begin
    $dumpfile("guia_1101_locked.vcd");
    $dumpvars(0, Guia_1101_Test);

    clk = 0;
    reset = 0;
    x = 0;

    // Ativa reset
    #5 reset = 1;

    // Envia sequência 1101
    #10 x = 1;
    #10 x = 1;
    #10 x = 0;
    #10 x = 1; // <- aqui espera-se y = 1

    // Envia mais bits para mostrar que travou no estado final
    #10 x = 0;
    #10 x = 1;
    #10 x = 0;

    #30 $finish;
  end
endmodule
