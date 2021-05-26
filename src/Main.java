import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> edges;
	static int vNum;
	static int eNum;
	static int[] occurrence;

	public static void main(String[] args) {
		
		/* The code being measured starts */
		long startTime = System.nanoTime();
		
		edges = new ArrayList<ArrayList<Integer>>();
		
		try {
			File file = new File("tests/test2.txt");
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
		
		occurrence = new int[vNum];
		
		for(int i=0; i<vNum; i++) {
			int[] res = BFS(i);
			//System.out.println(i);
			if(res != null) {
				for(int j=i; j<vNum; j++) {
					int vertex = j;
					while(res[vertex] != i && res[vertex] != -1) {
						occurrence[res[vertex]]++;
						vertex = res[vertex];
					}
				}
			}
		}
		
		System.out.println(Arrays.toString(occurrence));
		
		int max = -1;
		for(int i=0; i < vNum; i++) {
			if(max < occurrence[i]) max = occurrence[i];
		}
		
		System.out.println(max);
		
        /* The code being measured ends */
 
        long endTime = System.nanoTime();
 
        long timeElapsed = endTime - startTime;
 
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}
	
	public static int[] BFS(int src) {
		ArrayDeque<Integer> container = new ArrayDeque<Integer>();
		
		int path[] = new int[vNum];
		Arrays.fill(path, -1); //initialize path array
		
		boolean visited[] = new boolean[vNum];
		Arrays.fill(visited, Boolean.FALSE); //initialize visited array
		
		container.add(src);
		visited[src] = true;
		
		while(!container.isEmpty()) {
			int vertex = container.remove();
			
			for(int i=0; i < edges.get(vertex).size(); i++) {
				if (!visited[edges.get(vertex).get(i)]) {
	                visited[edges.get(vertex).get(i)] = true;
	                path[edges.get(vertex).get(i)] = vertex;
	                container.add(edges.get(vertex).get(i));
				}
			}	
		}
		
		return path;
	}
	
}
