`timescale 1ns/1ns

// Módulo Moore que detecta a sequência 1101
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

  // Saída depende apenas do estado (Moore)
  always @(current_state)
    case (current_state)
      id1101: y = 1;
      default: y = 0;
    endcase

  // Lógica de transição de estados
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

  // Atualização de estado síncrona
  always @(posedge clk or negedge reset) begin
    if (!reset)
      current_state <= start;
    else
      current_state <= next_state;
  end
endmodule

// Testbench
module Moore_Test;
  reg clk, reset, x;
  wire y;

  moore_1101 dut (y, x, clk, reset);

  // Gerador de clock
  always #5 clk = ~clk;

  initial begin
    $dumpfile("moore_wave.vcd");
    $dumpvars(0, Moore_Test);

    // Inicialização
    clk = 1;
    reset = 0;
    x = 0;

    // Reset
    #5 reset = 1;

    // Sequência de teste: 1, 1, 0, 1
    #10 x = 1;  // -> 1
    #10 x = 1;  // -> 1
    #10 x = 0;  // -> 0
    #10 x = 1;  // -> 1 (espera-se saída y = 1 aqui ou logo após)
    #20 $finish;
  end
endmodule
