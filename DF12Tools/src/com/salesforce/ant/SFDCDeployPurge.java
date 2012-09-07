package com.salesforce.ant;

import com.salesforce.ant.DeployTask;

public class SFDCDeployPurge extends DeployTask
{
	
  public SFDCDeployPurge()
  {
    this.deployOptions.setPurgeOnDelete(false);
  }

  public void setPurgeOnDelete(boolean purgeOnDelete) {
    this.deployOptions.setPurgeOnDelete(purgeOnDelete);
  }
}


