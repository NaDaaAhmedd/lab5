package eg.edu.alexu.csd.datastructure.stack.cs72;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Stack N = new Stack();
		System.out.println("______________________");
		System.out.println("1- Push\r\n" + "2- Pop\r\n" + "3- Peek\r\n" + "4- IsEmpty\n" + "5- Size\r\n" + "6- Show\r\n"
				+ "7- Exit");
		System.out.println("=========================================================");
		System.out.println("Please choose an action:");

		int a;
		a = s.nextInt();
		while (a != 7) {

			if (a == 1) {
				System.out.println("Please choose 1 if letter 2 if Number 3 if String : ");
				int b;
				b = s.nextInt();
				if (b == 1) {
					char n;
					n = s.next().charAt(0);
					N.push(n);
				} else if (b == 2) {
					int c;
					c = s.nextInt();
					N.push(c);
				} else if (b == 3) {
					String m = s.next();
					N.push(m);
				}
			} else if (a == 2) {
				N.pop();
			} else if (a == 3) {
				System.out.println(N.peek());
			} else if (a == 4) {
				System.out.println(N.isEmpty());
			} else if (a == 5) {
				System.out.println(N.size());
			} else if (a == 6) {
				N.show();
			}
			System.out.println("______________________");
			System.out.println("1- Push\r\n" + "2- Pop\r\n" + "3- Peek\r\n" + "4- IsEmpty\n" + "5- Size\r\n"
					+ "6- Show\r\n" + "7- Exit");
			System.out.println("=========================================================");
			System.out.println("Please choose an action:");
			a = s.nextInt();
		}
		s.close();
	}
}

