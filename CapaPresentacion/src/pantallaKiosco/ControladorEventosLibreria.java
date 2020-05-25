
package pantallaKiosco;

import entities.Linea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControladorEventosLibreria implements Initializable {
    
    //ETIQUETAS
    
    @FXML
    private Label tituloLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Label numeroPrestamoLabel;
    @FXML
    private Label seleccionarLibroLabel;
    @FXML
    private Label cantidadLabel;
    @FXML
    private Label lineasPrestamoLabel;
    @FXML
    private Label totalPrestamoLabel;
    @FXML
    private Label cantidadMonedasLabel;
    @FXML
    private Label denominacionLabel;
    @FXML
    private Label saldoDisponibleLabel;
    @FXML
    private Label vueltasLabel;
    
    //BOTONES
    
    @FXML
    private Button nuevoPrestamoButton;
    @FXML
    private Button agregarLineaButton;
    @FXML
    private Button agregarMonedasButton;
    @FXML
    private Button terminarPrestamoButton;
    @FXML
    private Button generarReporteButton;
    
    //CHOICE BOX
    
    @FXML
    private ChoiceBox<String> seleccionarLibroChoiceBox;
    @FXML
    private ChoiceBox<String> seleccionarDenominacionChoiceBox;
    
    //TEXT FIELDS
    
    @FXML
    private TextField cantidadLibroTextField;
    @FXML
    private TextField cantidadMonedasTextField;
    
    //PARA LA TABLA
    
    @FXML
    private TableView<Linea> tablaTableView;
    @FXML
    private TableColumn<Linea, String> libroTableColumn;
    @FXML
    private TableColumn<Linea, String> cantidadTableColumn;
    @FXML
    private TableColumn<Linea, String> precioLibroTableColumn;
    @FXML
    private TableColumn<Linea, String> subTotalTableColumn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
