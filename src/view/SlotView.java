package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class SlotView extends BorderPane
{

	public SlotView()
	{
		Rectangle rectangle = new Rectangle(100, 100);
		this.setCenter(rectangle);
	}
}
