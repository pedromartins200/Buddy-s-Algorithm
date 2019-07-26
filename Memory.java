import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Memory {
	private Block head;
	MemoryArrayList freeBlocks=new MemoryArrayList();
	MemoryArrayList occupiedBlocks=new MemoryArrayList();

	//Construir uma nova memoria, e adicionar o primeiro bloco a lista dos freeblocks
	public Memory(int size) {
		head=new Block(size);
		freeBlocks.add(head);
	}
	//imprimir todos os blocos existentes na memoria
	public void printBlocks() {
		Block block=head;
		//enquanto houver mais blocos...
		while(block!=null){
			System.out.println(block);
			//ir para o proximo bloco
			block=block.getNextBlock();
		}
	}
		// usado so para fins de detectar bugs
		// descobrir o maior bloco existente na memoria que esteja livre
		public int getlargestblockspace() {
		Block block = head;
		int space = 0;
		while (block != null) {
			if (block.isFree()) {
				if (space < block.getSize()) {
					space = block.getSize();
				}
			}
			block = block.getNextBlock();
		}
		return space;
	}

	// usado so para fins de detectar bugs
	// descobrir o espaco total de memoria livre existente
	public int freeSpace() {
		Block block = head;
		int space = 0;
		while (block != null) {
			if (block.isFree()) {
				space += block.getSize();
			}
			block = block.getNextBlock();
		}
		return space;

	}

	//imprimir todos os blocos livres
	public void printFreeBlocks() {
		freeBlocks.sortBySize();
		for(int i=0;i<freeBlocks.getSize();i++){
			System.out.println(freeBlocks.get(i));
		}
	}
	//imprimir todos os blocos ocupados
	public void printOccupiedBlocks() {
		for(int i=0;i<occupiedBlocks.getSize();i++){
			System.out.println(occupiedBlocks.get(i));
		}
	}

	//descobrir um bloco sabendo o identificador
	public Block findblock(String identificador) {
		Block block=head;
		//enquanto houver mais blocos
		while(block!=null){
			//se o identificador for igual ao identificador deste bloco
			if(block.getIdentificador().equals(identificador))
				return block;
			//ir para o proximo bloco
			block=block.getNextBlock();
		}
		return null;
	}
	//colocar um bloco na memoria sabendo identificador e size
	public void put(String identificador, int size) {
		//caso de tentar colocar um bloco com tamanho superior ao maior bloco livre
		//deve ser impossivel inserilo
		if (getlargestblockspace() < size) {
			return;
		}
		Block block=findblock(identificador);
		//se o bloco existe e tem size 0
		//significa pedido de libertacao de memoria
		if(block!=null && size==0)
		{
			//libertar este bloco
			block.setIdentificador("f");
			//remover este bloco dos ocupados
			occupiedBlocks.remove(block);
			//adicionar aos free blocks
			freeBlocks.add(block);
			//associar blocks
			associateblocks();
		}
		else
		{
			//encontrar o proximo bloco disponivel
			Block nextAvailableBlock=findAvailableBlock(size);
			//verificar se o size deste bloco e otimo
			if(!isOptimalSize(nextAvailableBlock,size))
				nextAvailableBlock=splitBlock(nextAvailableBlock,size);
			//fazer set ao identificador deste bloco
			nextAvailableBlock.setIdentificador(identificador);
			//remover o bloco da lista dos free blocks
			freeBlocks.remove(nextAvailableBlock);
			//adicionar este bloco aos blocos ocupados
			occupiedBlocks.add(nextAvailableBlock);
		}
	}
	//encontrar o primeiro bloco que cabe aqui
	public Block findAvailableBlock(int size) {
		Block block=head;
		//enquanto houver mais blocos
		while(block!=null){
			//se o bloco estiver livre, e houver espaco suficiente
			if( block.getIdentificador().equals("f") && block.getSize()>=size )
				return block;
			//proximo bloco
			block=block.getNextBlock();
		}
		return null;
	}
	//fazer split aos blocos
	public Block splitBlock(Block block,int size) {
		//enquanto o tamanho nao for otimo
		while (!isOptimalSize(block,size))
		{
			//criar novo bloco com metade do tamanho e 2k+2
			Block block2=new Block("f",block.getSize()/2,block.getNextBlock(),block.getIndex()*2+2);
			block.setNextBlock(block2);
			//diminuir para metade o tamanho do bloco atual
			block.setSize(block.getSize()/2);
			//indice do bloco atual 2k+1
			block.setIndex(block.blockIndex*2+1);
			//adicionar este bloco aos blocos livres
			freeBlocks.add(block2);
		}
		return block;
	}
	//metodo para verificar se este bloco tem o tamanho otimo
	public boolean isOptimalSize(Block block,int size)
	{
		if(block.getSize()>=2*size)
			return false;
		else
			return true;
	}
	
	//metodo para fazer merge a potenciais blocos
	public void associateblocks(){
		Block block=head;
		//enquanto houver mais blocos
		while(block!=null){
			Block block2=block.getNextBlock();
			//se o proximo bloco nao e nulo
			if(block2!=null) {
				//se tamanho igual e ambos estao livres
				if (block.getSize()==block2.getSize() && block.isFree() && block2.isFree())
				{
					//entao posso fazer merge
					block.setSize(block.getSize()*2);
					block.setIndex((block.getIndex()-1)/2);
					block.setNextBlock(block2.getNextBlock());
					associateblocks();
					//remover o bloco 2 da lista dos blocos livres
					freeBlocks.remove(block2);
				}
			}
			block = block.getNextBlock();
		}
	}
}
