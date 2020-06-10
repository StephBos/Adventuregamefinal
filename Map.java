import java.io.*;
import java.util.Vector;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {
	public static char[][] matrix = null;
	public static Vector<Img> Images = new Vector<Img>(0);
	static Vector <Items> items = new Vector<Items>(0);
	
	public static int[] read(String file) {
		FileReader map = null;
		int space = 32;
		int newLine = 13;
		int zero = 48;
		int nine = 57;
		int semi = 59;
		String itemFile = "";
		String row = "";
		String column = "";
		String imgLength = "";
		String imgWidth = "";
		char ter;
		int r;
		int c;
		int[] loc = new int [2];
		
		
		
		try {
			map = new FileReader(file);
			int i;
			
		
			
			while((i = map.read()) != space){
				
				
				row = row + (char)i;
			}
			
			
			while((i = map.read()) != newLine) {
				
				column = column +(char)i;
			}
			
			
			
			map.read();
			r = Integer.parseInt(row);
			c = Integer.parseInt(column);
			loc [0] = r;
			loc [1] = c;
			matrix = new char[r][c];
			
			int y = 0;
			int x = 0;
			while((i = map.read()) != -1) {
				if(i == newLine) {
					map.read();
					x = 0;
					y++;
				}
				else if(i < nine && i > zero) {
					imgLength = imgLength + (char)i;
					break;
				}
				else {
					matrix[y][x] = (char)i;
					x++;
				}
			}
			
			while((i = map.read()) != space){
				imgLength = imgLength + (char)i;
			}
			
			while((i = map.read()) != newLine) {
				imgWidth = imgWidth +(char)i;
			}
			
			map.read();
			
			while((i = map.read()) != newLine) {
				itemFile = itemFile + (char)i;
			}
			ReadItemFile(itemFile);
			map.read();
			
			while((i = map.read()) != -1) {
				String terrain = "";
				String terFile = "";
				ter = (char)i;
				map.read();
				while((i = map.read()) != semi) {
					terrain = terrain + (char)i;
				}
				while((i = map.read()) != semi) {
					terFile = terFile + (char)i;
				}
				Img temp = new Img(imgLength, imgWidth, ter, terrain, terFile);
				Images.add(temp);
				map.read();
				map.read();
			}
			//String test = Images.elementAt(0).img;
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loc;
	}
	
	public static void Reset() {
		matrix = null;
		Images = new Vector<Img>(0);
		items = new Vector<Items>(0);
	}
	
	public static void ResetItem() {
		items = new Vector<Items>(0);
	}
	
	public static void ReadItemFile(String file) {
		FileReader i = null;
		int j;
		int semi = 59;
		int newLine = 13;
		int x;
		int y;
		
		
		try {
			i = new FileReader(file);
			while ((j = i.read()) != -1) {
				x = j;
				x = x - 48;
				i.read();
				y= i.read();
				y = y - 48;
				i.read();
				String name = "";
				while((j = i.read()) != semi) {
					name = name + (char)j;
				}
				i.read();
				i.read();
				Items temp = new Items(x,y,name);
				items.add(temp);
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String MapItems(String mi) {
		
		mi = mi + items.size() + ";";
		
		for(int i = 0; i < items.size(); i++) {
			mi = mi + items.elementAt(i).x + ";" + items.elementAt(i).y + ";" + items.elementAt(i).name + ";";
		}
		
		return mi;
	}
	
	public static void SetItem(int r, int c, String name) {
		Items temp = new Items(r,c,name);
		items.add(temp);
	}
	
	public static void SetMap(ImageView person, int[] loc, int[] edges, ImageView topLeft, ImageView topMiddle, ImageView topRight, ImageView middleLeft, ImageView middle, ImageView middleRight, ImageView botLeft, ImageView botMiddle, ImageView botRight, ImageView upper1, ImageView upper2, ImageView upper3, ImageView upper4, ImageView upper5, ImageView left1, ImageView left2, ImageView left3, ImageView right1, ImageView right2, ImageView right3, ImageView lower1, ImageView lower2, ImageView lower3, ImageView lower4, ImageView lower5) {
		
		if(loc[0] - 2 < 0 || loc[1] - 2 < 0) {
			Image tl = new Image(GetImage('-'));
			upper1.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-2][loc[1]-2]));
			upper1.setImage(tl);
		}//upper1
		
		if(loc[0] - 2 < 0 || loc[1] - 1 < 0) {
			Image tl = new Image(GetImage('-'));
			upper2.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-2][loc[1]-1]));
			upper2.setImage(tl);
		}//upper2
		
		if(loc[0] - 2 < 0) {
			Image tl = new Image(GetImage('-'));
			upper3.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-2][loc[1]]));
			upper3.setImage(tl);
		}//upper3
		
		if(loc[0] - 2 < 0 || loc[1] + 1 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			upper4.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-2][loc[1]+1]));
			upper4.setImage(tl);
		}//upper4
		
		if(loc[0] - 2 < 0 || loc[1] + 2 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			upper5.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-2][loc[1]+2]));
			upper5.setImage(tl);
		}//upper5
		
		if(loc[0] - 1 < 0 || loc[1] - 2 < 0) {
			Image tl = new Image(GetImage('-'));
			left1.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-1][loc[1]-2]));
			left1.setImage(tl);
		}//left1
		
		if(loc[1] - 2 < 0) {
			Image tl = new Image(GetImage('-'));
			left2.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]][loc[1]-2]));
			left2.setImage(tl);
		}//left2
		
		if(loc[0] + 1 > edges[0]-1 || loc[1] - 2 < 0) {
			Image tl = new Image(GetImage('-'));
			left3.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+1][loc[1]-2]));
			left3.setImage(tl);
		}//left3
		
		if(loc[0]-1 < 0 || loc[1] + 2 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			right1.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-1][loc[1]+2]));
			right1.setImage(tl);
		}//right1
		
		if(loc[1] + 2 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			right2.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]][loc[1]+2]));
			right2.setImage(tl);
		}//right2
		
		if(loc[0] + 1 > edges[0]-1 || loc[1] + 2 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			right3.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+1][loc[1]+2]));
			right3.setImage(tl);
		}//right3
		
		if(loc[0]+2 > edges[0]-1 || loc[1] - 2 < 0) {
			Image tl = new Image(GetImage('-'));
			lower1.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+2][loc[1]-2]));
			lower1.setImage(tl);
		}//lower1

		if(loc[0]+ 2 > edges[0]-1 || loc[1] - 1 < 0) {
			Image tl = new Image(GetImage('-'));
			lower2.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+2][loc[1]-1]));
			lower2.setImage(tl);
		}//lower2
		
		if(loc[0]+2 > edges[0]-1) {
			Image tl = new Image(GetImage('-'));
			lower3.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+2][loc[1]]));
			lower3.setImage(tl);
		}//lower3
		
		if(loc[0]+2 > edges[0]-1 || loc[1] + 1 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			lower4.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+2][loc[1]+1]));
			lower4.setImage(tl);
		}//lower4
		
		if(loc[0]+2 > edges[0]-1 || loc[1] + 2 > edges[1]-1) {
			Image tl = new Image(GetImage('-'));
			lower5.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]+2][loc[1]+2]));
			lower5.setImage(tl);
		}//lower5
		
		if(loc[0] - 1 < 0 || loc[1] - 1 < 0) {
			Image tl = new Image(GetImage('-'));
			topLeft.setImage(tl);
		}
		else {
			Image tl = new Image(GetImage(matrix[loc[0]-1][loc[1]-1]));
			topLeft.setImage(tl);
		}//top left
		
		if(loc[0] - 1 < 0) {
			Image tm = new Image(GetImage('-'));
			topMiddle.setImage(tm);
		}
		else {
			Image tm = new Image(GetImage(matrix[loc[0]-1][loc[1]]));
			topMiddle.setImage(tm);
		}//top middle
		
		if(loc[0] - 1 < 0 || loc[1] + 1 > edges[1] - 1) {
			Image tr = new Image(GetImage('-'));
			topRight.setImage(tr);
		}
		else {
			Image tr = new Image(GetImage(matrix[loc[0] - 1][loc[1] +1 ]));
			topRight.setImage(tr);
		}//top right
		
		if(loc[1] - 1 < 0) {
			Image ml = new Image(GetImage('-'));
			middleLeft.setImage(ml);
		}
		else {
			Image ml = new Image(GetImage(matrix[loc[0]][loc[1] - 1]));
			middleLeft.setImage(ml);
		}//middle left
		
		Image p = new Image(GetImage('1'));
		middle.setImage(p);
		//center
		
		if(loc[1] + 1 > edges[1] - 1) {
			Image mr = new Image(GetImage('-'));
			middleRight.setImage(mr);
		}
		else {
			Image mr = new Image(GetImage(matrix[loc[0]][loc[1] + 1]));
			middleRight.setImage(mr);
		}//middle right
		
		if(loc[0] + 1 > edges[0] - 1 || loc[1] - 1 < 0) {
			Image bl = new Image(GetImage('-'));
			botLeft.setImage(bl);
		}
		else {
			Image bl = new Image(GetImage(matrix[loc[0]+1][loc[1]-1]));
			botLeft.setImage(bl);
		}//bottom left
		
		if(loc[0] + 1 > edges[0] - 1) {
			Image bm = new Image(GetImage('-'));
			botMiddle.setImage(bm);
		}
		else {
			Image bm =  new Image(GetImage(matrix[loc[0]+1][loc[1]]));
			botMiddle.setImage(bm);
		}//bottom middle
		
		if(loc[0] + 1 > edges[0] - 1 || loc[1] + 1 > edges[1] - 1) {
			Image br = new Image(GetImage('-'));
			botRight.setImage(br);
		}
		else {
			Image br = new Image(GetImage(matrix[loc[0] + 1][loc[1] + 1]));
			botRight.setImage(br);
		}//bottom right
	}
	
	
	
	public static String GetImage(char t) {
		for(int i = 0; i < Images.size(); i++) {
			if(t == Images.elementAt(i).terrain) {
				return Images.elementAt(i).img;
			}
		}
		System.out.println("not finding it");
		return "no image by that name";
	}
	
	public static String GetItems(int loc[]) {
		String ite = null;
		for(int i = 0; i < items.size(); i++) {
			if(items.elementAt(i).x == loc[0] && items.elementAt(i).y == loc[1]) {
				if(ite == null) {
					ite = items.elementAt(i).name;
				}
				else {
					ite = ite + "\n" + items.elementAt(i).name;
				}
			}
		}
		
		return ite;
	}
	
	public static String GetItem(int loc[], String ite) {
		String item = null;
		for(int i = 0; i < items.size(); i++) {
			if(items.elementAt(i).x == loc[0] && items.elementAt(i).y == loc[1] && ite.equals(items.elementAt(i).name)){
				item = items.elementAt(i).name;
			}
		}
		
		
		return item;
	}
	
	public static void RemoveItem(int loc[],String ite) {
		for(int i = 0; i < items.size(); i++) {
			if(items.elementAt(i).x == loc[0] && items.elementAt(i).y == loc[1] && ite.equals(items.elementAt(i).name)) {
				items.remove(i);
			}
		}
	}
	
	public static void DropItem(String itm, int loc[]) {
		Items temp = new Items(loc[0],loc[1], itm);
		items.addElement(temp);
	}
	
}


