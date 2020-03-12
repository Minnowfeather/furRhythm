import pkg.*;
import java.util.*;
public class starter implements InputControl, InputKeyControl 
{
		static String[] keys;
		static furCatcher f;
		static ArrayList<furNote> currentNotes;
		static int scoreCounter;
		static boolean gameRunning;
		static furHealthBar healthBar;
        public static void main(String args[])
        {
		
			// following line is necessary for onMouseClick, don't change
			MouseController mC = new MouseController(Canvas.getInstance(),new starter());
			
			// please leave following line alone, necessary for keyboard input
			KeyController kC = new KeyController(Canvas.getInstance(),new starter());
			
			EasyReader speedReader = new EasyReader("scrollspeed.txt");
			EasyReader rateReader = new EasyReader("spawnrate.txt");
			int speed = speedReader.readInt();
			int rate = rateReader.readInt();


			gameRunning = true;

			keys = new String[]{"d", "f", "j", "k"};

			f = new furCatcher();

			healthBar = new furHealthBar(100);

			currentNotes = new ArrayList<furNote>();

			scoreCounter = 0;
			Text scoreCounterText = new Text(500,0, ""+scoreCounter);
			scoreCounterText.draw();

			int timeCounter = 0;
			
			// update loop
			while(gameRunning){
				timeCounter += 1;
				for(int i = 0; i < currentNotes.size(); i++){
					currentNotes.get(i).move(0, 2);
				}
				if(timeCounter%rate == 0){
					currentNotes.add(new furNote(keys[(int)(Math.random()*keys.length)], f));
				}
				Canvas.pause(speed);
				scoreCounterText.setText(""+scoreCounter);
				for(int i = 0; i < currentNotes.size(); i++){
					if(currentNotes.get(i).isOutOfBounds()){
						currentNotes.get(i).destroy();
						furNote n = currentNotes.remove(i);
						n = null;
						scoreCounter--;
						healthBar.setPercent(healthBar.getPercent()-2);
					}
				}
				healthBar.updateBar();
				if(healthBar.getPercent() <= 0){
					gameRunning = false;
				}
				
			}
			Rectangle clear = new Rectangle(0,0,600,600);
			clear.setColor(Color.WHITE);
			clear.fill();

			// Remove all current notes
			for(int i = 0; i < currentNotes.size(); i++){
				currentNotes.get(i).destroy();
				furNote n = currentNotes.remove(i);
				n = null;
			}

			// GUI destroy
			/*
			healthBar.destroy();
			healthBar = null;
			f.destroy();
			f = null;
			scoreCounterText.translate(600,600);
			scoreCounterText = null;
			*/

			Text end = new Text(250,270,"You died!");
			end.grow(2,2);
			end.draw();
		}
		
		public void onMouseClick(double x, double y){
			// and/or here
	
		}
		public void keyPress(String s)
		{
			// temp holds the enter character
			
			for(String key:keys){
				if(s.equals(key)){
					f.highlight(key);
					for(int i = 0; i < currentNotes.size(); i++){
						if(f.contains(key, currentNotes.get(i))){
							currentNotes.get(i).destroy();
							furNote n = currentNotes.remove(i);
							n = null;
							scoreCounter++;
						}
					}
				}
			}
					
			char done = (char)10;
			String temp = Character.toString(done);
		}
		
		public void keyRelease(String s){
			f.unHighlight(s);
		}
}
