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
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.web.cdi.mvc.Models;
import fr.paris.lutece.portal.web.util.LocalizedPaginator;
import fr.paris.lutece.util.html.Paginator;
import fr.paris.lutece.util.url.UrlItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Manages the cropping options the plugin offers to the image upload widget.
 */
@SessionScoped
@Named
@Controller( controllerJsp = "ManageUploadimage.jsp", controllerPath = "jsp/admin/plugins/uploadimage/", right = ManageUploadimageJspBean.RIGHT_MANAGEUPLOADIMAGE, securityTokenEnabled = true )
public class ManageUploadimageJspBean extends MVCAdminJspBean
{
    /** Right needed to reach the screens of this plugin */
    public static final String RIGHT_MANAGEUPLOADIMAGE = "UPLOADIMAGE_MANAGEMENT";

    private static final long serialVersionUID = 1L;

    private static final String PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE = "uploadimage.listItems.itemsPerPage";
    private static final String PARAMETER_PAGE_INDEX = "page_index";
    private static final String MARK_PAGINATOR = "paginator";
    private static final String MARK_NB_ITEMS_PER_PAGE = "nb_items_per_page";
    private static final String MARK_LIST_OPTIONS = "list_options";
    private static final String MARK_OPTION = "cropperOption";
    private static final String TEMPLATE_MANAGE_UPLOAD_IMAGE = "/admin/plugins/uploadimage/manage_uploadimage.html";
    private static final String TEMPLATE_MANAGE_VIEW_OPTION = "/admin/plugins/uploadimage/manage_option.html";
    private static final String PROPERTY_PAGE_TITLE_MANAGE = "uploadimage.adminFeature.ManageUploadimage.name";
    private static final String PROPERTY_PAGE_TITLE_OPTION = "uploadimage.option.pageTitle";
    private static final String MESSAGE_CONFIRM_REMOVE_OPTION = "uploadimage.message.confirmRemoveOptions";
    private static final String INFO_OPTION_CREATED = "uploadimage.message.option.assigned";
    private static final String INFO_OPTION_UPDATE = "uploadimage.message.option.update";
    private static final String ERROR_FIELDNAME = "uploadimage.message.error.fieldName";
    private static final String ERROR_FIELDNAME_DUPLICATE = "uploadimage.message.error.fieldNameDuplicate";
    private static final String ERROR_RATIO = "uploadimage.message.error.ratio";
    private static final String ERROR_UNKNOWN_OPTION = "uploadimage.message.error.unknownOption";
    private static final String PATTERN_FIELDNAME = "[a-zA-Z0-9]+";
    private static final String PATTERN_RATIO = "[0-9]+/[0-9]+";

    private static final String VIEW_MANAGE_UPLOADIMAGE = "manageUploadimage";
    private static final String VIEW_OPTION = "viewOption";
    private static final String VIEW_CONFIRM_REMOVE_OPTION = "confirmRemoveOption";
    private static final String ACTION_MANAGE_OPTIONS = "manageOptions";
    private static final String ACTION_REMOVE_OPTION = "removeOption";

