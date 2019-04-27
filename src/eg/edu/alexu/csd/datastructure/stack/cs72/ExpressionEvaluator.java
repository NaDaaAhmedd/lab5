package eg.edu.alexu.csd.datastructure.stack.cs72;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Math;

import org.junit.jupiter.api.Test;

public class ExpressionEvaluator {
	/**
	 * this method converts an infix expression to postfix
	 * 
	 * @param expression is an infix expression
	 * @return a string represents the postfix expression
	 */
	public String infixToPostfix(String expression) {
		Stack check = new Stack();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(') {
				check.push('(');
			}
			if (expression.charAt(i) == ')') {
				check.pop();
			}
		}

		if (!check.isEmpty()) {
			System.out.println("expression invalid!");
			return null;
		}
		Stack s = new Stack();
		Stack t = new Stack();
		String result = new String();

		for (int i = 0; i < expression.length(); i++) {
			if (Character.isDigit(expression.charAt(i)) || Character.isLetter(expression.charAt(i))) {
				result += expression.charAt(i);
			} else if (expression.charAt(i) == '(') {
				s.push('(');
				t.push(-1);
			} else if (expression.charAt(i) == '-') {
				result += " ";
				if (s.isEmpty() || (int) t.peek() == -1) {
					s.push('-');
					t.push(1);
				} else {
					int f1 = 0;
					while (!s.isEmpty()) {
						if ((int) t.peek() == -1) {
							f1 = 1;
							s.push('-');
							t.push(1);
							break;
						}
						result += s.pop();
						t.pop();
					}
					if (f1 == 0) {
						s.push('-');
						t.push(1);
					}
				}
			} else if (expression.charAt(i) == '+') {
				result += " ";
				if (s.isEmpty() || (int) t.peek() == -1) {
					s.push('+');
					t.push(1);
				} else {
					int f2 = 0;
					while (!s.isEmpty()) {
						if ((int) t.peek() == -1) {
							f2 = 1;
							s.push('+');
							t.push(1);
							break;
						}
						result += s.pop();
						t.pop();
					}
					if (f2 == 0) {
						s.push('+');
						t.push(1);
					}
				}
			} else if (expression.charAt(i) == '*') {
				result += " ";
				if (s.isEmpty() || (int) t.peek() < 2) {
					s.push('*');
					t.push(2);
				} else {
					int f3 = 0;
					while (!s.isEmpty()) {
						if ((int) t.peek() < 2) {
							f3 = 1;
							s.push('/');
							t.push(2);
							break;
						}
						if ((int) t.peek() == -1) {
							f3 = 1;
							s.push('*');
							t.push(2);
							break;
						}
						result += s.pop();
						t.pop();
					}
					if (f3 == 0) {
						s.push('*');
						t.push(2);
					}
				}
			} else if (expression.charAt(i) == '/') {
				result += " ";
				if (s.isEmpty() || (int) t.peek() < 2) {
					s.push('/');
					t.push(2);

				} else {
					int f4 = 0;
					while (!s.isEmpty()) {
						if ((int) t.peek() < 2) {
							f4 = 1;
							s.push('/');
							t.push(2);
							break;
						}
						if ((int) t.peek() == -1) {
							f4 = 1;
							s.push('/');
							t.push(2);
							break;
						}
						result += s.pop();
						t.pop();
					}
					if (f4 == 0) {
						s.push('/');
						t.push(2);
					}
				}
			}

			else if (expression.charAt(i) == ')') {
				while (!s.isEmpty()) {
					if ((int) t.peek() == -1) {
						s.pop();
						t.pop();
						break;
					}
					result += s.pop();
					t.pop();
				}
			}
		}
		while (!s.isEmpty()) {
			result += " ";
			result += s.pop();
		}
		return result;
	}

	/**
	 * this method evaluate a postfix numeric expression, with a single space
	 * separator
	 * 
	 * @param expression postfix expression
	 * @return the expression evaluated value
	 */
	public int evaluate(String expression) {
		Stack n = new Stack();
		for (int i = 0; i < expression.length(); i++) {
			if (Character.isDigit(expression.charAt(i))) {
				int m = 0;
				int j = 0;
				int z = 0;
				int q = 0;
				int sum = 0;
				z = q = i;
				while (Character.isDigit(expression.charAt(z)) && z < expression.length()) {
					j++;
					z++;
				}

				int[] arr = new int[j];
				for (int a = j; a > 0; a--) {
					arr[j - a] = Character.getNumericValue(expression.charAt(q));
					q++;
				}

				for (int a = 0; a < j / 2; a++) {
					int tmp = arr[j - a - 1];
					arr[j - a - 1] = arr[a];
					arr[a] = tmp;
				}

				for (int a = 0; a < j; a++) {
					sum += arr[a] * (int) Math.pow(10, m);
					m++;
				}
				
					n.push(sum);
				i = i + j - 1;
			}

			else if (expression.charAt(i) == '+' || (expression.charAt(i) == '-' && i + 1 >= expression.length())
					|| (expression.charAt(i) == '-' && !Character.isDigit(expression.charAt(i + 1)))
					|| expression.charAt(i) == '*' || expression.charAt(i) == '/') {
				int val1 = (int) n.pop();
				int val2 = (int) n.pop();
				switch (expression.charAt(i)) {
				case '+':
					n.push(val2 + val1);
					break;
				case '-':
					n.push(val2 - val1);
					break;
				case '*':
					n.push(val2 * val1);
					break;
				case '/':
					n.push(val2 / val1);
					break;
				}

			} else if (expression.charAt(i) == '-') {
				if (i + 1 < expression.length()) {
					if (Character.isDigit(expression.charAt(i + 1))) {
						int m = 0;
						int j = 0;
						int z = 0;
						int q = 0;
						int sum = 0;
						z = q = i + 1;
						while (Character.isDigit(expression.charAt(z)) && z < expression.length()) {
							j++;
							z++;
						}

						int[] arr = new int[j];
						for (int a = j; a > 0; a--) {
							arr[j - a] = Character.getNumericValue(expression.charAt(q));
							q++;
						}

						for (int a = 0; a < j / 2; a++) {
							int tmp = arr[j - a - 1];
							arr[j - a - 1] = arr[a];
							arr[a] = tmp;
						}

						for (int a = 0; a < j; a++) {
							sum += arr[a] * (int) Math.pow(10, m);
							m++;
						}
						n.push(-sum);

						i = i + j;
					}
				}
			}

		}

		return (int) n.pop();
	}
}
class JunitTest {

