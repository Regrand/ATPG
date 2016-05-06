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
wire[0:0] r;
wire[0:0] s;
wire[0:0] t;
wire[0:0] l;

buf(t, B);
buf(l, B);
xor(p, A, t, C );
nor( r, l, C );
not(s,r );
and( Z,s, p );

endmodule
 