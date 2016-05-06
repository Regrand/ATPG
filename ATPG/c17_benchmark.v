// Verilog
// c17
// Ninputs 5
// Noutputs 2
// NtotalGates 6
// NAND2 6

module c17 
(
input wire[0:0] N1,
input wire[0:0] N2,
input wire[0:0] N3,
input wire[0:0] N6,
input wire[0:0] N7,
output wire[0:0] N22,
output wire[0:0] N23
);
wire[0:0] N10; 
wire[0:0] N11; 
wire[0:0] N16; 
wire[0:0] N19;
wire[0:0] N4;
wire[0:0] N5;
wire[0:0] N12;
wire[0:0] N13;
wire[0:0] N17;
wire[0:0] N18;

nand (N10, N1, N4);
nand (N11, N5, N6);
nand (N16, N2, N12);
nand (N19, N13, N7);
nand (N22, N10, N17);
nand (N23, N18, N19);
buf (N4, N3);
buf (N5, N3);
buf (N12, N11);
buf (N13, N11);
buf (N17, N16);
buf (N18, N16);

