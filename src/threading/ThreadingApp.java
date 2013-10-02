/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author bhalepr
 */
public class ThreadingApp extends Application {

  @Override
  public void start(Stage stage) {
    //showSimpleProgressBar(stage);
    showProgressBarWithThread(stage);
  }


public static void main(String[] args) {
    launch(args);
  }

  private void showSimpleProgressBar(Stage stage) {
    SimpleProgressBar sp = new SimpleProgressBar();
    sp.start(stage);
  }
  
  private void showProgressBarWithThread(Stage stage) {
    ProgressBarWithServiceThread sp = new ProgressBarWithServiceThread();
    sp.start(stage);
  }
}
