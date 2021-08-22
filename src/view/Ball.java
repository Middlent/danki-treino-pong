package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	private double x, y, dx, dy, speed;
	private int size;
	private Game game;

	public Ball(Game game) {
		this.game = game;
		this.size = 3;
		this.speed = 2.5;

		this.x = (int) (this.game.getWIDTH()-size)/2; 
		this.y = (int) (this.game.getHEIGHT()-size)/2;
		int ang = new Random().nextInt(356);
		this.dx = Math.cos(ang);
		this.dy = Math.sin(ang);
	}

	public void tick() {
		x += dx*speed; 
		y += dy*speed; 
		
		if(y < 0) {
			y = 0;
			dy = -dy;
			dx += Integer.signum((int)dx)*0.1;
		}
		
		if(y + size > this.game.getHEIGHT()) {
			y = this.game.getHEIGHT() - size;
			dy = -dy;
			dx += Integer.signum((int)dx)*0.1;
		}
		
		if(x < 0) {
			this.game.getEnemy().setPontos(this.game.getEnemy().getPontos()+1);
			this.x = (int) (this.game.getWIDTH()-size)/2; 
			this.y = (int) (this.game.getHEIGHT()-size)/2;
			int ang = new Random().nextInt(356);
			this.dx = Math.cos(ang);
			this.dy = Math.sin(ang);
		}
		
		if(x + size > this.game.getWIDTH()) {
			this.game.getPlayer().setPontos(this.game.getPlayer().getPontos()+1);
			this.x = (int) (this.game.getWIDTH()-size)/2; 
			this.y = (int) (this.game.getHEIGHT()-size)/2;
			int ang = new Random().nextInt(356);
			this.dx = Math.cos(ang);
			this.dy = Math.sin(ang);
		}
		
		Rectangle bounds = new Rectangle((int)(x+dx*speed),(int)(y+dy*speed),size,size);
		
		Rectangle boundsPlayer = new Rectangle((int)game.getPlayer().getX(),(int)game.getPlayer().getY(),(int)game.getPlayer().getW(),(int)game.getPlayer().getH());
		Rectangle boundsEnemy = new Rectangle((int)game.getEnemy().getX(),(int)game.getEnemy().getY(),(int)game.getEnemy().getW(),(int)game.getEnemy().getH());
	
		if(bounds.intersects(boundsPlayer)) {
			if(game.getPlayer().isUp()) {
				this.dy -= 0.5;
			}else if(game.getPlayer().isDown()) {
				this.dy += 0.5;
			}
			dx = -dx;
		}else if(bounds.intersects(boundsEnemy)) {
			if(game.getEnemy().isUp()) {
				this.dy -= 0.5;
			}else if(game.getEnemy().isDown()) {
				this.dy += 0.5;
			}
			dx = -dx;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, size, size);
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

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


}
