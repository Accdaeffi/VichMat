package lab1;

import java.util.Scanner;

public class Lab1 {

	static String method;
	static String source;
	static double[] ans;
	public static void main(String[] args) {
		MathMethod mm;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("����� ������� ������� �����: Chord ��� Newton?");
		String s = sc.nextLine();
		method = s.trim();
		if ((!method.equals("Newton"))&&(!method.equals("Chord"))) {System.out.println("����������� �����!"); System.exit(1);} 		
		System.out.println("� ������ ���������� ����� �����: Console ��� File?");
		s = sc.nextLine();
		source = s.trim();
		if (source.equals("File")) {
			System.out.println("��������� ���� � ������� � ���������� ��������� � ������� ��� �������� (� ��������).");
			s = sc.nextLine();
			source = s.trim();
		} else if (!source.equals("Console")) {System.out.println("����������� ��������!"); System.exit(3);} 
		if (method.equals("Chord")) {mm = new Chord(source);}
		else {mm = new Newton(source);} 
		mm.calc();
		System.out.println("� ���� ��������: Console ��� File?");
		s = sc.nextLine();
		source = s.trim();
		if (source.equals("File")) {
			System.out.println("������� �������� ����� (� ��������).");
			s = sc.nextLine();
			source = s.trim();
		} else if (!source.equals("Console")) {System.out.println("����������� ��������!"); System.exit(4);} 
		
		mm.out(source);
		
		sc.close();
	}

}
