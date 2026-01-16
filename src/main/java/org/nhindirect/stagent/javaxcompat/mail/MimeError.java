package org.nhindirect.stagent.javaxcompat.mail;

/**
 * Enumerated causes of MimeExceptions
 * @author Greg Meyer
 * @author Umesh Madan
 */
public enum MimeError 
{
    Unexpected,
    InvalidCRLF,
    InvalidMimeEntity,
    InvalidHeader,
    InvalidBody,
    InvalidBodySubpart,
    MissingNameValueSeparator,
    MissingHeaderValue,
    MissingBody,
    ContentTypeMismatch,
    TransferEncodingMismatch,
    Base64EncodingRequired,
    DisallowedEncryptionAlgorithm
}
