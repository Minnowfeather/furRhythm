import pkg.*;
public class furCatcher
{
	private Rectangle d, f, j, k;
	private Rectangle hitbar;
	public final int defaultHeight = 100;
	public final int defaultWidth = 100;
	private boolean dActive, fActive, jActive, kActive;

	
	public furCatcher(){
		
		hitbar = new Rectangle(0, 490, defaultWidth*4, 10);
		d = new Rectangle(0, 450, defaultWidth, defaultHeight);
		f = new Rectangle(100, 450, defaultWidth, defaultHeight);
		j = new Rectangle(200, 450, defaultWidth, defaultHeight);
		k = new Rectangle(300, 450, defaultWidth, defaultHeight);
		
		dActive = false;
		fActive = false;
		jActive = false;
		kActive = false;

		hitbar.setColor(Color.RED);
		hitbar.draw();
		d.draw();
		f.draw();
		j.draw();
		k.draw();
	}
	
	public boolean contains(int catcher, furNote note){
		if(catcher == 0){
			if(d.contains(note.getHitbox())){
				return true;
			}
		} else if(catcher == 1){
			if(f.contains(note.getHitbox())){
				return true;
			}
		} else if(catcher == 2){
			if(j.contains(note.getHitbox())){
				return true;
			}
		} else if(catcher == 3){
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
	
	public int catcherX(int catcher){
		if(catcher == 0){
			return d.getX();
		} else if(catcher == 1){
			return f.getX();
		} else if(catcher == 2){
			return j.getX();
		} else if(catcher == 3){
			return k.getX();
		}
		return 0;
	}	
	public int catcherY(int catcher){
		if(catcher == 0){
			return d.getY();
		} else if(catcher == 1){
			return f.getY();
		} else if(catcher == 2){
			return j.getY();
		} else if(catcher == 3){
			return k.getY();
		}
		return 0;
	}
	public void highlight(int catcher){
		if(catcher == 0){
			d.setColor(Color.MAGENTA);
			d.fill();
		} else if(catcher == 1){
			f.setColor(Color.MAGENTA);
			f.fill();
		} else if(catcher == 2){
			j.setColor(Color.MAGENTA);
			j.fill();
		} else if(catcher == 3){
			k.setColor(Color.MAGENTA);
			k.fill();
		}
	}
	public void unHighlight(int catcher){
		if(catcher == 0){
			d.setColor(Color.BLACK);
			d.draw();
		} else if(catcher == 1){
			f.setColor(Color.BLACK);
			f.draw();
		} else if(catcher == 2){
			j.setColor(Color.BLACK);
			j.draw();
		} else if(catcher == 3){
			k.setColor(Color.BLACK);
			k.draw();
		}
	}

	public void activate(int catcher){
		if(catcher == 0){
			dActive = true;
		} else if(catcher == 1){
			fActive = true;
		} else if(catcher == 2){
			jActive = true;
		} else if(catcher == 3){
			kActive = true;
		}
	}

	public void deactivate(int catcher){
		if(catcher == 0){
			dActive = false;
		} else if(catcher == 1){
			fActive = false;
		} else if(catcher == 2){
			jActive = false;
		} else if(catcher == 3){
			kActive = false;
		}
	}

	public boolean isActivated(int catcher){
		if(catcher == 0){
			return dActive;
		} else if(catcher == 1){
			return fActive;
		} else if(catcher == 2){
			return jActive;
		} else if(catcher == 3){
			return kActive;
		}
		return false;
	}

	public void destroy(){
		d.translate(600, 600);
		f.translate(600, 600);
		j.translate(600, 600);
		k.translate(600, 600);
		hitbar.translate(600, 600);
	}
	
}
