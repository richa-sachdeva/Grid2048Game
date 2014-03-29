package com.GamePackage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GridRectangle extends FillableShape{

	private double length;
	private Color color2;
	private int num = 0;
	
	 
	public GridRectangle(double x, double y, Color color, double length, int num){
		super(x, y, color);
		this.length = length;
		this.color2 = color;
		this.setNum(num);
	}
 
	public double getLength(){
		return length;
	}
 
	public void paint(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
	//	g.setFont(new Font("Snap ITC", Font.ITALIC, 18));
		g.setFont(new Font("Sylfaen", Font.ITALIC, 30));
		BasicStroke s = new BasicStroke(8);
		
		((Graphics2D) g).setStroke(s);

		g.fillRoundRect((int)getX(), (int)getY(), (int)length, (int)length, 5, 5);
		g.setColor(Color.gray);
		g.drawRoundRect((int)getX(), (int)getY(), (int)length, (int)length, 5, 5);
		
		if(num != 0){
			g.setColor(Color.DARK_GRAY);
			g.drawString(""+num, (int)getX() + 35, (int)getY() + 55);
		}
		else{
			g.setColor(Color.DARK_GRAY);
			g.drawString("", (int)getX() + 55, (int)getY() + 55);
		}
	
	}
	
	public void setColor(Color c){
		this.color2 = c;
	}
	

	public Color getColor() { return this.color2; }

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
