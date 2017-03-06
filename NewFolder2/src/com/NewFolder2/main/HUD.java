package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Graphics;

import com.NewFolder2.main.Game.STATE;

public class HUD {
	
	public int bounds = 0;
	public static int HEALTH = 100;
	public int maxHealth = bounds + (HEALTH * 2);
	
	private int greenValue = 255;
	private int score = 0;
	private Game game;
	
	private int level = 0;
	
	
	public void tick(){
		
		HEALTH = Game.clamp( HEALTH, 0, 100 + (bounds / 2));
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = (HEALTH * 2);
		score += 2;
		
		
	}
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(15, 15, 300, 34);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 20, (HEALTH * 2), 22);
		g.setColor(Color.BLACK);
		g.drawRect(15, 20, maxHealth, 22);
		g.setColor(Color.green);
		
		
		g.drawString("Level " + level , 15, 65);
		g.drawString("Score " + score , 75, 65);
		g.drawString("'Space' to shop", 15, 100);
	}
	
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;	
	}
	

}
