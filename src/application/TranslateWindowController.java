package application;

import extension.Translator;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TranslateWindowController
{
    @FXML private Button translateButton;

    @FXML private TextField lecture1;
    @FXML private TextArea lecture2;

    public void translate()
    {
        if (lecture1.getText().isEmpty()) return;

        try
        {
            lecture2.setText(Translator.Translate("en", "vi", lecture1.getText()));
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

    }

}
