package org.jabong.com.auto_test_web_consistency;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlWriter {
	
	private String fileName;
	
	private String filePath;
	
	private File referenceFile;
	
	private DocumentBuilderFactory xmlFactory;
	
	private Document xmlDocument;
	
	private Element baseTagNode;
	
	public XmlWriter(String fileNameWithXmlExtension, String filePath){
		this.fileName = fileNameWithXmlExtension;
		this.filePath = filePath;
	}
	
	public void createFile() throws Exception{
		try{
			String pathOfFile = this.filePath + System.getProperty("file.separator") + this.fileName;
			
			Log.DEBUG("Target File Path: " + pathOfFile);
			
			referenceFile = new File(pathOfFile);
				if(!referenceFile.exists()){
					referenceFile.createNewFile();
				}
				
				this.createXmlDocument();
		}
		catch(Exception e){
			Log.ERROR(e.getMessage());
			throw e;
		}
	}
	
	private void createXmlDocument() throws Exception{
		try{
		
			xmlFactory = DocumentBuilderFactory.newInstance();
		
			DocumentBuilder xmlDocBuilder = xmlFactory.newDocumentBuilder();
		
			xmlDocument = xmlDocBuilder.newDocument();
		
			baseTagNode = xmlDocument.createElement("layout_data");
		
			xmlDocument.appendChild(baseTagNode);
		
			Log.DEBUG("Xml document object created.");
		
		}
		catch(Exception e){
			Log.ERROR(e.getMessage());
			throw e;
		}
	}
	
	public void writeInfoForTestObject(String tagName,String cssStyle, String attributes, String location, String dimension) throws Exception{
		try{
			
			Log.DEBUG("Adding " + tagName + " with attribute - " + attributes + " location - " + location + " dimensions - " + dimension + " and computed styles - " + cssStyle);

			Element webObjectBaseTag = xmlDocument.createElement(tagName);
			
			baseTagNode.appendChild(webObjectBaseTag);
				Element cssNode = xmlDocument.createElement("computed_style");
				cssNode.appendChild(xmlDocument.createTextNode(cssStyle));
				webObjectBaseTag.appendChild(cssNode);
				
				Element attributeNode = xmlDocument.createElement("attributes");
				attributeNode.appendChild(xmlDocument.createTextNode(attributes));
				webObjectBaseTag.appendChild(attributeNode);
				
				Element locationNode = xmlDocument.createElement("location");
				locationNode.appendChild(xmlDocument.createTextNode(location));
				webObjectBaseTag.appendChild(locationNode);
				
				Element dimensionNode = xmlDocument.createElement("dimension");
				dimensionNode.appendChild(xmlDocument.createTextNode(dimension));
				webObjectBaseTag.appendChild(dimensionNode);
		}
		catch(Exception e){
			Log.WARN(tagName + " not added!");
		}
	}
	
	public void generateFileWithWrittenInfo() throws Exception{
		try{			
			Log.INFO("Transforming captured data to XML");
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			Transformer transformer = transformerFactory.newTransformer();
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
			DOMSource source = new DOMSource(xmlDocument);
			
			StreamResult result = new StreamResult(referenceFile);
			
			transformer.transform(source, result);
		}
		catch(Exception e){
			Log.ERROR(e.getMessage());
			throw e;
		}
	}
}
