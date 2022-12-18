
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph = new Graph();
		graph.load();
		graph.cut();
		int diameter = graph.getDiameter();
		System.out.print(diameter + (graph.getNoPaths()-diameter)*2);
	}

}
