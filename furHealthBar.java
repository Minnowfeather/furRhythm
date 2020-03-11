import pkg.*;
public class furHealthBar
{
	private Rectangle bar;
	private Rectangle outline;
	private int percent;
	public furHealthBar(int percentage){
		percent = percentage;
		bar = new Rectangle(-100+percent, 0, 100, 20);
		outline = new Rectangle(0,0,100,20);
		bar.setColor(Color.PINK);
		bar.fill();
		outline.draw();
	}

	public void updateBar(){
		bar.translate(-100-(int)bar.getX()+percent, 0);
	}

	public void setPercent(int percentage){
		percent = percentage;
	}

	public int getPercent(){
		return percent;
	}
}