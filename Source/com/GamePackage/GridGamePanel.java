package com.GamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

class GridGamePanel extends JPanel implements KeyEventDispatcher {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int score;
	private List<GridRectangle> playableGrid;
	private double gridLength = 100;
	private boolean redrawGame;
	
	public GridGamePanel(){
		
		this.setLayout(new GridLayout(0, 1));
		this.setSize(500, 400);
		this.setBackground(Color.white);
		this.setBounds(new Rectangle(500, 400));
		
		initialiseGameGrid();
	//	this.addKeys();
		this.setFocusable(true);
	}
	
	private void addKeys() {
		// TODO Auto-generated method stub
		addKeyListener(new KeyListener() {
			@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			//			bat.keyReleased(e);
		//	playableGrid
		}

		@Override
		public void keyPressed(KeyEvent e) {
	//		bat.keyPressed(e);
			if (e.getKeyCode() == KeyEvent.VK_LEFT){
				System.out.println("Left pressed");
				moveLeft();
				// move grid towards left
				addRandomNumber();
				updateScoreLabel();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				System.out.println("Right pressed");
				moveRight();
				addRandomNumber();
				// move grid towards right
				updateScoreLabel();
			}
			if (e.getKeyCode() == KeyEvent.VK_UP){
				System.out.println("Up pressed");
				// move grid towards up
				moveUp();
				addRandomNumber();
				updateScoreLabel();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN){
				System.out.println("Down pressed");
				// move grid towards down
				moveDown();
				addRandomNumber();
				updateScoreLabel();
			}		
		}
		});
	}
	
	private void updateScoreLabel() {
		// TODO Auto-generated method stub
		GameClass.updateScoreLabel(score);
	}
	
	private void addRandomNumber() {
		int Min = 0, Max = 15, lower = 1;
		int colorIndex = (int) (Min + (int)(Math.random() * ((Max - Min) + 1)));
		
		int fullGrids = 0;
		while (getValueFromGrid(colorIndex) != 0){		
			colorIndex = (int) (Min + (int)(Math.random() * ((Max - Min) + 1)));
		//	fullGrids++;
		//	if(fullGrids == 16)
				exitGame();
		}
		
		int randomNum = (int) (Min + (int)(Math.random() * ((lower - Min) + 1)));
		if(randomNum == 0){
			setValueInGrid(colorIndex, 2);
		}
		else if(randomNum == 1){
			setValueInGrid(colorIndex, 4);
		}
	}
		private void exitGame() {
		// TODO Auto-generated method stub
		// check whether all are occupied, if yes then show message box, saying game over
			int zero = 0;
			boolean b = playableGrid.contains(new Integer(0));
			int i = 0;
			while(getValueFromGrid(i) != 0){
				i++;
				if(i == playableGrid.size()){
					System.out.println("doesnt contain");
					JOptionPane.showMessageDialog(this,
									"Game Over!!! Try Again.");
								this.setFocusable(false);
				}
			}
		
	}

		private void moveDown() {
			System.out.println("inside down fn");
			for(int i=12; i<16; i++){		
				moveGridDown(i);
				repaint();
			}
		}

		private void moveGridDown(int i) {
			// i -> Move down, so all tiles should move down
			// i is last element, i = 12, 13, 14, 15
			int first = i;
			int second = i-4;
			int third = i-8;
			int fourth = i-12;
			
			int value4 = getValueFromGrid(fourth); 
			int value3 = getValueFromGrid(third); 
			int value2 = getValueFromGrid(second); 
			int value1 = getValueFromGrid(first);
			
			List<Integer> l = GetList(value1, value2, value3, value4);
			
			List<Boolean> combined = new ArrayList<Boolean>();
			for(int p=0; p<4; p++){
				combined.add(false);
			}
			
			System.out.println("before vals");
			for(int v : l)
				System.out.println(v);
		
	//		boolean once = false;
			for(int j = 0; j<4; j++)
			{
				
				int curr = l.get(j);
				System.out.println("j: "+j+" curr: "+curr);
				
				for(int k=j+1; k<4;)
				{
					int temp = l.get(k);
					
					if(curr==0 && temp == 0)
						break;
					
					if(curr == 0){
						int jiter = j;
						int kiter = k;
						while( kiter < 4){
							l.set(jiter, l.get(kiter));
							if((kiter+1) == 4)
								l.set(kiter, 0);
							else
								l.set(kiter, l.get(kiter+1));
							jiter++;
							kiter++;
						}
						j--;
						break;
					}
					if(temp == 0){
						break;
					}
					else{
						// temp is non zero
						if(temp == curr){
							if(!combined.get(j)){
								l.set(j, 2*curr);
								l.set(k, 0);
								score+= 2*curr;
								combined.set(j, true);
								combined.set(k, true);
								j--;
							}
							break;
						}
						else{
							break;
						}
					}
				}
			}
			
			setValueInGrid(first, l.get(0));
			setValueInGrid(second, l.get(1));
			setValueInGrid(third, l.get(2));
			setValueInGrid(fourth, l.get(3));
			
		}

		private List<Integer> GetList(int value1, int value2, int value3,
				int value4) {
			List<Integer> l = new ArrayList<Integer>();
			l.add(value1); // leftmost element
			l.add(value2); // 1
			l.add(value3); // 2
			l.add(value4); // 3 
			return l;
		}

		private void moveUp() {
			System.out.println("inside up fn");
			System.out.println("inside down fn");
			for(int i=0; i<4; i++){		
				moveGridUp(i);
				repaint();
			}
		}

		private void moveGridUp(int i) {
			// i -> Move up, so all tiles should move up
			// i is last element, i = 0, 1, 2, 3
			int first = i;
			int second = i+4;
			int third = i+8;
			int fourth = i+12;
			
			int value1 = getValueFromGrid(first);
			int value2 = getValueFromGrid(second);
			int value3 = getValueFromGrid(third);
			int value4 = getValueFromGrid(fourth);
			
			List<Integer> l = GetList(value1, value2, value3, value4);
			
			List<Boolean> combined = new ArrayList<Boolean>();
			for(int p=0; p<4; p++){
				combined.add(false);
			}
			
			System.out.println("before vals: up");
			for(int v : l)
				System.out.println(v);
		
	//		boolean once = false;
			for(int j = 0; j<4; j++){
				int curr = l.get(j);
				System.out.println("j: "+j+" curr: "+curr);
				
				for(int k=j+1; k<4;)
				{
					int temp = l.get(k);
					
					if(curr==0 && temp == 0)
						break;
					
					if(curr == 0){
						int jiter = j;
						int kiter = k;
						while( kiter < 4){
							l.set(jiter, l.get(kiter));
							if((kiter+1) == 4)
								l.set(kiter, 0);
							else
								l.set(kiter, l.get(kiter+1));
							jiter++;
							kiter++;
						}
						j--;
						break;
					}
					if(temp==0)
						break;
					else{
						// temp is non zero
						if(temp == curr){
							if(!combined.get(j)){
								l.set(j, 2*curr);
								l.set(k, 0);
								score+= 2*curr;
								combined.set(j, true);
								combined.set(k, true);
								j--;
							}
							break;
						}
						else{
							break;
						}
					}
				}
			}
					
			System.out.println("outside it");
			for(int v : l)
				 System.out.println(v);
			
			setValueInGrid(first, l.get(0));
			setValueInGrid(second, l.get(1));
			setValueInGrid(third, l.get(2));
			setValueInGrid(fourth, l.get(3));
			
		}

		private void moveRight() {
			// moveRight --->>
			// If the number on the i+1 is 0 move right
			// If number is same, then add them, and result towards right
			System.out.println("inside right fn");
			for(int i=3; i<16; i+=4){		
				moveGridRight(i);
				repaint();
			}
		}

		private void moveGridRight(int i) {
		// i is the last element, where it should end, when moving right
		// i -> 3, 7, 11, 15
			int first = i;
			int second = i-1;
			int third = i-2;
			int fourth = i-3;
			
			int value4 = getValueFromGrid(first); 
			int value3 = getValueFromGrid(second); 
			int value2 = getValueFromGrid(third); 
			int value1 = getValueFromGrid(fourth);
			
			List<Integer> l = GetList(value1, value2, value3, value4);
			
			for(int j = 0; j<4; j++){
				int curr = l.get(j);
				
				for(int k=j+1; k<4;){
					
					int temp = l.get(k);
					if(temp == 0){
				//		moveZero = true;
						int kneg = j; // or = j
						int kplus = k;
						while(kneg > -1){
							l.set(kplus, l.get(kneg));
							l.set(kneg, 0);
							kneg--;
							kplus--;
						}
						break;
					}
					else{
						// temp is non zero
						if(temp == curr){
							l.set(j, 2*temp);
							l.set(k, 0);
							j--;
							score+= 2*temp;
							break;
						}
						else{
							break;
						}
					}
				}
			}
			
			setValueInGrid(first, l.get(3));
			setValueInGrid(second, l.get(2));
			setValueInGrid(third, l.get(1));
			setValueInGrid(fourth, l.get(0));
		
		}

		private void moveLeft() {
			// TODO Auto-generated method stub
			System.out.println("inside left fn");
			for(int i=0; i<16; i+=4){		
				moveGridLeft(i);
				repaint();
			}
		}
		
		private void moveGridLeft(int i) {
		// i -> Move left, so all tiles should move towards left , i = 0, 4, 8, 12
		int first = i;
		int second = i+1;
		int third = i+2;
		int fourth = i+3;
		
		int value1 = getValueFromGrid(fourth);
		int value2 = getValueFromGrid(third);
		int value3 = getValueFromGrid(second);
		int value4 = getValueFromGrid(first);
		
		List<Integer> l = GetList(value1, value2, value3, value4);
		
		for(int j = 0; j<4; j++){
			int curr = l.get(j);
			
			for(int k=j+1; k<4;){
				
				int temp = l.get(k);
				if(temp == 0){
			//		moveZero = true;
					int kneg = j; // or = j
					int kplus = k;
					while(kneg > -1){
						l.set(kplus, l.get(kneg));
						l.set(kneg, 0);
						kneg--;
						kplus--;
					}
					break;
				}
				else{
					// temp is non zero
					if(temp == curr){
						l.set(j, 2*temp);
						score+= 2*temp;
						l.set(k, 0);
						j--;
						break;
					}
					else{
						break;
					}
				}
			}
		}
		
		setValueInGrid(first, l.get(3));
		setValueInGrid(second, l.get(2));
		setValueInGrid(third, l.get(1));
		setValueInGrid(fourth, l.get(0));
	}


	private int getValueFromGrid(int index) {
		return playableGrid.get(index).getNum();
	}

	private void setValueInGrid(int index, int value) {
		playableGrid.get(index).setNum(value);
	}	

	public void initialiseGameGrid() {
		// TODO Auto-generated method stub
		Color c = Color.gray;
		redrawGame = true;
		playableGrid = new ArrayList<GridRectangle>();
		double x;
		double y = 25;
		
		for(int i=0; i<4; i++){
			x = 50;			
			for(int j=0; j<4; j++){
				playableGrid.add(new GridRectangle(x, y, c, gridLength, 0));
				x+=gridLength;
			}
			y += gridLength;
		}
		// At any 2 places -> selected randomly, add number - 2/4 -> random
		initNumbers();
		score = 0;
		this.addKeys();
		this.setFocusable(true);
		repaint();
	}
	
	private void initNumbers() {
		// TODO Auto-generated method stub
		int Min = 0, Max = 15, half = 7, lower = 1;
		int colorIndex = (int) (Min + (int)(Math.random() * ((Max - Min) + 1)));
		int colorIndex2 = (int) (Min + (int)(Math.random() * ((Max - Min) + 1)));
		
		while (colorIndex == colorIndex2)
			colorIndex2 = (int) (Min + (int)(Math.random() * ((Max - Min) + 1)));
		// position is random between 0-15, so function for that
		
		addNumToGrid(colorIndex);
		addNumToGrid(colorIndex2);
		
		
	}

	private void addNumToGrid(int colorIndex) {
		// TODO Auto-generated method stub
		// Kind of brute force but randomNum has 2 options only 0 and 1, if 0 -> add 2, else add 4
	/*	int Min = 0, Max = 15, half = 7, lower = 1;
		int randomNum = (int) (Min + (int)(Math.random() * ((lower - Min) + 1)));
		if(randomNum == 0){
			playableGrid.get(colorIndex).setNum(2);
		}
		else if(randomNum == 1){
			playableGrid.get(colorIndex).setNum(4);
		}*/
		
	//	playableGrid.get(0).setNum(2);
	//	playableGrid.get(4).setNum(2);
		playableGrid.get(8).setNum(2);
	//	playableGrid.get(12).setNum(2);
	}



	enum ColorEnum{
		// Colors for 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024 and 2048
		// Total color needed = 11
		RED(Color.red),
		PINK(Color.pink),
		BLUE(Color.blue),
		MAGNETA(Color.magenta),
		ORANGE(Color.orange);
		
		private final Color value;
		ColorEnum(Color value){
			this.value = value;
		}
		
		public Color getValue(){
			return this.value;
		}
	};
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		if(paintGame){
		for(GridRectangle l : playableGrid)
			l.paint(g);
//		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 if (e.getID() == KeyEvent.KEY_PRESSED) {
		             System.out.println("key pressed");
		      //       bat.keyPressed(e);
		         } else if (e.getID() == KeyEvent.KEY_RELEASED) {
		             System.out.println("key released");
		   //          bat.keyReleased(e);
		         }
		return false;
	}
	
	

}
