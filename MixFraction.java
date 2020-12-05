package main;

/**
 * This is a subclass of Fraction class that creates a mix fraction.
 *
 * @author Alvin John L. Cutay
 * @version 1.0, 03/18/2019
 * @version 2.0, 10/16/2020
 */
public class MixFraction extends Fraction {
    private int whole; // hold the whole number of this mix fraction

    /**
     * Class constructor with no parameter/s and sets a Mixfraction with a
     * numeric value equal to zero.
     */
    public MixFraction () {
        whole = 0;
        super.setNumerator(0);
        super.setDenominator(1);
    }

    /**
     *  Class constructor specifying the whole number and fraction part
     *  of the object to create.
     *
     * @param whole the whole number of the mix fraction
     * @param fraction the fraction part of the mix fraction
     */
    public MixFraction (int whole, Fraction fraction) {
        Fraction whle = new Fraction(whole);
        Fraction result;
        double signWhole = Math.signum(whole);
        double signNum = Math.signum(fraction.getNumerator());
        if (signWhole == -1 && signNum != -1) {
            int num = fraction.getNumerator() * -1;
            int den = fraction.getDenominator();
            this.whole = whole + num / den;
            super.setNumerator(fraction.getNumerator() % den);
            super.setDenominator(den);
            return;
        } else if (signWhole == -1 && signNum == -1) {
            result = whle.add(fraction);
            this.whole = result.getNumerator() / result.getDenominator();
            super.setNumerator((result.getNumerator() % result.getDenominator()) * -1);
            super.setDenominator(result.getDenominator());
            return;
        } else if (whole == 0 && signNum == -1) {
            this.whole = fraction.getNumerator() / fraction.getDenominator();
            super.setNumerator((fraction.getNumerator() % fraction.getDenominator()) * -1);
            super.setDenominator(fraction.getDenominator());
            return;
        }
        result = whle.add(fraction);
        this.whole = result.getNumerator() / result.getDenominator();
        super.setNumerator(result.getNumerator() % result.getDenominator());
        super.setDenominator(result.getDenominator());
    }

    /**
     * Class constructor specifying the whole number, numerator, and
     * denominator of the object to create.
     *
     * @param whole the whole number of the mix fraction
     * @param numerator the numerator of the mix fraction
     * @param denominator the denominator of the mix fraction
     */
    public MixFraction (int whole, int numerator, int denominator) {
        this (whole, new Fraction(numerator, denominator));
    }

    /**
     * Class constructor accepting fraction object and sets the whole part to 0.
     *
     * @param fraction the fraction of the mix fraction
     */
    public MixFraction (Fraction fraction) {
        this(0, fraction.getNumerator(), fraction.getDenominator());
    }

    /**
     * Mutator/Setter Method
     * Sets the whole number of the mix fraction.
     *
     * @param value the number to be set as the whole number
     */
    public void setWholePart (int value) {
        this.whole = value;
    }

    /**
     * Mutator/Setter Method
     * Sets the fraction part of the mix fraction.
     *
     * @param fraction the fraction to be set
     */
    public void setFractionPart (Fraction fraction) {
        if (Math.signum(this.whole) == -1 && fraction.getDenominator() == -1) {
            super.setNumerator(fraction.getNumerator());
            super.setDenominator(fraction.getDenominator() * -1);
        }
        super.setNumerator(fraction.getNumerator());
        super.setDenominator(fraction.getDenominator());
    }

    /**
     * Returns the whole number of this mix fraction.
     *
     * @return the value of the whole number
     */
    public int getWholePart() { return this.whole; }

    /**
     * Returns the proper fraction part of the mix fraction.
     *
     * @return the proper fraction
     */
    public Fraction getFractionPart() {
        Fraction fraction = new Fraction ();
        fraction.setNumerator(getNumerator());
        fraction.setDenominator(getDenominator());
        return fraction;
    }

    /**
     * Returns the converted mix fractions in improper fraction.
     *
     * @return the improper fraction
     */
    public Fraction toFraction () {
        Fraction fraction = new Fraction();
        if (Math.signum(this.whole) == -1) {
            fraction.setNumerator((Math.abs(this.whole) * getDenominator() + getNumerator()) * -1);
        } else {
            fraction.setNumerator(this.whole * getDenominator() + getNumerator());
        }
        fraction.setDenominator(getDenominator());
        return fraction;
    }