    // Parameters
    private static final String PARAMETER_STRICT = "strict";
    private static final String PARAMETER_RESPONSIVE = "responsive";
    private static final String PARAMETER_CHECKIMAGEORIGIN = "checkimageorigin";
    private static final String PARAMETER_MODAL = "modal";
    private static final String PARAMETER_GUIDES = "guides";
    private static final String PARAMETER_HIGHLIGHT = "highlight";
    private static final String PARAMETER_BACKGOUND = "background";
    private static final String PARAMETER_AUTOCROP = "autocrop";
    private static final String PARAMETER_DRAGCROP = "dragcrop";
    private static final String PARAMETER_MOVABLE = "movable";
    private static final String PARAMETER_ROTATABLE = "rotatable";
    private static final String PARAMETER_ZOOMABLE = "zoomable";
    private static final String PARAMETER_TOUCHDRAZOOM = "touchdragzoom";
    private static final String PARAMETER_MOUSEWHEELZOOM = "mousewheelzoom";
    private static final String PARAMETER_CROPBOXMOVABLE = "cropboxmovable";
    private static final String PARAMETER_CROPBOXRESIZABLE = "cropboxresizable";
    private static final String PARAMETER_DOUBLECLICKTOGGLE = "doubleclicktoggle";
    private static final String PARAMETER_WIDTH = "width";
    private static final String PARAMETER_HEIGHT = "height";
    private static final String PARAMETER_X = "x";
    private static final String PARAMETER_Y = "y";
    private static final String PARAMETER_RATIO = "ratio";
    private static final String PARAMATER_FIELDNAME = "fieldName";
    private static final String PARAMETER_IDOPTION = "id_option";

    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 576;
    private static final int DEFAULT_X = 128;
    private static final int DEFAULT_Y = 72;
    private static final String DEFAULT_RATIO = "16/9";

    private int _nDefaultItemsPerPage;
    private String _strCurrentPageIndex;
    private int _nItemsPerPage;
    private Options _option;

    /**
     * The list of cropping options.
     *
     * @param request
     *            the http request
     * @param model
     *            the view model
     * @return the page
     */
    @View( value = VIEW_MANAGE_UPLOADIMAGE, defaultView = true )
    public String getManageUploadimageHome( HttpServletRequest request, Models model )
    {
        _strCurrentPageIndex = Paginator.getPageIndex( request, Paginator.PARAMETER_PAGE_INDEX, _strCurrentPageIndex );
        _nDefaultItemsPerPage = AppPropertiesService.getPropertyInt( PROPERTY_DEFAULT_LIST_ITEM_PER_PAGE, 50 );
        _nItemsPerPage = Paginator.getItemsPerPage( request, Paginator.PARAMETER_ITEMS_PER_PAGE, _nItemsPerPage, _nDefaultItemsPerPage );

        Collection<Options> options = OptionsHome.getOptionssList( );
        List<Options> listOptions = new ArrayList<>( options );

        LocalizedPaginator<Options> paginator = new LocalizedPaginator<>( listOptions, _nItemsPerPage, getUrlPage( ), PARAMETER_PAGE_INDEX,
                _strCurrentPageIndex, getLocale( ) );

        model.put( MARK_NB_ITEMS_PER_PAGE, String.valueOf( _nItemsPerPage ) );
        model.put( MARK_PAGINATOR, paginator );
        model.put( MARK_LIST_OPTIONS, paginator.getPageItems( ) );

        return getPage( PROPERTY_PAGE_TITLE_MANAGE, TEMPLATE_MANAGE_UPLOAD_IMAGE, model );
    }

    /**
     * The form of one cropping option, empty with sensible defaults when no identifier is given.
     *
     * @param request
     *            the http request
     * @param model
     *            the view model
     * @return the page
     */
    @View( value = VIEW_OPTION, securityTokenAction = ACTION_MANAGE_OPTIONS )
    public String getViewOptions( HttpServletRequest request, Models model )
    {
        String strKey = request.getParameter( PARAMETER_IDOPTION );
        Options option = new Options( );

        if ( _option != null )
        {
            option = _option;
            _option = null;
        }
        else if ( strKey != null )
        {
            option = findOption( strKey );

            if ( option == null )
            {
                return redirect( request, AdminMessageService.getMessageUrl( request, ERROR_UNKNOWN_OPTION, getViewUrl( VIEW_MANAGE_UPLOADIMAGE ),
                        AdminMessage.TYPE_STOP ) );
            }
        }
        else
        {
            option.setHeight( DEFAULT_HEIGHT );
            option.setWidth( DEFAULT_WIDTH );
            option.setX( DEFAULT_X );
            option.setY( DEFAULT_Y );
            option.setRatio( DEFAULT_RATIO );
        }

        model.put( MARK_OPTION, option );

        return getPage( PROPERTY_PAGE_TITLE_OPTION, TEMPLATE_MANAGE_VIEW_OPTION, model );
    }

