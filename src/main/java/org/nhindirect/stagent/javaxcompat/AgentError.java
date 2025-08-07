package org.nhindirect.stagent.javaxcompat;

/**
 * Enumeration of security and trust agent errors.
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public enum AgentError 
{
    Unexpected,
    MissingTo,
    MissingFrom,
    MissingMessage,
    MessageNotWrapped,
    NoRecipients,
    NoSender,
    InvalidSignature,
    InvalidEncryption,
    UntrustedMessage,
    UntrustedSender,
    UnknownRecipient,
    UnsignedMessage,
    MissingSenderSignature,
    MissingSenderCertificate,
    MissingRecipientCertificate,
    NoTrustedRecipients,
    AllCertsInResolverInvalid,
    InvalidPolicy,
    MessageTamperDectection
}