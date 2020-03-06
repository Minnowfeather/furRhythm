import pkg.*;
public class furNote
{
	
	private Rectangle hitbox;
	
	public furNote(int x){
		hitbox = new Rectangle(x, -20, 40, 20);
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