import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<ArrayList<Integer>> edges;
	static int vNum;
	static int eNum;
	static int[] cb;

	public static void main(String[] args) {
		/* The code being measured starts */
		long startTime = System.nanoTime();
		
		edges = new ArrayList<ArrayList<Integer>>();
		
		try {
			File file = new File("../tests/" + args[0]);
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
		
		cb = new int[vNum];
		
		System.out.println(vNum);

		for(int i=0; i<vNum; i++) {
			Stack<Integer> stack = new Stack<Integer>();
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			int[] theta = new int [vNum];
			int[]  d = new int[vNum] ;
			double[] sigma = new double[vNum];
			for(int j=0; j < vNum; j++) {
				list.add(j,new ArrayList<Integer>());
			}
			
			Arrays.fill(d, -1);
			d[i] = 0;
			theta[i] = 1;
			sigma[i] = 0;
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(i);
			while (!queue.isEmpty()) {
				int id = queue.remove();
				stack.push(id);
				for (int k = 0;k<edges.get(id).size();k++) {
					int neighbor = edges.get(id).get(k);
					if(d[neighbor]<0) {
						queue.add(neighbor);
						d[neighbor]= d[id]+1;
					}
					if(d[neighbor]==d[id]+1) {
						theta[neighbor] += theta[id];

						list.get(neighbor).add(id);
					}
				}
			}


			while (!stack.isEmpty()) {
				int vert = stack.pop();
				for (int elem:list.get(vert)) {
					if(theta[vert] != 0) {
					sigma[elem] +=(double)(((double)theta[elem]/(double)theta[vert])*(1+sigma[vert]));
					}
					if(vert != i) {
						cb[vert] += sigma[vert];
					}
				}
			}
			if(i==-1) {
				System.out.println(Arrays.toString(theta));
				System.out.println(Arrays.toString(d));
				System.out.println(Arrays.toString(sigma));
			}
			
		}
		
		
		int max = 0;
		for(int i=0; i < vNum; i++) {
			if(cb[max] < cb[i]) max = i;
		}

		System.out.println("Most popular person is " + max + " with " + cb[max] + " occurrence.");
		
		/* The code being measured ends */
        long endTime = System.nanoTime();

        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
	}

}
