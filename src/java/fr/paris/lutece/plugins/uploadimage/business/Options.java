/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.uploadimage.business;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

/**
 * This is the business class for the object Options
 */
public class Options
{
    // Variables declarations 
    private int _nId;

    private boolean _bStrict;

    private boolean _bResponsive;

    private boolean _bCheckImageOrigin;

    private boolean _bModal;

    private boolean _bGuides;

    private boolean _bHighlight;

    private boolean _bBackground;

    private boolean _bAutoCrop;

    private boolean _bDragCrop;

    private boolean _bMovable;

    private boolean _bRotatable;

    private boolean _bZoomable;

    private boolean _bTouchDragZoom;

    private boolean _bMouseWheelZoom;

    private boolean _bCropBoxMovable;

    private boolean _bCropBoxResizable;

    private boolean _bDoubleClickToggle;

    private int _nWidth;

    private int _nHeight;

    private int _nX;

    private int _nY;

    private int nMaxHeight;

    @Size( max = 50, message = "#i18n{uploadimage.validation.options.Rotate.size}" )
    private String _strRatio;

    @Size( max = 50, message = "#i18n{uploadimage.validation.options.Rotate.size}" )
    private String _strFieldName;

    /**
     * Returns the Id
     *
     * @return The Id
     */
    public int getId( )
    {
        return _nId;
    }

    /**
     * Sets the Id
     *
     * @param nId
     *         The Id
     */
    public void setId( int nId )
    {
        _nId = nId;
    }

    /**
     * Returns the Strict
     *
     * @return The Strict
     */
    public boolean getStrict( )
    {
        return _bStrict;
    }

    /**
     * Sets the Strict
     *
     * @param bStrict
     *         The Strict
     */
    public void setStrict( boolean bStrict )
    {
        _bStrict = bStrict;
    }

    /**
     * Returns the Responsive
     *
     * @return The Responsive
     */
    public boolean getResponsive( )
    {
        return _bResponsive;
    }

    /**
     * Sets the Responsive
     *
     * @param bResponsive
     *         The Responsive
     */
    public void setResponsive( boolean bResponsive )
    {
        _bResponsive = bResponsive;
    }

    /**
     * Returns the CheckImageOrigin
     *
     * @return The CheckImageOrigin
     */
    public boolean getCheckImageOrigin( )
    {
        return _bCheckImageOrigin;
    }

    /**
     * Sets the CheckImageOrigin
     *
     * @param bCheckImageOrigin
     *         The CheckImageOrigin
     */
    public void setCheckImageOrigin( boolean bCheckImageOrigin )
    {
        _bCheckImageOrigin = bCheckImageOrigin;
    }

    /**
     * Returns the Modal
     *
     * @return The Modal
     */
    public boolean getModal( )
    {
        return _bModal;
    }

    /**
     * Sets the Modal
     *
     * @param bModal
     *         The Modal
     */
    public void setModal( boolean bModal )
    {
        _bModal = bModal;
    }

    /**
     * Returns the Guides
     *
     * @return The Guides
     */
    public boolean getGuides( )
    {
        return _bGuides;
    }

    /**
     * Sets the Guides
     *
     * @param bGuides
     *         The Guides
     */
    public void setGuides( boolean bGuides )
    {
        _bGuides = bGuides;
    }

    /**
     * Returns the Highlight
     *
     * @return The Highlight
     */
    public boolean getHighlight( )
    {
        return _bHighlight;
    }

    /**
     * Sets the Highlight
     *
     * @param bHighlight
     *         The Highlight
     */
    public void setHighlight( boolean bHighlight )
    {
        _bHighlight = bHighlight;
    }

    /**
     * Returns the Background
     *
     * @return The Background
     */
    public boolean getBackground( )
    {
        return _bBackground;
    }

    /**
     * Sets the Background
     *
     * @param bBackground
     *         The Background
     */
    public void setBackground( boolean bBackground )
    {
        _bBackground = bBackground;
    }

    /**
     * Returns the AutoCrop
     *
     * @return The AutoCrop
     */
    public boolean getAutoCrop( )
    {
        return _bAutoCrop;
    }

    /**
     * Sets the AutoCrop
     *
     * @param bAutoCrop
     *         The AutoCrop
     */
    public void setAutoCrop( boolean bAutoCrop )
    {
        _bAutoCrop = bAutoCrop;
    }

    /**
     * Returns the DragCrop
     *
     * @return The DragCrop
     */
    public boolean getDragCrop( )
    {
        return _bDragCrop;
    }

    /**
     * Sets the DragCrop
     *
     * @param bDragCrop
     *         The DragCrop
     */
    public void setDragCrop( boolean bDragCrop )
    {
        _bDragCrop = bDragCrop;
    }

    /**
     * Returns the Movable
     *
     * @return The Movable
     */
    public boolean getMovable( )
    {
        return _bMovable;
    }

    /**
     * Sets the Movable
     *
     * @param bMovable
     *         The Movable
     */
    public void setMovable( boolean bMovable )
    {
        _bMovable = bMovable;
    }

    /**
     * Returns the Rotatable
     *
     * @return The Rotatable
     */
    public boolean getRotatable( )
    {
        return _bRotatable;
    }

