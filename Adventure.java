import java.util.Scanner;
import java.util.Vector;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;


public class Adventure extends Application{

	static ImageView topLeft = new ImageView();
	static ImageView topMiddle = new ImageView();
	static ImageView topRight = new ImageView();
	static ImageView middleLeft = new ImageView();
	static ImageView middle = new ImageView();
	static ImageView person = new ImageView();
	static ImageView middleRight = new ImageView();
	static ImageView botLeft = new ImageView();
	static ImageView botMiddle = new ImageView();
	static ImageView botRight = new ImageView();
	static ImageView upper1 = new ImageView();
	static ImageView upper2 = new ImageView();
	static ImageView upper3 = new ImageView();
	static ImageView upper4 = new ImageView();
	static ImageView upper5 = new ImageView();
	static ImageView left1 = new ImageView();
	static ImageView left2 = new ImageView();
	static ImageView left3 = new ImageView();
	static ImageView right1 = new ImageView();
	static ImageView right2 = new ImageView();
	static ImageView right3 = new ImageView();
	static ImageView lower1 = new ImageView();
	static ImageView lower2 = new ImageView();
	static ImageView lower3 = new ImageView();
	static ImageView lower4 = new ImageView();
	static ImageView lower5 = new ImageView();
	static int[] loc = {0,0};
	static int[] edges = new int [2];
	static Text text = new Text();
	static Vector<String> check = new Vector<String>(4);
	static TextField input = new TextField();
	static String theFile;
	
	public void start(Stage stage){
		BorderPane bPane = new BorderPane();
		GridPane map = new GridPane();
		HBox bottom = new HBox(40);
		
		Button save = new Button("Save");
		save.setOnAction(e-> Save(stage));
		
		Button open = new Button("Open");
		open.setOnAction(e -> Open(stage));
		
		input.setOnKeyPressed(e-> {
			switch(e.getCode()){
			case ENTER:
				Submit(input);
				break;
			case UP:
				Go("n",edges);
				break;
			case DOWN:
				Go("s",edges);
				break;
			case LEFT:
				Go("w",edges);
				break;
			case RIGHT:
				Go("e",edges);
				break;
			}
		});
		
		Map.SetMap(person, loc, edges, topLeft, topMiddle, topRight, middleLeft, middle, middleRight, botLeft, botMiddle, botRight, upper1, upper2, upper3, upper4, upper5, left1, left2, left3, right1, right2,right3, lower1,lower2, lower3, lower4, lower5);
		
		map.addRow(0,upper1,upper2,upper3,upper4,upper5);
		map.addRow(1, left1,topLeft,topMiddle,topRight,right1);
		map.addRow(2, left2,middleLeft,middle,middleRight,right2);
		map.addRow(3, left3,botLeft,botMiddle,botRight,right3);
		map.addRow(4, lower1,lower2,lower3,lower4,lower5);
		bottom.getChildren().addAll(save, open, input);
		bottom.setAlignment(Pos.BASELINE_CENTER);
		bottom.setBackground(new Background(new BackgroundFill(Color.rgb(10,10,20), CornerRadii.EMPTY, Insets.EMPTY)));
		bPane.setTop(bottom);
		bPane.setLeft(map);
		bPane.setRight(text);
		Scene scene = new Scene(bPane, 1000, 500);
		stage.setScene(scene);
		stage.show();
	}
	
