package lab1;

import java.util.Scanner;

public class lab2 {
	
	static double x[];
	static double y[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k=7;
		x = new double[k];
		String inStr = sc.nextLine();
		String[] inArr = inStr.split(" ");
		for (int i=0;i<k;i++)
		{
			x[i]=Double.parseDouble(inArr[i]);
		}
		
		y = new double[k];
		inStr = sc.nextLine();
		inArr = inStr.split(" ");
		for (int i=0;i<k;i++)
		{
			y[i]=Double.parseDouble(inArr[i]);
		}
			
		/*
		1.1 2.3 3.7 4.5 5.4 6.8 7.5         	
		2.73 5.12 7.74 8.91 10.59 12.75 13.43
		*/
		
		Answer qLin = lab2Meth.Lineal(x, y);
		double qQua = lab2Meth.Quadra(x, y);
		Answer qPow = lab2Meth.Power(x, y);
		Answer qExp = lab2Meth.Expone(x, y);
		Answer qLog = lab2Meth.Logari(x, y);
		System.out.println(qLin.a+" "+qLin.b);
		System.out.println(qLin.S+" "+qLin.q+" Линейка");
		System.out.println(qQua+" Квадрат");
		System.out.println(qPow.S+" "+qPow.q+" Степенная x^a");
		System.out.println(qExp.S+" "+qExp.q+" Экспонента ae^x");
		System.out.println(qLog.S+" "+qLog.q+" Логарифм aln(x)");
		sc.close();
	}
}
