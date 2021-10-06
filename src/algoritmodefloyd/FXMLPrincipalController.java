/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmodefloyd;

import algoritmodefloyd.extra.Grafo;
import algoritmodefloyd.extra.Tabela;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lucas
 */
public class FXMLPrincipalController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<Tabela> tvMatriz;
    @FXML
    private JFXButton bAbrir;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXComboBox<String> cbOrigem;
    @FXML
    private JFXComboBox<String> cbDestino;
    @FXML
    private JFXTextField tfCusto;
    @FXML
    private JFXTextArea taPercurso;
    @FXML
    private TableColumn<?, String> Vertice;
    @FXML
    private TableColumn<?, String> CA;
    @FXML
    private TableColumn<?, String> CB;
    @FXML
    private TableColumn<?, String> CC;
    @FXML
    private TableColumn<?, String> CD;
    @FXML
    private TableColumn<?, String> CE;
    @FXML
    private TableColumn<?, String> CF;
    @FXML
    private TableColumn<?, String> CG;
    @FXML
    private TableColumn<?, String> CH;
    @FXML
    private TableColumn<?, String> CI;
    @FXML
    private TableColumn<?, String> CJ;
    @FXML
    private ToggleGroup gp;
    @FXML
    private HBox hMatriz;
    @FXML
    private VBox vPainel;
    private Grafo g;
    private static Tabela t[];
    @FXML
    private JFXRadioButton rbDistancia;
    @FXML
    private JFXRadioButton rbSolucao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Vertice.setCellValueFactory(new PropertyValueFactory<>("id"));
        CA.setCellValueFactory(new PropertyValueFactory<>("c0"));
        CB.setCellValueFactory(new PropertyValueFactory<>("c1"));
        CC.setCellValueFactory(new PropertyValueFactory<>("c2"));
        CD.setCellValueFactory(new PropertyValueFactory<>("c3"));
        CE.setCellValueFactory(new PropertyValueFactory<>("c4"));
        CF.setCellValueFactory(new PropertyValueFactory<>("c5"));
        CG.setCellValueFactory(new PropertyValueFactory<>("c6"));
        CH.setCellValueFactory(new PropertyValueFactory<>("c7"));
        CI.setCellValueFactory(new PropertyValueFactory<>("c8"));
        CJ.setCellValueFactory(new PropertyValueFactory<>("c9"));
        t = new Tabela[10];
    }    
    
    public void habilitar(int pos)
    {
        if (pos == 0) {
            Vertice.setVisible(true); 
        } else if (pos == 1) {
            CA.setVisible(true);
        } else if (pos == 2) {
            CB.setVisible(true);
        } else if (pos == 3) {
            CC.setVisible(true);
        } else if (pos == 4) {
            CD.setVisible(true);
        } else if (pos == 5) {
            CE.setVisible(true);
        } else if (pos == 6) {
            CF.setVisible(true);
        } else if (pos == 7) {
            CG.setVisible(true);
        } else if (pos == 8) {
            CH.setVisible(true);
        } else if (pos == 9) {
            CI.setVisible(true);
        }
        else if (pos == 10) {
            CJ.setVisible(true);
        }
    }
    
    @FXML
    private void clkAbrir(ActionEvent event) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

    
        FileFilter ff = new FileNameExtensionFilter("Arquivo Texto","txt");
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(ff);
        
        fc.showOpenDialog(null);
        if (fc != null)
        {
            g = Grafo.leGrafo(fc.getSelectedFile().toString());
            if(g==null)
            {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Erro ao abrir o arquivo:\n" + Grafo.getErro());
                a.showAndWait();
            }
            else
            {
                tfNome.setText(fc.getSelectedFile().getName());
                hMatriz.setDisable(false);
                vPainel.setDisable(false);
                tvMatriz.setDisable(false);
                tfNome.setDisable(false);
                g.floyd();
                String [] l = new String[g.getQtdeV()];
                tvMatriz.getItems().clear();
                int [][] md = g.getMatrizDistancia();
                
                for(int i=0; i<g.getQtdeV();i++)//Carrega Tabela
                {
                    char c = (char) ((char)65+i);
                    
                   t[i] = new Tabela(c+"");
                   l[i] = c+""; 
                   for(int j=0; j<g.getQtdeV();j++) 
                   {
                       if(i<g.getQtdeV() && j<g.getQtdeV())
                       {
                            int s = md[i][j];
                            t[i].insere(j, s+"");
                       }

                   }
                   tvMatriz.getItems().add(t[i]);
                }
                for(int i=0;i<g.getQtdeV()+1;i++)
                    habilitar(i);
                
                cbOrigem.setItems(FXCollections.observableArrayList(l));
                cbOrigem.getSelectionModel().selectFirst();
                cbDestino.setItems(FXCollections.observableArrayList(l));
                cbDestino.getSelectionModel().selectFirst();
                //tfCusto.setText(0+"");
                calcula();
            }
        }
    }

    @FXML
    private void clkDistancia(ActionEvent event) {
        tvMatriz.getItems().clear();
        int [][] md = g.getMatrizDistancia();
                
                for(int i=0; i<g.getQtdeV();i++)
                {
                    char c = (char) ((char)65+i);
                    
                   t[i] = new Tabela(c+"");
                   for(int j=0; j<g.getQtdeV();j++) 
                   {
                        int s = md[i][j];
                        t[i].insere(j, s+"");
                   }
                   tvMatriz.getItems().add(t[i]);
                }
                for(int i=0;i<g.getQtdeV()+1;i++)
                    habilitar(i);
    }

    @FXML
    private void clkSolucao(ActionEvent event) {
        tvMatriz.getItems().clear();
        int [][] ms = g.getMatrizSolucao();
                
                for(int i=0; i<g.getQtdeV();i++)
                {
                    char c = (char) ((char)65+i);
                    
                   t[i] = new Tabela(c+"");
                   for(int j=0; j<g.getQtdeV();j++) 
                   {
                        int s = ms[i][j];
                        t[i].insere(j, s+"");

                   }
                   tvMatriz.getItems().add(t[i]);
                }
                for(int i=0;i<g.getQtdeV()+1;i++)
                    habilitar(i);
    }
    
    private void calcula()
    {
        int lin,col;
        lin = cbOrigem.getValue().charAt(0)-65;
        col = cbDestino.getValue().charAt(0)-65;
        int md[][] = g.getMatrizDistancia();
        int ms[][] = g.getMatrizSolucao();
        tfCusto.setText(md[lin][col]+"");
        String s = "";
       /* while(ms[lin][col]!=0)
        {
            s+=ms[lin][col];
            
        }*/
       if(Integer.parseInt(tfCusto.getText())!=0)
       {
             ArrayList<Integer> caminho = g.getCaminho(lin, col);
            for(int i=0;i <caminho.size();i++)
            {

                s+=caminho.get(i)+"";
                if(i+1<caminho.size())
                    s+="->";
            }         
            //s+=col+"";
            taPercurso.setText(s);     
       }
       else
           taPercurso.setText(""); 
    }
    
    @FXML
    private void clkChanged(Event event) {
         calcula();
    }
    
}
