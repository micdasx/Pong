import java.awt.*;

/*
	Milleny Caroliny de Almeida Santos - NUSP 11937979
	
*/

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	//variáveis globais para acesso do objeto

	private double cx, cy, width, height, speed, direcaoX, direcaoY;

	private Color color;

	private String colisao;


	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		super();

		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.speed = speed;	
		this.color = color;

		this.direcaoX =  Math.random() <= 0.5 ? 1: -1;
		this.direcaoY =  Math.random() <= 0.5 ? 1: -1;

		this.colisao = "";
	}

	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(this.color);
		GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/


	public void update(long delta){

		this.cy += delta * this.direcaoY;
		this.cx += delta * this.direcaoX;
	}


	private void mudarDirecao(String id) {
		if(id.equals(Pong.LEFT)) {
			this.direcaoX = Math.abs(this.speed);
		}
		if(id.equals(Pong.RIGHT)) {
			this.direcaoX = -Math.abs(this.speed);
		}
		if(id.equals(Pong.TOP)) {
			this.direcaoY = Math.abs(this.speed);
		}
		if(id.equals(Pong.BOTTOM)) {
			this.direcaoY = -Math.abs(this.speed);
		}
	}


	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){

		if(this.colisao.isEmpty()){
			colisaoPlayer(playerId);
		} else {
			mudarDirecao(this.colisao);
			this.colisao = "";
		}
	}

	private void colisaoPlayer(String playerId) {
		if(playerId.equals(Pong.PLAYER1)) {
			this.direcaoX = Math.abs(this.speed);
		} else {
			this.direcaoX = -Math.abs(this.speed);
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		mudarDirecao(wallId);
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		String wallId = wall.getId();
		
		if(wallId.equals("Right")){
			return (this.cx + (this.width/2) >= wall.getCx() - (wall.getWidth()/2));
		}

		if(wallId.equals("Left")){
			return (this.cx - (this.width/2) <= wall.getCx() + (wall.getWidth()/2));
		}

		if(wallId.equals("Top")){
			return (this.cy - (this.height/2) <= wall.getCy() + (wall.getHeight()/2));
		}

		if(wallId.equals("Bottom")){
			return(this.cy + (this.height/2) >= wall.getCy() - (wall.getHeight()/2));
		}

		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		double posiWidth = this.width/2 + player.getWidth()/2;
		double playY = this.cy - player.getCy();
		double playX = this.cx - player.getCx();
		double playH = this.height/2 + player.getHeight()/2;

		if(Math.abs(playX) <= posiWidth && Math.abs(playY) <= playH) {

			if(posiWidth - Math.abs(playX) >= playH - Math.abs(playY)) {
				if(playY > 0) {
					this.colisao = Pong.TOP;
				} else {
					this.colisao = Pong.BOTTOM;
				}
			} else { 
				if(playX > 0) {
					this.colisao = Pong.LEFT;
				} else {
					this.colisao = Pong.RIGHT;
				}
			}
			return true;
		}
		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/
	
	public double getCy(){
		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){
		return this.speed;
	}

}
