import java.util.regex.Pattern;


public class testingTal {

	enum types{ABC, DEF, GHI};


	
	public static void main(String[] args) {

		String line = "    test =       ss   ;     "    ;
		String linetemp = line.trim();
		String[] nameOfVar = linetemp.split("[ ]+");
		System.out.println(nameOfVar.length);
		
		for (String word:nameOfVar) {
			System.out.println(word);
		}
		
			

	}

		
}