	public static int[] Go(String dir, int[] edges) {
		String east = "e";
		String north = "n";
		String south = "s";
		String west = "w";
		String first = String.valueOf(dir.charAt(0));
		first = first.toLowerCase();
		
		
		if(first.contentEquals(north)) {
			AddToText("Going North");
			if(loc[0] == 0) {
				AddToText("Can't go that way still at " + loc[0] + ", " + loc[1]);
			}
			else {
				loc[0]--;
				AddToText("Now at " + loc[0] + ", " + loc[1]);
			}
			Map.SetMap(person,loc, edges, topLeft, topMiddle, topRight, middleLeft, middle, middleRight, botLeft, botMiddle, botRight,upper1, upper2, upper3, upper4, upper5, left1, left2, left3, right1, right2,right3, lower1,lower2, lower3, lower4, lower5);
			
			if(Map.GetItems(loc) != null) {
				AddToText("You found " + Map.GetItems(loc));
			}
			
			return loc;
		}//end north
		else if(first.equals(east)) {
			AddToText("Going East");
			if(loc[1] == edges[1] - 1) {
				AddToText("Can't go that way still at " + loc[0] + ", " + loc[1]);
			}
			else {
				loc[1]++;
				AddToText("Now at " + loc[0] + ", " + loc[1]);
			}
			Map.SetMap(person, loc, edges, topLeft, topMiddle, topRight, middleLeft, middle, middleRight, botLeft, botMiddle, botRight,upper1, upper2, upper3, upper4, upper5, left1, left2, left3, right1, right2,right3, lower1,lower2, lower3, lower4, lower5);
			
			if(Map.GetItems(loc) != null) {
				AddToText("You found " + Map.GetItems(loc));
			}
			
			return loc;
		}//east
		else if(first.equals(south)) {
			AddToText("Going South");
			if(loc[0] == edges[0] - 1) {
				AddToText("Can't go that way still at " + loc[0] + ", " + loc[1]);
			}
			else {
				loc[0]++;
				AddToText("Now at " + loc[0] + ", " + loc[1]);
			}
			Map.SetMap(person, loc, edges, topLeft, topMiddle, topRight, middleLeft, middle, middleRight, botLeft, botMiddle, botRight,upper1, upper2, upper3, upper4, upper5, left1, left2, left3, right1, right2,right3, lower1,lower2, lower3, lower4, lower5);
			
			if(Map.GetItems(loc) != null) {
				AddToText("You found " + Map.GetItems(loc));
			}
			
			return loc;
		}//end south
		else if(first.equals(west)) {
			AddToText("Going West");
			if(loc[1] == 0) {
				AddToText("Can't go that way still at " + loc[0] + ", " + loc[1]);
			}
			else {
				loc[1]--;
				AddToText("Now at " + loc[0] + ", " + loc[1]);
			}
			Map.SetMap(person, loc, edges, topLeft, topMiddle, topRight, middleLeft, middle, middleRight, botLeft, botMiddle, botRight,upper1, upper2, upper3, upper4, upper5, left1, left2, left3, right1, right2,right3, lower1,lower2, lower3, lower4, lower5);
			
			if(Map.GetItems(loc) != null) {
				AddToText("You found " + Map.GetItems(loc));
			}
			
			return loc;
		}//end west
		else {
			AddToText("Not a viable direction");
			return loc;
		}//default
		
		
	}
	
	public static void AddToText(String s) {
		String temp = s + "\n" + text.getText();
		text.setText(temp);
	}
	
	public static void Submit(TextField s) {
		String temp = s.getText();
		String first = String.valueOf(temp.charAt(0));
		int words = 0;
		
		if(first.equals("i")) {
			Check();
		}
		else if(first.equals("d")) {
			words = Words(temp);
			int i;
			Boolean skip = true;
			String item = null;
			if(words > 0) {
				for(int j = 1; j <= words; j++) {
					String t = temp.split(" ")[j];
					if(item == null) {
						item = t;
					}
					else {
						item = item + " " + t;
					}
				}
				
				for(i = 0; i < check.size(); i++) {
					if(item.equals(check.elementAt(i))){
						check.remove(i);
						Map.DropItem(item, loc);
						AddToText("Dropped " + item);
						skip = false;
						break;
					}
				}
				if(skip) {
					AddToText("You don't have that item");
				}
			}
		}
		else if(first.equals("t")) {
			String item = null;
			words = Words(temp);
			if(words > 0) {
				if(Map.GetItems(loc) != null) {
					for(int j = 1; j <= words; j++) {
						String t = temp.split(" ")[j];
						if(item == null) {
							item = t;
						}
						else {
							item = item + " " + t;
						}
					}
					
					if(item.equals(Map.GetItem(loc,item))) {
						check.addElement(Map.GetItem(loc,item));
						AddToText("Picked up " + Map.GetItem(loc,item));
						Map.RemoveItem(loc,item);
					}
					else {
						AddToText("No item by that name here");
					}
					
					
				}
				else {
					AddToText("No item here");
				}
			}
			else {
				AddToText("Not enough information");
			}
		}
		input.clear();
	}
	
