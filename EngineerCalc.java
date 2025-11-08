import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class EngineerCalc extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(5);
        root.setStyle("-fx-background-color: #EDF0F2");
        Scene scene = new Scene(root);

        root.getChildren().add(configureMenu());

        HBox box = configureResultView();
        root.getChildren().add(box);
        VBox.setMargin(box, new Insets(10, 10, 5, 10));

        HBox radioBox = configureRadioButtons();
        root.getChildren().add(radioBox);
        VBox.setMargin(radioBox, new Insets(5, 10, 5, 10));

        root.getChildren().add(configureButtons());

        primaryStage.setTitle("Инженерный калькулятор");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class MyButton extends Button {
        MyButton(String text) {
            super(text);
            this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    private MenuBar configureMenu() {
        MenuBar bar = new MenuBar();

        Menu view = new Menu("_Вид");
        ToggleGroup viewGroup = new ToggleGroup();

        RadioMenuItem standard = new RadioMenuItem("Обычный");
        standard.setToggleGroup(viewGroup);

        RadioMenuItem engineer = new RadioMenuItem("Инженерный");
        engineer.setToggleGroup(viewGroup);
        engineer.setSelected(true);

        RadioMenuItem programmer = new RadioMenuItem("Программист");
        programmer.setToggleGroup(viewGroup);

        view.getItems().addAll(standard, engineer, programmer, new SeparatorMenuItem());

        Menu edit = new Menu("_Правка");
        Menu help = new Menu("_Справка");

        bar.getMenus().addAll(view, edit, help);
        return bar;
    }

    private HBox configureResultView() {
        HBox box = new HBox();
        box.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 3;" +
                "-fx-border-color: gray;" +
                "-fx-padding: 25 2 6 30;" +
                "-fx-background-color: linear-gradient(to bottom, #d3eefb 0%,#f4f8f9 59%);");

        Text text = new Text("0");
        text.setTextAlignment(TextAlignment.RIGHT);
        text.setFont(new Font(40));

        box.getChildren().add(text);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        return box;
    }

    private HBox configureRadioButtons() {
        HBox radioBox = new HBox(10);
        radioBox.setPadding(new Insets(5, 5, 5, 5));
        radioBox.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup group = new ToggleGroup();

        RadioButton radGradusy = new RadioButton("Градусы");
        radGradusy.setToggleGroup(group);
        radGradusy.setSelected(true);

        RadioButton radRadiany = new RadioButton("Радианы");
        radRadiany.setToggleGroup(group);

        RadioButton radGrady = new RadioButton("Грады");
        radGrady.setToggleGroup(group);

        radioBox.getChildren().addAll(radGradusy, radRadiany, radGrady);
        return radioBox;
    }

    private GridPane configureButtons() {
        GridPane pane = new GridPane();

        pane.setStyle("-fx-padding: 2 10 4 10");
        pane.setHgap(6);
        pane.setVgap(6);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setFillWidth(true);
        cc.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().addAll(cc, cc, cc, cc, cc, cc, cc, cc, cc, cc);

        RowConstraints rc = new RowConstraints();
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().addAll(rc, rc, rc, rc, rc, rc);

        pane.add(new MyButton("MC"), 5, 0);
        pane.add(new MyButton("MR"), 6, 0);
        pane.add(new MyButton("MS"), 7, 0);
        pane.add(new MyButton("M+"), 8, 0);
        pane.add(new MyButton("M-"), 9, 0);

        pane.add(new MyButton("Inv"), 0, 1);
        pane.add(new MyButton("ln"), 1, 1);
        pane.add(new MyButton("("), 2, 1);
        pane.add(new MyButton(")"), 3, 1);
        pane.add(new MyButton("<-"), 5, 1);
        pane.add(new MyButton("CE"), 6, 1);
        pane.add(new MyButton("C"), 7, 1);
        pane.add(new MyButton("±"), 8, 1);
        pane.add(new MyButton("√"), 9, 1);

        pane.add(new MyButton("Int"), 0, 2);
        pane.add(new MyButton("sinh"), 1, 2);
        pane.add(new MyButton("sin"), 2, 2);
        pane.add(new MyButton("x²"), 3, 2);
        pane.add(new MyButton("n!"), 4, 2);
        pane.add(new MyButton("7"), 5, 2);
        pane.add(new MyButton("8"), 6, 2);
        pane.add(new MyButton("9"), 7, 2);
        pane.add(new MyButton("/"), 8, 2);
        pane.add(new MyButton("%"), 9, 2);

        pane.add(new MyButton("dms"), 0, 3);
        pane.add(new MyButton("cosh"), 1, 3);
        pane.add(new MyButton("cos"), 2, 3);
        pane.add(new MyButton("x^y"), 3, 3);
        pane.add(new MyButton("y√x"), 4, 3);
        pane.add(new MyButton("4"), 5, 3);
        pane.add(new MyButton("5"), 6, 3);
        pane.add(new MyButton("6"), 7, 3);
        pane.add(new MyButton("*"), 8, 3);
        pane.add(new MyButton("1/x"), 9, 3);

        pane.add(new MyButton("π"), 0, 4);
        pane.add(new MyButton("tanh"), 1, 4);
        pane.add(new MyButton("tan"), 2, 4);
        pane.add(new MyButton("x³"), 3, 4);
        pane.add(new MyButton("³√x"), 4, 4);
        pane.add(new MyButton("1"), 5, 4);
        pane.add(new MyButton("2"), 6, 4);
        pane.add(new MyButton("3"), 7, 4);
        pane.add(new MyButton("-"), 8, 4);

        pane.add(new MyButton("="), 9, 4, 1, 2);

        pane.add(new MyButton("F-E"), 0, 5);
        pane.add(new MyButton("Exp"), 1, 5);
        pane.add(new MyButton("Mod"), 2, 5);
        pane.add(new MyButton("log"), 3, 5);
        pane.add(new MyButton("10^x"), 4, 5);

        pane.add(new MyButton("0"), 5, 5, 2, 1);
        pane.add(new MyButton(","), 7, 5);
        pane.add(new MyButton("+"), 8, 5);

        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
