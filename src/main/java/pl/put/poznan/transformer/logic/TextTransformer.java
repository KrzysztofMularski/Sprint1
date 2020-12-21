package pl.put.poznan.transformer.logic;

import org.apache.commons.text.WordUtils;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }

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
            }
        }
        return newText;
    }

    public String upper(String text){
        // of course, normally it would do something based on the transforms
        return text.toUpperCase();
    }

    public String lower(String text){
        return text.toLowerCase();
    }

    public String capitalize(String text){
        return WordUtils.capitalizeFully(text);
    }

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

    public String abbreviation_to_ab(String text){
        String replaceString = text;

        replaceString = replaceString.replaceAll("\\bprofesor\\b","prof.");
        replaceString = replaceString.replaceAll("\\bProfesor\\b","Prof.");
        replaceString = replaceString.replaceAll("\\bdoktor\\b","dr");
        replaceString = replaceString.replaceAll("\\bDoktor\\b","Dr");
        replaceString = replaceString.replaceAll("\\bna przykład\\b","np.");
        replaceString = replaceString.replaceAll("\\bNa przykład\\b","Np.");
        replaceString = replaceString.replaceAll("\\bi tym podobne\\b","itp.");
        replaceString = replaceString.replaceAll("\\bI tym podobne\\b","Itp.");
        replaceString = replaceString.replaceAll("\\bi tak dalej\\b","itd.");
        replaceString = replaceString.replaceAll("\\bI tak dalej\\b","Itd.");

        return replaceString;
    }
}
