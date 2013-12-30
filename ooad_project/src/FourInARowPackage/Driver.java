package FourInARowPackage;

public class Driver {
	public static void main(String[] args) {
		FourInARowModel b = new FourInARowModel(5,6);
		int [][] a1 = b.getBoard();
		a1[2][2] = 5;
		int [][] a2 = b.getBoard();
		System.out.println(a2[2][2]);
		
//		DiscGraphic = new DiscGraphic("x");
//		CellGraphic = new CellGraphic("|");
//		BoardGraphic = new BoardGraphic();
//		
//		CellGraphic.addGraphic(Disc..)
//		BoardGraphic.addGraphic(CellGraphic)
//		CellGraphic.setLocation(x,y)
		
	}
}
