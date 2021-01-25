package pl.put.poznan.transformer.logic;

import org.apache.commons.text.WordUtils;

import java.util.regex.Pattern;

/**
 * Class which contains all the logic for TextTransformer project
 */
public class TextTransformer {

    private final String[] transforms;

    /**
     * Constructor
     *
     * @param transforms contains all transformations that user put to transform his text
     */
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

    /**
     * This function transforms text according to transformations in transform array
     *
     * @param text Text which is transformed
     * @return Transformed text according to all transformations
     */
    public String transform(String text){
        String newText = text;
        // of course, normally it would do something based on the transforms
        for (String transform : transforms) {
            switch (transform) {
                case "upper":
                    newText = upper(newText);
                    break;
                case "lower":
                    newText = lower(newText);
                    break;
                case "capitalize":
                    newText = capitalize(newText);
                    break;
                case "reverse":
                    newText = reverse(newText);
                    break;
                case "ab_to_abbreviation":
                    newText = ab_to_abbreviation(newText);
                    break;
                case "abbreviation_to_ab":
                    newText = abbreviation_to_ab(newText);
                    break;
                case "latex":
                    newText = latex(newText);
                    break;
                case "in_words":
                    newText = in_words(newText);
                    break;
                case "repeats":
                    newText = repeats(newText);
                    break;
            }
        }
        return newText;
    }

    /**
     * Converts string value to uppercase
     *
     * @param text Text which is transformed
     * @return Transformed text
     */
    public String upper(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }

    /**
     * Converts string value to lowercase
     *
     * @param text Text which is transformed
     * @return Transformed text
     */
    public String lower(String text){
        return text.toLowerCase();
    }

    /**
     * Converts all the whitespace separated words in a String into capitalized words
     *
     * @param text Text which is transformed
     * @return Transformed text
     */
    public String capitalize(String text){
        return WordUtils.capitalizeFully(text);
    }

    /**
     * Reverses the text maintaining capitalized letters' positions
     *
     * @param text Text which is transformed
     * @return Transformed text
     */
    public String reverse(String text) {
        boolean[] upperCase = new boolean[text.length()];

        for (int i = 0; i < text.length(); i++)
            upperCase[i] = Character.isUpperCase(text.charAt(i));

        text = text.toLowerCase();
        String output = "";
        for (int i = text.length() - 1; i >= 0; i--) {
            if (upperCase[text.length() - 1 - i])
                output += Character.toUpperCase(text.charAt(i));
            else
                output += text.charAt(i);
        }

        return output;
    }

    /**
     * Changes abbreviation to full words in string
     *
     * @param text String with text to transform
     * @return String with transformed text
     */
    public String ab_to_abbreviation(String text){
        String replaceString = text;

        replaceString = replaceString.replaceAll("\\bprof[.]\\B","profesor");
        replaceString = replaceString.replaceAll("\\bProf[.]\\B","Profesor");
        replaceString = replaceString.replaceAll("\\bdr\\b","doktor");
        replaceString = replaceString.replaceAll("\\bDr\\b","Doktor");
        replaceString = replaceString.replaceAll("\\bnp[.]\\B","na przykład");
        replaceString = replaceString.replaceAll("\\bNp[.]\\B","Na przykład");
        replaceString = replaceString.replaceAll("\\bitp[.]\\B","i tym podobne");
        replaceString = replaceString.replaceAll("\\bItp[.]\\B","I tym podobne");
        replaceString = replaceString.replaceAll("\\bitd[.]\\B","i tak dalej");
        replaceString = replaceString.replaceAll("\\bItd[.]\\B","I tak dalej");

        return replaceString;
    }

    /**
     * Changes full words to abbreviation in string
     *
     * @param text String with text to transform
     * @return String with transformed text
     */
    public String abbreviation_to_ab(String text){
        String replaceString = text;

        replaceString = replaceString.replaceAll("\\bmiędzy innymi\\b","m.in.");
        replaceString = replaceString.replaceAll("\\bMiędzy innymi\\b","M.in.");
        replaceString = replaceString.replaceAll("\\bna przykład\\b","np.");
        replaceString = replaceString.replaceAll("\\bNa przykład\\b","Np.");
        replaceString = replaceString.replaceAll("\\bi tym podobne\\b","itp.");
        replaceString = replaceString.replaceAll("\\bI tym podobne\\b","Itp.");
        replaceString = replaceString.replaceAll("\\bi tak dalej\\b","itd.");
        replaceString = replaceString.replaceAll("\\bI tak dalej\\b","Itd.");

        return replaceString;
    }

