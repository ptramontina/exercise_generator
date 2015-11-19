package generator;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * <h1>FreeMarkerEngine</h1>
 * The FreeMarkerEngine is the responsable for filling all blank spaces
 * on all templates. It uses the FreeMarker library, and receives the
 * parameters and inserts where the ${var} are situated.
 * 
 * @author Pedro Ernesto Tramontina
 * @version 1.0
 */
public class FreeMarkerEngine {
    
    private Configuration cfg;
    
    /**
     * 
     */
    public FreeMarkerEngine () {
        configureFreeMarker();
    }
    
    /**
     * Method that is called in constructor, and configures
     * FreeMarker for this object.
     */
    private void configureFreeMarker () {
        /* Create and adjust the configuration singleton */
        cfg = new Configuration(Configuration.VERSION_2_3_22);
        //cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);        
    }
    
    /**
     * @param templateToBeFilled Text to be filled
     * @param mapParameters All parameters that FreeMarker uses
     *                      to fill the text
     * @return The filled text
     * @throws IOException 
     * @throws TemplateException 
     */
    public String fillTemplate (String templateToBeFilled, Map <String,String> mapParameters) throws IOException, TemplateException {

        freemarker.template.Template temp = new freemarker.template.Template("", new StringReader(templateToBeFilled), cfg);
        
        Writer filledTemplate = new StringWriter();
        temp.process(mapParameters, filledTemplate);
        
        return filledTemplate.toString();
    }
    
}
