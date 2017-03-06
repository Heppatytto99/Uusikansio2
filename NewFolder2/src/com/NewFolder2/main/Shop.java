package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
	
	Handler handler;
	HUD hud;
	private int B1 = 250;
	private int B2 = 220;
	private int B3 = 500;
	
	
	public Shop(Handler handler, HUD hud){
		this.handler = handler;	
		this.hud = hud;
		
	}
	
	

	
	public void render(Graphics g){
		
		g.setColor(Color.gray);
		g.fillRect(20, 110, 170, 300);
		g.setColor(Color.white);
		
		g.drawRect(30, 120, 30, 30);
		g.drawString("Upgrade Health", 80, 130);
		g.drawString("COST: " + B1 , 80, 145);
		
		g.drawRect(30, 180, 30, 30);
		g.drawString("Upgrade Speed", 80, 190);
		g.drawString("COST: " + B2, 80, 205);
		
		g.drawRect(30, 240, 30, 30);
		g.drawString("Heal 20", 80, 250);
		g.drawString("COST: " + B3, 80, 265);
		
		g.drawRect(30, 300, 30, 30);
		g.drawString("OUT OF STOCK", 80, 310);
		g.drawString("COST: n/a ", 80, 325);
		
		g.drawRect(30, 360, 30, 30);
		g.drawString("OUT OF STOCK", 80, 370);
		g.drawString("COST: n/a ", 80, 385);	
	}
	public void mousePressed(MouseEvent e){
		int my = e.getY();
		int mx = e.getX();
		AudioPlayer.getSound("menu_sound").play();
		
		//box1
		if(mx >= 30 && mx <= 60){
			if(my >= 120 && my <= 150){
				if(hud.getScore() >= B1){
					hud.setScore(hud.getScore() - B1);
					B1 += 250;
					hud.maxHealth += 20;
					
				}
			}
			if (my >= 180 && my <= 210){
				if(hud.getScore() >= B2){
					handler.speed++;
					hud.setScore(hud.getScore() - B2);
					B2 += 250;
				}
				
			}
			if (my >= 240 && my <= 270){
				if(hud.getScore() >= B3){
					hud.setScore(hud.getScore() - B3);
					B3 += 120;
					hud.HEALTH += 20;										
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e){
		
		
	}
}
