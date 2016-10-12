package bearzu.com.derivative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Richard on 10/11/2016.
 */
public class Derivatives {
    private String derivative;
    public String derivation;

    private String[] terms;

    Map<Integer,String> groupTerms = new TreeMap<Integer,String>();
    public Derivatives(String derivative) {
        this.derivative = derivative;
        this.derivative.replaceAll("\\s","");
        this.terms = this.derivative.split("(?<=[-+])|(?=[-+])");
    }

    public String getTerms() {

        String finalTerm = "";
        for(int i=0; i < this.terms.length;i++)
        {
            Term t = new Term(this.terms[i]);

            String additionalTerm = t.assessTerm();
            if(! additionalTerm.equals("0")) finalTerm += additionalTerm;
            else
            {
                if(finalTerm.length() > 1)
                    finalTerm = finalTerm.substring(0,finalTerm.length()-1);
            }
        }


        //Simplification of Terms
        //Grouping all terms together
        this.terms = finalTerm.split("(?<=[-+])|(?=[-+])");
        for(int i = 0; i < this.terms.length; i++)
        {

            int key = 0;
            int value = 0;

            String val = "0";
            String tempVal = "0";
            this.terms[i] = this.terms[i].replaceAll("n","-");
            if(! this.terms[i].contains("+") && ! this.terms[i].contains("-")) {
                if (this.terms[i].contains("^")) {

                    String[] inTerms = this.terms[i].split("\\^");
                    System.out.println("inTerms array is: ");
                    System.out.println(Arrays.toString(inTerms));
                    inTerms[0] = inTerms[0].replace("x", "");
                    System.out.println("Parsing ");
                    System.out.println(inTerms[0]);
                    tempVal = inTerms[0];

                    System.out.println("Parsing ");
                    System.out.println(inTerms[1]);
                    key = Integer.parseInt(inTerms[1]);
                } else {
                    if (this.terms[i].contains("x")) {
                        key = 1;
                    }
                    String defaultTerm = this.terms[i];
                    defaultTerm = defaultTerm.replaceAll("x", "");
                    if(! defaultTerm.equals(""))
                        tempVal = defaultTerm;
                }


                if (this.groupTerms.containsKey(key)) {
                    val = this.groupTerms.get(key);
                }
                /* Defunct negative value check
                if (i > 0) {
                    if (this.terms[i - 1].contains("-")) {
                        tempVal = tempVal;
                    }
                }
                val = val + tempVal;
                */
                if(i > 0) {
                    if (this.terms[i - 1].contains("-")) {
                        tempVal = "-" + tempVal;
                    }
                }
                if(val.contains("/") || tempVal.contains("/"))
                { // Is a fraction now
                    System.out.println("Fraction found");
                    System.out.println(key);
                    long numerator, denominator, Nnumerator,Ndenominator;
                    long Rnumerator,Rdenominator;
                    String[] originalfraction = new String[2];
                    String[] newfraction = new String[2];
                    String[] rawfraction;

                    if(!val.contains("/"))
                    {
                        val += "/1";
                    }

                    if(!tempVal.contains("/"))
                    {
                        tempVal += "/1";
                    }

                    originalfraction = val.split("/");
                    newfraction = tempVal.split("/");
                    numerator = Long.parseLong(originalfraction[0]);
                    denominator = Long.parseLong(originalfraction[1]);
                    Nnumerator = Long.parseLong(newfraction[0]);
                    Ndenominator = Long.parseLong(newfraction[1]);


                    if(denominator == Ndenominator) {
                        Rdenominator = denominator;
                        Rnumerator = numerator + Nnumerator;
                    }
                    else { //Cross multiply
                        Rnumerator = (numerator * Ndenominator) + (Nnumerator*denominator);
                        Rdenominator = denominator * Ndenominator;
                    }

                    String ResultFraction = asFraction(Rnumerator,Rdenominator);
                    ResultFraction = ResultFraction.replaceAll("/1","");
                    System.out.println("Key: " + key + " Value: " +ResultFraction);
                    val = ResultFraction;
                }
                else
                {

                    value = Integer.parseInt(val) + Integer.parseInt(tempVal);
                    val = Integer.toString(value);
                }
                this.groupTerms.put(key, val);
            }
        }
        int entries = 0;
        finalTerm = "";
        ArrayList<Integer> keys = new ArrayList<Integer>(this.groupTerms.keySet());
        for(int i=keys.size()-1;i>=0;i--)
        {

            int exponent = (int) keys.get(i);
            String intval = this.groupTerms.get(exponent);
            /*
            if(intval > 0 )
            {
                finalTerm += "-" + Integer.toString(intval);
            }
            else
            {
                if(entries > 0)
                    finalTerm += "+" + Integer.toString(intval);
                else
                    finalTerm += Integer.toString(intval);
            }
            */
            if(entries == 0)
            {
                finalTerm += intval;
            }
            else
            {
                if(!intval.contains("-"))
                {
                    intval = intval.replaceAll("-","");
                    finalTerm += "+" + intval;
                }
                else
                {
                    finalTerm += intval;
                }
            }
            entries++;
            if(exponent > 0)
            {
                finalTerm += "x";
                if(exponent > 1) finalTerm += "^" + Integer.toString(exponent);
                else
                {
                    if(exponent < 0)
                    {
                        finalTerm += "undefinedFraction:" + Integer.toString(exponent)  ;
                    }

                }
            }
        }
        return finalTerm;


    }


    /** @return the greatest common denominator */
    public static long gcm(long a, long b) {
        return b == 0 ? a : gcm(b, a % b); // Not bad for one line of code :)
    }

    public static String asFraction(long a, long b) {
        long gcm = gcm(a, b);
        return (a / gcm) + "/" + (b / gcm);
    }
}