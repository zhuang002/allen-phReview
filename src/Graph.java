import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
	ArrayList<Node> nodes = null;

	public int getDiameter() {
		// TODO Auto-generated method stub
		Node node = null;
		for (int i=0;i<this.nodes.size();i++) {
			node = this.nodes.get(i);
			if (!node.getNeighbours().isEmpty()) {
				break;
			}
		}
		NodeWithDistance nodeWithDist = this.getFurthestNode(node);
		nodeWithDist = this.getFurthestNode(nodeWithDist.node);
		return nodeWithDist.distance;
	}

	private NodeWithDistance getFurthestNode(Node node) {
		// TODO Auto-generated method stub
		NodeWithDistance nodeWithDist = new NodeWithDistance();
		ArrayList<Node> children = node.getNeighbours();

		for (Node child:children) {
			NodeWithDistance tmpNodeWithDist = getDepth(node, child);
			if (nodeWithDist.distance < tmpNodeWithDist.distance) {
				nodeWithDist = tmpNodeWithDist;
			}
		}
		nodeWithDist.distance ++;
		return nodeWithDist;
	}

	private NodeWithDistance getDepth(Node parent, Node current) {
		// TODO Auto-generated method stub
		
		NodeWithDistance nodeWithDist = new NodeWithDistance();
		ArrayList<Node> children = current.getNeighbours();
		
		if (children.size()==1) {
			nodeWithDist.distance = 0;
			nodeWithDist.node = current;
		}

	
		for (Node child:children) {
			if(child == parent)
				continue;
			NodeWithDistance tmpNodeWithDist = getDepth(current, child);
			if (nodeWithDist.distance < tmpNodeWithDist.distance) {
				nodeWithDist = tmpNodeWithDist;
			}
		}
		nodeWithDist.distance ++;
		return nodeWithDist;
	}

	public int getNoPaths() {
		// TODO Auto-generated method stub
		return nodes.size()-1;
	}

	public void load() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int nNodes = sc.nextInt();
		int nPhs = sc.nextInt();
		
		this.nodes = new ArrayList<>();
		for (int i=0;i<nNodes;i++) {
			this.nodes.add(new Node(i));
		}
		
		for (int i=0;i<nPhs;i++) {
			int phId = sc.nextInt();
			nodes.get(phId).setPh();
		}
		
		for (int i=0;i<nNodes-1;i++) {
			int id1 = sc.nextInt();
			int id2 = sc.nextInt();
			nodes.get(id1).getNeighbours().add(nodes.get(id2));
			nodes.get(id2).getNeighbours().add(nodes.get(id1));
			
		}
	}

	public void cut() {
		// TODO Auto-generated method stub
		ArrayList<Node> leaves = getAllLeaves();
		ArrayList<Node> newLeaves = new ArrayList<>();
		while(!leaves.isEmpty()) {
			for (Node leaf:leaves) {
				Node neighbour = leaf.getNeighbours().get(0);
				neighbour.getNeighbours().remove(leaf);
				if (!neighbour.isPh() && neighbour.getNeighbours().size()==1)
					newLeaves.add(neighbour);
			}
			leaves = newLeaves;
			newLeaves = new ArrayList<>();
		}
	}

	private ArrayList<Node> getAllLeaves() {
		// TODO Auto-generated method stub
		ArrayList<Node> leaves = new ArrayList<>();
		for (Node node:this.nodes) {
			if (!node.isPh() && node.getNeighbours().size() == 1) {
				leaves.add(node);
			}
		}
		return leaves;
	}
	
	
}
