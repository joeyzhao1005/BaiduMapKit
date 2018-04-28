package com.kit.baidumap;

public class PointKit {

	private double point_x;
	private double point_y;

	public PointKit() {

	}

	public PointKit(double x, double y) {
		this.point_x = x;
		this.point_y = y;
	}

	public double getX() {
		return point_x;
	}

	public void setX(double x) {
		this.point_x = x;
	}

	public double getY() {
		return point_y;
	}

	public void setY(double y) {
		this.point_y = y;
	}

	@Override
	public String toString() {
		return "point_x=" + point_x + ", point_y=" + point_y;
	}

}
