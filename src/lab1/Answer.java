package lab1;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Answer {

	public double x[],y[],f[],e[],p[],a=0,b=0,c=0,S=0,r=0,q=0;
	int size;
	String desc;
	
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
	
	public void write(String source) {
		if (source.equals("Console")) {
			System.out.println("Параметры функции (a,b,c):"+a+" "+b+" "+c);
			System.out.println("Мера отклонения:"+S);
			System.out.println("Среднеквадратическое отклонение:"+q);}
			else { 
				try (FileWriter writer = new FileWriter(source);) {
					writer.write("Параметры функции (a,b,c):"+new Double(a).toString()+" "+new Double(b).toString()+" "+new Double(c).toString()+"\n");
					writer.write("Мера отклонения:"+new Double(S).toString()+"\n");
					writer.write("Среднеквадратическое отклонение:"+new Double(q).toString()+"\n");
				} catch (IOException e) {
					System.out.println("Проблема с записью в файл!");;
				}
			}
			
		XYSeries series = new XYSeries(desc);
					 
		for(double i = x[0]-5; i < x[size-1]+5; i+=0.1){
			switch (desc) { 
				case "y=ax+b":series.add(i, a*i+b); break;
				case "y=ax^2+bx+c":series.add(i, a*i*i+b*i+c); break;
				case "y=a*x^b":series.add(i, a*Math.pow(i, b)); break;
				case "y=a*e^(bx)":series.add(i, a*Math.pow(Math.E, b*i)); break;
				case "y=a+b*ln(x)":series.add(i, a+b*Math.log(i)); break;
			}
		}
			 
		XYDataset xyDataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory
				.createXYLineChart(desc, "x", "y",
						xyDataset, 
			            PlotOrientation.VERTICAL,
			            true, true, true);
		JFrame frame = new JFrame("Optimal function");
		frame.add(new ChartPanel(chart));
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
