package game.snake;


public class AI {
	

	/** ˼�� */
	public void think(Snake snake, Block fruit, int rows, int cols) {
		int fruitRow = fruit.getRowIndex();
		int fruitCol = fruit.getColIndex();
		switch (snake.direction) {
		case '��':
		case '��':
			if(snake.row==fruitRow){
				// ��ֱ�ƶ�ʱ��ˮƽ������ˮ����ͬ��Ҫ����ת��
				if(snake.col>fruitCol){
					snake.turnLeft();
				}else{
					snake.turnRight();
				}
			}else if(snake.direction=='��' && snake.row<fruitRow){
				// �����ͷ�������ߣ�������ͷ��ˮ�����Ϸ�
				if(snake.col>fruitCol){
					snake.turnLeft();
				}else{
					snake.turnRight();
				}
			}else if(snake.direction=='��' && snake.row>fruitRow){
				// �����ͷ�������ߣ�������ͷ��ˮ�����·�
				if(snake.col>fruitCol){
					snake.turnLeft();
				}else{
					snake.turnRight();
				}
			}
			break;
		case '��':
		case '��':
			if(snake.col==fruitCol){
				// ˮƽ�ƶ�ʱ������ֱ������ˮ����ͬ��Ҫ����ת��
				if(snake.row>fruitRow){
					snake.turnUp();
				}else{
					snake.turnDown();
				}
			} else if(snake.direction=='��' && snake.col<fruitCol){
				// �����ͷ�������ߣ�������ͷ��ˮ�������
				if(snake.row>fruitRow){
					snake.turnUp();
				}else{
					snake.turnDown();
				}
			}else if(snake.direction=='��' && snake.col>fruitCol){
				// �����ͷ�������ߣ�������ͷ��ˮ�����ұ�
				if(snake.row>fruitRow){
					snake.turnUp();
				}else{
					snake.turnDown();
				}
			}
			break;			
		}
		// ��ת����󣬼�鼸��֮��᲻��ײ������
		checkInStep(step, snake, rows, cols);
	}

	public static int step = 20;
	
	/***
	 * step���󣬻᲻��ײ������
	 * @param step
	 */
	public void checkInStep(int step, Snake snake, int rows, int cols){
		int currBoomStep = snake.checkBoomStep(step, snake.direction);
		int nextBoomStep = snake.checkBoomStep(step, snake.nextDirection);
		if(currBoomStep==-1 && nextBoomStep>=1){
			System.out.println("currBoomStep:" + currBoomStep);
			System.out.println("nextBoomStep:" + nextBoomStep);
			System.out.println("-------------------------");
			snake.keepDierection();
		}
		
		
	}
	
	/**
	 * 
	 * @param snake
	 * @param offerRow ֻ��ȡֵ��0��+1��-1
	 * @param offerCol ֻ��ȡֵ��0��+1��-1
	 * @return
	 */
	public BB checkBoom(Snake snake, int offerRow, int offerCol){
		boolean boom = false;
		int count = 0;
		for (int k = 1; k < snake.body.size()/2; k++) {
			count++;
			if(snake.check(snake.row + offerRow*k, snake.col + offerCol*k)==true){
				boom = true;
				break;
			}
		}
		BB bb = new BB();
		bb.boom = boom;
		bb.count = count;
		return bb;
	}
}
