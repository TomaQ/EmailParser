/*
The next steps I would take in my implementation would be:
-Change the way looking up the To field is to support multiple recipients
-Include peoples names such as "John Doe" (doe.john@gmail.com)
-Format the date better
-Format and handle the body of the message better, including html and other syntax in the body
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
        List<String> list = new ArrayList<>(); //List for adding lines from the text file
        File file = new File("email.txt");
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

        //Prints all of the information from the email
        printEmail(list);

    }

    //Prints certain parameters from the email
    public static void printEmail(List<String> list) {
        System.out.println("To: " + getTo(list));
        System.out.println("From: " + getFrom(list));
        System.out.println("Date: " + getDate(list));
        System.out.println("Subject: " + getSubject(list));
        System.out.println("Body: " + getMessage(list));
    }

    //Gets who the email was sent to
    public static String getTo(List<String> text) {
        for (String s : text) {
            if (s.matches("To: .*")) {
                return s.substring(s.indexOf("<") + 1, s.length() - 1); //Gets the text between the first instance of an '<'
            }
        }
        return null;
    }

    //Gets who sent the email
    public static String getFrom(List<String> text) {
        for (String s : text) {
            if (s.matches("From: .*")) {
                return s.substring(s.indexOf("<") + 1, s.length() - 1); //Gets the text between the first instance of an '<'
            }
        }
        return null;
    }

    //Returns the date from the email
    public static String getDate(List<String> text) {
        for (String s : text) {
            if (s.matches("Date: .*")) {
                return s.substring(s.indexOf(" ") + 1);
            }
        }
        return null;
    }

    //Returns the message of the email
    public static String getMessage(List<String> text) {
        String body = "";
        for (int i = 0; i < text.size(); i++) {
            if (text.get(i).matches("Content-Transfer-Encoding: .*")) {
                //This gets all of the plain text from the email and adds it to a string
                while (!text.get(i + 1).matches(".*--_.*") && i < text.size() - 1) {
                    i++; //increment first since we're on the line we don't want
                    body += text.get(i);
                }
                break; //once it finishes getting all of the text there's no need to search the rest of the email
            }
        }
        return body;
    }

    //Returns the subject of the email
    public static String getSubject(List<String> text) {
        for (String s : text) {
            if (s.matches("Subject: .*")) {
                return s.substring(s.indexOf(" ") + 1);
            }
        }
        return null;
    }
}
