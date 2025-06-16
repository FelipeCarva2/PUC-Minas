/* 
Guia_0102.v 
Felipe Gabriel de Carvalho - 1543536
*/ 
module Guia_0102; 
// define data 
integer   x = 0;                  
// decimal 
reg [7:0] b = 8'b0001101; // binary (bits - little endian) 
// actions 
initial 
begin : main 
$display ( "Guia_0102 - Tests" ); 
$display ( "x = %d"  , x ); 
$display ( "b = %8b", b ); 
x = b; 
$display ( "b = %d", x ); 
$display( "------- Exerciciios ------");
b = 8'b00010011;
$display ( "b = %8b", b ); 
x = b; 
$display ( "b = %d", x ); 

b = 8'b00111011;
$display ( "b = %8b", b ); 
x = b; 
$display ( "b = %d", x );

//Tem alguma maneira de fazer cada teste sem ficar escrevendo código para cada valor em binário?

end // main 
endmodule // Guia_0102
/*
Para compilar: iverilog -o Guia_0102.vvp Guia_0102.v
Para executar:  vvp Guia_0102.vvp 
*/