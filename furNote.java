import pkg.*;
public abstract class furNote
{
	
	public abstract void move(double dx, double dy);
	public abstract void moveTo(double x, double y);

	public abstract Rectangle getHitbox();
	
	public abstract void destroy();
	
	public abstract boolean isOutOfBounds();
	public abstract boolean isAboveBounds();
	
	public abstract int getLane();
	public abstract int getY();
	
	
	public abstract void lock();
	public abstract void unlock();
	public abstract boolean isActivatable();
	public abstract boolean isActivated();
	public abstract void activate();
	public abstract void deactivate();
	public abstract int getLength();
	
	
}
