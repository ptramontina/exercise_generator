package generator;

import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Pedro Ernesto Tramontina
 */
public class Generator {
    
    /**
     * Constants
     */
    private static final String TEMPLATES_PATH = "src/Templates";
    
    /**
     * Variables
     */
    private ArrayList <Template> templateList = new ArrayList<>();
    
    
    public Generator () {
        loadTemplates();
    }
    
    private ArrayList<File> getFilesFromDirectory (String path) {
       File filePath = new File(path);
       File[] fileList = filePath.listFiles();
      
       return new ArrayList<>(Arrays.asList(fileList));
    }
    
    private void insertTemplateIntoList (File file) {
        Template exercise = new Template(file);                
        templateList.add(exercise);
    }
    
    private void loadTemplates () {
        ArrayList <File> fileList = getFilesFromDirectory(TEMPLATES_PATH);
        
        for (File file : fileList) {
            insertTemplateIntoList(file);
        }
    }
    
    //Return an ArrayList with all Templates specified by type
    //If there isn't any, return an empty ArrayList
    private ArrayList <Template> selectTemplatesByTopic (ArrayList <Template> allTemplates, String topic) {
        ArrayList <Template> specificTopicTemplateList = new ArrayList<> ();
        
        for (Template template : allTemplates) {
            if(template.isTopic(topic)) {
                specificTopicTemplateList.add(template);
            }
        } 
        
        return specificTopicTemplateList;
    }
    
    public ArrayList<Template> selectTemplateByTags (ArrayList <Template> allTemplates, ArrayList<String> tags) {
        ArrayList<Template> selectedTemplates = new ArrayList<>();
        
        for (Template template : allTemplates) {
            for (String tag : tags) {
                if(template.hasTag(tag)) {
                    selectedTemplates.add(template);
                    //Breaks because template can't be chosen and added again
                    break;
                }
            }
        }
        
        return selectedTemplates;
    }
    
    public ArrayList<Template> selectTemplateByTopicTagsLevel (ArrayList<Template> allTemplates, ArrayList<String> topicAndTags, String level) {
        String topic = "";
        ArrayList<String> tags=new ArrayList<>();
        ArrayList<Template> selectedTemplates;
        topic = topicAndTags.get(0);
        
        //Loop to get only tags, so, the first is ignored
        //The '.remove()' function cannot be used, because 
        //it removes the original 'topicAndTags' sent by the 
        //method that uses 'selectTemplateByTopicTagsLevel'
        for(int i=1;i<topicAndTags.size();i++) {
            tags.add(topicAndTags.get(i));
        }
        
        selectedTemplates = selectTemplatesByLevel(allTemplates, level);
        if (selectedTemplates.isEmpty()) {
            System.out.println("No templates found with level '"+level+"'");
            System.exit(0);            
        }
        
        selectedTemplates = selectTemplatesByTopic(allTemplates, topic);
        if (tags.size() > 0) {
             selectedTemplates = selectTemplateByTags(selectedTemplates, tags);
        }
        
        if(selectedTemplates.isEmpty()) {
            System.out.println("No templates found with topic '"+topic+"' and 'Tags' "+tags.toString());
            System.exit(0);
        }
        
        return selectedTemplates;
    }
    
    private ArrayList <Template> selectTemplatesByLevel (ArrayList <Template> allTemplates, String level) {
        ArrayList <Template> specificLevelTemplateList = new ArrayList<> ();
        
        for (Template template : allTemplates) {
            if(template.isLevel(level)) {
                specificLevelTemplateList.add(template);
            }
        } 
        
        return specificLevelTemplateList;    
    } 
      
