package com;

import java.awt.Rectangle;

public class Ball {
	static int ballX;
	static int ballY;
	static Boolean living;
	static int BallJumpspeed;
	private static Boolean jump;
	static int jumpStepCounter = 80;
    static int  time;
    
	public static Boolean getJump() {
		return jump;
	}

	public static void setJump(Boolean jump) {
		Ball.jump = jump;
	}

	Ball() {
		BallJumpspeed = -60;
		setJump(false);
		living = true;
		ballX = 50;
		ballY = 500;

		Thread BallThread = new Thread(new BallX());
		BallThread.start();
	}

	int getBallY() {
		
			return ballY;
	}
	int getBallYWithTime(int time)
	{
		return getBallY()+BallJumpspeed*time - 5*time*time;
	}

	void setBallY(int argY) {
		Ball.ballY = argY;
	}

	public Rectangle getBallRect() {
		return new Rectangle(ballX + 5, ballY, 40, 40);
	}

	/*
	 * I have to implement S=u^2/2g, u is given when u is BallJupSpeed, T is
	 * Thread counter
	 */
	private class BallX implements Runnable {
		public void run() {
			if (Screen.IsOver == false) {
				while (Screen.IsOver == false) {

					if (getJump() == true) {
						
						
						setBallY(getBallY()-5);
						try {
							Thread.sleep(8);
							// System.out.println(ballY);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (ballY < 10) {
							ballY = 10;
							try {
								Thread.sleep(8);
								// System.out.println(ballY);

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						// System.out.println("is "+ballY);
					} else {
						time = 0;
						ballY++;
						if (ballY > 500)
							ballY = 500;
						// System.out.println("is false");
					}
					try {
						Thread.sleep(8);
						// System.out.println(ballY);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if (Screen.IsOver == true) {
				while (Screen.IsOver == true) {
					ballY++;
					Screen.gameOverY--;
					Screen.ScoreX--;
					if (Screen.gameOverY < 200)
						Screen.gameOverY = 200;
					if (Screen.ScoreX < 400)
						Screen.ScoreX = 400;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
