package org.jabong.com.auto_test_web_consistency;

import java.io.File;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;



public class AppTest 
{
	
    public void testAnalyser(String xmlFileName){
    	String resultXmlFileName = "JabongHome.xml"; 
		if(xmlFileName != "" && xmlFileName != null){
			resultXmlFileName = xmlFileName;
		}
    	try{
		LayoutAnalyzer analyzer = new LayoutAnalyzer("KEYAUTO2016");
		DesiredCapabilities executionCapabilities = DesiredCapabilities.chrome();
		ChromeDriverService service = new ChromeDriverService.Builder()
		.usingAnyFreePort()
		.usingDriverExecutable(new File("chromedriver"))
		.build();
		service.start();
		WebDriver driver = new ChromeDriver(service, executionCapabilities);		
		driver.get("http://www.jabong.com");
		analyzer.analyze(driver, "JabongHome", "Desktop", 1024, 768);
		analyzer.captureAnalysisResults(resultXmlFileName, "/Users/kapil/Nayan-workspace/Jabong_WebConsistancy_Framework/branchmaster/Jabong_WebConsistancyFramework/target");
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    		assert false;
    	}
    }
	
	@Test
	public void testComparator(){
		try{
		testAnalyser("JabongHomeXml1");
		testAnalyser("JabongHomeXml2");
		LayoutAnalyzer analyzer = new LayoutAnalyzer("KEYAUTO2016");
		analyzer.compareAnalysisResults("/Users/kapil/Nayan-workspace/Jabong_WebConsistancy_Framework/branchmaster/Jabong_WebConsistancyFramework/target/JabongHomeXml1", "/Users/kapil/Nayan-workspace/Jabong_WebConsistancy_Framework/branchmaster/Jabong_WebConsistancyFramework/target/JabongHomeXml2");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			assert false;
		}
	}
    
    
}
