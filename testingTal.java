import java.util.regex.Pattern;

import oop.ex7.type.*;
public class testingTal {

	enum types{ABC, DEF, GHI};


	private Integer x;
	private Double y;
	private testingTal a;
	public testingTal(){
		a=null;
		x=10;
		y=500.5;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public testingTal getA() {
		return a;
	}
	public void setA(testingTal a) {
		this.a = a;
	}
	
	public static void main(String[] args) {

		String line = "double s=sdsd;";
		String[] stringsInLine = VariableFactory.getBothStr(line);
		
		for (String word:stringsInLine) {
			System.out.println(word);
		}
	} 
		
//			
//
//	}

		
}

