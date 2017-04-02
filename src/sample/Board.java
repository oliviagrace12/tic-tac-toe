package sample;

import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 * Created by oliviachisman on 3/25/17
 */
public class Board {

    private final static Color START_COLOR = Color.GRAY;

    private final Circle circle1;
    private final Circle circle2;
    private final Circle circle3;
    private final Circle circle4;
    private final Circle circle5;
    private final Circle circle6;
    private final Circle circle7;
    private final Circle circle8;
    private final Circle circle9;

    Board(Group root) {
        circle1 = new Circle(-100, -100, 50);
        setCircleProperties(circle1, root);
        circle2 = new Circle(0, -100, 50);
        setCircleProperties(circle2, root);
        circle3 = new Circle(100, -100, 50);
        setCircleProperties(circle3, root);

        circle4 = new Circle(-100, 0, 50);
        setCircleProperties(circle4, root);
        circle5 = new Circle(0, 0, 50);
        setCircleProperties(circle5, root);
        circle6 = new Circle(100, 0, 50);
        setCircleProperties(circle6, root);

        circle7 = new Circle(-100, 100, 50);
        setCircleProperties(circle7, root);
        circle8 = new Circle(0, 100, 50);
        setCircleProperties(circle8, root);
        circle9 = new Circle(100, 100, 50);
        setCircleProperties(circle9, root);

    }

    private void setCircleProperties(Circle circle, Group root) {
        circle.setFill(START_COLOR);
        root.getChildren().add(circle);
        circle.setOnMouseClicked((a) -> changeColor(Color.MEDIUMAQUAMARINE, Color.FUCHSIA, circle, a));
    }

    private void changeColor(Color color1, Color color2, Shape shape, MouseEvent a) {
        if (a.getButton().equals(MouseButton.SECONDARY)) {
            shape.setFill(color2);
        } else {
            shape.setFill(color1);
        }
    }

    private boolean winConfig(Circle a, Circle b, Circle c) {
        return !a.getFill().equals(START_COLOR)
                && a.getFill().equals(b.getFill())
                && a.getFill().equals(c.getFill());
    }

    private boolean horizonalWin() {
        return winConfig(circle1, circle2, circle3)
                || winConfig(circle4, circle5, circle6)
                || winConfig(circle7, circle8, circle9);
    }

    private boolean verticalWin() {
        return winConfig(circle1, circle4, circle7)
                || winConfig(circle2, circle5, circle8)
                || winConfig(circle3, circle6, circle9);
    }

    private boolean diagonalWin() {
        return winConfig(circle1, circle5, circle9)
                || winConfig(circle3, circle5, circle7);
    }

    boolean hasWin() {
        return horizonalWin() || verticalWin() || diagonalWin();
    }

    boolean isFull() {
        return !circle1.getFill().equals(START_COLOR) &&
                !circle2.getFill().equals(START_COLOR) &&
                !circle3.getFill().equals(START_COLOR) &&
                !circle4.getFill().equals(START_COLOR) &&
                !circle5.getFill().equals(START_COLOR) &&
                !circle6.getFill().equals(START_COLOR) &&
                !circle7.getFill().equals(START_COLOR) &&
                !circle8.getFill().equals(START_COLOR) &&
                !circle9.getFill().equals(START_COLOR);
    }

    void reset() {
        circle1.setFill(START_COLOR);
        circle2.setFill(START_COLOR);
        circle3.setFill(START_COLOR);
        circle4.setFill(START_COLOR);
        circle5.setFill(START_COLOR);
        circle6.setFill(START_COLOR);
        circle7.setFill(START_COLOR);
        circle8.setFill(START_COLOR);
        circle9.setFill(START_COLOR);
    }

}