    /**
     * Creates the option, or updates the one the form carries an identifier for.
     *
     * @param request
     *            the http request
     * @return the manage view
     */
    @Action( ACTION_MANAGE_OPTIONS )
    public String manageOptions( HttpServletRequest request )
    {
        Options option = new Options( );

        option.setStrict( readBoolean( request, PARAMETER_STRICT ) );
        option.setResponsive( readBoolean( request, PARAMETER_RESPONSIVE ) );
        option.setCheckImageOrigin( readBoolean( request, PARAMETER_CHECKIMAGEORIGIN ) );
        option.setModal( readBoolean( request, PARAMETER_MODAL ) );
        option.setGuides( readBoolean( request, PARAMETER_GUIDES ) );
        option.setHighlight( readBoolean( request, PARAMETER_HIGHLIGHT ) );
        option.setBackground( readBoolean( request, PARAMETER_BACKGOUND ) );
        option.setAutoCrop( readBoolean( request, PARAMETER_AUTOCROP ) );
        option.setDragCrop( readBoolean( request, PARAMETER_DRAGCROP ) );
        option.setMovable( readBoolean( request, PARAMETER_MOVABLE ) );
        option.setRotatable( readBoolean( request, PARAMETER_ROTATABLE ) );
        option.setZoomable( readBoolean( request, PARAMETER_ZOOMABLE ) );
        option.setTouchDragZoom( readBoolean( request, PARAMETER_TOUCHDRAZOOM ) );
        option.setMouseWheelZoom( readBoolean( request, PARAMETER_MOUSEWHEELZOOM ) );
        option.setCropBoxMovable( readBoolean( request, PARAMETER_CROPBOXMOVABLE ) );
        option.setCropBoxResizable( readBoolean( request, PARAMETER_CROPBOXRESIZABLE ) );
        option.setDoubleClickToggle( readBoolean( request, PARAMETER_DOUBLECLICKTOGGLE ) );

        option.setHeight( readInt( request, PARAMETER_HEIGHT, DEFAULT_HEIGHT ) );
        option.setWidth( readInt( request, PARAMETER_WIDTH, DEFAULT_WIDTH ) );
        option.setX( readInt( request, PARAMETER_X, DEFAULT_X ) );
        option.setY( readInt( request, PARAMETER_Y, DEFAULT_Y ) );
        option.setRatio( request.getParameter( PARAMETER_RATIO ) );
        option.setFieldName( request.getParameter( PARAMATER_FIELDNAME ) );

        String strKey = request.getParameter( PARAMETER_IDOPTION );
        boolean bModify = strKey != null && !strKey.isEmpty( );

        if ( bModify )
        {
            Options stored = findOption( strKey );

            if ( stored == null )
            {
                addError( ERROR_UNKNOWN_OPTION, getLocale( ) );

                return redirectView( request, VIEW_MANAGE_UPLOADIMAGE );
            }
            option.setId( stored.getId( ) );
        }

        if ( !validate( option ) )
        {
            _option = option;

            return bModify ? redirect( request, VIEW_OPTION, PARAMETER_IDOPTION, option.getId( ) ) : redirectView( request, VIEW_OPTION );
        }

        if ( bModify )
        {
            OptionsHome.update( option );
            addInfo( INFO_OPTION_UPDATE, getLocale( ) );
        }
        else
        {
            OptionsHome.create( option );
            addInfo( INFO_OPTION_CREATED, getLocale( ) );
        }

        return redirectView( request, VIEW_MANAGE_UPLOADIMAGE );
    }

