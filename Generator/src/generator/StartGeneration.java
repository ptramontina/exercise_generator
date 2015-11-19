
package generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.RecognitionException;

/**
 *
 * @author Pedro Ernesto Tramontina
 */
public class StartGeneration {    
    public static void main (String[] args) throws IOException, RecognitionException, Exception {
        
        int amount = 0;
        String type = "", outputPath = "", level = "";
        TranslatorParser.HintLevel hintLevel = null;
        TranslatorParser.Language languageOutput = null;        
        ArrayList<String> exerciseListTags = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> testTags = new HashMap<>();

        ArrayList<String> arguments = new ArrayList(Arrays.asList(args));

//        arguments.add("-t");
//        arguments.add("testlist");        
//        arguments.add("-i");
//        arguments.add("1");
//        arguments.add("string");
//        arguments.add("for");
//        arguments.add("math");
//        arguments.add("2");
//        arguments.add("string");
//        arguments.add("for");
//        arguments.add("input");
//        arguments.add("3");
//        arguments.add("string");
//
//        arguments.add("-t");
//        arguments.add("exerciselist");        
//        arguments.add("-i");
//        arguments.add("pedro");
//        arguments.add("for");
//        arguments.add("input");        
//        
//        arguments.add("-n");
//        arguments.add("10");
//        arguments.add("-ln");
//        arguments.add("c");
//        arguments.add("-h");
//        arguments.add("basic");
//        arguments.add("-o");
//        arguments.add("C:\\Users\\Pedro\\Desktop\\OutputTest");
//        arguments.add("-lv");
//        arguments.add("simple");
     
        //Test hint parameter
        if (arguments.contains("-h")) {
            String stringHintLevel = arguments.get(arguments.lastIndexOf("-h") + 1);
            if (stringHintLevel.equals("basic")) {
                hintLevel = TranslatorParser.HintLevel.BASIC;
            } else if (stringHintLevel.equals("medium")) {
                hintLevel = TranslatorParser.HintLevel.MEDIUM;
            } else if (stringHintLevel.equals("advanced")) {
                hintLevel = TranslatorParser.HintLevel.ADVANCED;
            } else {
                System.err.println("Wrong [-h] parameter!");
            }
        } else {
            System.err.print("Missing [-h] parameter!");
        }
        
        //Test language parameter
        if (arguments.contains("-ln")) {
            String stringLanguage = arguments.get(arguments.lastIndexOf("-ln") + 1);
            if (stringLanguage.equals("java")) {
                languageOutput = TranslatorParser.Language.JAVA;
            } else if (stringLanguage.equals("c")) {
                languageOutput = TranslatorParser.Language.C;
            } else if (stringLanguage.equals("c++")) {
                languageOutput = TranslatorParser.Language.C_PLUS_PLUS;
            } else if (stringLanguage.equals("python")) {
                languageOutput = TranslatorParser.Language.PYTHON;
            } else if (stringLanguage.equals("portuguese")) {
                languageOutput = TranslatorParser.Language.PORTUGUESE;
            } else {
                System.err.println("Wrong [-ln] parameter!");
            }
        } else {
            System.err.print("Missing [-ln] parameter!");
        }

        //Test level parameter
        if (arguments.contains("-lv")) {
            String stringLanguage = arguments.get(arguments.lastIndexOf("-lv") + 1);
            if (stringLanguage.equals("simple")) {
                level = "simple";
            } else if (stringLanguage.equals("medium")) {
                level = "medium";
            } else if (stringLanguage.equals("advanced")) {
                level = "advanced";
            } else {
                System.err.println("Wrong [-lv] parameter!");
            }
        } else {
            System.err.print("Missing [-lv] parameter!");
        }
        
        
        //Test number parameter
        if (arguments.contains("-n")) {
            try {
                amount = Integer.parseInt(arguments.get(arguments.lastIndexOf("-n") + 1));
            } catch (Exception e) {
                System.err.print("Argument [-n] incorrect!");
            }
        } else {
            System.err.println("Missing [-n] parameter!");
            System.exit(1);
        }

        if (arguments.contains("-o")) {
            outputPath = arguments.get(arguments.lastIndexOf("-o") + 1);
        } else {
            System.err.println("Missing [-o] parameter!");
            System.exit(1);
        }

        
        //Test type parameter, then test the input parameter
        //and generate exercises and tests
        if (arguments.contains("-t")) {
            type = arguments.get(arguments.lastIndexOf("-t") + 1);
            if (type.equals("testlist")) {
                testTags = readTestArguments(arguments);
                generateTest(testTags, amount, outputPath, languageOutput, hintLevel, level);
            } else if (type.equals("exerciselist")) {
                exerciseListTags = readExerciseListArguments(arguments);
                generateExerciseList(exerciseListTags, amount, outputPath, languageOutput, hintLevel, level);
            } else {
                System.err.println("Wrong [-t] parameter!");
            }
        } else {
            System.err.print("Missing [-t] parameter!");
        }
           
    }    
    
