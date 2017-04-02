package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private final Group root = new Group();
    private final PerspectiveCamera camera = new PerspectiveCamera(true);
    private final Xform cameraXform = new Xform();
    private final Xform cameraXform2 = new Xform();
    private final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -750;
    private static final double CAMERA_INITIAL_X_ANGLE = 0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;

    private Board board;
    private Text text;
    private Stage primaryStage;

    private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

    private void setup(Stage primaryStage) {
        this.primaryStage = primaryStage;
        buildCamera();
        board = new Board(root);

        Scene scene = new Scene(root, 824, 768, true);
        scene.setFill(Color.WHITESMOKE);
        scene.setCamera(camera);

        Button button = new Button();
        setButtonProperties(button);

        text = new Text();
        root.getChildren().add(text);
        setDefaultTextProperties("Play a game!");

        scene.setOnMouseClicked((a) -> isWin(primaryStage));
        scene.setOnMouseClicked((a) -> isCatsGame(primaryStage));

        primaryStage.setTitle("tic tac toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void setDefaultTextProperties(String string) {
        text.setText(string);
        text.setRotate(180);
        text.setTranslateX(-75);
        text.setTranslateY(180);
        text.setFill(Color.BLACK);
        text.setFont(Font.font(25));
    }

    private void setButtonProperties(Button button) {
        button.setText("Reset!");
        button.setOnMouseClicked((a) -> resetGame(primaryStage));
        root.getChildren().add(button);
        button.setTranslateY(-200.0);
        button.setTranslateX(-200);
        button.setRotate(180);
        button.setTextFill(Color.BLACK);
    }

    private void resetGame(Stage primaryStage) {
        board.reset();
        setDefaultTextProperties("Play a game!");
        primaryStage.getScene().setFill(Color.WHITESMOKE);
    }

    private void isCatsGame(Stage primaryStage) {
        if (!isWin(primaryStage) && board.isFull()) {
            setDefaultTextProperties("Cat's game :(");
            primaryStage.getScene().setFill(Color.GRAY);
        }
    }

    private boolean isWin(Stage primaryStage) {
        if (board.hasWin()) {
            text.setText("Win!");
            text.setTranslateX(-40);
            text.setTranslateY(190);
            text.setFill(Color.DARKORCHID);
            text.setFont(Font.font(40));
            primaryStage.getScene().setFill(Color.GOLD);
            return true;
        } else return false;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        setup(primaryStage);

    }

}