    /**
     * Checks the field name, its uniqueness and the ratio, adding an error for each problem.
     *
     * @param option
     *            the option read from the form
     * @return true when the option can be stored
     */
    private boolean validate( Options option )
    {
        boolean bValid = true;
        String strFieldName = option.getFieldName( );

        if ( strFieldName == null || !strFieldName.matches( PATTERN_FIELDNAME ) )
        {
            addError( ERROR_FIELDNAME, getLocale( ) );
            bValid = false;
        }
        else
        {
            Options homonym = OptionsHome.findByFieldName( strFieldName );

            if ( homonym != null && homonym.getId( ) != option.getId( ) )
            {
                addError( ERROR_FIELDNAME_DUPLICATE, getLocale( ) );
                bValid = false;
            }
        }

        if ( option.getRatio( ) == null || !option.getRatio( ).matches( PATTERN_RATIO ) )
        {
            addError( ERROR_RATIO, getLocale( ) );
            bValid = false;
        }

        return bValid;
    }

    /**
     * The confirmation question before removing an option.
     *
     * @param request
     *            the http request
     * @return the message url
     */
    @View( value = VIEW_CONFIRM_REMOVE_OPTION, securityTokenAction = ACTION_REMOVE_OPTION )
    public String getConfirmRemoveOption( HttpServletRequest request )
    {
        UrlItem url = new UrlItem( getActionUrl( ACTION_REMOVE_OPTION ) );
        url.addParameter( PARAMETER_IDOPTION, request.getParameter( PARAMETER_IDOPTION ) );

        return redirect( request, AdminMessageService.getMessageUrl( request, MESSAGE_CONFIRM_REMOVE_OPTION, url.getUrl( ),
                AdminMessage.TYPE_CONFIRMATION ) );
    }

    /**
     * Removes the option the request names.
     *
     * @param request
     *            the http request
     * @return the manage view
     */
    @Action( ACTION_REMOVE_OPTION )
    public String removeOptions( HttpServletRequest request )
    {
        Options option = findOption( request.getParameter( PARAMETER_IDOPTION ) );

        if ( option == null )
        {
            addError( ERROR_UNKNOWN_OPTION, getLocale( ) );
        }
        else
        {
            OptionsHome.remove( option.getId( ) );
        }

        return redirectView( request, VIEW_MANAGE_UPLOADIMAGE );
    }

    /**
     * The stored option an identifier names.
     *
     * @param strKey
     *            the identifier read from the request
     * @return the option, or null when the identifier is not a number or names no option
     */
    private static Options findOption( String strKey )
    {
        if ( strKey == null || !strKey.matches( "[0-9]{1,9}" ) )
        {
            return null;
        }

        return OptionsHome.findByPrimaryKey( Integer.parseInt( strKey ) );
    }

    /**
     * Reads a checkbox, absent when unchecked.
     *
     * @param request
     *            the http request
     * @param strParameter
     *            the parameter name
     * @return its boolean value
     */
    private static boolean readBoolean( HttpServletRequest request, String strParameter )
    {
        return Boolean.parseBoolean( request.getParameter( strParameter ) );
    }

    /**
     * Reads a number, falling back on a default rather than failing on an empty or malformed field.
     *
     * @param request
     *            the http request
     * @param strParameter
     *            the parameter name
     * @param nDefault
     *            the value to use when the parameter is absent or not a number
     * @return the number
     */
    private static int readInt( HttpServletRequest request, String strParameter, int nDefault )
    {
        String strValue = request.getParameter( strParameter );

        if ( strValue == null || strValue.isEmpty( ) )
        {
            return nDefault;
        }

        try
        {
            return Integer.parseInt( strValue );
        }
        catch( NumberFormatException e )
        {
            return nDefault;
        }
    }

    /**
     * Url of the manage screen, the paginator's base.
     *
     * @return the url
     */
    private String getUrlPage( )
    {
        return new UrlItem( getViewUrl( VIEW_MANAGE_UPLOADIMAGE ) ).getUrl( );
    }
}
