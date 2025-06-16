/* 
Guia_0205.v 
Felipe Gabriel de Carvalho - 1543536
*/ 
module Guia_0205; 
// define data 
reg [7:0] a = 8'b000_1010 ; // binary 
reg [7:0] b = 8'b000_1100 ; // binary 
reg [7:0] c; 
// actions 
initial 
begin : main 
$display ( "Guia_0205 - Tests" ); 
$display ( "a = %8b", a ); 
$display ( "b = %8b", b ); 
c = a+b; 
$display ( "c = a+b = %8b", c ); 
c = a-b; 
$display ( "c = a-b = %8b", c ); 
c = b-a; 
$display ( "c = b-a = %8b", c ); 
c = a*b; 
$display ( "c = a*b = %8b", c ); 
c = b/a; 
$display ( "c = b/a = %8b", c ); 
c = b%a; 
$display ( "c = b%%a = %8b", c ); 
end // main 
endmodule // Guia_0205
/*
Para compilar: iverilog -o Guia_0205.vvp Guia_0205.v 
Para executar: vvp Guia_0205.vvp
*/