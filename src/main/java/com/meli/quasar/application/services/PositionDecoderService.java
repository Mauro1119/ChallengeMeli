package com.meli.quasar.application.services;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.SingularMatrixException;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
/**

* Decodificador de la posición de la nave.

*/
public class PositionDecoderService {

	public static double[] GetLocation(double[][] positions, double... distances) {
		try {		
			TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions, distances);
			NonLinearLeastSquaresSolver nSolver = new NonLinearLeastSquaresSolver(trilaterationFunction, new LevenbergMarquardtOptimizer());
			return  nSolver.solve().getPoint().toArray();	
		} catch (SingularMatrixException e) {
			return new double[] {};
		} catch (Exception e) {
			return new double[] {};
		}        
	}
	
} 
