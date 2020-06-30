package application;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FenetreAffichee extends Stage {
	
	public FenetreAffichee() {
			
			///////////////////////////////////////////////////////////
			//Hack afin de diminuer le délais d'affichage des tooltip//
			///////////////////////////////////////////////////////////
			
			// https://stackoverflow.com/questions/26854301/how-to-control-the-javafx-tooltips-delay
			
				    try {
				        // Get the non public field "BEHAVIOR"
				        Field fieldBehavior = Tooltip.class.getDeclaredField("BEHAVIOR");
				        // Make the field accessible to be able to get and set its value
				        fieldBehavior.setAccessible(true);
				        // Get the value of the static field
				        Object objBehavior = fieldBehavior.get(null);
				        // Get the constructor of the private static inner class TooltipBehavior
				        Constructor<?> constructor = objBehavior.getClass().getDeclaredConstructor(
				            Duration.class, Duration.class, Duration.class, boolean.class
				        );
				        // Make the constructor accessible to be able to invoke it
				        constructor.setAccessible(true);
				        // Create a new instance of the private static inner class TooltipBehavior
				        Object tooltipBehavior = constructor.newInstance(
				            new Duration(10), new Duration(5000),
				            new Duration(200), true
				        );
				        // Set the new instance of TooltipBehavior
				        fieldBehavior.set(null, tooltipBehavior);
				    } catch (Exception e) {
				        throw new IllegalStateException(e);
				    }
				    
			this.setTitle("Accueil");
			this.setX(10);
			this.setY(10);
			this.setResizable(true);
			this.setMinHeight(700);
			this.setMinWidth(900);
			this.getIcons().add(new Image(getClass().getResourceAsStream("logo advercity.png")));
	}
	
	public void setLaScene(Scene scene) {
		this.setScene(scene);
	}
}
