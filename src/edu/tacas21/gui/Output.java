package edu.tacas21.gui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Output {
	private File file;
	private FileOutputStream fileOutputStream;
	private PrintStream printStream;
	
	public Output(String path) {
		file = new File(path);

		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutputStream = new FileOutputStream(file, false);
			printStream = new PrintStream(fileOutputStream);
			System.setOut(printStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeText() {
		try {
			printStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
