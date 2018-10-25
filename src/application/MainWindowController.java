package application;

import dictionary.Dictionary;
import dictionary.DictionaryManagement;
import dictionary.Word;

import extension.Speaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable
{
    @FXML private Button settingButton;
    @FXML private Button addWordButton;
    @FXML private Button editWordButton;
    @FXML private Button deleteWordButton;
    @FXML private Button translateButton;
    @FXML private Button searchButton;
    @FXML private Button speakButton;

    @FXML private ListView<String> wordList;

    @FXML private TextArea wordDetail;

    @FXML private TextField searchBox;

    @FXML private Text englishText;
    @FXML private Text pronunciationText;

    private String currentWord;

    public void setDefault()
    {
        searchButton.setDisable(true);
        speakButton.setDisable(true);
        editWordButton.setDisable(true);
        deleteWordButton.setDisable(true);
        searchBox.setText("");
        englishText.setText("");
        pronunciationText.setText("");
        wordDetail.setText("");
        wordList.setItems(convertToObservableList(Dictionary.all()));
    }

    public void setting()
    {
        settingButton.setOnAction
        (
            event ->
            {
                try
                {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("SettingWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("image\\WindowIcon.png"));
                    stage.setTitle("Cài Đặt");
                    stage.show();
                    setDefault();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        );
    }

    public ObservableList<String> convertToObservableList(ArrayList<Word> words)
    {
        ObservableList<String> observables = FXCollections.observableArrayList();
        for(Word word : words)
            observables.add(word.getEngWord());
        return observables;
    }

    public void searchWord()
    {
        searchButton.setOnAction
        (
            event -> wordList.getSelectionModel().selectFirst()
        );
    }

    public void pronounceWord()
    {
        speakButton.setOnAction
        (
                event -> Speaker.speak(englishText.getText())
        );
    }

    public void displayDetail()
    {
        englishText.setText("");
        wordDetail.setText("");

        wordList.getSelectionModel().selectedItemProperty().addListener
        (
            (observable, oldValue, newValue) ->
            {
                String target = newValue;
                currentWord = target;
                Word word = Dictionary.find(target);
                englishText.setText(word.getEngWord());
                pronunciationText.setText(word.getPronunciation());
                wordDetail.setText(word.getDetail());
                speakButton.setDisable(false);
                editWordButton.setDisable(false);
                deleteWordButton.setDisable(false);
            }
        );
    }

    public void interact()
    {
        searchWord();
        pronounceWord();
        displayDetail();

        speakButton.setDisable(true);
        editWordButton.setDisable(true);
        deleteWordButton.setDisable(true);

        searchBox.textProperty().addListener
        (
            (observable, oldValue, newValue) ->
            {
                if (searchBox.getText().isEmpty())
                {
                    searchButton.setDisable(true);
                    wordList.setItems(convertToObservableList(Dictionary.all()));
                }
                else
                {
                    String target = searchBox.getText();

                    target = Word.modify(target);

                    ObservableList<String> observables = convertToObservableList(Dictionary.searchByPrefix(target));

                    wordList.setItems(observables);

                    searchButton.setDisable((Dictionary.find(target) == null));
                }
            }
        );
    }

    public void addWord()
    {
        addWordButton.setOnAction
        (
            event ->
            {
                try
                {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("AddWordWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("image\\WindowIcon.png"));
                    stage.setTitle("Thêm Từ Mới");
                    stage.show();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        );
    }

    public void deleteWord()
    {
        deleteWordButton.setOnAction
        (
            event ->
            {
                Alert confirmBox = new Alert(Alert.AlertType.NONE);
                confirmBox.setTitle("Xác Nhận");
                confirmBox.setContentText("Bạn có chắc chắn muốn xóa từ này khỏi từ điểnkhông?");
                ButtonType yesButton = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
                ButtonType noButton = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
                confirmBox.getButtonTypes().addAll(yesButton, noButton);
                Optional<ButtonType> result = confirmBox.showAndWait();


                if (result.get() == yesButton)
                {
                    Dictionary.delete(currentWord);
                    setDefault();
                }
                else confirmBox.close();
            }
        );
    }

    public void editWord()
    {
        editWordButton.setOnAction
        (
            event ->
            {
                try
                {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("EditWordWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("image\\WindowIcon.png"));
                    stage.setTitle("Chỉnh Sửa Từ");
                    stage.show();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        );
    }

    public void translate()
    {
        translateButton.setOnAction
        (
            event ->
            {
                try
                {
                    Parent parent = FXMLLoader.load(this.getClass().getResource("TranslateWindow.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("image\\WindowIcon.png"));
                    stage.setTitle("Dịch Văn Bản");
                    stage.show();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        DictionaryManagement.importData();
        wordList.setItems(convertToObservableList(Dictionary.all()));
        interact();
        setting();
        addWord();
        editWord();
        deleteWord();
        translate();
    }

}
