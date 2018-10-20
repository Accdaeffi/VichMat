package lab1;

public class Answer {

	public double x[],y[],f[],e[],p[],a=0,b=0,c=0,S=0,r=0,q=0;
	int size;
	
	public Answer (double x[], double y[]) {
		this.size=x.length;
		this.x=new double[size];
		this.y=new double[size];
		p=new double[size];
		e=new double[size];
		f=new double[size];
		for (int i=0;i<size;i++) {
			this.x[i]=x[i];
			this.y[i]=y[i];
		}
	}
}
