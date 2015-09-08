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

import fr.paris.lutece.plugins.uploadimage.business.Options;
import fr.paris.lutece.plugins.uploadimage.business.OptionsHome;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.HtmlTemplate;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * ManageUploadimage JSP Bean abstract class for JSP Bean
 */
public class ManageUploadimageJspBean extends MVCAdminJspBean
{
    // Right
    public static final String RIGHT_MANAGEUPLOADIMAGE = "UPLOADIMAGE_MANAGEMENT";

    private static final String PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE = "uploadimage.listItems.itemsPerPage";
    private static final String PARAMETER_PAGE_INDEX                = "page_index";
    private static final String MARK_PAGINATOR                      = "paginator";
    private static final String MARK_NB_ITEMS_PER_PAGE              = "nb_items_per_page";
    private static final String MARK_LIST_OPTIONS                   = "list_options";
    private static final String TEMPLATE_MANAGE_UPLOAD_IMAGE        = "/admin/plugins/uploadimage/manageuploadimage_tabs.html";
    private static final String TEMPLATE_MANAGE_VIEW_OPTION         = "/admin/plugins/uploadimage/manage_option.html";
    private static final String JSP_MANAGE_OPTIONS                  = "jsp/admin/plugins/uploadimage/ManageUploadimage.jsp";
    private static final String JSP_DO_REMOVE_OPTION                = "jsp/admin/plugins/uploadimage/DoRemoveOptions.jsp";
    /* Jsp Redirect */
    private static final String JSP_REDIRECT_TO_MANAGE_UPLOAD_IMAGE = "ManageUploadimage.jsp";
    /* Messages */
    private static final String MESSAGE_CONFIRM_REMOVE_OPTION       = "uploadimage.message.confirmRemoveOption";
    private static final String INFO_OPTION_CREATED                 = "uploadimage.message.option.assigned";
    private static final String INFO_OPTION_UPDATE                  = "uploadimage.message.option.update";
    //Parameters
    private static final String PARAMETER_STRICT                    = "strict";
    private static final String PARAMETER_RESPONSIVE                = "responsive";
    private static final String PARAMETER_CHECKIMAGEORIGIN          = "checkimageorigin";
    private static final String PARAMETER_Modal                     = "modal";
    private static final String PARAMETER_GUIDES                    = "guides";
    private static final String PARAMETER_HIGHLIGHT                 = "highlight";
    private static final String PARAMETER_BACKGOUND                 = "background";
    private static final String PARAMETER_AUTOCROP                  = "autocrop";
    private static final String PARAMETER_DRAGCROP                  = "dragcrop";
    private static final String PARAMETER_MOVABLE                   = "movable";
    private static final String PARAMETER_ROTATABLE                 = "rotatable";
    private static final String PARAMETER_ZOOMABLE                  = "zoomable";
    private static final String PARAMETER_TOUCHDRAZOOM              = "touchdragzoom";
    private static final String PARAMETER_MOUSEWHEELZOOM            = "mousewheelzoom";
    private static final String PARAMETER_CROPBOXMOVABLE            = "cropboxmovable";
    private static final String PARAMETER_CROPBOXRESIZABLE          = "cropboxresizable";
    private static final String PARAMETER_DOUBLECLICKTOGGLE         = "doubleclicktoggle";
    private static final String PARAMETER_WIDTH                     = "width";
    private static final String PARAMETER_HEIGHT                    = "height";
    private static final String PARAMETER_X                         = "x";
    private static final String PARAMETER_Y                         = "y";
    private static final String PARAMETER_RATIO                     = "ratio";
    private static final String PARAMATER_FIELDNAME                 = "fieldName";
    private static final String PARAMETER_IDOPTION                  = "id_option";
    private static final String PARAMETER_MAX_HEIGHT                = "maxHeight";

    //Variables
    private int    _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int    _nItemsPerPage;

    protected Map<String, Object> getPaginatedListModel( HttpServletRequest request, String strBookmark, List list, String strManageJsp )
    {
        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage, _nDefaultItemsPerPage );

