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
import fr.paris.lutece.plugins.uploadimage.service.UploadImageCacheService;
import fr.paris.lutece.portal.web.xpages.XPage;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.util.html.HtmlTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.apache.commons.lang3.util.*;

/**
 * This class provides a simple implementation of an XPage
 */
 
@RequestScoped
@Named( "uploadimage.xpage.uploadimage" )
@Controller( xpageName = "uploadimage" , pageTitleI18nKey = "uploadimage.xpage.uploadimage.pageTitle" , pagePathI18nKey = "uploadimage.xpage.uploadimage.pagePathLabel" )
public class UploadimageApp extends MVCApplication
{
    @Inject
    private UploadImageCacheService _cacheService;

    private static final String TEMPLATE_XPAGE = "/skin/plugins/uploadimage/uploadimageXpage.html";
 // Templates
    private static final String TEMPLATE_MAIN_UPLOAD_IMAGE_JS = "skin/plugins/uploadimage/main.js";
    
    private static final String VIEW_HOME = "home";
    
    private static final String IDOPTION = "id_option";
    
    private static final String MARK_FIELDNAME = "fieldName";
    private static final String PARAMATER_FIELDNAME = "fieldName";
    
    /**
     * Returns the content of the page uploadimage. 
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_HOME , defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
    	Map<String, Object> model = new HashMap<String, Object>(  );
    //	model.put("main", getMainUploadJs(request));
        return getXPage( TEMPLATE_XPAGE, request.getLocale(  ), model );
    }
    
    

}
