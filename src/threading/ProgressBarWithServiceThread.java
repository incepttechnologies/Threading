package threading;

/**
 *
 * @author bhalepr
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ProgressBarWithServiceThread {

  static final int max = 100;
  Scene scene;
  
  public void start(Stage stage) {
    Group root = new Group();
    scene = new Scene(root, 400, 200);
    stage.setScene(scene);
    stage.setTitle("ProgressBarWithServiceThread: Progress Controls -- ");

    ProgressBar bar = new ProgressBar();
    bar.setId("pBarId");
    TextField txt = new TextField();
    txt.setId("txtFldId");
    Button addButton = new Button("Add");
    addButton.setTooltip(new Tooltip("Add new TextField"));
    addButton.setId("addButId");
    
    ServiceThread task = new ServiceThread();
    task.setMax(10);
    task.setScene(scene);
    //task.setT(this);
    
    bar.progressProperty().bind(task.progressProperty());
    txt.textProperty().bind(task.runningProperty().asString() );
    txt.setDisable(true);
    System.out.println("ProgressBarWithServiceThread: outside thread declation ");

    final HBox hb = new HBox();
    hb.setSpacing(5);
    hb.setAlignment(Pos.CENTER);
    hb.getChildren().addAll(bar, txt, addButton);
    scene.setRoot(hb);
    task.start();
    addButton.disableProperty().bind(task.stateProperty().isEqualTo(Worker.State.RUNNING));//task.runningProperty());
    stage.show();
//    try {
//      Thread.sleep(500);
//    } catch (InterruptedException ex) {
//      Logger.getLogger(ProgressBarWithServiceThread.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    System.out.println("task.stateProperty().get()=" + 
//            task.stateProperty().get());
//    while(task.stateProperty().isEqualTo(Worker.State.RUNNING).get())  {
//      try {
//        System.out.println("Thread is Running ="+ task.getWorkDone() + ":" + task.runningProperty().asString().get()
//                +":" + txt.getText());
//        Thread.sleep(1000);
//      } catch (InterruptedException ex) {
//        Logger.getLogger(ProgressBarWithServiceThread.class.getName()).log(Level.SEVERE, null, ex);
//      }
//    }
    addToScene(task.txt);
  }
  
  public void addToScene(Node node)  {
    System.out.println("Thread is finished Running");
     //((HBox)(scene.getRoot())).getChildren().add(txt);
    ((HBox)(scene.getRoot())).getChildren().add(node);
  }
}
