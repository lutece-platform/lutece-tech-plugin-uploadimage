/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *	 and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *	 and the following disclaimer in the documentation and/or other materials
 *	 provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *	 contributors may be used to endorse or promote products derived from
 *	 this software without specific prior written permission.
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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class provides Data Access methods for Options objects
 */

public final class OptionsDAO implements IOptionsDAO
{
    // Constants
    private static final String SQL_QUERY_NEW_PK           = "SELECT max( id_options ) FROM uploadimage_options";
    private static final String SQL_QUERY_SELECT           = "SELECT id_options, strict, responsive, checkImageOrigin, modal, guides, highlight, background, autoCrop, dragCrop, movable, rotatable, zoomable, touchDragZoom, mouseWheelZoom, cropBoxMovable, cropBoxResizable, doubleClickToggle, width, height, x, y, ratio, max_height, fieldName FROM uploadimage_options WHERE id_options = ?";
    private static final String SQL_QUERY_INSERT           = "INSERT INTO uploadimage_options ( id_options, strict, responsive, checkImageOrigin, modal, guides, highlight, background, autoCrop, dragCrop, movable, rotatable, zoomable, touchDragZoom, mouseWheelZoom, cropBoxMovable, cropBoxResizable, doubleClickToggle, width, height, x, y, ratio, max_height, fieldName ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final String SQL_QUERY_DELETE           = "DELETE FROM uploadimage_options WHERE id_options = ? ";
    private static final String SQL_QUERY_UPDATE           = "UPDATE uploadimage_options SET id_options = ?, strict = ?, responsive = ?, checkImageOrigin = ?, modal = ?, guides = ?, highlight = ?, background = ?, autoCrop = ?, dragCrop = ?, movable = ?, rotatable = ?, zoomable = ?, touchDragZoom = ?, mouseWheelZoom = ?, cropBoxMovable = ?, cropBoxResizable = ?, doubleClickToggle = ?, width = ?, height = ?, x = ?, y = ?, ratio = ?, max_height = ?, fieldName = ? WHERE id_options = ?";
    private static final String SQL_QUERY_SELECTALL        = "SELECT id_options, strict, responsive, checkImageOrigin, modal, guides, highlight, background, autoCrop, dragCrop, movable, rotatable, zoomable, touchDragZoom, mouseWheelZoom, cropBoxMovable, cropBoxResizable, doubleClickToggle, width, height, x, y, ratio, max_height , fieldName FROM uploadimage_options";
    private static final String SQL_QUERY_SELECTALL_ID     = "SELECT id_options FROM uploadimage_options";
    private static final String SQL_QUERY_SELECT_FIELDNAME = "SELECT id_options, strict, responsive, checkImageOrigin, modal, guides, highlight, background, autoCrop, dragCrop, movable, rotatable, zoomable, touchDragZoom, mouseWheelZoom, cropBoxMovable, cropBoxResizable, doubleClickToggle, width, height, x, y, ratio, max_height, fieldName FROM uploadimage_options u WHERE u.fieldName = ?";

    /**
     * Generates a new primary key
     *
     * @param plugin
     *         The Plugin
     * @return The new primary key
     */
    public int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery( );

        int nKey = 1;

        if ( daoUtil.next( ) )
        {
            nKey = daoUtil.getInt( 1 ) + 1;
        }

        daoUtil.free( );

