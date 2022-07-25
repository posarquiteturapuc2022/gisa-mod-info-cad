
package br.com.posarquiteturapuc2022.utils;

public class Testes {

	static Integer a = 10;
	static Integer b = 10;
	static int c = 10;
	static float d = 10.0f;
	static Integer e = 10;

	public static void main(String[] args) {
		exception();
	}


	public static void exception() {
		try {
			for (int cont = 0; cont < 10; cont++) {
				a += cont * 10;
				b += cont * 10;
			}
			if (a == b) {
				throw new Exception("E ");
			} else {
				throw new Exception("NE ");
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			if (c == e) {
				System.out.print("E ");
			}
			if (d == e) {
				System.out.print("E ");
			} else {
				System.out.print("NE ");
			}
			System.out.print("Exec ");
		}
		System.out.println("End");
	}

}
