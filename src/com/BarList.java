package com;

import java.util.ArrayList;
import java.util.Random;

public class BarList implements Runnable {
	static ArrayList<Bars> BarList;
	static int[][] Lengths;
	static int TimeDelay;

	BarList() {
		BarList = new ArrayList<Bars>();
        arrayInit();
	}

	public void arrayInit() {
      Lengths = new int[5][2];
      Lengths[0][0]=250;
      Lengths[0][1]=250;
      Lengths[1][0]=400;
      Lengths[1][1]=100;
      Lengths[2][0]=100;
      Lengths[2][1]=400;
      Lengths[3][0]=300;
      Lengths[3][1]=200;
      Lengths[4][0]=150;
      Lengths[4][1]=350;
  	
	}

	@Override
	public void run() {
		Random RandTime = new Random();
		Random RandLength = new Random();
		while (true) {
			TimeDelay = RandTime.nextInt((2500 - 1700) + 1) + 1700;

			try {
				Thread.sleep((int) TimeDelay);
				Bars newBar = new Bars();
				// System.out.println("BarList is "+BarList);
				int RandomIndex = RandLength.nextInt((4-0)+1)+0;

				// System.out.println("Random length is "+RandomLength);
				newBar.setBarLen(Lengths[RandomIndex][0]);
				int RandomLengthI = 200;// RandLength.nextInt((270-150)+1)+150;
				// int RandomLengthI =100;
				newBar.setInvBarLen(Lengths[RandomIndex][1]);
				// newBar.setInvBarLen(RandomLength);
				newBar.setBarY(newBar.getBarY() - Lengths[RandomIndex][0] + 50);
				BarList.add(newBar);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
