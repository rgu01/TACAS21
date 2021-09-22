package edu.tacas21.ta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fmaes.j2uppaal.datastructures.base.BaseUppaalElement;
import org.fmaes.j2uppaal.datastructures.uppaalstructures.UppaalAutomaton;
import org.fmaes.j2uppaal.datastructures.uppaalstructures.UppaalDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import edu.tacas21.gui.Output;
import edu.tacas21.gui.Parameters;

public class ModelGenerator {
	public static String prefix = "./out/game";
	private static String templateXML = "template.xml"; // under the bin folder

	public static void normalize(String path) {
		try {
			File filename = new File(path);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			Scanner sc = new Scanner(reader);
			String str = "";
			String fullContent = "";
			while (sc != null && sc.hasNextLine()) {
				str = sc.nextLine();
				if (str.contains("&&")) {
					str = str.replace("&&", "&amp;&amp;");
				}
				fullContent += str + "\n";
			}
			sc.close();
			Output print = new Output(path);
			System.out.println(fullContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void configure(UppaalDocument doc, Parameters para, String output) {
		String decStr = doc.getDeclaration();
		String newStrTask = "";
		String newStrMove = "";
		BaseUppaalElement existinChildFromList;
		UppaalAutomaton collaboration_i = (UppaalAutomaton) doc.getAutomatonByName("Collaboration_I");
		UppaalAutomaton collaboration_h = (UppaalAutomaton) doc.getAutomatonByName("Collaboration_H");
		String paraStr_i = collaboration_i.getParameter();
		String paraStr_h = collaboration_h.getParameter();
		QueryList qList = new QueryList();

		qList.addQuery(QueryGenerator.produce(para.getLearningQuery(), "for learning"));
		if(para.save) {
			qList.addQuery(QueryGenerator.produce(para.getSaveStrategyQuery(para.path_save), "for saving the strategy"));
		}
		qList.addQuery(QueryGenerator.produce(para.getLivenessQuery(), "for verification and compact"));
		qList.addQuery(QueryGenerator.produce("", "for test\n" + para.getReachabilityQuery()));
		if(para.compact) {
			qList.addQuery(QueryGenerator.produce(para.getSaveStrategyQuery(para.path_compact), "for compacting the strategy"));
			qList.addQuery(QueryGenerator.produce(para.getLivenessQuery(), "for verifying the compact strategy"));
		}

		decStr = decStr.replace("const int NOAGENTS = 2", "const int NOAGENTS = " + para.getAgentNum());
		decStr = decStr.replace("const int NOMILESTONES = 3", "const int NOMILESTONES = " + para.getDevNum());
		decStr = decStr.replace("const int NODEVICES = 3", "const int NODEVICES = " + para.getDevNum());
		decStr = decStr.replace("const time_t TOTALTIME = 1 * 60 * 60",
				"const time_t TOTALTIME = " + para.getTotalTime());

		newStrTask = "const task_t TASK_IDLE = {ID_IDLE, UNKOWNDEVICE, {";
		newStrMove = "const task_t TASK_MOVING = {ID_MOVING, UNKOWNDEVICE, {";
		for (int i = 0; i < para.getDevNum(); i++) {
			if (i != para.getDevNum() - 1) {
				newStrTask += "UNKOWNDEVICE, ";
				newStrMove += "UNKOWNDEVICE, ";
			} else {
				newStrTask += "UNKOWNDEVICE";
				newStrMove += "UNKOWNDEVICE";
			}
		}
		decStr = decStr.replace(
				"const task_t TASK_IDLE = {ID_IDLE, UNKOWNDEVICE, {UNKOWNDEVICE, UNKOWNDEVICE, UNKOWNDEVICE",
				newStrTask);
		decStr = decStr.replace(
				"const task_t TASK_MOVING = {ID_MOVING, UNKOWNDEVICE, {UNKOWNDEVICE, UNKOWNDEVICE, UNKOWNDEVICE",
				newStrMove);
		doc.setDeclaration(decStr);
		
		paraStr_i = paraStr_i.replace("collaborations[1]", "collaborations[" + para.tkNum + "]");
		existinChildFromList = doc.getChildElementByName(collaboration_i.tagName); // remove the first TA template
		doc.removeChildElement(existinChildFromList);
		collaboration_i.setParameter(paraStr_i);
		doc.addAutomaton(collaboration_i);
		
		paraStr_h = paraStr_h.replace("collaborations[1]", "collaborations[" + para.wlNum + "]");
		existinChildFromList = doc.getChildElementByName(collaboration_h.tagName);  // remove the first TA template
		doc.removeChildElement(existinChildFromList);
		collaboration_h.setParameter(paraStr_h);
		doc.addAutomaton(collaboration_h);
		

		doc.setSystem(para.getDelarations());
		doc.addOrReplaceChildElement(qList);
		doc.saveToFile(output);
		ModelGenerator.normalize(output);
	}

	public static void run(Parameters para, String output) {
		String show = "";
		String tempatePath = "";
		UppaalDocument doc = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			tempatePath = new File(".").getCanonicalPath() + File.separator + "res" + File.separator + templateXML;
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream is = new FileInputStream(new File(tempatePath));
			if (is != null) {
				Document templatedoc = builder.parse(is);
				templatedoc.getDocumentElement().normalize();
				Element root = templatedoc.getDocumentElement();
				doc = new UppaalDocument(root);
			}
		} catch (ParserConfigurationException ex) {

			show = ex.getMessage();
			JOptionPane.showMessageDialog(null, show, "Error", JOptionPane.PLAIN_MESSAGE);
		} catch (SAXException ex) {

			show = ex.getMessage();
			JOptionPane.showMessageDialog(null, show, "Error", JOptionPane.PLAIN_MESSAGE);
		} catch (IOException ex) {

			show = ex.getMessage();
			JOptionPane.showMessageDialog(null, show, "Error", JOptionPane.PLAIN_MESSAGE);
		}

		ModelGenerator.configure(doc, para, output);
	}
}