    /**
     * Returns the String value of the mix fraction with the format whole numerator/denominator.
     *
     * @return the mix fraction string
     */
    public String toString () {
        if (this.whole == 0) {
            return super.toString();
        } else if (getNumerator() == 0) {
            return String.valueOf(this.whole);
        } else if (this.whole == 0 && getNumerator() == 0) {
            return "";
        } else if (Math.signum(this.whole) == -1 && getNumerator() == -1) {
            return this.whole +" "+ new Fraction(getNumerator() * -1, getDenominator());
        }


        return this.whole + " " + super.toString();
    }

    /**
     * Returns the equivalent value of the mix fraction in double format
     *
     * @return the double format of the mix fraction
     */
    public double toDouble () {
        return getWholePart() + super.toDouble();
    }

    /**
     * Returns the sum of this mix fraction and another mix fraction.
     *
     * @param other the addend to be added to the first addend or the operand one
     * @param <T> describes the type of parameter whether Fraction or MixFraction
     * @return the sum of the two mix fraction
     */
    public <T> MixFraction addTo(T other) {
        Fraction res;
        if (other instanceof MixFraction) {
            res = this.toFraction().add(((MixFraction) other).toFraction());
        } else {
            res = this.toFraction().add((Fraction) other);
        }

        res.reduce();
        return new MixFraction(res);
    }

    /**
     * Returns the difference of this mix fraction and another mix fraction.
     *
     * @param other the subtrahend subtracted to the minuend or the operand one
     * @param <T> describes the type of parameter whether Fraction or MixFraction
     * @return the difference of the two mix fraction
     */
    public <T> MixFraction subtractTo(T other) {
        Fraction res;
        if (other instanceof MixFraction) {
            res = this.toFraction().subtract(((MixFraction) other).toFraction());
        } else {
            res = this.toFraction().subtract((Fraction) other);
        }

        res.reduce();
        return new MixFraction(res);
    }

    /**
     * Returns the product of this mix fraction and another mix fraction.
     *
     * @param other the factor to be multiplied to the first factor or the operand one
     * @param <T> describes the type of parameter whether Fraction or MixFraction
     * @return the product of two mix fraction
     */
    public <T> MixFraction multiplyBy(T other) {
        Fraction res;
        if (other instanceof MixFraction) {
            res = this.toFraction().multiply(((MixFraction) other).toFraction());
        } else {
            res = this.toFraction().multiply((Fraction) other);
        }

        res.reduce();
        return new MixFraction(res);
    }

    /**
     * Returns the quotient of this mix fraction and another mix fraction.
     *
     * @param other the divisor to be divided to the dividend or the operand one
     * @param <T> describes the type of parameter whether Fraction or MixFraction
     * @return the quotient of two mix fractions
     */
    public <T> MixFraction divideBy(T other) {
        Fraction res;
        if (other instanceof MixFraction) {
            res = this.toFraction().divide(((MixFraction) other).toFraction());
        } else {
            res = this.toFraction().divide((Fraction) other);
        }

        res.reduce();
        return new MixFraction(res);
    }

    /**
     * Reduces this mix fraction to its simplest form.
     */
    public void reduce() {
        Fraction fraction = new Fraction();
        int newWhole;
        int num;
        int den;
        if (getWholePart() == 0 && Math.signum(getNumerator()) == -1 ) {
            newWhole =  (getNumerator() / getDenominator());
            num = -1 * (getNumerator() % getDenominator());
        } else if (Math.signum(getWholePart()) == -1 && Math.signum(getNumerator()) == -1) {
            newWhole =  (getNumerator() / getDenominator());
            num = -1 * (getNumerator() % getDenominator());
        } else {
            newWhole = getWholePart() + getNumerator() / getDenominator();
            num = getNumerator() % getDenominator();
        }
        den = getDenominator();
        fraction.setNumerator(num);
        fraction.setDenominator(den);
        fraction.reduce();

        setWholePart(newWhole);
        setFractionPart(fraction);
    }
}
