import pkg.*;
public class furNoteHold extends furNote
{
	private Rectangle hitbox;
	private int lane;
	private boolean activated;
	private boolean activatable;
	public int hitValue;
	public furNoteHold(int catcher, furCatcher f, int l){
		lane = catcher;
		hitbox = new Rectangle(f.catcherX(catcher), -20-l, f.defaultWidth, l);
		hitbox.draw();
		activated = false;
		activatable = true;
		hitValue = -1;
	}
	
	public void move(double dx, double dy){
		hitbox.translate(dx, dy);
	}
	public void moveTo(double x, double y){
		hitbox.translate(x-hitbox.getX(), y-hitbox.getY());
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public void destroy(){
		move(600,600);
	}
	public boolean isOutOfBounds(){
		if(hitbox.getY() > 600){
			return true;
		}
		return false;
	}
	public boolean isAboveBounds(){
		if(hitbox.getY() < 0){
			return true;
		}
		return false;
	}
	public int getLane(){
		return lane;
	}
	public void activate(){
		if(activatable){
			activated = true;
		}
	}
	public void deactivate(){
		activated = false;
	}
	public boolean isActivated(){
		return activated;
	}
	public void lock(){
		activated = false;
		activatable = false;
	}
	public void unlock(){
		activatable = true;
	}
	public boolean isLocked(){
		return activatable;
	}
	public int getY(){
		return hitbox.getY();
	}
	public int getLength(){
		return hitbox.getHeight();
	}
}
