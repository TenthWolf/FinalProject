package testing;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class JcountWords {

	@Test
	void test() throws FileNotFoundException {
		JunitOcurrences word = new JunitOcurrences();
		Map<String, Integer> text = new HashMap<String, Integer>();
		word.countAllWords("C:\\Users\\17876\\Documents\\Raven.txt", text);

	}

}
