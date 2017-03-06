package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossOne extends GameObject {
	
	private Handler handler;
	private int timer = 220;
	Random r = new Random();
	
	

	public BossOne(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 2;	
		
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, 148, 48);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		
		if(y == 90){
			timer--;
			velY = 0;
			if(timer <= 0){
				timer = 220;
				velX += 10;
				
			}
			else if(velX == 0) {
				int spawn = r.nextInt(8);
				if(spawn == 0){
					handler.addObject(new EnemyBullet((int)x + 74 ,(int)y + 24, ID.EnemyBullet, handler));
				}			
			}
			else if(velX != 0) {
				int spawn = r.nextInt(20);
				if(spawn == 0){
					handler.addObject(new SmartEnemyBullet((int)x + 74 ,(int)y + 24, ID.SmartEnemyBullet, handler));
				}			
			}
			
			else {
				timer--;
			}
			
		}
		
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.RED, 148, 48, 0.1f, handler));
				
	}

	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
		
		
	}

}
