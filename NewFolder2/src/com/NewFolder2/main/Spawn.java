package com.NewFolder2.main;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	private int levelScore = 500;
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
		
		
	}
	
	public void tick(){
		scoreKeep++;
		
		if(scoreKeep >= 300){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if(hud.getLevel() == 0){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel()== 1){
				hud.setScore(hud.getScore() + levelScore);
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel()== 2){
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT - 30), ID.BasicEnemy, handler));
			}
			else if(hud.getLevel() == 3){
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT -30), ID.FastEnemy, handler));
			}
			else if(hud.getLevel() == 4){
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 30), r.nextInt(Game.HEIGHT -30), ID.SmartEnemy, handler));
			}
			else if(hud.getLevel() == 5){
				handler.clearEnemies();
				handler.addObject(new BossOne((Game.WIDTH / 2) - 48, -96, ID.BossOne, handler));
		}
		
			
	}

	}
}
