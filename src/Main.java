import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> edges;
	static int vNum;
	static int eNum;

	public static void main(String[] args) {
		edges = new ArrayList<ArrayList<Integer>>();
		
		try {
			File file = new File("tests/test1.txt");
			Scanner reader = new Scanner(file);
			
			String data = reader.nextLine();
			vNum = Integer.parseInt(data);
			
			for(int i=0; i < vNum; i++) {
				edges.add(new ArrayList<Integer>());
			}
			
			data = reader.nextLine();
			eNum = Integer.parseInt(data);
			
			while (reader.hasNextLine()) {
				data = reader.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(data, " ");
				String token = tokenizer.nextToken();
				int v1 = Integer.parseInt(token);
				token = tokenizer.nextToken();
				int v2 = Integer.parseInt(token);
				edges.get(v1).add(v2);
				edges.get(v2).add(v1);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file!");
			e.printStackTrace();
		}
		System.out.println(edges);
	}
	
}
