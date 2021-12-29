package xingming;

import java.util.Objects;

public class Laptop {

	private String mid;
	private String brand;
	private String color;
	private String screen;
	private int num;
	
	
	public Laptop(String mid, String brand, String color, String screen, int num) {
		super();
		this.mid = mid;
		this.brand = brand;
		this.color = color;
		this.screen = screen;
		this.num = num;
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public int hashCode() {
		return Objects.hash(brand, color, mid, num, screen);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laptop other = (Laptop) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(color, other.color)
				&& Objects.equals(mid, other.mid) && num == other.num && Objects.equals(screen, other.screen);
	}
	@Override
	public String toString() {
		return "Laptop [mid=" + mid + ", brand=" + brand + ", color=" + color + ", screen=" + screen + ", num=" + num
				+ "]";
	}
	
	
}
