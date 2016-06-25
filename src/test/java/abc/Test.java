package abc;
public class Test {
	public void test1() {
		String string="a,b,c,d";
		String [] string2=string.split(",");
		for (String str : string2) {
			System.out.println(str);
		}
	}
}
