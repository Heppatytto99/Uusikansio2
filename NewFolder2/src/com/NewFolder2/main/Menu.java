package com.NewFolder2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.NewFolder2.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu (Game game, Handler handler,HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int my = e.getY();
		int mx = e.getX();
		AudioPlayer.getSound("menu_sound").play();
		
		//MENUbuttons
		if(game.gameState == STATE.Menu){
					
			if(mouseOver(mx, my, (Game.WIDTH / 2) - 100, 100, 200, 64)){
				//handler.clearEnemies();
				//game.gameState = STATE.Game;
				//handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				game.gameState = STATE.Select;
			}
			
			else if(mouseOver(mx, my, (Game.WIDTH / 2) - 100, 200, 200, 64)){
				game.gameState = STATE.Options;
			}
			
			else if((mouseOver(mx, my,(Game.WIDTH / 2) - 100, 300, 200, 64))){
				System.exit(1);
			}
				
			
		}
		
		else if(game.gameState == STATE.Select){
			//normal
			if(mouseOver(mx ,my ,(Game.WIDTH / 3) + 5 , 135, 180, 50)){
				hud.setLevel(0);
				handler.clearEnemies();
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(80, 80, ID.BasicEnemy, handler));
				game.diff = 0;
	
			}
			//hard
			else if(mouseOver(mx, my ,(Game.WIDTH / 3) + 5 , 235, 180, 50)){
				hud.setLevel(0);
				handler.clearEnemies();
				game.gameState = STATE.Game;
				
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new HardEnemy(50, 50, ID.HardEnemy, handler));
				handler.addObject(new HardEnemy(300, 50, ID.HardEnemy, handler));
				game.diff = 1;
			}
			else if(mouseOver(mx, my ,(Game.WIDTH / 6) - 100 , 390, 125, 40)){
				game.gameState = STATE.Menu;
			}
		}
		
		else if(game.gameState == STATE.Options){
			if(mouseOver(mx, my,(Game.WIDTH / 6) - 100 , 402, 64, 28 )){
				game.gameState = STATE.Menu;
			}			
		}
		else if(game.gameState == STATE.End){
			if(mouseOver(mx,my,20, 400, 58, 20)){
				game.gameState = STATE.Menu;
			}
		}
		
		//playButton
	/*if(game.gameState == STATE.Menu){
			if(mouseOver(mx, my, (Game.WIDTH / 2) - 100, 100, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			}
		}
		//optionsButton
		if(game.gameState == STATE.Menu){
			if(mouseOver(mx, my, (Game.WIDTH / 2) - 100, 200, 200, 64)){
				game.gameState = STATE.Options;
			}
		}
		//quitButton
		if(game.gameState == STATE.Menu){
			if(mouseOver(mx, my,(Game.WIDTH / 2) - 100, 300, 200, 64)){
				System.exit(1);
			}
		}*/
		
		
	}
	
	public void mouseReleased(MouseEvent e){
		
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;		
		}		
	}
	 
	
	public void tick(){
		}

	public void render(Graphics g){
		Font fnt  = new Font("arial", 0, 50);
		Font fnt2  = new Font("arial", 0, 40);
		Font fnt3 = new Font("arial", 0, 20);
		
		
		if(game.gameState == STATE.Menu){
		
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("MENU", (Game.WIDTH / 2) - 75, 50);
			
			g.setFont(fnt2);
			
			g.drawString("PLAY", (Game.WIDTH / 2) - 75, 150);
			g.drawRect((Game.WIDTH / 2) - 100 , 100, 200, 64);
			
			g.drawString("OPTIONS", (Game.WIDTH / 2) - 75, 250);
			g.drawRect((Game.WIDTH / 2) - 100 , 200, 200, 64);
			
			g.drawString("EXIT", (Game.WIDTH / 2) - 75, 350);
			g.drawRect((Game.WIDTH / 2) - 100 , 300, 200, 64);
			
		}
		else if(game.gameState == STATE.Options){
			g.setColor(Color.white);
			g.setFont(fnt3);
			g.drawString("W,A,S,D to move. SPACE to enter the shop", 120, Game.WIDTH / 2);
			g.drawString("MENU", (Game.WIDTH / 6) - 95, 425);
			g.drawRect((Game.WIDTH / 6) - 100 , 402, 64, 28);
		
		}
		else if(game.gameState == STATE.Select){
			g.setColor(Color.white);
			g.setFont(fnt2);
			
			g.drawString("SELECT DIFFICULTY", (Game.WIDTH / 5), 50);			
			
			g.drawString("NORMAL", (Game.WIDTH / 3 + 10), 175);
			g.drawRect((Game.WIDTH / 3) + 5 , 135, 180, 50);
			
			g.drawString("HARD", (Game.WIDTH / 3) + 40, 275);
			g.drawRect((Game.WIDTH / 3) + 5 , 235, 180, 50);
			
			g.drawString("MENU", (Game.WIDTH / 6) - 95, 425);
			g.drawRect((Game.WIDTH / 6) - 100 , 390, 125, 40);
		
		}
		else if(game.gameState == STATE.End){
			g.setColor(Color.WHITE);
			g.setFont(fnt3);
			
			g.drawString("Your Level: " + hud.getLevel(), Game.WIDTH / 4 + 50, 150);
			g.drawRect(20, 400, 58, 20);
			g.drawString("MENU", 20, 418);
			
			g.setFont(fnt);
			
			g.drawString("YOU LOST!", Game.WIDTH / 3 - 50, 100);
			
			
		}
		
	}
}
