/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threading;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author bhalepr
 */
public class ServiceThread extends Service<Integer> {

  private IntegerProperty max = new SimpleIntegerProperty(this, "max");
  private Scene scene;
  TextField txt = new TextField("Hall Bol");

  public Scene getScene() {
    return scene;
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public Integer getMax() {
    return max.getValue();
  }

  public void setMax(Integer max) {
    this.max.set(max);
  }

  public final IntegerProperty maxProperty() {
    return max;
  }
  

  @Override
  protected Task<Integer> createTask() {
    return new Task() {
      @Override
      protected Void call() {
        this.updateMessage("Waiting...");
        for (int i = 1; i <= max.get(); i++) {
          System.out.println("i=" + i);
          if (isCancelled() || i > 10) {
            break;
          }
          updateProgress(i, 10);
          updateMessage("Processing ...." + (i));
          try {
            Thread.sleep(500);
          } catch (InterruptedException ex) {
            Logger.getLogger(ProgressBarWithThread.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        updateMessage("done ...");
        
        printSceneInfo();
        System.out.println("Done");
        return null;
      }
    };
  }

  public void printSceneInfo() {
    System.out.println("Printing Scene Info");
    Parent p = scene.getRoot();
    System.out.println("Parent = " + p.getId() + "," + p.getClass());
    ObservableList<Node> list = ((HBox) p).getChildren();
    for (Node obj : list) {
      System.out.println("child = " + obj.getId() + "," + obj.getClass());
    }
  }
}
