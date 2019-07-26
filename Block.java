public class Block {
	private String identificador;
	private int size;
	private Block nextBlock;
	int blockIndex;

	//Construtor sabendo identificador, size, nextblock e indice.
	//Vai ser utilizado nos splits e merges
	public Block(String identificador, int size,Block nextBlock,int blockIndex) {
		this.identificador = identificador;
		this.size = size;
		this.nextBlock=nextBlock;
		this.blockIndex=blockIndex;
	}
	//Construtor sabendo size e identificador
	public Block(String identificador, int size) {
		this.identificador = identificador;
		this.size = size;
		this.nextBlock=null;
	}
	//Construtor so sabendo o size
	public Block(int size) {
		this.identificador="f";
		this.size = size;
		this.nextBlock=null;
		this.blockIndex=0;
	}
	//Obter o identificador deste bloco
	public String getIdentificador() {
		return this.identificador;
	}
	//Atualizar o identificador deste bloco
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	//atualizar o size deste bloco
	public void setSize(int size) {
		this.size = size;
	}
	//obter size do bloco
	public int getSize() {
		return this.size;
	}
	//toString para ver este bloco
	public String toString() {
		return "" + this.getIndex();
	}
	//Obter o nextblock
	public Block getNextBlock() {
		return nextBlock;
	}
	//atualizar o proximo bloco deste bloco
	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}
	//metodo para verificar se o bloco esta livre
	public boolean isFree()
	{
		if (identificador.equals("f"))
			return true;
		else
			return false;
	}
	//Obter o indice deste bloco
	public int getIndex() {
		return blockIndex;
	}
	//Atualizar o indice deste bloco
	public void setIndex(int blockIndex) {
		this.blockIndex = blockIndex;
	}
}
