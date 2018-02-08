#!/ms/dist/python/PROJ/core/2.7.11-64/bin/python

import math
import ms.version
ms.version.addpkg('numpy', '1.9.2')

import numpy as np

def m2(m):
    s=len(m)
    ss= 2**int(math.ceil(math.log(s, 2)))
    if s==ss:
        return
    for r in m:
        r.extend([0 for i in range(ss-s)])
    m.extend([[0 for i in range(ss)] for j in range(ss-s)])

    assert ss==len(m)
    assert all([ss==len(r) for r in m])

def strassen(a, b):

    def splitM(m):
        s=len(m)
        assert 0==s%2
        h=s/2
        rc=[((0, h), (0, h)), ((h, s), (0, h)), ((0, h),(h, s)), ((h, s), (h,s))]
        def fm(x):
            (xa, xb, ya, yb)=x[0]+x[1]
            #print ('mmmm', x, xa, xb, ya, yb)
            return [[m[y][x] for x in range(xa, xb)] for y in range(ya, yb)]
        return map(fm, rc)

    
    s=len(a)
    assert s==len(b)
    if 1==s:
        return [[a[0][0]*b[0][0]]]

    print( "\n---- * * ----- \n")
    (a11, a12, a21, a22)=splitM(a)
    print( np.matrix(a))
    print( "\n==> \n")
    for m in [a11, a12, a21, a22]:
        print( np.matrix(m))
    print( "\n---- * * ----- \n")
    (b11, b12, b21, b22)=splitM(b)
    print( np.matrix(b))
    print( "\n==> \n")
    for m in [b11, b12, b21, b22]:
        print( np.matrix(m))
    
    def addM(a, b):
        s=len(a)
        return [ [a[y][x]+b[y][x] for x in range(s)]for y in range(s)]
    def substM(a, b):
        s=len(a)
        return [ [a[y][x]-b[y][x] for x in range(s)]for y in range(s)]


    s=len(a11)
    m1=strassen(addM(a11, a22), addM(b11, b22))
    m2=strassen(addM(a21, a22), b11)
    m3=strassen(a11, substM(b12, b22))
    m4=strassen(a22, substM(b21, b11))
    m5=strassen(addM(a11, a12), b22)
    m6=strassen(substM(a21, a11), addM(b11, b12))
    m7=strassen(substM(a12, a22), addM(b21, b22))
    
    c11=substM(addM(addM(m1, m4), m7), m5)
    c12=addM(m3, m5)
    c21=addM(m2, m4)
    c22=substM(addM(addM(m1, m3), m6), m2)

    #print(c11, c12, c21, c22)
    
    [ c11[i].extend(c12[i]) for i in range(s)]
    [ c21[i].extend(c22[i]) for i in range(s)]
    c11.extend(c21)
    
    print("\n"+( '='*80))
    print np.matrix(c11)
    print("\n"+('='*80))

    return c11

def strassenProd(a, b):
    s=len(a)
    m2(a)
    m2(b)
    m=strassen(a, b)
    return [[m[y][x] for x in range(s)] for y in range(s)]


    
def test(input, prod):
    result=[]
    lines= open(input).readlines()
    i=0;
    n=int(lines[i].strip())
    print('total number: {}'.format(n))
    for j in range(0, n):
        i+=1
        m=int(lines[i].strip())
        #print m;
        i+=1
        v=[ float(x.strip()) for x in  lines[i].strip().split()]
        #print v;
        ma=np.reshape(v, (m,m))
        i+=1
        v=[ float(x.strip()) for x in  lines[i].strip().split()]
        #print v;
        mb=np.reshape(v, (m,m))

        if  j!=2:
            pass
            #continue

        print "\n", ("-.-" * 9), "\n", np.matrix(ma),"\n *", "\n", np.matrix(mb)
        mc=prod(ma.tolist(), mb.tolist())
        result.append(mc)
        #mc=prod(ma, mb.to)
        print "=\n", np.matrix(mc)
    return result
        
    

def main():
    r1=test('./input.txt', strassenProd)
    r2=test('./input.txt', np.dot)
    
    #np.random(9)
    #print(np.matrix(np.arange(15).reshape(3,5)), np.matrix(np.arange(16).reshape(4,4)))
    pass


if __name__=='__main__':
    main()
        
