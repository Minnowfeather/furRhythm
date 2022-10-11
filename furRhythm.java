import pkg.*;
import java.util.*;
public class furRhythm implements InputControl	, InputKeyControl 
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
		int combo = 0;
		Text comboText = new Text(300,300,""+combo);
		comboText.translate(-comboText.getWidth()/2, -comboText.getHeight()/2);
		comboText.draw();
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
					if((currentNotes.get(i).hitValue != -1) && f.isActivated(j) && (currentNotes.get(i) instanceof furNoteTap)){
						f.deactivate(j);
						currentNotes.get(i).destroy();
						furNote n = currentNotes.remove(i);
						n = null;
						scoreCounter += currentNotes.get(i).hitValue;
						combo++;
					}
					
					// Handle holds
					// Check type
					if(currentNotes.get(i) instanceof furNoteHold){
						
						// If holding the key and catcher contains thing
						if(f.isActivated(currentNotes.get(i).getLane()) && (currentNotes.get(i).hitValue != -1)){
							if(currentNotes.get(i).isActivated()){
								// do nothing
								System.out.println(currentNotes.get(i).hitValue);
							} else {
								currentNotes.get(i).activate();
								currentNotes.get(i).hitValue = 300;
								System.out.println(currentNotes.get(i).hitValue);
							}
						// else if the top of the note is inside the hitzone AND the note is activated
						} else if((currentNotes.get(i).getY() >= 470) && (currentNotes.get(i).isActivated())){
							// remove note
							
							//ToDO: Figure out whty hitValue resets to -1 here
							System.out.println(currentNotes.get(i).hitValue);
							f.deactivate(currentNotes.get(i).getLane());
							currentNotes.get(i).destroy();
							furNote n = currentNotes.remove(i);
							n.move(200,-200);
							n = null;
							scoreCounter += currentNotes.get(i).hitValue;
							combo++;
							System.out.println("done holding " + currentNotes.get(i).hitValue);
						} else if(currentNotes.get(i).isActivated() && !f.isActivated(currentNotes.get(i).getLane()) && currentNotes.get(i).isLocked()){
							System.out.println("locked");
							currentNotes.get(i).deactivate();
							currentNotes.get(i).lock();
							currentNotes.get(i).hitValue = -1;
							System.out.println(currentNotes.get(i).hitValue);
						} else if(((currentNotes.get(i).getY()+currentNotes.get(i).getLength()) > 570) && (!currentNotes.get(i).isActivated()) && currentNotes.get(i).isLocked()){
							System.out.println("locked");
							currentNotes.get(i).lock();
							currentNotes.get(i).hitValue = -1;
							System.out.println(currentNotes.get(i).hitValue);
						}
					}
					if(f.isActivated(currentNotes.get(i).getLane()) && currentNotes.get(i).hitValue == -1){
						currentNotes.get(i).hitValue = f.contains(j, currentNotes.get(i));
					}
				}
			}

			//Spawn notes randomly
			if(timeCounter%rate == 0){
				// int position = (int)(Math.random()*keys.length);
				int position = 1;
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
					// currentNotes.add(new furNoteTap(position, f));
					currentNotes.add(new furNoteHold(position, f, 200));
				}
			}
			
			// check for offscreen notes
			for(int i = 0; i < currentNotes.size(); i++){
				if(currentNotes.get(i).isOutOfBounds()){
					currentNotes.get(i).destroy();
					furNote n = currentNotes.remove(i);
					n = null;
					healthBar.setPercent(healthBar.getPercent()-5);
					combo = 0;
				}
			}
			
			// update GUI
			scoreCounterText.setText(""+scoreCounter);
			scoreCounterText.translate(-scoreCounterText.getX()+600-scoreCounterText.getWidth(),0);
			healthBar.updateBar();
			comboText.setText(""+combo);
			comboText.translate(-comboText.getX()+300-comboText.getX()/2, -comboText.getY()+300-comboText.getHeight()/2);
			if(combo == 0){
				comboText.setColor(Color.RED);
				comboText.draw();
			} else {
				comboText.setColor(Color.BLACK);
				comboText.draw();
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
