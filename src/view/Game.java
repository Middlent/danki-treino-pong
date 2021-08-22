package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable,KeyListener{
	
	private static final long serialVersionUID = 1L;
	private Thread thread;
	private boolean isRunning;
	private BufferedImage image;
	public static JFrame frame;
	private final int WIDTH = 288;
	private final int HEIGHT = 162;
	private final int SCALE = 4;
	
	private Player player;
	private Enemy enemy;
	private Ball ball;
	
	
	public Game() {
		player = new Player(this);
		enemy = new Enemy(this);
		ball = new Ball(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	private void initFrame() {
		frame = new JFrame("Pong");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
			}
		}
		stop();
	}
	
	public void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public void stop() {
		
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
		
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.setFont(Font.getFont(Font.SANS_SERIF));
		int w = g.getFontMetrics().stringWidth(player.getPontos()+" x "+enemy.getPontos());
		g.drawString(player.getPontos()+" x "+enemy.getPontos(), (WIDTH-w)/2, 10);
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.WHITE);
		g.setFont(Font.getFont(Font.SANS_SERIF));
		int w = g.getFontMetrics().stringWidth(player.getPontos()+" x "+enemy.getPontos());
		g.drawString(player.getPontos()+" x "+enemy.getPontos(), (WIDTH-w)/2, 10);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.setUp(true);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.setDown(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.setUp(false);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			player.setDown(false);
		}
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		Game.frame = frame;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public int getSCALE() {
		return SCALE;
	}
	
	
}