	@Test
	void test() {
		ExpressionEvaluator n = new ExpressionEvaluator();

		String n1 = n.infixToPostfix("6/2-3+4*2");
		assertEquals("6 2 /3 -4 2 * +", n1);

		int n2 = n.evaluate("6 2 /3 - 4 2 * +");
		assertEquals(8, n2);

		String n3 = n.infixToPostfix("30+10*5/2");
		assertEquals("30 10 5 *2 / +", n3);

		int n4 = n.evaluate("30 10 5 *2 / +");
		assertEquals(55, n4);

		String n5 = n.infixToPostfix("6/(2-3+4)*2+10");
		assertEquals("6 2 3 -4+ /2 *10 +", n5);

		int n6 = n.evaluate("6 2 3 - 4 + /2 *10 +");
		assertEquals(14, n6);

		String n7 = n.infixToPostfix("13+20*(5+2*3)-2");
		assertEquals("13 20 5 2 3*+ *+2 -", n7);

		int n8 = n.evaluate("13 20 5 2 3*+ *+2 -");
		assertEquals(231, n8);

		String n9 = n.infixToPostfix("4-55");
		assertEquals("4 55 -", n9);

		int n10 = n.evaluate("100 -20 +");
		assertEquals(80, n10);

		String n11 = n.infixToPostfix("13+20*((5+2)*3)-2");
		assertEquals("13 20 5 2+ 3* *+2 -", n11);

		int n12 = n.evaluate("-100 -200 *");
		assertEquals(20000, n12);

		String n13 = n.infixToPostfix("(a / (b - c + d)) * (e - a) * c");
		assertEquals("a b c -d+/ e a- *c *", n13);
		
		int n14 = n.evaluate("2500 -5 /");
		assertEquals(-500, n14);
	}

}

interface IExpressionEvaluator {
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic or numeric term)
*
* @param expression
* infix expression
* @return postfix expression
*/
public String infixToPostfix(String expression);
/**
* Evaluate a postfix numeric expression, with a single space separator
*
* @param expression
* postfix expression
* @return the expression evaluated value
*/
public int evaluate(String expression);
}