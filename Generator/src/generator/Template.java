/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import freemarker.template.TemplateException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** Description of Template 
 *
 * @author Pedro Ernesto Tramontina
 * @version 1.0 Oct 07, 2015.
 */
public class Template {
    
    //Template variables
    private String title, 
                   code, 
                   level, 
                   topic, 
                   description, 
                   pseudocode, 
                   solution;
    
    private String markDownTitle, 
                   markDownCode, 
                   markDownLevel, 
                   markDownTopic, 
                   markDownTags, 
                   markDownParameters,
                   markDownDescription, 
                   markDownPseudocode, 
                   markDownExpectedResults, 
                   markDownHints;

    private String path;
    private ArrayList<String> tags = new ArrayList<>();
    private ArrayList<String> expectedResults = new ArrayList<>();
    private ArrayList<String> hints = new ArrayList<>();
    private ArrayList<TemplateParameter> parameters = new ArrayList<>();
 
    public Template(File file) {
        setPath(file.getPath());
        readTemplate(file);
        getTextWithoutMarkdown();
    }
        
    public GeneratedTemplate generateOutput (TranslatorParser.Language outputLanguage, TranslatorParser.HintLevel hintLevel) throws IOException, TemplateException, Exception {

        GeneratedTemplate generatedTemplate = new GeneratedTemplate();
        String filledDescription, filledPseudocode;
        Map <String, String> randomParameters = generateRandomParameters(parameters);        
        filledDescription = fillText(description, randomParameters);
        filledPseudocode = fillText(pseudocode, randomParameters);
        
        switch (outputLanguage) {
            case JAVA:
                TranslatorParser.setOutputLanguage(TranslatorParser.Language.JAVA);
                break;
            case C:
                TranslatorParser.setOutputLanguage(TranslatorParser.Language.C);
                break;
            case C_PLUS_PLUS:
                TranslatorParser.setOutputLanguage(TranslatorParser.Language.C_PLUS_PLUS);
                break;  
            case PYTHON:
                TranslatorParser.setOutputLanguage(TranslatorParser.Language.PYTHON);
                break;                  
            case PORTUGUESE:
                TranslatorParser.setOutputLanguage(TranslatorParser.Language.PORTUGUESE);
                break;                  
        }
        
        switch (hintLevel) {
            case BASIC:
                TranslatorParser.setHintLevel(TranslatorParser.HintLevel.BASIC);
                break;
            case MEDIUM:
                TranslatorParser.setHintLevel(TranslatorParser.HintLevel.MEDIUM);
                break;
            case ADVANCED:
                TranslatorParser.setHintLevel(TranslatorParser.HintLevel.ADVANCED);
                break;                       
        }
                
        TranslatorParser.translate(fillText(pseudocode, randomParameters));
        solution = TranslatorParser.getSolution();
        
        //'currentHints' variable was created because the same template might be selected
        //lots of times. And if the hints generated from TranslatorParser were added directly,
        //the hints duplicate
        ArrayList<String> currentHints = new ArrayList<>();        
        for(String hint : hints) {
            currentHints.add(hint);
        }
        currentHints.addAll(TranslatorParser.getHints());
        
        for (int i = 0; i < currentHints.size(); i++) {
            String hint = currentHints.get(i);
            if(hint.contains("${")) {
                currentHints.set(i, fillText(hint, randomParameters));
            }
        }
        
        //Checks if the expected results need to be filled with FreeMarker
        for (int i = 0; i < expectedResults.size(); i++) {
            String expectedResult = expectedResults.get(i);
            if(expectedResult.contains("${")) {
                expectedResults.set(i, fillText(expectedResult, randomParameters));
            }            
        }
        
        generatedTemplate.setTitle(title);
        generatedTemplate.setCode(code);
        generatedTemplate.setLevel(level);
        generatedTemplate.setTopic(topic);
        generatedTemplate.setTags(tags);
        generatedTemplate.setDescription(filledDescription);
        generatedTemplate.setPseudocode(filledPseudocode);
        generatedTemplate.setExpectedResults(expectedResults);
        generatedTemplate.setHints(currentHints); 
        generatedTemplate.setSolution(solution);
              
        return generatedTemplate;
    }
    
