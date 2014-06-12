
public class testing_LIOR {

	
	
	public enum vars {INT , STUFF, CHAR };
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		testingTal test1=new testingTal();
		testingTal test2=new testingTal();
		testingTal testcheck=test1.getA();
		System.out.println(testcheck);
		testcheck=test2;
		System.out.println(test1.getA());
		
	}

	
	
	
}
