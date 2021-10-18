

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class JavaClassDirectory {
	static String option = null;
	static String directory = null;
	static String fileName = null;
	static int count = 0;

	public static void main(String[] args) {

		String condition = "Y";
		while ("Y".equalsIgnoreCase(condition)) {
			condition = "N";
			System.out.println("-------------------------------------------------------");
			System.out.println("Welcome to the LockedMe.Com Website");
			System.out.println("Developed By VijayRavuru");
			System.out.println("-------------------------------------------------------");
			System.out.println("1. View All files and Directories");
			System.out.println("2. Search for files and Directories");
			System.out.println("3. Create a new files");
			System.out.println("4. Delete a file");
			System.out.println("5. Read a File");
			System.out.println("6. Write to a File \n");

			System.out.println("Please choose to any one of the above Options \n");

			Scanner sc = new Scanner(System.in);
			option = sc.nextLine();

			switch (option) {

			case "1":
				System.out.println("Please Enter the Directory Path \n");
				directory = sc.next();
				viewFilesandFolder(directory, count);
				break;
			case "2":
				System.out.println("Please Enter the Directory Path \n");
				directory = sc.next();
				System.out.println("Please Enter the FileName \n");
				fileName = sc.next();
				search(directory, fileName);
				break;
			case "3":
				System.out.println("Please Enter the Directory Path \n");
				directory = sc.next();
				System.out.println("Please Enter the FileName \n");
				fileName = sc.next();
				create(directory, fileName);
				break;
			case "4":
				System.out.println("Please Enter the Directory Path \n");
				directory = sc.next();
				System.out.println("Please Enter the FileName \n");
				fileName = sc.next();
				delete(directory, fileName);
				break;
			case "5":
				System.out.println("Please Enter the Directory Path \n");
				directory = sc.next();
				System.out.println("Please Enter the FileName \n");
				fileName = sc.next();
				readingContentFromFile(directory, fileName);
				break;
			case "6":
				System.out.println("Please Enter the Directory Path \n");
				directory = sc.next();
				System.out.println("Please Enter the FileName \n");
				fileName = sc.next();
				writingContentToFile(directory, fileName);
				break;
			default:
				System.out.println("Please Choose the Right Option \n");
			}

			System.out.println("Do you want to continue. Please Enter y/n  \n");
			condition = sc.next();
		}
		System.out.println("\nSucessfully Exited the Application. ThankYou For Visiting LockedMe.Com");

	}

	/**
	 * This is a method to print all the files in a Directory and its SubDirectory
	 * 
	 * @param path
	 * @param count
	 * @return void
	 */
	public static void viewFilesandFolder(String directory, int count) {
		File fileContainer = null;
		File[] filesList = null;
		// Creating a File object for directory

		fileContainer = new File(directory);
		if (!fileContainer.exists() && !fileContainer.isDirectory()) {
			System.out.println("This is not a valid Directory or this Directory Does not Exist");
			return;
		}
		if (count == 0) {
			System.out.println("-------------------------------------------------------");
			System.out.println("Main Direactory:  " + directory);
			System.out.println("-------------------------------------------------------");
		}
		filesList = fileContainer.listFiles();

		if (Objects.nonNull(filesList) && filesList.length != 0) {
			for (File file : filesList) {

				System.out.println(file.getName());
			}
			for (File file : filesList) {
				if (file.isDirectory()) {
					count++;
					System.out.println("-------------------------------------------------------");
					System.out.println("Sub-Directory-" + count + ":  " + file.getAbsolutePath());
					System.out.println("-------------------------------------------------------");
					viewFilesandFolder(file.getAbsolutePath(), count);
					count--;
				}
			}
		} else {
			System.out.println("Its a Empty Directory");
		}
	}

	/**
	 * This is a method to search for a file in the Directory.
	 * 
	 * @param path
	 * @param fileName
	 * @return void
	 */
	public static void search(String directory, String fileName) {

		File dir = new File(directory);

		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("This is not a valid Directory or this Directory Does not Exist");
			return;
		}

		String combinedPath = directory + "//" + fileName;
		File file = new File(combinedPath);
		if (file.exists() && file.isFile()) {
			System.out.println("Found File and the Absolute path is: " + file.getAbsolutePath());
		} else if (file.exists() && file.isDirectory()) {
			System.out.println("Found the Directory and the Absolute path is: " + file.getAbsolutePath());
		} else {
			System.out.println("This File or Directory is not found in the given Directory");
		}
	}

	/**
	 * This is a method to delete a file in the Directory.
	 * 
	 * @param path
	 * @param fileName
	 * @return void
	 */
	public static void delete(String directory, String fileName) {

		File dir = new File(directory);

		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("This is not a valid Directory or this Directory Does not Exist");
			return;
		}

		String combinedPath = directory + "//" + fileName;
		File file = new File(combinedPath);
		if (file.exists() && file.isFile()) {
			try {
				file.deleteOnExit();
				System.out.println(
						"Sucessfully Deleted the File. This Files Absolute path is: \" + file.getAbsolutePath()");
			} catch (Exception e) {
				System.out
						.println("The Delete operations is not Performed on the file due to Security or Access issues");
			}
		} else if (file.exists() && file.isDirectory()) {
			System.out.println("This is not a file its Directory So can not be deleted");
		}
	}

	/**
	 * This is a method to create a new file in the Directory.
	 * 
	 * @param path
	 * @param fileName
	 * @return void
	 */
	public static void create(String directory, String fileName) {

		File dir = new File(directory);

		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("This is not a valid Directory or this Directory Does not Exist");
			return;
		}

		String combinedPath = directory + "//" + fileName;
		File file = new File(combinedPath);

		try {
			file.createNewFile();
			System.out.println("File create Sucessfully");
		} catch (IOException e) {
			System.out.println("Failed to create the file exception Occured");
		}
	}

	/**
	 * This is a method to read content from existing file in the provided
	 * Directory.
	 * 
	 * @param path
	 * @param fileName
	 * @return void
	 */
	public static void readingContentFromFile(String directory, String fileName) {

		File dir = new File(directory);

		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("This is not a valid Directory or this Directory Does not Exist");
			return;
		}

		String combinedPath = directory + "//" + fileName;

		// Read the content from file
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(combinedPath))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
			System.out.println("SUCESSFULLY READ THE FILE");
		} catch (FileNotFoundException e) {
			System.out.println("Could Not Read Data from the File as This file Does not Exist");
		} catch (IOException e) {
			System.out.println("Could Not Read Data from the File as Exception Occured");
		}

	}

	/**
	 * This is a method to write content into existing file in the provided
	 * Directory.
	 * 
	 * @param path
	 * @param fileName
	 * @return void
	 */
	public static void writingContentToFile(String directory, String fileName) {
		File dir = new File(directory);

		if (!dir.exists() && !dir.isDirectory()) {
			System.out.println("This is not a valid Directory or this Directory Does not Exist");
			return;
		}

		String combinedPath = directory + "//" + fileName;

		// Write the content in file
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(combinedPath))) {
			String fileContent = "This is a sample text.";
			bufferedWriter.write(fileContent);
			System.out.println("SUCESSFULLY WRITTEN TO THE FILE");
		} catch (IOException e) {
			System.out.println("Could Not Write Data as Exception Occured");
		}
	}
}
