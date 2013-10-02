package threading;

/**
 *
 * @author bhalepr
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgressBarWithThread {

  static final int max = 100;

  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 200);
    stage.setScene(scene);
    stage.setTitle("ProgressBarWithThread: Progress Controls -- ");

    //
    Task task = new Task<Void>() {
      @Override
      public Void call() {
        
        this.updateMessage("Waiting...");
        for (int i = 1; i <= max; i++) {
          System.out.println("i=" + i);
          if (isCancelled() || i>20) {
            break;
          }
          updateProgress(i, 20);
          updateMessage("Processing ...." + (i/2.0));
          try {
            Thread.sleep(500);
          } catch (InterruptedException ex) {
            Logger.getLogger(ProgressBarWithThread.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        updateMessage("done ...");
        return null;
      }
      
    };
    ProgressBar bar = new ProgressBar();
    TextField txt = new TextField();
    bar.progressProperty().bind(task.progressProperty());
    txt.textProperty().bind(task.messageProperty());
    txt.setDisable(true);
    Thread t = new Thread(task);
    t.setDaemon(true);
    System.out.println("ProgressBarWithThread: outside thread declation ");
    t.start();



    //



    final HBox hb = new HBox();
    hb.setSpacing(5);
    hb.setAlignment(Pos.CENTER);
    hb.getChildren().addAll(bar, txt);
    scene.setRoot(hb);

    stage.show();

//    try {
//      Thread.sleep(10000);
//      task.cancel();
//    } catch (InterruptedException ex) {
//      Logger.getLogger(ProgressBarWithThreading.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }
}
