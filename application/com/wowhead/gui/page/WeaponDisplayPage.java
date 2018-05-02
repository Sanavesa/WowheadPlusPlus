// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Weapon;
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

public class WeaponDisplayPage extends Page
{
	private TopView topView;
	private Label itemName, itemLevel, itemType, itemAttackSpeed, itemAttackDamage,
			itemStamina, itemStrength, itemSpirit, itemIntellect, itemAgility;
	private Text itemDescription;

	public WeaponDisplayPage(PageManager pageManager)
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
		itemAttackSpeed = new Label("");
		itemAttackDamage = new Label("");
		itemStamina = new Label("");
		itemStrength = new Label("");
		itemSpirit = new Label("");
		itemIntellect = new Label("");
		itemAgility = new Label("");

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Weapon weapon)
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
		
		itemName.setText(weapon.getName());
		itemName.setStyle("-fx-font-size: 24px;");
		itemName.setTextFill(weapon.getRarity().getColor());
		layout.addRow(rowIndex++, itemName);
		
		itemType.setText(weapon.getWeaponType().toString());
		itemType.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemType);
		
		itemAttackDamage.setText(weapon.getAttackDamage() + " Damage");
		itemAttackDamage.setStyle("-fx-font-size: 16px;");
		itemAttackSpeed.setText("Speed " + weapon.getAttackSpeed());
		itemAttackSpeed.setStyle("-fx-font-size: 16px;");
		itemAttackSpeed.setTextAlignment(TextAlignment.RIGHT);
		GridPane.setHalignment(itemAttackDamage, HPos.LEFT);
		GridPane.setHalignment(itemAttackSpeed, HPos.RIGHT);
		layout.addRow(rowIndex++, itemAttackDamage, itemAttackSpeed);
		
		if(weapon.getStamina() != 0)
		{
			itemStamina.setText("+" + weapon.getStamina() + " Stamina");
			itemStamina.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStamina);
		}
		
		if(weapon.getStrength() != 0)
		{
			itemStrength.setText("+" + weapon.getStrength() + " Strength");
			itemStrength.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemStrength);
		}
		
		if(weapon.getSpirit() != 0)
		{
			itemSpirit.setText("+" + weapon.getSpirit() + " Spirit");
			itemSpirit.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemSpirit);
		}
		
		if(weapon.getIntellect() != 0)
		{
			itemIntellect.setText("+" + weapon.getIntellect() + " Intellect");
			itemIntellect.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemIntellect);
		}
		
		if(weapon.getAgility() != 0)
		{
			itemAgility.setText("+" + weapon.getAgility() + " Agility");
			itemAgility.setStyle("-fx-font-size: 16px;");
			layout.addRow(rowIndex++, itemAgility);
		}
		
		if(weapon.getDescription().length() > 0)
		{
			itemDescription.setText(weapon.getDescription());
			itemDescription.setStyle("-fx-font-size: 16px;");
			itemDescription.setFill(Color.web("#11FF11"));
			layout.addRow(rowIndex++, itemDescription);
		}
		
		itemLevel.setText("Requires Level " + weapon.getLevelReq());
		itemLevel.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, itemLevel);
		
		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}