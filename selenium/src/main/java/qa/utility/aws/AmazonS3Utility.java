package qa.utility.aws;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;

import qa.SeleniumTest;
import qa.utility.FileTool;

/**
 * 
 * @author lshields - lshields@bizjournals.com
 *
 */

public class AmazonS3Utility {
	//temporary
	private static String bucketName     = "qa-reports.bizj-internal.com";
	private static String keyName;
	private String accessKey;
	private AmazonS3 s3client;
	TransferManager fileManager;
	
	public AmazonS3Utility(DefaultAWSCredentialsProviderChain credentials){
		s3client = new AmazonS3Client(credentials);
		fileManager = new TransferManager(credentials.getCredentials());
		keyName = "Nightly Regression";
		accessKey = credentials.getCredentials().getAWSAccessKeyId();
		
	}
	
	public void uploadFileToS3(File file){
		try{
			
		//try single file upload
		 s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
		}catch(AmazonServiceException ase){
			
			//Procedure for an amazon service exception
			SeleniumTest.logger.severe("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon S3, but was rejected with an error response for some reason."+System.lineSeparator());
			SeleniumTest.logger.severe("Error Message:    " + ase.getMessage()+System.lineSeparator());
			SeleniumTest.logger.severe("HTTP Status Code: " + ase.getStatusCode()+System.lineSeparator());
			SeleniumTest.logger.severe("AWS Error Code:   " + ase.getErrorCode()+System.lineSeparator());
			SeleniumTest.logger.severe("Error Type:       " + ase.getErrorType()+System.lineSeparator());
			SeleniumTest.logger.severe("Request ID:       " + ase.getRequestId()+System.lineSeparator());
			SeleniumTest.logger.severe("Access Key ID:    " + accessKey+System.lineSeparator());			
		}catch(AmazonClientException ace){
			
			//procedure for an amazon client exception
			SeleniumTest.logger.severe("Caught an AmazonClientException, which means the client encountered " +
                    "an internal error while trying to communicate with S3, such as not being able to access "
                    + "the network."+System.lineSeparator());
			SeleniumTest.logger.severe("Error Message: " + ace.getMessage()+System.lineSeparator());
		}
	}
	
	public void uploadDirectoryToS3(File file){						
		
		//try directory upload using file manager		
		MultipleFileUpload myUpload = fileManager.uploadDirectory(bucketName, keyName, file, true);
		
//		//metadata
//		S3ResponseMetadata md = myUpload.getCachedResponseMetadata(req);
//		System.out.println("Host ID: " + md.getHostId() + " RequestID: " + md.getRequestId());
				
		
		if (myUpload.isDone() == false) {
			   System.out.println("Access Key: "+accessKey);
			   System.out.println("Destination: " + bucketName);
			   System.out.println("Folder: "+keyName);
		       System.out.println("Transfer: " + myUpload.getDescription());
		       System.out.println("  - State: " + myUpload.getState());
		       System.out.println("  - Progress: "
		                       + myUpload.getProgress().getBytesTransferred());
		}		 
		
		 
		// Or you can block the current thread and wait for your transfer to
		// to complete. If the transfer fails, this method will throw an
		// AmazonClientException or AmazonServiceException detailing the reason.
		try{
		myUpload.waitForCompletion();
		
		//monitor upload
		if (myUpload.isDone() == false) {
			   System.out.println(keyName);
		       System.out.println("Transfer: " + myUpload.getDescription());
		       System.out.println("  - State: " + myUpload.getState());
		       System.out.println("  - Progress: "
		                       + myUpload.getProgress().getBytesTransferred());		      
		}	
		
		}catch(AmazonServiceException ase){
			
			//Procedure for an amazon service exception
			SeleniumTest.logger.severe("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon S3, but was rejected with an error response for some reason."+System.lineSeparator());
			SeleniumTest.logger.severe("Error Message:    " + ase.getMessage()+System.lineSeparator());
			SeleniumTest.logger.severe("HTTP Status Code: " + ase.getStatusCode()+System.lineSeparator());
			SeleniumTest.logger.severe("AWS Error Code:   " + ase.getErrorCode()+System.lineSeparator());
			SeleniumTest.logger.severe("Error Type:       " + ase.getErrorType()+System.lineSeparator());
			SeleniumTest.logger.severe("Request ID:       " + ase.getRequestId()+System.lineSeparator());
		}catch(AmazonClientException ace){
			
			//procedure for an amazon client exception
			SeleniumTest.logger.severe("Caught an AmazonClientException, which means the client encountered " +
                    "an internal error while trying to communicate with S3, such as not being able to access "
                    + "the network."+System.lineSeparator());
			SeleniumTest.logger.severe("Error Message: " + ace.getMessage()+System.lineSeparator());
		}catch(Exception ex){
			SeleniumTest.logger.severe(ex.getMessage()+System.lineSeparator());
		}
		// After the upload is complete, call shutdownNow to release the resources.
		fileManager.shutdownNow();		
	}
	
