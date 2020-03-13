import pkg.*;
public class furNote
{
	
	private Rectangle hitbox;
	
	public furNote(int catcher, furCatcher f){
		hitbox = new Rectangle(f.catcherX(catcher), -20, f.defaultWidth, 20);
		hitbox.draw();
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
	
}
