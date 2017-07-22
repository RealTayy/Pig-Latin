import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.HashMap;

public class mainWindow {
    private JPanel mainPane;
    private JTextField inputTextField;
    private JComboBox inputComboBox;
    private JComboBox outputComboBox;
    private JTextArea textInput;
    private JTextField outputTextField;
    private JTextArea textOutput;

    public int inputLanguage;
    public int outputLanguage;

    private String engStr = "";

    public mainWindow() {
        HashMap<Integer, String> languageKey = new HashMap<>();
        languageKey.put(0, "English");
        languageKey.put(1, "Pig Latin");
        languageKey.put(2, "Ubbi Dubbi");
        languageKey.put(3, "Haigy Paigy");
        languageKey.put(4, "Gibberish");
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
        outputComboBox.setSelectedIndex(0);

        //Changes current input language
        inputComboBox.addActionListener(e -> inputLanguage = inputComboBox.getSelectedIndex());

        //Changes current output language and translate existing text if there.
        outputComboBox.addActionListener(e -> {
            outputLanguage = inputComboBox.getSelectedIndex();
            //Translates language in inputbox to english and then to selected language
            textOutput.setText(toLanguage(toEnglish(textInput.getText(), inputComboBox.getSelectedIndex()), outputComboBox.getSelectedIndex()));
        });

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                //Translates language in inputbox to english and then to selected language
                textOutput.setText(toLanguage(toEnglish(textInput.getText(), inputComboBox.getSelectedIndex()), outputComboBox.getSelectedIndex()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                //Translates language in inputbox to english and then to selected language
                textOutput.setText(toLanguage(toEnglish(textInput.getText(), inputComboBox.getSelectedIndex()), outputComboBox.getSelectedIndex()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //Translates language in inputbox to english and then to selected language
                textOutput.setText(toLanguage(toEnglish(textInput.getText(), inputComboBox.getSelectedIndex()), outputComboBox.getSelectedIndex()));
            }
        };

        textInput.getDocument().addDocumentListener(documentListener);
    }

    public String toEnglish(String str, int inputLanguage) {
        String finalString = "";

        //If field is empty return nothing
        if (textInput.getText().length() == 0) {
            return finalString;
        }

        //Splits each word into a separate string
        String[] wordsArray = str.split(" ");

        switch (inputLanguage){
            case 0: //Translate from English
                finalString = str;
                return finalString;

            case 1: //Translates from Pig Latin
                for (String word : wordsArray){

                    //Rules for if word doesn't exist AKA user pressed space twice in a row. Skip the "word"
                    if (word.length() == 0) {
                        finalString = finalString + word;
                    }

                    //Rules for if word starts with a punctuation. Returns as is
                    else if (!Character.isLetterOrDigit(word.charAt(0))) {
                        finalString = finalString + word + " ";
                    }

                    //Rules if word starts with a num or letter
                    else {

                        boolean isUppercase = false;

                        //Checks if word starts with a letter and if is uppercase or not
                        if (Character.isLetter(word.charAt(0))) {
                            isUppercase = Character.isUpperCase(word.charAt(0)); //Saves whether or not is capitalized word
                        }

                        word = word.toLowerCase();  //Changes all chars to lowercase

                        //Finds the index of the '-' if it exist. Returns -1 if it doesn't
                        int indexOfSpilt = indexOfFirstChar(word, '-');

                        //Rules if word isn't typed in pig latin language
                        if (indexOfSpilt == -1) {
                            finalString += word + " ";

                            //Rules if word follows pig latin rules
                        } else {

                            //divides word in two parts where the '-' is found.
                            String begString = word.substring(indexOfSpilt);
                            String endString = word.substring(0, indexOfSpilt);

                            //Gets rid of the '-' and 'ay' that is added for pig latin language
                            begString = begString.replaceFirst("-", "");
                            begString = begString.replaceFirst("[a].", "");
                            begString = begString.replaceFirst("[a]", "");

                            int indexOfPunc = begString.length();

                            for (int i = 0; i < begString.length(); i++) {
                                boolean isCharOrNum = Character.isLetterOrDigit(begString.charAt(i));
                                if (!isCharOrNum) {
                                    indexOfPunc = i;
                                    break;
                                }
                            }

                            String punc = begString.substring(indexOfPunc);
                            begString = begString.substring(0, indexOfPunc);

                            word = begString + endString + punc;

                            // Capitalizes word if it was previous capitalized
                            if (isUppercase && word.length() > 1) {
                                word = (String.valueOf(word.charAt(0)).toUpperCase()) + word.substring(1);
                            } else if (isUppercase && (word.length() == 1)) {
                                word = (String.valueOf(word.charAt(0)).toUpperCase());
                            } else if (isUppercase) {
                                finalString = "Error when understanding caps";
                                return finalString;
                            }

                            finalString += word + " ";
                        }
                    }
                }
                return finalString;
        }

        return "Unable to translate selected input language to english";
    }

    public String toLanguage(String str, int outputLanguage) {
        String finalString = "";

        //If field is empty return nothing
        if (textInput.getText().length() == 0) {
            return finalString;
        }

        //Splits each word into a separate string
        String[] wordsArray = str.split(" ");

        //Translates word by word
        switch (outputLanguage){
            case 0: //Translates to English
                finalString = str;
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

                        //Looks for any punctuation, deletes it and any letter after it. Saves index of where the punc was found
                        for (int i = 0; i < wordLength; i++) {
                            boolean isCharOrNum = Character.isLetterOrDigit(word.charAt(i));
                            if (!isCharOrNum) {
                                indexOfPunc = i;
                                break;
                            }
                        }

                        //Saves string after the punctuation mark is found is it exist.
                        String punctuation = word.substring(indexOfPunc);

                    /*Rules for create each word structure*/
                        //Rules for if word contains no vowels
                        String begString;
                        String endString;
                        if (!hasVowel(word)) {
                            begString = word.substring(0, indexOfPunc);
                            endString = "";
                        }
                        //Rules if word does contains vowel
                        else {

                            //Rules if word begins with vowel
                            if (isVowel(word.charAt(0))) {
                                begString = word.substring(0, indexOfPunc);
                                endString = "";
                            }

                            //Rules if word does not begin with vowel
                            else {
                                if (hasVowel(word.substring(0, indexOfPunc))) { //Looks for vowels before punc mark only.
                                    int indexOfSpilt = indexOfFirstVowel(word.substring(0, indexOfPunc));
                                    begString = word.substring(indexOfSpilt, indexOfPunc);
                                    endString = word.substring(0, indexOfSpilt);
                                } else {
                                    begString = word.substring(0, indexOfPunc);
                                    endString = "";
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

                        //Adds word to sentence and adds a space afterwards
                        finalString += word + punctuation + " ";
                    }
                }
                return finalString;

            case 2: //Translates to Ubbi Dubbi

                /*Logic for translating*/
                StringBuilder strSB = new StringBuilder();
                boolean firstVowel = true;
                boolean firstLetter = true;
                for (int i = 0; i < str.length(); i++) {
                    //Rules for if char is a " " *Space
                    if (!Character.isLetterOrDigit(str.charAt(i))) {
                        firstLetter = true;
                        strSB.append(str.charAt(i));

                    //Rules for if char is a vowel
                    } else if (isVowel(str.charAt(i)) && firstVowel) {
                        firstVowel = false;
                        //Rules for if char is vowel and first letter of word
                        if (firstLetter) {
                            firstLetter = false;
                            strSB.append("ub-");
                        //Rules for if char is a vowel and NOT first letter of word
                        } else {
                            strSB.append("-ub-");
                        }
                        strSB.append(str.charAt(i));

                    //Rules for if char is vowel but not the first vowel in cluster EX: The letter 'a' in 'teal
                    } else if (isVowel(str.charAt(i)) && !firstVowel) {
                        firstVowel = false;
                        strSB.append(str.charAt(i));

                    //Rules for if char is not a vowel
                    } else {
                        firstVowel = true;
                        firstLetter = false;
                        strSB.append(str.charAt(i));
                    }

                    finalString = strSB.toString();
                }
                return finalString;

            case 3: //Translates to Haigy Paigy

                /*Logic for translating*/
                strSB = new StringBuilder();
                firstVowel = true;
                firstLetter = true;
                for (int i  = 0; i < str.length(); i++) {
                    //Rules for if char is a " " *Space
                    if (!Character.isLetterOrDigit(str.charAt(i))) {
                        firstLetter = true;
                        strSB.append(str.charAt(i));

                    //Rules for if char is a vowel
                    } else if (isVowel(str.charAt(i)) && firstVowel) {
                        firstVowel = false;
                        //Rules for if char is vowel and first letter of word
                        if (firstLetter) {
                            firstLetter = false;
                            strSB.append("haig-");
                        //Rules for if char is a vowel and NOT first letter of word
                        } else {
                            strSB.append("-aig-");
                        }
                        strSB.append(str.charAt(i));

                    //Rules for if char is vowel but not the first vowel in cluster EX: The letter 'a' in 'teal
                    } else if (isVowel(str.charAt(i)) && !firstVowel) {
                        firstVowel = false;
                        strSB.append(str.charAt(i));

                    //Rules for if char is not a vowel
                    } else {
                        firstVowel = true;
                        firstLetter = false;
                        strSB.append(str.charAt(i));
                    }

                    finalString = strSB.toString();;
                }
                return finalString;

            case 4: //Translates to Gibberish

                /*Logic for translating*/
                strSB = new StringBuilder();
                //Translates word by word
                for (String word : wordsArray){
                    firstVowel = true;
                    firstLetter = true;

                    for (int i = 0; i < word.length(); i++) {
                        if (isVowel(word.charAt(i))){
                            //Rules for if firstVowel and firstLetter
                            if (firstVowel && firstLetter){
                                firstVowel = false;

                                //Rules if word is 7+ chars
                                if (word.length() >= 7){
                                    strSB.append("Idig-");
                                //Rules if word is 4-6 chars
                                } else if (word.length() >= 4){
                                    strSB.append("Itug-");
                                //Rules if word is 1-3 chars
                                } else if (word.length() >= 1){
                                    strSB.append("Itherg-");
                                } else {
                                    finalString = "ERROR: Word length is less then 1";
                                    return finalString;
                                }
                                strSB.append(word.charAt(i));

                            //Rules for if firstVowel but not firstLetter
                            } else if (firstVowel && !firstLetter){
                                firstVowel = false;

                                //Rules if word is 7+ chars
                                if (word.length() >= 7){
                                    strSB.append("-idig-");
                                //Rules if word is 4-6 chars
                                } else if (word.length() >= 4){
                                    strSB.append("-itug-");
                                //Rules if word is 1-3 chars
                                } else if (word.length() >= 1){
                                    strSB.append("-itherg-");
                                } else {
                                    finalString = "ERROR: Word length is less then 1";
                                    return finalString;
                                }
                                strSB.append(word.charAt(i));

                            //Rules for if vowel but not firstVowel
                            } else {
                                strSB.append(word.charAt(i));
                            }

                            firstLetter = false; //Sets firstLetter to false after typing in a char

                        //Rules for if not vowel
                        } else {
                            strSB.append(word.charAt(i));
                            firstLetter = false;
                            firstVowel = true;
                        }
                    }
                    //Adds space after each word
                    strSB.append(" ");
                }
                finalString = strSB.toString();
                return finalString;
        }
        return "Language missing logic for translating from english to language";
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

    public int indexOfFirstChar (String str, char c){
        for (int index = 0; index < str.length(); index++){
            if (str.charAt(index) == c){
                return index;
            }
        }
        //If char not found return index as -1
        return -1;
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Pig Latin Translator");
        frame.setContentPane(new mainWindow().mainPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
