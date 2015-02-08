import hudson.model.*;
import jenkins.model.*;
import hudson.util.*;


def jenkins = Jenkins.instance
try {
  def uc = Jenkins.instance.updateCenter
  uc.updateAllSites();

  restart = false;

  workflow = jenkins.pluginManager.getPlugin("workflow-aggregator")
  if (workflow == null || workflow.versionNumber.isOlderThan(new VersionNumber("1.2"))) {
    uc.getPlugin("workflow-aggregator").deploy(false)
    restart = true;
  }

 if (restart)
    //jenkins.safeRestart();
} catch (Throwable t) {
    println("Error when upgrading plugins");
}
