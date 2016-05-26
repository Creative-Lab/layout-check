package org.jabong.com.auto_test_web_consistency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.custommonkey.xmlunit.*;

class XmlComparator {
	
	
	XmlComparator() {
		
	}
	
	public void findDifferencesInTwoXmlDocuments(String XmlFile1Location, String XmlFile2Location) throws Exception{
		FileReader xmlReader1;
		FileReader xmlReader2;
		try{
			xmlReader1 = new FileReader(new File(XmlFile1Location));
			xmlReader2 = new FileReader(new File(XmlFile2Location));
		}
		catch(FileNotFoundException fe){
			throw fe;
		}
		try{
			Diff xmlDiff = new Diff(xmlReader1, xmlReader2);
			DetailedDiff differences = new DetailedDiff(xmlDiff);
			
			List<?> lstDifferences =  differences.getAllDifferences();
			
			for (Object diff : lstDifferences) {
				System.out.println(diff.toString());
			}
		}
		catch(Exception e){
			throw e;
		}
		
	}
	

}
