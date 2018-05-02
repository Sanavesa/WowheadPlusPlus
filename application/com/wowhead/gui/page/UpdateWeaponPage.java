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

public class UpdateWeaponPage extends Page
{
	private TopView topView;
	private TextField itemName, itemLevel, itemDescription, itemAttackSpeed, itemAttackDamage,
			itemStamina, itemStrength, itemSpirit, itemIntellect, itemAgility;
	private ComboBox<ItemRarity> itemRarity;
	private ComboBox<WeaponType> weaponType;
	private GridPane gridPane;
	private Button btnSave, btnCancel;
	private Weapon loadedWeapon = null;

	public UpdateWeaponPage(PageManager pageManager)
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
		
		btnSave = new Button("Save");
		btnSave.setOnAction(e ->
		{
			Weapon weapon = new Weapon(
					loadedWeapon.getId(), 
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
			
			Database.getInstance().updateWeapon(weapon);
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
		gridPane.addRow(13, btnSave, btnCancel);

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
	
	public void loadFromWeapon(Weapon weapon)
	{
		this.loadedWeapon = weapon;
		itemName.setText(weapon.getName());
		itemDescription.setText(weapon.getDescription());
		itemLevel.setText(String.valueOf(weapon.getLevelReq()));
		itemAttackSpeed.setText(String.valueOf(weapon.getAttackSpeed()));
		itemAttackDamage.setText(String.valueOf(weapon.getAttackDamage()));
		itemStamina.setText(String.valueOf(weapon.getStamina()));
		itemStrength.setText(String.valueOf(weapon.getStrength()));
		itemSpirit.setText(String.valueOf(weapon.getSpirit()));
		itemIntellect.setText(String.valueOf(weapon.getIntellect()));
		itemAgility.setText(String.valueOf(weapon.getAgility()));
		itemRarity.setValue(weapon.getRarity());
		weaponType.setValue(weapon.getWeaponType());
	}

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