import java.util.Collections;
import java.util.List;
import java.util.*;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Memory memory = new Memory(sc.nextInt());
		//enquanto houver mais input, ler o input
		while(sc.hasNext()) {
			String identificador=sc.next();
			int size=sc.nextInt();
			memory.put(identificador,size);
	    }
	    //Display the free and occupied blocks
	    //System.out.println("\nTotal memoria livre = " + memory.freeSpace() + "\nMaior pedaco de memoria livre = " + memory.getlargestblockspace() + "\n");
	    //System.out.println("----Resultado----");
	    //memory.printBlocks();
	    System.out.println("Blocos livres");
		memory.printFreeBlocks();
		System.out.println("Blocos ocupados");
		memory.printOccupiedBlocks();
		//memory.printBlocks();
	}

}

