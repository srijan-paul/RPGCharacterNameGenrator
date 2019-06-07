import java.io.File;
public class Main {

	public static void main(String[] args) {
		
		WordGenerator gen = new WordGenerator();
		gen.setCount(10);
		gen.setSourceFile(new File("RPGnames.txt"));
		System.out.println(gen.getRandom());

	}

}
