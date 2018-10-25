package application;

import dictionary.Word;
import dictionary.Dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class EditWordWindowController
{
    @FXML private Button editWordButton;

    @FXML private TextField engWordText;
    @FXML private TextField pronunciationText;
    @FXML private TextField detailText;

    public void Alert(String string)
    {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Thông Báo");
        alert.setContentText(string);
        ButtonType button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().add(button);
        alert.showAndWait();
        alert.close();
    }

    public void editWord()
    {
        Stage stage = (Stage) editWordButton.getScene().getWindow();

        String engWord;
        String pronunciation;
        String detail;

        if (engWordText.getText().isEmpty())
            Alert("Bạn chưa nhập từ tiếng Anh.");
        else
        {
            engWord = Word.modify(engWordText.getText());

            if (pronunciationText.getText().isEmpty())
                Alert("Bạn chưa nhập phiên âm của từ.");
            else
            {
                pronunciation = pronunciationText.getText();

                if (detailText.getText().isEmpty())
                    Alert("Bạn chưa nhập thông tin của từ.");
                else {
                    detail = detailText.getText();


                    Alert confirmBox = new Alert(Alert.AlertType.NONE);
                    confirmBox.setTitle("Xác Nhận");
                    if (Dictionary.find(engWord) != null)
                        confirmBox.setContentText("Bạn có chắc chắn muốn chỉnh sửa thông tin của từ này không?");
                    else
                        confirmBox.setContentText("Từ này chưa tồn tại trong từ điển, bạn có muốn thêm từ này vào từ điển không?");

                    ButtonType yesButton = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
                    ButtonType noButton = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
                    confirmBox.getButtonTypes().addAll(yesButton, noButton);
                    Optional<ButtonType> result = confirmBox.showAndWait();


                    if (result.get() == yesButton)
                    {
                        Dictionary.insert(new Word(engWord, pronunciation, detail));
                        stage.close();
                    }
                    confirmBox.close();
                }
            }
        }
    }

}
