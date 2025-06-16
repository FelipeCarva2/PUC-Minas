`timescale 1ns/1ps
`define found 1
`define notfound 0

// Módulo Mealy que detecta a sequência "1101"
module mealy_1101 (y, x, clk, reset);
  output reg y;
  input x, clk, reset;

  parameter
    start = 2'b00,
    id1 = 2'b01,
    id11 = 2'b11,
    id110 = 2'b10;

  reg [1:0] current_state, next_state;

  // Lógica do próximo estado e saída (Mealy)
  always @(x or current_state) begin
    y = `notfound;
    case (current_state)
      start: next_state = x ? id1 : start;
      id1: next_state = x ? id11 : start;
      id11: next_state = x ? id11 : id110;
      id110: begin
        next_state = x ? id1 : start;
        y = x ? `found : `notfound;
      end
      default: next_state = start;
    endcase
  end

  // Atualização de estado
  always @(posedge clk or negedge reset) begin
    if (!reset)
      current_state <= start;
    else
      current_state <= next_state;
  end
endmodule

// Testbench incluído no mesmo arquivo
module tb_mealy_1101;
  reg x, clk, reset;
  wire y;

  // Instanciando o módulo
  mealy_1101 dut (.y(y), .x(x), .clk(clk), .reset(reset));

  // Gerador de clock
  always #5 clk = ~clk;

  initial begin
    // Dump para GTKWave (opcional)
    $dumpfile("wave.vcd");
    $dumpvars(0, tb_mealy_1101);

    // Inicialização
    clk = 0;
    reset = 0;
    x = 0;

    // Reset
    #10 reset = 1;

    // Sequência de teste: 1101
    #10 x = 1;
    #10 x = 1;
    #10 x = 0;
    #10 x = 1; // Aqui y deve ser 1

    // Sequência extra para observar comportamento
    #10 x = 1;
    #10 x = 0;
    #10 x = 1;
    #10 x = 1;

    #20 $finish;
  end
endmodule
