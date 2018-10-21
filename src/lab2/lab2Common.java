package lab2;

public class lab2Common {

	static double countR (double[] x, double[] y) { 
		double r=0;
		double xm=0, ym=0;
		for (int i=0;i<x.length;i++) 
		{
			xm=xm+x[i];
			ym=ym+y[i];
		}
		xm=xm/x.length;
		ym=ym/x.length;
		
		double chis=0, znam1=0, znam2=0;
		for (int i=0;i<x.length;i++) 
		{
			chis = chis + (x[i]-xm)*(y[i]-ym);
			znam1 = znam1 + (x[i]-xm)*(x[i]-xm);
			znam2 = znam2 + (y[i]-ym)*(y[i]-ym);
		}
		r = chis/(Math.sqrt(znam1*znam2));
		return r;
	}
	
	
	
	static void findAB (Answer ans) {
		double SX=0,SY=0,SXX=0,SXY=0;
		
		for (int i=0;i<ans.size;i++)
		{
			SX=SX+ans.x[i];
			SY=SY+ans.y[i];
			SXX=SXX+ans.x[i]*ans.x[i];
			SXY=SXY+ans.x[i]*ans.y[i];
		}
		
		ans.a = (SXY*ans.size-SX*SY)/(SXX*ans.size-SX*SX);
		ans.b = (SXX*SY-SX*SXY)/(SXX*ans.size-SX*SX);
		ans.c=0;
	}	
}
