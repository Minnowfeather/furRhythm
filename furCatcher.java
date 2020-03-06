import pkg.*;
public class furCatcher
{
	private Rectangle d;
	private Rectangle f;
	private Rectangle j;
	private Rectangle k;
	
	public furCatcher(){
		d = new Rectangle(0, 400, 100, 50);
		f = new Rectangle(100, 400, 100, 50);
		j = new Rectangle(200, 400, 100, 50);
		k = new Rectangle(300, 400, 100, 50);
		
		d.draw();
		f.draw();
		j.draw();
		k.draw();
	}
	
	public boolean contains(String catcher, furNote note){
		if(catcher.equals("d")){
			if(d.contains(note.getHitbox())){
				return true;
			}
		} else if(catcher.equals("f")){
			if(f.contains(note.getHitbox())){
				return true;
			}
		} else if(catcher.equals("j")){
			if(j.contains(note.getHitbox())){
				return true;
			}
		} else if(catcher.equals("k")){
			if(k.contains(note.getHitbox())){
				return true;
			}
		}
		return false;
	}
	
	// public void highlight(String catcher){
		// switch(catcher){
			// case "d":
				// break;
			// case "f":
				// break;
			// case "j":
				// break;
			// case "k":
				// break;
			// default:
				
		// }
			
	
}