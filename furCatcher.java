import pkg.*;
public class furCatcher
{
	private Rectangle d, f, j, k;
private Rectangle d100, f100, j100, k100;
	private Rectangle hitbar;
	public final int defaultHeight = 50;
	public final int defaultWidth = 100;
	private boolean dActive, fActive, jActive, kActive;

	
	public furCatcher(){
		
		hitbar = new Rectangle(0, 490, defaultWidth*4, 10);
		d = new Rectangle(0, 470, defaultWidth, defaultHeight);
		f = new Rectangle(100, 470, defaultWidth, defaultHeight);
		j = new Rectangle(200, 470, defaultWidth, defaultHeight);
		k = new Rectangle(300, 470, defaultWidth, defaultHeight);
		d100 = new Rectangle(0, d.getY()-20, defaultWidth, defaultHeight+40);
		f100 = new Rectangle(100, f.getY()-20, defaultWidth, defaultHeight+40);
		j100 = new Rectangle(200, j.getY()-20, defaultWidth, defaultHeight+40);
		k100 = new Rectangle(300, k.getY()-20, defaultWidth, defaultHeight+40);
		d100.setColor(Color.GRAY);
		f100.setColor(Color.GRAY);
		j100.setColor(Color.GRAY);
		k100.setColor(Color.GRAY);
		dActive = false;
		fActive = false;
		jActive = false;
		kActive = false;

		// d100.draw();
		// f100.draw();
		// j100.draw();
		// k100.draw();
		
		d.draw();
		f.draw();
		j.draw();
		k.draw();
		
		hitbar.setColor(Color.RED);
		hitbar.draw();
	}
	
	public int contains(int catcher, furNote note){
		if(catcher == 0)
		{
			if(d.contains(note.getHitbox())){
				return 300;
			}
			if(d100.contains(note.getHitbox())){
				return 100;
			}
		}
		else if(catcher == 1)
		{
			if(f.contains(note.getHitbox())){
				return 300;
			}
			if(f100.contains(note.getHitbox())){
				return 100;
			}
		}
		else if(catcher == 2)
		{
			if(j.contains(note.getHitbox())){
				return 300;
			}
			if(j100.contains(note.getHitbox())){
				return 100;
			}
		}
		else if(catcher == 3)
		{
			if(k.contains(note.getHitbox())){
				return 300;
			}
			if(k100.contains(note.getHitbox())){
				return 100;
			}
		}
		return -1;
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
