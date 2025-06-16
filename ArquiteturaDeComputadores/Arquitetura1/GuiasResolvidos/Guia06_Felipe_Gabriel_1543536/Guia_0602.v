module logic_functions (
    input wire X,
    input wire Y,
    input wire Z,
    output wire F_a,
    output wire F_b,
    output wire F_c,
    output wire F_d,
    output wire F_e
);

    // a) F(X, Y, Z) = π M(1, 5, 7) = (X + Y + Z') • (X' + Y + Z') • (X' + Y' + Z')
    assign F_a = (X || Y || ~Z) && (~X || Y || ~Z) && (~X || ~Y || ~Z);

    // b) F(X, Y, Z) = π M(0, 4, 6) = (X + Y + Z) • (X' + Y + Z) • (X' + Y' + Z)
    assign F_b = (X || Y || Z) && (~X || Y || Z) && (~X || ~Y || Z);

    // c) F(X, Y, Z) = π M(1, 2, 3, 6) = (X + Y' + Z) • (X + Y' + Z') • (X' + Y' + Z)
    assign F_c = (X || ~Y || Z) && (X || ~Y || ~Z) && (~X || ~Y || Z);

    // d) F(X, Y, Z) = π M(0, 1, 5, 6) = (X + Y + Z) • (X + Y + Z') • (X' + Y + Z') • (X' + Y' + Z)
    assign F_d = (X || Y || Z) && (X || Y || ~Z) && (~X || Y || ~Z) && (~X || ~Y || Z);

    // e) F(X, Y, Z) = π M(0, 2, 4, 7) = (X + Y + Z) • (X + Y' + Z) • (X' + Y + Z) • (X' + Y' + Z')
    assign F_e = (X || Y || Z) && (X || ~Y || Z) && (~X || Y || Z) && (~X || ~Y || ~Z);

endmodule
module testbench;
    reg X, Y, Z;
    wire F_a, F_b, F_c, F_d, F_e;

    logic_functions uut (
        .X(X),
        .Y(Y),
        .Z(Z),
        .F_a(F_a),
        .F_b(F_b),
        .F_c(F_c),
        .F_d(F_d),
        .F_e(F_e)
    );

    initial begin
        $display("X Y Z | F_a F_b F_c F_d F_e");
        $display("---------------------------");
        for (integer i = 0; i < 8; i = i + 1) begin
            {X, Y, Z} = i;
            #10;  // Aguarda 10 unidades de tempo
            $display("%b %b %b |  %b   %b   %b   %b   %b", X, Y, Z, F_a, F_b, F_c, F_d, F_e);
        end
        $finish;
    end
endmodule

/*
Para compilar: iverilog -o Guia_0602.vvp Guia_0602.v 
Para executar: vvp Guia_0602.vvp
*/