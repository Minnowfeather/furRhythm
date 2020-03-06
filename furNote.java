import pkg.*;
public class furNote
{
	
	private Rectangle hitbox;
	
	public furNote(int x, furCatcher f){
		hitbox = new Rectangle(x, -20, f.defaultWidth, 20);
		hitbox.draw();
	}
	public furNote(String catcher, furCatcher f){
		hitbox = new Rectangle(f.catcherX(catcher), -20, f.defaultWidth, 20);
		hitbox.draw();
	}
	
	public void move(int dx, int dy){
		hitbox.translate(dx, dy);
	}
	public void moveTo(int x, int y){
		// ToDO: implement
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public void destroy(){
		move(600,600);
	}
	
}
