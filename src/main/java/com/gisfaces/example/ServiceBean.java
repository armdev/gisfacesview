package com.gisfaces.example;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import com.gisfaces.event.MapClickEvent;
import com.gisfaces.event.MapExtentEvent;
import com.gisfaces.event.MapGraphicViewEvent;
import com.gisfaces.model.map.Background;
import com.gisfaces.utilities.JSFUtilities;

@ManagedBean
@SessionScoped
public class ServiceBean implements Serializable
{
	private static final long serialVersionUID = 9135707557221898353L;

	private String background;
	private double latitude;
	private double longitude;
	private int zoom;
	private String url;

	public ServiceBean()
	{
		super();
		reset();
	}

	public void reset()
	{
		this.background = "gray";
		this.latitude = 39.828175;
		this.longitude = -98.5795;
		this.zoom = 4;
		this.url = "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/PublicSafety/PublicSafetyOperationalLayers/MapServer";
	}

	public List<SelectItem> getBackgrounds()
	{
		return Background.getSelectItems();
	}

	public void doMapClickListener(AjaxBehaviorEvent event)
	{
		MapClickEvent e = (MapClickEvent) event;

		String summary = "Map Click Event";
		String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s', Scale='%s', XMin='%s', YMin='%s', XMax='%s', YMax='%s', Height='%s', Width='%s', X='%s', Y='%s'", e.getLatitude(), e.getLongitude(), e.getZoom(), e.getScale(), e.getExtent().getXmin(), e.getExtent().getYmin(), e.getExtent().getXmax(), e.getExtent().getYmax(), e.getScreen().getHeight(), e.getScreen().getWidth(), e.getScreen().getX(), e.getScreen().getY());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapExtentListener(AjaxBehaviorEvent event)
	{
		MapExtentEvent e = (MapExtentEvent) event;

		String summary = "Map Extent Update Event";
		String detail = String.format("Latitude='%s', Longitude='%s', Zoom='%s', Scale='%s', XMin='%s', YMin='%s', XMax='%s', YMax='%s', Height='%s', Width='%s'", e.getLatitude(), e.getLongitude(), e.getZoom(), e.getScale(), e.getExtent().getXmin(), e.getExtent().getYmin(), e.getExtent().getXmax(), e.getExtent().getYmax(), e.getScreen().getHeight(), e.getScreen().getWidth());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapGraphicViewListener(AjaxBehaviorEvent event)
	{
		MapGraphicViewEvent e = (MapGraphicViewEvent) event;

		String summary = "Map Graphic View Event";
		String detail = String.format("Service ID='%s', Layer ID='%s', Graphics ID='%s', Attributes='%s'", e.getServiceId(), e.getLayerId(), e.getGraphicId(), e.getAttributesJson());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public String doViewButtonAction()
	{
		System.out.println("View button clicked.");

		if (!this.url.toLowerCase().endsWith("/mapserver"))
		{
			JSFUtilities.addErrorMessage("Not a valid ESRI dynamic map serivce.", "URL must end in '/MapServer'.");

			return null;
		}
		else
		{
			// Must redirect for GISFaces to reload service.
			return "service?faces-redirect=true";
		}
	}

	public String getBackground()
	{
		return background;
	}

	public void setBackground(String background)
	{
		this.background = background;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public int getZoom()
	{
		return zoom;
	}

	public void setZoom(int zoom)
	{
		this.zoom = zoom;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
