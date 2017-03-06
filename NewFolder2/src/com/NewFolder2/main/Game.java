package com.NewFolder2.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	
	
	private static final long serialVersionUID = -874829860231360535L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running	= false;
	
	public static boolean paused = false;
	public int  diff = 0;
	//normal = 0, hard = 1
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	public enum STATE {
		Menu,
		Options,
		End,
		Select,
		Shop,
		Game
	};
	
	public static STATE gameState = STATE.Menu;
	
	public static BufferedImage sprite_sheet;
	
	
	
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		AudioPlayer.load();	
		
		new Window(WIDTH, HEIGHT, "Uusi kansio2", this);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		sprite_sheet = loader.loadImage("/kuva1.png");
		
		r = new Random();
		
		spawner = new Spawn(handler,hud);
		
		for(int i = 0; i < 20; i++){
			handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
		}	
	}


	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;				
	}
	
	public synchronized void stop(){
		
		try{
		thread.join();
		running = false;
		}
		
		catch(Exception e){
		e.printStackTrace();
		}
	}
				
	
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running) {
				render();
				}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			//System.out.println("FPS " + frames);
			frames = 0;
			}
		}
		stop();
	}
	
	private void tick(){
		
		
		
		if(gameState == STATE.Game){
			if(!paused)	{
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if(HUD.HEALTH == 0){
					handler.clearAll();
					
					for(int i = 0; i < 20; i++){
						handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
						}
					
					HUD.HEALTH = 100;
					//hud.setLevel(0);
					hud.setScore(0);
					gameState = STATE.End;
					
					}
				}
			}
		
		else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
			menu.tick();
			handler.tick();
		}
		
		
	
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);
		
		if(paused){
			g.setColor(Color.RED);
			g.drawString("PAUSED", 100, 100);
			}
		
		if(gameState == STATE.Game){
			hud.render(g);			
		}else if (gameState == STATE.Shop){
			shop.render(g);
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Options || gameState == STATE.End || gameState == STATE.Select){
			menu.render(g);	
		
		}
		
		
		g.dispose();	
		bs.show();
	}
		
	public static int clamp(int var, int min, int max){
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String args[]){
		
		new Game();
		
	}

}
	