        return nKey;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void insert( Options options, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        options.setId( newPrimaryKey( plugin ) );

        daoUtil.setInt( 1, options.getId( ) );
        daoUtil.setBoolean( 2, options.getStrict( ) );
        daoUtil.setBoolean( 3, options.getResponsive( ) );
        daoUtil.setBoolean( 4, options.getCheckImageOrigin( ) );
        daoUtil.setBoolean( 5, options.getModal( ) );
        daoUtil.setBoolean( 6, options.getGuides( ) );
        daoUtil.setBoolean( 7, options.getHighlight( ) );
        daoUtil.setBoolean( 8, options.getBackground( ) );
        daoUtil.setBoolean( 9, options.getAutoCrop( ) );
        daoUtil.setBoolean( 10, options.getDragCrop( ) );
        daoUtil.setBoolean( 11, options.getMovable( ) );
        daoUtil.setBoolean( 12, options.getRotatable( ) );
        daoUtil.setBoolean( 13, options.getZoomable( ) );
        daoUtil.setBoolean( 14, options.getTouchDragZoom( ) );
        daoUtil.setBoolean( 15, options.getMouseWheelZoom( ) );
        daoUtil.setBoolean( 16, options.getCropBoxMovable( ) );
        daoUtil.setBoolean( 17, options.getCropBoxResizable( ) );
        daoUtil.setBoolean( 18, options.getDoubleClickToggle( ) );
        daoUtil.setInt( 19, options.getWidth( ) );
        daoUtil.setInt( 20, options.getHeight( ) );
        daoUtil.setInt( 21, options.getX( ) );
        daoUtil.setInt( 22, options.getY( ) );
        daoUtil.setString( 23, options.getRatio( ) );
        daoUtil.setInt( 24, options.getMaxHeight( ) );
        daoUtil.setString( 25, options.getFieldName( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Options load( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeQuery( );

        Options options = null;

        if ( daoUtil.next( ) )
        {
            options = new Options( );
            options.setId( daoUtil.getInt( 1 ) );
            options.setStrict( daoUtil.getBoolean( 2 ) );
            options.setResponsive( daoUtil.getBoolean( 3 ) );
            options.setCheckImageOrigin( daoUtil.getBoolean( 4 ) );
            options.setModal( daoUtil.getBoolean( 5 ) );
            options.setGuides( daoUtil.getBoolean( 6 ) );
            options.setHighlight( daoUtil.getBoolean( 7 ) );
            options.setBackground( daoUtil.getBoolean( 8 ) );
            options.setAutoCrop( daoUtil.getBoolean( 9 ) );
            options.setDragCrop( daoUtil.getBoolean( 10 ) );
            options.setMovable( daoUtil.getBoolean( 11 ) );
            options.setRotatable( daoUtil.getBoolean( 12 ) );
            options.setZoomable( daoUtil.getBoolean( 13 ) );
            options.setTouchDragZoom( daoUtil.getBoolean( 14 ) );
            options.setMouseWheelZoom( daoUtil.getBoolean( 15 ) );
            options.setCropBoxMovable( daoUtil.getBoolean( 16 ) );
            options.setCropBoxResizable( daoUtil.getBoolean( 17 ) );
            options.setDoubleClickToggle( daoUtil.getBoolean( 18 ) );
            options.setWidth( daoUtil.getInt( 19 ) );
            options.setHeight( daoUtil.getInt( 20 ) );
            options.setX( daoUtil.getInt( 21 ) );
            options.setY( daoUtil.getInt( 22 ) );
            options.setRatio( daoUtil.getString( 23 ) );
            options.setMaxHeight( daoUtil.getInt( 24 ) );
            options.setFieldName( daoUtil.getString( 25 ) );
        }

        daoUtil.free( );
        return options;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void delete( int nKey, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE, plugin );
        daoUtil.setInt( 1, nKey );
        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void store( Options options, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE, plugin );

        daoUtil.setInt( 1, options.getId( ) );
        daoUtil.setBoolean( 2, options.getStrict( ) );
        daoUtil.setBoolean( 3, options.getResponsive( ) );
        daoUtil.setBoolean( 4, options.getCheckImageOrigin( ) );
        daoUtil.setBoolean( 5, options.getModal( ) );
        daoUtil.setBoolean( 6, options.getGuides( ) );
        daoUtil.setBoolean( 7, options.getHighlight( ) );
        daoUtil.setBoolean( 8, options.getBackground( ) );
        daoUtil.setBoolean( 9, options.getAutoCrop( ) );
        daoUtil.setBoolean( 10, options.getDragCrop( ) );
        daoUtil.setBoolean( 11, options.getMovable( ) );
        daoUtil.setBoolean( 12, options.getRotatable( ) );
        daoUtil.setBoolean( 13, options.getZoomable( ) );
        daoUtil.setBoolean( 14, options.getTouchDragZoom( ) );
        daoUtil.setBoolean( 15, options.getMouseWheelZoom( ) );
        daoUtil.setBoolean( 16, options.getCropBoxMovable( ) );
        daoUtil.setBoolean( 17, options.getCropBoxResizable( ) );
        daoUtil.setBoolean( 18, options.getDoubleClickToggle( ) );
        daoUtil.setInt( 19, options.getWidth( ) );
        daoUtil.setInt( 20, options.getHeight( ) );
        daoUtil.setInt( 21, options.getX( ) );
        daoUtil.setInt( 22, options.getY( ) );
        daoUtil.setString( 23, options.getRatio( ) );
        daoUtil.setInt( 24, options.getMaxHeight( ) );
        daoUtil.setString( 25, options.getFieldName( ) );
        daoUtil.setInt( 26, options.getId( ) );

        daoUtil.executeUpdate( );
        daoUtil.free( );
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Options> selectOptionssList( Plugin plugin )
    {
        Collection<Options> optionsList = new ArrayList<Options>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            Options options = new Options( );

            options.setId( daoUtil.getInt( 1 ) );
            options.setStrict( daoUtil.getBoolean( 2 ) );
            options.setResponsive( daoUtil.getBoolean( 3 ) );
            options.setCheckImageOrigin( daoUtil.getBoolean( 4 ) );
            options.setModal( daoUtil.getBoolean( 5 ) );
            options.setGuides( daoUtil.getBoolean( 6 ) );
            options.setHighlight( daoUtil.getBoolean( 7 ) );
            options.setBackground( daoUtil.getBoolean( 8 ) );
            options.setAutoCrop( daoUtil.getBoolean( 9 ) );
            options.setDragCrop( daoUtil.getBoolean( 10 ) );
            options.setMovable( daoUtil.getBoolean( 11 ) );
            options.setRotatable( daoUtil.getBoolean( 12 ) );
            options.setZoomable( daoUtil.getBoolean( 13 ) );
            options.setTouchDragZoom( daoUtil.getBoolean( 14 ) );
            options.setMouseWheelZoom( daoUtil.getBoolean( 15 ) );
            options.setCropBoxMovable( daoUtil.getBoolean( 16 ) );
            options.setCropBoxResizable( daoUtil.getBoolean( 17 ) );
            options.setDoubleClickToggle( daoUtil.getBoolean( 18 ) );
            options.setWidth( daoUtil.getInt( 19 ) );
            options.setHeight( daoUtil.getInt( 20 ) );
            options.setX( daoUtil.getInt( 21 ) );
            options.setY( daoUtil.getInt( 22 ) );
            options.setRatio( daoUtil.getString( 23 ) );
            options.setMaxHeight( daoUtil.getInt( 24 ) );
            options.setFieldName( daoUtil.getString( 25 ) );

            optionsList.add( options );
        }

        daoUtil.free( );
        return optionsList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<Integer> selectIdOptionssList( Plugin plugin )
    {
        Collection<Integer> optionsList = new ArrayList<Integer>( );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_ID, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            optionsList.add( daoUtil.getInt( 1 ) );
        }

        daoUtil.free( );
        return optionsList;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Options loadOptionByFieldName( String fieldName, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_FIELDNAME, plugin );
        daoUtil.setString( 1, fieldName );
        daoUtil.executeQuery( );

        Options options = null;

        if ( daoUtil.next( ) )
        {
            options = new Options( );
            options.setId( daoUtil.getInt( 1 ) );
            options.setStrict( daoUtil.getBoolean( 2 ) );
            options.setResponsive( daoUtil.getBoolean( 3 ) );
            options.setCheckImageOrigin( daoUtil.getBoolean( 4 ) );
            options.setModal( daoUtil.getBoolean( 5 ) );
            options.setGuides( daoUtil.getBoolean( 6 ) );
            options.setHighlight( daoUtil.getBoolean( 7 ) );
            options.setBackground( daoUtil.getBoolean( 8 ) );
            options.setAutoCrop( daoUtil.getBoolean( 9 ) );
            options.setDragCrop( daoUtil.getBoolean( 10 ) );
            options.setMovable( daoUtil.getBoolean( 11 ) );
            options.setRotatable( daoUtil.getBoolean( 12 ) );
            options.setZoomable( daoUtil.getBoolean( 13 ) );
            options.setTouchDragZoom( daoUtil.getBoolean( 14 ) );
            options.setMouseWheelZoom( daoUtil.getBoolean( 15 ) );
            options.setCropBoxMovable( daoUtil.getBoolean( 16 ) );
            options.setCropBoxResizable( daoUtil.getBoolean( 17 ) );
            options.setDoubleClickToggle( daoUtil.getBoolean( 18 ) );
            options.setWidth( daoUtil.getInt( 19 ) );
            options.setHeight( daoUtil.getInt( 20 ) );
            options.setX( daoUtil.getInt( 21 ) );
            options.setY( daoUtil.getInt( 22 ) );
            options.setRatio( daoUtil.getString( 23 ) );
            options.setMaxHeight( daoUtil.getInt( 24 ) );
            options.setFieldName( daoUtil.getString( 25 ) );
        }

        daoUtil.free( );
        return options;
    }

}
