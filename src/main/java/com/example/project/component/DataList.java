package com.example.project.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIData;

import com.example.project.renderer.DataListRenderer;

@FacesComponent(DataList.COMPONENT_TYPE)
public class DataList extends UIData {

	public static final String COMPONENT_TYPE = "example.DataList";

	public DataList() {
		setRendererType(DataListRenderer.RENDERER_TYPE);
	}

}