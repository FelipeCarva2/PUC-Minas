/* 
Guia_0302.v 
Felipe gabriel de Carvalho - 1543536
*/ 
module Guia_0302; 
// define data 
reg [7:0] a = 8'h0a ; // hexadecimal 
reg [6:0] b = 8'o15 ; // octal 
reg [5:0] c = 13   ; 
reg [7:0] d = 0    ;    
reg [6:0] e = 0   ;     
reg [5:0] f  = 0  ;      
// actions 
initial 
begin : main 

$display ( "Guia_0302 - Tests" ); 
d = ~a+1; 
$display ( "a = %8b -> C1(a) = %8b -> C2(a) = %8b", a, ~a, d ); 
e = ~b+1; 
$display ( "b = %7b -> C1(b) = %7b -> C2(b) = %7b", b, ~b, e ); 
f = ~c+1; 
$display ( "c = %6b -> C1(c) = %6b -> C2(c) = %6b", c, ~c, f ); 
end // main 
endmodule // Guia_0302

/*
Para compilar: iverilog -o Guia_0302.vvp Guia_0302.v 
Para executar: vvp Guia_0302.vvp
*/