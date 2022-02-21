import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


import java.io.*;

public class A4_2019MT10685  {
	
	
	public static void average(graph graph) {
		
		
		//without iterator
		float noofnodes = graph.nodes.size();
		float noofadjacentnodes = 0;
		for(int j=0;j<graph.nodes.size();j++) {
			noofadjacentnodes += graph.adjlist.get(graph.nodes.get(j)).size();
		}
		
		
		System.out.printf("%.2f", noofadjacentnodes/noofnodes);
		return;
		/*
		float abc = ((float)Math.round((noofadjacentnodes/noofnodes)*100.0))/100;
		String asd = String.valueOf(abc);
		if(asd.charAt(asd.length()-3)=='.') {
			System.out.println(asd);
			return;
		}
		if(asd.charAt(asd.length()-2)=='.') {
			System.out.println(asd+"0");
			return;
		}
		else {
			System.out.println(asd+"00");
			return;

		}
		*/
		
	}
	
	public static void rank(graph graph) {
		
		
		//without iterator
		int n = graph.nodes.size();
		String[] arrweight = new String[n];
		for(int j=0;j<n;j++) {
			LinkedList<ArrayList<String>> linkedlist =graph.adjlist.get(graph.nodes.get(j));
			int b = 0;
			for(int k =0; k<linkedlist.size();k++) {
				ArrayList<String> arraylist = linkedlist.get(k);
				b+=Integer.valueOf(arraylist.get(1));
			}
			arrweight[j] = String.valueOf(b);
		}
		
		String[] dummy = new String[graph.nodes.size()];
		for(int j =0;j<n;j++) {
			dummy[j] = arrweight[j] + "@@%$%@@" + graph.nodes.get(j);
		}
		A4_2019MT10685 tp = new A4_2019MT10685();
		tp.mergesort(dummy, 0, n-1);

		
		for(int j=0;j<n-1;j++) {
			int r=0;
			while(true) {
				if(dummy[j].charAt(r)=='@') {
					System.out.print(dummy[j].substring(r+7)+",");
					break;
				}
				r++;
			}
		}
		int e=0;
		while(true) {
			if(dummy[n-1].charAt(e)=='@') {
				System.out.print(dummy[n-1].substring(e+7));
				break;
			}
			e++;
		}
	}
	
	
	public void mergesort(String[] A, int beg, int end) {
		if(beg>=end) {
			return;
		}
		int mid = (beg+end)/2;
		mergesort(A, beg, mid);
		mergesort(A, mid+1, end);
		merge(A, beg, mid, end);
		
	}
	
	public static void merge(String[] A, int beg, int mid, int end) {
		int l = mid-beg+1;
		int r = end-mid;
		
		String[] leftarray = new String[l];
		String[] rightarray = new String[r];
		
		for(int t=0; t<l; t++) {
			leftarray[t] = A[beg+t];
		}
		for(int m=0; m<r; m++ ) {
			rightarray[m] = A[mid+1+m];
		}
		
		int i=0;
		int j=0;
		int k=beg;
		while(i<l&&j<r) {
			if(s1greaterthans2(leftarray[i], rightarray[j])) {
				A[k]=leftarray[i];
				i++;
			}
			else {
				A[k]=rightarray[j];
				j++;
			}
			k++;
		}
		
		while(i<l) {
			A[k]=leftarray[i];
			i++;
			k++;
		}
		
		while(j<r) {
			A[k]=rightarray[j];
			j++;
			k++;
		}
	}
	
	public static boolean s1greaterthans2(String s1, String s2) {
		int j = 0;

		int s1int;
		int s2int;
		String s1string;
		String s2string;
		while(true) {
			if(s1.charAt(j)=='@') {
				s1int = Integer.valueOf(s1.substring(0, j));
				s1string = s1.substring(j+7);
				break;
			}
			j++;
		}
		j=0;
		while(true) {
			if(s2.charAt(j)=='@') {
				s2int = Integer.valueOf(s2.substring(0, j));
				s2string = s2.substring(j+7);
				break;
			}
			j++;
		}
		
		if(s1int>s2int) {
			return true;
		}
		if(s1int<s2int) {
			return false;
		}
		int abc = s1string.compareTo(s2string);
		if(abc>0) {
			return true;
		}
		if(abc<0) {
			return false;
		}
		return true;
	}
	
