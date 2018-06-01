import java.awt.Point;


public abstract class Projectile {

	public abstract Point getCoord(); //returns the coord of the projectile

	public abstract void stop(); //stops the projectile so that it can shoot again

	public abstract void go(); //
	
	public abstract boolean cold(); //returns true if the projectile is a snowpea so that it will slow the zombie
}
