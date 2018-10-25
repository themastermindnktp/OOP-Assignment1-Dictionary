package application;

import dictionary.Dictionary;
import dictionary.DictionaryManagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class SettingWindowController
{
    @FXML private Button importButton;
    @FXML private Button exportButton;

    public void importAction()
    {
        Alert confirmBox = new Alert(Alert.AlertType.NONE);
        confirmBox.setTitle("Xác Nhận");
        confirmBox.setContentText("Khi tải lại dữ liệu từ điển, những từ được thêm mới hoặc được thay đổi sẽ biến mất. Bạn có chắc chắn muốn tải lại dữ liệu hay không?");
        ButtonType yesButton = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmBox.getButtonTypes().addAll(yesButton, noButton);
        Optional<ButtonType> result = confirmBox.showAndWait();
        if (result.get() == yesButton)
        {
            Dictionary.clear();
            DictionaryManagement.importData();
        }
        confirmBox.close();
    }


    public void exportAction()
    {
        Alert confirmBox = new Alert(Alert.AlertType.NONE);
        confirmBox.setTitle("Xác Nhận");
        confirmBox.setContentText("Khi lưu lại dữ liệu từ điển, những từ bạn đã xóa sẽ biến mất. Bạn có chắc chắn muốn lưu lại dữ liệu hay không?");
        ButtonType yesButton = new ButtonType("Có", ButtonBar.ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
        confirmBox.getButtonTypes().addAll(yesButton, noButton);
        Optional<ButtonType> result = confirmBox.showAndWait();
        if (result.get() == yesButton) DictionaryManagement.exportData();
        confirmBox.close();
    }

}
