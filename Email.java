package emailparser;

import java.util.List;

public class Email {

    private String to = null;
    private String from = null;
    private String date = null;
    private String subject = null;
    private String message = null;

    public Email(List<String> list) {
        to = getTo(list);
        from = getFrom(list);
        date = getDate(list);
        subject = getSubject(list);
        message = getMessage(list);
    }

    //Gets who the email was sent to
    private String getTo(List<String> text) {
        for (String s : text) {
            if (s.matches("To: .*")) {
                return s.substring(s.indexOf("<") + 1, s.length() - 1); //Gets the text between the first instance of an '<'
            }
        }
        return null;
    }

    //Gets who sent the email
    private String getFrom(List<String> text) {
        for (String s : text) {
            if (s.matches("From: .*")) {
                return s.substring(s.indexOf("<") + 1, s.length() - 1); //Gets the text between the first instance of an '<'
            }
        }
        return null;
    }

    //Returns the date from the email
    private String getDate(List<String> text) {
        for (String s : text) {
            if (s.matches("Date: .*")) {
                return s.substring(s.indexOf(" ") + 1);
            }
        }
        return null;
    }

    //Returns the message of the email
    private String getMessage(List<String> text) {
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
    private String getSubject(List<String> text) {
        for (String s : text) {
            if (s.matches("Subject: .*")) {
                return s.substring(s.indexOf(" ") + 1);
            }
        }
        return null;
    }

    //Prints certain parameters from the email
    public void printEmail() {
        System.out.println("To: " + getTo());
        System.out.println("From: " + getFrom());
        System.out.println("Date: " + getDate());
        System.out.println("Subject: " + getSubject());
        System.out.println("Body: " + getMessage());
    }
    
    //Returns the class variables
    public String getTo() {
        return to;
    }
    
    public String getFrom() {
        return from;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getMessage() {
        return message;
    }
}