    /**
     * This method receives all parameters and generates an exercise list,
     * through Generator class.
     * @param exercisesAndTags
     * @param amount
     * @param outputPath
     * @param language
     * @param hintLevel
     * @param level
     * @throws Exception 
     */
    private static void generateTest (Map<Integer,ArrayList<String>> exercisesAndTags, int amount, String outputPath, TranslatorParser.Language language, TranslatorParser.HintLevel hintLevel, String level) throws Exception {
        Generator generator = new Generator();
        generator.generateTestList(exercisesAndTags, amount, outputPath, language, hintLevel, level);
    }
    
    /**
     * This method receives all parameters and generates an test list,
     * through Generator class.
     * @param tags
     * @param amount
     * @param outputPath
     * @param language
     * @param hintLevel
     * @param level
     * @throws Exception 
     */
    private static void generateExerciseList(ArrayList<String> tags, int amount, String outputPath, TranslatorParser.Language language, TranslatorParser.HintLevel hintLevel, String level) throws Exception {
        Generator generator = new Generator();
        generator.generateExerciseList(tags, amount, outputPath, language, hintLevel, level);
    }

    /**
     * This method reads and prepares all data within [-i] parameters,
     * when the parameter is 'exerciselist'.
     * @param arguments
     * @return 
     */
    private static ArrayList<String> readExerciseListArguments(ArrayList<String> arguments) {
        int argumentIndex = arguments.indexOf("-i");
        ArrayList<String> tags = new ArrayList<>();
       
        //If the argument is missing, prints error
        //otherwise, gets the index of next element
        if (argumentIndex == -1) {
            System.err.println("Missing [-i] parameter!");
            System.exit(1);
        } else {
            argumentIndex++;
        }

        while (!isNumeric(arguments.get(argumentIndex)) && !arguments.get(argumentIndex).contains("-") && argumentIndex != arguments.size()) {
            tags.add(arguments.get(argumentIndex));
            argumentIndex++;
        }
        
        if (tags.isEmpty()) {
            System.err.println("Error: Wrong [-i] parameters!");
            System.exit(1);
        }

        return tags;
    }

    /**
     * This method reads and prepares all data within [-i] parameters,
     * when the parameter is 'testlist'
     * @param arguments
     * @return 
     */
    private static HashMap<Integer, ArrayList<String>> readTestArguments(ArrayList<String> arguments) {
        int argumentIndex = arguments.indexOf("-i"), exercise = 1;
        ArrayList<String> tags = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> testExercisesAndTags = new HashMap<>();

        //If the argument is missing, prints error
        //otherwise, gets the index of next element
        if (argumentIndex == -1) {
            System.err.println("Missing [-i] parameter!");
            System.exit(1);
        } else {
            argumentIndex++;
        }

        while (isNumeric(arguments.get(argumentIndex))) {
            tags = new ArrayList<>();
            while (!isNumeric(arguments.get(argumentIndex+1)) && !arguments.get(argumentIndex+1).contains("-") && argumentIndex != arguments.size()) {
                tags.add(arguments.get(argumentIndex+1));
                argumentIndex++;
            }
            testExercisesAndTags.put(exercise, tags);
            exercise++;
            argumentIndex++;
        }

        if(testExercisesAndTags.isEmpty()) {
            System.err.println("Error: Wrong [-i] parameters!");
            System.exit(1);
        }
        return testExercisesAndTags;
    }

    /**
     * Checks if 'str' is a numbers
     * @param str
     * @return 
     */
    public static boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
