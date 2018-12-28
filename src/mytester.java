

import java.math.BigInteger;

public class mytester {

	public static void main(String[] args) {
		Polynomial p1 = new Polynomial();
		
		
		p1.addTerm(new Term(2, new BigInteger("0")));
		p1.addTerm(new Term(5, new BigInteger("2")));
		p1.addTerm(new Term(0, new BigInteger("1")));
		p1.addTerm(new Term(-2, new BigInteger("2")));
		


		System.out.println(p1);
	}

}
