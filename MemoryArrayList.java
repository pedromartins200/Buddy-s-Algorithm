public class MemoryArrayList {
    //size e a variavel utilizada para definir o tamanho maxximo de blocos deste array
    int size=1;
    //variavel current e usada para saber a posicao ate qual este array esta atualmente preenchido
    int current=0;
    //array para guardar os blocos nesta lista
    public Block[] blocks;
    //inicializar este array com size
    public MemoryArrayList(){
        blocks=new Block[size];
    }
    //adicionar um bloco a este array
    //adaptado dos slides de AED, fernando lobo
    public void add(Block block)
    {
        //nao meti a questao de T.size == 0, iniciando o size logo a 1
        if(current==size)
        {
            //Se nao houver espaco, duplicar o tamanho do array
            size=size*2;
            //criar um novo array com o dobro do tamanho
            Block[] blocksNew=new Block[size];
            //mover todos os blocos do array antigo para o array novo
            for(int i=0;i<current;i++)
                blocksNew[i]=blocks[i];
            //trocar o antigo array mais pequeno pelo novo array com o dobro do tamanho
            blocks=blocksNew;
        }
        //adicionar este bloco a posicao current
        blocks[current++]=block;
    }
    //remover um elemento desta classe
    public void remove(Block block)
    {
        for(int i=0;i<current;i++) {
            //encontrar este bloco nesta classe
            if (blocks[i] == block) {
                //se o bloco foi encontrado na posicao i, entao mover um espaco para a direita
                for(int j=i;j<current-1;j++)
                {
                    blocks[j]=blocks[j+1];
                }
                //ultimo elemento = null
                blocks[current-1]=null;
                //reduzir o numero de blocos para menos 1
                current--;
            }
        }
    }
    //obter o bloco neste indice
    public Block get(int index)
    {
        return blocks[index];
    }

    //associar um bloco a uma posicao nesta classe
    public void set(int index,Block block)
    {
        blocks[index]=block;
    }
    //retornar o numero de elementos do array
    public int getSize(){
        return current;
    }
    
    // podia ter sido usado exatamente o mesmo insertion sort do primeiro problema aed,
    // mas isto tambem funciona
    // vai ser muito ineficiente, mas dado que nunca vamos ter uma quantidade absurda de elemtnso, funciona
    // alias o Insertion Sort e recomendado para casos de arrays pequenos
    // facil implementacao
    // necessario para print dos free blocks e occupied blocks
    public void sortBySize(){
        for(int i=0;i<current;i++) {
            for (int j = i + 1; j < current; j++) {
                //adaptado de programacao imperativa, pedro guerreiro 
                if (blocks[i].getSize() > blocks[j].getSize()) {
                    Block temp=blocks[i];
                    blocks[i]=blocks[j];
                    blocks[j]=temp;
                }
            }
        }

    }

}
