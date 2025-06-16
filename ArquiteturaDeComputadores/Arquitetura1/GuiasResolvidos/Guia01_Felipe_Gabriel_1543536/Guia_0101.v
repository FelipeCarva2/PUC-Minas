/* 
Guia_0101.v 
Felipe Gabriel de Carvalho - 1543536 
*/ 
module Guia01_Felipe_Gabriel_1543536; 
// define data 
integer   x = 13; // decimal 
reg [7:0] b =  0;  // binary   (bits - little endian) 
// actions 
initial 
begin : main 
$display ( "Guia_0101 - Tests" ); 
$display ( "x = %d"  , x ); 
$display ( "b = %8b", b ); 
b = x; 
$display ( "b = %8b", b ); 
end // main 
endmodule // Guia_0101 
/*Para compilar: iverilog -o Guia_0101.vvp Guia_0101.v 
Para executar:  vvp Guia_0101.vvp 
*/