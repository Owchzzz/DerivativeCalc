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
        if(exponent_string.length() > 0) {
            hasExponent = true;
            exponent = Integer.parseInt(exponent_string);
        }


        //parse numeric value
        boolean isFraction = false;
        if(numeric_string.length() > 0)
        {
            if(numeric_string.indexOf('/') > 0) {
                isFraction = true;
                String[] numericals_string = numeric_string.split("/");
                numericals[0] = Integer.parseInt(numericals_string[0]);
                numericals[0] = Integer.parseInt(numericals_string[0]);
            }
            else
            {
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


        }

        //Build new Term with variables given
        term = Integer.toString(numeric) + Character.toString(var);
        if(hasExponent && exponent != 1)
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
