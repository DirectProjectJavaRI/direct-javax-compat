package org.nhindirect.common.javaxcompat.mail;

/**
 * Standard SMIM headers and utility methods
 * @author Greg Meyer
 * @author Umesh Madan
 * @since 1.1
 */
public class SMIMEStandard 
{
    //
    // MIME Types
    //
    public static final String MediaType_Multipart = "multipart";
    public static final String MultiPartType_Mixed = "multipart/mixed;";
    public static final String MultiPartType_Signed = "multipart/signed; protocol=\"application/pkcs7-signature\";";
    public static final String MICAlgorithmKey = "micalg"; // Message Integrity Check Protocol   
    
    //
    // Cryptography
    //
    public static final String CmsEnvelopeMediaType = "application/pkcs7-mime";
    public static final String CmsEnvelopeMediaTypeAlt = "application/x-pkcs7-mime";   // we are forgiving when we receive messages    
    
    public static final String EncryptedContentTypeHeaderValue = "application/pkcs7-mime; smime-type=enveloped-data; name=\"smime.p7m\"";
    public static final String EncryptedContentMediaType = "application/pkcs7-mime";
    public static final String EncryptedContentMediaTypeAlternative = "application/x-pkcs7-mime";   // we are forgiving when we receive messages
    public static final String SignatureContentTypeHeaderValue = "application/pkcs7-signature; name=\"smime.p7s\"";
    public static final String SignatureContentMediaType = "application/pkcs7-signature";
    public static final String SignatureContentMediaTypeAlternative = "application/x-pkcs7-signature"; // we are forgiving when we receive messages
    public static final String SignatureDisposition = "attachment; filename=\"smime.p7s\"";
    
    public static final String SmimeTypeParameterKey = "smime-type";
    public static final String EnvelopedDataSmimeType = "enveloped-data";
    public static final String  SignedDataSmimeType = "signed-data";
    public static final String DefaultFileName = "smime.p7m";   
}
