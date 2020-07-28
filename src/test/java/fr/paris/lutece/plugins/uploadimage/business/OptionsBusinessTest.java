/*
 * Copyright (c) 2002-2020, City of Paris
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

import fr.paris.lutece.test.LuteceTestCase;

public class OptionsBusinessTest extends LuteceTestCase
{
    private final static boolean STRICT1 = true;
    private final static boolean STRICT2 = false;
    private final static boolean RESPONSIVE1 = true;
    private final static boolean RESPONSIVE2 = false;
    private final static boolean CHECKIMAGEORIGIN1 = true;
    private final static boolean CHECKIMAGEORIGIN2 = false;
    private final static boolean MODAL1 = true;
    private final static boolean MODAL2 = false;
    private final static boolean GUIDES1 = true;
    private final static boolean GUIDES2 = false;
    private final static boolean HIGHLIGHT1 = true;
    private final static boolean HIGHLIGHT2 = false;
    private final static boolean BACKGROUND1 = true;
    private final static boolean BACKGROUND2 = false;
    private final static boolean AUTOCROP1 = true;
    private final static boolean AUTOCROP2 = false;
    private final static boolean DRAGCROP1 = true;
    private final static boolean DRAGCROP2 = false;
    private final static boolean MOVABLE1 = true;
    private final static boolean MOVABLE2 = false;
    private final static boolean ROTATABLE1 = true;
    private final static boolean ROTATABLE2 = false;
    private final static boolean ZOOMABLE1 = true;
    private final static boolean ZOOMABLE2 = false;
    private final static boolean TOUCHDRAGZOOM1 = true;
    private final static boolean TOUCHDRAGZOOM2 = false;
    private final static boolean MOUSEWHEELZOOM1 = true;
    private final static boolean MOUSEWHEELZOOM2 = false;
    private final static boolean CROPBOXMOVABLE1 = true;
    private final static boolean CROPBOXMOVABLE2 = false;
    private final static boolean CROPBOXRESIZABLE1 = true;
    private final static boolean CROPBOXRESIZABLE2 = false;
    private final static boolean DOUBLECLICKTOGGLE1 = true;
    private final static boolean DOUBLECLICKTOGGLE2 = false;
    private final static int WIDTH1 = 1;
    private final static int WIDTH2 = 2;
    private final static int HEIGHT1 = 1;
    private final static int HEIGHT2 = 2;
    private final static int X1 = 1;
    private final static int X2 = 2;
    private final static int Y1 = 1;
    private final static int Y2 = 2;
    private final static String ROTATE1 = "Rotate1";
    private final static String ROTATE2 = "Rotate2";
    private final static String FIELDNAME = "fieldName";

    public void testBusiness( )
    {
        // Initialize an object
        Options options = new Options( );
        options.setStrict( STRICT1 );
        options.setResponsive( RESPONSIVE1 );
        options.setCheckImageOrigin( CHECKIMAGEORIGIN1 );
        options.setModal( MODAL1 );
        options.setGuides( GUIDES1 );
        options.setHighlight( HIGHLIGHT1 );
        options.setBackground( BACKGROUND1 );
        options.setAutoCrop( AUTOCROP1 );
        options.setDragCrop( DRAGCROP1 );
        options.setMovable( MOVABLE1 );
        options.setRotatable( ROTATABLE1 );
        options.setZoomable( ZOOMABLE1 );
        options.setTouchDragZoom( TOUCHDRAGZOOM1 );
        options.setMouseWheelZoom( MOUSEWHEELZOOM1 );
        options.setCropBoxMovable( CROPBOXMOVABLE1 );
        options.setCropBoxResizable( CROPBOXRESIZABLE1 );
        options.setDoubleClickToggle( DOUBLECLICKTOGGLE1 );
        options.setWidth( WIDTH1 );
        options.setHeight( HEIGHT1 );
        options.setX( X1 );
        options.setY( Y1 );
        options.setRatio( ROTATE1 );
        options.setFieldName( FIELDNAME );

        // Create test
        OptionsHome.create( options );
        Options optionsStored = OptionsHome.findByPrimaryKey( options.getId( ) );
        assertEquals( optionsStored.getStrict( ), options.getStrict( ) );
        assertEquals( optionsStored.getResponsive( ), options.getResponsive( ) );
        assertEquals( optionsStored.getCheckImageOrigin( ), options.getCheckImageOrigin( ) );
        assertEquals( optionsStored.getModal( ), options.getModal( ) );
        assertEquals( optionsStored.getGuides( ), options.getGuides( ) );
        assertEquals( optionsStored.getHighlight( ), options.getHighlight( ) );
        assertEquals( optionsStored.getBackground( ), options.getBackground( ) );
        assertEquals( optionsStored.getAutoCrop( ), options.getAutoCrop( ) );
        assertEquals( optionsStored.getDragCrop( ), options.getDragCrop( ) );
        assertEquals( optionsStored.getMovable( ), options.getMovable( ) );
        assertEquals( optionsStored.getRotatable( ), options.getRotatable( ) );
        assertEquals( optionsStored.getZoomable( ), options.getZoomable( ) );
        assertEquals( optionsStored.getTouchDragZoom( ), options.getTouchDragZoom( ) );
        assertEquals( optionsStored.getMouseWheelZoom( ), options.getMouseWheelZoom( ) );
        assertEquals( optionsStored.getCropBoxMovable( ), options.getCropBoxMovable( ) );
        assertEquals( optionsStored.getCropBoxResizable( ), options.getCropBoxResizable( ) );
        assertEquals( optionsStored.getDoubleClickToggle( ), options.getDoubleClickToggle( ) );
        assertEquals( optionsStored.getWidth( ), options.getWidth( ) );
        assertEquals( optionsStored.getHeight( ), options.getHeight( ) );
        assertEquals( optionsStored.getX( ), options.getX( ) );
        assertEquals( optionsStored.getY( ), options.getY( ) );
        assertEquals( optionsStored.getRatio( ), options.getRatio( ) );

        // Update test
        options.setStrict( STRICT2 );
        options.setResponsive( RESPONSIVE2 );
        options.setCheckImageOrigin( CHECKIMAGEORIGIN2 );
        options.setModal( MODAL2 );
        options.setGuides( GUIDES2 );
        options.setHighlight( HIGHLIGHT2 );
        options.setBackground( BACKGROUND2 );
        options.setAutoCrop( AUTOCROP2 );
        options.setDragCrop( DRAGCROP2 );
        options.setMovable( MOVABLE2 );
        options.setRotatable( ROTATABLE2 );
        options.setZoomable( ZOOMABLE2 );
        options.setTouchDragZoom( TOUCHDRAGZOOM2 );
        options.setMouseWheelZoom( MOUSEWHEELZOOM2 );
        options.setCropBoxMovable( CROPBOXMOVABLE2 );
        options.setCropBoxResizable( CROPBOXRESIZABLE2 );
        options.setDoubleClickToggle( DOUBLECLICKTOGGLE2 );
        options.setWidth( WIDTH2 );
        options.setHeight( HEIGHT2 );
        options.setX( X2 );
        options.setY( Y2 );
        options.setRatio( ROTATE2 );
        OptionsHome.update( options );
        optionsStored = OptionsHome.findByPrimaryKey( options.getId( ) );
        assertEquals( optionsStored.getStrict( ), options.getStrict( ) );
        assertEquals( optionsStored.getResponsive( ), options.getResponsive( ) );
        assertEquals( optionsStored.getCheckImageOrigin( ), options.getCheckImageOrigin( ) );
        assertEquals( optionsStored.getModal( ), options.getModal( ) );
        assertEquals( optionsStored.getGuides( ), options.getGuides( ) );
        assertEquals( optionsStored.getHighlight( ), options.getHighlight( ) );
        assertEquals( optionsStored.getBackground( ), options.getBackground( ) );
        assertEquals( optionsStored.getAutoCrop( ), options.getAutoCrop( ) );
        assertEquals( optionsStored.getDragCrop( ), options.getDragCrop( ) );
        assertEquals( optionsStored.getMovable( ), options.getMovable( ) );
        assertEquals( optionsStored.getRotatable( ), options.getRotatable( ) );
        assertEquals( optionsStored.getZoomable( ), options.getZoomable( ) );
        assertEquals( optionsStored.getTouchDragZoom( ), options.getTouchDragZoom( ) );
        assertEquals( optionsStored.getMouseWheelZoom( ), options.getMouseWheelZoom( ) );
        assertEquals( optionsStored.getCropBoxMovable( ), options.getCropBoxMovable( ) );
        assertEquals( optionsStored.getCropBoxResizable( ), options.getCropBoxResizable( ) );
        assertEquals( optionsStored.getDoubleClickToggle( ), options.getDoubleClickToggle( ) );
        assertEquals( optionsStored.getWidth( ), options.getWidth( ) );
        assertEquals( optionsStored.getHeight( ), options.getHeight( ) );
        assertEquals( optionsStored.getX( ), options.getX( ) );
        assertEquals( optionsStored.getY( ), options.getY( ) );
        assertEquals( optionsStored.getRatio( ), options.getRatio( ) );

        // List test
        OptionsHome.getOptionssList( );

        // Delete test
        OptionsHome.remove( options.getId( ) );
        optionsStored = OptionsHome.findByPrimaryKey( options.getId( ) );
        assertNull( optionsStored );

    }

}
