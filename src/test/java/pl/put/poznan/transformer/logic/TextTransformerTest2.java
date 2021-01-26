package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TextTransformerTest2{
    private TextTransformer transformer;
    String[] text = {};

    @BeforeEach
    public void setUp() {
        transformer  = new TextTransformer(text);
    }

    @Test
    public void testAbToAbbreviation() {
        Assertions.assertEquals("profesor", transformer.ab_to_abbreviation("prof."));
    }
    @Test
    public void testAbToAbbreviation2() {
        Assertions.assertEquals("Profesor", transformer.ab_to_abbreviation("Prof."));
    }
    @Test
    public void testAbToAbbreviation3() {
        Assertions.assertEquals("doktor", transformer.ab_to_abbreviation("dr"));
    }
    @Test
    public void testAbToAbbreviation4() {
        Assertions.assertEquals("Doktor", transformer.ab_to_abbreviation("Dr"));
    }

    @Test
    public void testAbbreviationToAb() {
        Assertions.assertEquals("np.", transformer.abbreviation_to_ab("na przykład"));
    }
    @Test
    public void testAbbreviationToAb2() {
        Assertions.assertEquals("Np.", transformer.abbreviation_to_ab("Na przykład"));
    }
    @Test
    public void testAbbreviationToAb3() {
        Assertions.assertEquals("m.in.", transformer.abbreviation_to_ab("między innymi"));
    }
    @Test
    public void testAbbreviationToAb4() {
        Assertions.assertEquals("M.in.", transformer.abbreviation_to_ab("Między innymi"));
    }

    @Test
    public void testLatex() {
        Assertions.assertEquals("\\&", transformer.latex("&"));
    }
    @Test
    public void testLatex2() {
        Assertions.assertEquals("\\$", transformer.latex("$"));
    }

    @Test
    public void testInWords() {
        Assertions.assertEquals("Daj sto złotych", transformer.in_words("Daj 100 złotych"));
    }

    @Test
    public void testRepeats1() {
        Assertions.assertEquals("Daj sto złotych", transformer.repeats("Daj sto sto złotych"));
    }
    @Test
    public void testRepeats2() {
        Assertions.assertEquals("Daj dwa banknoty po sto złotych", transformer.repeats("Daj dwa dwa banknoty po po sto sto złotych"));
    }
    @Test
    public void testRepeats3() {
        Assertions.assertEquals("hej", transformer.repeats("hej hej"));
    }
}
