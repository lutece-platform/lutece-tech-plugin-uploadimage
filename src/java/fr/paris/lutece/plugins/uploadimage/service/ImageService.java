package fr.paris.lutece.plugins.uploadimage.service;

import fr.paris.lutece.plugins.uploadimage.business.Options;
import fr.paris.lutece.plugins.uploadimage.business.OptionsHome;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImageService implements IImageService
{

	@Override
	public void createOption(Options option) {
		
		OptionsHome.create(option);
		
	}

}
