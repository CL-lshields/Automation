package qa.utility.aws;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CancelSpotInstanceRequestsRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeSpotInstanceRequestsRequest;
import com.amazonaws.services.ec2.model.DescribeSpotInstanceRequestsResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.LaunchSpecification;
import com.amazonaws.services.ec2.model.RequestSpotInstancesRequest;
import com.amazonaws.services.ec2.model.RequestSpotInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.SpotInstanceRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

import qa.SeleniumTest;
import qa.utility.ExcelDriver;

public class AmazonEC2Utility {

	private AmazonEC2 ec2Client;
	private String accessKey;
	private RequestSpotInstancesResult requestResult;
	private static ArrayList<String> spotInstanceRequestIds = new ArrayList<String>();
	private static ArrayList<String> instanceIds = new ArrayList<String>();
	private HashMap<String,String> config;
	

	public AmazonEC2Utility(DefaultAWSCredentialsProviderChain credentials) {
		ec2Client = new AmazonEC2Client(credentials);
		accessKey = credentials.getCredentials().getAWSAccessKeyId();
		ExcelDriver config = new ExcelDriver();
		config.readInWorkbook(new File(ExcelDriver.getMagentoFilename()));
		this.config = config.getDataArray().get(1);
	}

	
	/**
	 * Create new security group to be assigned to new EC2 instances.
	 */
	public void createSecutiryGroup() {
		// Create a new security group.
		try {
			CreateSecurityGroupRequest securityGroupRequest = new CreateSecurityGroupRequest("NightlyRegressionGroup",
					"Nightly Regression Security Group");
			ec2Client.createSecurityGroup(securityGroupRequest);			
		} catch (AmazonServiceException ase) {
			// Likely this means that the group is already created, so ignore.
			System.out.println(ase.getMessage());
			SeleniumTest.logger.severe("Error Message:    " + ase.getMessage() + System.lineSeparator());
		}

		// Get the IP of the current host, so that we can limit the Security
		// Group by default to the ip range associated with your subnet.
		ArrayList<String> ipRanges = new ArrayList<String>();
		try {
			InetAddress addr = InetAddress.getLocalHost();

			// Get IP Address and create a range that you would like to
			// populate.
			ipRanges.add(addr.getHostAddress() + "/10");
		} catch (UnknownHostException e) {
			SeleniumTest.logger.severe("Error Message:    " + e.getMessage() + System.lineSeparator());
		}

		// Open up port 22 for TCP traffic to the associated IP
		// from above (e.g. ssh traffic).
		ArrayList<IpPermission> ipPermissions = new ArrayList<IpPermission>();
		IpPermission ipPermission = new IpPermission();
		ipPermission.setIpProtocol("tcp");
		ipPermission.setFromPort(new Integer(22));
		ipPermission.setToPort(new Integer(22));
		ipPermission.setIpRanges(ipRanges);
		ipPermissions.add(ipPermission);

		try {
			// Authorize the ports to the used.
			AuthorizeSecurityGroupIngressRequest ingressRequest = new AuthorizeSecurityGroupIngressRequest(
					"NightlyRegressionGroup", ipPermissions);
			ec2Client.authorizeSecurityGroupIngress(ingressRequest);
		} catch (AmazonServiceException ase) {
			// Ignore because this likely means the zone has
			// already been authorized.
			System.out.println(ase.getMessage());
			SeleniumTest.logger.severe("Error Message:    " + ase.getMessage() + System.lineSeparator());
		}
	}

	/**
	 * Request a number of Amazon EC2 spot instances for a maximum bid.
	 * 
	 * @param bid - a double specifying a maximum bid for server space.
	 * @param instances - the number of instances you wish to create.
	 */
	public void requestSpotInstance() {
		// Initializes a Spot Instance Request
		RequestSpotInstancesRequest requestRequest = new RequestSpotInstancesRequest();

		// Request 1 x t1.micro instance with a bid price of $0.03.
		requestRequest.setSpotPrice(config.get("bidPrice"));// "0.03"
		requestRequest.setInstanceCount(Integer.valueOf(config.get("InstanceNumber")));// 1

		// Setup the specifications of the launch. This includes the
		// instance type (e.g. t1.micro) and the latest Amazon Linux
		// AMI id available. Note, you should always use the latest
		// Amazon Linux AMI id or another of your choosing.
		LaunchSpecification launchSpecification = new LaunchSpecification();
		launchSpecification.setImageId("ami-8c1fece5");
		launchSpecification.setInstanceType(config.get("InstanceType"));

		// Add the security group to the request.
		ArrayList<String> securityGroups = new ArrayList<String>();
		securityGroups.add("NightlyRegressionGroup");
		launchSpecification.setSecurityGroups(securityGroups);

		// Add the launch specifications to the request.
		requestRequest.setLaunchSpecification(launchSpecification);

		// Call the RequestSpotInstance API.
		requestResult = ec2Client.requestSpotInstances(requestRequest);
	}

