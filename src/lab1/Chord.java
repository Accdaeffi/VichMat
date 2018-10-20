package lab1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chord extends MathMethod {
	
	
	static int iterationCount=0;
	static double param1=0, param2=0, e=0, root=0;
	static Scanner sc;
	
	public Chord(String source) {
		if (source.equals("Console")) {System.out.println("Введите 3 числа: левую границу диапозона, правую границу диапозона и погрешность вычисления."); sc = new Scanner(System.in);}
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
		String s = sc.nextLine();
		String[] params = s.split(" ");
		try {
			param1=Double.parseDouble(params[0]);
			param2=Double.parseDouble(params[1]);
			e=Double.parseDouble(params[2]);}
		catch (NumberFormatException err) {
			System.out.println("Кривые данные!"); System.exit(0);}
	}

	public void calc() {
		try {
			root = findRoot(param1,param2, e);
		}
		catch (NoRootInDiapasonException err) {
			System.out.println("Неверные данные!"); System.exit(0);
		} 
			
	}		
			
	public void out(String source) {	
		if (source.equals("Console")) {
		System.out.println(root);
		System.out.println(func(root));
		System.out.println(iterationCount);}
		else {
			try (FileWriter writer = new FileWriter(source);) {
				writer.write(new Double(root).toString()+"\n");
				writer.write(new Double(func(root)).toString()+"\n");
				writer.write(new Integer(iterationCount).toString()+"\n");
			} catch (IOException e) {
				System.out.println("Проблема с записью в файл!");;
			}
		}
		
		XYSeries series = new XYSeries("(x^3)-(4.5*x^2)-(9.21*x)-0.383");
				 
		for(double i = param1; i < param2; i+=0.1){
			series.add(i, func(i));
		}
			 
		XYDataset xyDataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory
				.createXYLineChart("y = (x^3)-(4.5*x^2)-(9.21*x)-0.383", "x", "y",
						xyDataset, 
			            PlotOrientation.VERTICAL,
			            true, true, true);
		JFrame frame = new JFrame("MinimalStaticChart");
		frame.add(new ChartPanel(chart));
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static double func(double x) {
		return x*x*x-4.5*x*x-9.21*x-0.383;
	}
	
	public static double findRoot(double a, double b, double e) throws NoRootInDiapasonException {
		if (func(a)<func(b)) {double c=a; a=b; b=c;}
		if (((func(b)>0)&&(func(a)>0))||((func(b)<0)&&(func(a)<0))) {throw new NoRootInDiapasonException();}
		
		while ((Math.abs(func(b))>e)&&(Math.abs(func(a))>e)) {
			iterationCount++;
			System.out.println(iterationCount+") "+a+" "+b);
			//double c = a - (a-b)/(func(a)-func(b))*func(a);
			double c = (a*func(b)-b*func(a))/(func(b)-func(a));
			if (Math.abs(func(c)-(e))==0) return c; else 
			if (func(c)>0) a=c; else b=c;  
		}
		if (Math.abs(func(b))>2*e) return a; else return b;
	}	
}

