package qa.utility;

import java.io.File;
import java.io.IOException;

import qa.SeleniumTest;

public class FileTool {

	private String SRC_FOLDER = "";

	public FileTool(String file) {
		this.SRC_FOLDER = file;
	}

	public void createFile() {
		// TODO
	}

	private void delete(File file) throws IOException {
		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				SeleniumTest.logger.info("Directory is deleted : " + file.getAbsolutePath() + System.lineSeparator());
			} else {

				// list all the directory contents
				String files[] = file.list();

				for (int i = 0; i < files.length; i++) {
					// construct the file structure
					File fileDelete = new File(file, files[i]);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					SeleniumTest.logger
							.info("Directory is deleted : " + file.getAbsolutePath() + System.lineSeparator());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			SeleniumTest.logger.info("File is deleted : " + file.getAbsolutePath() + System.lineSeparator());
		}
	}

	/*
	 * This method will recursively delete the contents of a directory before
	 * deleting the directory itself.
	 */
	public void deleteDriectory() {
		File directory = new File(SRC_FOLDER);

		if (directory.exists()) {
			try {
				delete(directory);
			} catch (IOException ex) {
				SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
			}
		} else {
			SeleniumTest.logger.severe("Directory does not exist");
		}
	}

	/*
	 * This method will recursively delete the contents of the directory, but
	 * will leave the directory itself intact
	 */
	public void deleteDirectoryContents(){
		File directory = new File(SRC_FOLDER);

		if (directory.exists()){
			try {
				String files[] = directory.list();
				for(String temp : files){
					File fileDelete = new File(SRC_FOLDER,temp);					
					delete(fileDelete);
				}				
			} catch (IOException ex) {
				SeleniumTest.logger.severe(ex.getMessage() + System.lineSeparator());
			}
		}
	}
}
