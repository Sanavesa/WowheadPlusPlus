// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.NPC;
import com.wowhead.gui.PageManager;
import com.wowhead.gui.page.view.TopView;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

public class NpcDisplayPage extends Page
{
	private TopView topView;
	private Label npcName, npcFaction, npcType, npcHealth;

	public NpcDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		npcName = new Label("");
		npcFaction = new Label("");
		npcType = new Label("");
		npcHealth = new Label("");

		root.setStyle("-fx-background: #000000");
		root.setTop(topView.getRoot());
	}

	public void loadDisplay(NPC npc)
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

		npcName.setText(npc.getName());
		npcName.setStyle("-fx-font-size: 24px;");
		layout.addRow(rowIndex++, npcName);
		
		npcType.setText(npc.getNpcType().toString());
		npcType.setStyle("-fx-font-size: 16px;");
		npcFaction.setText(npc.getFaction().toString());
		npcFaction.setStyle("-fx-font-size: 16px;");
		npcFaction.setTextAlignment(TextAlignment.RIGHT);
		GridPane.setHalignment(npcType, HPos.LEFT);
		GridPane.setHalignment(npcFaction, HPos.RIGHT);
		layout.addRow(rowIndex++, npcType, npcFaction);
		
		npcHealth.setText("Health: " + npc.getHealth());
		npcHealth.setStyle("-fx-font-size: 16px; -fx-text-fill: #11FF11;");
		layout.addRow(rowIndex++, npcHealth);
		
		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}