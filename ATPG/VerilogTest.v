//Verilog Test file

module structural_verilog
(
input wire[0:0] A,
input wire[0:0] B,
input wire[0:0] C,
output wire[0:0] Z,
output wire[0:0] Y
);

wire[0:0] p;
wire[0:0] q;
wire[0:0] r;
wire[0:0] s;
wire[0:0] t;
wire[0:0] l;

nand(p, A, B, C );
or( q,A, p );
xor( r, B, C );
not(s,r );
nand(t,r,A);
nor(l,B,r);
and( Z,s, q );
or(Y, l, t);

endmodule
 