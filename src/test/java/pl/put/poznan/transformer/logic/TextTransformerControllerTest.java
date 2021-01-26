package pl.put.poznan.transformer.logic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.rest.TextTransformerController;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextTransformerControllerTest {
    private TextTransformer transformer = mock(TextTransformer.class);
    @Test
    public String testPost() {
        String text = "test";
        String[] transforms = {"upper"};
        String resulttext = "TEST";
        when(transformer.transform(text)).thenReturn(resulttext);
        TextTransformerController controller = new TextTransformerController(transformer);
        Assertions.assertEquals(resulttext, controller.post(text, transforms));
        return transformer.transform(text);
    }
}