    /**
     * Sets the Rotatable
     *
     * @param bRotatable
     *         The Rotatable
     */
    public void setRotatable( boolean bRotatable )
    {
        _bRotatable = bRotatable;
    }

    /**
     * Returns the Zoomable
     *
     * @return The Zoomable
     */
    public boolean getZoomable( )
    {
        return _bZoomable;
    }

    /**
     * Sets the Zoomable
     *
     * @param bZoomable
     *         The Zoomable
     */
    public void setZoomable( boolean bZoomable )
    {
        _bZoomable = bZoomable;
    }

    /**
     * Returns the TouchDragZoom
     *
     * @return The TouchDragZoom
     */
    public boolean getTouchDragZoom( )
    {
        return _bTouchDragZoom;
    }

    /**
     * Sets the TouchDragZoom
     *
     * @param bTouchDragZoom
     *         The TouchDragZoom
     */
    public void setTouchDragZoom( boolean bTouchDragZoom )
    {
        _bTouchDragZoom = bTouchDragZoom;
    }

    /**
     * Returns the MouseWheelZoom
     *
     * @return The MouseWheelZoom
     */
    public boolean getMouseWheelZoom( )
    {
        return _bMouseWheelZoom;
    }

    /**
     * Sets the MouseWheelZoom
     *
     * @param bMouseWheelZoom
     *         The MouseWheelZoom
     */
    public void setMouseWheelZoom( boolean bMouseWheelZoom )
    {
        _bMouseWheelZoom = bMouseWheelZoom;
    }

    /**
     * Returns the CropBoxMovable
     *
     * @return The CropBoxMovable
     */
    public boolean getCropBoxMovable( )
    {
        return _bCropBoxMovable;
    }

    /**
     * Sets the CropBoxMovable
     *
     * @param bCropBoxMovable
     *         The CropBoxMovable
     */
    public void setCropBoxMovable( boolean bCropBoxMovable )
    {
        _bCropBoxMovable = bCropBoxMovable;
    }

    /**
     * Returns the CropBoxResizable
     *
     * @return The CropBoxResizable
     */
    public boolean getCropBoxResizable( )
    {
        return _bCropBoxResizable;
    }

    /**
     * Sets the CropBoxResizable
     *
     * @param bCropBoxResizable
     *         The CropBoxResizable
     */
    public void setCropBoxResizable( boolean bCropBoxResizable )
    {
        _bCropBoxResizable = bCropBoxResizable;
    }

    /**
     * Returns the DoubleClickToggle
     *
     * @return The DoubleClickToggle
     */
    public boolean getDoubleClickToggle( )
    {
        return _bDoubleClickToggle;
    }

    /**
     * Sets the DoubleClickToggle
     *
     * @param bDoubleClickToggle
     *         The DoubleClickToggle
     */
    public void setDoubleClickToggle( boolean bDoubleClickToggle )
    {
        _bDoubleClickToggle = bDoubleClickToggle;
    }

    /**
     * Returns the Width
     *
     * @return The Width
     */
    public int getWidth( )
    {
        return _nWidth;
    }

    /**
     * Sets the Width
     *
     * @param nWidth
     *         The Width
     */
    public void setWidth( int nWidth )
    {
        _nWidth = nWidth;
    }

    /**
     * Returns the Height
     *
     * @return The Height
     */
    public int getHeight( )
    {
        return _nHeight;
    }

    /**
     * Sets the Height
     *
     * @param nHeight
     *         The Height
     */
    public void setHeight( int nHeight )
    {
        _nHeight = nHeight;
    }

    /**
     * Returns the X
     *
     * @return The X
     */
    public int getX( )
    {
        return _nX;
    }

    /**
     * Sets the X
     *
     * @param nX
     *         The X
     */
    public void setX( int nX )
    {
        _nX = nX;
    }

    /**
     * Returns the Y
     *
     * @return The Y
     */
    public int getY( )
    {
        return _nY;
    }

    /**
     * Sets the Y
     *
     * @param nY
     *         The Y
     */
    public void setY( int nY )
    {
        _nY = nY;
    }

    /**
     * Returns the Rotate
     *
     * @return The Rotate
     */
    public String getRatio( )
    {
        return _strRatio;
    }

    /**
     * Sets the ratio
     *
     * @param strRatio
     *         The Ratio
     */
    public void setRatio( String strRatio )
    {
        _strRatio = strRatio;
    }

    /**
     * Returns the FieldName
     *
     * @return The FieldName
     */
    public String getFieldName( )
    {
        return _strFieldName;
    }

    /**
     * Sets the FieldName
     *
     * @param fieldName
     *         The FieldName
     */
    public void setFieldName( String fieldName )
    {
        _strFieldName = fieldName;
    }

    /**
     * Returns the Max height for the image
     *
     * @return
     */
    public int getMaxHeight( )
    {
        return nMaxHeight;
    }

    /**
     * Set the Max height for the image
     *
     * @param nMaxHeight
     *         The max height
     */
    public void setMaxHeight( int nMaxHeight )
    {
        this.nMaxHeight = nMaxHeight;
    }
}
