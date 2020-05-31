package planningoptimization115657k62.nguyenthinhung.assignment;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class Liquid_with_Choco {
	
	public static void main(String[] agrs) {
		int N = 20;
		int Q = 5;
		int[] c = {20, 15, 10, 20, 20, 25, 30, 15, 10, 10, 20, 25, 20 ,10, 30, 40, 25, 35, 10, 10};
		int[] V = {60, 70, 80, 90, 100};
		int[] oneN = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		int[] oneQ = {1,1,1,1,1};
		int[][] m = {{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } ,
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, }};
		Model model = new Model("Liquid");
		IntVar[][] x =  new IntVar[N][Q];
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < Q;j ++) {
				x[i][j] = model.intVar("x[" + i + "," + j + "]", 0,1);
			}
		}
		
		for(int i = 0; i < N;i++) {
			for(int j = 0;j < Q;j++) {
				model.scalar(x[i], oneQ, "=", 1).post();
			}
		}
		
		for(int i = 0;i < Q;i++) {
			IntVar[] y = new IntVar[N];
			for(int j = 0; j < N ; j++) y[j] = x[j][i];
			// model.scalar(y, oneN, "=", 1).post();
			model.scalar(y, c, "<=", V[i]).post();
		}
		
		for(int i = 0;i < N;i++) {
			for(int q = 0;q < Q;q++) {
				if(m[i][q] == 1) {
					for(int j = 0;j < Q;j++) {
						 model.arithm(x[i][j], "+", x[q][j], "<=", 1).post();
					}
				}
			}
		}
		
		model.getSolver().solve();
		
		for(int i = 0;i < N;i++) {
			for(int j = 0;j < Q;j++) {
				if(x[i][j].getValue() == 1) {
					System.out.print("[Chat long " + i + "- thung " + j + "] ");
					System.out.println();
				}
			}
		}
	}
}