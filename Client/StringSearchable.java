package Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSearchable {
    public List<String> terms = new ArrayList<String>();

    public StringSearchable(List<String> terms) {
        this.terms.addAll(terms);
    }

    public Collection<String> search(String value) {

        List<String> founds = new ArrayList<String>();


        for (String s : terms) {

            if (s.toUpperCase().indexOf(value.toUpperCase()) == 0) {

                founds.add(s);

            }

        }

        return founds;
    }

}
