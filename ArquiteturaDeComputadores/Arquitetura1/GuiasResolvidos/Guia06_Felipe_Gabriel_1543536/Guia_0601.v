module todas_funcoes(
    input x, y, z,
    output f_a, f_b, f_c, f_d, f_e
);
    // Função a) f(x,y,z) = x'z + yz
    assign f_a = (~x & z) | (y & z);

    // Função b) f(x,y,z) = y'z' + xz'
    assign f_b = (~y & ~z) | (x & ~z);

    // Função c) f(x,y,z) = x'z + yz'
    assign f_c = (~x & z) | (y & ~z);

    // Função d) f(x,y,z) = x'z + yz'
    assign f_d = (~x & z) | (y & ~z);

    // Função e) f(x,y,z) = y'z' + x'z' + xyz
    assign f_e = (~y & ~z) | (~x & ~z) | (x & y & z);
endmodule

module testbench;
    reg x, y, z;
    wire f_a, f_b, f_c, f_d, f_e;

    // Instância do módulo que implementa as funções
    todas_funcoes uut(
        .x(x),
        .y(y),
        .z(z),
        .f_a(f_a),
        .f_b(f_b),
        .f_c(f_c),
        .f_d(f_d),
        .f_e(f_e)
    );

    // Teste de todas as combinações de x, y, z
    initial begin
        // Cabeçalho da tabela-verdade
        $display("x | y | z || f_a | f_b | f_c | f_d | f_e");
        $display("-------------------------------");

        // Loop para testar todas as combinações
        x = 0; y = 0; z = 0;
        repeat (8) begin
            #10; // Aguarda um tempo para estabilizar
            $display("%b | %b | %b ||  %b  |  %b  |  %b  |  %b  |  %b",
                     x, y, z, f_a, f_b, f_c, f_d, f_e);

            // Incrementa as entradas
            if (z == 0) z = 1;
            else begin
                z = 0;
                if (y == 0) y = 1;
                else begin
                    y = 0;
                    if (x == 0) x = 1;
                    else x = 0;
                end
            end
        end
    end
endmodule

/*
Para compilar: iverilog -o Guia_0601.vvp Guia_0601.v 
Para executar: vvp Guia_0601.vvp
*/