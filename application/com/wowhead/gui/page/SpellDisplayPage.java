// Mohammad
package com.wowhead.gui.page;

import com.wowhead.database.tables.Spell;
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

public class SpellDisplayPage extends Page
{
	private TopView topView;
	private Label spellName, spellRange, spellDuration, spellMagicSchool;
	private Text spellDescription;

	public SpellDisplayPage(PageManager pageManager)
	{
		super(pageManager);
	}

	@Override
	protected void initializePage()
	{
		topView = new TopView(pageManager);
		spellName = new Label("");
		spellDescription = new Text("");
		spellDescription.setWrappingWidth(390);
		spellRange = new Label("");
		spellDuration = new Label("");
		spellMagicSchool = new Label("");

		root.setStyle("-fx-background: #000000");

		root.setTop(topView.getRoot());
	}

	public void loadDisplay(Spell spell)
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

		spellName.setText(spell.getName());
		spellName.setStyle("-fx-font-size: 24px;");
		layout.addRow(rowIndex++, spellName);
		
		spellMagicSchool.setText(spell.getMagicSchool().toString());
		spellMagicSchool.setStyle("-fx-font-size: 16px;");
		layout.addRow(rowIndex++, spellMagicSchool);
		
		if(spell.getDuration() == 0)
		{
			spellDuration.setText("Instant cast");
		}
		else
		{
			spellDuration.setText(spell.getDuration() + " sec cast");
		}
		spellDuration.setStyle("-fx-font-size: 16px;");
		spellRange.setText(spell.getRange() + " yd range");
		spellRange.setStyle("-fx-font-size: 16px;");
		spellRange.setTextAlignment(TextAlignment.RIGHT);
		GridPane.setHalignment(spellDuration, HPos.LEFT);
		GridPane.setHalignment(spellRange, HPos.RIGHT);
		layout.addRow(rowIndex++, spellDuration, spellRange);

		if (spell.getDescription().length() > 0)
		{
			spellDescription.setText(spell.getDescription());
			spellDescription.setStyle("-fx-font-size: 16px;");
			spellDescription.setFill(Color.web("#FBDC03"));
			layout.addRow(rowIndex++, spellDescription);
		}

		layout.setMaxHeight(24 + 16 * rowIndex + 20);
	}
}