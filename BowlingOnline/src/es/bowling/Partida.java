package es.bowling;

public class Partida {

	public static void main(String[] args) {
		System.out.println("Bowling online");

		Integer[][] bolos;
		//Variable para posible tirada bonus de la ultima tirada
		Integer tiradaBonus=0;
		// Definición del array principal y todos los arrays internos.
		bolos = new Integer[10][3];
		
		System.out.println("------------------------------");
		// Cargar arrays con números al azar entre 0 y 10.
		for (int frame = 0; frame < 10; frame++) {
			System.out.println("Ronda número: " + (frame+1));
			System.out.println("------------------------------");
			bolos[frame][0]=getScore(0,10);
			
			if(bolos[frame][0]==10) {
				System.out.println("¡Strike!");
				//Si es la última ronda se puede tirar hasta tres veces
				if(frame == 9) {
					System.out.println("Otra tirada...");
					bolos[frame][1]=getScore(0,10);
					
					int max;
					if(bolos[frame][1]==10) {
						System.out.println("¡Strike!");
						max=10;
					}else {
						max=10-bolos[frame][1];
						System.out.println("Bolos tirados: "+bolos[frame][1]);
					}
					
					System.out.println(" Otra tirada...");
					tiradaBonus=getScore(0,max);
					System.out.println("Bolos tirados: "+tiradaBonus);
				}
			}else {
				System.out.println("Bolos tirados: "+bolos[frame][0]+ "\n Otra tirada...");
				bolos[frame][1]=getScore(0,(10-bolos[frame][0]));
				System.out.println("Bolos tirados: "+bolos[frame][1]);
				
				//Si es la ultima ronda y suman 10 pude tirar otra
				if(frame == 9 && (bolos[frame][0]+bolos[frame][1])==10) {
					System.out.println(" Otra tirada...");
					tiradaBonus=getScore(0,10);
					System.out.println("Bolos tirados: "+tiradaBonus);
				}
			}
			System.out.println("------------------------------");
		}
		System.out.println("******************************");
		
		System.out.println("Resultado de la partida");
		System.out.println("------------------------------");
		
		Integer total=0;
		for (int i = 0; i < bolos.length; i++) {
			bolos[i][2]=bolos[i][0];
			
			//Si es la ultima ronda sumamos las tres tiradas, incluida la bonus
			if(i==(bolos.length-1)) {
				bolos[i][2]+=bolos[i][1]+tiradaBonus;
			}
			//Si la segunda tirada es null es porque es strike hay que acumular con posteriores
			else if(bolos[i][1]==null && i<(bolos.length-1)) {
				bolos[i][2]+=bolos[i+1][0];
				//Si la segunda tirada es null es porque es strike hay que acumular con posteriores
				if(bolos[i+1][1]==null && i<(bolos.length-2)) {
					bolos[i][2]+=bolos[i+2][0];
				}else {
					bolos[i][2]+=bolos[i+1][1];
				}
			}
			//Si entre los dos suman 10 hay que sumar lo de la siguiente
			else if((bolos[i][0]+bolos[i][1])==10) {
				bolos[i][2]+=bolos[i][1];
				//Prevenimos que no se salga de rango
				if(i<(bolos.length-1)) {
					bolos[i][2]+=bolos[i+1][0];
				}
			}
			//Si no se suman las dos tiradas
			else {
				bolos[i][2]+=bolos[i][1];
			}
			String cadenaResultado="Ronda "+(i+1)+": ";
			//Para alinear el resultado
			if (i<9) {
				cadenaResultado+=" ";
			}
			cadenaResultado+="["+bolos[i][0]+"-";
			//Si es la ultima ronda pintamos las 3 tiradas
			if(i==(bolos.length-1)) {
				cadenaResultado+=bolos[i][1]+"-"+tiradaBonus+"] ";
			}
			//Si es null pintamos una X
			else if(bolos[i][1]==null) {
				cadenaResultado+="X]  ";
			}else {
				cadenaResultado+=bolos[i][1]+"]\t  ";
			}
			cadenaResultado+="Total: "+bolos[i][2];
			System.out.println(cadenaResultado);
			total+=bolos[i][2];
		}
		System.out.println("------------------------------");
		System.out.println("Puntuacion total: "+total);
		System.out.println("******************************");
	}
	
	
	private static int getScore(int min, int max)
	{
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}
}
