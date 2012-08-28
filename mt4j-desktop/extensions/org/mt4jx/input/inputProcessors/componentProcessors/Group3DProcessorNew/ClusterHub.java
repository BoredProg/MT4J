/***********************************************************************
*   MT4j Copyright (c) 2008 - 2012, C.Ruff, Fraunhofer-Gesellschaft All rights reserved.
*
*   This file is part of MT4j.
*
*   MT4j is free software: you can redistribute it and/or modify
*   it under the terms of the GNU Lesser General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   MT4j is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
*   GNU Lesser General Public License for more details.
*
*   You should have received a copy of the GNU Lesser General Public License
*   along with MT4j.  If not, see <http://www.gnu.org/licenses/>.
*
************************************************************************/
package org.mt4jx.input.inputProcessors.componentProcessors.Group3DProcessorNew;

import java.util.List;
import java.util.ArrayList;

import org.mt4j.input.IMTEventListener;
import org.mt4j.input.MTEvent;



public class ClusterHub implements ISelectionListener,IClusterEventListener {
	
	private List<IMTEventListener> eventListener;
	
	public ClusterHub()
	{
		this.eventListener = new ArrayList<IMTEventListener>();
	}
	
	public void addEventListener(IMTEventListener listener)
	{
		this.eventListener.add(listener);
	}
	
	public void removeEventListener(IMTEventListener listener)
	{
		this.eventListener.remove(listener);
	}
	
	public List<IMTEventListener> getListeners()
	{
		return eventListener;
	}
	
	public void processMTEvent(MTEvent event)
	{
		for(int i=0;i<eventListener.size();i++)
		{
			this.eventListener.get(i).processMTEvent(event);
		}
	}
}