	public static int Words(String cmd) {
		int num = 0;
		for(int i = 0; i < cmd.length(); i++) {
			if(cmd.charAt(i) == ' ' && i != 0) {
				num++;
			}
		}
		return num;
	}
	
	public static void Check() {
		
		for(int i = 0; i<check.size(); i++) {
			AddToText(check.get(i));
		}
		AddToText("You have:");
	}

	public static void Save(Stage stage) {
		FileChooser set = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		set.getExtensionFilters().add(extFilter);
		File file = set.showSaveDialog(stage);
		String info = Compile();
		
		if(file != null) {
			SaveTextToFile(info, file);
		}
	}
	
	public static String Compile() {
		String info = "";
		int i;
		
		info = info + theFile + ";" + loc[0] + ";" + loc[1] + ";" + check.size();
		
		for(i = 0; i < check.size(); i++) {
			info = info + ";" + check.elementAt(i);  
		}
		info = info + ";";
		String temp = info;
		info = Map.MapItems(temp);
		return info;
	}
	
	public static void SaveTextToFile(String info, File file) {
		try {
			PrintWriter save;
			save = new PrintWriter(file);
			save.print(info);
			save.close();
		}catch(IOException x) {
			x.printStackTrace();
		}
		
	}
	
	public static void Open(Stage stage) {
		FileChooser select = new FileChooser();
		File file = select.showOpenDialog(stage);
		FileReader open;
		int semi = 59;
		int i;
		int semis = 0;
		int index = 0;
		String map = "";
		String loc0 = "";
		String loc1 = "";
		String numItems = "";
		String numItms = "";
		
		
		
		try {
			open = new FileReader(file);
			while((i = open.read()) != semi) {
				map = map + (char)i;
			}
			while((i = open.read()) != semi) {
				loc0 = loc0 + (char)i;
			}
			while((i = open.read()) != semi) {
				loc1 = loc1 + (char)i;
			}
			while((i = open.read()) != semi) {
				numItems = numItems + (char)i;
			}
			Vector<String> temp = new Vector<String>(Integer.parseInt(numItems));
			String t = "";
			while(semis < Integer.parseInt(numItems)) {
				i = open.read();
				if(i == semi) {
					temp.add(t);
					t = "";
					semis++;
					index ++;
				}
				else {
					t = t + (char)i;
				}
			}
			check = temp;
			loc[0] = Integer.parseInt(loc0);
			loc[1] = Integer.parseInt(loc1);
			Map.Reset();
			Map.read(map);
			while((i = open.read()) != semi) {
				numItms = numItms + (char)i;
			}
			int end = Integer.parseInt(numItms) * 3;
			int ind = 0;
			int s = 1;
			String x = "";
			int r = 0;
			String y = "";
			int c = 0;
			String name = "";
			Map.ResetItem();
			while(ind != end) {
				int pos = s % 3;
				i = open.read();
				if(pos == 0 && i != semi) {
					name = name + (char)i;
				}
				else if(pos == 1 && i != semi) {
					x = x + (char)i;
					r = Integer.parseInt(x);
				}
				else if(pos == 2 && i != semi) {
					y =  y + (char)i;
					c = Integer.parseInt(y);
				}
				else {
					if(pos == 0) {
						Map.SetItem(r,c,name);
						System.out.println(name);
						System.out.println(r);
						System.out.println(c);
						x = "";
						y = "";		
						name = "";
					}
					s++;
					ind++;
				}
				
				
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException x) {
			x.printStackTrace();
		}
		
		
		Map.SetMap(person, loc, edges, topLeft, topMiddle, topRight, middleLeft, middle, middleRight, botLeft, botMiddle, botRight,upper1, upper2, upper3, upper4, upper5, left1, left2, left3, right1, right2,right3, lower1,lower2, lower3, lower4, lower5);
	}
	
	
	public static void main(String[] args) {
		String go = "g";
		String inventory = "i";
		String quit = "q";
		boolean stay = true;
		
		edges = Map.read(args[0]);
		theFile = args[0];		
		
		
		check.addElement("brass lantern");
		check.addElement("rope");
		check.addElement("rations");
		check.addElement("staff");
		
		launch(args);
		
		Scanner in = new Scanner(System.in);
		
		
		
		String first = " ";
		String second = " ";
		int words = 0;
		
		
		in.close();
	}

	
}
