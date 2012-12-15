package ca.viaware.rpg.entity;

import java.awt.Rectangle;

import org.lwjgl.input.Mouse;

import ca.viaware.rpg.game.Globals;

public abstract class AbstractEntity implements Entity {

	protected double x, y, width, height;
	protected Rectangle hitbox = new Rectangle();

	public AbstractEntity(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;

	}

	@Override
	public void setX(double x) {
		this.x = x;

	}

	@Override
	public void setY(double y) {
		this.y = y;

	}

	@Override
	public void setWidth(double width) {
		this.width = width;

	}

	@Override
	public void setHeight(double height) {
		this.height = height;

	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public boolean intersects(Entity other) {
		hitbox.setBounds((int) x, (int) y, (int) width, (int) height);
		return hitbox.intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
	}
	@Override
	public boolean intersectsMouse() {
		hitbox.setBounds((int) x, (int) y, (int) width, (int) height);
		
		System.out.println("MOuse x"+Mouse.getX()+"X"+x);
		return hitbox.intersects(Mouse.getX(), Mouse.getY(),Globals.cursor.getWidth(),Globals.cursor.getHeight() );
	}
	/*
	 * public boolean intersects(double x2,double y2,Entity other) {
	 * hitbox.setBounds((int) x, (int) y, (int) width, (int) height); return
	 * hitbox.intersects(x2,y2, other.getWidth(), other.getHeight()); }
	 */

}
