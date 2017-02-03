package qa.utility.aws;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

public class WebServicesFactory {
	
	private final DefaultAWSCredentialsProviderChain credentials;
	
	public WebServicesFactory(final DefaultAWSCredentialsProviderChain credentials){
		this.credentials = credentials;
	}
	
	public AmazonS3Utility amazonS3Utility(){
		return new AmazonS3Utility(credentials);
	}
	
	public AmazonEC2Utility amazonEC2Utility(){
		return new AmazonEC2Utility(credentials);
	}

}
