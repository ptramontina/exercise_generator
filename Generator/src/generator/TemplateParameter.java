/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;



/**
 *
 * @author Pedro
 */
public class TemplateParameter {
    
    //Constants
    public enum Type {
        INTEGER, DOUBLE, CHARACTER, BOOLEAN, STRING;   
    }
    
    //Variables
    private String parameterName;
    private Type parameterType;
    private int integerMinValue;
    private int integerMaxValue;
    private double doubleMinValue;
    private double doubleMaxValue;
    private char characterMinValue;
    private char characterMaxValue;
    private ArrayList<String> stringBounds;
    
    public TemplateParameter (String templateParameter) {
        splitTemplateParameter(templateParameter);
    }
    
    private void splitTemplateParameter (String templateParameter) {
        String bounds, type;

        //Removes the '<' and '>' characters from the template
        templateParameter = templateParameter.substring(templateParameter.indexOf("<")+1, templateParameter.indexOf(">"));
        
        //Gets the paramater name before the '=' character
        String[] splittedTemplate = templateParameter.split("=");
        parameterName = splittedTemplate[0].trim();

        //If is not a boolean, gets the type and text between '[' and ']' characters
        //Otherwise, gets only type, because boolean does not need bounds
        if(!splittedTemplate[1].contains("boolean")) {
            type = splittedTemplate[1].substring(0, splittedTemplate[1].indexOf("[")-1).trim();
            bounds = splittedTemplate[1].substring(splittedTemplate[1].indexOf("[")).trim();
        } else {
            type = splittedTemplate[1].trim();
            bounds = "";
        }
        
        //Sets type and bounds
        setType(type);
        setBounds(bounds);
    }
    
    private void setType(String type) {
        if(type.contains("integer")) {
            parameterType = Type.INTEGER;
        } else if (type.contains("double")) {
            parameterType = Type.DOUBLE;
        } else if (type.contains("char")) {
            parameterType = Type.CHARACTER;
        } else if (type.contains("string")) {
            parameterType = Type.STRING;
        } else if (type.contains("boolean")) {
            parameterType = Type.BOOLEAN;
        } else {
            System.err.print("There's an error with parameter in template!");
        }
    }
    
    private void setBounds (String bounds) {
        switch (parameterType) {
            case INTEGER:
                setIntegerBounds(bounds);
                break;
            case DOUBLE:
                setDoubleBounds(bounds);
                break;
            case CHARACTER:
                setCharacterBounds(bounds);
                break;
            case STRING:
                setStringBounds(bounds);
                break;
            case BOOLEAN:
                break;
        }
    }
    
    private void setIntegerBounds(String bounds) {
        String[] splittedBounds = new String [2];
        bounds = bounds.substring(bounds.indexOf("[")+1, bounds.indexOf("]"));
        
        splittedBounds = bounds.split("-");
        
        integerMinValue = Integer.parseInt(splittedBounds[0]);
        integerMaxValue = Integer.parseInt(splittedBounds[1]);
    }
    
    private void setDoubleBounds (String bounds) {
        String[] splittedBounds = new String [2];
        bounds = bounds.substring(bounds.indexOf("[")+1, bounds.indexOf("]"));
        
        splittedBounds = bounds.split("-");
        
        doubleMinValue = Double.parseDouble(splittedBounds[0]);
        doubleMaxValue = Double.parseDouble(splittedBounds[1]);
    }
    
    private void setCharacterBounds (String bounds) {
        String[] splittedBounds = new String [2];
        bounds = bounds.substring(bounds.indexOf("[")+1, bounds.indexOf("]"));
        
        splittedBounds = bounds.split("-");
        
        characterMinValue = splittedBounds[0].charAt(1);
        characterMaxValue = splittedBounds[1].charAt(1);
    }
    
    private void setStringBounds (String bounds) {
        bounds = bounds.substring(bounds.indexOf("[")+1, bounds.indexOf("]"));
        
        this.stringBounds = new ArrayList(Arrays.asList(bounds.split(",")));
    }
    
    public String getParameterName () {
        return parameterName;
    }
    
    public Type getParameterType () {
        return parameterType;
    }
    
    public String getRandomValue () {
        String randomValue = "";
        switch (parameterType) {
            case INTEGER:
                randomValue = getRandomInteger();
                break;
            case DOUBLE:
                randomValue = getRandomDouble();
                break;
            case CHARACTER:
                randomValue = getRandomCharacter();
                break;
            case STRING:
                randomValue = getRandomString();
                break;
            case BOOLEAN:
                randomValue = getRandomBoolean();
                break;
        }
        return randomValue;
    }
    
    private String getRandomInteger() {
        Random random = new Random();
        return ""+(random.nextInt((integerMaxValue - integerMinValue) + 1) + integerMinValue);
    }
    
    private String getRandomDouble() {
        Random random = new Random();
        return ""+(doubleMinValue + (doubleMaxValue - doubleMinValue) * random.nextDouble());
    }    
    
    private String getRandomString() {
        Random random = new Random();
        int randomIndex = random.nextInt(stringBounds.size());
        return stringBounds.get(randomIndex)+"";
    }
    
    private String getRandomBoolean() {
        Random random = new Random();        
        return ""+random.nextBoolean();
    }
    
    private String getRandomCharacter() {
        Random random = new Random ();                
        String characterBound = "";
        char currentCharacter = characterMinValue;
        
        while (currentCharacter <= characterMaxValue) {
            characterBound += currentCharacter;
            currentCharacter++;
        }
        return characterBound.charAt(random.nextInt(characterBound.length()))+"";
    }
    
}
