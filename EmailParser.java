/*
 The next steps I would take in my implementation would be:
 -Change the way looking up the To field is to support multiple recipients
 -Include peoples names such as "John Doe" (doe.john@gmail.com)
 -Format the date better
 -Format and handle the body of the message better, including html and other syntax in the body
 */

/*
 message body class
 fields classes

 https://github.com/hkal/mailreader
 https://github.com/hkal/mailreader/blob/master/lib/mailreader/email.rb#L24

 do that and then ruby version
 */
package emailparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailParser {

    public static void main(String[] args) {
        File file = new File("email.txt");
        List<String> list = readFile(file); //List for adding lines from the text file

        Email email = new Email(list);

        //Prints all of the information from the email
        email.printEmail();
    }

    private static List<String> readFile(File file) {
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            //Adds all of the lines from the email to the list
            while ((text = reader.readLine()) != null) {
                list.add(text);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
            }
        }
        return list;
    }
}
