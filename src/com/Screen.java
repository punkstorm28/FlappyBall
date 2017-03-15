package com;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.System;

public class Screen {
	BufferedImage BlockUp;
	BufferedImage BlockDown;
	BufferedImage Ball;
	BufferedImage Back;
	static JFrame frameGame;
	static Boolean IsOver;
	int FrameHeight = 600;
	int FrameWidth = 900;
	Bars GameBars;
	Ball gameBall;
	int StrokeCounter = 1;
	static int gameScore;
	static int gameOverX;
	static int gameOverY;
	static int ScoreX;
	static int ScoreY;

	Screen() throws IOException {
		ScoreX = 1000;
		ScoreY = 260;
		gameScore = 0;
		gameOverX = 500;
		gameOverY = 800;
		IsOver = false;
		BlockUp = ImageIO.read(new File("Block1.png"));
		BlockDown = ImageIO
				.read(new File("Block2.png"));
		Ball = ImageIO.read(new File("DP.png"));
		Back = ImageIO.read(new File("Back.jpg"));
		frameGame = new JFrame("FlyieBird");
		GameBars = new Bars();
		frameGame.setSize(FrameWidth, FrameHeight);
		// frameGame.setBackground(new Color(123, 100, 23));

		frameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameGame.add(getPanel());
		frameGame.addKeyListener(new KeyInputManager());
		frameGame.setVisible(true);
		frameGame.setResizable(false);
		BarList list = new BarList();
		gameBall = new Ball();

		Thread barListUpdater = new Thread(list);

		barListUpdater.setPriority(10);
		barListUpdater.start();
		Timer.Starter();

	}

	void drawFrame() {
		frameGame.repaint();
	}

	JPanel getPanel() {
		JPanel panelGame = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				g.drawImage(Back, 0, 0, null);
				if (Screen.IsOver == false) {
					g.setColor(new Color(0, 255, 255));
					g.drawString("Score :" + gameScore, 800, 15);

					drawBar(g);
					repaint();
				}
				g.setColor(new Color(255, 0, 0));

				Font currentFont = g.getFont();
				Font newFont = currentFont
						.deriveFont(currentFont.getSize() * 5F);
				g.setFont(newFont);
				if (Screen.IsOver == true) {

					g.drawString("GameOver", gameOverX, gameOverY);
					g.setColor(new Color(0, 0, 255));
					g.drawString("Score:" + gameScore, ScoreX, ScoreY);

				}

			}
		};
		panelGame.setPreferredSize(new Dimension(1000, 500));
		panelGame.repaint();
		frameGame.repaint();

		return panelGame;
	}

	public void drawBar(Graphics g) {
		// g.drawImage(BlockUp, GameBars.barX, GameBars.barY-70, 130, 200+70,
		// null);
		for (int i = 0; i < BarList.BarList.size(); i++) { // System.out.println(+i+" Drawn");

			if (Screen.IsOver == false) {
				g.drawImage(Ball, gameBall.ballX, gameBall.ballY, 50, 50, null);
				g.drawImage(BlockUp, BarList.BarList.get(i).barX,
						BarList.BarList.get(i).getBarY() + 100, 120,
						BarList.BarList.get(i).getBarLen() - 10, null);
				g.drawImage(BlockDown, BarList.BarList.get(i).barX, 0, 120,
						BarList.BarList.get(i).getInvBarLen(), null);

				// g.drawImage(BlockUp,BarList.BarList.get(i).barX,BarList.BarList.get(i).getBarY()+100,130,00,null);
				// g.drawImage(BlockDown,BarList.BarList.get(i).barX,0,130,300,null);
				if (checkCollision(i) == true) {
					Screen.IsOver = true;

					// System.out.println("collision detected");
				}
				frameGame.repaint();

				if (BarList.BarList.get(i).barX < -140) {
					BarList.BarList.remove(i);
					Screen.gameScore++;
				}

			}

		}
	}

	public Boolean checkCollision(int argIndex) {
		if (gameBall.getBallRect().intersects(
				BarList.BarList.get(argIndex).getBarRect()) == true)
			return true;
		else if (gameBall.getBallRect().intersects(
				BarList.BarList.get(argIndex).getInvBarRect()) == true)
			return true;
		else
			return false;
	}

	public static void main(String... args) throws IOException,
			InterruptedException {
		Screen GameScreen = new Screen();
		Thread tisThread = Thread.currentThread();
		while (true) {
			frameGame.repaint();
			Thread.currentThread().setPriority(10);
			Thread.sleep(5);
		}

	}

	public class KeyInputManager implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
		
				int Keys = e.getKeyCode();
				if (Keys == KeyEvent.VK_SPACE) {
					// System.out.println("space");
					
						//com.Ball.setJump(true);
						Timer.resetTimer();

						
				}
				if (Keys == KeyEvent.VK_R) {
					BarList.BarList.clear();
					Screen.IsOver = false;
					Screen.gameScore = 0;
					gameBall.ballY = 500;
				}
			}
		

		@Override
		public void keyReleased(KeyEvent r) {

			int Keys = r.getKeyCode();
			if (Keys == KeyEvent.VK_SPACE) {
				//	System.out.println("inside");
				//	com.Ball.setJump(false);
			}

		}

		@Override
		public void keyTyped(KeyEvent p) {
		}

	}

}
