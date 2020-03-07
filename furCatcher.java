import pkg.*;
public class furCatcher
{
	private Rectangle d;
	private Rectangle f;
	private Rectangle j;
	private Rectangle k;
	private Rectangle hitbar;
	public final int defaultHeight = 100;
	public final int defaultWidth = 100;
	
	public furCatcher(){
		
		hitbar = new Rectangle(0, 440, 400, 10);
		d = new Rectangle(0, 400, defaultWidth, defaultHeight);
		f = new Rectangle(100, 400, defaultWidth, defaultHeight);
		j = new Rectangle(200, 400, defaultWidth, defaultHeight);
		k = new Rectangle(300, 400, defaultWidth, defaultHeight);
		
		hitbar.setColor(Color.RED);
		hitbar.draw();
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
	
	public int catcherX(String catcher){
		if(catcher.equals("d")){
			return d.getX();
		} else if(catcher.equals("f")){
			return f.getX();
		} else if(catcher.equals("j")){
			return j.getX();
		} else if(catcher.equals("k")){
			return k.getX();
		}
		return 0;
	}	
	public int catcherY(String catcher){
		if(catcher.equals("d")){
			return d.getY();
		} else if(catcher.equals("f")){
			return f.getY();
		} else if(catcher.equals("j")){
			return j.getY();
		} else if(catcher.equals("k")){
			return k.getY();
		}
		return 0;
	}
	public void highlight(String catcher){
		if(catcher.equals("d")){
			d.setColor(Color.MAGENTA);
			d.fill();
		} else if(catcher.equals("f")){
			f.setColor(Color.MAGENTA);
			f.fill();
		} else if(catcher.equals("j")){
			j.setColor(Color.MAGENTA);
			j.fill();
		} else if(catcher.equals("k")){
			k.setColor(Color.MAGENTA);
			k.fill();
		}
	}
	public void unHighlight(String catcher){
		if(catcher.equals("d")){
			d.setColor(Color.BLACK);
			d.draw();
		} else if(catcher.equals("f")){
			f.setColor(Color.BLACK);
			f.draw();
		} else if(catcher.equals("j")){
			j.setColor(Color.BLACK);
			j.draw();
		} else if(catcher.equals("k")){
			k.setColor(Color.BLACK);
			k.draw();
		}
	}	
	
}
