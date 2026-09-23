package fr.paris.lutece.plugins.uploadimage.service;

import fr.paris.lutece.plugins.uploadimage.business.Options;
import fr.paris.lutece.plugins.uploadimage.business.OptionsHome;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Default implementation of the option service.
 */
@ApplicationScoped
public class ImageService implements IImageService
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createOption(Options option) {
		
		OptionsHome.create(option);
		
	}

}
