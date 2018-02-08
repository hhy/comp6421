#include<iostream>
#include<fstream>
#include<cstring>
#include<cmath>
#include<iomanip>
#include<cassert>
#include "simple_matrix.hpp"
using namespace std;


void showMatrix(SMatrix<double>  mtx){
  int m=mtx.size();
  //cout<<"--size: "<<m<<"-------"<<endl;
  //  return;
  cout<<endl;
  for(int i=0; i<m; i++){
    cout<<'|';
    for(int j=0; j<m; j++){
      cout<<setw(5)<<mtx(i, j);
    }
    cout<<'|'<<endl;
  }
}

SMatrix<double>*** init(ifstream& infile){
  int gn;
  infile>>gn;
  SMatrix<double>*** msp=new SMatrix<double>**[gn];

  for(int i=0; i< gn; i++){
    int m;
    infile>>m;
    SMatrix<double>** ms=new SMatrix<double>*[2];
    *(msp+i)=ms;
    for(int j=0; j<2; j++){
      SMatrix<double>* ptr=new SMatrix<double>(m);
      ms[j]=ptr;
      for(int p=0; p<m; p++)
	for(int q=0; q<m; q++){
	  double v;
	  infile>>v;
	  cout<<v<<',';
	  (*ptr)(p, q)=v;
	}
      cout<<endl;
    }
  }
  return msp;
}


/**

 */
SMatrix<double> expand(SMatrix<double> m){
  int s=m.size();
  //cout<<"aa"<<endl;
  int ss=ceil(log2(s));
  //cout<<"bb"<<endl;
  ss=exp2(ss);
  //cout<<"mi: " <<log2(s) <<','<<ceil(log2(s))<<','<< ss<<endl;
  
  SMatrix<double> mm(ss);
  for(int i=0; i<ss; i++){
      for(int j=0; j<ss; j++){
	(i<s && j<s)? mm(i, j)=m(i, j): mm(i, j)=0;
      }
  }
  //cout<<"cc"<<endl;
  return mm;
}

SMatrix<double>** splitM(SMatrix<double> m){

  int s=m.size();
  int h=s/2;
  assert(h>=1);
  //cout<< 'h'<<h<<'m'<<s<<endl;
  SMatrix<double>** ms=new SMatrix<double>*[4];

  for(int i=0; i<4; i++)
    //ms[i]=new SMatrix<double>(h);
    ms[i]=new SMatrix<double>(h);
  for(int i=0; i<s; i++){
    for(int j=0; j<s; j++){
      int r=i<h?0:1;
      int c=j<h?0:1;
      //cout<< 'r'<<r<<'c'<<c<<'n'<<(r*2+c)<<endl;
      (*(ms[r*2+c]))(i%h,j%h)=m(i,j);
      
    }
  }

  return ms;
}

const SMatrix<double> operator+(SMatrix<double> ma, SMatrix<double> mb){
  int s=ma.size();
  SMatrix<double> m(s);
  for(int i=0; i<s; i++)
    for(int j=0; j<s; j++)
      m(i,j)=ma(i,j)+mb(i,j);
  return m;
}
const SMatrix<double> operator-(SMatrix<double> ma, SMatrix<double> mb){
  int s=ma.size();
  SMatrix<double> m(s);
  for(int i=0; i<s; i++)
    for(int j=0; j<s; j++)
      m(i,j)=ma(i,j)-mb(i,j);
  return m;
}




SMatrix<double> strassen(SMatrix<double> ma, SMatrix<double> mb){
  int s=ma.size();
  assert(s>0);
  assert(s==mb.size());
  if(s==1){
    SMatrix<double> m(1);
    m(0,0)=ma(0,0)*mb(0,0);
    return m;
  }

  SMatrix<double>** maa=splitM(ma);
  SMatrix<double>** mbb=splitM(mb);

  SMatrix<double> a11=*maa[0];
  SMatrix<double>  a12=*maa[1];
  SMatrix<double>    a21=*maa[2];
  SMatrix<double>    a22=*maa[3];
  SMatrix<double>    b11=*mbb[0];
  SMatrix<double>    b12=*mbb[1];
  SMatrix<double>   b21=*mbb[2];
  SMatrix<double>    b22=*mbb[3];

  SMatrix<double> m1=strassen(a11+a22, b11+b22);
  SMatrix<double> m2=strassen(a21+a22, b11);
  SMatrix<double> m3=strassen(a11, (b12- b22));
  SMatrix<double> m4=strassen(a22, (b21- b11));
  SMatrix<double>    m5=strassen((a11+ a12), b22);
  SMatrix<double>  m6=strassen((a21- a11), (b11+ b12));
  SMatrix<double>    m7=strassen((a12- a22), (b21+ b22));

  SMatrix<double> c11=(((m1+ m4)+ m7)- m5);
  SMatrix<double> c12=(m3+ m5);
  SMatrix<double> c21=(m2+ m4);
  SMatrix<double> c22=(((m1+ m3)+ m6)- m2);
  
  SMatrix<double> cc(s);
  SMatrix<double> cs[]={c11, c12, c21, c22};
  int h=s/2;
  for(int i=0; i<s; i++){
    int r=i<h?0:1;
    for(int j=0; j<s; j++){
      int c=j<h?0:1;
      cc(i,j)=(cs[r*2+c])(i%h, j%h);
    }
  }

  return cc;
}


//SMatrix<double> strassenProd(SMatrix<double> ma, SMatrix<double> mb){
const SMatrix<double> operator*(SMatrix<double>& ma, SMatrix<double>& mb){

  int s=ma.size();
  ma=expand(ma);
  mb=expand(mb);
  SMatrix<double> mm=strassen(ma, mb);
  SMatrix<double> m(s);
  for(int i=0;i<s; i++)
    for(int j=0;j<s;j++)
      m(i,j)=mm(i,j);
  return m;
}



int main(int argc, char** argv){
  if(argc!=2||strlen(argv[1])<1){
    cout<<"no input file"<<endl;
    return 1;
  }
  ifstream infile(argv[1]);
  if(!infile){
    cout<<"fail open input file"<<endl;
    return 1;
  }

  SMatrix<double>*** ms=init(infile);
  for(int i=0; i<3; i++){
    if(i!=0){
      //continue;
    }
    for(int j=0; j<2; j++){
      //cout<<'d'<<endl;
      SMatrix<double> m=*ms[i][j];
      if(1>=m.size()){
	continue;
      }
      showMatrix(m);
      cout<< "==> "<<endl;
      SMatrix<double> mm=expand(m);
      //showMatrix(mm);
      //cout<< "= split to ===> "<<endl;
      SMatrix<double>** p=splitM(mm);
      for(int k=0; k<4; k++){
	//showMatrix(*(p[k]));
      }
      //cout<< "= split end ===> "<<endl;
    }
    cout<<"sum ==> "<<endl;
    //showMatrix( (*ms[i][0])+(*ms[i][1]) );
    
    //showMatrix( strassenProd( (*ms[i][0]),(*ms[i][1]) ));
    SMatrix<double> ma=*ms[i][0];
    SMatrix<double> mb=*ms[i][1];
    showMatrix(ma*mb);
    cout<<"sum end==<< "<<endl;
  }
  return 0;
  /*
      Smatrix<int> a(3);
      cout<< a.size()<<endl;
        a(1,1)=9;
	cout<<a(1,1)<<endl;
	  return 0;
  */
}
