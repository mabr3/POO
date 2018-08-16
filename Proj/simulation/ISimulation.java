package simulation;
/**
 * Interface for the simulation to use, has the methods that must be defined to ensure the program works
 * 
 * @author Miguel Rodrigues, nr 76176
 * @author Afonso Soares, nr 81086
 */
public interface ISimulation {
	
	/**Add a grid to this simulation**/
	void addGrid(int cols, int rows);
	/**Add a PEC to this simulation**/
	void addPEC();
	/**Put everything in the right place and initialize the simulation*/
	void simulate();

}
