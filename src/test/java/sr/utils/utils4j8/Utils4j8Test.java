package sr.utils.utils4j8;

import java.util.Arrays;

import org.junit.Test;

import sr.utils.utils4j8.Utils4j8;

public class Utils4j8Test {
	@Test
	public void test() {
		Utils4j8.getHashSet(Arrays.asList(1,2,3,4,5,6), i -> i*i);
	}

}
