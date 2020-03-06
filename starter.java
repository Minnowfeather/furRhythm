import pkg.*;
import java.util.*;
public class starter implements InputControl, InputKeyControl 
{
		static String[] keys;
		static furCatcher f;
		static ArrayList<furNote> currentNotes;
		static int scoreCounter;
		
        public static void main(String args[])
        {
		
			// following line is necessary for onMouseClick, don't change
			MouseController mC = new MouseController(Canvas.getInstance(),new starter());
			
			// please leave following line alone, necessary for keyboard input
			KeyController kC = new KeyController(Canvas.getInstance(),new starter());
			
			keys = new String[]{"d", "f", "j", "k"};
			f = new furCatcher();
			currentNotes = new ArrayList<furNote>();
			scoreCounter = 0;
			Text scoreCounterText = new Text(0,0, ""+scoreCounter);
			scoreCounterText.draw();
			
			furNote n = new furNote(50);
			currentNotes.add(n);
			int timeCounter = 0;
			while(true){
				timeCounter += 1;
				for(int i = 0; i < currentNotes.size(); i++){
					currentNotes.get(i).move(0, 2);
				}
				if(timeCounter%20 == 0){
					currentNotes.add(new furNote(100*(int)(Math.random()*4)));
				}
				Canvas.pause(20);
				scoreCounterText.setText(""+scoreCounter);
			}
		}
		
		public void onMouseClick(double x, double y){
			// and/or here
	
		}
		
		public void keyPress(String s)
		{
			// temp holds the enter character
			
			for(String key:keys){
				if(s.equals(key)){
					// f.highlight(key);
					for(int i = 0; i < currentNotes.size(); i++){
						if(f.contains(key, currentNotes.get(i))){
							currentNotes.get(i).destroy();
							currentNotes.remove(i);
							scoreCounter++;
						}
					}
				}
			}
					
			
			char done = (char)10;
			String temp = Character.toString(done);
			
			
			
		}
}
