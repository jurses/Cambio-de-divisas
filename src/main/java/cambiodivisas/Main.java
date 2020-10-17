package cambiodivisas;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage; 
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Main extends Application{
	private static ObservableList<Divisa> l_divisas;

	public static void main(String[] args) {
		Divisa euro = new Divisa("Euro", 1.0);
		Divisa bitcoin = new Divisa("Bitcoin", 0.0001);
		Divisa dolar = new Divisa("Dolar", 1.17);
		Divisa peseta = new Divisa("Peseta", 166.386);
		
		l_divisas = FXCollections.observableArrayList(euro, bitcoin, dolar, peseta);
		
		launch(args);
	}

	@Override
	public void start(Stage s)
	{
		s.setTitle("Cambio de divisas");
		VBox vertElem = new VBox();
		Scene sc = new Scene(vertElem, 400, 200);
		TextField monedaOrigen = new TextField();
		TextField monedaDestino = new TextField();
		ComboBox<Divisa> cb_monedaOrigen = new ComboBox<Divisa>();
		ComboBox<Divisa> cb_monedaDestino = new ComboBox<Divisa>();
		Button bt_calculate = new Button("Calcular");
		
		monedaDestino.setEditable(false);
		monedaDestino.setDisable(false);
			
		cb_monedaDestino.getItems().addAll(l_divisas);
		cb_monedaOrigen.getItems().addAll(l_divisas);
		
		cb_monedaOrigen.getSelectionModel().selectFirst();
		cb_monedaDestino.getSelectionModel().selectFirst();
		
		HBox h_monedaOrigen = new HBox();
		h_monedaOrigen.getChildren().add(monedaOrigen);
		h_monedaOrigen.getChildren().add(cb_monedaOrigen);
		
		HBox h_monedaDestino = new HBox();
		h_monedaDestino.getChildren().add(monedaDestino);
		h_monedaDestino.getChildren().add(cb_monedaDestino);
		
		vertElem.setAlignment(Pos.CENTER);
		vertElem.getChildren().add(h_monedaOrigen);
		vertElem.getChildren().add(h_monedaDestino);
		vertElem.getChildren().add(bt_calculate);
		
		bt_calculate.setOnAction(event -> {
			double valorMonedaOrigen = Double.parseDouble(monedaOrigen.getText());
			
			monedaDestino.setText(String.valueOf(
					Divisa.fromTo(cb_monedaOrigen.getValue(),
					cb_monedaDestino.getValue(), valorMonedaOrigen)));
		});
		
		s.setScene(sc);
		s.show();
	}

}
