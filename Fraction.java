package main;

import java.util.Objects;

/**
 * A class that creates a proper fraction.
 *
 * @author Alvin John L. Cutay
 * @version 1.0, 03/18/2019
 * @version 2.0, 10/16/2020
 */
public class Fraction {
    private int numerator; // holds the numerator of this fraction
    private int denominator; // holds the denominator of this fraction

    /**
     * A constructor that allows a Fraction with an equivalent numeric value of zero to
     * be created.
     **/
    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    /**
     * A constructor that creates a Fraction with the specified whole number as its initial value.
     *
     * @param wholeNumVal the value of the numerator.
     */
    public Fraction(int wholeNumVal) {
        this.numerator = wholeNumVal;
        denominator = 1;
    }

    /**
     * A constructor that creates a Fraction using the explicit numerator and denominator values.
     *
     * @param numerator the value of the numerator
     * @param denominator the value of the denominator
     */
    public Fraction(int numerator, int denominator) {
        double signNum = Math.signum(numerator);
        double signDen = Math.signum(denominator);

        if (signDen == -1) {
            if (signNum == -1 || signNum == 1) {
                numerator *= -1;
                denominator *= -1;

                this.numerator = numerator;
                this.denominator = denominator;
            }
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    /**
     * Returns the mix faction equivalent of an improper fraction.
     *
     * @return the mix fraction
     */
    public MixFraction toMixFraction () {
        return new MixFraction(0, new Fraction(getNumerator(), getDenominator()));
    }

    /**
     * Accessor/Getter Method
     * Returns the value of the numerator of this fraction.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Accessor/Getter Method
     * Returns the value of the denominator of this fraction.
     */
    public int getDenominator() {
        return denominator;
    }


    /**
     * Mutator/Setter Method
     * Sets the value of the numerator of this fraction.
     *
     * @param numerator the value of the numerator to be set
     */
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    /**
     * Mutator/Setter Method
     * Sets the value of the denominator of this fraction.
     *
     * @param denom the value of the denominator to be set
     */
    public void setDenominator(int denom) {
        if (Math.signum(denom) == -1) {
            numerator *= -1;
            denominator = denom * -1;
        } else {
            denominator = denom;
        }

    }

    /**
     * Returns a string form of the fraction following the format numerator/denominator.
     **/
    public String toString() {  // this is an overridden method from the Object class
        if (Math.signum(getNumerator()) != Math.signum(getDenominator())) {
            if (Math.signum(getDenominator()) == -1) {
                return getNumerator() * -1 + "/" + getDenominator() * -1;
            }
        }
        return getNumerator() + "/" + getDenominator();
    }

    /**
     * Returns the equivalent value of the fraction in double format.
     */
    public double toDouble() {
        return (double) numerator / denominator;
    }

    /**
     * Reduces this fraction to its simplest form.
     */
    public void reduce() {
        int gcd = computeGCD(); // determine the greatest common divisor of numerator and denominator
        int newNumerator = numerator / gcd; //compute newNumerator, the numerator of the simplest form of this fraction
        int newDenominator = denominator / gcd; //compute newDenominator, the denominator of the simplest form of this fraction
        setNumerator(newNumerator); // set the value of the new numerator for this fraction
        setDenominator(newDenominator); // set the value of the new denominator for this fraction
    }

    /**
     * Returns the sum of this fraction and another fraction.
     *
     * @param other the addend to be added to first operand
     * @return the sum of two fractions
     */
    public Fraction add(Fraction other) {
        Fraction result = new Fraction(); // create result fraction, the difference of two fractions
        int den = denominator * other.getDenominator();
        int num = den / denominator * numerator + den / other.getDenominator() * other.getNumerator();
        result.setNumerator(num);
        result.setDenominator(den);
        result.reduce();
        return result;
    }

    /**
     * Returns the difference of this fraction and another fraction.
     *
     * @param other the subtrahend to be subtracted to the first operand
     * @return the difference of two fractions
     */
    public Fraction subtract(Fraction other) {
        Fraction result = new Fraction(); // create result fraction, the difference of two fractions
        int den = denominator * other.getDenominator(); // compute den, the denominator of the difference
        int num = den / denominator * numerator - den / other.getDenominator() * other.getNumerator(); // compute num, the numerator of the difference
        result.setNumerator(num); // set the value of the new numerator of the result fraction
        result.setDenominator(den); // set the value of the new denominator of the result fraction
        result.reduce(); // reduce the result fraction
        return result;
    }

    /**
     * Returns the product of this fraction and another fraction.
     *
     * @param other the factor to be multiplied to the first operand
     * @return the product of two fractions
     */
    public Fraction multiply(Fraction other) {
        Fraction result = new Fraction(); // create result fraction, the product of two fractions
        int num = numerator * other.getNumerator(); // compute num, the numerator of the product
        int den = denominator * other.getDenominator(); // compute den, the denominator of the difference
        result.setNumerator(num); // set the value of the new numerator of the result fraction
        result.setDenominator(den); // set the value of the new denominator of the result fraction
        result.reduce(); // reduce the result
        return result;
    }

    /**
     * Returns the quotient of this fraction and another fraction.
     *
     * @param other the divisor to be divided to the first operand
     * @return the quotient of two fractions
     */
    public Fraction divide(Fraction other) {
        Fraction result = new Fraction(); // create result fraction, the quotient of two fractions
        int num = numerator * other.getDenominator(); // compute num, the numerator of the quotient
        int den = denominator * other.getNumerator(); // compute den, the denominator of the quotient
        result.setNumerator(num); // set the value of the new numerator of the result fraction
        result.setDenominator(den); // set the value of the new denominator of the result fraction
        result.reduce(); // reduce the result
        return result;
    }

    /**
     * Computes the greatest common divisor of the numerator and denominator.
     */
    public int computeGCD() {
        int remainder;
        int operand1 = numerator;
        int operand2 = denominator;
        do {
            remainder = operand1 % operand2;
            if (remainder == 0) {
                return operand2;
            }
            operand1 = operand2;
            operand2 = remainder;
        } while (remainder != 0);

        return operand2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator &&
                denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
