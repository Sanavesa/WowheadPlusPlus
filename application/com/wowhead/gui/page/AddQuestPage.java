// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.Database;
import com.wowhead.database.constants.ItemRarity;
import com.wowhead.database.constants.WeaponType;
import com.wowhead.database.tables.Weapon;
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

public class AddQuestPage extends Page
{
	private TopView topView;
	private TextField itemName, itemLevel, itemDescription, itemAttackSpeed, itemAttackDamage,
			itemStamina, itemStrength, itemSpirit, itemIntellect, itemAgility;
	private ComboBox<ItemRarity> itemRarity;
	private ComboBox<WeaponType> weaponType;
	private GridPane gridPane;
	private Button btnAdd, btnCancel;

	public AddQuestPage(PageManager pageManager)
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
		itemAttackSpeed = new TextField("");
		itemAttackDamage = new TextField("");
		itemStamina = new TextField("");
		itemStrength = new TextField("");
		itemSpirit = new TextField("");
		itemIntellect = new TextField("");
		itemAgility = new TextField("");
		
		itemRarity = new ComboBox<>();
		itemRarity.setPrefWidth(160);
		weaponType = new ComboBox<>();
		weaponType.setPrefWidth(160);
		itemRarity.getItems().addAll(FXCollections.observableArrayList(ItemRarity.values()));
		weaponType.getItems().addAll(FXCollections.observableArrayList(WeaponType.values()));
		
		gridPane.setPrefWidth(500);
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		
		btnAdd = new Button("Add");
		btnAdd.setOnAction(e ->
		{
			Weapon weapon = new Weapon(
					0, 
					itemName.getText(),
					itemRarity.getValue(),
					itemDescription.getText(),
					Integer.parseInt(itemLevel.getText()),
					weaponType.getValue(),
					Double.parseDouble(itemAttackSpeed.getText()),
					Double.parseDouble(itemAttackDamage.getText()),
					Integer.parseInt(itemStamina.getText()),
					Integer.parseInt(itemStrength.getText()),
					Integer.parseInt(itemSpirit.getText()),
					Integer.parseInt(itemIntellect.getText()),
					Integer.parseInt(itemAgility.getText()));
			
			Database.getInstance().addWeapon(weapon);
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
		gridPane.addRow(3, new Label("Weapon Type"), weaponType);
		gridPane.addRow(4, new Label("Rarity"), itemRarity);
		gridPane.addRow(5, new Label("Attack Damage"), itemAttackDamage);
		gridPane.addRow(6, new Label("Attack Speed"), itemAttackSpeed);
		gridPane.addRow(7, new Label("Stamina"), itemStamina);
		gridPane.addRow(8, new Label("Strength"), itemStrength);
		gridPane.addRow(9, new Label("Spirit"), itemSpirit);
		gridPane.addRow(10, new Label("Intellect"), itemIntellect);
		gridPane.addRow(11, new Label("Agility"), itemAgility);
		gridPane.addRow(13, btnAdd, btnCancel);

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

//	public void loadDisplay(Weapon weapon)
//	{
//		GridPane layout = new GridPane();
//		layout.setMaxWidth(400);
//		ColumnConstraints col1 = new ColumnConstraints(300);
//		col1.setHalignment(HPos.LEFT);
//		layout.getColumnConstraints().add(col1);
//		layout.setPadding(new Insets(10, 5, 10, 5));
//		layout.setStyle("-fx-border-width: 2px; -fx-border-radius: 12px; -fx-border-color: #888888;");
//		root.setCenter(layout);
//		
//		int rowIndex = 0;
//		
//		itemName.setText(weapon.getName());
//		itemName.setStyle("-fx-font-size: 24px;");
//		itemName.setTextFill(weapon.getRarity().getColor());
//		layout.addRow(rowIndex++, itemName);
//		
//		itemType.setText(weapon.getWeaponType().toString());
//		itemType.setStyle("-fx-font-size: 16px;");
//		layout.addRow(rowIndex++, itemType);
//		
//		itemAttackDamage.setText(weapon.getAttackDamage() + " Damage");
//		itemAttackDamage.setStyle("-fx-font-size: 16px;");
//		itemAttackSpeed.setText("Speed " + weapon.getAttackSpeed());
//		itemAttackSpeed.setStyle("-fx-font-size: 16px;");
//		itemAttackSpeed.setTextAlignment(TextAlignment.RIGHT);
//		GridPane.setHalignment(itemAttackDamage, HPos.LEFT);
//		GridPane.setHalignment(itemAttackSpeed, HPos.RIGHT);
//		layout.addRow(rowIndex++, itemAttackDamage, itemAttackSpeed);
//		
//		if(weapon.getStamina() != 0)
//		{
//			itemStamina.setText("+" + weapon.getStamina() + " Stamina");
//			itemStamina.setStyle("-fx-font-size: 16px;");
//			layout.addRow(rowIndex++, itemStamina);
//		}
//		
//		if(weapon.getStrength() != 0)
//		{
//			itemStrength.setText("+" + weapon.getStrength() + " Strength");
//			itemStrength.setStyle("-fx-font-size: 16px;");
//			layout.addRow(rowIndex++, itemStrength);
//		}
//		
//		if(weapon.getSpirit() != 0)
//		{
//			itemSpirit.setText("+" + weapon.getSpirit() + " Spirit");
//			itemSpirit.setStyle("-fx-font-size: 16px;");
//			layout.addRow(rowIndex++, itemSpirit);
//		}
//		
//		if(weapon.getIntellect() != 0)
//		{
//			itemIntellect.setText("+" + weapon.getIntellect() + " Intellect");
//			itemIntellect.setStyle("-fx-font-size: 16px;");
//			layout.addRow(rowIndex++, itemIntellect);
//		}
//		
//		if(weapon.getAgility() != 0)
//		{
//			itemAgility.setText("+" + weapon.getAgility() + " Agility");
//			itemAgility.setStyle("-fx-font-size: 16px;");
//			layout.addRow(rowIndex++, itemAgility);
//		}
//		
//		if(weapon.getDescription().length() > 0)
//		{
//			itemDescription.setText(weapon.getDescription());
//			itemDescription.setStyle("-fx-font-size: 16px;");
//			itemDescription.setFill(Color.web("#11FF11"));
//			layout.addRow(rowIndex++, itemDescription);
//		}
//		
//		itemLevel.setText("Requires Level " + weapon.getLevelReq());
//		itemLevel.setStyle("-fx-font-size: 16px;");
//		layout.addRow(rowIndex++, itemLevel);
//		
//		layout.setMaxHeight(24 + 16 * rowIndex + 20);
//	}

	public void resetToDefault()
	{
		itemName.setText("");
		itemDescription.setText("");
		itemLevel.setText("");
		itemAttackSpeed.setText("");
		itemAttackDamage.setText("");
		itemStamina.setText("");
		itemStrength.setText("");
		itemSpirit.setText("");
		itemIntellect.setText("");
		itemAgility.setText("");
		itemRarity.setValue(ItemRarity.UNCOMMON);
		weaponType.setValue(WeaponType.FIST_WEAPON);
	}
}