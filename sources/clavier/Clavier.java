package clavier;
import java.io.*;

public class Clavier {

	final private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static int readInt() {
		int resultat = 0;
		try {
			resultat = Integer.parseInt(br.readLine()) ;
		} catch (IOException ioe ) {}
		return resultat;
	}

	public static long readLong() {
		long resultat = 0;
		try {
			resultat = Long.parseLong(br.readLine()) ;
		} catch (IOException ioe ) {}
		return resultat;
	}

	public static float readFloat() {
		float resultat = 0;
		try {
			resultat = Float.parseFloat(br.readLine()) ;
		} catch (IOException ioe ) {}
		return resultat;
	}

	public static double readDouble() {
		double resultat = 0;
		try {
			resultat = Double.parseDouble(br.readLine()) ;
		} catch (IOException ioe ) {}
		return resultat;
	}

	public static char readChar() {
		String resultat = "";
		try {
			resultat = br.readLine();
		} catch (IOException ioe ) {}
		if (resultat.length() > 1){
			return resultat.charAt(0);
		}
		return resultat.charAt(0);
	}

	public static String readString(String s) {
		System.out.print(s) ;
		return readString() ;
	}    

	public static String readString() {
		String resultat = "";
		try {
			resultat = br.readLine();
		} catch (IOException ioe ) {}
		return resultat;
	}
}
