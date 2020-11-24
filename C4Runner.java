import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.stage.Stage; 
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.lang.Math; 
import java.util.Random;
import javax.swing.JOptionPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class C4Runner extends Application {
	
	protected Text winMessage = new Text(null);
	private static final int CHIP = 75;
	private static final int COLUMNS = 7;
	private static final int ROWS = 6;
	private boolean redORyellow = true;
	private int[] bottomOFboard = new int[7];
	private int[] boardRow = new int[7];
	private int[] boardColumn = new int[7];
	protected C4Board ConnectFour = new C4Board(ROWS,COLUMNS);
	protected Pane board = new Pane();
	Random rand = new Random();
	private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    Stage window;
	Scene scene1, scene2;
	
	/**
	 * Sets the counters for the bottom of each column, 
	 * board rows, and columns
	 */
	private void setBottom() {
		for(int i = 0; i < COLUMNS; i++) {
    		bottomOFboard[i] = 480;
    	}
		for(int i = 0; i < COLUMNS; i++) {
			boardRow[i] = 5;
    	}
		for(int i = 0; i < COLUMNS; i++) {
			boardColumn[i] = i;
    	}
	}
	
	/**
	 * Creates a connect board
	 * @return 
	 */
	private Shape makeGrid() {
	    Shape shape = new Rectangle((COLUMNS + 1) * CHIP, (ROWS + 1) * CHIP);      
	    for (int y = 0; y < ROWS; y++) {
	        for (int x = 0; x < COLUMNS; x++) {
	            Circle circle = new Circle(CHIP / 2);
	            circle.setCenterX(CHIP / 2);
	            circle.setCenterY(CHIP / 2);
	            circle.setTranslateX(x * (CHIP + 5) + CHIP / 4);
	            circle.setTranslateY(y * (CHIP + 5) + CHIP / 4);
	            shape = Shape.subtract(shape, circle);
	        }
	    }
	        shape.setFill(Color.BLUE);
	        return shape;
     }
	
	/**
	 * The cpu chooses a column to drop a chip in 
	 * @param a The column number the cpu randomly chooses 
	 */
	private void cpuTurn(int a) {
		int b = a;
		
		if(bottomOFboard[a] == 0) {
			while(bottomOFboard[a] == 0) {
				a = rand.nextInt(7);	
			}
			Circle chip = new Circle((55 + (80 * a)),-25 ,CHIP / 2);
			TranslateTransition drop = new TranslateTransition(Duration.seconds(2), chip);
			drop.setToX(0);
			drop.setToY(bottomOFboard[a]);
			drop.setNode(chip);
			drop.play();
			chip.setFill(redORyellow(redORyellow));
			System.out.print("Yellow: ");
			System.out.println("Row: " + Math.abs(boardRow[a] - 6) + " Column: " + (boardColumn[a] + 1) + "\n");
			ConnectFour.setChip(boardRow[a], boardColumn[a], "Y" );
			boardRow[a]--;
			board.getChildren().add(chip);
			bottomOFboard[a] += -80;
			
		}else {
			Circle chip = new Circle((55 + (80 * a)),-25 ,CHIP / 2);
			TranslateTransition drop = new TranslateTransition(Duration.seconds(2), chip);
			drop.setToX(0);
			drop.setToY(bottomOFboard[a]);
			drop.setNode(chip);
			drop.play();
			chip.setFill(redORyellow(redORyellow));
			System.out.print("Yellow: ");
			System.out.println("Row: " + Math.abs(boardRow[a] - 6) + " Column: " + (boardColumn[a] + 1) + "\n");
			ConnectFour.setChip(boardRow[a], boardColumn[a], "Y" );
			boardRow[a]--;
			board.getChildren().add(chip);
			bottomOFboard[a] += -80;	
		}	
	}
	
	/**
	 * The player chooses a column to drop a chip in 
	 * @param a The column the player chooses
	 */
	private void playerTurn(int a) {
		Circle chip = new Circle((55 + (80 * a)),-25 ,CHIP / 2);
		TranslateTransition drop = new TranslateTransition(Duration.seconds(1), chip);
		drop.setToX(0);
		drop.setToY(bottomOFboard[a]);
		drop.setNode(chip);
		drop.play();
		chip.setFill(redORyellow(redORyellow));
		System.out.print("Red: ");
		System.out.println("Row: " + Math.abs(boardRow[a] - 6) + " Column: " + (boardColumn[a] + 1) + "\n");
		ConnectFour.setChip(boardRow[a], boardColumn[a], "R" );
		boardRow[a]--;
		board.getChildren().add(chip);
		bottomOFboard[a] += -80;
	}
	
	/**
	 * Every time a chip is dropped the color of the chip changes
	 * @param a the color the chip is currently
	 * @return 
	 */
	private Color redORyellow(boolean a) {
		if(a == true) {
			redORyellow = false;
			return Color.RED;
		}else{
			redORyellow = true;
			return Color.YELLOW;
		}	
	}
	
	/**
	 * Creates the scene and all the functions of the board
	 */
	public void start(Stage primaryStage){ 
		window = primaryStage;
        window.setTitle("Connect Four!");
        
        setBottom();
        
       
        
        board.getChildren().add(makeGrid());

        button1 = new Button("Column 1");
        button2 = new Button("Column 2");
        button3 = new Button("Column 3");
        button4 = new Button("Column 4");
        button5 = new Button("Column 5");
        button6 = new Button("Column 6");
        button7 = new Button("Column 7");
        
        /**
         * Button to drop the chip in column 1
         */
		button1.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[0] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(0);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}
				}
			}
		});
		
		 /**
         * Button to drop the chip in column 2
         */
		button2.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[1] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(1);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}
				}
			}
		});
		
		 /**
         * Button to drop the chip in column 3
         */
		button3.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[2] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(2);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}
				}
			}
		});
		
		 /**
         * Button to drop the chip in column 4
         */
		button4.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[3] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(3);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}
				}
			}
		});

		 /**
         * Button to drop the chip in column 5
         */
		button5.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[4] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(4);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}
				}
			}
		});
        
		 /**
         * Button to drop the chip in column 6
         */
		button6.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[5] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(5);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}			
				}
			}
		});
		
		 /**
         * Button to drop the chip in column 7
         */
		button7.setOnAction(new EventHandler<ActionEvent>() {    	
			public void handle(ActionEvent event) {
				if(bottomOFboard[6] == 0) {
					System.out.println("Column is full choose another");
				}else {
					if(winMessage.getText() != "Yellow Wins!" && winMessage.getText() != "Red Wins!") {
						playerTurn(6);
						if(ConnectFour.winCheckR() == true) {
							System.out.println("Game over. Red wins.");
							winMessage.setText("Red Wins!");
						}
						else {
							cpuTurn(rand.nextInt(7));	//moved this down 4 lines
							if(ConnectFour.winCheckY() == true) {
								System.out.println("Game over. Yellow wins.");
								winMessage.setText("Yellow Wins!");
							}
						}	
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Game is already over");
					}
				}			
			}
		});

        //Scene1 layout
		winMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
		winMessage.setFill(Color.FIREBRICK);
        HBox hbox = new HBox(button1, button2, button3, button4, button5, button6, button7);
        hbox.setSpacing(12);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(board,hbox, winMessage);
        vbox.setAlignment(Pos.CENTER);
        scene1 = new Scene(vbox); 
      
        window.setScene(scene1); 
        window.show(); 
    } 
	
	/**
	 * Puts all the pieces together to create the board
	 * @param args The scene to be shown 
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}