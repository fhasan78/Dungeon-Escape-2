
/**
 * CS1027-Assignment3
 * @author Fatima Hasan 
 * This class will implement the algorithm to compute a shortest path from the initial chamber to the exit.  
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {
	private static Dungeon dungeon;
	private static FindShortestPath chambers;
	private static DLinkedPriorityQueue<Hexagon> dungeonQueue;

	/**
	 * This is the constructor for the FindShortestPath class, it receives as
	 * input the name of the file containing the description of the dungeon.
	 * 
	 * @param filename
	 * @throws InvalidDungeonCharacterException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FindShortestPath(String filename)
			throws InvalidDungeonCharacterException, FileNotFoundException, IOException {
		FindShortestPath.dungeon = new Dungeon(filename);

	}

	/**
	 * The adjacentToDragon(Hexagon chamber) method returns a boolean saying if
	 * the current chamber is adjacent to a dragon's lair.
	 * 
	 * @param chamber
	 *            - where the warrior currently is.
	 * @return a boolean true if adjacent to dragon,false otherwise.
	 */
	private static boolean adjacentToDragon(Hexagon chamber) {
		for (int i = 0; i < 6; i++) {
			Hexagon neighbourChamber = chamber.getNeighbour(i);
			if (neighbourChamber != null && neighbourChamber.isDragon()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * This main method will create an object of the class FindShortestPath
	 * using the constructor. This method will also print a message saying
	 * whether the exit was found or not, and if the exit was found, the number
	 * of chambers in the path will be printed,too
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			if (args.length < 1) {
				throw new IllegalArgumentException("Please provide a file as a command line argument");
			} else {
				String dungeonFileName = args[0];
				chambers = new FindShortestPath(dungeonFileName);
			}
			FindShortestPath.dungeonQueue = new DLinkedPriorityQueue<Hexagon>();// creates
																				// queue
			Dungeon dungeonChamber = FindShortestPath.dungeon;
			dungeonChamber.getStart().markStart();
			dungeonQueue.add(dungeonChamber.getStart(), 0);// so the queue isn't
			dungeonChamber.getStart().markEnqueued(); // empty once it
			// goes into the
			// while loop

			while (!dungeonQueue.isEmpty()) {
				Hexagon currentChamber = dungeonQueue.removeMin();

				currentChamber.markDequeued();
				if (currentChamber.isExit()) {
					System.out.println("The exit has been found! The size of the dungeon queue is "
							+ dungeonQueue.size() + " The number of chambers in the path to the exit is "
							+ (currentChamber.getDistanceToStart() + 1));
					// currentChamber.getDistanceToStart() +1 is the length of
					// the path

					break;// to exit the loop
				}
				if (!adjacentToDragon(currentChamber)) {
					for (int i = 0; i < 6; i++) {
						Hexagon neighbour = currentChamber.getNeighbour(i);
						if (neighbour != null && !neighbour.isWall()) {

							int distance = currentChamber.getDistanceToStart() + 1;
							boolean distanceModified = false;
							if (neighbour.getDistanceToStart() > distance) {
								distanceModified = true;
								neighbour.setDistanceToStart(distance);
								neighbour.setPredecessor(currentChamber);

							}
							if (neighbour.isMarkedEnqueued() && distanceModified) {
								dungeonQueue.updatePriority(neighbour,
										neighbour.getDistanceToExit(dungeon) + neighbour.getDistanceToStart());

							}
							if (!neighbour.isMarkedDequeued()) {
								dungeonQueue.add(neighbour,
										neighbour.getDistanceToExit(dungeon) + neighbour.getDistanceToStart());
								neighbour.markEnqueued();
							}

						}
					}
				}

			}
			// to notify the user if the exit can't be found.
			if (dungeonQueue.isEmpty()) {
				System.out.println(" The exit is not found!");
			}
			// catching exceptions

		} catch (InvalidDungeonCharacterException e) {
			System.out.println("There is an invalid dungeon character in the file, please try again!");
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found, please try again!");
		} catch (IOException e) {
			System.out.println("Invalid Input/Output file, please try again!");
		}
	}

}
