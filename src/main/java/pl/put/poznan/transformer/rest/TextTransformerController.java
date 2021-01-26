package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;

/**
 * Class which controls whole logic part of application
 */
@RestController
public class TextTransformerController {

    int transformerPresent;
    TextTransformer transformer;

    public TextTransformerController(TextTransformer trans){
        this.transformerPresent = 1;
        transformer = trans;

    }
    public TextTransformerController(){
        this.transformerPresent = 0;
    }

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Transforms user-defined text according to transformations
     *
     * @param text Text to be transformed
     * @param transforms Array of transformations
     * @return
     */
    public String post(@RequestBody String text,
                       @RequestParam String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        if (this.transformerPresent == 0){
            transformer = new TextTransformer(transforms);
        }
        return transformer.transform(text);
    }

}


