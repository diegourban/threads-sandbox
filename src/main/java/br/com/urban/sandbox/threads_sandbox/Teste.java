package br.com.urban.sandbox.threads_sandbox;

import java.util.Random;

public class Teste {
	
	public static void main(String[] args) throws InterruptedException {
		boolean[] array = new boolean[10000000];
		
		Random random = new Random();
		
		int trueCount = 0;
		int falseCount = 0;
		for(int i = 0; i < array.length; i++) {
			boolean nextBoolean = random.nextBoolean();
			if(nextBoolean) {
				trueCount++;
			} else {
				falseCount++;
			}
			array[i] = nextBoolean;
		}
		System.out.println("True: " + trueCount);
		System.out.println("False: " + falseCount);
		
		
		int totalTrue = 0;
		int totalFalse = 0;
		
		
//		long inicio = System.currentTimeMillis();
//		for(int i = 0; i < array.length; i++) {
//			if(array[i] == true) {
//				totalTrue++;
//			} else {
//				totalFalse++;
//			}
//		}
//		long fim = System.currentTimeMillis();
//		System.out.println("Demorei: " + (fim-inicio));
		
		long inicio = System.currentTimeMillis();
		Teste t = new Teste();
		Conta conta1 = t.new Conta(array, 0, array.length/4);
		Conta conta2 = t.new Conta(array, array.length/4, array.length/2);
		Conta conta3 = t.new Conta(array, array.length/2, (array.length*3)/4);
		Conta conta4 = t.new Conta(array, (array.length*3)/4, array.length);
		
		Thread thread1 = new Thread(conta1);
		Thread thread2 = new Thread(conta2);
		Thread thread3 = new Thread(conta3);
		Thread thread4 = new Thread(conta4);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		
		totalTrue = conta1.getQtdTrue() + conta2.getQtdTrue() + conta3.getQtdTrue() + conta4.getQtdTrue();
		totalFalse = conta1.getQtdFalse() + conta2.getQtdFalse() + conta3.getQtdFalse() + conta4.getQtdFalse();
		
		long fim = System.currentTimeMillis();
		System.out.println("Demorei: " + (fim-inicio));
		
		System.out.println("Contei True: " + totalTrue);
		System.out.println("Contei False: " + totalFalse);
		
	}

	class Conta implements Runnable {
		
		private boolean[] array;
		private int qtdTrue;
		private int qtdFalse;
		private int from;
		private int to;

		public Conta(boolean[] array, int from, int to) {
			this.array = array;
			this.from = from;
			this.to = to;
			qtdTrue = 0;
			qtdFalse = 0;
		}

		@Override
		public void run() {
			for(int i = from; i < to; i++) {
				if(array[i]) {
					qtdTrue++;
				} else {
					qtdFalse++;
				}
			}
		}
		
		public int getQtdTrue() {
			return qtdTrue;
		}
		
		public int getQtdFalse() {
			return qtdFalse;
		}
		
	}
}
