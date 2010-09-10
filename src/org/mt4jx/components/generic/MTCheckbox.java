package org.mt4jx.components.generic;

import org.mt4j.components.css.style.CSSStyle;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.rotateProcessor.RotateProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.scaleProcessor.ScaleProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.tapProcessor.TapProcessor;
import org.mt4j.input.inputProcessors.componentProcessors.zoomProcessor.ZoomProcessor;
import org.mt4j.util.MTColor;

import processing.core.PApplet;

/**
 * The Class MTCheckbox.
 */
public class MTCheckbox extends MTForm implements BooleanForm{

	/** The boolean value. */
	boolean booleanValue = false;;	
	
	/** The background color. */
	MTColor backgroundColor;
	
	/** The stroke color. */
	MTColor strokeColor;
	
	/**
	 * Instantiates a new MTCheckBox
	 *
	 * @param size the size of the side of the square
	 * @param pApplet the applet
	 */
	public MTCheckbox(float size, PApplet pApplet) {
		super(0, 0, size, size, pApplet, MTForm.BOOLEAN);
		
		this.setCssForceDisable(true);

		this.setFillColor(backgroundColor);
		this.setNoFill(true);
		this.setNoStroke(false);
		this.setStrokeWeight(2f);
		
		this.style();
		
		this.setGestureAllowance(TapProcessor.class, true);
		this.registerInputProcessor(new TapProcessor(pApplet));
		this.addGestureListener(TapProcessor.class, new BooleanTapListener());
		
		this.setGestureAllowance(DragProcessor.class, false);
		this.setGestureAllowance(ScaleProcessor.class, false);
		this.setGestureAllowance(ZoomProcessor.class, false);
		this.setGestureAllowance(RotateProcessor.class, false);
	}

	private void style() {
		//Is the Item CSSStyle? If no, take standard colors
		if (this.isCSSStyled()) {
			CSSStyle vss = this.getCssHelper().getVirtualStyleSheet();
			this.setStrokeWeight(vss.getBorderWidth());
			this.setLineStipple(vss.getBorderStylePattern());
			
			if (vss.isModifiedBackgroundColor()) this.backgroundColor = vss.getBackgroundColor();
			else backgroundColor = MTColor.YELLOW;
			if (vss.isModifiedBorderColor()) strokeColor = vss.getBorderColor();
			else strokeColor = MTColor.WHITE;
		} else {
			this.backgroundColor = MTColor.YELLOW;
			this.strokeColor = MTColor.WHITE;
		}
		
		if (!brightEnough(backgroundColor)) {
			this.backgroundColor = MTColor.YELLOW;
		}
		this.setFillColor(backgroundColor);
		this.setStrokeColor(strokeColor);
	}
	
	private boolean brightEnough(MTColor color) {
		return color.getR() + color.getG() + color.getB() > 200 && color.getAlpha() > 200;

	}
	
	@Override
	public void applyStyleSheet() {
		System.out.println("Styling now. CSSID: " + this.getCSSID());
		style();
	}
	
	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#getBooleanValue()
	 */
	@Override
	public boolean getBooleanValue() {
		return booleanValue;
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#getStringValue()
	 */
	@Override
	public String getStringValue() {
		return String.valueOf(this.getBooleanValue());
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#getNumericValue()
	 */
	@Override
	public float getNumericValue() {
		if (this.getBooleanValue() == true) return 1;
		else return 0;
	}

	/* (non-Javadoc)
	 * @see org.mt4jx.components.generic.MTForm#setBooleanValue(boolean)
	 */
	@Override
	public void setBooleanValue(boolean value) {
		this.booleanValue = value;
		if (this.booleanValue == true) {
			this.setNoFill(false);
		} else {
			this.setNoFill(true);
		}
		
	}


}
