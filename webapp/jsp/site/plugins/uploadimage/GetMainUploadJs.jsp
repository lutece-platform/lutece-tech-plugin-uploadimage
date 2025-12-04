

<%@ page contentType="application/javascript" errorPage="../../ErrorPagePortal.jsp" trimDirectiveWhitespaces="true" %>

<jsp:useBean id="uploadimageApp" scope="request" class="fr.paris.lutece.plugins.uploadimage.web.UploadimageApp" />

<%= uploadimageApp.getMainUploadJs( request ) %>