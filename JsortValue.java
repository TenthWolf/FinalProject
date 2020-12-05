package testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

class JsortValue {

	@Test
	void test() {
		JunitOcurrences sort = new JunitOcurrences();
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		List<Entry<String, Integer>> list = sort.sortByValue(wordMap);
		for (Map.Entry<String, Integer> entry : list) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}

}
