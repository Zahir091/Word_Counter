import java.util.*;
import java.io.*;
import javax.swing.*;

public class wordCounter {
	public static void main(String[] args) {
		TreeMap<String, Integer> myWords = new TreeMap<String, Integer>(); //I am creating a TreeMap which holds a String Key and Integer value
		Scanner myInput = null; //I am creating a scanner
		String inputLine; 
		try { 
			String myFilePath = chooseFile(); //I am storing the file path returned from my choose file method
			myInput = new Scanner(new File(myFilePath));  //I am using a scanner to read a file, using the filepath from the JFileChooser
			while (myInput.hasNextLine()) { 
				inputLine = myInput.nextLine(); //I am checking if the inputFile has more non-null lines
				/*In the next line, I am using reg Expressions to identify what characters in the string aren't alphabetical chars or whitespace. 
				Then I replace them with a whitespace, and use the whitespaces to split the string into seperate words and store it into a String array */
				String[] wordsInLine = inputLine.replaceAll("[^a-zA-Z ]", " ").split("\\s+"); 
				for(String word: wordsInLine) { //I am iterating through the wordsInLine array to check each word individually
					if(myWords.containsKey(word)) { //I am checking to see if the word/key was already in the TreeMap
						Integer freq = myWords.get(word); //I am storing the frequency value stored already for this key/word
						myWords.put(word, freq+1); //I am updating the frequency value by 1
					}
					else {
						myWords.put(word, 1); //I am inserting the word into my TreeMap with a freq of 1 if it's not already in the Map
					}
				}
			}
			for(String x : myWords.keySet()) //I am iterating through all the keys inside the TreeMap
				System.out.println(x + " - " + myWords.get(x));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}

	public static String chooseFile() { //File Chooser function
		int status; 
        JFileChooser chooser = new JFileChooser(); //I am creating a new JFileChooser to allow users to choose a file from file directory
        status = chooser.showOpenDialog(null); 
        if (status == JFileChooser.APPROVE_OPTION) {
            String path = chooser.getSelectedFile().getAbsolutePath(); //I am storing the path of the selected file
            return path;
        }
        return null;
    }
}
