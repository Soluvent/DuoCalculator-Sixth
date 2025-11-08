import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProgrammerCalc extends Application {

    private class MyButton extends Button {
        MyButton(String text) {
            super(text);
            this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();
        root.setStyle("-fx-background-color: #EDF0F2");

        VBox leftPanel = buildProgrammerCalc();

        Separator separator = new Separator(javafx.geometry.Orientation.VERTICAL);

        VBox rightPanel = buildLeasingWorksheet();
        HBox.setMargin(rightPanel, new Insets(10));

        root.getChildren().addAll(leftPanel, separator, rightPanel);

        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        Scene scene = new Scene(root, 930, 510);
        primaryStage.setTitle("Калькулятор");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox buildProgrammerCalc() {
        VBox calcRoot = new VBox();
        calcRoot.setPadding(new Insets(0, 0, 10, 10));

        MenuBar menu = configureMenu();

        Pane resultArea = buildResultArea();
        VBox.setMargin(resultArea, new Insets(10, 10, 10, 0));

        Pane buttonsPanel = buildProgrammerButtons();
        VBox.setMargin(buttonsPanel, new Insets(0, 10, 0, 0));

        calcRoot.getChildren().addAll(menu, resultArea, buttonsPanel);
        VBox.setVgrow(buttonsPanel, Priority.ALWAYS);

        return calcRoot;
    }

    private VBox buildLeasingWorksheet() {
        VBox sheetRoot = new VBox(10);
        sheetRoot.setPadding(new Insets(10));
        sheetRoot.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: #F8F8F8;");
        sheetRoot.setPrefWidth(320);

        Label title = new Label("Выберите значение для вычисления");
        title.setFont(new Font(13));

        ComboBox<String> combo = new ComboBox<>(
                FXCollections.observableArrayList("Регулярный платеж", "Стоимость аренды", "Срок аренды")
        );
        combo.setValue("Регулярный платеж");
        combo.setMaxWidth(Double.MAX_VALUE);

        GridPane formGrid = new GridPane();
        formGrid.setVgap(8);
        formGrid.setHgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(45);
        col1.setHalignment(HPos.RIGHT);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(55);
        formGrid.getColumnConstraints().addAll(col1, col2);

        formGrid.add(new Label("Стоимость аренды:"), 0, 0);
        formGrid.add(new TextField("Ввод значения"), 1, 0);
        formGrid.add(new Label("Срок аренды:"), 0, 1);
        formGrid.add(new TextField("Ввод значения"), 1, 1);
        formGrid.add(new Label("Годовые выплаты:"), 0, 2);
        formGrid.add(new TextField("Ввод значения"), 1, 2);
        formGrid.add(new Label("Остаточная стоимость:"), 0, 3);
        formGrid.add(new TextField("Ввод значения"), 1, 3);
        formGrid.add(new Label("Процентная ставка (%):"), 0, 4);
        formGrid.add(new TextField("Ввод значения"), 1, 4);

        Button calculateButton = new Button("Вычислить");
        calculateButton.setPrefWidth(100);

        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.setStyle("-fx-background-color: #eee;");

        formGrid.add(calculateButton, 0, 5);
        formGrid.add(resultField, 1, 5);
        GridPane.setHalignment(calculateButton, HPos.LEFT);

        sheetRoot.getChildren().addAll(
                title, combo, new Separator(),
                formGrid
        );

        return sheetRoot;
    }

    private MenuBar configureMenu() {
        MenuBar bar = new MenuBar();
        Menu view = new Menu("_Вид");
        ToggleGroup viewGroup = new ToggleGroup();
        RadioMenuItem standard = new RadioMenuItem("Обычный");
        standard.setToggleGroup(viewGroup);
        RadioMenuItem engineer = new RadioMenuItem("Инженерный");
        engineer.setToggleGroup(viewGroup);
        RadioMenuItem programmer = new RadioMenuItem("Программист");
        programmer.setToggleGroup(viewGroup);
        programmer.setSelected(true);
        view.getItems().addAll(standard, engineer, programmer);

        Menu edit = new Menu("_Правка");
        Menu help = new Menu("_Справка");
        bar.getMenus().addAll(view, edit, help);
        return bar;
    }

    private Pane buildResultArea() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setStyle("-fx-border-style: solid inside; -fx-border-width: 1; -fx-border-color: lightgray; -fx-background-color: #F8F8F8;");
        grid.setHgap(10);
        grid.setVgap(5);

        Text mainResult = new Text("0");
        mainResult.setFont(new Font(36));
        grid.add(mainResult, 0, 0, 8, 1);
        GridPane.setHalignment(mainResult, HPos.RIGHT);

        String[] bitValues = {"0000", "0000", "0000", "0000", "0000", "47", "0000", "0000"};
        for (int i = 0; i < bitValues.length; i++) {
            Text t = new Text(bitValues[i]);
            t.setFont(new Font(14));
            grid.add(t, i, 1);
            GridPane.setHalignment(t, HPos.CENTER);
        }

        String[] bitNumbers = {"63", "47", "32"};
        grid.add(new Text(bitNumbers[0]), 0, 2);
        grid.add(new Text(bitNumbers[1]), 3, 2);
        grid.add(new Text(bitNumbers[2]), 7, 2);

        String[] bitValues2 = {"0000", "0000", "0000", "0000", "0000", "0000", "0000", "0000"};
        for (int i = 0; i < bitValues2.length; i++) {
            Text t = new Text(bitValues2[i]);
            t.setFont(new Font(14));
            grid.add(t, i, 3);
            GridPane.setHalignment(t, HPos.CENTER);
        }

        String[] bitNumbers2 = {"31", "15", "0"};
        grid.add(new Text(bitNumbers2[0]), 0, 4);
        grid.add(new Text(bitNumbers2[1]), 3, 4);
        grid.add(new Text(bitNumbers2[2]), 7, 4);

        return grid;
    }

    private Pane buildProgrammerButtons() {
        GridPane pane = new GridPane();
        pane.setHgap(6);
        pane.setVgap(6);

        for (int i = 0; i < 8; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(12.5);
            cc.setHgrow(Priority.ALWAYS);
            pane.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < 6; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPrefHeight(40);
            rc.setVgrow(Priority.ALWAYS);
            pane.getRowConstraints().add(rc);
        }

        VBox radioHex = createRadioGroup(true, "Hex", "Dec", "Oct", "Bin");
        pane.add(radioHex, 0, 0, 1, 4);

        VBox radioBytes = createRadioGroup(true, "8 байт", "4 байта", "2 байта", "1 байт");
        pane.add(radioBytes, 1, 0, 1, 4);

        pane.add(new MyButton("Mod"), 2, 0);
        pane.add(new MyButton("A"), 3, 0);
        pane.add(new MyButton("MC"), 4, 0);
        pane.add(new MyButton("MR"), 5, 0);
        pane.add(new MyButton("MS"), 6, 0);
        pane.add(new MyButton("M+"), 7, 0);

        pane.add(new MyButton("("), 2, 1);
        pane.add(new MyButton("B"), 3, 1);
        pane.add(new MyButton("<-"), 4, 1);
        pane.add(new MyButton("CE"), 5, 1);
        pane.add(new MyButton("C"), 6, 1);
        pane.add(new MyButton("±"), 7, 1);

        pane.add(new MyButton("RoL"), 0, 4);
        pane.add(new MyButton("RoR"), 1, 4);
        pane.add(new MyButton("D"), 2, 2);
        pane.add(new MyButton("7"), 3, 2);
        pane.add(new MyButton("8"), 4, 2);
        pane.add(new MyButton("9"), 5, 2);
        pane.add(new MyButton("/"), 6, 2);
        pane.add(new MyButton("%"), 7, 2);

        pane.add(new MyButton("Or"), 0, 5);
        pane.add(new MyButton("Xor"), 1, 5);
        pane.add(new MyButton("E"), 2, 3);
        pane.add(new MyButton("4"), 3, 3);
        pane.add(new MyButton("5"), 4, 3);
        pane.add(new MyButton("6"), 5, 3);
        pane.add(new MyButton("*"), 6, 3);
        pane.add(new MyButton("1/x"), 7, 3);

        pane.add(new MyButton("Lsh"), 2, 4);
        pane.add(new MyButton("F"), 3, 4);
        pane.add(new MyButton("1"), 4, 4);
        pane.add(new MyButton("2"), 5, 4);
        pane.add(new MyButton("3"), 6, 4);

        pane.add(new MyButton("Not"), 2, 5);
        pane.add(new MyButton("And"), 3, 5);
        pane.add(new MyButton("0"), 4, 5, 2, 1);
        pane.add(new MyButton(","), 6, 5);
        pane.add(new MyButton("+"), 7, 5);

        pane.add(new MyButton("="), 7, 4, 1, 2);

        return pane;
    }

    private VBox createRadioGroup(boolean firstSelected, String... items) {
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER_LEFT);
        ToggleGroup group = new ToggleGroup();
        for (int i = 0; i < items.length; i++) {
            RadioButton rb = new RadioButton(items[i]);
            rb.setToggleGroup(group);
            if (i == 0 && firstSelected) {
                rb.setSelected(true);
            }
            vbox.getChildren().add(rb);
        }
        return vbox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
