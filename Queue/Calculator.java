import java.io.*;

class charStack {
	private int maxSize;
	private char[] stackArray;
	private int top;

//--------------------------------------------------------------
	public charStack(int s) // constructor
	{
		maxSize = s;
		stackArray = new char[maxSize];
		top = -1;
	}

//--------------------------------------------------------------
	public void push(char j) // put item on top of stack
	{
		stackArray[++top] = j;
	}

//--------------------------------------------------------------
	public char pop() // take item from top of stack
	{
		return stackArray[top--];
	}

//--------------------------------------------------------------
	public char peek() // peek at top of stack
	{
		return stackArray[top];
	}

//--------------------------------------------------------------
	public boolean isEmpty() // true if stack is empty
	{
		return (top == -1);
	}

//-------------------------------------------------------------
	public int size() // return size
	{
		return top + 1;
	}

//--------------------------------------------------------------
	public char peekN(int n) // return item at index n
	{
		return stackArray[n];
	}

//--------------------------------------------------------------
	public void displayStack(String s) {
		System.out.print(s);
		System.out.print("Stack (bottom-->top): ");
		for (int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.print(' ');
		}
		System.out.println("");
	}
//--------------------------------------------------------------
} // end class StackX
////////////////////////////////////////////////////////////////

class noStack {
	private int maxSize;
	private long[] stackArray;
	private int top;

//--------------------------------------------------------------
	public noStack(int size) // constructor
	{
		maxSize = size;
		stackArray = new long[maxSize];
		top = -1;
	}

//--------------------------------------------------------------
	public void push(long j) // put item on top of stack
	{
		stackArray[++top] = j;
	}

//--------------------------------------------------------------
	public long pop() // take item from top of stack
	{
		return stackArray[top--];
	}

//--------------------------------------------------------------
	public long peek() // peek at top of stack
	{
		return stackArray[top];
	}

//--------------------------------------------------------------
	public boolean isEmpty() // true if stack is empty
	{
		return (top == -1);
	}

//--------------------------------------------------------------
	public boolean isFull() // true if stack is full
	{
		return (top == maxSize - 1);
	}

//--------------------------------------------------------------
	public int size() // return size
	{
		return top + 1;
	}

//--------------------------------------------------------------
	public long peekN(int n) // peek at index n
	{
		return stackArray[n];
	}

//--------------------------------------------------------------
	public void displayStack(String s) {
		System.out.print(s);
		System.out.print("Stack (bottom-->top): ");
		for (int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.print(' ');
		}
		System.out.println("");
	}
//--------------------------------------------------------------
}

class InToPost // infix to postfix conversion
{
	private charStack theStack;
	private String input;
	private String output = "";
	private String number = "";

//--------------------------------------------------------------
	public InToPost(String in) // constructor
	{
		input = in;
		int stackSize = input.length();
		theStack = new charStack(stackSize);
	}

//--------------------------------------------------------------
	public String doTrans() // do translation to postfix
	{
		for (int j = 0; j < input.length(); j++) // for each char
		{
			char ch = input.charAt(j); // get it
			theStack.displayStack("For " + ch + " "); // *diagnostic*
			switch (ch) {
			case '+': // it's + or -
			case '-':
				if (!(number.contentEquals("")))
					output = output + '(' + number + ')';
				number = "";
				gotOper(ch, 1); // go pop operators
				break; // (precedence 1)
			case '*': // it's * or /
			case '/':
				if (!(number.contentEquals("")))
					output = output + '(' + number + ')';
				number = "";
				gotOper(ch, 2); // go pop operators
				break; // (precedence 2)
			case '(': // it's a left paren
				theStack.push(ch); // push it
				break;
			case ')': // it's a right paren
				if (!(number.contentEquals("")))
					output = output + '(' + number + ')';
				number = "";
				gotParen(ch); // go pop operators
				break;
			default: // must be an operand
				number = number + ch; // write it to output
				if (j == input.length() - 1)
					if (!(number.contentEquals("")))
						output = output + '(' + number + ')';
				break;
			} // end switch
		} // end for
		while (!theStack.isEmpty()) // pop remaining opers
		{
			theStack.displayStack("While "); // *diagnostic*
			output = output + theStack.pop(); // write to output
		}
		theStack.displayStack("End   "); // *diagnostic*
		return output; // return postfix
	} // end doTrans()
//--------------------------------------------------------------

	public void gotOper(char opThis, int prec1) { // got operator from input
		while (!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if (opTop == '(') // if it's a '('
			{
				theStack.push(opTop); // restore '('
				break;
			} else // it's an operator
			{
				int prec2; // precedence of new op

				if (opTop == '+' || opTop == '-') // find new op prec
					prec2 = 1;
				else
					prec2 = 2;
				if (prec2 < prec1) // if prec of new op less
				{ // than prec of old
					theStack.push(opTop); // save newly-popped op
					break;
				} else // prec of new not less
					output = output + opTop; // than prec of old
			} // end else (it's an operator)
		} // end while
		theStack.push(opThis); // push new operator
	} // end gotOp()
//--------------------------------------------------------------

	public void gotParen(char ch) { // got right paren from input
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			if (chx == '(') // if popped '('
				break; // we're done
			else // if popped operator
				output = output + chx; // output it
		} // end while
	} // end popOps()
//--------------------------------------------------------------
} // end class InToPost

class ParsePost {
	private noStack noStack;
	private String input;
	private String number;

//--------------------------------------------------------------
	public ParsePost(String s) {
		input = s;
	}

//--------------------------------------------------------------
	public long doParse() {
		noStack = new noStack(20); // make new stack
		char ch;
		int j;
		long num1, num2, interAns;

		for (j = 0; j < input.length(); j++) // for each char,
		{
			ch = input.charAt(j); // read from input
			noStack.displayStack("" + ch + " "); // *diagnostic*
			if (ch >= '0' && ch <= '9') // if it's a number
				number = number + ch;
			// theStack.push((int) (ch - '0')); // push it
			else if (ch == '(')
				number = "";
			else if (ch == ')')
				noStack.push(Long.parseLong(number));
			else // it's an operator
			{
				num2 = noStack.pop(); // pop operands
				num1 = noStack.pop();
				switch (ch) // do arithmetic
				{
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/':
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				} // end switch
				noStack.push(interAns); // push result
			} // end else
		} // end for
		interAns = noStack.pop(); // get answer
		return interAns;
	} // end doParse()
} // end class ParsePost

class CalCApp {
	public static void main(String[] args) throws IOException {
		String input, output;
		long result;
		while (true) {
			System.out.print("Enter infix: ");
			System.out.flush();
			input = getString(); // read a string from kbd
			if (input.equals("")) // quit if [Enter]
				break;
			// make a translator
			InToPost theTrans = new InToPost(input);
			output = theTrans.doTrans(); // do the translation
			System.out.println("Postfix is " + output + '\n');
			ParsePost aParser = new ParsePost(output);
			result = aParser.doParse(); // do the evaluation
			System.out.println("Evaluates to " + result);
		} // end while
	} // end main()
//--------------------------------------------------------------

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
//--------------------------------------------------------------
} // end class InfixApp
