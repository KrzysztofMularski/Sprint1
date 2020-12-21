package pl.put.poznan.transformer.logic;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import org.apache.commons.text.WordUtils;

import java.util.Locale;

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
}
