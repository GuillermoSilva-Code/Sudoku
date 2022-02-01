public class SudokuAlgorithm {

	private static final int GRID_SIZE = 9;

	public static void main(String[] args) {

		int[][] tabla = {
				{7, 0, 2, 0, 5, 0, 6, 0, 0},
				{0, 0, 0, 0, 0, 3, 0, 0, 0},
				{1, 0, 0, 0, 0, 9, 5, 0, 0},
				
				{8, 0, 0, 0, 0, 0, 0, 9, 0},
				{0, 4, 3, 0, 0, 0, 7, 5, 0},
				{0, 9, 0, 0, 0, 0, 0, 0, 8},
				
				{0, 0, 9, 7, 0, 0, 0, 0, 5},
				{0, 0, 0, 2, 0, 0, 0, 0, 0},
				{0, 0, 7, 0, 4, 0, 2, 0, 3} 
		};

		if(ResolverTabla(tabla)) {
			System.out.println("Completado correctamente.\n");
		}
		else {
			System.out.println("Error. Tablero irresoluble");
		}
		
		imprimirTabla(tabla);

	}

	private static void imprimirTabla(int[][] tabla) {
		for(int fila = 0; fila < GRID_SIZE; fila++) {
			if(fila % 3 == 0 && fila != 0) {
				System.out.println("-----------");
			}
			for(int columna = 0; columna < GRID_SIZE; columna++) {
				if(columna % 3 == 0 && columna != 0) {
					System.out.print("|");
				}
				System.out.print(tabla[fila][columna]);
			}
			System.out.println();
		}

	}

	private static boolean NumeroEnFila(int[][] tabla, int numero, int fila) {
		for(int i = 0; i < GRID_SIZE; i++) {
			if(tabla[fila][i]==numero) {
				return true;
			}
		}
		return false;
	}

	private static boolean NumeroEnColumna(int[][] tabla, int numero, int columna) {
		for(int i = 0; i < GRID_SIZE; i++) {
			if(tabla[i][columna]==numero) {
				return true;
			}
		}
		return false;
	}

	private static boolean NumeroEnCaja(int[][] tabla, int numero, int fila, int columna) {
		int localBoxFila = fila - fila % 3;
		int localBoxColumna = columna - columna % 3;
		for(int i = localBoxFila; i < localBoxFila + 3; i++) {
			for (int j = localBoxColumna; j < localBoxColumna + 3; j++) {
				if(tabla[i][j] == numero) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean EsValido(int [][] tabla, int numero, int fila, int columna) {
		return !NumeroEnFila(tabla, numero, fila) &&
				!NumeroEnColumna(tabla, numero, columna) &&
				!NumeroEnCaja(tabla, numero, fila, columna);
	}

	private static boolean ResolverTabla (int [][] tabla) {
		for(int fila = 0; fila < GRID_SIZE; fila++) {
			for(int columna = 0; columna < GRID_SIZE; columna++) {
				if(tabla[fila][columna] == 0) {
					for(int numeroPrueba = 1; numeroPrueba <= GRID_SIZE; numeroPrueba++) {
						if(EsValido(tabla, numeroPrueba, fila, columna)) {
							tabla[fila][columna] = numeroPrueba;

							if(ResolverTabla(tabla)) {
								return true;
							}else {
								tabla[fila][columna] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

}