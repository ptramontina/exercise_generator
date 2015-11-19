/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author petramontina
 */
public class GeneratedTemplate {
    
    private String title;
    private String code;
    private String level;
    private String topic;
    private String solution;
    private String description;
    private String pseudocode;
    private ArrayList<String> tags;
    private ArrayList<String> expectedResults;
    private ArrayList<String> hints;
    
    public GeneratedTemplate () {
        
    }

    /**
     * @return the code
     */
    public String getCode() {
        String code = "\n### code: "+this.code;
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the level
     */
    public String getLevel() {
        return "\n### level: "+level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return "\n### topic: "+topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        String outputTags = "\n### tags: ";
        for (String tag : tags) {
            outputTags += tag+" ";
        }
        return outputTags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        String outputDescription="";
        outputDescription += "\n";
        outputDescription += "## Description\n";
        outputDescription += "\n";
        outputDescription += description;
        
        return outputDescription;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the pseudocode
     */
    public String getPseudocode() {
        String outputPseudocode = "\n";
        outputPseudocode += "## Pseudocode";
        outputPseudocode += "\n";
        
        ArrayList<String> arrayPseudocode = new ArrayList(Arrays.asList(pseudocode.split("\r?\n")));
        for(String pseudocodeLine : arrayPseudocode) {
            outputPseudocode += "    "+pseudocodeLine+"\n";
        }
        
        return outputPseudocode;
    }

    /**
     * @param pseudocode the pseudocode to set
     */
    public void setPseudocode(String pseudocode) {
        this.pseudocode = pseudocode;
    }

    /**
     * @return the expectedResults
     */
    public String getExpectedResults() {

        String outputExpectedResults = "";
        outputExpectedResults += "\n";
        outputExpectedResults += "## Expected Results\n";
        outputExpectedResults += "\n";
        for (String expectedResult : expectedResults) {
            //Checks if the line is empty. If true, the '-' should not be printed.
            if(!expectedResult.equals(""))
                outputExpectedResults += "- "+expectedResult+"\n";
        }
        return outputExpectedResults;
    }

    /**
     * @param expectedResults the expectedResults to set
     */
    public void setExpectedResults(ArrayList<String> expectedResults) {
        this.expectedResults = expectedResults;
    }

    /**
     * @return the hints
     */
    public String getHints() {

        String outputHints = "";
        outputHints += "\n";
        outputHints += "## Hints\n";
        outputHints += "\n";
             
        for (String hint : hints) {
            //Checks if the line is empty. If true, the '-' should not be printed.
            if(!hint.equals(""))
                outputHints += "- "+hint+"\n";
        }        
        return outputHints;
    }

    /**
     * @param hints the hints to set
     */
    public void setHints(ArrayList<String> hints) {
        this.hints = hints;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return "# "+title+"\n";        
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the solution
     */
    public String getSolution() {
        String outputSolution = "\n";
        outputSolution += "## Solution\n";
        outputSolution += "\n";
        
        ArrayList<String> arraySolution = new ArrayList(Arrays.asList(solution.split("\r?\n")));
        for(String solutionLine : arraySolution) {
            outputSolution += "    "+solutionLine+"\n";
        } 
        
        return outputSolution;
    }

    /**
     * @param solution the solution to set
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    
    
}
