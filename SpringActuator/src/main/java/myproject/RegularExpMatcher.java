package myproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpMatcher {

	public static void main(String args[]) {
		String line = "Hi am  here Hi And here am";
		String pattern = "\\b(\\w+)\\s+\\1\\b ";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found value: " + m.group()+" Start: "+m.start()+" End: "+m.end());
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.println("Found value for Group " + i + " : " + m.group(i));
			}
		} else {
			System.out.println("NO MATCH");
		}
	}
}
