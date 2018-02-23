/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

/**
 *
 * @author Basham
 */
public class Matrix {
   public int data[][];
   int height;
   int width;

    public Matrix() {
    }

    public Matrix(int[][] data, int height, int width) {
        this.data = data;
        this.height = height;
        this.width = width;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
  
}
