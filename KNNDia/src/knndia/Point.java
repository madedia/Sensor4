/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package knndia;

/**
 *
 * @author MD Dia Agustya
 */
public class Point {
    private double[] data;
    private int dataClass;
    private double euclidian;

    public Point(double[] dataLine) {
        data = new double[dataLine.length];
        System.arraycopy(dataLine, 0, this.data, 0, dataLine.length);        
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public int getDataClass() {
        return dataClass;
    }

    public void setDataClass(int dataClass) {
        this.dataClass = dataClass;
    }

    public double getEuclidian() {
        return euclidian;
    }

    public void setEuclidian(double euclidian) {
        this.euclidian = euclidian;
    }
}
