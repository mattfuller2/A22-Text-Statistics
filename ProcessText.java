
import java.io.File;

/**
 * Gets a list of one or more filenames from the command line and collects
 * statistics on each of the files using an instance of the TextStatistics
 * object.
 *
 * @author Matt Fuller
 */
public class ProcessText {
	/**
	 * Accepts a text file from the console. S
	 * 
	 * @param args String array
	 */
	public static void main(String[] args) {
		// Informs the user on how to run the program
		if (args.length == 0) {
			System.err.println("Usage: java ProcessText file1 [file2 ...]");
			System.exit(1);
		} else {
			for (String file : args) {
				File filename = new File("etext/" + file);
				if (filename.exists() && filename.canRead()) {
					TextStatisticsInterface stats = new TextStatistics(filename);
					System.out.println(stats.toString());
				} else {
					System.err.println("Invalid file path: " + file + "\n");
				}
			}
		}
	}
}
