package com.logigear.test.ta_dashboard.data_object;

public class GeneralPanel {
	
	protected PanelType _panelType;
	protected String _dataProfile, _displayName;
	
	public String getDataProfile() {
		return _dataProfile;
	}
	public void setDataProfile(String _dataProfile) {
		this._dataProfile = _dataProfile;
	}
	public String getDisplayName() {
		return _displayName;
	}
	public void setDisplayName(String _displayName) {
		this._displayName = _displayName;
	}
	
	public enum PanelType {
		CHART("Chart"),
		INDICATOR("Indicator"),
		REPORT("Report"),
		HEAT_MAP("Heat Map");
		
		private String _panelType;
		
		public String getValue() {
			return _panelType;
		}

		public void setValue(String panelType) {
			this._panelType = panelType;
		}

		private PanelType(String panelType) {
			this._panelType = panelType;
		}
	}
	

}
