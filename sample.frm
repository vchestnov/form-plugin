Functions f,g,sin;
Symbols x,y;
Vectors p;
Tensors T;
Indices mu,nu,i1,i2;
* Pattern matching with fucntions
Local F = f(sin,x) * f(x,x) * x;
* F -> F=sin(x,x)*f(x)*x;
id f(g?,x) = g(x,x);
* New module: Local declarations can only be in the beginning of a new
* module. So the outline of a program looks as follows:
* -declarations
* -id's
* -.sort
* -more declarations ...
.sort
* Pattern matching with tensors
Local G = T(mu,nu) * T(i1);
* G -> G=T(i1)*p(i1)*p(i2);
id T(i1?,i2?) = p(i1) * p(i2);
* F -> F=sin(x,x)*p(mu)*p(nu);
id x = y;
Print F,G;
.sort
Local F1 = a^2 + a * b + c;

* a^2 + a * b + c -> a^2 + a * 12345 + c
if (count(a,1) == 1);
    id b = 12345;
endif;

#define a "5"
.end