	/**
	 * Get the current status of all spot instances requested by this AmazonEC2Utility.
	 */
	public void spotInstanceStatus() {
		// only return status if there are spot requests active
		if (requestResult.getSpotInstanceRequests().isEmpty()) {
			SeleniumTest.logger.severe("No spot requests currently active");
		} else {
			List<SpotInstanceRequest> requestResponses = requestResult.getSpotInstanceRequests();

//			// Setup an arraylist to collect all of the request ids we want to
//			// watch hit the running state.
//			ArrayList<String> spotInstanceRequestIds = new ArrayList<String>();

			// Add all of the request ids to the hashset, so we can determine
			// when they hit the
			// active state.
			for (SpotInstanceRequest requestResponse : requestResponses) {
				System.out.println("Created Spot Request: " + requestResponse.getSpotInstanceRequestId());
				SeleniumTest.logger.info("Retreived Spot Request: " + requestResponse.getSpotInstanceRequestId());
				spotInstanceRequestIds.add(requestResponse.getSpotInstanceRequestId());
			}

			// Create a variable that will track whether there are any
			// requests still in the open state.
			boolean anyOpen;

			do {
				// Create the describeRequest object with all of the request ids
				// to monitor (e.g. that we started).
				DescribeSpotInstanceRequestsRequest describeRequest = new DescribeSpotInstanceRequestsRequest();
				describeRequest.setSpotInstanceRequestIds(spotInstanceRequestIds);

				// Initialize the anyOpen variable to false - which assumes
				// there
				// are no requests open unless we find one that is still open.
				anyOpen = false;

				try {
					// Retrieve all of the requests we want to monitor.
					DescribeSpotInstanceRequestsResult describeResult = ec2Client
							.describeSpotInstanceRequests(describeRequest);
					List<SpotInstanceRequest> describeResponses = describeResult.getSpotInstanceRequests();
					

			        // Look through each request and determine if they are all in
			        // the active state.
			        for (SpotInstanceRequest describeResponse : describeResponses) {
			            // If the state is open, it hasn't changed since we attempted
			            // to request it. There is the potential for it to transition
			            // almost immediately to closed or cancelled so we compare
			            // against open instead of active.
			        	if (describeResponse.getState().equals("open")) {
			        		anyOpen = true;			        		
			        		break;
			        	}
			        	instanceIds.add(describeResponse.getInstanceId());
			        }
				} catch (AmazonServiceException e) {
					// If we have an exception, ensure we don't break out of
					// the loop. This prevents the scenario where there was
					// blip on the wire.
					anyOpen = true;
					SeleniumTest.logger.severe(e.getMessage()+System.lineSeparator());
				}
				
				try {
				        // Sleep for 60 seconds.
				        Thread.sleep(60*1000);
				} catch (Exception e) {
				   // Do nothing because it woke up early.
					SeleniumTest.logger.severe(e.getMessage()+System.lineSeparator());
				}
			} while (anyOpen);
		}
	}
	
	/**
	 * W.I.P 
	 * this method is still under development and should not be used
	 */
	public void launchEc2Instance(){
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();

		runInstancesRequest.withImageId("ami-8c1fece5")
			               .withInstanceType(config.get("InstanceType"))
			               .withMinCount(1)
			               .withMaxCount(1)
			               //TODO - keypair
			               .withKeyName("my-key-pair")
			               .withSecurityGroups("NightlyRegressionGroup");
		RunInstancesResult runInstancesResult = ec2Client.runInstances(runInstancesRequest);		
		DescribeInstancesResult result= ec2Client.describeInstances();
		List <Reservation> list  = result.getReservations();
		for (Reservation res:list) {
		     List <Instance> instanceList= res.getInstances();
		     for (Instance instance:instanceList){
		             System.out.println("Instance Public IP :" + instance.getPublicIpAddress());
		     }     
		}
	}
	
	/**
	 * W.I.P 
	 * this method is still under development and should not be used
	 */
	public void fetchEc2Instance(){		
		DescribeInstancesResult result= ec2Client.describeInstances();
		List <Reservation> list  = result.getReservations();
		for (Reservation res:list) {
		     List <Instance> instanceList= res.getInstances();
		     for (Instance instance:instanceList){
		             System.out.println("Instance Public IP :" + instance.getPublicIpAddress());
		             System.out.println("Instance ID:" + instance.getInstanceId());
		             System.out.println("Instance Type :" + instance.getInstanceType());
		             System.out.println("Instance Key Pair:" + instance.getKeyName());
		             System.out.println("Instance Key Pair:" + instance.getLaunchTime());
		     }     
		}
	}
	
	/**
	 * Cleanup all requests & instances initiated by this AmazonEC2Utility.
	 */
	public void cleanupInstances(){
		try {
		    // Cancel requests & terminate instances
		    CancelSpotInstanceRequestsRequest cancelRequest = new CancelSpotInstanceRequestsRequest(spotInstanceRequestIds);
		    ec2Client.cancelSpotInstanceRequests(cancelRequest);
		    TerminateInstancesRequest terminateRequest = new TerminateInstancesRequest(instanceIds);
		    ec2Client.terminateInstances(terminateRequest);
		    instanceIds.clear();
		    spotInstanceRequestIds.clear();
		    
		} catch (AmazonServiceException e) {
		    // Write out any exceptions that may have occurred.
		    System.out.println("Error cancelling requests & instances");
		    System.out.println("Caught Exception: " + e.getMessage());
		    System.out.println("Reponse Status Code: " + e.getStatusCode());
		    System.out.println("Error Code: " + e.getErrorCode());
		    System.out.println("Request ID: " + e.getRequestId());
		    SeleniumTest.logger.severe("Caught Exception: " + e.getMessage());
		    SeleniumTest.logger.severe("Reponse Status Code: " + e.getStatusCode());
		    SeleniumTest.logger.severe("Error Code: " + e.getErrorCode());
		    SeleniumTest.logger.severe("Request ID: " + e.getRequestId());		    
		}
	}
}
