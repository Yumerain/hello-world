package game.tetris.models;

import java.awt.Color;
import java.util.Random;

import game.tetris.Model;

public abstract class BaseModel implements Model {
	
	protected Color randomColor(){
		Random rd = new Random();
		int r = rd.nextInt(255);
		int g = rd.nextInt(255);
		int b = rd.nextInt(255);
		return new Color(r, g, b);
	}

}
