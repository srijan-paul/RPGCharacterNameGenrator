import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordGenerator{
	private File sourceFile;
	private String sourceText;
	private File defaultFile = new File("defaultsource.txt");
	private HashMap<String , ArrayList<String>> ngrams = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> beginnings = new ArrayList<String>();
	private int order = 3;
	private int wordLength = 20;
	private final int MAX_LENGTH = 500;
	private final int MIN_LENGTH = 10;
	private final int MIN_ORDER = 3;
	private final int MAX_ORDER = 5;
	private int count = 5;
	
	public WordGenerator(){
		setSourceFile(new File("defaultsource.txt"));
		
	}
	
	public WordGenerator(File file) {
		setSourceFile(file);
	
	}
	
	
	public void setSourceFile(File file) {
		
		boolean condition = file.getName().contains(".txt") && file.exists();
		
		if(condition) {
			sourceFile = file;
			ngrams.clear();			
			beginnings.clear();
			try {
				Scanner sc = new Scanner(file);
				
				while(sc.hasNextLine()) {
					
					String next = sc.nextLine();
					
					if(next.length() > order) {
					
						sourceText += "\n" + next;
						beginnings.add(next.substring(0,order));
											
						for(int i = 0 ; i < next.length() - order ; i++) {
							String gram = next.substring(i, i + order);
							
							if(!ngrams.containsKey(gram)) {
								ngrams.put(gram, new ArrayList<String>());
							}
							
							ngrams.get(gram).add(Character.toString(next.charAt(i+order)));
						}//close loop
						
					}//close if
				}//close while
				sc.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}//close try catch
			
			
		}
	}//close method
	
	public String getRandom() {
	
	String list = "";
	String result = "";
	 
	for(int j = 0; j < count ; j++) {	
		String currGram = random(beginnings);
		 result = currGram;
			
		for(int i = 0 ; i < wordLength ;i ++) {

			ArrayList<String> possibilities = ngrams.get(currGram);
			if(possibilities != null) {
				String next  = random(possibilities);
				result += next;
				int len = result.length();
				currGram = result.substring(len-order, len);
				if(len >= wordLength) {
					break;
				}
				
			}else {
				break;
			}
			
		}
		list += result + "\n";
	}
		return list;
			
	}//close method
	
	//method to return a random element from a given array. 
	//This is used to select a random beginning for the word to be generated.
	public static <T> T random(ArrayList<T> arr){
		int len = arr.size();
		int index = (int)Math.floor(Math.random()*len);
		return (T) arr.get(index);
		
	}

	public void setWordLength(int i){
		if(i < MAX_LENGTH && i > MIN_LENGTH) {
			wordLength = i;
		}
	}//close method
	
	public void setOrder(int o) {
		if(o < MAX_ORDER && o > MIN_ORDER) {
			order = o;
		}
	}
	
	public void setCount(int i) {
		if(i > 0) {
			count = i;
		}
	}

}//close class