	public static void dfs(graph graph) {
		
		
		HashMap<String, Boolean> visited = new  HashMap<String, Boolean>();
		for(int i=0; i<graph.nodes.size();i++) {
			visited.put(graph.nodes.get(i), false);
		}
		ArrayList<LinkedList<String>> A = new ArrayList<LinkedList<String>>();
		
		for(int i=0;i<graph.nodes.size();i++) {
			if(!visited.get(graph.nodes.get(i))) {
				LinkedList<String> w = new LinkedList<String>();
				dfscomponent(graph.nodes.get(i),visited,w, graph);
				A.add(w);
			}
		}
		
		
		for(int i=0;i<A.size();i++) {
			LinkedList<String> u =  sortlinkedlist(A.get(i));
			A.set(i, u);
		}
		
		A4_2019MT10685 yuyu = new A4_2019MT10685();
		yuyu.mergesort3(A, 0, A.size()-1);
		
		for(int i=0;i<A.size();i++) {
			LinkedList<String> g =  A.get(i);
			for(int j=0;j<g.size()-1;j++) {
				System.out.print(g.get(j)+",");
			}
			System.out.println(g.get(g.size()-1));
		}
		return;
	}
	
	public static LinkedList<String> sortlinkedlist(LinkedList<String> v) {
		String[] good = new String[v.size()];
		for(int i=0;i<v.size();i++) {
			good[i] = v.get(i);
		}
		
		A4_2019MT10685 sr = new A4_2019MT10685();
		sr.mergesort2(good, 0, v.size()-1);
		
		for(int i=0;i<v.size();i++) {
			v.set(i, good[i]);
		}
		
		return v;

		
		
	}
	
	public static void dfscomponent(String node, HashMap<String, Boolean> visited,LinkedList<String> w, graph graph) {
		visited.put(node, true);
		w.add(node);
		LinkedList<ArrayList<String>> s = graph.adjlist.get(node);
		for(int j = 0;j<s.size();j++) {
			
			if(!visited.get(s.get(j).get(0))) {
				dfscomponent(s.get(j).get(0), visited,w,graph);
			}
		}
		
		/*
		visited[t] = true;
		w.add(graph.nodes.get(t));
		for(int j = 0;j<graph.adjlist.get(graph.nodes.get(t)).size();j++) {
			
			if((!visited[graph.nodes.indexOf(graph.adjlist.get(graph.nodes.get(t)).get(j).get(0))])) {
				dfscomponent(graph.nodes.indexOf(graph.adjlist.get(graph.nodes.get(t)).get(j).get(0)), visited,w,graph);
			}
		}*/
	}
	
	public void mergesort2(String[] A, int beg, int end) {
		if(beg>=end) {
			return;
		}
		int mid = (beg+end)/2;
		mergesort2(A, beg, mid);
		mergesort2(A, mid+1, end);
		merge2(A, beg, mid, end);
		
	}
	
	public static void merge2(String[] A, int beg, int mid, int end) {
		int l = mid-beg+1;
		int r = end-mid;
		
		String[] leftarray = new String[l];
		String[] rightarray = new String[r];
		
		for(int t=0; t<l; t++) {
			leftarray[t] = A[beg+t];
		}
		for(int m=0; m<r; m++ ) {
			rightarray[m] = A[mid+1+m];
		}
		
		int i=0;
		int j=0;
		int k=beg;
		while(i<l&&j<r) {
			if(s1greaters2(leftarray[i], rightarray[j])) {
				A[k]=leftarray[i];
				i++;
			}
			else {
				A[k]=rightarray[j];
				j++;
			}
			k++;
		}
		
		while(i<l) {
			A[k]=leftarray[i];
			i++;
			k++;
		}
		
		while(j<r) {
			A[k]=rightarray[j];
			j++;
			k++;
		}
	}
	
	public static boolean s1greaters2(String s1, String s2) {
		
		int abc = s1.compareTo(s2);
		if(abc>0) {
			return true;
		}
		if(abc<0) {
			return false;
		}
		return true;
	}
	
	public void mergesort3(ArrayList<LinkedList<String>> A, int beg, int end) {
		if(beg>=end) {
			return;
		}
		int mid = (beg+end)/2;
		mergesort3(A, beg, mid);
		mergesort3(A, mid+1, end);
		merge3(A, beg, mid, end);
		
	}
	
	public static void merge3(ArrayList<LinkedList<String>> A, int beg, int mid, int end) {
		int l = mid-beg+1;
		int r = end-mid;
		
		ArrayList<LinkedList<String>> leftarray = new ArrayList<LinkedList<String>>();
		ArrayList<LinkedList<String>> rightarray = new ArrayList<LinkedList<String>>();
		
		for(int t=0; t<l; t++) {
			leftarray.add(A.get(beg+t));
		}
		for(int m=0; m<r; m++ ) {
			rightarray.add(A.get(mid+1+m));
		}
		
		int i=0;
		int j=0;
		int k=beg;
		while(i<l&&j<r) {
			if(s1greas2(leftarray.get(i), rightarray.get(j))) {
				A.set(k,leftarray.get(i));
				i++;
			}
			else {
				A.set(k,rightarray.get(j));
				j++;
			}
			k++;
		}
		
		while(i<l) {
			A.set(k,leftarray.get(i));
			i++;
			k++;
		}
		
		while(j<r) {
			A.set(k,rightarray.get(j));
			j++;
			k++;
		}
	}
	
