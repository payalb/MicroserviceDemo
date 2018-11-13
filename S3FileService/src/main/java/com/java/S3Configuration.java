package com.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;

@Configuration
public class S3Configuration {
	
	@Autowired S3Properties properties;
	/*An IAM user or an AWS Account can request temporary security credentials (see Making Requests) using the AWS SDK for Java and use them to access Amazon S3. These credentials expire after the specified session duration. To use IAM temporary security credentials, do the following:

		Create an instance of the AWSSecurityTokenServiceClient class. For information about providing credentials, see Using the AWS SDKs, CLI, and Explorers.

		Assume the desired role by calling the assumeRole() method of the Security Token Service (STS) client.

		Start a session by calling the getSessionToken() method of the STS client. You provide session information to this method using a GetSessionTokenRequest object.

		The method returns the temporary security credentials.

		Package the temporary security credentials into a BasicSessionCredentials object. You use this object to provide the temporary security credentials to your Amazon S3 client.

		Create an instance of the AmazonS3Client class using the temporary security credentials. You send requests to Amazon S3 using this client. If you send requests using expired credentials, Amazon S3 will return an error.

		Note

		If you obtain temporary security credentials using your AWS account security credentials, the temporary credentials are valid for only one hour. You can specify the session duration only if you use IAM user credentials to request a session.*/
	@Bean("client")
	public AmazonS3Client getClient() {
		  AWSSecurityTokenServiceClient stsClient =
	                new AWSSecurityTokenServiceClient(new BasicAWSCredentials(properties.getAws().getAccessKeyId(), properties.getAws().getAccessKeySecret()));

		  /*The duration of temporary credentials can range from 900 seconds (15 minutes) to 129600 seconds (36 hours) for IAM users. If a duration isn't specified, then 43200 seconds (12 hours) is used by default.
		   * It is strongly recommended, from a security standpoint, that you use IAM users instead of the root account for AWS access.
*/	        // Start a new session for managing a service instance's bucket
	        GetSessionTokenRequest getSessionTokenRequest =
	                new GetSessionTokenRequest().withDurationSeconds(43200);

	        // Get the session token for the service instance's bucket
	        com.amazonaws.services.securitytoken.model.Credentials credentials= stsClient.getSessionToken(getSessionTokenRequest).getCredentials();

	      
		BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(),
				credentials.getSecretAccessKey(),
				credentials.getSessionToken());
		 return new AmazonS3Client(basicSessionCredentials);
	}

}
