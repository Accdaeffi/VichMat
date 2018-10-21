package lab2;

public class lab2Meth {

	static Answer Lineal(double x[], double y[]) {
		
		Answer ans = new Answer(x,y);
		
		lab2Common.findAB(ans);
		
		for (int i=0; i<ans.size; i++) 
		{
			ans.e[i]=((ans.x[i]*ans.a+ans.b)-ans.y[i]);
			ans.p[i]=ans.e[i]/ans.y[i];
			ans.S=ans.S+ans.e[i]*ans.e[i];
		}		
		
		ans.r = lab2Common.countR(x,y);
		
		ans.q = Math.sqrt(ans.S/ans.size);
		return ans;		
	}
	
	static Answer Quadra(double x[], double y[]) {
		
		Answer ans = new Answer(x,y);
		
		double SX=0,SXX=0,SXXX=0,SXXXX=0,SY=0,SXY=0,SXXY=0;
		
		for (int i=0;i<ans.size;i++)
		{
			SX=SX+x[i];
			SY=SY+y[i];
			SXX=SXX+x[i]*x[i];
			SXY=SXY+x[i]*y[i];
			SXXX=SXXX+x[i]*x[i]*x[i];
			SXXXX=SXXXX+x[i]*x[i]*x[i]*x[i];
			SXXY=SXXY+x[i]*x[i]*y[i];
		}
		
		
		double[][] equat = {{ans.size, SX, SXX, SY}, {SX, SXX, SXXX, SXY}, {SXX, SXXX, SXXXX, SXXY}};
		
		GaussMatrix gm = new GaussMatrix(equat);
		gm = gm.triangleMatrix();
		double[] roots = gm.roots();
		ans.a=roots[2]; ans.b=roots[1]; ans.c=roots[0];
		
		for (int i=0; i<ans.size; i++) 
		{
			ans.e[i]=(x[i]*x[i]*ans.a+x[i]*ans.b+ans.c-ans.y[i]);
			ans.p[i]=ans.e[i]/ans.y[i];
			ans.S=ans.S+ans.e[i]*ans.e[i];
		}
		
		ans.r = lab2Common.countR(ans.x,ans.y);
		
		ans.q = Math.sqrt(ans.S/ans.size);
		return ans;		
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
