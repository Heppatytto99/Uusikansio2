package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBullet extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	
	

	public EnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX =(float) r.nextInt(5 - -5)+ -5;
		velY =(float) r.nextInt((10 - -10) + -10);
		
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, 3, 3);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		if(x >= Game.WIDTH) handler.removeObject(this);
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.RED, 3, 3, 0.01f, handler));
				
	}

	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 3, 5);
		
		
	}

}
