package org.jabong.com.auto_test_web_consistency;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LayoutAnalyzer 
{
	
	private LinkedHashMap<String, String> layoutcapturedData = null;
	
	public LayoutAnalyzer(String analyser_key) throws Exception{		
		//Will implement some logic in future for analyzer key.
		if(!analyser_key.equals("KEYAUTO2016")){
			throw new Exception("Sorry invalid analyser key - " + analyser_key);
		}
		else{
			String loglevel = System.getProperty("log_level");
			Log.setLogLevel(loglevel);
			Log.INFO("Welcome to Layout analyser!!");
			layoutcapturedData = new LinkedHashMap<String,String>(); 
			
		}
	}
	
	public void analyze(WebDriver driver,String pageName,String targetPlatform,int windowWidth,int windowHeight) throws Exception{
		try{
			CaptureLayout capLayoutInstance = new CaptureLayout(driver);
			
			Dimension dimension = new Dimension(windowWidth, windowHeight);
			
			driver.manage().window().setSize(dimension);
			
			List<WebElement> testObjectsInWebPage = driver.findElements(By.xpath("//*"));
			
			int i = 1;
			
			for (WebElement testObject : testObjectsInWebPage) {				
				
				String tagName = testObject.getTagName();
				
				String dimensionsOfTestObject = capLayoutInstance.getDimensionsOfWebObject(testObject);
				
				String locationOfTestObject = capLayoutInstance.getLocationOfWebElement(testObject);
				
				String computedStyleOfTestObject = capLayoutInstance.getComputedStyleOfWebObject(testObject);
				
				String attributeOfTestObject = capLayoutInstance.getAttributesOfAWebObject(testObject);
				
				String manipulatedKeyForMap = tagName + "_" + i;
				
				String manipulatedValue = dimensionsOfTestObject + "|" + locationOfTestObject + "|" + computedStyleOfTestObject + "|" + attributeOfTestObject;
				
				layoutcapturedData.put(manipulatedKeyForMap, manipulatedValue);
				
				i++;
			}
		}
		catch(Exception e){
			Log.ERROR(e.getMessage());
			throw e;
		}
	}
	
	public void captureAnalysisResults(String targetFileNameWithExtension,String absoluteFilePath) throws Exception{
		try{
			XmlWriter writer = new XmlWriter(targetFileNameWithExtension, absoluteFilePath);
			writer.createFile();
			for (String tag : layoutcapturedData.keySet()) {
				
				String valueForKey = layoutcapturedData.get(tag);
				
				String[] parsedvalue = valueForKey.split("\\|");
				
				String dimension = parsedvalue[0].trim();
				
				String location = parsedvalue[1].trim();
				
				String computedStyle = parsedvalue[2].trim();
				
				String attributes = parsedvalue[3].trim();
				
				writer.writeInfoForTestObject(tag, computedStyle, attributes, location, dimension);
			}
			writer.generateFileWithWrittenInfo();
		}
		catch(Exception e){
			Log.ERROR(e.getMessage());
			throw e;
		}
	}
	
	public void compareAnalysisResults(String fileLocationOne, String fileLocationTwo) throws Exception{
		try{
			XmlComparator compare = new XmlComparator();
			compare.findDifferencesInTwoXmlDocuments(fileLocationOne, fileLocationTwo);
		}
		catch(Exception e){
			throw e;
		}
	}
	
}
