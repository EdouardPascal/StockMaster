package Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {

        SwingUtilities.invokeAndWait(new Runnable() {


            @Override

            public void run() {

                List<String> myWords = new ArrayList<String>();

                myWords.add("bike");

                myWords.add("car");

                myWords.add("cap");

                myWords.add("cape");

                myWords.add("canadian");

                myWords.add("caprecious");

                myWords.add("catepult");


                StringSearchable searchable = new StringSearchable(myWords);

                AutoCompleteJComboBox combo = new AutoCompleteJComboBox(searchable);


                JFrame frame = new JFrame();

                frame.add(combo);

                frame.pack();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setVisible(true);

            }


        });

    }
}
