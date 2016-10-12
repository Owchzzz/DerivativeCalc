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
        char var;
        int numeric = 1;
        int exponent = 0;

        String numeric_string="";
        String exponent_string="";

        String[] numericals;
        for( int i = 0; i < term.length(); i++)
        {
            char c  = term.charAt(i);
            if(Character.isLetter(c)) {
                //Found the term
                varFound = true;
                System.out.println("Variable found.");
            }

            if(! varFound)
            {
                numeric_string+=(Character.toString(c));
            }

            else {
                if(! varPassed)
                {
                    var = c;
                    varPassed = true;
                }
                else
                {
                    exponent_string+=(Character.toString(c));
                }
            }



            //parse numeric value
            boolean isFraction = false;
            if(numeric_string.length() > 0)
            {
                if(numeric_string.indexOf('/') > 0) {
                    isFraction = true;
                    numericals = numeric_string.split("/");
                }
                else
                {
                    numeric = Integer.parseInt(numeric_string);
                }
            }

            //Assess the exponential term
            boolean hasExponent = false;
            if(exponent_string.length() > 0) {
                hasExponent = true;
            }


            //Term has been made;
            if(!hasExponent) // Simple Term
            {
                return Integer.toString(numeric);
            }
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
