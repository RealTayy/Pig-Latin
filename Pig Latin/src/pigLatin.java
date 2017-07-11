import java.util.ArrayList;
import java.util.HashMap;

/*

//If field is empty return nothing
        if (textInput.getText().length() == 0){
                return finalString;
                }

*/

public class pigLatin {


    public String toLanguage (String str, int outputLanguage){
        String intString = str;
        String finalString = "";
/*
        //If field is empty return nothing
        if (textInput.getText().length() == 0){
            return finalString;
        }
*/

        //Spilts each word into a separate string and detects if it was Uppercase of not
        String[] wordsArray = intString.split(" ");

        //Translates word by word
        switch (outputLanguage) {
            case 0: //Translates to English
                finalString = intString;
                return finalString;

            case 1: //Translates to Pig Latin

                for (String word : wordsArray) {
                    //Rules for if word doesn't exist AKA user pressed space twice in a row. Skip the "word"
                    if (word.length() == 0){
                        finalString = finalString + word;
                    }

                    //Rules for if word starts with a punctuation
                    else if (!Character.isLetterOrDigit(word.charAt(0))) {
                        finalString = finalString + word + " ";
                    }

                    //Rules for if word starts with Number or Letter
                    else {
                        int wordLength = word.length();
                        boolean isUppercase = false;

                        //Checks if word starts with a letter and if is uppercase or not
                        if (Character.isLetter(word.charAt(0))) {
                            isUppercase = Character.isUpperCase(word.charAt(0));
                        }

                        word = word.toLowerCase();  //Changes all chars to lowercase

                        int indexPunc = word.length(); //Sets Punc marker to be at end of work and keeps it that way if there is no punctuation in the word.
                        boolean hasPunc = false;
                        String punctuation = "";

                        //Looks for any punctuation deletes it and any letter after it. and saves it and puts it at the end of the word later on
                        for (int i = 0; i < wordLength; i++) {
                            boolean isCharNum = Character.isLetterOrDigit(word.charAt(i));
                            if (!isCharNum) {
                                hasPunc = true;
                                indexPunc = i;
                                break;
                            }
                        }

                        //Saves all the chars as a string after the first punc if found and adds it again later at the end of string for word
                        if (hasPunc) {
                            punctuation = word.substring(indexPunc);
                        }

                        //Rules for Translation to selected Language!!!!!!!!!!!!!!!!
                        //Rules for if word contains no vowels
                        String begString = "begString not defined";
                        String endString = "endString not defined";
                        if (indexOfFirstVowel(word) == -1){
                            begString = word;
                            endString = "w";
                        }
                        //Rules if word does contain vowel
                        else {

                            //Rules if word begins with vowel
                            if (isVowel(word.charAt(0))){
                                begString = word;
                                endString = "w";
                            }

                            //Rules if word does not begin with vowel
                            else {
                                int indexOfSpilt = indexOfFirstVowel(word);
                                begString = word.substring(indexOfSpilt, indexPunc);
                                endString = word.substring(0, indexOfSpilt);
                            }
                        }
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
                finalString = intString;
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

        pigLatin test = new pigLatin();
        //System.out.println(test.toLanguage("You is gay. Suck my Drick", 1));
        //System.out.println(test.indexOfFirstVowel("sdff"));
        System.out.println(test.hasVowel("sd;a"));

    }

}

