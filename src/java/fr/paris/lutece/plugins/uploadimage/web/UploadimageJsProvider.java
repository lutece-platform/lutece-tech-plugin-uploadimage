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
package fr.paris.lutece.plugins.uploadimage.web;

import java.util.HashMap;
import java.util.Map;

import fr.paris.lutece.plugins.uploadimage.business.Options;
import fr.paris.lutece.plugins.uploadimage.business.OptionsHome;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.html.HtmlTemplate;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Builds the javascript that drives the cropping widget of one upload field.
 *
 * Separate from the XPage on purpose: the JSP serving it needs a bean it can name in EL, and the XPage's own CDI
 * name carries the dots the XPage registry requires.
 */
@RequestScoped
@Named
public class UploadimageJsProvider
{
    private static final String TEMPLATE_MAIN_UPLOAD_IMAGE_JS = "skin/plugins/uploadimage/main.js";
    private static final String MARK_FIELDNAME = "fieldName";
    private static final String MARK_BASE_URL = "MARK_BASE_URL";
    private static final String PARAMETER_FIELDNAME = "fieldName";

    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 576;
    private static final int DEFAULT_X = 128;
    private static final int DEFAULT_Y = 72;
    private static final String DEFAULT_RATIO = "16/9";

    /**
     * The javascript configuring the widget for the field the request names, with the options stored for it or
     * the defaults when it has none.
     *
     * @param request
     *            the http request
     * @return the javascript
     */
    public String getMainUploadJs( HttpServletRequest request )
    {
        String strFieldname = request.getParameter( PARAMETER_FIELDNAME );
        Options option = null;

        if ( strFieldname != null && !strFieldname.isEmpty( ) )
        {
            option = OptionsHome.findByFieldName( strFieldname );
        }

        if ( option == null )
        {
            option = getDefaultOption( );
        }

        Map<String, Object> model = new HashMap<>( );
        model.put( MARK_BASE_URL, " strBaseUrl" );
        model.put( "cropperOption", option );
        model.put( MARK_FIELDNAME, strFieldname );

        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MAIN_UPLOAD_IMAGE_JS, request.getLocale( ), model );

        return template.getHtml( );
    }

    /**
     * The options a field with no stored configuration is cropped with.
     *
     * @return the default options
     */
    private Options getDefaultOption( )
    {
        Options option = new Options( );

        option.setAutoCrop( true );
        option.setBackground( true );
        option.setCheckImageOrigin( true );
        option.setCropBoxMovable( true );
        option.setDoubleClickToggle( true );
        option.setDragCrop( true );
        option.setGuides( true );
        option.setHighlight( true );
        option.setModal( true );
        option.setMouseWheelZoom( true );
        option.setMovable( true );
        option.setResponsive( true );
        option.setRotatable( true );
        option.setStrict( true );
        option.setTouchDragZoom( true );
        option.setZoomable( true );
        option.setCropBoxResizable( true );

        option.setHeight( DEFAULT_HEIGHT );
        option.setWidth( DEFAULT_WIDTH );
        option.setX( DEFAULT_X );
        option.setY( DEFAULT_Y );
        option.setRatio( DEFAULT_RATIO );
        option.setFieldName( MARK_FIELDNAME );

        return option;
    }
}
