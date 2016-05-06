//Verilog Test file
//sa1 at node s

module structural_verilog
(
input wire[0:0] A,
input wire[0:0] B,
input wire[0:0] C,
output wire[0:0] X,
output wire[0:0] Y,
output wire[0:0] Z
);
wire[0:0] d;
wire[0:0] e;
wire[0:0] f;
wire[0:0] g;
wire[0:0] h;
wire[0:0] k;
wire[0:0] l;
wire[0:0] p;
wire[0:0] q;
wire[0:0] r;
wire[0:0] s;
wire[0:0] t;
wire[0:0] u;
wire[0:0] v;


and(d,A,B);
buf(h,B);
buf(k,h);
buf(l,h);
buf(g,d);
xnor(m,g,k);
buf(n,m);
buf(p,m);
not(q,p);
buf(f,d);
buf(e,d);
xnor(r,f,n);
not(s,r);
buf(t,s);
nand(X,r,e);
or(Y,t,q);
buf(u,s);
xnor(v,u,l);
nand(Z,v,m,C);

endmodule
 