        UrlItem url = new UrlItem( strManageJsp );
        String strUrl = url.getUrl( );

        // PAGINATOR
        LocalizedPaginator paginator = new LocalizedPaginator( list, _nItemsPerPage, strUrl, PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale( ) );

        Map<String, Object> model = getModel( );

        model.put( MARK_NB_ITEMS_PER_PAGE, "" + _nItemsPerPage );
        model.put( MARK_PAGINATOR, paginator );
        model.put( strBookmark, paginator.getPageItems( ) );

        return model;
    }

    /**
     * get form to import file
     *
     * @param request
     * @return view of formulaire
     */
    public String getManageUploadimageHome( HttpServletRequest request )
    {

        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage, _nDefaultItemsPerPage );

        Collection<Options> options = OptionsHome.getOptionssList( );

        // PAGINATOR
        LocalizedPaginator paginator = new LocalizedPaginator( ( List ) options, _nItemsPerPage, getUrlPage( ), PARAMETER_PAGE_INDEX, _strCurrentPageIndex, getLocale( ) );

        Map<String, Object> model = getModel( );

        model.put( MARK_NB_ITEMS_PER_PAGE, "" + _nItemsPerPage );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_LIST_OPTIONS, paginator.getPageItems( ) );

        HtmlTemplate templateList = AppTemplateService.getTemplate( TEMPLATE_MANAGE_UPLOAD_IMAGE, getLocale( ), model );

        return getAdminPage( templateList.getHtml( ) );
    }

    public String manageOptions( HttpServletRequest request )
    {

        boolean bStrict = Boolean.parseBoolean( request.getParameter( PARAMETER_STRICT ) );
        boolean bResponsive = Boolean.parseBoolean( request.getParameter( PARAMETER_RESPONSIVE ) );
        boolean bCheckImageOrigin = Boolean.parseBoolean( request.getParameter( PARAMETER_CHECKIMAGEORIGIN ) );
        boolean bModal = Boolean.parseBoolean( request.getParameter( PARAMETER_Modal ) );
        boolean bGuides = Boolean.parseBoolean( request.getParameter( PARAMETER_GUIDES ) );
        boolean bHighlight = Boolean.parseBoolean( request.getParameter( PARAMETER_HIGHLIGHT ) );
        boolean bBackground = Boolean.parseBoolean( request.getParameter( PARAMETER_BACKGOUND ) );
        boolean bAutocrop = Boolean.parseBoolean( request.getParameter( PARAMETER_AUTOCROP ) );
        boolean bDragCrop = Boolean.parseBoolean( request.getParameter( PARAMETER_DRAGCROP ) );
        boolean bMovable = Boolean.parseBoolean( request.getParameter( PARAMETER_MOVABLE ) );
        boolean bRotatable = Boolean.parseBoolean( request.getParameter( PARAMETER_ROTATABLE ) );
        boolean bZoomable = Boolean.parseBoolean( request.getParameter( PARAMETER_ZOOMABLE ) );
        boolean bTouchDragZoom = Boolean.parseBoolean( request.getParameter( PARAMETER_TOUCHDRAZOOM ) );
        boolean bMouseWheelZoom = Boolean.parseBoolean( request.getParameter( PARAMETER_MOUSEWHEELZOOM ) );
        boolean bCropBoxMovable = Boolean.parseBoolean( request.getParameter( PARAMETER_CROPBOXMOVABLE ) );
        boolean bCropBoxResizable = Boolean.parseBoolean( request.getParameter( PARAMETER_CROPBOXRESIZABLE ) );
        boolean bDoubleClickToggle = Boolean.parseBoolean( request.getParameter( PARAMETER_DOUBLECLICKTOGGLE ) );

        int nWidth = Integer.parseInt( request.getParameter( PARAMETER_WIDTH ) );
        int nHeight = Integer.parseInt( request.getParameter( PARAMETER_HEIGHT ) );
        int nX = Integer.parseInt( request.getParameter( PARAMETER_X ) );
        int nY = Integer.parseInt( request.getParameter( PARAMETER_Y ) );
        int nMaxHeight = Integer.parseInt( request.getParameter( PARAMETER_MAX_HEIGHT ) );

        String strId = request.getParameter( PARAMETER_IDOPTION );

        String strRatio = request.getParameter( PARAMETER_RATIO );
        String strFieldName = request.getParameter( PARAMATER_FIELDNAME );

        String action = request.getParameter( "action" );

        Options option = new Options( );

        option.setAutoCrop( bAutocrop );
        option.setBackground( bBackground );
        option.setCheckImageOrigin( bCheckImageOrigin );
        option.setCropBoxMovable( bCropBoxMovable );
        option.setDoubleClickToggle( bDoubleClickToggle );
        option.setDragCrop( bDragCrop );
        option.setGuides( bGuides );
        option.setHighlight( bHighlight );
        option.setModal( bModal );
        option.setMouseWheelZoom( bMouseWheelZoom );
        option.setMovable( bMovable );
        option.setResponsive( bResponsive );
        option.setRotatable( bRotatable );
        option.setStrict( bStrict );
        option.setTouchDragZoom( bTouchDragZoom );
        option.setZoomable( bZoomable );
        option.setCropBoxResizable( bCropBoxResizable );

        option.setHeight( nHeight );
        option.setWidth( nWidth );
        option.setX( nX );
        option.setY( nY );
        option.setRatio( strRatio );
        option.setFieldName( strFieldName );
        option.setMaxHeight( nMaxHeight );

        if ( action.equals( "modify_option" ) )
        {
            option.setId( Integer.parseInt( strId ) );
            OptionsHome.update( option );
            addInfo( INFO_OPTION_CREATED, getLocale( ) );
        } else
        {
            OptionsHome.create( option );
            addInfo( INFO_OPTION_UPDATE, getLocale( ) );
        }

        Map<String, Object> model = getModel( );
        model.put( "option", option );
        model.put( "action", "modify_option" );

        return getManageUploadimageHome( request );
    }

    public String getViewOptions( HttpServletRequest request )
    {

        String nKey = request.getParameter( PARAMETER_IDOPTION );
        String strAction = request.getParameter( "action" );
        Options option = new Options( );
        if ( nKey != null )
        {
            option = OptionsHome.findByPrimaryKey( Integer.parseInt( nKey ) );

        } else
        {
            option.setHeight( 576 );
            option.setWidth( 1024 );
            option.setX( 128 );
            option.setY( 72 );
            option.setRatio( "16/9" );
            option.setMaxHeight( 1000 );
        }
        Map<String, Object> model = getModel( );
        model.put( "option", option );
        model.put( "action", strAction );
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_MANAGE_VIEW_OPTION, request.getLocale( ), model );

        return template.getHtml( );
    }

    /**
     * Manages the removal form of a options whose identifier is in the http
     * request
     *
     * @param request
     *         The HTTP request
     * @return the HTML code to confirm
     */
    public String getConfirmRemoveOption( HttpServletRequest request )
    {
        String nKey = request.getParameter( PARAMETER_IDOPTION );
        UrlItem url = new UrlItem( JSP_DO_REMOVE_OPTION );
        url.addParameter( PARAMETER_IDOPTION, nKey );

        return AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_OPTION, url.getUrl( ), AdminMessage.TYPE_CONFIRMATION );

    }

    public String removeOptions( HttpServletRequest request )
    {

        String nKey = request.getParameter( PARAMETER_IDOPTION );
        if ( nKey != null )
        {
            OptionsHome.remove( Integer.parseInt( nKey ) );
        }

        return JSP_REDIRECT_TO_MANAGE_UPLOAD_IMAGE;
    }

    private String getUrlPage( )
    {
        UrlItem url = new UrlItem( JSP_MANAGE_OPTIONS );

        return url.getUrl( );
    }

}
