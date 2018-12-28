//Home made test
import java.math.BigInteger;
public class timecomplex{
	public static void main (String[] args) {
		testAddTerm_3();
		testAdd_3();
		testEval_3();
		testEval_4();
		testMultiplyTerm2();
		testMultiplyTerm3();
		testMultiply2();
	}
private static void testAddTerm_3()
{
	/*
	 * Try to add
	 * 4x^4
	 * -4x^4
	 * 0x^0
	 * =0 (empty polynomial)
	 */
	Polynomial p1 = new Polynomial();
	Polynomial p2 = new Polynomial(); // reference polynomial
	p1.addTerm(new Term(4, new BigInteger("4")));
	p1.addTerm(new Term(4, new BigInteger("-4")));
	p1.addTerm(new Term(0, new BigInteger("0")));
	if (p1.size() == 0 && p2.size() == 0 && p1.checkEqual(p2))
	{
		System.out.println("Passed: addTerm() - Test3");
		return;
	}
	System.out.println("Failed: addTerm() - Test3");
	System.out.println("Expected: "+p2 +"\tresult: "+ p1 );
}
//home made test
private static void testAdd_3(){
	/*
	 * 2x^4+x^3+2x^2+1
	 * + 0    (empty polynomial) 
	 * ==2x^4+x^3+2x^2+1  ?
	 */
	Polynomial p1 = new Polynomial();
	Polynomial p2 = new Polynomial();
	p1.addTerm(new Term(2, new BigInteger("2")));
	p1.addTerm(new Term(0, new BigInteger("1")));
	p1.addTerm(new Term(3, new BigInteger("1")));
	p1.addTerm(new Term(4, new BigInteger("2")));
	Polynomial sum = Polynomial.add(p1, p2);
	Polynomial expectedSum = new Polynomial(); //reference
	expectedSum.addTermLast(new Term(4, new BigInteger("2")));
	expectedSum.addTermLast(new Term(3, new BigInteger("1")));
	expectedSum.addTermLast(new Term(2, new BigInteger("2")));
	expectedSum.addTermLast(new Term(0, new BigInteger("1")));
	if (sum != null && expectedSum.checkEqual(sum))
	{
		System.out.println("Passed: add() - Test 3");
		return;
	}
	System.out.println("Failed: add() - Test 3");
	System.out.println("Expected: "+expectedSum +"\tresult: "+ sum );
}
//Home made test for polynomial evaluation
private static void testEval_3(){
	//2x^4+x^3+2x^2+x+2 @x=180
	//(((2x+1)x+2)x+1)x+2
	// ->2 361 64982 11696761 2105416982
	Polynomial p1 = new Polynomial();
	p1.addTermLast(new Term(4, new BigInteger("2")));
	p1.addTermLast(new Term(3, new BigInteger("1")));
	p1.addTermLast(new Term(2, new BigInteger("2")));
	p1.addTermLast(new Term(1, new BigInteger("1")));
	p1.addTermLast(new Term(0, new BigInteger("2")));
	BigInteger result=p1.eval(new BigInteger("180"));
	if (result.compareTo(new BigInteger("2105416982")) == 0)
	{
		System.out.println("Passed: eval() - Test 3");
		return;
	}
	System.out.println("Failed: eval()- Test 3");
	System.out.println("Expected: 2105416982\tresult: "+ result );
}
//Home made test for polynomial evaluation
private static void testEval_4(){
	//654223x^8+987x^3+20000 @x=0
	//(((654223x+987)x)x)x+20000
	// ->654223 987 0 0 0 20000
	Polynomial p1 = new Polynomial();
	p1.addTermLast(new Term(8, new BigInteger("654223")));
	p1.addTermLast(new Term(3, new BigInteger("987")));
	p1.addTermLast(new Term(0, new BigInteger("20000")));
	BigInteger result=p1.eval(new BigInteger("0"));
	if (result.compareTo(new BigInteger("20000")) == 0)
	{
		System.out.println("Passed: eval() - Test 4");
		return;
	}
	System.out.println("Failed: eval()- Test 4");
	System.out.println("Expected: 20000\tresult: "+ result );
}
//Home made test for term multiplication
private static void testMultiplyTerm2()
{
	Polynomial p1 = new Polynomial();
	p1.addTermLast(new Term(2, new BigInteger("2")));
	p1.addTermLast(new Term(0, new BigInteger("1")));
	p1.multiplyTermTest(new Term(0, new BigInteger("0")));
	Polynomial result = new Polynomial();
	if (p1.size() == 0 && p1.checkEqual(result))
	{
		System.out.println("Passed: multiplyTerm2()");
		return;
	}
	System.out.println("Failed: multiplyTerm2()");
	System.out.println("Expected: "+result + "\tresult: "+ p1 );
}
//Home made test for term multiplication
private static void testMultiplyTerm3()
{
	Polynomial p1 = new Polynomial();
	p1.addTermLast(new Term(1, new BigInteger("1")));
	p1.addTermLast(new Term(0, new BigInteger("1")));
	p1.multiplyTermTest(new Term(1, new BigInteger("1")));
	Polynomial result = new Polynomial();
	result.addTermLast(new Term(2, new BigInteger("1")));
	result.addTermLast(new Term(1, new BigInteger("1")));
	if (p1.size() != 0 && p1.checkEqual(result))
	{
		System.out.println("Passed: multiplyTerm3()");
		return;
	}
	System.out.println("Failed: multiplyTerm3()");
	System.out.println("Expected: "+result + "\tresult: "+ p1 );
}
//Home made basic multiplication test (to try when having problems)
private static void testMultiply2()
{
	Polynomial p1 = new Polynomial();
	Polynomial p2 = new Polynomial();
	p1.addTermLast(new Term(1, new BigInteger("1")));
	p1.addTermLast(new Term(0, new BigInteger("1")));
	p2.addTermLast(new Term(1, new BigInteger("1")));
	p2.addTermLast(new Term(0, new BigInteger("1")));
	Polynomial product = Polynomial.multiply(p1, p2);
	Polynomial expectedProduct = new Polynomial();
	expectedProduct.addTermLast(new Term(2, new BigInteger("1")));
	expectedProduct.addTermLast(new Term(1, new BigInteger("2")));
	expectedProduct.addTermLast(new Term(0, new BigInteger("1")));
	if (product != null && product.checkEqual(expectedProduct))
	{
		System.out.println("Passed: multiply2()");
		return;
	}
	System.out.println("Failed: multiply2()");
	System.out.println("Expected: "+expectedProduct + "\tresult: "+ product );
}

}