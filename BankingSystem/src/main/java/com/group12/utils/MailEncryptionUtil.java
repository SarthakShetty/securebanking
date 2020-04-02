package com.group12.utils;

import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.smime.SMIMECapabilitiesAttribute;
import org.bouncycastle.asn1.smime.SMIMECapability;
import org.bouncycastle.asn1.smime.SMIMECapabilityVector;
import org.bouncycastle.asn1.smime.SMIMEEncryptionKeyPreferenceAttribute;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMESignedGenerator;

public class MailEncryptionUtil{

public static MimeMessage signMessage(MimeMessage message) throws Exception {
	
	Security.addProvider(new BouncyCastleProvider());

	CertificateDetails certDetails = CertificateUtil.getCertificateDetails("/home/ubuntu/group12-banking-system.jks","sbsPrac");
	
	// Create the SMIMESignedGenerator
	SMIMECapabilityVector capabilities = new SMIMECapabilityVector();
	capabilities.addCapability(SMIMECapability.dES_EDE3_CBC);
	capabilities.addCapability(SMIMECapability.rC2_CBC, 128);
	capabilities.addCapability(SMIMECapability.dES_CBC);
	capabilities.addCapability(SMIMECapability.aES256_CBC);
	
	ASN1EncodableVector attributes = new ASN1EncodableVector();
	attributes.add(new SMIMECapabilitiesAttribute(capabilities));
	
	IssuerAndSerialNumber issAndSer = new IssuerAndSerialNumber(new X500Name(certDetails.getX509Certificate().getIssuerDN().getName()),
			certDetails.getX509Certificate().getSerialNumber());
	attributes.add(new SMIMEEncryptionKeyPreferenceAttribute(issAndSer));
	
	SMIMESignedGenerator signer = new SMIMESignedGenerator();
	
	signer.addSignerInfoGenerator(new JcaSimpleSignerInfoGeneratorBuilder()
			.setProvider("BC")
			.setSignedAttributeGenerator(new AttributeTable(attributes))
			.build("SHA1withRSA", certDetails.getPrivateKey(), 
					certDetails.getX509Certificate()));
	
	// Add the list of certs to the generator
	List<X509Certificate> certList = new ArrayList<X509Certificate>();
	certList.add(certDetails.getX509Certificate());
	
	JcaCertStore bcerts = new JcaCertStore(certList);
	signer.addCertificates(bcerts);
	
	// Sign the message
	MimeMultipart mm = signer.generate(message);

	// Set the content of the signed message
	message.setContent(mm, mm.getContentType());
	message.saveChanges();
	
	return message;
}
}