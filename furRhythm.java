import pkg.*;
import java.util.*;
public class furRhythm implements InputControl, InputKeyControl 
{
	static ArrayList<Object> gameThings;
	static String[] keys;
	static furCatcher f;
	static furHealthBar healthBar;
	static int scoreCounter;
	static int timeCounter;
	static Text scoreCounterText;
	static boolean gameRunning;
	static boolean startGame;
	static ArrayList<furNote> currentNotes;
	
	static Text mainMenuName;
	static Text mainMenuSubName;
	
	
	public void start()
	{
		// initialize mouse and keys
		MouseController mC = new MouseController(Canvas.getInstance(), new furRhythm());
		KeyController kC = new KeyController(Canvas.getInstance(), new furRhythm());
		
		startGame = false;
		gameThings = new ArrayList<Object>();
		mainMenuInit();
		gameExit();
	}
	
	
	
	public void mainMenuInit()
	{
		
		mainMenuName = new Text(500,300,"Main Menu");
		mainMenuName.grow(200,5);
		mainMenuName.translate(-mainMenuName.getWidth()/2,-mainMenuName.getHeight()/2);
		mainMenuName.draw();
		
		mainMenuSubName = new Text(500,300+mainMenuName.getHeight(), "Press 'p' to play");
		mainMenuSubName.grow(200,5);
		mainMenuSubName.translate(-mainMenuSubName.getWidth()/2,-mainMenuSubName.getHeight()/2);
		mainMenuSubName.draw();
		
		gameThings.add(mainMenuName);
		gameThings.add(mainMenuSubName);
		
		mainMenuLoop();
	}
	
	public void mainMenuLoop()
	{
		while(true)
		{
			System.out.println("ToDO: fix this");
			if(startGame){
				startGame = false;
				mainMenuName.translate(1200,1200);
				mainMenuSubName.translate(1200,1200);
				gameInit();
			}
		}
	}
	
	
	public void gameInit()
	{
		clearScreen();
		
		gameRunning = true;

		keys = new String[]{
			furConstants.d,
			furConstants.f,
			furConstants.j,
			furConstants.k
		};

		f = new furCatcher();

		healthBar = new furHealthBar(100);

		currentNotes = new ArrayList<furNote>();

		scoreCounter = 0;
		scoreCounterText = new Text(600,0, ""+scoreCounter);
		scoreCounterText.translate(-scoreCounterText.getWidth(),0);
		scoreCounterText.draw();

		timeCounter = 0;
		
		// gameThings.add(rateReader);
		gameThings.add(f);
		gameThings.add(healthBar);
		gameThings.add(scoreCounterText);
		
		gameLoop(furConstants.speed, furConstants.rate);
	}
	
	public void gameLoop(int speed, int rate)
	{
		while(gameRunning)
		{
			// keep track of time
			timeCounter += 1;

			// move notes down
			for(int i = 0; i < currentNotes.size(); i++){
				currentNotes.get(i).move(0, 2);
			}

			//check for note hit
			for(int i = 0; i < currentNotes.size(); i++){
				for(int j = 0; j < keys.length; j++){
					
					// Handle tap notes
					if(f.contains(j, currentNotes.get(i)) && f.isActivated(j) && (currentNotes.get(i) instanceof furNoteTap)){
						f.deactivate(j);
						currentNotes.get(i).destroy();
						furNote n = currentNotes.remove(i);
						n = null;
						scoreCounter++;
					}
					
					// Handle holds
					// Check type
					if(currentNotes.get(i) instanceof furNoteHold){
						
						// If holding the key and catcher contains thing
						if(f.isActivated(currentNotes.get(i).getLane()) && f.contains(j, currentNotes.get(i))){
							if(currentNotes.get(i).isActivated()){
								// do nothing
							} else {
								currentNotes.get(i).activate();
							}
						} else if(currentNotes.get(i).isActivated() && !f.isActivated(currentNotes.get(i).getLane())){
							currentNotes.get(i).deactivate();
							currentNotes.get(i).lock();
						// else if the top of the note is inside the hitzone AND the note is activated
						} else if((currentNotes.get(i).getY() >= 450) && (currentNotes.get(i).isActivated())){
							// remove note
							f.deactivate(currentNotes.get(i).getLane());
							currentNotes.get(i).destroy();
							furNote n = currentNotes.remove(i);
							n = null;
							scoreCounter++;
						} else if(((currentNotes.get(i).getY()+currentNotes.get(i).getLength()) > 550) && (!currentNotes.get(i).isActivated())){
							currentNotes.get(i).lock();
						}
					}
				}
			}

			//Spawn notes randomly
			if(timeCounter%rate == 0){
				int position = (int)(Math.random()*keys.length);
				boolean reroll = true;
				while(reroll){
					reroll = false;
					for(int i = 0; i < currentNotes.size(); i++){
						if(currentNotes.get(i) instanceof furNoteHold){
							if((currentNotes.get(i).getLane() == position) && currentNotes.get(i).isAboveBounds()){
								position = (int)(Math.random()*keys.length);
								reroll = true;
							}
						}
					}
				}
				int rand = (int)(Math.random()*2);
				if(rand == 1){
					currentNotes.add(new furNoteHold(position, f, 200));
				} else {
					currentNotes.add(new furNoteTap(position, f));
				}
			}
			
			// update GUI
			scoreCounterText.setText(""+scoreCounter);
			scoreCounterText.translate(-scoreCounterText.getX()+600-scoreCounterText.getWidth(),0);
			healthBar.updateBar();

			// check for offscreen notes
			for(int i = 0; i < currentNotes.size(); i++){
				if(currentNotes.get(i).isOutOfBounds()){
					currentNotes.get(i).destroy();
					furNote n = currentNotes.remove(i);
					n = null;
					scoreCounter = 0;
					healthBar.setPercent(healthBar.getPercent()-5);
				}
			}
			
			// death condition
			if(healthBar.getPercent() <= 0){
				gameRunning = false;
			}
			
			// delay before next frame
			Canvas.pause(speed);
		}
		endGameplay();
	}
	
	public void endGameplay()
	{
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
		
		clearScreen();
		Canvas.pause(200);
		mainMenuInit();
	}
	
	public void clearScreen()
	{
		for(int i = 0; i < gameThings.size(); i++){
			Object j = gameThings.remove(i);
			j = null;
		}
		Rectangle clear = new Rectangle(0,0,600,600);
		clear.setColor(Color.WHITE);
		clear.fill();
		gameThings.add(clear);
	}
	
	public void gameExit()
	{
		
		clearScreen();
		
	}
	
	
	public void onMouseClick(double x, double y)
	{


	}
	
	public void keyPress(String s)
	{
		if(s.equals("p")){
			startGame = true;
		}
		
		if(gameRunning){
			for(int j =0; j < keys.length; j++){
				if(s.equals(keys[j])){
					f.highlight(j);
					f.activate(j);
				}
			}
		}
		
		char enterTemp = (char)10;
		String enterKey = Character.toString(enterTemp);
	}
	
	public void keyRelease(String s)
	{
		if(gameRunning)
		{
			for(int i = 0; i < keys.length; i++){
				if(s.equals(keys[i])){
					f.unHighlight(i);
					f.deactivate(i);
				}
			}
		}
	}
}
