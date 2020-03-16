
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 
 *
 * @author Matt Fuller
 */
public class TextStatistics implements TextStatisticsInterface {
	private File nextFile;
	public static final int ERROR_CODE = 1;
	private int charCOUNT, wordCOUNT, lineCOUNT, letterCOUNT;
	private double aveWordLength;
	private int[] numLetters = new int[26];
	private int[] wordLength = new int[24];
	private double sum = 0;
	private static final String DELIMITERS = "[\\W\\d_]+";

	// Formats each statistic to two decimal places
	DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Constructor: Accepts a File object as a parameter, opens the file, and read the entire file
	 * line-by-line, processing each line as it reads it.
	 * 
	 * @param file Text file
	 */
	public TextStatistics(File file)
		{
			nextFile = file;
			System.out.print("Statistics for: " + nextFile);
			try {
				Scanner fileScan = new Scanner(nextFile);

				// Reads in each line of the file
				while (fileScan.hasNextLine()) {
					String line = fileScan.nextLine(); // Reads one line

					// Counts the number of characters in the file
					Scanner lineScan = new Scanner(line);

					charCOUNT = charCOUNT + line.length() + 1;
					lineCOUNT++;

					for (letterCOUNT = 0; letterCOUNT < line.length(); letterCOUNT++) {
						line = line.toLowerCase();
						char current = line.charAt(letterCOUNT);
						if (current >= 'a' && current <= 'z')
							numLetters[current - 'a']++;
					}

					// Scanner will not return any delimiter characters
					lineScan.useDelimiter(DELIMITERS);

					// Reads in number of words in file
					while (lineScan.hasNext()) {
						wordCOUNT++;
						String word = lineScan.next();

						wordLength[word.length()]++;
					}
					lineScan.close();
				}
				fileScan.close();
				System.out.println();

				for (int i = 0; i < wordLength.length; i++) {
					sum = sum + wordLength[i] * i;
				}
				aveWordLength = sum / wordCOUNT;
			}
			catch (FileNotFoundException e1) {
				System.out.println("File \"" + nextFile + "\" could not be opened.");
				System.out.println(e1.getMessage());
			}
		}

	/**
	 * @return true If file exists
	 */
	public boolean exists() {
		return true;
	}

	/*
	 * @return charCount Number of characters in the file
	 */
	@Override
	public int getCharCount() {
		return charCOUNT;
	}

	/*
	 * @return wordCOUNT Number of words in the file
	 */
	@Override
	public int getWordCount() {
		return wordCOUNT;
	}

	/*
	 * @return lineCOUNT Number of lines in the file
	 */
	@Override
	public int getLineCount() {
		return lineCOUNT;
	}

	/*
	 * @return numLetters Number of letters in the file
	 */
	@Override
	public int[] getLetterCount() {
		return numLetters;
	}

	/*
	 * @return wordLength Length of words in the file
	 */
	@Override
	public int[] getWordLengthCount() {
		return wordLength;
	}

	/*
	 * @return aveWordLength Average word length in the file
	 */
	@Override
	public double getAverageWordLength() {
		return aveWordLength;
	}

	/*
	 * @return file statistics
	 */
	@Override
	public String toString() {
		return "=========================================================\n" + lineCOUNT + " lines\n"
		        + wordCOUNT + " words\n" + charCOUNT + " characters\n" + "------------------------------\n"
		        + "a = " + numLetters[0] + "\t\t\t n = " + numLetters[13] + "\n" + "b = " + numLetters[1]
		        + "\t\t\t o = " + numLetters[14] + "\n" + "c = " + numLetters[2] + "\t\t\t p = "
		        + numLetters[15] + "\n" + "d = " + numLetters[3] + "\t\t\t q = " + numLetters[16] + "\n"
		        + "e = " + numLetters[4] + "\t\t\t r = " + numLetters[17] + "\n" + "f = " + numLetters[5]
		        + "\t\t\t s = " + numLetters[18] + "\n" + "g = " + numLetters[6] + "\t\t\t t = "
		        + numLetters[19] + "\n" + "h = " + numLetters[7] + "\t\t\t u = " + numLetters[20] + "\n"
		        + "i = " + numLetters[8] + "\t\t\t v = " + numLetters[21] + "\n" + "j = " + numLetters[9]
		        + "\t\t\t w = " + numLetters[22] + "\n" + "k = " + numLetters[10] + "\t\t\t x = "
		        + numLetters[23] + "\n" + "l = " + numLetters[11] + "\t\t\t y = " + numLetters[24] + "\n"
		        + "m = " + numLetters[12] + "\t\t\t z = " + numLetters[25] + "\n"
		        + "------------------------------\n" + "length\t frequency\n" + "-----  --------\n"
		        + "      1           " + wordLength[1] + "\n" + "      2           " + wordLength[2] + "\n"
		        + "      3           " + wordLength[3] + "\n" + "      4           " + wordLength[4] + "\n"
		        + "      5           " + wordLength[5] + "\n" + "      6           " + wordLength[6] + "\n"
		        + "      7           " + wordLength[7] + "\n" + "      8           " + wordLength[8] + "\n"
		        + "      9           " + wordLength[9] + "\n" + "      10         " + wordLength[10] + "\n"
		        + "      11         " + wordLength[11] + "\n" + "Average word length = " + df.format(
		                getAverageWordLength()) + "\n"
		        + "=========================================================\n";

	}
}
