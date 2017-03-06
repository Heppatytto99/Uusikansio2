package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject {
	
	private Handler handler;
	Random r = new Random();
	

	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 4;
		velY = 4;
		
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, 10, 10);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -2;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -2;
		if(velX > 12) velX -= ((r.nextInt(5)*2));
		//if(velX > 12) System.out.println(velX);
		if(velY > 12) velY -= ((r.nextInt(5)*2));
		
		handler.addObject(new BasicTrail(x, y, ID.BasicTrail, Color.YELLOW, 10, 10, 0.08f, handler));
				
	}

	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int) y, 10, 10);
		
		
	}

}
