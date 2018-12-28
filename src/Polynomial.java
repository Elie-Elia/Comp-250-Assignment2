/**
 * Name: Elie Elia
 * Student ID: 260759306
 */

import java.math.BigInteger;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t) {
	BigInteger zero = new BigInteger("0");
	if (t.getExponent() >= 0 ) {
		if (!(t.getCoefficient().equals(zero))) {
		if (polynomial.isEmpty()) {
		polynomial.addFirst(t);
	} else {
		int m = 0;
		boolean isitadded = false;
		
		for (Term currentTerm : polynomial) {
			if(t.getExponent() > currentTerm.getExponent()) {
				polynomial.add(m, t);
				isitadded = true;
				break;} 
			
			else if(t.getExponent() == currentTerm.getExponent()) {
				if(t.getCoefficient().equals(new BigInteger ("-1").multiply((currentTerm.getCoefficient())))) {
					polynomial.remove(m);
					isitadded=true;
					break;}
				
					else {	
				BigInteger total = new BigInteger("0");
				total = t.getCoefficient().add(currentTerm.getCoefficient());
				currentTerm.setCoefficient(total);
				isitadded = true;
				break;}}
			m++;
		}
		if(isitadded == false) {
			polynomial.addLast(t);
		}
	}
	}
	}
	}
	
		/**** ADD CODE HERE ****/
		
		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
		/*
		for (Term currentTerm: polynomial)
		{
			// The for loop iterates over each term in the polynomial!!
			// Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
		}
		*/
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
		{
		Polynomial copy1 = p1.deepClone();
		Polynomial copy2 = p2.deepClone();
		Polynomial Result = new Polynomial();
		while(!copy1.polynomial.isEmpty() || !copy2.polynomial.isEmpty()) {
			if(copy1.polynomial.isEmpty()) {
				
				Result.addTermLast(new Term(copy2.polynomial.get(0).getExponent(), copy2.polynomial.get(0).getCoefficient()));
				copy2.polynomial.removeFirst();
				
			}else if(copy2.polynomial.isEmpty()){
				
				Result.addTermLast(new Term(copy1.polynomial.get(0).getExponent(), copy1.polynomial.get(0).getCoefficient()));
				copy1.polynomial.removeFirst();
				
			}else if(copy1.polynomial.get(0).getExponent() == copy2.polynomial.get(0).getExponent() ) {
				
				Result.addTermLast(new Term(copy1.polynomial.get(0).getExponent(), copy1.polynomial.get(0).getCoefficient().add(copy2.polynomial.get(0).getCoefficient())));
				copy2.polynomial.removeFirst();
				copy1.polynomial.removeFirst();
				
			}else if(copy1.polynomial.get(0).getExponent()> copy2.polynomial.get(0).getExponent() ) {
				
				Result.addTermLast(new Term(copy1.polynomial.get(0).getExponent(), copy1.polynomial.get(0).getCoefficient()));
				copy1.polynomial.removeFirst();
				
			}else {
				Result.addTermLast(new Term(copy2.polynomial.get(0).getExponent(), copy2.polynomial.get(0).getCoefficient()));
				copy2.polynomial.removeFirst();
			}
		}
		
		return Result;
	}
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		if (t.getExponent() >= 0) {
		if(t.getCoefficient().equals(new BigInteger("0"))) {
			polynomial.clear();
		}else {
		BigInteger coeff = new BigInteger("0");
		int exponent=0;
		for (Term currentTerm:polynomial) {
			exponent=(t.getExponent()+currentTerm.getExponent());
			coeff=(t.getCoefficient().multiply(currentTerm.getCoefficient()));
			currentTerm.setCoefficient(coeff);
			currentTerm.setExponent(exponent);
		}}}}
	
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		
		Polynomial p1clone = p1.deepClone();
		Polynomial p2clone = p2.deepClone();
		Polynomial p2multiplied = new Polynomial();
		
		for (int i=0;i<p1clone.size();i++) {
			p2clone.multiplyTerm(p1clone.polynomial.get(i));
			p2multiplied =add (p2clone, p2multiplied);
			p2clone=p2.deepClone();
		
		}
		
		return p2multiplied;
	}
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
        if(!(this.polynomial.isEmpty())){
        int sizeofarray = this.getTerm(0).getExponent()+1;
		BigInteger[] arraypolynomial = new BigInteger[sizeofarray];
	
		arraypolynomial[0] = this.getTerm(0).getCoefficient();
		
		for(int i = 1; i<sizeofarray; i++) {
			arraypolynomial[i] = new BigInteger("0");}
		
		Polynomial copypoly = this.deepClone();
		copypoly.polynomial.removeFirst();
		while(!(copypoly.polynomial.isEmpty())) {
			BigInteger coefftoadd = copypoly.getTerm(0).getCoefficient();
			int expforposition = copypoly.getTerm(0).getExponent();
			copypoly.polynomial.removeFirst();
			arraypolynomial[this.getTerm(0).getExponent() - expforposition] = coefftoadd;
					
		}
     	
        BigInteger horner = arraypolynomial[0];
    	
        BigInteger multiplied = new BigInteger("0");
        	
        for (int i=1; i<arraypolynomial.length; i++) {
        	multiplied = (horner.multiply(x));
        	horner = multiplied.add(arraypolynomial[i]);
        }
        return horner;}
        else {
        	return (new BigInteger("0"));
        }
}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
