
public class testing_LIOR {

	

	public enum vars {INT , STUFF, CHAR };
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DifferentTypes[] abc=DifferentTypes.values();
		
		for(DifferentTypes i:abc){
			System.out.println(i);
		}

}
	
	public int bla(int a){
		a=6;
		return 8;
	}
}