    private String fillText (String textToBeFilled, Map <String,String> parameters) throws IOException, TemplateException {
        String filledTemplate;
        FreeMarkerEngine freeMarker = new FreeMarkerEngine();
        
        filledTemplate = freeMarker.fillTemplate(textToBeFilled, parameters);
        return filledTemplate;
    }
    
    private Map generateRandomParameters (ArrayList<TemplateParameter> templateParameters) {
        Map <String,String> mapParameters = new HashMap();
        
        for (TemplateParameter parameter : templateParameters) {
            mapParameters.put(parameter.getParameterName(), parameter.getRandomValue());
        }
        
        return mapParameters;
    }
    
    private void getTextWithoutMarkdown () {
        title = markDownTitle.replace("# ", "").trim();
        code = markDownCode.replace("### code:", "").trim();
        level = markDownLevel.replace("### level:", "").trim();
        topic = markDownTopic.replace("### topic:", "").trim();
        tags = new ArrayList(Arrays.asList(markDownTags.replace("### tags:", "").trim().split(" ")));
        parameters = getParametersWithoutMarkDown(markDownParameters);
        description = markDownDescription.replace("## Description", "").trim();
        pseudocode = getPseudocodeWithoutMarkDown(markDownPseudocode);     
        expectedResults = getExpectedResultsWithoutMarkDown(markDownExpectedResults);
        hints = getHintsWithoutMarkDown(markDownHints);
    }
    
    private ArrayList<TemplateParameter> getParametersWithoutMarkDown (String markDownParameters) {
        ArrayList<String> stringParameters = new ArrayList<>();
        ArrayList<TemplateParameter> parameters = new ArrayList<>();
        
        //Removes the Markdown code '## Parameters'
        markDownParameters = markDownParameters.replace("## Parameters", "").trim();

        //Splits parameters by line
        stringParameters = new ArrayList(Arrays.asList(markDownParameters.split("\r?\n")));
        for (String parameter : stringParameters) {
            //If parameter is not an input, inserts it in parameter list
            if(!parameter.contains("input")) {
                parameters.add(new TemplateParameter(parameter.trim()));
            }
        }

        return parameters;
    }
    
    private ArrayList<String> getHintsWithoutMarkDown (String markDownHints) {
        ArrayList<String> hints = new ArrayList<>();
        
        //Removes the Markdown code '## Hints'
        markDownHints = markDownHints.replace("## Hints", "").trim();

        //Removes the character '-' at the beginning of the string
        hints = new ArrayList(Arrays.asList(markDownHints.split("\r?\n")));
        for (String hint : hints) {
            hints.set(hints.indexOf(hint), hint.replace("-", "").trim());
        }        
        return hints;
    }

    private ArrayList<String> getExpectedResultsWithoutMarkDown (String markDownExpectedResults) {
        ArrayList<String> expectedResults = new ArrayList<>();
        
        //Removes the Markdown code '## Expected results'
        markDownExpectedResults = markDownExpectedResults.replace("## Expected results", "").trim();

        //Removes the character '-' at the beginning of the string
        expectedResults = new ArrayList(Arrays.asList(markDownExpectedResults.split("\r?\n")));
        for (String expectedResult : expectedResults) {
            expectedResults.set(expectedResults.indexOf(expectedResult), expectedResult.replace("-", "").trim());
        }        
        
        return expectedResults;
    }
    
