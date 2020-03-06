import pkg.*;
public class furCatcher
{
	private Rectangle d;
	private Rectangle f;
	private Rectangle j;
	private Rectangle k;
	public final int defaultHeight = 50;
	public final int defaultWidth = 100;
	
	public furCatcher(){
		d = new Rectangle(0, 400, defaultWidth, defaultHeight);
		f = new Rectangle(100, 400, defaultWidth, defaultHeight);
		j = new Rectangle(200, 400, defaultWidth, defaultHeight);
		k = new Rectangle(300, 400, defaultWidth, defaultHeight);
		
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
	/*
	public static int defaultHeight(){
		return defaultHeight;
	}
	public static int defaultWidth(){
		return defaultWidth;
	}
	*/
	
	public static int catcherX(String catcher){
		if(catcher.equals("d")){
			return d.getX();
		} else if(catcher.equals("f")){
			return k.getX();
		} else if(catcher.equals("j")){
			return j.getX();
		} else if(catcher.equals("k")){
			return k.getX();
		}
	}
	public static int catcherY(String catcher){
		if(catcher.equals("d")){
			return d.getY();
		} else if(catcher.equals("f")){
			return k.getY();
		} else if(catcher.equals("j")){
			return j.getY();
		} else if(catcher.equals("k")){
			return k.getY();
		}
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
