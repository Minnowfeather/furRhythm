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
		// Text penis = new Text(300,300,"penis");
		// penis.draw();
		
		// EasyReader rateReader = new EasyReader("rates.txt");
		// int speed = rateReader.readInt();
		// int rate = rateReader.readInt();
		
		int speed = 3;
		int rate = 40;
		
		gameRunning = true;

		keys = new String[]{"d", "f", "j", "k"};

		f = new furCatcher();

		healthBar = new furHealthBar(100);

		currentNotes = new ArrayList<furNote>();

		scoreCounter = 0;
		scoreCounterText = new Text(500,0, ""+scoreCounter);
		scoreCounterText.draw();

		timeCounter = 0;
		
		// gameThings.add(rateReader);
		gameThings.add(f);
		gameThings.add(healthBar);
		gameThings.add(scoreCounterText);
		
		gameLoop(speed, rate);
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
					if(f.contains(j, currentNotes.get(i)) && f.isActivated(j)){
						f.deactivate(j);
						currentNotes.get(i).destroy();
						furNote n = currentNotes.remove(i);
						n = null;
						scoreCounter++;
					}
				}
			}

			//Spawn notes randomly
			if(timeCounter%rate == 0){
				currentNotes.add(new furNote((int)(Math.random()*keys.length), f));
			}
			
			// update GUI
			scoreCounterText.setText(""+scoreCounter);
			healthBar.updateBar();

			// check for offscreen notes
			for(int i = 0; i < currentNotes.size(); i++){
				if(currentNotes.get(i).isOutOfBounds()){
					currentNotes.get(i).destroy();
					furNote n = currentNotes.remove(i);
					n = null;
					scoreCounter--;
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

// ToDO: create options or be able to set controls upon each boot up