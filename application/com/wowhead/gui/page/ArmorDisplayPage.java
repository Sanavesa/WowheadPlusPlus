// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Armor;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ArmorDisplayPage extends Page
{
	private TopView topView;
	private Label itemName, itemLevel, itemType, itemEquipmentSlot, itemStamina, itemStrength,
			itemSpirit, itemIntellect, itemAgility;
	private Text itemDescription;
	
	public ArmorDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		itemName = new Label("");
		itemDescription = new Text("");
		itemDescription.setWrappingWidth(390);
		itemLevel = new Label("");
		itemType = new Label("");
		itemEquipmentSlot = new Label("");
		itemStamina = new Label("");
		itemStrength = new Label("");
		itemSpirit = new Label("");
		itemIntellect = new Label("");
		itemAgility = new Label("");

		root.setStyle("-fx-background: #000000");
		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Armor armor)
	{
		GridPane layout = new GridPane();
		layout.setMaxWidth(400);
		ColumnConstraints col1 = new ColumnConstraints(300);
		col1.setHalignment(HPos.LEFT);
		layout.getColumnConstraints().add(col1);
		layout.setPadding(new Insets(10, 5, 10, 5));
		layout.setStyle("-fx-border-width: 2px; -fx-border-radius: 12px; -fx-border-color: #888888;");
		root.setCenter(layout);

		int rowIndex = 0;

		itemName.setText(armor.getName());
		itemName.setStyle("-fx-font-size: 24px;");
		itemName.setTextFill(armor.getRarity().getColor());
		layout.addRow(rowIndex++, itemName);

		itemType.setText(armor.getArmorType().toString());
		itemType.setStyle("-fx-font-size: 16px;");
		itemEquipmentSlot.setText(armor.getEquipmentSlot().toString());
		itemEquipmentSlot.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemType, itemEquipmentSlot);
		itemEquipmentSlot.setTextAlignment(TextAlignment.RIGHT);
		GridPane.setHalignment(itemType, HPos.LEFT);
		GridPane.setHalignment(itemEquipmentSlot, HPos.RIGHT);

		if (armor.getStamina() != 0)
		{
			itemStamina.setText("+" + armor.getStamina() + " Stamina");
			itemStamina.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStamina);
		}

		if (armor.getStrength() != 0)
		{
			itemStrength.setText("+" + armor.getStrength() + " Strength");
			itemStrength.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStrength);
		}

		if (armor.getSpirit() != 0)
		{
			itemSpirit.setText("+" + armor.getSpirit() + " Spirit");
			itemSpirit.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemSpirit);
		}

		if (armor.getIntellect() != 0)
		{
			itemIntellect.setText("+" + armor.getIntellect() + " Intellect");
			itemIntellect.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemIntellect);
		}

		if (armor.getAgility() != 0)
		{
			itemAgility.setText("+" + armor.getAgility() + " Agility");
			itemAgility.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemAgility);
		}

		if (armor.getDescription().length() > 0)
		{
			itemDescription.setText(armor.getDescription());
			itemDescription.setStyle("-fx-font-size: 16px;");
			itemDescription.setFill(Color.web("#11FF11"));
			layout.addRow(rowIndex++, itemDescription);
		}

		itemLevel.setText("Requires Level " + armor.getLevelReq());
		itemLevel.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemLevel);

		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}