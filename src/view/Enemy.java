package view;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	private boolean up, down;
	private double x, y;
	private int w, h, speed, pontos;
	private Game game;

	public Enemy(Game game) {
		this.game = game;
		this.up = false;
		this.down = false;
		this.w = 5;
		this.h = 30;
		this.x = this.game.getWIDTH() - 20; 
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
		y += Integer.signum((int)(game.getBall().getY() - y)) * speed;
		
		
		if(y < 0) {
			y = 0;
		}
		
		if(y + h > game.getHEIGHT()) {
			y = game.getHEIGHT() - h;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, w, h);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
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
