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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import java.util.Collection;

/**
 * This class provides instances management methods (create, find, ...) for Options objects
 */

public final class OptionsHome
{
    // Static variable pointed at the DAO instance

    private static IOptionsDAO _dao = SpringContextService.getBean( "uploadimage.optionsDAO" );
    private static Plugin _plugin = PluginService.getPlugin( "uploadimage" );

    /**
     * Private constructor - this class need not be instantiated
     */
    private OptionsHome( )
    {
    }

    /**
     * Create an instance of the options class
     * 
     * @param options
     *            The instance of the Options which contains the informations to store
     * @return The instance of options which has been created with its primary key.
     */
    public static Options create( Options options )
    {
        _dao.insert( options, _plugin );

        return options;
    }

    /**
     * Update of the options which is specified in parameter
     * 
     * @param options
     *            The instance of the Options which contains the data to store
     * @return The instance of the options which has been updated
     */
    public static Options update( Options options )
    {
        _dao.store( options, _plugin );

        return options;
    }

    /**
     * Remove the options whose identifier is specified in parameter
     * 
     * @param nKey
     *            The options Id
     */
    public static void remove( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Returns an instance of a options whose identifier is specified in parameter
     * 
     * @param nKey
     *            The options primary key
     * @return an instance of Options
     */
    public static Options findByPrimaryKey( int nKey )
    {
        return _dao.load( nKey, _plugin );
    }

    /**
     * Returns an instance of a options whose field name is specified in parameter
     * 
     * @param nKey
     *            The options primary key
     * @return an instance of Options
     */
    public static Options findByFieldName( String fieldName )
    {
        return _dao.loadOptionByFieldName( fieldName, _plugin );
    }

    /**
     * Load the data of all the options objects and returns them in form of a collection
     * 
     * @return the collection which contains the data of all the options objects
     */
    public static Collection<Options> getOptionssList( )
    {
        return _dao.selectOptionssList( _plugin );
    }

    /**
     * Load the id of all the options objects and returns them in form of a collection
     * 
     * @return the collection which contains the id of all the options objects
     */
    public static Collection<Integer> getIdOptionssList( )
    {
        return _dao.selectIdOptionssList( _plugin );
    }
}
