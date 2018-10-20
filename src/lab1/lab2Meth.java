package lab1;

public class lab2Meth {

	static Answer Lineal(double x[], double y[]) {
		
		Answer ans = new Answer(x,y);
		
		lab2Common.findAB(ans);
		lab2Common.findSPE(ans);		
		
		ans.r = lab2Common.countR(x,y);
		
		ans.q = Math.sqrt(ans.S/ans.size);
		return ans;		
	}
	
	static double Quadra(double x[], double y[]) {
		
		double SX=0,SXX=0,SXXX=0,SXXXX=0,SY=0,SXY=0,SXXY=0;
		int size = x.length;
		
		for (int i=0;i<size;i++)
		{
			SX=SX+x[i];
			SY=SY+y[i];
			SXX=SXX+x[i]*x[i];
			SXY=SXY+x[i]*y[i];
			SXXX=SXXX+x[i]*x[i]*x[i];
			SXXXX=SXXXX+x[i]*x[i]*x[i]*x[i];
			SXXY=SXXY+x[i]*x[i]*y[i];
		}
		
		
		double[][] equat = {{size, SX, SXX, SY}, {SX, SXX, SXXX, SXY}, {SXX, SXXX, SXXXX, SXXY}};
		/*
		for (int i=0;i<4;i++) {
			equat[1][i]=equat[1][i]-equat[0][i]*(equat[1][0]/equat[0][0]);
			equat[2][i]=equat[2][i]-equat[0][i]*(equat[2][0]/equat[0][0]);
			equat[2][i]=equat[2][i]-equat[1][i]*(equat[2][1]/equat[1][1]);
		}
		
		double a2=equat[2][3]/equat[2][2];
		double a1=(equat[1][3]-(a2*equat[1][2]))/equat[1][1];
		double a0=(equat[0][3]-(a2*equat[0][2])-(a1*equat[0][1]))/equat[0][0];
		*/
		
		GaussMatrix gm = new GaussMatrix(equat);
		gm = gm.triangleMatrix();
		double[] roots = gm.roots();
		
		
		double S=0;
		double[] p = new double[size], e = new double[size]; 
		
		for (int i=0; i<size; i++) 
		{
			e[i]=(x[i]*x[i]*roots[2]+x[i]*roots[1]+roots[0]-y[i]);
			p[i]=e[i]/y[i];
			S=S+e[i]*e[i];
		}
		
		double r = lab2Common.countR(x,y);
		
		double q = Math.sqrt(S/size);
		return q;		
	}
	
	static Answer Power (double x[], double y[]) 
	{
		Answer ans = new Answer(x,y);
		for (int i=0; i<x.length; i++) {
			ans.x[i]=Math.log(x[i]);
			ans.y[i]=Math.log(y[i]);
		}
		
		lab2Common.findAB(ans);
		double c = ans.a;
		ans.a=Math.exp(ans.b);
		ans.b=c;
		
		for (int i=0; i<ans.size; i++) {
			ans.y[i]=Math.pow(Math.E, ans.y[i]);
			ans.x[i]=Math.pow(Math.E, ans.x[i]);
			ans.e[i]=((ans.a*Math.pow(ans.x[i],ans.b))-ans.y[i]);
			ans.p[i]=ans.e[i]/ans.y[i];
			ans.S=ans.S+ans.e[i]*ans.e[i];
		}		
		
		ans.r = lab2Common.countR(ans.x,ans.y);
		ans.q = Math.sqrt(ans.S/ans.size);
		return ans;						
	}
	
	static Answer Expone (double x[], double y[]) 
	{
		Answer ans = new Answer(x,y);
		for (int i=0; i<ans.size; i++) {
			ans.x[i]=x[i];
			ans.y[i]=Math.log(y[i]);
		}
		
		lab2Common.findAB(ans);
		
		double c = ans.a;
		ans.a=Math.exp(ans.b);
		ans.b=c;
		
		for (int i=0; i<ans.size; i++) {
			ans.y[i]=Math.pow(Math.E, ans.y[i]);
			ans.e[i]=((ans.a*Math.pow(Math.E, ans.x[i]*ans.b))-ans.y[i]);
			ans.p[i]=ans.e[i]/ans.y[i];
			ans.S=ans.S+ans.e[i]*ans.e[i];
		}		
		
		ans.r = lab2Common.countR(ans.x,ans.y);
		ans.q = Math.sqrt(ans.S/ans.size);
		return ans;		
	}
	
	static Answer Logari (double x[], double y[]) 
	{
		Answer ans = new Answer(x,y);
		for (int i=0; i<x.length; i++) {
			ans.x[i]=Math.log(x[i]);
			ans.y[i]=y[i];
		}
		
		lab2Common.findAB(ans);
		double c = ans.a;
		ans.a=ans.b;
		ans.b=c;
		
		for (int i=0; i<ans.size; i++) {
			ans.x[i]=Math.pow(Math.E, ans.x[i]);
			ans.e[i]=((ans.a+ans.b*Math.log(ans.x[i]))-ans.y[i]);
			ans.p[i]=ans.e[i]/ans.y[i];
			ans.S=ans.S+ans.e[i]*ans.e[i];
		}		
		
		ans.r = lab2Common.countR(ans.x,ans.y);
		ans.q = Math.sqrt(ans.S/ans.size);
		return ans;	
	}
}
