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

    Map<Integer,Integer> groupTerms = new TreeMap<Integer,Integer>();
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

        if(1 == 1)
        {
            return finalTerm;
        }
        //Simplification of Terms
        //Grouping all terms together
        this.terms = finalTerm.split("(?<=[-+])|(?=[-+])");
        for(int i = 0; i < this.terms.length; i++)
        {
            int key = 0;
            int value = 0;

            int val = 0;
            int tempVal = 0;

            if(! this.terms[i].contains("+") && ! this.terms[i].contains("-")) {
                if (this.terms[i].contains("^")) {
                    String[] inTerms = this.terms[i].split("\\^");
                    System.out.println("inTerms array is: ");
                    System.out.println(Arrays.toString(inTerms));
                    inTerms[0] = inTerms[0].replace("x", "");
                    System.out.println("Parsing ");
                    System.out.println(inTerms[0]);
                    tempVal = Integer.parseInt(inTerms[0]);

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
                        tempVal = Integer.parseInt(defaultTerm);
                }


                if (this.groupTerms.containsKey(key)) {
                    val = this.groupTerms.get(key);
                }
                if (i > 0) {
                    if (this.terms[i - 1].contains("-")) {
                        tempVal = tempVal * -1;
                    }
                }
                val = val + tempVal;

                this.groupTerms.put(key, val);
            }
        }
        int entries = 0;
        finalTerm = "";
        ArrayList<Integer> keys = new ArrayList<Integer>(this.groupTerms.keySet());
        for(int i=keys.size()-1;i>=0;i--)
        {

            int exponent = (int) keys.get(i);
            int intval = (int) this.groupTerms.get(exponent);
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
                finalTerm += Integer.toString(intval);
            }
            else
            {
                if(intval >= 0)
                {
                    finalTerm += "+" + Integer.toString(intval);
                }
                else
                {
                    finalTerm += Integer.toString(intval);
                }
            }
            entries++;
            if(exponent > 0)
            {
                finalTerm += "x";
                if(exponent > 1) finalTerm += "^" + Integer.toString(exponent);
            }
        }
        return finalTerm;


    }


    public void assessTerms() {
        for(int i = 0; i < this.terms.length; i++) {

        }
    }
}
