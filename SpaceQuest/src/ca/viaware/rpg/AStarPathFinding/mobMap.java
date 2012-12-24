package ca.viaware.rpg.AStarPathFinding;

import java.util.ArrayList;

import ca.viaware.rpg.game.Globals;
import ca.viaware.rpg.map.Tile;

public class mobMap {

	 int mapWidth;
	 int mapHeight;
	private ArrayList<ArrayList<Node>> map;
	private int startLocationX = 0;
	private int startLocationY = 0;
	private int goalLocationX = 0;
	private int goalLocationY = 0;

	ArrayList ObstacleMap = new ArrayList<Tile>();

	mobMap() {
		this.mapWidth = Globals.gameMap.getXSize();
		this.mapHeight = Globals.gameMap.getYSize();
		

		// inner arrray;
		ArrayList<Tile> innerObstacleMap = new ArrayList<Tile>();
		// x
		for (int X = 0; Globals.gameMap.getMap().length < X; X++) {
			// y
			for (int Y = 0; Globals.gameMap.getMap()[0].length < Y; Y++) {
				if (Globals.gameMap.getMap()[X][Y].hasCollision()) {
					ObstacleMap.add(Globals.gameMap.getMap()[X][Y]);
				}
			}
		}

		createMap();

		registerEdges();
		System.out.println("Mob map successfully initialized");
	}

	private void createMap() {
		Node node;
		map = new ArrayList<ArrayList<Node>>();
		for (int x = 0; x < mapWidth; x++) {
			map.add(new ArrayList<Node>());
			for (int y = 0; y < mapHeight; y++) {
				node = new Node(x, y);
				for (int i = 0; i < ObstacleMap.size(); i++) {
					if (((Tile) ObstacleMap.get(i)).getBX() == 1) {
						if (((Tile) ObstacleMap.get(i)).getBY() == 1) {
							node.setObstical(true);
							map.get(x).add(node);
						}
					}

				}
			}

		}
	}

	private void registerEdges() {
		for (int x = 0; x < mapWidth - 1; x++) {
			for (int y = 0; y < mapHeight - 1; y++) {
				Node node = map.get(x).get(y);
				if (!(y == 0))
					node.setNorth(map.get(x).get(y - 1));
				if (!(y == 0) && !(x == mapWidth))
					node.setNorthEast(map.get(x + 1).get(y - 1));
				if (!(x == mapWidth))
					node.setEast(map.get(x + 1).get(y));
				if (!(x == mapWidth) && !(y == mapHeight))
					node.setSouthEast(map.get(x + 1).get(y + 1));
				if (!(y == mapHeight))
					node.setSouth(map.get(x).get(y + 1));
				if (!(x == 0) && !(y == mapHeight))
					node.setSouthWest(map.get(x - 1).get(y + 1));
				if (!(x == 0))
					node.setWest(map.get(x - 1).get(y));
				if (!(x == 0) && !(y == 0))
					node.setNorthWest(map.get(x - 1).get(y - 1));
			}
		}
	}

	public ArrayList<ArrayList<Node>> getNodes() {
		return map;
	}

	public void setObstacle(int x, int y, boolean isObstical) {
		map.get(x).get(y).setObstical(isObstical);
	}

	public Node getNode(int x, int y) {
		return map.get(x).get(y);
	}

	public void setStartLocation(int x, int y) {
		map.get(startLocationX).get(startLocationY).setStart(false);
		map.get(x).get(y).setStart(true);
		startLocationX = x;
		startLocationY = y;
	}

	public void setGoalLocation(int x, int y) {
		map.get(goalLocationX).get(goalLocationY).setGoal(false);
		map.get(x).get(y).setGoal(true);
		goalLocationX = x;
		goalLocationY = y;
	}

	public int getStartLocationX() {
		return startLocationX;
	}

	public int getStartLocationY() {
		return startLocationY;
	}

	public Node getStartNode() {
		return map.get(startLocationX).get(startLocationY);
	}

	public int getGoalLocationX() {
		return goalLocationX;
	}

	public int getGoalLocationY() {
		return goalLocationY;
	}

	public Node getGoalLocation() {
		return map.get(goalLocationX).get(goalLocationY);
	}

	public float getDistanceBetween(Node node1, Node node2) {
		// if the nodes are on top or next to each other, return 1
		if (node1.getX() == node2.getX() || node1.getY() == node2.getY()) {
			return 1 * (mapHeight + mapWidth);
		} else { // if they are diagonal to each other return diagonal distance:
					// pythagorean thereom
			return (float) 1.7 * (mapHeight + mapWidth);
		}
	}

	public void clear() {
		startLocationX = 0;
		startLocationY = 0;
		goalLocationX = 0;
		goalLocationY = 0;
		createMap();
		registerEdges();
	}
}