    public void generateExerciseList (ArrayList<String> tags, int amount, String outputPath, TranslatorParser.Language language, TranslatorParser.HintLevel hintLevel, String level) throws TemplateException, Exception {
        Template selectedTemplate;
        GeneratedTemplate generatedTemplate;
        String generatedOutput;
        ArrayList<Template> selectedTemplates = selectTemplateByTopicTagsLevel(templateList, tags, level);
        
        for(int timesExecuted = 0; timesExecuted < amount; timesExecuted++) {

            generatedOutput = "";
            selectedTemplate = selectedTemplates.get(new Random().nextInt(selectedTemplates.size()));
            generatedTemplate = selectedTemplate.generateOutput(language, hintLevel);
            
            generatedOutput += generatedTemplate.getTitle();
            generatedOutput += generatedTemplate.getCode();
            generatedOutput += generatedTemplate.getLevel();
            generatedOutput += generatedTemplate.getTopic();
            generatedOutput += generatedTemplate.getTags();
            generatedOutput += generatedTemplate.getDescription();
            generatedOutput += generatedTemplate.getPseudocode();
            generatedOutput += generatedTemplate.getExpectedResults();
            generatedOutput += generatedTemplate.getHints();
            generatedOutput += generatedTemplate.getSolution();
            
            //If the last character of 'outputPath' is not a '\', inserts it.
            createAndSaveFile("Exercise_"+(timesExecuted+1)+".md", outputPath.charAt(outputPath.length()-1)=='\\' ? outputPath : outputPath+"\\", generatedOutput);
        }
    }
    /**
     * 
     * @param exercisesAndTags Map that contains all exercises with 
     *                         their tags.
     * @param amount Amount of tests to be created.
     * @param outputPath Path to save all generated tests.
     * @param language The language to generate 
     * @param hintLevel The level of hints to send 
     * @param level
     * @throws TemplateException FreeMarker exception
     * @throws Exception 
     */
    public void generateTestList (Map<Integer, ArrayList<String>> exercisesAndTags,int amount, String outputPath, TranslatorParser.Language language, TranslatorParser.HintLevel hintLevel, String level) throws TemplateException, Exception {
        Template selectedTemplate;
        String testOutput="", answerOutput="";
        GeneratedTemplate generatedTemplate;
        ArrayList<Template> selectedTemplates;  
        
        for(int timesExecuted = 0; timesExecuted < amount; timesExecuted++) {            
            testOutput = "";
            answerOutput = "";
            for (Map.Entry<Integer, ArrayList<String>> entry : exercisesAndTags.entrySet()) {
        
                selectedTemplates = selectTemplateByTopicTagsLevel(templateList, entry.getValue(), level);
                selectedTemplate = selectedTemplates.get(new Random().nextInt(selectedTemplates.size()));
                generatedTemplate = selectedTemplate.generateOutput(language, hintLevel);                
                
                testOutput += "___\n# Exercise "+entry.getKey()+" - ";
                testOutput += generatedTemplate.getTitle().replace("# ", "")+"___\n";
                testOutput += generatedTemplate.getDescription();
                testOutput += "\n\n";
                
                answerOutput += "___\n# Exercise  "+entry.getKey()+" - *Hints and Answers*\n___\n";
                answerOutput += generatedTemplate.getTitle();
                answerOutput += generatedTemplate.getHints();
                answerOutput += generatedTemplate.getExpectedResults();
                answerOutput += generatedTemplate.getPseudocode();
                answerOutput += generatedTemplate.getSolution();                
            }
            
            //If the last character of 'outputPath' is not a '\', inserts it.
            createAndSaveFile("Test_"+(timesExecuted+1)+".md", outputPath.charAt(outputPath.length()-1)=='\\' ? outputPath : outputPath+"\\", testOutput);
            createAndSaveFile("Answer_Test_"+(timesExecuted+1)+".md", outputPath.charAt(outputPath.length()-1)=='\\' ? outputPath : outputPath+"\\", answerOutput);
        }
    }
    
    /**
     * Method that receives some text (textToBeSaved), then creates 
     * a file and saves on the 'outputPath' with the name 'fileName'
     * 
     * @param fileName The output file's name
     * @param outputPath The output file's path
     *                   (where the file will be saved).
     * @param textToBeSaved Text to be inserted on the saved file
     */
    private void createAndSaveFile (String fileName, String outputPath, String textToBeSaved) {
        try {
            File newTextFile = new File(outputPath+fileName);

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(textToBeSaved);
            fw.close();

        } catch (FileNotFoundException fe) { 
            System.err.println("Error saving file '"+fileName+"'!\nCannot find '"+outputPath+"'");
            System.exit(1);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    
}
