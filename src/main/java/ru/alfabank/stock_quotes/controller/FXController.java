package ru.alfabank.stock_quotes.controller;

import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alfabank.stock_quotes.service.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

@Component
@FxmlView("sample.fxml")
public class FXController {

    private final Service service;

    private Map<String, String> tickers;

    private final String base = "USD";
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    public FXController(Service service) {
        this.service = service;
    }

    @FXML
    private ComboBox<String> secondTicker;
    @FXML
    private TextField fullNameOfFirstTicker;
    @FXML
    private Button getRate;
    @FXML
    private Button getAllRates;
    @FXML
    private HBox calendarHBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label dateLbl;
    @FXML
    private Button getAllTickers;
    @FXML
    private Label stocks;
    @FXML
    private HBox ratesHBox1;
    @FXML
    private HBox rateHBox;
    @FXML
    private Label infoLbl;


    public void initialize() {
        datePicker.setDayCellFactory(getDayCellFactory());
        datePicker.setValue(LocalDate.now());
        selectDate();
    }

    public void selectDate() {
        dateLbl.setText(String.format("Курс USD на %s", datePicker.getValue().format(dateFormatter)));
    }

    public void getRate(ActionEvent actionEvent) {
        String ticker1 = secondTicker.getValue();
        String selectedDate = datePicker.getValue().toString();
        JsonNode node;
        if (Objects.equals(datePicker.getValue(), LocalDate.now())) {
            node = service.getRate(base, ticker1);
        } else {
            node = service.getRate(selectedDate, base, ticker1);
        }
        Iterator<Map.Entry<String, JsonNode>> fieldNames = node.get("rates").fields();
        String secondTicketName = null;
        BigDecimal secondTicketPrice = null;
        while (fieldNames.hasNext()) {
            Map.Entry<String, JsonNode> e = fieldNames.next();
            secondTicketName = e.getKey();
            secondTicketPrice = BigDecimal.valueOf(e.getValue().asDouble());
        }
        stocks.setText(String.format(("%s -> %s : %s"), base, secondTicketName, secondTicketPrice));
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker1) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isAfter(LocalDate.now()) || item.isBefore(LocalDate.of(1999, 1, 1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #D3D3D3");
                        }
                    }
                };
            }
        };
    }

    public void selectSecondTicker() {
        fullNameOfFirstTicker.setText(tickers.get(secondTicker.getValue()));
    }

    public void getAllTickers(ActionEvent actionEvent) {
        tickers = service.getCurrencies();
        if (tickers == null || tickers.size() == 0) {
            System.out.println("Не удалось загрузить список тикеров.");
            return;
        }
        infoLbl.setText(String.format("Всего загружено %s тикеров", tickers.size()));

        ObservableList<String> listSQ = FXCollections.observableArrayList(tickers.keySet());
        secondTicker.setItems(listSQ);
        secondTicker.getSelectionModel().select(0);

        selectSecondTicker();
        calendarHBox.setVisible(true);
        infoLbl.setVisible(true);
        ratesHBox1.setVisible(true);
        rateHBox.setVisible(true);
    }

}