	public static boolean s1greas2(LinkedList<String> s1, LinkedList<String> s2) {
		if(s1.size()>s2.size()) {
			return true;
		}
		if(s1.size()<s2.size()) {
			return false;
		}
		
		int abc = s1.get(0).compareTo(s2.get(0));
		if(abc>0) {
			return true;
		}
		if(abc<0) {
			return false;
		}
		return true;
		}
	
	public static void main(String[] args) {
		
		graph graph = new graph();
		
		try {
			BufferedReader nodescsv = new BufferedReader(new FileReader(args[0]));
			BufferedReader edgescsv = new BufferedReader(new FileReader(args[1]));
			String s1 = nodescsv.readLine();
			
			
			while((s1=nodescsv.readLine())!=null) {
				
				String[] data = s1.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				for (int i = 0; i < data.length; i++) {
				        if(data[i].startsWith("\"") && data[i].endsWith("\""))
				        data[i] = data[i].substring(1, data[i].length()-1);
				}
				graph.addnode(data[1]);
			}
			
			String s2 = edgescsv.readLine();
			while((s2=edgescsv.readLine())!=null) {
				String[] data = s2.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				for (int i = 0; i < data.length; i++) {
				        if(data[i].startsWith("\"") && data[i].endsWith("\""))
				        data[i] = data[i].substring(1, data[i].length()-1);
				}
				graph.addedge(data[0], data[1], data[2]);
				graph.addedge(data[1], data[0], data[2]);
			}
			
			nodescsv.close();
			edgescsv.close();
		}
		catch(IOException e){
			System.out.println("Error in reading file");
		}
		
		if(args[2].equals("average")) {
			average(graph);
		}
		
		if(args[2].equals("rank")) {
			rank(graph);
		}
		if(args[2].equals("independent_storylines_dfs")) {
			dfs(graph);
		}
	}
}


class graph{
	HashMap<String, LinkedList<ArrayList<String>>> adjlist;
	ArrayList<String> nodes;
	
	public graph() {
		this.adjlist = new HashMap<String, LinkedList<ArrayList<String>>>();
		this.nodes = new ArrayList<String>();
	}
	
	public void addnode(String a){
		nodes.add(a);
		LinkedList<ArrayList<String>> b = new LinkedList<>();
		adjlist.put(a, b);
	}
	
	public void addedge(String sourcenode, String destinationnode, String weight) {
		ArrayList<String> newedge = new ArrayList<>();
		newedge.add(destinationnode);
		newedge.add(weight);
		
		LinkedList<ArrayList<String>> list = adjlist.get(sourcenode);
		
		list.add(newedge);
		adjlist.put(sourcenode, list);
		
	}
}


/*
	public static void average(graph graph) {
	//Usingiterator
	Iterator<Map.Entry<String, LinkedList<ArrayList<String>>>> m = graph.adjlist.entrySet().iterator();
	
	float noofnodes = 0;
	float noofadjacentnodes = 0;
	while(m.hasNext()) {
		Map.Entry<String, LinkedList<ArrayList<String>>> element = (Map.Entry<String, LinkedList<ArrayList<String>>>)m.next();
		noofnodes+=1.0;
		noofadjacentnodes += element.getValue().size();
	}
	return (float)Math.round((noofadjacentnodes/noofnodes)*100.0)/100;
	}
*/


/*
	public static void rank(graph graph) {
	//using iterator 
	
	int[] arrweight = new int[graph.nodes.size()];
	String[] nod = new String[graph.nodes.size()];
	Iterator<Map.Entry<String, LinkedList<ArrayList<String>>>> m = graph.adjlist.entrySet().iterator();
	int i = 0;
	while(m.hasNext()) {
		Map.Entry<String, LinkedList<ArrayList<String>>> element = (Map.Entry<String, LinkedList<ArrayList<String>>>)m.next();
		LinkedList<ArrayList<String>> linkedlist = element.getValue();
		int b = 0;
		for(int n =0; n<linkedlist.size();n++) {
			ArrayList<String> arraylist = linkedlist.get(n);
			b+=Integer.valueOf(arraylist.get(1));
		}
		arrweight[i] = b;
		nod[i]=element.getKey();
		i++;
	}
	String[] dummy = new String[graph.nodes.size()];
	for(int j = 0; j<graph.nodes.size();j++) {
		dummy[j] = 
	}
	mergesort(nod, arrweight, 0, arrweight.length-1);
	
	return ;
*/