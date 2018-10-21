package lab2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class lab2 {
	
	static double x[];
	static double y[];
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		Scanner bu = sc;
		String s, source;
		System.out.println("Откуда информацию брать будем: Console или File?");
		s = sc.nextLine();
		source = s.trim();
		if (source.equals("File")) {
			System.out.println("Поместите файл с данными в директорию программы и введите его название (с форматом).");
			s = sc.nextLine();
			source = s.trim();
		} else if (!source.equals("Console")) {System.out.println("Неизвестный источник!"); System.exit(0);} 
		
		
		
		if (source.equals("Console")) {System.out.println("Введите на трёх строчках: размер массива, массив X и массив Y"); sc = new Scanner(System.in);} 
		else {
			FileReader fr;
			try {
				fr = new FileReader(source);
				sc = new Scanner(fr);
			} catch (FileNotFoundException e) {
				System.out.println("Файл не найден!");
				System.exit(0);
			}
		}
		String[] inArr = sc.nextLine().split(" ");
		int k = Integer.parseInt(inArr[0]);
		x = new double[k];
		y = new double[k];
		try {
			inArr = sc.nextLine().split(" ");
			for (int i=0;i<k;i++)
			{
				x[i]=Double.parseDouble(inArr[i]);
			}
			inArr = sc.nextLine().split(" ");
			for (int i=0;i<k;i++)
			{
				y[i]=Double.parseDouble(inArr[i]);
			}
		}
		catch (NumberFormatException err) {
			System.out.println("Кривые данные!"); System.exit(0);}	
		
		/*
		14
		0.1 0.3 0.6 0.9 1.2 1.5 1.8 2.1 2.4 2.7 3 3.3 3.6 3.9         	
		0.23 0.64 1.32 1.87 2.45 3.11 3.95 4.67 5.13 5.79 6.45 7.32 7.84 8.55
		*/
		
		Answer qLin = lab2Meth.Lineal(x, y); qLin.desc="y=ax+b";
		Answer qQua = lab2Meth.Quadra(x, y); qQua.desc="y=ax^2+bx+c";
		Answer qPow = lab2Meth.Power(x, y);  qPow.desc="y=a*x^b";
		Answer qExp = lab2Meth.Expone(x, y); qExp.desc="y=a*e^(bx)";
		Answer qLog = lab2Meth.Logari(x, y); qLog.desc="y=a+b*ln(x)";
		
		Answer best=qLin;
		if (best.q>qQua.q) best=qQua;
		if (best.q>qPow.q) best=qPow;
		if (best.q>qExp.q) best=qExp;
		if (best.q>qLog.q) best=qLog;
		
		sc = bu;
		System.out.println("А куда выводить: Console или File?");
		s = bu.nextLine();
		source = s.trim();
		if (source.equals("File")) {
			System.out.println("Введите название файла (с форматом).");
			s = bu.nextLine();
			source = s.trim();
		} else if (!source.equals("Console")) {System.out.println("Неизвестный источник!"); System.exit(0);} 
		
		best.write(source);
		
		sc.close();
	}
}
