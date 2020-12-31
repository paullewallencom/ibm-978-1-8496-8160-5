package com.rtcessentials.cliext.teamcentral;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

import com.ibm.team.jface.dashboard.Section;

public class HelloWorldSection extends Section {

	private Link hwLink;
	
	public void createContent(final Composite parent) {
		GridLayout grid= new GridLayout(1, false);
		grid.marginHeight= 1;
		parent.setLayout(grid);

		// Show ClickMe Link
		hwLink= new Link(parent, SWT.NONE);
		hwLink.setText("RTC Client Extention. <a>Click Me!</a>");
		hwLink.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		hwLink.setBackground(parent.getBackground());
		hwLink.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MessageDialog.openInformation(parent.getShell(), "RTC Client Extension Demo", "This is a quick demo of the extending the \nRational Team Concert's Eclipse client.");
			}
		});

		// Label Separator
		Label separator = new Label(parent, SWT.HORIZONTAL | SWT.SEPARATOR);
		separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Show Info Image Label
		Label infoImgLabel= new Label(parent, SWT.NONE);
		infoImgLabel.setBackground(parent.getBackground());
		infoImgLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
		infoImgLabel.setImage(parent.getDisplay().getSystemImage(SWT.ICON_INFORMATION));

		// Label Separator
		separator = new Label(parent, SWT.HORIZONTAL | SWT.SEPARATOR);
		separator.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Show Message
		StyledText warningMsg= new StyledText(parent, SWT.MULTI | SWT.READ_ONLY);
		warningMsg.setBackground(parent.getBackground());
		warningMsg.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
		warningMsg.setText("Extending RTC Client is really easy.\n" +
				           "Make use of the various extensions and\n" +
				           "drop the plugins into the Eclipse cleint.");
	}

	@Override
	public void setFocus() {
		hwLink.setFocus();
	}

}
