package bearzu.com.derivative;

import android.os.Debug;

import java.io.Console;

/**
 * Created by Administrator on 10/11/2016.
 */
public class Term {
    private String term;
    public Term(String term) {
        this.term = term;


    }

    public String assessTerm() {
        String term = this.term;
        boolean varFound = false;
        boolean varPassed = false;
        char var = 'x';
        int numeric = 1;
        int exponent = 0;

        String numeric_string="";
        String exponent_string="";

        int[] numericals = new int[2];

        for( int i = 0; i < term.length(); i++)
        {

            char c  = term.charAt(i);
            if(c == '+' || c == '-')
            {
                return Character.toString(c);
            }
            if(Character.isLetter(c)) {
                //Found the term
                varFound = true;
                System.out.println("Variable found.");
            }


            if(!varFound) numeric_string = numeric_string + c;

            else {
                if(! varPassed)
                {
                    var = c;
                    varPassed = true;
                }
                else
                {
                    if(c != '^') exponent_string+=(Character.toString(c));
                }
            }

        }

        //Assess the exponential term
        boolean hasExponent = false;
        exponent_string = exponent_string.replaceAll("\\s","");
        exponent_string = exponent_string.replaceAll("[()]","");
        if(exponent_string.length() > 0) {
            hasExponent = true;

            exponent = Integer.parseInt(exponent_string);
        }


        //parse numeric value
        boolean isFraction = false;
        if(numeric_string.length() > 0)
        {
            if(numeric_string.contains("/")) {
                isFraction = true;
                String[] numericals_string = numeric_string.split("\\/");
                numericals[0] = Integer.parseInt(numericals_string[0]);

                if(numericals_string[1].contains("("))
                { // This means that the x is below the fraction
                    numericals_string[1] = numericals_string[1].replaceAll("[()]","");

                    //Return a custom value for this fraction
                    String fractionx = numericals_string[1];
                    if(hasExponent) {
                        exponent = exponent * -1;
                    }
                    else {
                        exponent = -1;
                    }
                        numeric = Integer.parseInt(numericals_string[0]);
                        int numeric_2 = Integer.parseInt(numericals_string[1]);

                        numeric =  numeric * exponent;
                        exponent -= 1;
                        /*
                        if(1 == 1) // For debugging
                        {
                            exponent = exponent * -1;
                            term = Integer.toString(numeric) + "/" + Integer.toString(numeric_2) + Character.toString(var) + "^n" + Integer.toString(exponent);

                            return term;
                        }
                        */

                        if(numeric%numeric_2 == 0)
                        {
                            numeric = numeric/numeric_2;
                            numeric_2 = 1;
                        }
                        exponent = exponent * -1;
                        if(numeric_2 == 1)
                        {
                            term = Integer.toString(numeric) + "/" + Character.toString(var) + "^" + Integer.toString(exponent);
                        }
                        else {
                            term = Integer.toString(numeric) + "/" + Integer.toString(numeric_2) + Character.toString(var) + "^" + Integer.toString(exponent);
                        }
                        term = term.replaceAll("-","n");
                        return term;



                }
                else
                {
                    numericals[1] = Integer.parseInt(numericals_string[1]);
                }
            }
            else
            {
                numeric_string = numeric_string.replaceAll("\\s","");
                numeric = Integer.parseInt(numeric_string);
            }
        }

        //Term has been made;
        if(!varFound)
        {
            return "0";
        }

        if(!hasExponent) // Simple Term
        {
            return numeric_string;
        }

        else {
            if(! isFraction)
            {
                numeric = exponent * numeric;
                exponent = exponent - 1;
            }
            else
            {
                numeric = exponent * numericals[0];
                exponent = exponent - 1;
            }

        }

        //Build new Term with variables given
        if(!isFraction) term = Integer.toString(numeric) + Character.toString(var);
        else {
            if(numeric_string.contains("("))
            {

            }

            if (numeric % numericals[1] == 0) {
                numeric = numeric / numericals[1];
                term = (numeric > 1) ? Integer.toString(numeric) + Character.toString(var) : Character.toString(var);

            } else {
                term = Integer.toString(numeric) + "/" + numericals[1] + Character.toString(var);
            }
        }
        if(hasExponent && exponent > 1)
        {
            term += "^" + Integer.toString(exponent);
        }


        return term;
    }



    private int get_gcf(long a, long b) {
        while (b > 0)
        {
            long temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return (int)a;
    }
}