	public void copyFileToS3(String source, String destination) {
		try {

			// try single file copy
			s3client.copyObject(new CopyObjectRequest(bucketName, source, bucketName, destination));
		} catch (AmazonServiceException ase) {

			// Procedure for an amazon service exception
			SeleniumTest.logger.severe("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason."
					+ System.lineSeparator());
			SeleniumTest.logger.severe("Error Message:    " + ase.getMessage() + System.lineSeparator());
			SeleniumTest.logger.severe("HTTP Status Code: " + ase.getStatusCode() + System.lineSeparator());
			SeleniumTest.logger.severe("AWS Error Code:   " + ase.getErrorCode() + System.lineSeparator());
			SeleniumTest.logger.severe("Error Type:       " + ase.getErrorType() + System.lineSeparator());
			SeleniumTest.logger.severe("Request ID:       " + ase.getRequestId() + System.lineSeparator());
			SeleniumTest.logger.severe("Access Key ID:    " + accessKey + System.lineSeparator());
		} catch (AmazonClientException ace) {

			// procedure for an amazon client exception
			SeleniumTest.logger.severe("Caught an AmazonClientException, which means the client encountered "
					+ "an internal error while trying to communicate with S3, such as not being able to access "
					+ "the network." + System.lineSeparator());
			SeleniumTest.logger.severe("Error Message: " + ace.getMessage() + System.lineSeparator());
		}
	}

	public void deleteFileFromS3(String source) {
		try {

			// try single file copy
			s3client.deleteObject(new DeleteObjectRequest(bucketName, source));
		} catch (AmazonServiceException ase) {

			// Procedure for an amazon service exception
			SeleniumTest.logger.severe("Caught an AmazonServiceException, which means your request made it "
					+ "to Amazon S3, but was rejected with an error response for some reason."
					+ System.lineSeparator());
			SeleniumTest.logger.severe("Error Message:    " + ase.getMessage() + System.lineSeparator());
			SeleniumTest.logger.severe("HTTP Status Code: " + ase.getStatusCode() + System.lineSeparator());
			SeleniumTest.logger.severe("AWS Error Code:   " + ase.getErrorCode() + System.lineSeparator());
			SeleniumTest.logger.severe("Error Type:       " + ase.getErrorType() + System.lineSeparator());
			SeleniumTest.logger.severe("Request ID:       " + ase.getRequestId() + System.lineSeparator());
			SeleniumTest.logger.severe("Access Key ID:    " + accessKey + System.lineSeparator());
		} catch (AmazonClientException ace) {

			// procedure for an amazon client exception
			SeleniumTest.logger.severe("Caught an AmazonClientException, which means the client encountered "
					+ "an internal error while trying to communicate with S3, such as not being able to access "
					+ "the network." + System.lineSeparator());
			SeleniumTest.logger.severe("Error Message: " + ace.getMessage() + System.lineSeparator());
		}
	}

	public void ArchiveFilesFromS3() {

		// setup archive criteria
		SimpleDateFormat dateFormat = new SimpleDateFormat("-yyyyMMM");
		SimpleDateFormat altdateFormat = new SimpleDateFormat("yyyy-MM-");
		Calendar c = Calendar.getInstance();
		Date now = new java.util.Date();
		c.setTime(now);
		c.add(Calendar.MONTH, -1);
		dateFormat.format(c.getTime());

		// only run on the first of each month
		if (c.get(Calendar.DAY_OF_MONTH) == 1) {
			
			//delete local qa reports so that they arent re-uploaded to the s3 bucket
			FileTool fileManager = new FileTool(System.getProperty("user.dir") + "/D3/qa");
		    fileManager.deleteDirectoryContents();

			// execute archiving on S3
			try {
				System.out.println("Listing objects");
				final ListObjectsRequest req = new ListObjectsRequest().withBucketName(bucketName).withMaxKeys(2);
				ObjectListing result;
				do {
					result = s3client.listObjects(req);

					// loop through the list of files in the bucket
					for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {

						// If a file is old enough and the name matches either
						// of
						// the specified date formats, then archive it.
						if (!objectSummary.getKey().contains("Archive")) {
							if (objectSummary.getKey().contains(dateFormat.format(c.getTime()))
									|| objectSummary.getKey().contains(altdateFormat.format(c.getTime()))) {
								String destination = "Archive/"
										+ c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + "/"
										+ objectSummary.getKey();
								copyFileToS3(objectSummary.getKey(), destination);
								deleteFileFromS3(objectSummary.getKey());
								System.out.println("Archived " + objectSummary.getKey());
							}
						}
					}
					// System.out.println("Next Continuation Token : " +
					// result.getMarker());
					req.setMarker(result.getNextMarker());
				} while (result.isTruncated() == true);

			} catch (AmazonServiceException ase) {

				// Procedure for an amazon service exception
				SeleniumTest.logger.severe("Caught an AmazonServiceException, which means your request made it "
						+ "to Amazon S3, but was rejected with an error response for some reason."
						+ System.lineSeparator());
				SeleniumTest.logger.severe("Error Message:    " + ase.getMessage() + System.lineSeparator());
				SeleniumTest.logger.severe("HTTP Status Code: " + ase.getStatusCode() + System.lineSeparator());
				SeleniumTest.logger.severe("AWS Error Code:   " + ase.getErrorCode() + System.lineSeparator());
				SeleniumTest.logger.severe("Error Type:       " + ase.getErrorType() + System.lineSeparator());
				SeleniumTest.logger.severe("Request ID:       " + ase.getRequestId() + System.lineSeparator());
				SeleniumTest.logger.severe("Access Key ID:    " + accessKey + System.lineSeparator());
			} catch (AmazonClientException ace) {

				// procedure for an amazon client exception
				SeleniumTest.logger.severe("Caught an AmazonClientException, which means the client encountered "
						+ "an internal error while trying to communicate with S3, such as not being able to access "
						+ "the network." + System.lineSeparator());
				SeleniumTest.logger.severe("Error Message: " + ace.getMessage() + System.lineSeparator());
			}
		}
	}
}
