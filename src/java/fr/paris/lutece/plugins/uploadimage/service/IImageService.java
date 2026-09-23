package fr.paris.lutece.plugins.uploadimage.service;

import fr.paris.lutece.plugins.uploadimage.business.Options;

/**
 * Stores the cropping options.
 */
public interface IImageService {
	
	/**
	 * Creates an option.
	 *
	 * @param option
	 *            the option to store
	 */
	public void createOption(Options option);

}
