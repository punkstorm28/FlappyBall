package com;

import java.awt.Rectangle;

public class Bars {
	int barX;
	int barY;
	int barLen;
	int InvBarLen;

	Bars() {
		barX = 1000;
		barY = 470;

		Thread BallThread = new Thread(new BarX());
		BallThread.start();

	}

	public void setBarY(int Y) {
		barY = Y;
	}

	public int getBarY() {
		return barY;
	}

	public void setBarLen(int barLength) {
		barLen = barLength;
	}

	public void setInvBarLen(int InvLen) {
		InvBarLen = InvLen;
	}

	public int getInvBarLen() {
		return InvBarLen;
	}

	public int getBarLen() {
		return barLen;
	}
	public Rectangle getBarRect()
	{
		return new Rectangle(barX,getBarY()+100,130,getBarLen());
	}
   public Rectangle getInvBarRect()
   {
	   return new Rectangle(barX,0,120,getInvBarLen());
   }
	private class BarX implements Runnable {
		public void run() {

			while (true) {

				barX--;
				try {
					Thread.sleep(5);
					Thread.currentThread().setPriority(10);
					// System.out.println(barX);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
