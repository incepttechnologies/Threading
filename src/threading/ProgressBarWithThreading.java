package threading;

/**
 *
 * @author bhalepr
 */
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgressBarWithThreading {

  public void start(Stage stage) {
    Group root = new Group();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Progress Controls -- ");



    final Slider slider = new Slider();
    slider.setMin(0);
    slider.setMax(50);

    final ProgressBar pb = new ProgressBar(0);
    final ProgressIndicator pi = new ProgressIndicator(0);
    //
    Task<Void> task = new Task<Void>() {
      @Override
      protected Void call() throws Exception {
        System.out.println("inside call " );
        int iterations = 0;
        for (iterations = 1; iterations < 10000; iterations++) {
          
          System.out.println("Iteration " + iterations);
          updateProgress(iterations/50, 10000);
          Thread.sleep(1000);
          if(iterations > 11) break;
          //System.out.println("progress property = " +  progressProperty() );
        }
        return null;
      }

    };
    //
    System.out.println("outside declation " );
    pb.progressProperty().bind(task.progressProperty());
    pi.progressProperty().bind(task.progressProperty());

    final HBox hb = new HBox();
    hb.setSpacing(5);
    hb.setAlignment(Pos.CENTER);
    hb.getChildren().addAll(slider, pb, pi);
    scene.setRoot(hb);
    
    stage.show();
    new Thread(task).start();
//    try {
//      Thread.sleep(10000);
//      task.cancel();
//    } catch (InterruptedException ex) {
//      Logger.getLogger(ProgressBarWithThreading.class.getName()).log(Level.SEVERE, null, ex);
//    }
  }

}
