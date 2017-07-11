import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.HashMap;

public class mainWindow {
    private JPanel mainPane;
    private JTextField inputTextField;
    private JComboBox inputComboBox;
    private JComboBox outputComboBox;
    private JTextArea textInput;
    private JTextField outputTextField;
    private JTextArea textOutput;

    int inputLanguage;
    int outputLanguage;

    public mainWindow() {
        HashMap<Integer, String> languageKey = new HashMap<>();
        languageKey.put(0, "English");
        languageKey.put(1, "Pig Latin");
        languageKey.put(2, "Ubbi Dubbi");
        int numberOfLanguages = languageKey.size();

        //Populates items for inputComboBox
        for (int i = 0; i < numberOfLanguages; i++) {
            inputComboBox.addItem(languageKey.get(i));
        }

        //Populates items for outComboBox
        for (int i = 0; i < numberOfLanguages; i++) {
            outputComboBox.addItem(languageKey.get(i));
        }

        //Sets the starting input/output language
        inputComboBox.setSelectedIndex(0);
        outputComboBox.setSelectedIndex(2);

        //Changes current input language
        inputComboBox.addActionListener(e -> {
            inputLanguage = inputComboBox.getSelectedIndex();
        });

        //Changes current output language
        outputComboBox.addActionListener(e -> {
            outputLanguage = inputComboBox.getSelectedIndex();
            textOutput.setText(toLanguage(textInput.getText(),outputComboBox.getSelectedIndex()));
        });

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                textOutput.setText(toLanguage(textInput.getText(),outputComboBox.getSelectedIndex()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                textOutput.setText(toLanguage(textInput.getText(),outputComboBox.getSelectedIndex()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textOutput.setText(toLanguage(textInput.getText(),outputComboBox.getSelectedIndex()));
            }
        };

        textInput.getDocument().addDocumentListener(documentListener);
    }

    public String toEnglish (String str, int inputLanguage){
        String finalString = str;

        return finalString;
    }

    public String toLanguage (String str, int outputLanguage){
        String intString = str;
        String finalString = "";

        //If field is empty return nothing
        if (textInput.getText().length() == 0){
            return finalString;
        }

        //Spilts each word into a separate string and detects if it was Uppercase of not
        String[] wordsArray = intString.split(" ");

        //Translates word by word
        switch (outputLanguage) {
            case 0: //Translates to English
                finalString = intString;
                return finalString;

            case 1: //Translates to Pig Latin

                /*Logic for translating*/
                for (String word : wordsArray) {
                    //Rules for if word doesn't exist AKA user pressed space twice in a row. Skip the "word"
                    if (word.length() == 0) {
                        finalString = finalString + word;
                    }

                    //Rules for if word starts with a punctuation. Returns as is
                    else if (!Character.isLetterOrDigit(word.charAt(0))) {
                        finalString = finalString + word + " ";
                    }

                    //Rules for if word starts with Number or Letter
                    else {
                        int wordLength = word.length();
                        boolean isUppercase = false;

                        //Checks if word starts with a letter and if is uppercase or not
                        if (Character.isLetter(word.charAt(0))) {
                            isUppercase = Character.isUpperCase(word.charAt(0)); //Saves whether or not is capitalized word
                        }

                        word = word.toLowerCase();  //Changes all chars to lowercase

                        int indexOfPunc = word.length(); //Sets Punc marker to be at end of work and keeps it that way if there is no punctuation in the word.
                        boolean hasPunc = false;
                        String punctuation = "";

                        //Looks for any punctuation, deletes it and any letter after it. Saves index of where the punc was found
                        for (int i = 0; i < wordLength; i++) {
                            boolean isCharOrNum = Character.isLetterOrDigit(word.charAt(i));
                            if (!isCharOrNum) {
                                hasPunc = true;
                                indexOfPunc = i;
                                break;
                            }
                        }

                        //Saves all the chars as a string after the first punc if found and adds it again later at the end of string for word
                        if (hasPunc) {
                            punctuation = word.substring(indexOfPunc);
                        }
                        /*Rules for Translation to selected Language*/
                        //Rules for if word contains no vowels
                        String begString = "begString not defined";
                        String endString = "endString not defined";
                        if (!hasVowel(word)) {
                            begString = word.substring(0, indexOfPunc);
                            endString = "w";
                        }
                        //Rules if word does contains vowel
                        else {

                            //Rules if word begins with vowel
                            if (isVowel(word.charAt(0))) {
                                begString = word.substring(0, indexOfPunc);
                                endString = "w";
                            }

                            //Rules if word does not begin with vowel
                            else {
                                if (hasVowel(word.substring(0, indexOfPunc))) { //Looks for vowels before punc mark only.
                                    int indexOfSpilt = indexOfFirstVowel(word.substring(0, indexOfPunc));
                                    begString = word.substring(indexOfSpilt, indexOfPunc);
                                    endString = word.substring(0, indexOfSpilt);
                                } else {
                                    int indexOfSpilt = indexOfFirstVowel(word.substring(0, indexOfPunc));
                                    begString = word.substring(0, indexOfPunc);
                                    endString = "w";
                                }
                            }
                        }

                        //Spits stuff out in correct format
                        word = begString + "-" + endString + "ay";

                        // Capitalizes word if it was previous capitalized
                        if (isUppercase && word.length() > 1) {
                            word = (String.valueOf(word.charAt(0)).toUpperCase()) + word.substring(1);
                        } else if (isUppercase && (word.length() == 1)) {
                            word = (String.valueOf(word.charAt(0)).toUpperCase());
                        } else if (isUppercase) {
                            finalString = "Error when understanding caps";
                            return finalString;
                        }

                        finalString = finalString + word + punctuation + " ";
                    }
                }
                return finalString;

            case 2: //Translates to Ubbi Dubbi

                /*Logic for translating*/
                for (String word : wordsArray) {
                    //Rules for if word doesn't exist AKA user pressed space twice in a row. Skip the "word"
                    if (word.length() == 0) {
                        finalString = finalString + word;
                    }

                    //Rules for if word starts with a punctuation. Returns as is
                    else if (!Character.isLetterOrDigit(word.charAt(0))) {
                        finalString = finalString + word + " ";
                    }

                    //Rules for if word starts with Number or Letter
                    else {
                        int wordLength = word.length();
                        boolean isUppercase = false;

                        //Checks if word starts with a letter and if is uppercase or not
                        if (Character.isLetter(word.charAt(0))) {
                            isUppercase = Character.isUpperCase(word.charAt(0)); //Saves whether or not is capitalized word
                        }

                        word = word.toLowerCase();  //Changes all chars to lowercase

                        int indexOfPunc = word.length(); //Sets Punc marker to be at end of work and keeps it that way if there is no punctuation in the word.
                        boolean hasPunc = false;
                        String punctuation = "";

                        //Looks for any punctuation, deletes it and any letter after it. Saves index of where the punc was found
                        for (int i = 0; i < wordLength; i++) {
                            boolean isCharOrNum = Character.isLetterOrDigit(word.charAt(i));
                            if (!isCharOrNum) {
                                hasPunc = true;
                                indexOfPunc = i;
                                break;
                            }
                        }

                        //Saves all the chars as a string after the first punc if found and adds it again later at the end of string for word
                        if (hasPunc) {
                            punctuation = word.substring(indexOfPunc);
                        }
                        /*Rules for Translation to selected Language*/
                        //Rules for if word contains no vowels
                        String begString = "begString not defined";
                        String endString = "endString not defined";
                        if (!hasVowel(word)) {
                            begString = word.substring(0, indexOfPunc);
                            endString = "w";
                        }
                        //Rules if word does contains vowel
                        else {

                            //Rules if word begins with vowel
                            if (isVowel(word.charAt(0))) {
                                begString = word.substring(0, indexOfPunc);
                                endString = "w";
                            }

                            //Rules if word does not begin with vowel
                            else {
                                if (hasVowel(word.substring(0, indexOfPunc))) { //Looks for vowels before punc mark only.
                                    int indexOfSpilt = indexOfFirstVowel(word.substring(0, indexOfPunc));
                                    begString = word.substring(indexOfSpilt, indexOfPunc);
                                    endString = word.substring(0, indexOfSpilt);
                                } else {
                                    int indexOfSpilt = indexOfFirstVowel(word.substring(0, indexOfPunc));
                                    begString = word.substring(0, indexOfPunc);
                                    endString = "w";
                                }
                            }
                        }

                        //Spits stuff out in correct format
                        word = begString + "-" + endString + "ay";

                        // Capitalizes word if it was previous capitalized
                        if (isUppercase && word.length() > 1) {
                            word = (String.valueOf(word.charAt(0)).toUpperCase()) + word.substring(1);
                        } else if (isUppercase && (word.length() == 1)) {
                            word = (String.valueOf(word.charAt(0)).toUpperCase());
                        } else if (isUppercase) {
                            finalString = "Error when understanding caps";
                            return finalString;
                        }

                        finalString = finalString + word + punctuation + " ";
                    }
                }
                return finalString;
        }
        return "-1";
    }

    public int indexOfFirstVowel (String str){
        String vowels = "AEIOUaeiou";
        for (int i = 0; i < str.length(); i++){
            if (vowels.contains(String.valueOf(str.charAt(i)))){
                return i;
            }
        }

        //If no vowel found return index as -1
        return -1;
    }

    public boolean isVowel (char c){
        String vowels = "AEIOUaeiou";
        if (vowels.contains(String.valueOf(c))){
            return true;
        } else {
            return false;
        }
    }

    public boolean hasVowel (String str) {
        for (int i = 0; i < str.length(); i++){
            if (isVowel(str.charAt(i))){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Pig Latin Translator");
        frame.setContentPane(new mainWindow().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
