package com.GamePackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameClass implements ActionListener {

	private static JFrame mainFrame;
	private static JPanel titlePanel, infoPanel;
	private static GridGamePanel gamePanel;
	
	private static JLabel titleLabel, moveLabel, moveCount, gameDesc, gameDesc2, gameDesc3;

	private static JButton restart;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GameClass gc = new GameClass();
	}
	
	GameClass(){
		
		mainFrame = new JFrame("2048 Game");
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setSize(500, 700);
		mainFrame.setBackground(Color.black);
		
		titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(0, 1));
		titlePanel.setBackground(Color.white);
		
		titleLabel = new JLabel(" Game: 2048 Grid ");
		titleLabel.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		
		titlePanel.add(titleLabel);
		
		gameDesc = new JLabel("  Join the numbers and get to the 2048 tile!");
		gameDesc2 = new JLabel(" HOW TO PLAY: Use your arrow keys to move the tiles.");
		gameDesc3 = new JLabel(" When two tiles with the same number touch, they merge into one!");
		gameDesc.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		gameDesc2.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		gameDesc3.setFont(new Font("Sylfaen", Font.ITALIC, 14));	
		
		titlePanel.add(gameDesc);
		titlePanel.add(gameDesc2);
		titlePanel.add(gameDesc3);
		
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		
		gamePanel = new GridGamePanel();
		
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 2));
		infoPanel.setBackground(Color.black);
		
		moveLabel = new JLabel("Score : ");
		moveLabel.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		moveLabel.setForeground(Color.white);
		infoPanel.add(moveLabel);
		
		moveCount = new JLabel("");
		moveCount.setFont(new Font("Sylfaen", Font.ITALIC, 14));
		moveCount.setForeground(Color.white);
		infoPanel.add(moveCount);
		
		restart = new JButton("Start/Restart");
		restart.addActionListener(this);
		
		
		infoPanel.add(restart);
		
		mainFrame.add(infoPanel, BorderLayout.SOUTH);
		
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
	    mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);	

	}
	
	public static void updateScoreLabel(int score){
		moveCount.setText(""+score);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		 JButton button = (JButton)arg0.getSource();
		 if(button == restart){
			 gamePanel.initialiseGameGrid();
			 restart.setFocusable(false);
			 gamePanel.setFocusable(true);
			 moveCount.setText(""+0);
		 }
	}

}
