package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemyBullet extends GameObject {
	
	private Handler handler;
	private GameObject player;
	private int timer = 320;
	

	public SmartEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
		
		
		
		
	}
	public Rectangle getBounds(){
		return new Rectangle((int) x,(int) y, 16, 16);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		timer--;
		
		float diffX = (float) x - player.getX()	- 8;
		float diffY = (float) y - player.getY()	- 8;
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));
			
		velX =(float) ((-1 / distance) * diffX);
        velY =(float) ((-1 / distance) * diffY);
		
					
		
		//if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        
		if(y >= Game.HEIGHT) handler.removeObject(this);
		if(x >= Game.WIDTH) handler.removeObject(this);
		if(timer == 0) handler.removeObject(this);
		
		
		/*for(int i = 220; i > 0; i--){
			if(i == 0){
				handler.removeObject(this);
			}
			else{
				i--;
			}
		}*/
		
		
		handler.addObject(new BasicTrail( x, y, ID.BasicTrail, Color.green, 8, 16, 0.02f,handler));
		
		
				
	}

	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x,(int) y, 8, 16);
		
		
	}

}
