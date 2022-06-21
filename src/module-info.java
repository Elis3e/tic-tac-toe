module tp5 {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.junit.jupiter.api;

	opens morpion.fx.v1 to javafx.graphics, javafx.fxml;
	opens morpion.fx.v2 to javafx.graphics, javafx.fxml;


}
