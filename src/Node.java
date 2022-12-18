import java.util.ArrayList;

public class Node {
	int id;
	ArrayList<Node> neighbours = new ArrayList<>();
	boolean isPh = false;
	
	public Node(int id) {
		this.id = id;
	}

	public void setPh() {
		// TODO Auto-generated method stub
		this.isPh = true;
	}

	public ArrayList<Node> getNeighbours() {
		// TODO Auto-generated method stub
		return this.neighbours;
	}

	public boolean isPh() {
		// TODO Auto-generated method stub
		return this.isPh;
	}
}
