package bearzu.com.derivative;

/**
 * Created by Administrator on 10/11/2016.
 */
public class Derivatives {
    private String derivative;
    public String derivation;

    private String[] terms;
    public Derivatives(String derivative) {
        this.derivative = derivative;
        this.terms = this.derivative.split("(?<=[-+])|(?=[-+])");
    }

    public String getTerms() {

        String finalTerm = "";
        for(int i=0; i < this.terms.length;i++)
        {
            Term t = new Term(this.terms[i]);
            String additionalTerm = t.assessTerm();
            finalTerm = finalTerm.concat(additionalTerm);
        }

        return finalTerm;


    }


    public void assessTerms() {
        for(int i = 0; i < this.terms.length; i++) {

        }
    }
}
