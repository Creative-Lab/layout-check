package org.jabong.com.auto_test_web_consistency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptureLayout {
	
	WebDriver driverInstance = null;
	
	public CaptureLayout(WebDriver driver){
		this.driverInstance = driver;
	}
	
	public String getComputedStyleOfWebObject(WebElement testObject){
    	
		String js = "var map = {};" + 
    			"var css = window.getComputedStyle(arguments[0]);" + 
    			"for(var i=0; i<css.length; i++)" + 
    			"{var value = css.getPropertyValue(css[i]);if(value.indexOf(\"rgb\")>-1){value=value.replace(/,/g ,\":\")};" + 
    			"var key = css[i];" + 
    			"map[key]=value;} " + 
    			"return map;";
    	Object css = ((JavascriptExecutor)driverInstance).executeScript(js,testObject);  
    	
    	List<String> cssKeyValues = Arrays.asList(css.toString().split(","));
    	
    	List<String> CSSSTYLES = new ArrayList<String>();
    	
    	for (String keyValues : cssKeyValues) {
    		CSSSTYLES.add(keyValues);			
		}
    	
    	return css.toString();
    }
	
    public String getAttributesOfAWebObject(WebElement testObject){
    	String js = "var map = {}; var attribute_count = arguments[0].attributes.length;" +
    				"for(var i=0;i<attribute_count;i++){" +
    				"var attributename = arguments[0].attributes[i].name;"+
    				"var attributevalue = arguments[0].attributes[i].value;"+
    				"map[attributename]=attributevalue;}" +
    				"return map;";
    	Object attributes = ((JavascriptExecutor)driverInstance).executeScript(js,testObject);
    	return attributes.toString();
    }
    
    public String getLocationOfWebElement(WebElement testObject){
    	Point coordinates = testObject.getLocation();
    	return coordinates.toString();
    }
    
    public String getDimensionsOfWebObject(WebElement testObject){
    	Dimension dimensions = testObject.getSize();
    	return dimensions.toString();
    }
}
