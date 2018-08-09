package ord2018.util;

import java.io.File;

public class CandidateNumbersFromPdfs {

	public static void main(final String[] args) {
		final File dir = new File("/Users/hal/Documents/tdt4100/2018/InsperaAssessment_522722132");
		for (final File file : dir.listFiles()) {
			final String name = file.getName();
			if (name.endsWith(".pdf")) {
				final int pos = name.indexOf("_");
				if (pos == 5) {
					System.out.println(name.substring(0, pos));
				}
			}
		}
	}
}
