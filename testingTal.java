import java.io.File;
import java.util.regex.*;

import oop.ex7.main.RegexConfig;
import oop.ex7.type.*;
public class testingTal {

	
	public static final String VALID_OPERATOR = "[+-\\/\\*]";
	public static final String GENERAL_NAME = "( )*([_][^ ]+|[^\\d_\\+-\\/\\*][^ ]*)( )*";
	
	public static void main(String[] args) {
	
	File file = new File("/Users/taldovrat/git/ex7/tests");
	System.out.println(file.getAbsolutePath());
//		String \j = "/5";
//		System.out.println(check.matches(GENERAL_NAME));
	
	} 
		
//			
//
//	}

		
}

