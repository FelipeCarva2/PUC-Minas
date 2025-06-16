/* 
Guia_0103.v 
Felipe Gabriel de Carvalho - 1543536 
*/ 
module Guia_0103; 
// define data 
integer   x = 13; // decimal 
reg [7:0] b =  0;  // binary 
// actions 
initial 
begin : main 
$display ( "Guia_0103 - Tests" ); 
$display ( "x = %d"  , x ); 
$display ( "b = %8b", b ); 
b = x; 
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b ); 
$display("------ Exercicios ------");
//Está printando x mas é a variavel b que recebe o valor
b = 54; 
$display ( "x = %d"  , b );
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
b = 67; 
$display ( "x = %d"  , b );
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
b = 76; 
$display ( "x = %d"  , b );
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
b = 157; 
$display ( "x = %d"  , b );
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
b = 731; 
$display ( "x = %d"  , b );
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
b = 21; 
$display ( "x = %d"  , b );
$display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );


end // main 
endmodule // Guia_0103 
/*
Para compilar: iverilog -o Guia_0103.vvp Guia_0103.v
Para executar:  vvp Guia_0103.vvp 
*/