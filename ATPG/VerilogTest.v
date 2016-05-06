//Verilog Test file

module structural_verilog
(
input wire[0:0] A,
input wire[0:0] B,
input wire[0:0] C,
output wire[0:0] Z
);

wire[0:0] p;
wire[0:0] r;
wire[0:0] s;
wire[0:0] t;
wire[0:0] l;
wire[0:0] u;
wire[0:0] v;

buf(t, B);
buf(l, B);
buf(u, C);
buf(v, C);
xor(p, A, t, u );
nor( r, l, v );
not(s,r );
and( Z,s, p );

endmodule
 