package bearzu.com.derivative;

import java.util.HashMap;

/**
 * Created by Administrator on 10/11/2016.
 */
public class Derivatives {
    private String derivative;
    public String derivation;

    private String[] terms;

    HashMap<Integer,Integer> groupTerms = new HashMap<Integer,Integer>();
    public Derivatives(String derivative) {
        this.derivative = derivative;
        this.derivative.replaceAll(" ","");
        this.terms = this.derivative.split("(?<=[-+])|(?=[-+])");
    }

    public String getTerms() {

        String finalTerm = "";
        for(int i=0; i < this.terms.length;i++)
        {
            Term t = new Term(this.terms[i]);

            String additionalTerm = t.assessTerm();
            if(additionalTerm != "0") finalTerm += additionalTerm;
            else finalTerm = finalTerm.substring(0,finalTerm.length()-1);
        }



        //Simplification of Terms
        //Grouping all terms together
        this.terms = finalTerm.split("(?<=[-+])|(?=[-+])");
        for(int i = 0; i < this.terms.length; i++)
        {
            if(this.terms[i].contains("^"))
            {
                String[] inTerms = this.terms[i].split("^");
                inTerms[0] = inTerms[0].replaceAll("x","");
                this.groupTerms.put(Integer.parseInt(inTerms[1]),Integer.parseInt(inTerms[0]));
            }
            else
            {
                String defaultTerm = this.terms[i];
                defaultTerm = defaultTerm.replaceAll("x","");
                this.groupTerms.put(1,Integer.parseInt(defaultTerm));
            }
        }


        return finalTerm;


    }


    public void assessTerms() {
        for(int i = 0; i < this.terms.length; i++) {

        }
    }
}