    private String getPseudocodeWithoutMarkDown(String markDownPseudocode) {
        markDownPseudocode = markDownPseudocode.substring(markDownPseudocode.indexOf("\n")+1);
        
        ArrayList<String> splittedPseudocode = new ArrayList<>();
        splittedPseudocode = new ArrayList(Arrays.asList(markDownPseudocode.split("\r?\n")));
        
        for(String pseudocodeLine : splittedPseudocode) {
            int indexOfPseudocodeLine = splittedPseudocode.indexOf(pseudocodeLine);
            if(pseudocodeLine.length() > 4) {
                splittedPseudocode.set(indexOfPseudocodeLine, pseudocodeLine.substring(4));
            } 
            splittedPseudocode.set(indexOfPseudocodeLine, splittedPseudocode.get(indexOfPseudocodeLine)+"\n");
        }
        
        return arrayListToString(splittedPseudocode);
    }

    private void readTemplate (File file) {
        String wholeString = readTextFromFile(file);
       
        markDownTitle = readUpToDelimiter(wholeString, "### code");
        wholeString = removeUpToDelimiter(wholeString, "### code");

        markDownCode = readUpToDelimiter(wholeString, "### level");       
        wholeString = removeUpToDelimiter(wholeString, "### level");
        
        markDownLevel = readUpToDelimiter(wholeString, "### topic");       
        wholeString = removeUpToDelimiter(wholeString, "### topic");

        markDownTopic = readUpToDelimiter(wholeString, "### tags");       
        wholeString = removeUpToDelimiter(wholeString, "### tags");
        
        markDownTags = readUpToDelimiter(wholeString, "## Parameters");       
        wholeString = removeUpToDelimiter(wholeString, "## Parameters");
        
        markDownParameters = readUpToDelimiter(wholeString, "## Description");       
        wholeString = removeUpToDelimiter(wholeString, "## Description");
               
        markDownDescription = readUpToDelimiter(wholeString, "## Pseudocode");       
        wholeString = removeUpToDelimiter(wholeString, "## Pseudocode");
        
        markDownPseudocode = readUpToDelimiter(wholeString, "## Expected results");       
        wholeString = removeUpToDelimiter(wholeString, "## Expected results");
        
        markDownExpectedResults = readUpToDelimiter(wholeString, "## Hints");       
        wholeString = removeUpToDelimiter(wholeString, "## Hints");
        
        markDownHints = removeUpToDelimiter(wholeString, "");       
        wholeString = removeUpToDelimiter(wholeString, "");
    }
    
    public void setPath (String path) {
        this.path = path;
    }
    
    private String readBetweenDelimiters (String string, String firstDelimiter, String secondDelimiter) {
        try {
            return string.substring(string.indexOf(firstDelimiter), string.indexOf(secondDelimiter));
        } catch (Exception e) {
            return null;
        }
    }

    private String readUpToDelimiter (String string, String delimiter) {
        try {
            return string.substring(0, string.indexOf(delimiter));
        } catch (Exception e) {
            return null;
        }        
    } 

    private String removeUpToDelimiter (String string, String delimiter) {
        try {
            return string.substring(string.indexOf(delimiter));
        } catch (Exception e) {
            return null;
        }        
    }    
        
    private String readTextFromFile(File file) {
        String textFromFile = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int character;

            while ((character = reader.read()) != -1) {
                textFromFile += (char) character;
            }
            reader.close();
        } catch (Exception e) {
            System.err.print("Error occurred trying to read '%s'.");
            e.printStackTrace();
        }
        return textFromFile;
    }
    
    private String arrayListToString (ArrayList<String> stringArrayList) {
        String joinedString = "";
        for(String str : stringArrayList) {
            joinedString += str;
        }
        return joinedString;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @return the tags
     */
    public ArrayList<String> getTags() {
        return tags;
    }
    
    /**
     * @return the tags
     */
    public ArrayList<String> getExpectedResults() {
        return expectedResults;
    }
    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }
    
    public boolean hasTag (String tag) {
        return tags.contains(tag);
    }
    
    public boolean isTopic (String topic) {
        return this.topic.equals(topic);
    }
    
    public boolean isLevel (String level) {
        return this.level.equals(level);
    }

}
