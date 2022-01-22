import java.awt.*;

/*
	Milleny Caroliny de Almeida Santos - NUSP 11937979
	
*/

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {

	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/
	private String playerId;
	private int score = 0; 

	
	public Score(String playerId){
		super();
		this.playerId = playerId;
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){

		if(this.playerId == Pong.PLAYER1){
			GameLib.setColor(Color.GREEN);
			GameLib.drawText(Pong.PLAYER1 + ": " + getScore(), 70, GameLib.ALIGN_LEFT);
		}
			
		if(this.playerId == Pong.PLAYER2){
			GameLib.setColor(Color.BLUE);
			GameLib.drawText(Pong.PLAYER2 + ": " + getScore(), 70, GameLib.ALIGN_RIGHT);
		}			
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		this.score++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){
		return this.score;
	}
}
