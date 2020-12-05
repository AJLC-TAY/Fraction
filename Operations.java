package main;

import java.util.Arrays;

/**
 * A class containing operations that can be used
 * on Fraction and MixFraction objects.
 *
 * @author Alvin John L. Cutay and Benedict Pineda Jr.
 * @version 1.0, 10/16/2020
 */
public class Operations {

    /**
     * A generic method that adds fractions and returns their summation.
     *
     * @param one first addend
     * @param two second addend
     * @param <T> describes the type of parameter whether Fraction or MixFraction
     * @return the sum of the two fractions
     */
    public static <T> Fraction addFractions(T one, T two) {
        if (one instanceof MixFraction) {
            if (two instanceof MixFraction) {
                return ((MixFraction)one).addTo((MixFraction)two);
            } else {
                return ((MixFraction)one).addTo((Fraction)two);
            }
        } else {
            if (two instanceof MixFraction) {
                return ((Fraction)one).add(((MixFraction)two).toFraction());
            } else {
                return ((Fraction)one).add((Fraction)two);
            }
        }
    }

    /**
     * A generic method that subtracts fractions and returns their difference.
     *
     * @param one minuend of the expression
     * @param two subtrahend of the expression
     * @param <T> describes the type of parameter whether Fraction or MixFraction
     * @return the difference of the two fractions
     */
    public static <T> Fraction subtractFractions(T one, T two) {
        if (one instanceof MixFraction) {
            if (two instanceof MixFraction) {
                return ((MixFraction)one).subtractTo((MixFraction)two);
            } else {
                return ((MixFraction)one).subtractTo((Fraction)two);
            }
        } else {
            if (two instanceof MixFraction) {
                return ((Fraction)one).subtract(((MixFraction)two).toFraction());
            } else {
                return ((Fraction)one).subtract((Fraction)two);
            }
        }
    }


    /**
     * Creates and returns an array of randomized MixFraction objects from the explicit array size.
     *
     * @param arraySize the length of the array
     * @return the array of MixFraction objects
     */
    public static MixFraction[] createMixFractionArray(int arraySize) {
        MixFraction[] mixFractionArray = new MixFraction[arraySize];
        for (int i = 0; i < arraySize; i++) {
            int randomNum1 = (int)(Math.random()*(10-1+1)+1);
            int randomNum2 = (int)(Math.random()*(10-1+1)+1);
            int randomNum3 = (int)(Math.random()*(10-1+1)+1);
            MixFraction mf = new MixFraction(randomNum1, randomNum2, randomNum3);
            mixFractionArray[i] = mf;
        }
        return mixFractionArray;
    }

    /**
     * Returns the summation of all the MixFraction objects inside an array.
     *
     * @param mixFractionArray the array of MixFraction elements
     * @return the summation in mix fraction form
     */
    public static MixFraction addArrayOfMixFractions(MixFraction[] mixFractionArray) {
        Fraction summation = new Fraction();
        for (MixFraction mixFraction : mixFractionArray) {
            summation = addFractions(summation, mixFraction);
        }
        return summation.toMixFraction();
    }

    public static void main(String[] args) {
        MixFraction[] mfArray = createMixFractionArray(3);
        System.out.println(Arrays.toString(mfArray));
        System.out.println(addArrayOfMixFractions(mfArray).toString());
    }
}
