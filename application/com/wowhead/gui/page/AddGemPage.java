// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.Database;
import com.wowhead.database.constants.GemType;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.tables.Gem;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AddGemPage extends Page
{
	private TopView topView;
	private TextField itemName, itemLevel, itemDescription,
			itemStamina, itemStrength, itemSpirit, itemIntellect, itemAgility;
	private ComboBox<ItemRarity> itemRarity;
	private ComboBox<GemType> gemType;
	private GridPane gridPane;
	private Button btnAdd, btnCancel;

	public AddGemPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		gridPane = new GridPane();
		
		topView = new TopView(pageManager);
		itemName = new TextField("");
		itemDescription = new TextField("");
		itemLevel = new TextField("");
		itemStamina = new TextField("");
		itemStrength = new TextField("");
		itemSpirit = new TextField("");
		itemIntellect = new TextField("");
		itemAgility = new TextField("");
		
		itemRarity = new ComboBox<>();
		itemRarity.setPrefWidth(160);
		gemType = new ComboBox<>();
		gemType.setPrefWidth(160);
		itemRarity.getItems().addAll(FXCollections.observableArrayList(ItemRarity.values()));
		gemType.getItems().addAll(FXCollections.observableArrayList(GemType.values()));
		
		gridPane.setPrefWidth(500);
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		
		btnAdd = new Button("Add");
		btnAdd.setOnAction(e ->
		{
			Gem gem = new Gem(
					0, 
					itemName.getText(),
					itemRarity.getValue(),
					itemDescription.getText(),
					Integer.parseInt(itemLevel.getText()),
					gemType.getValue(),
					Integer.parseInt(itemStamina.getText()),
					Integer.parseInt(itemStrength.getText()),
					Integer.parseInt(itemSpirit.getText()),
					Integer.parseInt(itemIntellect.getText()),
					Integer.parseInt(itemAgility.getText()));
			
			Database.getInstance().addGem(gem);
			resetToDefault();
			pageManager.addPage(HomePage.class);
		});
		
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e ->
		{
			resetToDefault();
			pageManager.goBackOnePage();
		});
		
		gridPane.addRow(0, new Label("Name"), itemName);
		gridPane.addRow(1, new Label("Level"), itemLevel);
		gridPane.addRow(2, new Label("Description"), itemDescription);
		gridPane.addRow(3, new Label("Gem Type"), gemType);
		gridPane.addRow(4, new Label("Rarity"), itemRarity);
		gridPane.addRow(5, new Label("Stamina"), itemStamina);
		gridPane.addRow(6, new Label("Strength"), itemStrength);
		gridPane.addRow(7, new Label("Spirit"), itemSpirit);
		gridPane.addRow(8, new Label("Intellect"), itemIntellect);
		gridPane.addRow(9, new Label("Agility"), itemAgility);
		gridPane.addRow(11, btnAdd, btnCancel);

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
		
		Region r1 = new Region();
		Region r2 = new Region();
		Region r3 = new Region();
		Region r4 = new Region();
		HBox.setHgrow(r1, Priority.ALWAYS);
		HBox.setHgrow(r2, Priority.ALWAYS);
		VBox.setVgrow(r3, Priority.ALWAYS);
		VBox.setVgrow(r4, Priority.ALWAYS);
		HBox centerH = new HBox(r1, gridPane, r2);
		VBox centered = new VBox(r3, centerH, r4);
		root.setCenter(centered);
	}

	public void resetToDefault()
	{
		itemName.setText("");
		itemDescription.setText("");
		itemLevel.setText("");
		itemStamina.setText("");
		itemStrength.setText("");
		itemSpirit.setText("");
		itemIntellect.setText("");
		itemAgility.setText("");
		itemRarity.setValue(ItemRarity.UNCOMMON);
		gemType.setValue(GemType.RED);
	}
}