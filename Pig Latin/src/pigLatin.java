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
                        //Rules for structure of word
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
                        //Spilts word into sections. Spilt is at the beginning of every vowel sound
                        //String[] wordArray = word.split();

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

    public int indexOfFirstChar (String str, char c){
        for (int index = 0; index < str.length(); index++){
            if (str.charAt(index) == c){
                return index;
            }
        }
        //If char not found return index as -1
        return -1;
    }

    public boolean isVowel (char c){
        String vowels = "AEIOUaeiou";
        if (vowels.contains(String.valueOf(c))){
            return true;
        }
            return false;
    }

    public boolean hasVowel (String str) {
        for (int i = 0; i < str.length(); i++){
            if (isVowel(str.charAt(i))){
                return true;
            }
        }
        return false;
    }

    public int roll20 (){
        int randomNumber = (int)(Math.random() * 20 + 1);
        return randomNumber;
    }

    public int fkIt (){
        boolean twoOnes = false;
        int numberOfRolls = 1;
        while (!twoOnes){
            int currentRoll = roll20();
            if (currentRoll == 1 ){
                int secondRoll = roll20();
                if (secondRoll == 1){
                    twoOnes = true;
                }
            } else {
                numberOfRolls++;
            }
        }
        return numberOfRolls;
    }

    public int testRNG(){
        int testQ = 1;
        int totalT = 0;
        while (testQ != 1000000){
            int num = fkIt();
            System.out.println("Trial " + testQ + " : " + num);
            totalT += num;
            testQ++;
        }
        return totalT/testQ;
    }

    public String toEnglish(String str, int inputLanguage) {
        String finalString = "";

        //Splits each word into a separate string
        String[] wordsArray = str.split(" ");
        for (String word : wordsArray){
            System.out.println(word);
        }

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

    public static void main(String[] args) {

        pigLatin test = new pigLatin();
        //System.out.println(test.toLanguage("Weaw ladder  Sally's !a? sweet !.!", 2));
        //System.out.println(test.indexOfFirstVowel("sdff"));
        //System.out.println(test.hasVowel("sd;a"));
        //System.out.println(test.fkIt());
        //System.out.println(test.indexOfFirstChar("Ello-hay", '-'));
        System.out.println(test.toEnglish("KALSJDFKaksdjf a jEROIAJFeDS:FA k{EWf kaPKA(TQ%(* %AP$T Adsf?", 1));

    }

}