    /**
     * Prepares given text to Latex
     *
     * @param text String with text to transform
     * @return String with transformed text
     */
    public String latex(String text){
        String replaceString = text;

        replaceString = replaceString.replaceAll("[$]","\\\\\\$");
        replaceString = replaceString.replaceAll("[&]","\\\\\\&");

        return replaceString;
    }

    /**
     * Class intToWord helper, converting a digit integer to a word-digit String
     *
     * @param digit Digit to be transformed
     * @param size Helps recognize which order of magnitude is the digit parameter
     * @return Digit in word-string format
     */
    public String digitToString(int digit, int size)
    {
        String[] jednosci = {"", " jeden", " dwa", " trzy", " cztery", " pięć", " sześć", " siedem", " osiem", " dziewięć"};
        String[] nascie = {"dziesięć", " jedenaście", " dwanaście", " trzynaście", " czternaście", " piętnaście", " szesnaście", " siedemnaście", " osiemnaście", " dziewiętnaście"};
        String[] dziesiatki ={"", " dziesięć", " dwadzieścia", " trzydzieści", " czterdzieści", " pięćdziesiąt", " sześćdziesiąt", " siedemdziesiąt", " osiemdziesiąt", " dziewięćdziesiąt"};
        String[] setki = {"", " sto", " dwieście", " trzysta", " czterysta", " pięćset", " sześćset", " siedemset", " osiemset", " dziewięćset"};

        String result = "";

        if (size == 1)
            result = jednosci[digit];
        else if (size == 10)
            result =  nascie[digit];
        else if (size == 20)
            result = dziesiatki[digit];
        else if (size == 100)
            result = setki[digit];

        return result;
    }

    /**
     * Changes int to word-string, in_words helper
     *
     * @param numberInt Integer to change
     * @return Integer in word-string format
     */
    public String intToWord(int numberInt)
    {
        int number = numberInt;
        String result = "";

        // 123 : sto dwadziescia trzy

        if (number < 0) {
            result += "minus";
            number *= -1;
        }

        if (numberInt == 0) {
            result += "zero ";
        }

        if (number == 1000)
            result += "tysiac ";
        if (number >= 100 && number <= 999) {
            result += digitToString(number / 100, 100);
            number %= 100;
        }
        if ((number >= 20 && number <= 99) || number==10) {
            result += digitToString(number / 10, 20);
            number %= 10;
        }
        else if (number >= 11 && number <= 19) {
            result += digitToString(number / 10, 10);
            number = 0;
        }
        if (number >= 1 && number <= 9) {
            result += digitToString(number, 1);
        }
        result = result.trim();
        return result;
    }

    /**
     * Changes numbers written with letters to written with digits
     *
     * @param text String with text to transform
     * @return String with transformed text
     */
    public String in_words(String text) {
        String result = "";
        String[] newTextArr = text.split("\\s");
        String regexDouble = "^-?\\d+([.,]\\d+)$";
        String regexInt = "^-?\\d+";

        for (String word : newTextArr) {
            //zamiana na słowo
            if (Pattern.matches(regexDouble, word)) {
                String[] parts = word.split("[.,]");
                if (parts[0].equals("-0")) {
                    result += "minus ";
                }
                int part1 = Integer.parseInt(parts[0]);
                int sizePoPrzecinku = parts[1].length();
                int part2 = Integer.parseInt(parts[1]);
                String newWord = intToWord(part1);
                newWord += " i ";
                newWord += intToWord(part2);
                if (sizePoPrzecinku == 1)
                    newWord += " dziesiąte";
                else if (sizePoPrzecinku == 2)
                    newWord += " setne";
                result += newWord;
            } else if (Pattern.matches(regexInt, word)) {
                int numberInt = Integer.parseInt(word);
                String newWord = intToWord(numberInt);
                result += newWord;
            } else {
                result += word;
            }
            result += " ";
        }
        return result;
    }

    public String repeats(String text){
        String[] newTextArr = text.split(" ");
        String now;
        String result = "";
        int i = 0;
        int j;

        while(i < newTextArr.length){
            now = newTextArr[i];
            if(i < newTextArr.length - 1) {
                j = i + 1;

                while(j < newTextArr.length) {
                    if (now.compareTo(newTextArr[j]) == 0)
                        i = j;
                    j++;
                }
                result += now + " ";
            }
            else
                result += now;
            i++;
        }

        return result.trim();
    }
}
