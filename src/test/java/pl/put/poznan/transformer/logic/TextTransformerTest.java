package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TextTransformerTest {
    private TextTransformer transformer;
    String[] text = {};

    @BeforeEach
    public void setUp() {
        transformer  = new TextTransformer(text);
    }

    @Test
    public void testUpper() {
        Assertions.assertEquals("BIG", transformer.upper("Big"));
    }

    @Test
    public void testLower() {
        Assertions.assertEquals("lower", transformer.lower("LOWeR"));
    }

    @Test
    public void testCapitalize(){
        Assertions.assertEquals("Hey Hey", transformer.capitalize("hEY hEy"));
    }

    @Test
    public void testReverse1() {
        Assertions.assertEquals("RevERse", transformer.reverse("EsrEVer"));
    }

    @Test
    public void testReverse2() {
        Assertions.assertEquals("no HEjKA", transformer.reverse("akjEH ON"));
    }

    @Test
    public void testReverse3() {
        Assertions.assertEquals("siema SIEMA", transformer.reverse("ameis AMEIS"));
    }
}