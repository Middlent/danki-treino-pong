package view;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	private boolean up,down;
	private int x, y, w, h, speed, pontos;
	private Game game;

	public Player(Game game) {
		this.game = game;
		this.up = false;
		this.down = false;
		this.w = 5;
		this.h = 30;
		this.x = 20; 
		this.y =(int) (this.game.getHEIGHT()-h)/2;
		this.speed = 2;
		this.pontos = 0;
	}
	
	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void tick() {
		if(up) {
			y-=speed;
		}

		if(down) {
			y+=speed;
		}
		
		if(y < 0) {
			y = 0;
		}
		
		if(y + h > game.getHEIGHT()) {
			y = game.getHEIGHT() - h;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, w, h);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
}
