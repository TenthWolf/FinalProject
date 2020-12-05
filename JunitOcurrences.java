package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class JunitOcurrences {

	// Creates method for counting all words
	// HashMap will be used to store words
	// Iterator can be used to loop through collections. EX: .hasnext and .next
	// throws FileNotFound to prevent errors for popping up on Scanner
	public void countAllWords(String fileName, Map<String, Integer> text) throws FileNotFoundException {
		Scanner file = new Scanner(new File(fileName)); // reads from the file
		while (file.hasNext()) { // iterator loop returns true if there is another string in the file making it
									// // print the next word
			String line = file.next(); // starts at first word of the poem and proceeds to the next word after loop
										// ends
			Integer count = text.get(line); // create count to store number of appeared words
			if (count != null) { // count has to have a value
				count++; // increment after program runs
			} else
				count = 1;
			text.put(line, count); // counts the word and how many times each word appears
		}
		file.close();
	}

	public static List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		return list;
	}
}
